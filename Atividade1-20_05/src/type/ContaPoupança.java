package type;

import core.ContaBancaria;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContaPoupança extends ContaBancaria {

    private double taxaJurosMensal = 0.02; // o banco é murrinha
    
    //metodos:
    /** Checa se a data é válida */
    public boolean checkDia(int diaInicial, int mesInicio, int diaFinal, int mesFinal) {
        if (!isDataValida(diaInicial, mesInicio) || !isDataValida(diaFinal, mesFinal)) {
            return false;
        }

        if (!isDepoisOuIgual(diaInicial, mesInicio, diaFinal, mesFinal)) {
            return false;
        }
        calcularNovoSaldo(calcularDiasEntre(diaInicial, mesInicio, diaFinal, mesFinal));
        return true;
    }

    /** Calcula os dias */
    public static int calcularDiasEntre(int diaInicio, int mesInicio, int diaFinal, int mesFinal) {
        try {
            int ano = LocalDate.now().getYear();
            LocalDate inicio = LocalDate.of(ano, mesInicio, diaInicio);
            LocalDate fim = LocalDate.of(ano, mesFinal, diaFinal);
            double dias = ChronoUnit.DAYS.between(inicio, fim);
            if (dias < 0) {
                return -1;
            }
            return (int) dias/30; // Retorna em meses
        } catch (DateTimeException ex) {
            return -1;
        }
    }

    /** Calcula o saldo pos juros */
    public void calcularNovoSaldo(int meses) {
        double juros = getSaldo() * taxaJurosMensal * meses;
        depositar(juros);
    }

    // getters e setters:
    public double getTaxaJuros() {
        return taxaJurosMensal;
    }

    public void setTaxaJuros(double taxaJuros) {
        taxaJurosMensal = taxaJuros;
    }

    //checkers:
    private static boolean isDataValida(int dia, int mes) {
        return mes >= 1 && mes <= 12 && dia >= 1 && dia <= 31;
    }

    private static boolean isDepoisOuIgual(int diaInicial, int mesInicio, int diaFinal, int mesFinal) {
        if (mesFinal > mesInicio) {
            return true;
        }
        if (mesFinal == mesInicio) {
            return diaFinal >= diaInicial;
        }
        return false;
    }
}
