package com.kimy.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kimy.course.entities.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "tb_order") // nome da tabela no banco de dados
@Entity // indica que a classe é uma entidade JPA
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment no MySQL
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    // formata a data no padrão ISO 8601 para o JSON de saída
    private Instant moment;

    //Integer para armazenar o código do enum no banco de dados
    private Integer orderStatus;

    //******Cada Order precisa saber a qual User pertence, então a FK vai em Order (client_id).******
    @ManyToOne // muitos para um com User
    @JoinColumn(name = "client_id") // nome da chave estrangeira na tabela Order
    // Um User pode fazer muitos pedidos (Orders) e um Order pode ter um User (client)
    private User client;

    /*
    id = Atributo chamado id dentro do OrderItem, esse id é um OrderItemPk, dentro dele temos um order, que é onde estamos
        acessando
    mappedBy = estamos dizendo que a chave estrangeira está lá no id.order
    Esse id.order que tem a chave estrangeira da Order(classe atual desse comentario)
    */

    @OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER)
    private Set<OrderItem> items = new HashSet<>();


    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus,  User client) {

        this.id = id;
        this.moment = moment;
        this.client = client;
        setOrderStatus(orderStatus); // Usando o método setOrderStatus para garantir a conversão correta
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        //Esse orderStatus atributo é um Integer aqui na classe Order
        return OrderStatus.valueOf(orderStatus);
        //Método estático valueOf(int code) retorna  um OrderStatus correspondente ao código armazenado
            //Exemplo se orderStatus for 2, retorna OrderStatus.PAID
        //Ele pega um numero inteiro e converte para o enum OrderStatus
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) { // se o código for diferente de nulo
            this.orderStatus = orderStatus.getCode(); // armazena o código do enum no atributo orderStatus
            //getCode retorna o código do enum(integer)
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
