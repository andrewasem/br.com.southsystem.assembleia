package br.com.southsystem.assembleia.impl.voto;

import br.com.southsystem.assembleia.impl.utils.MessageResource;
import br.com.southsystem.exception.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum VotoTypeEnum {
    NAO(false),
    SIM(true);

    private final Boolean value;

    public static VotoTypeEnum fromValue(Boolean votoType) {
        var message = MessageResource.getInstance().getMessage("assembleia.votacao.voto.invalido");
        return Arrays.stream(VotoTypeEnum.values())
                .filter(it -> it.getValue().equals(votoType))
                .findFirst()
                .orElseThrow(() ->  ExceptionUtils.buildBadRequestException(message));
    }
}