package kr.co.ordermanagement.presentation.dto;

public class CreateOrderRequestDto {
    private Long id;
    private Integer amount;

    public CreateOrderRequestDto() {}

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}
