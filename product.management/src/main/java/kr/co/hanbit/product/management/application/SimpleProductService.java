package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private ProductRepository productRepository;
    private ValidationService validationService;

    @Autowired
    SimpleProductService(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    public ProductDTO add(ProductDTO productDto) {

        Product product = ProductDTO.toEntity(productDto);
        validationService.checkValid(product);
        Product savedProduct = productRepository.add(product);
        ProductDTO savedProductDTO = ProductDTO.toDTO(savedProduct);
        return savedProductDTO;
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById((id));
        return ProductDTO.toDTO(product);
    }

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> ProductDTO.toDTO(product))
                .toList();
        return productDTOS;
    }

    public List<ProductDTO> findByNameContaining(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        return products.stream()
                .map(product -> ProductDTO.toDTO(product))
                .toList();
    }

    public ProductDTO update(ProductDTO productDTO) {
        Product product = ProductDTO.toEntity(productDTO);
        Product updatedProduct = productRepository.update(product);
        return ProductDTO.toDTO(updatedProduct);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
