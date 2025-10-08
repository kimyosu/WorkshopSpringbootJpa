package com.kimy.course.resources;

import com.kimy.course.entities.Order;
import com.kimy.course.entities.User;
import com.kimy.course.services.OrderService;
import com.kimy.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // indica que a classe é um recurso web implementado por um controlador REST
@RequestMapping(value = "/orders") // nome do recurso
public class OrderResource {
    @Autowired

    private OrderService service;

    @GetMapping // indica que o método responde a requisições do tipo GET
    //ResponseEntity é um tipo especial do Spring que representa uma resposta HTTP completa
    // ou seja, ele pode conter o corpo da resposta, código de status, cabeçal
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list); // retorna o objeto u dentro do corpo da resposta HTTP
         /*
        ok = código de status HTTP 200 que indica que a requisição foi bem-sucedida
        body(user) = o corpo da resposta HTTP conterá a representação do objeto u em formato
         */
    }
    @GetMapping(value = "/{id}") // indica que o método responde a requisições do tipo GET
        //e aceita um parâmetro id na URL
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order Order = service.findById(id);
        return ResponseEntity.ok().body(Order);
        /*
        ok = código de status HTTP 200 que indica que a requisição foi bem-sucedida
        body(user) = o corpo da resposta HTTP conterá a representação do objeto u em formato
         */
    }
}
