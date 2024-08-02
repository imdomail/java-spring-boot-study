package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.infrastructure.DatabaseProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private DatabaseProductRepository databaseProductRepository;
    private ModelMapper modelMapper;
    private ValidationService validationService;

    @Autowired
    SimpleProductService(DatabaseProductRepository databaseProductRepository,  ModelMapper modelMapper, ValidationService validationService) {
        this.databaseProductRepository = databaseProductRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    public ProductDTO add(ProductDTO productDto) {

        Product product = modelMapper.map(productDto, Product.class);
        validationService.checkValid(product);
        Product savedProduct = databaseProductRepository.add(product);
        ProductDTO savedProductDTO = modelMapper.map(savedProduct, ProductDTO.class);
        return savedProductDTO;
    }

    public ProductDTO findById(Long id) {
        Product product = databaseProductRepository.findById((id));
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> findAll() {
        List<Product> products = databaseProductRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        return productDTOS;
    }

    public List<ProductDTO> findByNameContaining(String name) {
        List<Product> products = databaseProductRepository.findByNameContaining(name);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    public ProductDTO update(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product updatedProduct = databaseProductRepository.update(product);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    public void delete(Long id) {
        databaseProductRepository.delete(id);
    }
}
