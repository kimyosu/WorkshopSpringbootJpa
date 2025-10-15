package com.kimy.course.resources;

import com.kimy.course.entities.User;
import com.kimy.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController // indica que a classe é um recurso web implementado por um controlador REST
@RequestMapping(value = "/users") // nome do recurso
public class UserResource {
    @Autowired

    private UserService service;

    @GetMapping // indica que o método responde a requisições do tipo GET
    //ResponseEntity é um tipo especial do Spring que representa uma resposta HTTP completa
    // ou seja, ele pode conter o corpo da resposta, código de status, cabeçal
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list); // retorna o objeto u dentro do corpo da resposta HTTP
         /*
        ok = código de status HTTP 200 que indica que a requisição foi bem-sucedida
        body(user) = o corpo da resposta HTTP conterá a representação do objeto u em formato
         */
    }
    @GetMapping(value = "/{id}") // indica que o método responde a requisições do tipo GET
        //e aceita um parâmetro id na URL
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = service.findById(id);
        return ResponseEntity.ok().body(user);
        /*
        ok = código de status HTTP 200 que indica que a requisição foi bem-sucedida
        body(user) = o corpo da resposta HTTP conterá a representação do objeto u em formato
         */
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
