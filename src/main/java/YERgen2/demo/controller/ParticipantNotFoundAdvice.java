package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.ParticipantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ParticipantNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ParticipantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String participantNotFoundHandler(ParticipantNotFoundException ex) {
        return ex.getMessage();
    }

}
