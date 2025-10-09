package com.kimy.course.services;

import com.kimy.course.entities.Product;
import com.kimy.course.entities.User;
import com.kimy.course.repositories.ProductRepository;
import com.kimy.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll(); // busca todos os usuários no banco de dados
    }
    public Product findById(Long id){
        Optional<Product> optionalProduct =  productRepository.findById(id);
        return optionalProduct.get();
    }
}
