package controleestacionamento.example.controle.repositories;
import controleestacionamento.example.controle.entities.FormaPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FormaPagamentoRepository extends MongoRepository<FormaPagamento, Long>{

    List<FormaPagamento> findByTipoPagamento(String tipoPagamento);
}
