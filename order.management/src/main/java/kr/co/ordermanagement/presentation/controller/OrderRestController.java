package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.presentation.dto.ChangeOrderStateRequestDto;
import kr.co.ordermanagement.presentation.dto.CreateOrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderRestController {

    private SimpleOrderService simpleOrderService;

    @Autowired
    OrderRestController(SimpleOrderService simpleOrderService) {
        this.simpleOrderService = simpleOrderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<OrderDto> createOrder(@RequestBody List<CreateOrderRequestDto> request) {
        OrderDto orderDto = simpleOrderService.createOrder(request);
        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PATCH)
    public ResponseEntity<OrderDto> changeOrderStateForce(
            @PathVariable Long orderId,
            @RequestBody ChangeOrderStateRequestDto request
    ) {
        return ResponseEntity.ok(new OrderDto());
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderDto> findOrderById(@PathVariable Long orderId) {
        OrderDto orderDto = simpleOrderService.findById(orderId);
        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDto>> findOrdersByState(@RequestParam State state) {
        List<OrderDto> orderDtos = simpleOrderService.findByState(state);
        return ResponseEntity.ok(orderDtos);
    }

    @RequestMapping(value = "/orders/{orderId}/cancel", method = RequestMethod.PATCH)
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(new OrderDto());
    }
}
