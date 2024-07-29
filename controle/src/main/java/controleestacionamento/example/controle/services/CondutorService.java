package controleestacionamento.example.controle.services;

import controleestacionamento.example.controle.dto.CondutorDTO;
import controleestacionamento.example.controle.dto.FormaPagamentoDTO;
import controleestacionamento.example.controle.entities.Condutor;
import controleestacionamento.example.controle.entities.FormaPagamento;
import controleestacionamento.example.controle.repositories.CondutorRepository;
import controleestacionamento.example.controle.repositories.FormaPagamentoRepository;
import controleestacionamento.example.controle.services.exceptions.DataBaseException;
import controleestacionamento.example.controle.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository repository;
    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<CondutorDTO> findAll(){
        List<Condutor> list = repository.findAll();
        return list.stream().map(CondutorDTO::new).collect(Collectors.toList());
    }

    public CondutorDTO findByCpf(String cpf) {
        Optional<Condutor> obj = repository.findByCpf(cpf);
        Condutor entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CondutorDTO(entity);
    }
    @Transactional
    public CondutorDTO inserirCondutor(CondutorDTO condutorDTO) {
        Condutor condutor = new Condutor();
        condutor.setCpf(condutorDTO.getCpf());
        condutor.setNome(condutorDTO.getNome());
        condutor.setEndereco(condutorDTO.getEndereco());
        condutor.setTelefone(condutorDTO.getTelefone());

        FormaPagamento formaPagamento = new FormaPagamento();
        FormaPagamentoDTO formaPagamentoDTO = condutorDTO.getFormaPagamento();

        formaPagamento.setTipoPagamento(formaPagamentoDTO.getTipoPagamento());

        if ("CARTAO".equals(formaPagamentoDTO.getTipoPagamento())) {
            formaPagamento.setTipoCartao(formaPagamentoDTO.getTipoCartao());
            formaPagamento.setDadosCartao(formaPagamentoDTO.getDadosCartao());
        } else if ("PIX".equals(formaPagamentoDTO.getTipoPagamento())) {
            formaPagamento.setChavePix(UUID.randomUUID().toString()); // Gerar chave PIX
        }

        formaPagamento = formaPagamentoRepository.save(formaPagamento);
        condutor.setFormaPagamento(formaPagamento);
        condutor = condutorRepository.save(condutor);

        condutorDTO.setFormaPagamento(formaPagamentoDTO);
        return condutorDTO;
    }

    @Transactional
    public CondutorDTO update(String cpf, CondutorDTO dto){
        try{
            Condutor entity = repository.findByCpf(cpf)
             .orElseThrow(() -> new ResourceNotFoundException("Condutor não encontrado " + cpf));
            entity.setNome(dto.getNome());
            entity.setEndereco(dto.getEndereco());
            entity.setTelefone(dto.getTelefone());
            entity = repository.save(entity);
            return new CondutorDTO(entity);
        } catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Condutor não encontrado " + cpf);
        }

    }

    public void delete(String cpf){
        try{
            repository.deleteById(cpf);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Condutor não encontrado");
        }
        catch(DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }
    }
}

