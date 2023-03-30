package br.com.southsystem.assembleia.contract.v1.associado;

import br.com.southsystem.assembleia.contract.config.util.ConstantContract;
import br.com.southsystem.assembleia.contract.v1.associado.model.request.AssociadoRequest;
import br.com.southsystem.assembleia.contract.v1.associado.model.response.AssociadoResponse;
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
@RequestMapping(ConstantContract.PathController.ASSOCIADO)
@Api(tags = "Associado Controller")
public class AssociadoController {

    private final AssociadoFacade associadoFacade;

    @PostMapping
    @ApiOperation(code = 201, value = "Associado created", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Associado created successfully", response = AssociadoResponse.class),
            @ApiResponse(code = 400, message = "Informed data are invalid", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<AssociadoResponse> inserir(@Valid @RequestBody AssociadoRequest associadoRequest) {
        return associadoFacade.inserir(associadoRequest);
    }
}
