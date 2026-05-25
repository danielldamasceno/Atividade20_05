package core;

import javax.swing.JFrame;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class App {
    public static void main(String[] args) throws Exception {
        menuBanco();
    }

    public static void menuBanco() {
        JFrame painel = new JFrame("Banco do Povo App");
        PanelBanco panel = new PanelBanco();

        painel.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        painel.setResizable(false);
        painel.add(panel);
        painel.pack();
        painel.setLocationRelativeTo(null);
        painel.setVisible(true);
        painel.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                panel.saveContasToFile();
                painel.dispose();
                System.exit(0);
            }
        });
        
    } 

}
