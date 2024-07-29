package controleestacionamento.example.controle.services.exceptions;

public class ResourceNotFoundException  extends RuntimeException{
    public ResourceNotFoundException(String cpf){
        super("Condutor com CPF " + cpf + " não encontrado.");
    }
}
