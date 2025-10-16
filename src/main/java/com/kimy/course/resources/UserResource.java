package com.kimy.course.resources;

import com.kimy.course.entities.User;
import com.kimy.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping // indica que o método responde a requisições do tipo POST
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj); //service.insert(obj) salva o objeto no banco de dados e retorna o objeto salvo

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        /*
        SevletUriComponentsBuilder = classe do Spring que ajuda a construir URIs de forma dinâmica
        fromCurrentRequest() = inicia a construção da URI a partir da requisição atual
        path("/{id}") = Espaço reservado que sera substituído por um valor real depois
        buildAndExpand(obj.getId()) = substitui o {id} pelo valor do ID do objeto recém-criado
        toUri() = converte o resultado final em um objeto URI
         */
        //Retorna o código 201 (created) e informa a URI do novo recurso criado no cabeçalho Location da resposta HTTP
        return ResponseEntity.created(uri).body(obj);

    }

    @DeleteMapping(value = "/{id}") //indica que o método responde a requisições do tipo DELETE
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping(value = "/{id}") //indica que o método responde a requisições do tipo PUT
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        //@RequestBody = converte o corpo da requisição JSON em um objeto User

        obj = service.update(id, obj); //service.update vai retornar um novo objeto com os dados atualizados
        return ResponseEntity.ok().body(obj); //retorna o código 200 (ok) e o objeto atualizado no corpo da resposta
    }
}
