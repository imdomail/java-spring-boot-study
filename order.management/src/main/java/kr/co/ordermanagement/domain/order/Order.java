package kr.co.ordermanagement.domain.order;

import java.util.List;
import java.util.Objects;

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

    public void setState(State state) {
        this.state = state;
    }

    public Order() {}

    public Order(List<OrderedProduct> orderedProducts) {
        this.state = State.CREATED;
        this.orderedProducts = orderedProducts;
        this.totalPrice = orderedProducts.stream()
                .map(OrderedProduct::getTotalPrice)
                .reduce(0, Integer::sum);
    }

    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

}
