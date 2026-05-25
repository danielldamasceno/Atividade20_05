package core;

public class ContaBancaria {

    private String cliente;
    private int numeroConta;
    protected double Saldo;
    
    // metodos:
    public String sacar(double valor){
        if (valor > 0 && valor <= Saldo) {
            Saldo -= valor;
            return "Saque realizado com sucesso.";
        } else {
            if (valor <= 0) {
                return "Valor inválido.";
            } else {
                return "Saldo insuficiente.";
            }
        }
    }

    public String depositar(double valor){
        if (valor > 0) {
            Saldo += valor;
            return "Depósito realizado com sucesso.";
        } else {
            return "Valor inválido.";
        }
    }

    //setter
    public void setClient(String cliente, int numeroConta) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
    }
    
    //getters:
    public String getClient() {
        return cliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return Saldo;
    }
}
