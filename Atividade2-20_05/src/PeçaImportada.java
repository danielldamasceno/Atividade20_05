public class PeçaImportada extends Peça {
    private long taxaImportacao;
    private long taxaFrete;


    //construtor
    public PeçaImportada(String nome, long custo, long lucro, long taxaImportacao, long taxaFrete) {
        super(nome, custo, lucro);
        this.taxaImportacao = taxaImportacao;
        this.taxaFrete = taxaFrete;
    }

    //metodo
    @Override
    public long calcularPreço() {
        return super.calcularPreço() + taxaImportacao + taxaFrete;
    }

    //getters e setters
    @Override
    public String getInfo() {
        return "Peça: " + nome + 
                ",\nCusto: " + custo + 
                ",\nLucro: " + lucro + 
                ",\nTaxa de Importação: " + taxaImportacao +
                ",\nTaxa de Frete: " + taxaFrete +
                ",\nTotal: " + calcularPreço() +
                "\n";
    }

    public void setPeçaImportada(String nome, long custo, long lucro, long taxaImportacao, long taxaFrete) {
        setPeça(nome, custo, lucro);
        this.taxaImportacao = taxaImportacao;
        this.taxaFrete = taxaFrete;
    }

}
