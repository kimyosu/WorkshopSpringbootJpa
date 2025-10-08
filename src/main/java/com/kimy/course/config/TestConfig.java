package com.kimy.course.config;

import com.kimy.course.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.kimy.course.repositories.UserRepository;

import java.util.Arrays;

@Configuration
@Profile("test") // indica que a classe só será carregada quando o perfil "test" estiver ativo
public class TestConfig implements CommandLineRunner {
    @Autowired // injeta a dependência do UserRepository ou seja instancia
    // o objeto userRepository automaticamente
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1  = new User(null, "Maria Marque", "maria@gmail.com","888218", "12345");
        User user2  = new User(null, "Kimy Sysout", "kimy@gmail.com","199213129", "asda5");
        userRepository.saveAll(Arrays.asList(user1, user2));
        // salva os usuários no banco de dados em lote
    }
}
