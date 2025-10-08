package com.kimy.course.services;

import com.kimy.course.entities.Order;
import com.kimy.course.entities.User;
import com.kimy.course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository OrderRepository;
    public List<Order> findAll() {
        return OrderRepository.findAll(); // busca todos os usuários no banco de dados
    }
    public Order findById(Long id){
        Optional<Order> optionalOrder =  OrderRepository.findById(id);
        return optionalOrder.get();
    }
}
