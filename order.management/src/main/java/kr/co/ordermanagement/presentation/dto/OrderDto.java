package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderedProduct;
import kr.co.ordermanagement.domain.order.State;

import java.util.List;

public class OrderDto {
    private Long id;
    private List<OrderedProductDto> orderedProducts;
    private Integer totalPrice;
    private State state;

    public State getState() {
        return state;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public List<OrderedProductDto> getOrderedProducts() {
        return orderedProducts;
    }

    public Long getId() {
        return id;
    }

    public OrderDto() {}

    public OrderDto(Long id, List<OrderedProduct> orderedProducts, Integer totalPrice, State state) {
        this.id = id;
        this.orderedProducts = orderedProducts.stream().map(OrderedProductDto::toDto).toList();
        this.totalPrice = totalPrice;
        this.state = state;
    }

    public static OrderDto toDto(Order order) {
        return new OrderDto(
            order.getId(),
            order.getOrderedProducts(),
            order.getTotalPrice(),
            order.getState()
        );
    }
}
