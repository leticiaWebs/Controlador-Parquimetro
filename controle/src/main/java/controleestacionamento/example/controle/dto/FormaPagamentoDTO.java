package controleestacionamento.example.controle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDTO {
    public String tipoPagamento;
    public String tipoCartao;
    public String dadosCartao;
    public String chavePix;


}

