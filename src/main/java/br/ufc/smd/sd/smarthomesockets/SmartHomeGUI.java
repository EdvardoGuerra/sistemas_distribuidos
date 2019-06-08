package br.ufc.smd.sd.smarthomesockets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class SmartHomeGUI extends JFrame {

    private final JLabel somLabel;
    private final JRadioButton somLigar;
    private final JRadioButton somDesligar;
    private final ButtonGroup grupoSom;
    private final SmartHomeClient somCliente;


    public SmartHomeGUI() throws HeadlessException {

        super("SmartHome Sockets");


        setLayout(new FlowLayout());

        /*----------------------- SETUP SOM ------------------------------------------------*/
        somCliente = new SmartHomeClient("1", "Meu Som", "DESLIGADO");
        somLabel = new JLabel("SmartHome - Som");
        somLigar = new JRadioButton("Ligar Som", false);
        somDesligar = new JRadioButton("Desligar Som", true);

        add(somLabel);
        add(somLigar);
        add(somDesligar);

        grupoSom = new ButtonGroup();
        grupoSom.add(somLigar);
        grupoSom.add(somDesligar);


        somLigar.addItemListener(new somLigarHandler("LIGADO"));
        somDesligar.addItemListener(new somDesligarHandler("DESLIGADO"));

        /*----------------------- SETUP SOM ------------------------------------------------*/



    }

    private class somLigarHandler implements ItemListener {

        String novoStatus;

        public somLigarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if(!somCliente.getStatus().equals("LIGADO"))
                    somCliente.conectarServer(somCliente, novoStatus);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }


    private class somDesligarHandler implements ItemListener {

        String novoStatus;

        public somDesligarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if(!somCliente.getStatus().equals("DESLIGADO"))
                    somCliente.conectarServer(somCliente, novoStatus);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}
