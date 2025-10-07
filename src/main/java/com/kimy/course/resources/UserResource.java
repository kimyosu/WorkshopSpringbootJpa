package com.kimy.course.resources;

import com.kimy.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // indica que a classe é um recurso web implementado por um controlador REST
@RequestMapping(value = "/users") // nome do recurso
public class UserResource {

    @GetMapping // indica que o método responde a requisições do tipo GET
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Maria", "maria@gmail.com", "999999999", "123456");
        return ResponseEntity.ok().body(u); // retorna o objeto u dentro do corpo da resposta HTTP

    }
}
