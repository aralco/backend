package dev.factory.service;

import dev.factory.dao.ProductDao;
import dev.factory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Transactional
    public List<Product> getProducts()  {
        return productDao.findAll();
    }
}
