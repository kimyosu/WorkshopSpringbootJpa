package com.kimy.course.entities.enums;

public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);
    private int code;

    private OrderStatus(int code) {
        this.code = code;
    }
    public int getCode() { //retorna o codigo do enum
        return code;
    }

    //Exemplo: se passarmos o codigo 2, o metodo vai retornar o OrderStatus.PAID
    /*
    Converte um código inteiro em um valor do enum OrderStatus
     */
    public static OrderStatus valueOf(int code){
        for (OrderStatus value : OrderStatus.values()){ //values() retorna todos os valores do enum
            if (value.getCode() == code){ //se o codigo for igual ao code passado como parametro
                return value; //retorna o valor do enum correspondente(tipo OrderStatus.PAID)
            }
        }
        //Exceção relacionada a argumentos inválidos
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
