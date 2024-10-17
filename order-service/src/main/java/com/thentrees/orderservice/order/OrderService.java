package com.thentrees.orderservice.order;

import com.thentrees.orderservice.customer.ICustomerClient;
import com.thentrees.orderservice.exception.BusinessException;
import com.thentrees.orderservice.kafka.OrderConfirmation;
import com.thentrees.orderservice.kafka.OrderProducer;
import com.thentrees.orderservice.orderline.OrderLineRequest;
import com.thentrees.orderservice.orderline.OrderLineService;
import com.thentrees.orderservice.payment.PaymentClient;
import com.thentrees.orderservice.payment.PaymentRequest;
import com.thentrees.orderservice.product.ProductClient;
import com.thentrees.orderservice.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final ICustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;
    private final OrderProducer orderProducer;

    @Transactional
    public Integer createOrder(OrderRequest orderRequest) {
        var customer = customerClient.findCustomerById(orderRequest.getCustomerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchaseProducts = productClient.purchaseProducts(orderRequest.getProducts());
        var order = this.orderRepository.save(mapper.toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest:orderRequest.getProducts()){
            orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(),purchaseRequest.getProductId(),purchaseRequest.getQuantity()));
        }
        // todo start payment process
        orderProducer.sendOrderConfirmation(new OrderConfirmation(orderRequest.getReference(),
                orderRequest.getAmount(),
                orderRequest.getPaymentMethod(),
                customer,
                purchaseProducts));

        var paymentRequest = new PaymentRequest(
                orderRequest.getAmount(),
                orderRequest.getPaymentMethod(),
                orderRequest.getId(),
                orderRequest.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }


    public OrderResponse findById(Integer id) {
        return this.orderRepository.findById(id)
                .map(this.mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
