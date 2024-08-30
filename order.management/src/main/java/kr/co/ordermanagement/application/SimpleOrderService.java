package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.order.OrderedProduct;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.CreateOrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleOrderService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Autowired
    public SimpleOrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(List<CreateOrderRequestDto > orderedInfos) {
        List<OrderedProduct> orderedProducts = orderedInfos.stream().map(orderedInfo -> {
            Product foundProduct = productRepository.findById(orderedInfo.getId());
            return new OrderedProduct(
                foundProduct.getId(),
                foundProduct.getName(),
                foundProduct.getPrice(),
                orderedInfo.getAmount()
            );
        }).toList();

        // TODO: 재고 확인, 재고 수량 감소
        Order order = orderRepository.add(new Order(orderedProducts));
        return OrderDto.toDto(order);
    }

    public OrderDto findById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        return OrderDto.toDto(order);
    }

    public List<OrderDto> findByState(State state) {
        return orderRepository.findByState(state).stream()
                .map(OrderDto::toDto)
                .toList();
    }

    public OrderDto cancel(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.setState(State.CANCELED);
        orderRepository.update(order);
        return OrderDto.toDto(order);
    }
}
