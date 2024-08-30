package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.exception.LackOfProductAmountException;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.order.OrderedProduct;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.ChangeOrderStateRequestDto;
import kr.co.ordermanagement.presentation.dto.CreateOrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        orderedInfos.stream().forEach(orderedInfo -> {
            Product product = productRepository.findById(orderedInfo.getId());
            if (product.getAmount() - orderedInfo.getAmount() < 0) {
                throw new LackOfProductAmountException(orderedInfo.getId() + "번 상품의 수량이 부족합니다.");
            }
        });

        List<OrderedProduct> orderedProducts = new ArrayList<>();
        orderedInfos.forEach(orderedInfo -> {
            Product product = productRepository.findById(orderedInfo.getId());
            orderedProducts.add(new OrderedProduct(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    orderedInfo.getAmount()
            ));
            product.decreaseAmount(orderedInfo.getAmount());
            productRepository.update(product);
        });
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

    public OrderDto changeStateForce(Long orderId, ChangeOrderStateRequestDto request) {
        State state = request.getState();
        Order order = orderRepository.findById(orderId);
        order.setState(state);
        orderRepository.update(order);
        return OrderDto.toDto(order);
    }
}
