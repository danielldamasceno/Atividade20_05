public class ProdutoEstadual extends Produto {
    protected double imposto = 0.10;

    public ProdutoEstadual(String descricao, double valor) {
        super(descricao, valor);
    }

    public ProdutoEstadual(String descricao, double valor, double imposto) {
        super(descricao, valor);
        this.imposto = imposto;
    }

    @Override
    public double calcularValorFinal() {
        double base = super.calcularValorFinal();
        return base * (1 + imposto);
    }

    public String getInfo() {
        return "Produto: " + getDescricao() + "\nValor base: R$ " + String.format("%.2f", valor)
                + "\nImposto: " + String.format("%.2f", imposto * 100) + "%\nValor final: R$ "
                + String.format("%.2f", calcularValorFinal());
    }
}
