package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.EntityNotFoundException;
import kr.co.hanbit.product.management.presentation.ProductDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SimpleProductServiceTest {

    @Autowired
    SimpleProductService simpleProductService;

//    @Transactional
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

//    @Transactional
    @Test
    @DisplayName("상품을 추가한 후 전체 조회를 하면 추가한 상품들이 조회되어야 한다.")
    void productAddAndFindAllTest() {
        ProductDTO productDto1 = new ProductDTO("색연필", 200, 10);
        ProductDTO productDto2 = new ProductDTO("비누방울", 300, 1);
        ProductDTO productDto3 = new ProductDTO("장갑", 1000, 2);

        ProductDTO savedProductDto1 = simpleProductService.add(productDto1);
        ProductDTO savedProductDto2 = simpleProductService.add(productDto2);
        ProductDTO savedProductDto3 = simpleProductService.add(productDto3);

        List<ProductDTO> productDtos = simpleProductService.findAll();

        System.out.println(productDtos);
        int idx1 = productDtos.indexOf(savedProductDto1);
        assertTrue(idx1 > 0);
        ProductDTO foundProduct1 = productDtos.get(idx1);
        assertEquals(foundProduct1.getId(), savedProductDto1.getId());
        assertEquals(foundProduct1.getName(), savedProductDto1.getName());
        assertEquals(foundProduct1.getPrice(), savedProductDto1.getPrice());
        assertEquals(foundProduct1.getAmount(), savedProductDto1.getAmount());

        int idx2 = productDtos.indexOf(savedProductDto2);
        assertTrue(idx2 > 0);
        ProductDTO foundProduct2 = productDtos.get(idx2);
        assertEquals(foundProduct2.getId(), savedProductDto2.getId());
        assertEquals(foundProduct2.getName(), savedProductDto2.getName());
        assertEquals(foundProduct2.getPrice(), savedProductDto2.getPrice());
        assertEquals(foundProduct2.getAmount(), savedProductDto2.getAmount());

        int idx3 = productDtos.indexOf(savedProductDto3);
        assertTrue(idx3 > 0);
        ProductDTO foundProduct3 = productDtos.get(idx3);
        assertEquals(foundProduct3.getId(), savedProductDto3.getId());
        assertEquals(foundProduct3.getName(), savedProductDto3.getName());
        assertEquals(foundProduct3.getPrice(), savedProductDto3.getPrice());
        assertEquals(foundProduct3.getAmount(), savedProductDto3.getAmount());
    }

//    @Transactional
    @Test
    @DisplayName("상품 이름으로 조회하면 해당 상품들이 조회되어야 한다.")
    void findByNameContainingTest() {
        simpleProductService.add(new ProductDTO("색연필", 200, 10));
        simpleProductService.add(new ProductDTO("비누방울", 300, 1));
        simpleProductService.add(new ProductDTO("장갑", 1000, 2));

        String keyword = "연필";
        List<ProductDTO> productDtos = simpleProductService.findByNameContaining(keyword);
        assertTrue(productDtos.stream().allMatch(productDto -> productDto.getName().contains(keyword)));
    }

//    @Transactional
    @Test
    @DisplayName("상품을 업데이트하면 해당 상품 정보가 바뀌어야 한다.")
    void productUpdateTest() {
        ProductDTO productDto = new ProductDTO("곰인형시즌1", 3000, 100);

        ProductDTO savedProductDto = simpleProductService.add(productDto);
        Long savedProductId = savedProductDto.getId();

        ProductDTO updatedProductDto = new ProductDTO("곰인형시즌2", 3001, 101);
        updatedProductDto.setId(savedProductId);
        simpleProductService.update(updatedProductDto);

        ProductDTO foundProductDto = simpleProductService.findById(savedProductId);

        assertEquals(foundProductDto.getId(), updatedProductDto.getId());
        assertEquals(foundProductDto.getName(), updatedProductDto.getName());
        assertEquals(foundProductDto.getPrice(), updatedProductDto.getPrice());
        assertEquals(foundProductDto.getAmount(), updatedProductDto.getAmount());
    }

//    @Transactional
    @Test
    @DisplayName("상품을 삭제한 뒤 조회하면 해당 상품이 존재하지 않아야 한다.")
    void productDeleteTest() {
        simpleProductService.add(new ProductDTO("색연필", 200, 10));
        ProductDTO savedProductDto = simpleProductService.add(new ProductDTO("비누방울", 300, 1));
        simpleProductService.add(new ProductDTO("장갑", 1000, 2));

        Long productIdToDelete = savedProductDto.getId();
        simpleProductService.delete(productIdToDelete);

        assertThrows(EntityNotFoundException.class, () -> {
            simpleProductService.findById(productIdToDelete);
        });

        assertFalse(simpleProductService.findAll().contains(savedProductDto));
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