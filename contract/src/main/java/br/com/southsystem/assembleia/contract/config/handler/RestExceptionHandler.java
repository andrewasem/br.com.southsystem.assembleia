package br.com.southsystem.assembleia.contract.config.handler;

import br.com.southsystem.exception.errortype.NoContentException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Component
public class RestExceptionHandler {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Void> handleNotFoundException(NoContentException noContentException) {
        return ResponseEntity.noContent().build();
    }
}