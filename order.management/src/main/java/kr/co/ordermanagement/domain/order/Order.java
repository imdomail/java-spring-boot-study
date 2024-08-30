package kr.co.ordermanagement.domain.order;

import java.util.List;

public class Order {
    private Long id;
    private List<OrderedProduct> orderedProducts;
    private Integer totalPrice;
    private State state;

    public Long getId() {
        return id;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public State getState() {
        return state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order() {}

    public Order(List<OrderedProduct> orderedProducts) {
        this.state = State.CREATED;
        this.orderedProducts = orderedProducts;
        this.totalPrice = orderedProducts.stream()
                .map(OrderedProduct::getTotalPrice)
                .reduce(0, Integer::sum);
    }


}
