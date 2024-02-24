package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/categories")
public class CategoryResource {

    /**
     * Implentações dos endpoints
     */
    @Autowired
    private CategoryService service;

    @GetMapping()
    //public ResponseEntity<List<Category>> findAll() {
    public ResponseEntity<List<CategoryDTO>> findAll() {

        List<CategoryDTO> list = service.findAll();

    /*  List<Category> list = new ArrayList<>();
        list.add(new Category(1L,"Books"));
        list.add(new Category(2L,"Electronics"));*/
        return ResponseEntity.ok().body(list);
        /**
         * ResponseEntity Ele vai encapsular uma resposta HTTP, o tipo do dado
         * vai ficar dentro do <>
         *  findAll = todas as categorias
         *  Corrigido para DTO
         */
    }

    /**
     * ResponseEntity CategoryDTO
     *
     * @param *Long id o PathVariable vai fazer um pré processamento na
     *              hora de compilar o web service
     * @return uma variavel que corresponde o id no GetMapping
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {

        CategoryDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
