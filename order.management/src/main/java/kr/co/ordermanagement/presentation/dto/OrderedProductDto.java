package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.order.OrderedProduct;

public class OrderedProductDto {
    public Long id;
    public String name;
    public Integer price;
    public Integer amount;

    public OrderedProductDto(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public static OrderedProductDto toDto(OrderedProduct orderedProduct) {
        return new OrderedProductDto(
                orderedProduct.getId(),
                orderedProduct.getName(),
                orderedProduct.getPrice(),
                orderedProduct.getAmount()
        );
    }
}
