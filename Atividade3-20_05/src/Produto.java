public class Produto {
    private String descricao;
    protected double valor;

    public Produto(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public double calcularValorFinal() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

}
