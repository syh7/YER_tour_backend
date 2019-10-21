package YERgen2.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AdminNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String adminNotFoundHandler(AdminNotFoundException ex) {
        return ex.getMessage();
    }

}
