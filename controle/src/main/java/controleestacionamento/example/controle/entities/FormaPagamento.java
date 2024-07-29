package controleestacionamento.example.controle.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "FormaPagamento")
public class FormaPagamento {

    private String tipoPagamento;
    private String tipoCartao;
    private String dadosCartao;
    private String chavePix;

    @OneToOne(mappedBy = "formaPagamento", cascade = CascadeType.ALL)
    private Condutor condutor;
}
