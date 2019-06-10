package br.ufc.smd.sd.smarthomesockets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class SmartHomeGUI extends JFrame {

    /*----------------- VARIÁVEIS SOM ------------------------*/
    private final JLabel somLabel;
    private final JRadioButton somLigar;
    private final JRadioButton somDesligar;
    private final ButtonGroup grupoSom;
    private final SmartHomeClient somCliente;
    /*----------------- VARIÁVEIS SOM ------------------------*/

    /*----------------- VARIÁVEIS TV ------------------------*/
    private final JLabel tvLabel;
    private final JRadioButton tvLigar;
    private final JRadioButton tvDesligar;
    private final ButtonGroup grupoTV;
    private final SmartHomeClient tvCliente;
    /*----------------- VARIÁVEIS TV ------------------------*/

    /*----------------- VARIÁVEIS LAMPADA ------------------------*/
    private final JLabel lampadaLabel;
    private final JRadioButton lampadaLigar;
    private final JRadioButton lampadaDesligar;
    private final ButtonGroup grupoLampada;
    private final SmartHomeClient lampadaCliente;
    /*----------------- VARIÁVEIS LAMPADA ------------------------*/
    private final JRadioButton sensorLigar;
    private final JRadioButton sensorDesligar;
    private final ButtonGroup grupoSensor;
    private final SmartHomeClient sensorCliente;
    /*----------------- VARIÁVEIS AR CONDICIONADO  ------------------------*/
    private final JLabel arLabel;
    /*----------------- VARIÁVEIS SENSOR TEMPERATURA ------------------------*/
    private final JRadioButton arLigar;
    private final JRadioButton arDesligar;
    private final ButtonGroup grupoAr;
    private final SmartHomeClient arCliente;
    /*----------------- VARIÁVEIS SENSOR TEMPERATURA ------------------------*/
    private JLabel sensorLabel;
    /*----------------- VARIÁVEIS AR CONDICIONADO  ------------------------*/


    public SmartHomeGUI() throws HeadlessException {

        super("SmartHome Sockets");


        setLayout(new GridLayout(5, 3));

        /*----------------------- SETUP SOM ------------------------------------------------*/
        somCliente = new SmartHomeClient("1", "Meu Som", "DESLIGADO");
        somLabel = new JLabel("Som");
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

        /*----------------------- SETUP TV ------------------------------------------------*/
        tvCliente = new SmartHomeClient("2", "Minha TV", "DESLIGADO");
        tvLabel = new JLabel("TV");
        tvLigar = new JRadioButton("Ligar TV", false);
        tvDesligar = new JRadioButton("Desligar TV", true);

        add(tvLabel);
        add(tvLigar);
        add(tvDesligar);

        grupoTV = new ButtonGroup();
        grupoTV.add(tvLigar);
        grupoTV.add(tvDesligar);


        tvLigar.addItemListener(new tvLigarHandler("LIGADO"));
        tvDesligar.addItemListener(new tvDesligarHandler("DESLIGADO"));

        /*----------------------- SETUP TV ------------------------------------------------*/

        /*----------------------- SETUP LAMPADA ------------------------------------------------*/
        lampadaCliente = new SmartHomeClient("3", "Minha Lampada", "DESLIGADO");
        lampadaLabel = new JLabel("Lampada");
        lampadaLigar = new JRadioButton("Ligar Lampada", false);
        lampadaDesligar = new JRadioButton("Desligar Lampada", true);

        add(lampadaLabel);
        add(lampadaLigar);
        add(lampadaDesligar);

        grupoLampada = new ButtonGroup();
        grupoLampada.add(lampadaLigar);
        grupoLampada.add(lampadaDesligar);


        lampadaLigar.addItemListener(new lampadaLigarHandler("LIGADO"));
        lampadaDesligar.addItemListener(new lampadaDesligarHandler("DESLIGADO"));

        /*----------------------- SETUP LAMPADA ------------------------------------------------*/

        /*----------------------- SETUP SENSOR TEMPERATURA ------------------------------------------------*/
        sensorCliente = new SmartHomeClient("4", "Meu Sensor", "LIGADO", 25);

        sensorLigar = new JRadioButton("Ligar Sensor", true);
        sensorDesligar = new JRadioButton("Desligar Sensor", false);
        sensorLabel = new JLabel("Sensor de Temperatura");

        add(sensorLabel);

        add(sensorLigar);
        add(sensorDesligar);


        grupoSensor = new ButtonGroup();
        grupoSensor.add(sensorLigar);
        grupoSensor.add(sensorDesligar);


        sensorLigar.addItemListener(new sensorLigarHandler("LIGADO"));
        sensorDesligar.addItemListener(new sensorDesligarHandler("DESLIGADO"));

        SensorTemperatura s = new SensorTemperatura();
        Thread sensorT = new Thread(s);
        sensorT.start();

        /*----------------------- SETUP SENSOR TEMPERATURA ------------------------------------------------*/


        /*----------------------- SETUP AR CONDICIONADO ------------------------------------------------*/
        arCliente = new SmartHomeClient("5", "Meu Ar Condicionado", "DESLIGADO");
        arLabel = new JLabel("Ar Condicionado");
        arLigar = new JRadioButton("Ligar Ar", false);
        arDesligar = new JRadioButton("Desligar Ar", true);

        add(arLabel);
        add(arLigar);
        add(arDesligar);

        grupoAr = new ButtonGroup();
        grupoAr.add(arLigar);
        grupoAr.add(arDesligar);


        arLigar.addItemListener(new arLigarHandler("LIGADO"));
        arDesligar.addItemListener(new arDesligarHandler("DESLIGADO"));

        /*----------------------- SETUP AR CONDICIONADO ------------------------------------------------*/


    }

    private class somLigarHandler implements ItemListener {

        String novoStatus;

        public somLigarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if (!somCliente.getStatus().equals("LIGADO"))
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
                if (!somCliente.getStatus().equals("DESLIGADO"))
                    somCliente.conectarServer(somCliente, novoStatus);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    private class tvLigarHandler implements ItemListener {

        String novoStatus;

        public tvLigarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if (!tvCliente.getStatus().equals("LIGADO"))
                    tvCliente.conectarServer(tvCliente, novoStatus);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }


    private class tvDesligarHandler implements ItemListener {

        String novoStatus;

        public tvDesligarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if (!tvCliente.getStatus().equals("DESLIGADO"))
                    tvCliente.conectarServer(tvCliente, novoStatus);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }


    private class lampadaLigarHandler implements ItemListener {

        String novoStatus;

        public lampadaLigarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if (!lampadaCliente.getStatus().equals("LIGADO"))
                    lampadaCliente.conectarServer(lampadaCliente, novoStatus);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }


    private class lampadaDesligarHandler implements ItemListener {

        String novoStatus;

        public lampadaDesligarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if (!lampadaCliente.getStatus().equals("DESLIGADO"))
                    lampadaCliente.conectarServer(lampadaCliente, novoStatus);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }


    private class SensorTemperatura extends Thread {

        @Override
        public void run() {

            while (true) {
                try {

                    if (sensorCliente.getStatus().equals("LIGADO"))
                        sensorCliente.conectarServerTemperatura(sensorCliente, sensorCliente.getValor() + 1);

                    sensorLabel.setText("Sensor de Temperatura: atual " + sensorCliente.getValor() + "º");

                    Thread.sleep(10000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    private class sensorLigarHandler implements ItemListener {

        String novoStatus;

        public sensorLigarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if (!sensorCliente.getStatus().equals("LIGADO"))
                    sensorCliente.conectarServer(sensorCliente, novoStatus);

            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }

    }


    private class sensorDesligarHandler implements ItemListener {

        String novoStatus;

        public sensorDesligarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if (!sensorCliente.getStatus().equals("DESLIGADO"))
                    sensorCliente.conectarServer(sensorCliente, novoStatus);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            sensorCliente.setStatus(novoStatus);

        }

    }


    private class arLigarHandler implements ItemListener {

        String novoStatus;

        public arLigarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if (!arCliente.getStatus().equals("LIGADO"))
                    arCliente.conectarServer(arCliente, novoStatus);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }


    private class arDesligarHandler implements ItemListener {

        String novoStatus;

        public arDesligarHandler(String novoStatus) {
            this.novoStatus = novoStatus;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                if (!arCliente.getStatus().equals("DESLIGADO"))
                    arCliente.conectarServer(arCliente, novoStatus);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }


}
