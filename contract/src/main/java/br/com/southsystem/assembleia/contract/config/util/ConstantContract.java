package br.com.southsystem.assembleia.contract.config.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantContract {

    @UtilityClass
    public class PathController {

        public static final String PAUTA = "/v1/pauta";

        public static final String SESSAO = "/v1/sessao";

        public static final String ASSOCIADO = "/v1/associado";

        public static final String VOTO = "/v1/voto";
    }

}
