package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SimpleProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품 추가 후에는 추가된 상품이 반환되어야 한다.")
    void productAddTest() {
        ProductDTO productDto = new ProductDTO("연필", 300, 20);
        Long PRODUCT_ID = 1L;

        Product product = ProductDTO.toEntity(productDto);
        product.setId(PRODUCT_ID);
        when(productRepository.add(any())).thenReturn(product);

        ProductDTO savedProductDto = simpleProductService.add(productDto);

        assertEquals(savedProductDto.getId(), PRODUCT_ID);
        assertEquals(savedProductDto.getName(), productDto.getName());
        assertEquals(savedProductDto.getPrice(), productDto.getPrice());
        assertEquals(savedProductDto.getAmount(), productDto.getAmount());
    }
}