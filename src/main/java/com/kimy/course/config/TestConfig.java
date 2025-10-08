package com.kimy.course.config;

import com.kimy.course.entities.Category;
import com.kimy.course.entities.Order;
import com.kimy.course.entities.User;
import com.kimy.course.entities.enums.OrderStatus;
import com.kimy.course.repositories.CategoryRepository;
import com.kimy.course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.kimy.course.repositories.UserRepository;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test") // indica que a classe só será carregada quando o perfil "test" estiver ativo
public class TestConfig implements CommandLineRunner {
    @Autowired // injeta a dependência do UserRepository ou seja instancia
    // o objeto userRepository automaticamente
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));


        //#region User and Order instances
        User user1  = new User(null, "Maria Marque", "maria@gmail.com","888218", "12345");
        User user2  = new User(null, "Kimy Sysout", "kimy@gmail.com","199213129", "asda5");
        Order order = new Order(null, Instant.parse("2024-06-21T19:53:07Z"), OrderStatus.PAID, user1);
        Order order2 = new Order(null, Instant.parse("2024-08-13T19:41:07Z"),OrderStatus.CANCELED, user2);
        //#endregion


        // salva os usuários no banco de dados em lote
        userRepository.saveAll(Arrays.asList(user1, user2));

        // salva os pedidos no banco de dados em lote
        orderRepository.saveAll(Arrays.asList(order, order2));
    }
}
