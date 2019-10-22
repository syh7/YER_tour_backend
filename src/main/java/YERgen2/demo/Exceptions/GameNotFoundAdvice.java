package YERgen2.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MatchNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MatchNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String matchNotFoundHandler(MatchNotFoundException ex) {
        return ex.getMessage();
    }

}
