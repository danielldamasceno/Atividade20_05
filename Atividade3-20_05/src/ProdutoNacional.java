public class ProdutoNacional extends ProdutoEstadual {
    protected double taxa = 0.05;

    public ProdutoNacional(String descricao, double valor) {
        super(descricao, valor);
    }

    public ProdutoNacional(String descricao, double valor, double imposto, double taxa) {
        super(descricao, valor, imposto);
        this.taxa = taxa;
    }

    @Override
    public double calcularValorFinal() {
        double base = valor; // use base value to apply additive rates
        return base * (1 + imposto + taxa);
    }

    @Override
    public String getInfo() {
        return "Produto: " + getDescricao() + "\nValor base: R$ " + String.format("%.2f", valor)
                + "\nImposto: " + String.format("%.2f", imposto * 100) + "%\nTaxa: " + String.format("%.2f", taxa * 100)
                + "%\nValor final: R$ " + String.format("%.2f", calcularValorFinal());
    }
}
        
