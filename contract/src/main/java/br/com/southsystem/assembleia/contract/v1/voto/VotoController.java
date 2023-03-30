package br.com.southsystem.assembleia.contract.v1.voto;

import br.com.southsystem.assembleia.contract.config.util.ConstantContract;
import br.com.southsystem.assembleia.contract.v1.pauta.model.response.PautaResponse;
import br.com.southsystem.assembleia.contract.v1.voto.model.request.VotoRequest;
import br.com.southsystem.assembleia.contract.v1.voto.model.response.VotoResponse;
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
@RequestMapping(ConstantContract.PathController.VOTO)
@Api(tags = "Voto Controller")
public class VotoController {

    private final VotoFacade votoFacade;

    @PostMapping
    @ApiOperation(code = 201, value = "Voto Realizado", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pauta created successfully", response = PautaResponse.class),
            @ApiResponse(code = 400, message = "Informed data are invalid", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<VotoResponse> votar(@Valid @RequestBody VotoRequest votoRequest) {
        return votoFacade.votar(votoRequest);
    }
}
