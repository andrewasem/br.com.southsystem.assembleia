package br.com.southsystem.exception.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionConstants {

    public final String METHOD_ARGUMENT_NOT_VALID = "METHOD_ARGUMENT_NOT_VALID";

    public final String BAD_REQUEST = "BAD_REQUEST";

    @UtilityClass
    public class MessageConstants {

        public static final String SUGESTAO_ACAO_APLICACAO = "southsystem.sugestacao.acao.aplicacao";

        public static final String SUGESTAO_ACAO_USUARIO = "southsystem.sugestacao.acao.usuario";
    }
}
