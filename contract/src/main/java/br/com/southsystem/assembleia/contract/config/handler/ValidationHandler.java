package br.com.southsystem.assembleia.contract.config.handler;

import br.com.southsystem.exception.errortype.GenericException;
import br.com.southsystem.exception.handler.response.ErrorInfo;
import br.com.southsystem.exception.utils.ExceptionUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorInfo> handleException(WebExchangeBindException e) {
        var errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(ExceptionUtils.buildBadRequestException(errors.get(0)).getErrorInfo());
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorInfo> handleException(GenericException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getErrorInfo());
    }
}