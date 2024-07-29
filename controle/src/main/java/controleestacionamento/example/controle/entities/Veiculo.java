package controleestacionamento.example.controle.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "veiculos")
public class Veiculo  implements Serializable {

    @Id
    private String placa;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private Double valor;
    private boolean precoFixo;
    private int horasEscolhidas;

}
