package controleestacionamento.example.controle.dto;


import controleestacionamento.example.controle.entities.Condutor;
import controleestacionamento.example.controle.entities.FormaPagamento;
import controleestacionamento.example.controle.entities.Veiculo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
@Getter
@Setter
public class CondutorDTO{

    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    private FormaPagamentoDTO formaPagamento;

    public CondutorDTO(String nome, String endereco, String telefone, String cpf) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;

    }
    public CondutorDTO(Condutor entity) {
        this.nome = entity.getNome();
        this.endereco = entity.getEndereco();
        this.telefone =entity.getTelefone();
        this.cpf = entity.getCpf();
    }



}
