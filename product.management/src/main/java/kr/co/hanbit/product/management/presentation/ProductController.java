package kr.co.hanbit.product.management.presentation;

import kr.co.hanbit.product.management.application.SimpleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private SimpleProductService simpleProductService;

    @Autowired
    ProductController(SimpleProductService simpleProductService) {
        simpleProductService = simpleProductService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDTO createProduct(@RequestBody ProductDTO productDto) {
        return simpleProductService.add(productDto);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDTO findProductById(@PathVariable Long id) {
        return simpleProductService.findById((id));
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDTO> findProducts(@RequestParam(required = false) String name) {
        if (null == name) {
            return simpleProductService.findAll();
        }
        return simpleProductService.findByNameContaining(name);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ProductDTO updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO
    ) {
        productDTO.setId(id);
        return simpleProductService.update(productDTO);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
        simpleProductService.delete(id);
    }

}
