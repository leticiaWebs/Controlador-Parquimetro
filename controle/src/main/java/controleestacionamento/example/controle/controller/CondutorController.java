package controleestacionamento.example.controle.controller;

import controleestacionamento.example.controle.dto.CondutorDTO;
import controleestacionamento.example.controle.services.CondutorService;
import controleestacionamento.example.controle.services.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/condutor")
public class CondutorController {

    @Autowired
    private CondutorService service;

    @GetMapping
    @Operation(description = "Lista todos os condutores registrados no sistema")
    public ResponseEntity<List<CondutorDTO>> findAll(){
        List<CondutorDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/cpf")
    @Operation(description = "Retorna as informações de um condutor usando o cpf como identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condutor registrado no sistema")
    })
    public ResponseEntity<CondutorDTO> findByCpf(@RequestParam String cpf) {
        try {
        CondutorDTO dto = service.findByCpf(cpf);
        return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/inserir")
    @Operation(description = "Insere um novo condutor e sua forma de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condutor inserido no sistema")
    })
    public ResponseEntity<CondutorDTO> insert (@RequestBody CondutorDTO dto){
        dto = service.inserirCondutor(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}")
                .buildAndExpand(dto.getCpf()).toUri();
                return ResponseEntity.created(uri).body(dto);

    }

    @PutMapping("update/cpf")
    @Operation(description = "Atualiza os dados de um condutor ja registrado no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados atualizados no sistema"),
            @ApiResponse(responseCode = "404", description = "Condutor nao encontrado no sitema")
    })
    public ResponseEntity<CondutorDTO> insert (@RequestParam String cpf, @RequestBody CondutorDTO dto){
        dto = service.update(cpf, dto);
        return ResponseEntity.ok().body(dto);


    }

    @DeleteMapping(value ="delete/{id}")
    @Operation(description = "Deleta os dados do condutor usando o cpf como identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados deletados"),
            @ApiResponse(responseCode = "200", description = "Condutor não encontrado ")
    })
    public ResponseEntity<CondutorDTO> delete (@RequestParam String cpf){
        service.delete(cpf);
        return ResponseEntity.noContent().build();

    }
}
