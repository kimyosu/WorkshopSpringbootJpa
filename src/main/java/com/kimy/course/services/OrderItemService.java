package com.kimy.course.services;

import com.kimy.course.entities.OrderItem;
import com.kimy.course.entities.User;
import com.kimy.course.repositories.OrderItemRepository;
import com.kimy.course.repositories.OrderRepository;
import com.kimy.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll(); // busca todos os usuários no banco de dados
    }
    public OrderItem findById(Long id){
        Optional<OrderItem> optionalOrder =  orderItemRepository.findById(id);
        return optionalOrder.get();
    }
}
