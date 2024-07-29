package controleestacionamento.example.controle.services;

import controleestacionamento.example.controle.dto.VeiculoDTO;
import controleestacionamento.example.controle.entities.Veiculo;
import controleestacionamento.example.controle.repositories.VeiculoRepository;
import controleestacionamento.example.controle.services.exceptions.ResourceNotFoundException;
import controleestacionamento.example.controle.services.exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService{

    @Autowired
    private VeiculoRepository repository;

    public List<VeiculoDTO> findAll() {
        List<Veiculo> list = repository.findAll();
        return list.stream().map(VeiculoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public VeiculoDTO findByPlaca(String placa) {
        Optional<Veiculo> obj = repository.findByPlaca(placa);
        Veiculo entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new VeiculoDTO(entity);
    }

    @Transactional
    public VeiculoDTO inserirVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setHoraEntrada(veiculoDTO.getHoraEntrada());
        veiculo.setHoraSaida(veiculoDTO.getHoraSaida());
        veiculo.setPrecoFixo(veiculoDTO.isPrecoFixo());
        veiculo.setHorasEscolhidas(veiculoDTO.getHorasEscolhidas());

        LocalDateTime entrada = veiculo.getHoraEntrada();
        LocalDateTime saida = veiculo.getHoraSaida();
        boolean precoFixo = veiculo.isPrecoFixo();
        int horasEscolhidas = veiculo.getHorasEscolhidas();

        double valor = 0.0;

        if (precoFixo) {
            valor = horasEscolhidas * 2.50;
        } else {
            long minutos = Duration.between(entrada, saida).toMinutes();
            long horas = minutos / 60;
            long minutosExcedentes = minutos % 60;

            if (minutosExcedentes > 10) {
                horas++;
            }

            valor = horas * 3.50;
        }

        veiculo.setValor(valor);
        veiculo = repository.save(veiculo);

        VeiculoDTO resultDTO = new VeiculoDTO();
        resultDTO.setPlaca(veiculo.getPlaca());
        resultDTO.setHoraEntrada(veiculo.getHoraEntrada());
        resultDTO.setHoraSaida(veiculo.getHoraSaida());
        resultDTO.setPrecoFixo(veiculo.isPrecoFixo());
        resultDTO.setHorasEscolhidas(veiculo.getHorasEscolhidas());
        resultDTO.setValor(veiculo.getValor());
        return resultDTO;
    }

    @Transactional
    public VeiculoDTO update(String placa, VeiculoDTO dto) {
        Optional<Veiculo> optionalVeiculo = repository.findById(placa);

        if (optionalVeiculo.isEmpty()) {
            throw new ResourceNotFoundException("Placa não encontrada no sistema: " + placa);
        }

        Veiculo veiculo = optionalVeiculo.get();
        veiculo.setHorasEscolhidas(dto.getHorasEscolhidas());
        veiculo.setHoraSaida(dto.getHoraSaida());
        veiculo.setHoraEntrada(dto.getHoraEntrada());

        veiculo = repository.save(veiculo); // Persiste as alterações
        return new VeiculoDTO(veiculo);
    }

    public void delete(String placa) {
        try {
            repository.deleteById(placa);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Placa não encontrada");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
    }
}



