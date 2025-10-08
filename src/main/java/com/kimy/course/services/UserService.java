package com.kimy.course.services;

import com.kimy.course.entities.User;
import com.kimy.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return optionalUser.get();
    }
}
