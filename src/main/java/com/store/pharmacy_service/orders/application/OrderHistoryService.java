package com.store.pharmacy_service.orders.application;

import com.store.pharmacy_service.orders.domain.entities.OrderItem;
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
        List<OrderItem> orderItems = this.orderItemRepository.getOrderItemByOrderType(type.name());
        return orderItems.stream().map(this::orderItemHistoryResponse).parallel().toList();
    }

    private OrderItemHistoryResponse orderItemHistoryResponse(OrderItem orderItem) {
        return OrderItemHistoryResponse.builder()
                .id(orderItem.getId())
                .product(MapProduct.mapToProductResponse(orderItem.getProduct()))
                .quantity(orderItem.getQuantity())
                .subTotal(orderItem.getSubTotal())
                .unitPrice(orderItem.getPrice())
                .orderDate(orderItem.getDate())
                .build();
    }

}
