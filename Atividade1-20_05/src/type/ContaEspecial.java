package type;

import core.ContaBancaria;

public class ContaEspecial extends ContaBancaria {
    private double Limite = 1000.0;

    @Override //sobrepoe o metodo sacar original
    //metodo:
    public String sacar(double valor) {
        if (valor > 0 && valor <= (Limite + Saldo)) {
            Saldo -= valor;
            return "Saque realizado com sucesso.";
        } else {
            if (valor <= 0) {
                return "Valor inválido.";
            } else {
                return "Limite insuficiente.";
            }
        }
    }
    
    //getters e setters
    public double getLimite() {
        return Limite;
    }

    public void setLimite(double limite) {
        Limite = limite;
    }
}
