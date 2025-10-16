package com.kimy.course.services;

import com.kimy.course.entities.User;
import com.kimy.course.repositories.UserRepository;
import com.kimy.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll() {
        return userRepository.findAll(); // busca todos os usuários no banco de dados
    }
    public User findById(Long id){
        Optional<User> optionalUser =  userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new ResourceNotFoundException(id));
        // se o usuário não for encontrado, lança uma exceção ResourceNotFoundException
    }

    public User insert(User obj){
        return userRepository.save(obj);
        //.save retorna o objeto, por isso juntei com o return
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User update(Long id, User obj){
        /* getReferenceById = instancia um objeto sem ir no banco de dados, diferente do findById que já vai no banco de dados
         o getReferenceById só vai no banco de dados quando for necessário */

        User entity = userRepository.getReferenceById(id);

        // atualiza os dados da entidade com base nos dados do obj
        updateDta(entity, obj);
        return userRepository.save(entity);
    }

    private void updateDta(User entity, User obj) {
        // atualiza os dados da entidade com base nos dados do obj
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
