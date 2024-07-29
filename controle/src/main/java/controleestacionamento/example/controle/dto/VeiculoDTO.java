package controleestacionamento.example.controle.dto;

import controleestacionamento.example.controle.entities.Veiculo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class VeiculoDTO {

    @Id
    private String placa;
    private  LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private Double valor;
    private boolean precoFixo;
    private int horasEscolhidas;

    public VeiculoDTO(String placa, LocalDateTime horaEntrada, LocalDateTime horaSaida, Double valor, boolean precoFixo, int horasEscolhidas) {
        this.placa = placa;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.valor = valor;
        this.precoFixo = precoFixo;
        this.horasEscolhidas = horasEscolhidas;
    }

    public VeiculoDTO(Veiculo entity){
        this.placa = entity.getPlaca();
        this.horaEntrada = entity.getHoraEntrada();
        this.horaSaida = entity.getHoraSaida();
        this.valor = entity.getValor();
        this.precoFixo = entity.isPrecoFixo();
        this.horasEscolhidas = entity.getHorasEscolhidas();
    }


    public VeiculoDTO() {

    }
}
