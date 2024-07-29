package controleestacionamento.example.controle.entities;

import controleestacionamento.example.controle.dto.FormaPagamentoDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter

@Document(collection = "condutores")
public class Condutor implements Serializable {
    @Id
    @DBRef
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forma_pagamento_id", referencedColumnName = "id")
    private FormaPagamento formaPagamento;


}