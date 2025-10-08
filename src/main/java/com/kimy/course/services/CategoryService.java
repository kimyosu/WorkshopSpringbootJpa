package com.kimy.course.services;

import com.kimy.course.entities.Category;
import com.kimy.course.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository CategoryRepository;
    public List<Category> findAll() {
        return CategoryRepository.findAll(); // busca todos os usuários no banco de dados
    }
    public Category findById(Long id){
        Optional<Category> optionalCategory =  CategoryRepository.findById(id);
        return optionalCategory.get();
    }
}
