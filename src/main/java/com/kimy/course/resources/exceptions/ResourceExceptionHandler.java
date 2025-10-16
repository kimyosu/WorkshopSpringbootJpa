package com.kimy.course.resources.exceptions;

import com.kimy.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice //intercepta as exceções que acontecerem na aplicação
public class ResourceExceptionHandler {

    //Ele vai interceptar as exceções do tipo ResourceNotFoundException
    //e vai chamar esse método pra tratar a exceção
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StardardError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        //HttpServletRequest = objeto que contém informações sobre a requisição que gerou a exceção

        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; //código 404 que indica que o recurso não foi encontrado
        StardardError err = new StardardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        /*
        Instant.now() = momento em que o erro aconteceu
        status.value() = código do erro
        error = descrição do erro
        e.getMessage() = mensagem detalhada do erro
        request.getRequestURI() = caminho da requisição que gerou o erro
         */
        return ResponseEntity.status(status).body(err);
        //retorna a resposta HTTP com o código de status e o corpo da resposta contendo o erro
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StardardError> dataBase(DataBaseException e, HttpServletRequest request) {
        //HttpServletRequest = objeto que contém informações sobre a requisição que gerou a exceção

        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST; //código 400 que indica que a requisição é inválida
        StardardError err = new StardardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        /*
        Instant.now() = momento em que o erro aconteceu
        status.value() = código do erro
        error = descrição do erro
        e.getMessage() = mensagem detalhada do erro
        request.getRequestURI() = caminho da requisição que gerou o erro
         */
        return ResponseEntity.status(status).body(err);
        //retorna a resposta HTTP com o código de status e o corpo da resposta contendo o erro
    }
}
