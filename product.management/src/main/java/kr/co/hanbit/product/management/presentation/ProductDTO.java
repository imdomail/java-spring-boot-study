package kr.co.hanbit.product.management.presentation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.hanbit.product.management.domain.Product;

import java.util.Objects;

public class ProductDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer amount;

    public ProductDTO() {}

    public ProductDTO(String name, Integer price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }


    public void setId(Long id) {
        this.id = id;
    }

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

    public static Product toEntity(ProductDTO productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setAmount(productDto.getAmount());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public static ProductDTO toDTO(Product product) {
        ProductDTO productDto = new ProductDTO(
                product.getName(),
                product.getPrice(),
                product.getAmount()
        );
        productDto.setId(product.getId());
        return productDto;
    }
}
