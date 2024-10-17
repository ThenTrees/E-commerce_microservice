package com.thentrees.notification.kafka;

import com.thentrees.notification.email.EmailService;
import com.thentrees.notification.kafka.order.OrderConfirmation;
import com.thentrees.notification.kafka.payment.PaymentConfirmation;
import com.thentrees.notification.kafka.payment.PaymentMethod;
import com.thentrees.notification.notification.Notification;
import com.thentrees.notification.notification.NotificationRepository;
import com.thentrees.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumerPaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic Topic:: {}", paymentConfirmation.toString());
        notificationRepository.save(Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                .build());

        var customerName = paymentConfirmation.getCustomerFirstName() + " " +paymentConfirmation.getCustomerLastName();
        // event send email
        emailService.sendPaymentSuccessMail(
                paymentConfirmation.getCustomerEmail(),
                customerName,
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumerOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic Topic:: {}", orderConfirmation.toString());
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // event send mail
        var customerName = orderConfirmation.getCustomer().getFirstName() + " " +orderConfirmation.getCustomer().getLastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.getCustomer().getEmail(),
                customerName,
                orderConfirmation.getTotalAmount(),
                orderConfirmation.getOrderReference(),
                orderConfirmation.getProducts()
        );
    }


}
