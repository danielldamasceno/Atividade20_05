package core;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import type.ContaEspecial;
import type.ContaPoupança;

//Qualquer semelhança ao banco do brasil é mera coincidencia...

public class PanelBanco extends JPanel {

    //cria um arquivo .csv para armazenar o mapa que foi construdido durante funcionamento do programa.
    private static final String DATA_FILE = "contas.csv";
    private final Map<Integer, ContaBancaria> contas = new HashMap<>(); // o mapa para armazenar as contas

    // Botões principais
    private final JButton btnAddConta = new JButton("Adicionar Conta");
    private final JButton btnSacar = new JButton("Sacar");
    private final JButton btnDepositar = new JButton("Depositar");
    private final JButton btnInfo = new JButton("Obter Informações");
    private final JButton btnExit = new JButton("Sair");

    private final javax.swing.JPanel formPanel = new javax.swing.JPanel();

    //definição do painel
    public PanelBanco() {
        int screenWidth = 800;
        int screenHeight = 480;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(220, 220, 0));
        setLayout(null);

        JLabel lblTitulo = new JLabel("Bem-vindo ao Banco do Povo");
        lblTitulo.setForeground(new Color(0, 80, 240));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(20, 10, 400, 30);
        add(lblTitulo);


        btnAddConta.setBounds(20, 60, 160, 30);
        btnSacar.setBounds(20, 100, 160, 30);
        btnDepositar.setBounds(20, 140, 160, 30);
        btnInfo.setBounds(20, 180, 160, 30);
        btnExit.setBounds(20, 220, 160, 30);

        add(btnAddConta);
        add(btnSacar);
        add(btnDepositar);
        add(btnInfo);
        add(btnExit);


        formPanel.setLayout(null);
        formPanel.setBounds(200, 60, 560, 360);
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        add(formPanel);


        btnAddConta.addActionListener(e -> showAddAccountForm());
        btnSacar.addActionListener(e -> showTransactionForm("Sacar"));
        btnDepositar.addActionListener(e -> showTransactionForm("Depositar"));
        btnInfo.addActionListener(e -> showInfoForm());
        btnExit.addActionListener(e -> {
            saveContasToFile();
            System.exit(0);
        });

