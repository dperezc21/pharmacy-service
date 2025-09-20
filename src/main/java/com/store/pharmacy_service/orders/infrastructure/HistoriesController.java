package com.store.pharmacy_service.orders.infrastructure;

import com.store.pharmacy_service.orders.application.OrderHistoryService;
import com.store.pharmacy_service.orders.domain.models.OrderItemHistoryResponse;
import com.store.pharmacy_service.orders.domain.models.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/histories")
public class HistoriesController {

    @Autowired private OrderHistoryService orderHistoryService;

    @GetMapping("/sales")
    public ResponseEntity<List<OrderItemHistoryResponse>> getSalesHistory() {
        return ResponseEntity.ok(this.orderHistoryService.getHistory(OrderType.SALE));
    }

    @GetMapping("/buys")
    public ResponseEntity<List<OrderItemHistoryResponse>> getPurchasesHistory() {
        return ResponseEntity.ok(this.orderHistoryService.getHistory(OrderType.BUYS));
    }
}
