package br.com.southsystem.exception.errortype;


import br.com.southsystem.exception.handler.response.ErrorInfo;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericException extends RuntimeException {

    private ErrorInfo errorInfo;

    private HttpStatus status;
}