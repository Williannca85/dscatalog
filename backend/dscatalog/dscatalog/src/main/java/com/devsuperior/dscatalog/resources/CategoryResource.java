package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    /**
     * metodo insert, para inserir uma categoria os dados devem vir como um objeto
     * que pode ter varios paramentros. Por está razão o parametro será o CategoryDTO
     * Para o endpoint reconher o objeto que vai ser enviado na requisição e caso esse objeto com
     * esse dto teremos que adicionar a Annotation @RequestBody.
     * Quando a uma inserção no padrão Rest, o endpoint deve ser um POST
     * Usaremos um macete para criar um HTTP com resposta 201, por se tratar que ele foi criado
     * Vamos utilizar uma variavel do tipo URI
     * @return ResponseEntity.created como argumento a (uri) e o corpo              body(dto);
     */
    @PostMapping()
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
        //A variavel dto vai receber service de metodo insert que vai receber o dto
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);

    }
}
