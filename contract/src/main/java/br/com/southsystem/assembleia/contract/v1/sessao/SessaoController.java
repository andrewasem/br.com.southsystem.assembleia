package br.com.southsystem.assembleia.contract.v1.sessao;

import br.com.southsystem.assembleia.contract.config.util.ConstantContract;
import br.com.southsystem.assembleia.contract.v1.sessao.model.request.SessaoRequest;
import br.com.southsystem.assembleia.contract.v1.sessao.model.response.SessaoResponse;
import br.com.southsystem.exception.handler.response.ErrorInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(ConstantContract.PathController.SESSAO)
@Api(tags = "Sessão Controller")
public class SessaoController {

    private final SessaoFacade sessaoFacade;

    @PostMapping
    @ApiOperation(code = 201, value = "Sessão aberta", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sessão aberta successfully", response = SessaoResponse.class),
            @ApiResponse(code = 400, message = "Informed data are invalid", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<SessaoResponse> abrirSessao(@Valid @RequestBody SessaoRequest sessaoRequest) {
        return sessaoFacade.abrirSessao(sessaoRequest);
    }
}
