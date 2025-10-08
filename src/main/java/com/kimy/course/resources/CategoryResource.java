package com.kimy.course.resources;

import com.kimy.course.entities.Category;
import com.kimy.course.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // indica que a classe é um recurso web implementado por um controlador REST
@RequestMapping(value = "/categories") // nome do recurso
public class CategoryResource {
    @Autowired
    private CategoryService service;

    @GetMapping // indica que o método responde a requisições do tipo GET
    //ResponseEntity é um tipo especial do Spring que representa uma resposta HTTP completa
    // ou seja, ele pode conter o corpo da resposta, código de status, cabeçal
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list); // retorna o objeto u dentro do corpo da resposta HTTP
         /*
        ok = código de status HTTP 200 que indica que a requisição foi bem-sucedida
        body(user) = o corpo da resposta HTTP conterá a representação do objeto u em formato
         */
    }
    @GetMapping(value = "/{id}") // indica que o método responde a requisições do tipo GET
        //e aceita um parâmetro id na URL
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category category = service.findById(id);
        return ResponseEntity.ok().body(category);
        /*
        ok = código de status HTTP 200 que indica que a requisição foi bem-sucedida
        body(user) = o corpo da resposta HTTP conterá a representação do objeto u em formato
         */
    }
}
