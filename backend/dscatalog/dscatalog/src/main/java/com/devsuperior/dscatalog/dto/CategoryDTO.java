package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    /**
     *
     * @param entity
     * Esse construtor vai povoar o CategoryDTO por se tratar que estou usando o entity como arguemnto
     */
    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
