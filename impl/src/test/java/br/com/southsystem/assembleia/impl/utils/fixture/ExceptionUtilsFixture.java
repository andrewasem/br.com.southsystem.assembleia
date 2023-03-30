package br.com.southsystem.assembleia.impl.utils.fixture;

import br.com.southsystem.exception.constants.ExceptionConstants;
import br.com.southsystem.exception.message.MessageResource;
import br.com.southsystem.exception.utils.ExceptionUtils;
import org.springframework.http.HttpStatus;

public class ExceptionUtilsFixture {

    public static void doSimpleExceptionWithSuggestedApplicationAction() {
        throw ExceptionUtils.buildGenericException(HttpStatus.NOT_FOUND, "Not Found",
                MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_USUARIO),
                MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_APLICACAO));
    }

    public static void doSimpleException() {
        throw ExceptionUtils.buildGenericException(HttpStatus.NOT_FOUND,
                MessageResource.getInstance().getMessage("southsystem.common.exception.registro.nao.encontrado"), null, null);
    }
}