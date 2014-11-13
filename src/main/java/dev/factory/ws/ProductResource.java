package dev.factory.ws;

import dev.factory.model.Product;
import dev.factory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products",
        consumes = "application/json",
        produces = "application/json")
public class ProductResource {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> listProducts() {
        return productService.getProducts();
    }

    @RequestMapping(value = "{code}", method = RequestMethod.GET)
    public Product viewProduct(@PathVariable String code) {
        return productService.getProduct(code);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.DELETE)
    public CommonResponse deleteProduct(@PathVariable String code) {
        String message = productService.deleteProduct(code);
        return new CommonResponse(message);
    }


}
