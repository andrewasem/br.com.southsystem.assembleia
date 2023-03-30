package br.com.southsystem.assembleia.contract.v1.util;

import br.com.southsystem.exception.constants.ExceptionConstants;
import br.com.southsystem.exception.handler.response.ErrorInfo;
import br.com.southsystem.exception.handler.response.ErrorSpec;
import br.com.southsystem.exception.handler.response.Issue;
import br.com.southsystem.exception.message.MessageResource;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class ErrorInfoUtil {

    public ErrorInfo buildOrderedErrorInfo(ErrorInfo error) {

        Stream<ErrorSpec> orderedErrorSpec = error.getErrors().stream().sorted(Comparator.comparing(ErrorSpec::getMessage));
        return ErrorInfo.builder()
                .namespace(error.getNamespace())
                .language(error.getLanguage())
                .errors(orderedErrorSpec.collect(Collectors.toList()))
                .build();
    }

    public ErrorInfo expectedOrderedError(String namespace, HttpStatus status, List<String> errorMessages, List<String> issueErrorMessage) {
        return ErrorInfo.builder()
                .errors(errorMessages.stream().sorted()
                        .map(errorMessage -> ErrorSpec.builder()
                                .namespace(namespace)
                                .suggestedUserActions(List.of(MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_USUARIO)))
                                .suggestedApplicationActions(List.of(MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_APLICACAO)))
                                .name(status.name())
                                .message(errorMessage)
                                .issues(issueErrorMessage.stream().sorted().map(issueMessage -> Issue.builder()
                                        .issueText(issueMessage)
                                        .build())
                                        .collect(Collectors.toList()))
                                .httpStatusCodes(List.of(status))
                                .build()
                        )
                        .collect(Collectors.toList())
                )
                .build();
    }

    public ErrorInfo expectedOrderedErrorInfoNotFound(List<String> errorMessages, List<String> issueErrorMessage) {
        return ErrorInfo.builder()
                .errors(errorMessages.stream().sorted()
                        .map(errorMessage -> ErrorSpec.builder()
                                .suggestedUserActions(List.of(
                                        MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_USUARIO)
                                ))
                                .suggestedApplicationActions(List.of(
                                        MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_APLICACAO)
                                ))
                                .name("NOT_FOUND")
                                .message(errorMessage)
                                .issues(issueErrorMessage.stream().sorted().map(issueMessage -> Issue.builder()
                                        .issueText(issueMessage)
                                        .build())
                                        .collect(Collectors.toList()))
                                .httpStatusCodes(List.of(HttpStatus.NOT_FOUND))
                                .build()
                        )
                        .collect(Collectors.toList())
                )
                .build();
    }

    public ErrorInfo expectedOrderedErrorInfoBadRequest(List<String> errorMessages, List<String> issueErrorMessage) {
        return ErrorInfo.builder()
                .errors(errorMessages.stream().sorted()
                        .map(errorMessage -> ErrorSpec.builder()
                                .suggestedUserActions(List.of(
                                        MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_USUARIO)
                                ))
                                .suggestedApplicationActions(List.of(
                                        MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_APLICACAO)
                                ))
                                .name(ExceptionConstants.BAD_REQUEST)
                                .message(errorMessage)
                                .issues(issueErrorMessage.stream().sorted().map(issueMessage -> Issue.builder()
                                        .issueText(issueMessage)
                                        .build())
                                        .collect(Collectors.toList()))
                                .httpStatusCodes(List.of(HttpStatus.BAD_REQUEST))
                                .build()
                        )
                        .collect(Collectors.toList())
                )
                .build();
    }

    public ErrorInfo expectedOrderedErrorInfoArgumentNotValid(List<String> errorMessages, List<String> issueErrorMessage) {
        return ErrorInfo.builder()
                .errors(errorMessages.stream().sorted()
                        .map(errorMessage -> ErrorSpec.builder()
                                .suggestedUserActions(List.of(MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_USUARIO)))
                                .suggestedApplicationActions(List.of(MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_APLICACAO)))
                                .name(ExceptionConstants.METHOD_ARGUMENT_NOT_VALID)
                                .message(errorMessage)
                                .issues(issueErrorMessage.stream().sorted().map(issueMessage -> Issue.builder()
                                        .id("INVALID_FIELD_VALUE")
                                        .issueText(issueMessage)
                                        .build())
                                        .collect(Collectors.toList()))
                                .httpStatusCodes(List.of(HttpStatus.BAD_REQUEST))
                                .build()
                        )
                        .collect(Collectors.toList())
                )
                .build();
    }

    public ErrorInfo builderErrorInfo(List<String> errorMessages, List<String> issueErrorMessages, String requestName) {
        if (ExceptionConstants.METHOD_ARGUMENT_NOT_VALID.equals(requestName)) {
            return ErrorInfoUtil.expectedOrderedErrorInfoArgumentNotValid(errorMessages, issueErrorMessages);
        } else {
            return ErrorInfoUtil.expectedOrderedErrorInfoBadRequest(errorMessages, issueErrorMessages);
        }
    }
}
