package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entity.Category;
import com.devsuperior.dscatalog.repository.CategoryRepository;

import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    /**
     * Dependencia do CategoryRepository
     */
    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly = true)
    //public List<Category> findAll(){
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        return list.stream().map(cat -> new CategoryDTO(cat)).collect(Collectors.toList());

    }

    @Transactional
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));

        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);

        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) throws EntityNotFoundException{
        try {
        Category entity = repository.getOne(id);
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado" + id);
        }
    }
}

/**
 * Tudo que tiver uma transação com o banco, você deve adicionar a anotação @Transactional
 * para somente leitura, tem que adicionar readOnly=true
 * Com o ajuste da DTO, agora a List vai ser via DTO
 * O corre um erro no return repository porque ele atua com entidade e nã ocom DTO
 * Agora teremos de declarar um List<Category></Category> list recebendo o repository.findAll
 * Agora vai ser convertido a lista Category em uma DTO
 */
/**
 * vai converter a coleção normal (lista) em uma stream.
 * .stream() vai trabalhar com funções de alta ordem, com expressões lambda que é um recurso de
 * programação funcional
 * map vai transformar cada elemento original em uma outra coisa. ELa aplica uma função a cada elemento
 * da sua lista. Eu irei transformar lista do tipo category para uma nova lista categoryDto
 *
 */
/**
 * o orElseThrow vai lançar uma excecão se o resultado não for o esperado
 */

/**
 * Teremos que converter para um objeto do tipo Category para fazer o metodo insert
 * não vai ser feito o entity.setId poís o id é nulo e vai ser o banco de dados que vai atribuir
 * o id autoincremental
 * @return new CategoryDTO como argumento o entity;
 */

/**
 * update category
 * Teremos que instanciar o objeto do tipo Category nesse novo serviço
 * Diferença entre findById par o GetOne
 * findById efetiva o acesso ao banco de dados
 * GetOne não "toca" no banco de dados, ele vai instanciar um objeto provisorio
 * o id no objeto. Quando for encaminhado para salvar que ele vai para o bd
 * Teremos que criar um try/catch pois se for enviado um id errado, ele vai
 * gerar uma exceção
 */