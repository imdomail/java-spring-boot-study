package kr.co.ordermanagement.domain.order;

import java.util.List;

public interface OrderRepository {
    public Order add(Order order);
    public Order findById(Long id);
    public List<Order> findByState(State state);
    public void update(Order order);
}
