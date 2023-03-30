package br.com.southsystem.assembleia.impl.utils;

import br.com.southsystem.exception.constants.ExceptionConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class MessageResourceTest {

    @Test
    void testFindMessage() {
        var expected = "Por favor, caso o problema persista entre em contato com o nosso suporte";
        var actual = MessageResource.getInstance().getMessage(ExceptionConstants.MessageConstants.SUGESTAO_ACAO_USUARIO);
        Assert.assertEquals(actual, expected);
    }

}
