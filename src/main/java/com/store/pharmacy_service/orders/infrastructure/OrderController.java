package com.store.pharmacy_service.orders.infrastructure;

import com.store.pharmacy_service.orders.application.OrderService;
import com.store.pharmacy_service.orders.domain.models.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired private OrderService orderService;

    @PutMapping("/buys")
    public ResponseEntity<Boolean> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            this.orderService.placeOrder(orderRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
        return  ResponseEntity.ok(true);
    }

    @PutMapping("/sale")
    public ResponseEntity<Boolean> saleOrder(@RequestBody OrderRequest orderRequest) {
        try {
            this.orderService.saleOrder(orderRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok(true);
    }

}
