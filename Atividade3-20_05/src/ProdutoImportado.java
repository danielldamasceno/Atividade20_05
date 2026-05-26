public class ProdutoImportado extends ProdutoNacional {
    private double taxaImportacao = 0.05;

    public ProdutoImportado(String descricao, double valor) {
        super(descricao, valor);
    }

    public ProdutoImportado(String descricao, double valor, double imposto, double taxa, double taxaImportacao) {
        super(descricao, valor, imposto, taxa);
        this.taxaImportacao = taxaImportacao;
    }

    @Override
    public double calcularValorFinal() {
        double base = valor;
        return base * (1 + imposto + taxa + taxaImportacao);
    }

    @Override
    public String getInfo() {
        return "Produto: " + getDescricao() + "\nValor base: R$ " + String.format("%.2f", valor)
                + "\nImposto: " + String.format("%.2f", imposto * 100) + "%\nTaxa: " + String.format("%.2f", taxa * 100)
                + "%\nTaxa de importação: " + String.format("%.2f", taxaImportacao * 100)
                + "%\nValor final: R$ " + String.format("%.2f", calcularValorFinal());
    }
}
