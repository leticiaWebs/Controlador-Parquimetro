package controleestacionamento.example.controle.repositories;

import controleestacionamento.example.controle.entities.Condutor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CondutorRepository extends MongoRepository<Condutor, String> {
    Optional<Condutor> findByCpf(String cpf);
}