        loadContasFromFile();
    }

    //limpar o form
    private void clearForm() {
        formPanel.removeAll();
        formPanel.revalidate();
        formPanel.repaint();
    }

    /** abre o form para criar conta */
    private void showAddAccountForm() {
        clearForm();

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 100, 25);
        JTextField txtNome = new JTextField();
        txtNome.setBounds(120, 20, 200, 25);

        JLabel lblNumero = new JLabel("Número da conta:");
        lblNumero.setBounds(20, 60, 120, 25);
        JTextField txtNumero = new JTextField();
        txtNumero.setBounds(150, 60, 170, 25);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 100, 100, 25);
        String[] tipos = {"Comum", "Especial", "Poupança"};
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        cbTipo.setBounds(120, 100, 200, 25);

        JButton btnConfirm = new JButton("Criar Conta");
        btnConfirm.setBounds(120, 150, 140, 30);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(280, 150, 100, 30);

        formPanel.add(lblNome);
        formPanel.add(txtNome);
        formPanel.add(lblNumero);
        formPanel.add(txtNumero);
        formPanel.add(lblTipo);
        formPanel.add(cbTipo);
        formPanel.add(btnConfirm);
        formPanel.add(btnCancelar);

        btnCancelar.addActionListener(e -> clearForm());

        btnConfirm.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String numStr = txtNumero.getText().trim();
            if (nome.isEmpty() || numStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha nome e número da conta.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int num;
            try {
                num = Integer.parseInt(numStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número de conta inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (contas.containsKey(num)) {
                JOptionPane.showMessageDialog(this, "Já existe uma conta com esse número.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tipo = (String) cbTipo.getSelectedItem();
            ContaBancaria nova;
            if ("Especial".equals(tipo)) {
                nova = new ContaEspecial();
            } else if ("Poupança".equals(tipo) || "Poupanca".equals(tipo)) {
                nova = new ContaPoupança();
            } else {
                nova = new ContaBancaria();
            }
            nova.setClient(nome, num);
            contas.put(num, nova);

            JOptionPane.showMessageDialog(this, "Conta criada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        });
    }

    /** abre o form para transações */
    private void showTransactionForm(String operacao) {
        clearForm();

        JLabel lblNumero = new JLabel("Número da conta:");
        lblNumero.setBounds(20, 20, 120, 25);
        JTextField txtNumero = new JTextField();
        txtNumero.setBounds(150, 20, 170, 25);

        JLabel lblValor = new JLabel("Valor (R$):");
        lblValor.setBounds(20, 60, 120, 25);
        JTextField txtValor = new JTextField();
        txtValor.setBounds(150, 60, 170, 25);

        JButton btnConfirm = new JButton(operacao);
        btnConfirm.setBounds(150, 100, 120, 30);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(280, 100, 100, 30);

        formPanel.add(lblNumero);
        formPanel.add(txtNumero);
        formPanel.add(lblValor);
        formPanel.add(txtValor);
        formPanel.add(btnConfirm);
        formPanel.add(btnCancelar);

        btnCancelar.addActionListener(e -> clearForm());

        btnConfirm.addActionListener(e -> {
            String numStr = txtNumero.getText().trim();
            String valStr = txtValor.getText().trim();
            if (numStr.isEmpty() || valStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha número da conta e valor.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int num;
            double valor;
            try {
                num = Integer.parseInt(numStr);
                valor = Double.parseDouble(valStr.replace(',', '.'));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número da conta ou valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ContaBancaria conta = contas.get(num);
            if (conta == null) {
                JOptionPane.showMessageDialog(this, "Conta não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ("Sacar".equals(operacao)) {
                if(conta.sacar(valor).equals("Saque realizado com sucesso.")) {
                    JOptionPane.showMessageDialog(this, "Saque efetuado. Saldo: R$ " + String.format("%.2f", conta.getSaldo()), "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, conta.sacar(valor), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if(conta.depositar(valor).equals("Depósito realizado com sucesso.")) {
                    JOptionPane.showMessageDialog(this, "Depósito efetuado. Saldo: R$ " + String.format("%.2f", conta.getSaldo()), "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, conta.depositar(valor), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            clearForm();
        });
    }

    /** abre o form para mostrar informações da conta escolhida */
    private void showInfoForm() {
        clearForm();

        JLabel lblNumero = new JLabel("Número da conta:");
        lblNumero.setBounds(20, 20, 120, 25);
        JTextField txtNumero = new JTextField();
        txtNumero.setBounds(150, 20, 170, 25);

        JLabel lblDiaInicio = new JLabel("Dia do depósito:");
        lblDiaInicio.setBounds(20, 60, 120, 25);
        JTextField txtDiaInicio = new JTextField();
        txtDiaInicio.setBounds(150, 60, 170, 25);

        JLabel lblMesInicio = new JLabel("Mês do depósito:");
        lblMesInicio.setBounds(20, 100, 120, 25);
        JTextField txtMesInicio = new JTextField();
        txtMesInicio.setBounds(150, 100, 170, 25);

        JLabel lblDiaFinal = new JLabel("Dia de checagem:");
        lblDiaFinal.setBounds(20, 140, 120, 25);
        JTextField txtDiaFinal = new JTextField();
        txtDiaFinal.setBounds(150, 140, 170, 25);

        JLabel lblMesFinal = new JLabel("Mês de checagem:");
        lblMesFinal.setBounds(20, 180, 120, 25);
        JTextField txtMesFinal = new JTextField();
        txtMesFinal.setBounds(150, 180, 170, 25);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(150, 220, 120, 30);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(280, 220, 100, 30);

        formPanel.add(lblNumero);
        formPanel.add(txtNumero);
        formPanel.add(lblDiaInicio);
        formPanel.add(txtDiaInicio);
        formPanel.add(lblMesInicio);
        formPanel.add(txtMesInicio);
        formPanel.add(lblDiaFinal);
        formPanel.add(txtDiaFinal);
        formPanel.add(lblMesFinal);
        formPanel.add(txtMesFinal);
        formPanel.add(btnBuscar);
        formPanel.add(btnCancelar);

        btnCancelar.addActionListener(e -> clearForm());

        btnBuscar.addActionListener(e -> {
            String numStr = txtNumero.getText().trim();
            if (numStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o número da conta.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int num;
            try {
                num = Integer.parseInt(numStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ContaBancaria conta = contas.get(num);
            if (conta == null) {
                JOptionPane.showMessageDialog(this, "Conta não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (conta instanceof ContaPoupança) {
                String diaInicioStr = txtDiaInicio.getText().trim();
                String mesInicioStr = txtMesInicio.getText().trim();
                String diaFinalStr = txtDiaFinal.getText().trim();
                String mesFinalStr = txtMesFinal.getText().trim();
                if (diaInicioStr.isEmpty() || mesInicioStr.isEmpty() || diaFinalStr.isEmpty() || mesFinalStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe dia e mês de início e de checagem.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int diaInicio, mesInicio, diaFinal, mesFinal;
                try {
                    diaInicio = Integer.parseInt(diaInicioStr);
                    mesInicio = Integer.parseInt(mesInicioStr);
                    diaFinal = Integer.parseInt(diaFinalStr);
                    mesFinal = Integer.parseInt(mesFinalStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Valores de dia/mês inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(((ContaPoupança) conta).checkDia(diaInicio, mesInicio, diaFinal, mesFinal) == false) {
                    JOptionPane.showMessageDialog(this, "Datas inválidas.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                    
                }
            }

            String info = "Cliente: " + conta.getClient() + "\n" +
                          "Número da Conta: " + conta.getNumeroConta() + "\n" +
                          "Saldo: R$ " + String.format("%.2f", conta.getSaldo()) + "\n" +
                          "Tipo: " + (conta instanceof ContaEspecial ? "Especial" : conta instanceof ContaPoupança ? "Poupança" : "Comum");
            JOptionPane.showMessageDialog(this, info, "Informações da Conta", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        });
    }

    /** Salva as contas que estão no mapa em um arquivo de texto */
    public void saveContasToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (ContaBancaria conta : contas.values()) {
                String tipo = "Comum";
                String extra = "";
                if (conta instanceof ContaEspecial) {
                    tipo = "Especial";
                    extra = String.valueOf(((ContaEspecial) conta).getLimite());
                } else if (conta instanceof ContaPoupança) {
                    tipo = "Poupança";
                    extra = String.valueOf(((ContaPoupança) conta).getTaxaJuros());
                }
                String nome = conta.getClient() == null ? "" : conta.getClient().replace("\n", " ").replace(",", " ");
                String saldo = String.format(Locale.US, "%.2f", conta.getSaldo());
                writer.write(conta.getNumeroConta() + "," + tipo + "," + nome + "," + saldo + "," + extra);
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Erro ao salvar contas: " + ex.getMessage());
        }
    }

    /** Carrega as contas do arquivo de texto pro mapa */
    private void loadContasFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 4) {
                    continue;
                }
                int numero = Integer.parseInt(parts[0]);
                String tipo = parts[1];
                String nome = parts[2];
                double saldo = Double.parseDouble(parts[3].replace(',', '.'));

                ContaBancaria conta;
                if ("Especial".equals(tipo)) {
                    conta = new ContaEspecial();
                } else if ("Poupança".equals(tipo) || "Poupanca".equals(tipo)) {
                    conta = new ContaPoupança();
                } else {
                    conta = new ContaBancaria();
                }
                conta.setClient(nome, numero);
                if (conta instanceof ContaEspecial && parts.length > 4 && !parts[4].isEmpty()) {
                    ((ContaEspecial) conta).setLimite(Double.parseDouble(parts[4].replace(',', '.')));
                }
                if (conta instanceof ContaPoupança && parts.length > 4 && !parts[4].isEmpty()) {
                    ((ContaPoupança) conta).setTaxaJuros(Double.parseDouble(parts[4].replace(',', '.')));
                }
                if (saldo > 0) {
                    conta.depositar(saldo);
                }
                contas.put(numero, conta);
            }
        } catch (IOException | NumberFormatException ex) {
        }
    }
}
