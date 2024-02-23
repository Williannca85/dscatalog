package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.entity.Category;
import com.devsuperior.dscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    /**
     * Dependencia do CategoryRepository
     */
    @Autowired
    private CategoryRepository repository;

    /**
     *
     * Tudo que tiver uma transação com o banco, você deve adicionar a anotação @Transactional
     * para somente leitura, tem que adicionar readOnly=true
     */
    @Transactional(readOnly = true)
    public List<Category> findAll(){
        return repository.findAll();

    }
}
