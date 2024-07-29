package controleestacionamento.example.controle.controller;

import controleestacionamento.example.controle.dto.VeiculoDTO;
import controleestacionamento.example.controle.entities.Veiculo;
import controleestacionamento.example.controle.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/veiculo")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);
    @Autowired
    private VeiculoService service;


    @PostMapping("/inserir")
    @Operation(description = "Insere informações do veiculo na base")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cadastra novo veiculo")
    })
    public VeiculoDTO inserirVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        VeiculoDTO novoVeiculo = service.inserirVeiculo(veiculoDTO);
        return service.inserirVeiculo(veiculoDTO);
    }
    @GetMapping
    @Operation(description = "Lista todos os veiculos registrados na base")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastra novo veiculo")
    })
    public ResponseEntity<List<VeiculoDTO>> findAll(){
        List <VeiculoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{placa}")
    @Operation(description = "Retorna os os veiculos registrados na base usando o identificador da placa")
    public ResponseEntity<VeiculoDTO> findByPlaca(@PathVariable String placa){
        VeiculoDTO dto = service.findByPlaca(placa);
        return ResponseEntity.ok().body(dto);
    }


    @PutMapping (value = "/{placa}")
    @Operation(description = "Possibilita a atualização dos dados de um veiculo atraves da placa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do veiculo feita com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não encontrado")
    })
    public ResponseEntity<VeiculoDTO> insert (@PathVariable String id, @RequestBody VeiculoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping (value = "/{id}")
    @Operation(description = "Deleta um veiculo que está cadastrado no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não encontrado no sistema")
    })
    public ResponseEntity<VeiculoDTO> delete (@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}

