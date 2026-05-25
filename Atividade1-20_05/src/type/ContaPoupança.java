package type;

import core.ContaBancaria;

public class ContaPoupança extends ContaBancaria {
    private double taxaJurosMensal;

    public void checkDia(int dia) {
        if (dia == 5) {
            calcularNovoSaldo();
        } 
    }

    public void calcularNovoSaldo() {
        double juros = Saldo * taxaJurosMensal;
        depositar(juros);
    }

    public double getTaxaJuros() {
        return taxaJurosMensal;
    }

    public void setTaxaJuros(double taxaJuros) {
        taxaJurosMensal = taxaJuros;
    }

}
