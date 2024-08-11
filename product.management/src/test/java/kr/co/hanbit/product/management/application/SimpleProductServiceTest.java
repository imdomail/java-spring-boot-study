package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.EntityNotFoundException;
import kr.co.hanbit.product.management.presentation.ProductDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("prod")
class SimpleProductServiceTest {

    @Autowired
    SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품을 추가한 후 id로 조회하면 해당 상품이 조회되어야 한다.")
    void productAddAndFindByIdTest() {
        ProductDTO productDto = new ProductDTO("연필", 300, 20);

        ProductDTO savedProductDto = simpleProductService.add(productDto);
        Long savedProductId = savedProductDto.getId();

        ProductDTO foundProductDTO = simpleProductService.findById(savedProductId);

        assertEquals(savedProductDto.getId(), foundProductDTO.getId());
        assertEquals(savedProductDto.getName(), foundProductDTO.getName());
        assertEquals(savedProductDto.getPrice(), foundProductDTO.getPrice());
        assertEquals(savedProductDto.getAmount(), foundProductDTO.getAmount());
    }

    @Test
    @DisplayName("존재하지 않는 상품 id로 조회하면 EntityNotFoundException이 발생해야 한다.")
    void findProductNotExistIdTest() {
        Long notExistId = 1L;

        assertThrows(EntityNotFoundException.class, () -> {
            simpleProductService.findById(notExistId);
        });
    }

}