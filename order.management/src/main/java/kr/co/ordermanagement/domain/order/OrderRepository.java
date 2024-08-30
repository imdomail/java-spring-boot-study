package kr.co.ordermanagement.domain.order;

public interface OrderRepository {
    public Order add(Order order);
    public Order findById(Long id);
}
