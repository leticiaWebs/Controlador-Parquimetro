package controleestacionamento.example.controle.repositories;

import controleestacionamento.example.controle.entities.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
    Optional<Veiculo> findByPlaca(String placa);


}
