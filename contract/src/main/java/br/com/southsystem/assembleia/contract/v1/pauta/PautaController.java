package br.com.southsystem.assembleia.contract.v1.pauta;

import br.com.southsystem.assembleia.contract.config.util.ConstantContract;
import br.com.southsystem.assembleia.contract.v1.pauta.model.request.PautaRequest;
import br.com.southsystem.assembleia.contract.v1.pauta.model.response.PautaResponse;
import br.com.southsystem.exception.handler.response.ErrorInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(ConstantContract.PathController.PAUTA)
@Api(tags = "Pauta Controller")
public class PautaController {

    private final PautaFacade pautaFacade;

    @PostMapping
    @ApiOperation(code = 201, value = "Create a Pauta", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pauta created successfully", response = PautaResponse.class),
            @ApiResponse(code = 400, message = "Informed data are invalid", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<PautaResponse> createPauta(@Valid @RequestBody PautaRequest pautaRequest) {
        return pautaFacade.insert(pautaRequest);
    }

    @GetMapping("/listar")
    @ApiOperation(value = "Lista de Pauta", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List Pauta successfully", response = PautaResponse.class),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Informed data are invalid", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    public Flux<PautaResponse> listPautas() {
        return pautaFacade.findAll();
    }
}
