package kr.co.ordermanagement.domain.order;

public class OrderedProduct {
    public Long id;
    public String name;
    public Integer price;
    public Integer amount;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getTotalPrice() {
        return price * amount;
    }

    public OrderedProduct(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
