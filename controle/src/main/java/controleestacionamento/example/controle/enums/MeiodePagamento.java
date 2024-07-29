package controleestacionamento.example.controle.enums;

public enum MeiodePagamento {
        DINHEIRO("Dinheiro"),
        CARTAO_CREDITO("Cartão de Crédito"),
        CARTAO_DEBITO("Cartão de Débito"),
        PIX("Pix"),
        BOLETO("Boleto");

        private final String descricao;

    MeiodePagamento(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

}
