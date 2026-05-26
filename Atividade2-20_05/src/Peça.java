public class Peça {
    protected String nome;
    protected long custo;
    protected long lucro;

    //construtor
    public Peça(String nome, long custo, long lucro) {
        this.nome = nome;
        this.custo = custo;
        this.lucro = lucro;
    }

    //metodo
    public long calcularPreço() {
        return custo + lucro;
    }

    //getters e setters
    public void setPeça(String nome, long custo, long lucro) {
        this.nome = nome;
        this.custo = custo; 
        this.lucro = lucro;
    }

    public String getInfo() {
        return  "Peça: " + nome + 
                ",\nCusto: " + custo + 
                ",\nLucro: " + lucro + 
                ",\nTotal: " + calcularPreço() +
                "\n";
    }
}