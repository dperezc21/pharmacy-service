package com.store.pharmacy_service.orders.application;

import com.store.pharmacy_service.orders.domain.entities.Order;
import com.store.pharmacy_service.orders.domain.entities.OrderItem;
import com.store.pharmacy_service.orders.domain.models.OrderHistoryResponse;
import com.store.pharmacy_service.orders.domain.models.OrderItemHistoryResponse;
import com.store.pharmacy_service.orders.domain.models.OrderType;
import com.store.pharmacy_service.orders.domain.repositories.OrderItemRepository;
import com.store.pharmacy_service.products.utils.mappers.MapProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryService {
    @Autowired private OrderItemRepository orderItemRepository;

    public List<OrderItemHistoryResponse> getHistory(OrderType type) {
        List<OrderItem> orderItems = this.orderItemRepository.getOrderItemByOrderOrderType(type.name());
        return orderItems.stream().map(this::orderItemHistoryResponse).parallel().toList();
    }

    private OrderHistoryResponse getOrderHistoryResponse(Order order) {
        return OrderHistoryResponse.builder()
                .id(order.getId())
                .date(order.getDate())
                .total(order.getTotal())
                .observations(order.getObservations())
                .details(order.getOrderItems().stream().map(this::orderItemHistoryResponse).toList())
                .build();
    }

    private OrderItemHistoryResponse orderItemHistoryResponse(OrderItem orderItem) {
        return OrderItemHistoryResponse.builder()
                .id(orderItem.getId())
                .product(MapProduct.mapToProductResponse(orderItem.getProduct()))
                .quantity(orderItem.getQuantity())
                .subTotal(orderItem.getSubTotal())
                .unitPrice(orderItem.getPrice())
                .orderDate(orderItem.getOrder().getDate())
                .build();
    }

}
