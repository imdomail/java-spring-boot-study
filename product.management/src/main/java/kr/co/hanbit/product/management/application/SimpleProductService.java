package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.infrastructure.ListProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private ListProductRepository listProductRepository;
    private ModelMapper modelMapper;

    @Autowired
    SimpleProductService(ListProductRepository listProductRepository, ModelMapper modelMapper) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDTO add(ProductDTO productDto) {

        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = listProductRepository.add(product);
        ProductDTO savedProductDTO = modelMapper.map(savedProduct, ProductDTO.class);
        return savedProductDTO;
    }

    public ProductDTO findById(Long id) {
        Product product = listProductRepository.findById((id));
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> findAll() {
        List<Product> products = listProductRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        return productDTOS;
    }

    public List<ProductDTO> findByNameContaining(String name) {
        List<Product> products = listProductRepository.findByNameContaining(name);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    public ProductDTO update(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product updatedProduct = listProductRepository.update(product);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    public void delete(Long id) {
        listProductRepository.delete(id);
    }
}
