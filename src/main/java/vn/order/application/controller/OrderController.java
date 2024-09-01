package vn.order.application.controller;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vn.order.application.request.CreateOrderRequest;
import vn.order.application.response.OnlineResponse;
import vn.order.domain.model.Order;
import vn.order.domain.service.OrderService;


@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping(value = "/createOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity"),
            @ApiResponse(responseCode = "500", description = "System Error")
    })
    @Transactional
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        Order order = orderService.createOrder(Order.builder()
                .shopId(createOrderRequest.getShopId())
                .menuIds(createOrderRequest.getMenuIds())
                .customerId(createOrderRequest.getCustomerId()).build());
            return ResponseEntity.ok(order);

    }

}
