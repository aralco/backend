package com.dev.backend.service;

import com.dev.backend.model.Product;
import com.dev.backend.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Transactional
    public Product createProduct(Product product)  {
        productDao.save(product);
        return product;
    }

    @Transactional
    public List<Product> getProducts()  {
        return productDao.findAll();
    }

    @Transactional
    public Product getProduct(String code)  {
        return productDao.findByCode(code);
    }

    @Transactional
    public String deleteProduct(String code)  {
        Product product = productDao.findByCode(code);
        productDao.delete(product);
        return "Product successfully deleted.";
    }

}
