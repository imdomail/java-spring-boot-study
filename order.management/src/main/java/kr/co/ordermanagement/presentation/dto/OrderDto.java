package kr.co.ordermanagement.presentation.dto;

import java.util.List;

public class OrderDto {
    private Long id;
    private List<OrderedProductDto> orderedProducts;
    private Integer totalPrice;
    private String state;

    public String getState() {
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
}
