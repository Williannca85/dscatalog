package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.entity.Category;
import com.devsuperior.dscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    /**
     * Dependencia do CategoryRepository
     */
    @Autowired
    private CategoryRepository repository;
    public List<Category> findAll(){
        return repository.findAll();

    }
}