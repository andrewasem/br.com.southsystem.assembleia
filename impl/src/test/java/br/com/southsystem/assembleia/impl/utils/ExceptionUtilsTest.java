package br.com.southsystem.assembleia.impl.utils;

import br.com.southsystem.assembleia.impl.utils.fixture.ExceptionUtilsFixture;
import br.com.southsystem.exception.errortype.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ExceptionUtilsTest {

    @Test
    void buildSimpleExceptionWithSuggestedApplicationActionTest() {
        Assert.assertThrows(GenericException.class, ExceptionUtilsFixture::doSimpleExceptionWithSuggestedApplicationAction);
    }

    @Test
    void buildSimpleExceptionTest() {
        Assert.assertThrows(GenericException.class, ExceptionUtilsFixture::doSimpleException);
    }
}
