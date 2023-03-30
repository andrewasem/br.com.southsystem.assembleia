package br.com.southsystem.exception.utils;

import br.com.southsystem.exception.constants.ExceptionConstants;
import br.com.southsystem.exception.errortype.GenericException;
import br.com.southsystem.exception.handler.response.ErrorInfo;
import br.com.southsystem.exception.handler.response.ErrorSpec;
import br.com.southsystem.exception.handler.response.Issue;
import br.com.southsystem.exception.message.MessageResource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtils {

    public static GenericException buildGenericException(@NonNull HttpStatus status, String message,
                                                         String suggestedUserAction,
                                                         String suggestedApplicationAction) {

        var msgSugestaoAcaoUsuario = MessageResource.getInstance()
                .getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_USUARIO);

        var msgSugestaoAcaoAplicacao = MessageResource.getInstance()
                .getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_APLICACAO);

        return GenericException.builder()
                .status(status)
                .errorInfo(ErrorInfo.builder()
                        .errors(List.of(ErrorSpec.builder()
                                .suggestedUserActions(List.of(Objects.isNull(suggestedUserAction) ? msgSugestaoAcaoUsuario : suggestedUserAction))
                                .suggestedApplicationActions(List.of(Objects.isNull(suggestedApplicationAction) ? msgSugestaoAcaoAplicacao : suggestedApplicationAction))
                                .name(status.name())
                                .message(message)
                                .issues(List.of(Issue.builder()
                                        .issueText(message)
                                        .build()))
                                .httpStatusCodes(List.of(status))
                                .build()))
                        .build())
                .build();
    }

    public static GenericException buildBadRequestException(String message) {
        return buildGenericException(HttpStatus.BAD_REQUEST, message, null, null);
    }

    public static <T> Mono<T> buildNotFoundException(String message) {
        return Mono.error(ExceptionUtils.buildGenericException(HttpStatus.NOT_FOUND, message, null, null));
    }
}
