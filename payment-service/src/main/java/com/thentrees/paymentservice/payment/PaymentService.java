package com.thentrees.paymentservice.payment;

import com.thentrees.paymentservice.notification.NotificationProducer;
import com.thentrees.paymentservice.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;
    public Integer createPayment(PaymentRequest request) {
        var payment = this.paymentRepository.save(paymentMapper.toPayment(request));
        // handle event notification
        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.getOrderReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        request.getCustomer().getFirstName(),
                        request.getCustomer().getLastName(),
                        request.getCustomer().getEmail()
                )
        );
        return payment.getId();
    }
}
