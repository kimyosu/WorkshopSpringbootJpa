package com.kimy.course.services.exceptions;

//RuntimeException = exceção que não é obrigatória tratá-la
public class ResourceNotFoundException extends RuntimeException{

    //Recebe o id do recurso que não foi encontrado, pra imprimir na mensagem de erro
    public ResourceNotFoundException(Object id){
        super("Resource not found. Id " + id);
    }
}
