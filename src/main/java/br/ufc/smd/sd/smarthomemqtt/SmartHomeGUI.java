/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.smd.sd.smarthomemqtt;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author Edvardo e Igor
 */
public class SmartHomeGUI extends JFrame{
    private final JLabel lamp01Label;
    private final JRadioButton lamp01Ligar;
    private final JRadioButton lamp01Desligar;
    private final JRadioButton lamp01MeiaLuz;
    private final ButtonGroup lamp01Grupo;
        
    private final JLabel lamp02Label;
    private final JRadioButton lamp02Ligar;    
    private final JRadioButton lamp02Desligar;
    private final JRadioButton lamp02MeiaLuz;
    private final ButtonGroup lamp02Grupo;
 
    private final SmartHomeEquip lamp01;
    private final SmartHomeClient lamp01Cliente;
    private final SmartHomeCallback lamp01Callback;

    private final SmartHomeEquip lamp02;
    private final SmartHomeClient lamp02Cliente;
    private final SmartHomeCallback lamp02Callback;
    
    public SmartHomeGUI() throws MqttException {
        
        super("SmartHomeGUI");
        
        lamp01 = new SmartHomeEquip("lamp01", "desligado", "0%");
        lamp01Cliente = new SmartHomeClient("lamp01");
        lamp01Callback = new SmartHomeCallback(lamp01);
        lamp01Cliente.conectar();
        lamp01Cliente.subscrever(lamp01Callback);
        
        lamp02 = new SmartHomeEquip("lamp02", "desligado", "0%");
        lamp02Cliente = new SmartHomeClient("lamp02");
        lamp02Callback = new SmartHomeCallback(lamp02);
        lamp02Cliente.conectar();
        lamp02Cliente.subscrever(lamp02Callback);
        
        
        setLayout(new FlowLayout());
        lamp01Label = new JLabel("Lâmpada 01");
        add(lamp01Label);
                
        lamp01Ligar =  new JRadioButton("Ligar", true);
        lamp01Desligar = new JRadioButton("Desligar", false);
        lamp01MeiaLuz = new JRadioButton("Meia-Luz", false);
        add(lamp01Ligar);
        add(lamp01MeiaLuz);
        add(lamp01Desligar);
        
        lamp01Grupo = new ButtonGroup();
        lamp01Grupo.add(lamp01Ligar);
        lamp01Grupo.add(lamp01MeiaLuz);
        lamp01Grupo.add(lamp01Desligar);
        
        lamp01Ligar.addItemListener(new Lamp01Handler(lamp01, "ligar"));
        lamp01MeiaLuz.addItemListener(new Lamp01Handler(lamp01, "meia-luz"));
        lamp01Desligar.addItemListener(new Lamp01Handler(lamp01, "desligar"));
        
        lamp02Label = new JLabel("Lâmpada 02");
        add(lamp02Label);
        lamp02Ligar =  new JRadioButton("Ligar", true);
        lamp02Desligar = new JRadioButton("Desligar", false);
        lamp02MeiaLuz = new JRadioButton("Meia-Luz", false);
        add(lamp02Ligar);
        add(lamp02MeiaLuz);
        add(lamp02Desligar);
        
        lamp02Grupo = new ButtonGroup();
        lamp02Grupo.add(lamp02Ligar);
        lamp02Grupo.add(lamp02MeiaLuz);
        lamp02Grupo.add(lamp02Desligar);
        
        lamp02Ligar.addItemListener(new Lamp02Handler(lamp02, "ligar"));
        lamp02MeiaLuz.addItemListener(new Lamp02Handler(lamp02, "meia-luz"));
        lamp02Desligar.addItemListener(new Lamp02Handler(lamp02, "desligar"));
        
        /*      
        
        SmartHomeEquip arCond = new SmartHomeEquip("arCond01", "desligado", "20ºC");
        SmartHomeEquip TV = new SmartHomeEquip("TV01", "desligado", "canal 10");
        
        
        SmartHomeClient arCondClient = new SmartHomeClient("arCond01");
        SmartHomeClient TVClient = new SmartHomeClient("TV01");
        
        
        SmartHomeCallback arCondCallback = new SmartHomeCallback(arCond);
        SmartHomeCallback TVCallback = new SmartHomeCallback(TV);
        
        
                
        arCondClient.conectar();
        arCondClient.subscrever(arCondCallback);
        
        TVClient.conectar();
        TVClient.subscrever(TVCallback);

*/
    }
    
    private class Lamp01Handler implements ItemListener{
        
        SmartHomeEquip equip;
        String mensagem;

        public Lamp01Handler(SmartHomeEquip equip, String mensagem) {
            this.equip = equip;
            this.mensagem = mensagem;
        }
        
           
       
        @Override
        public void itemStateChanged(ItemEvent e) {
            lamp01Cliente.publicarMensagem(mensagem);
            
        }
        
    }
    
    private class Lamp02Handler implements ItemListener{

        private String mensagem;
        private SmartHomeEquip equip;

        public Lamp02Handler(SmartHomeEquip equip, String mensagem) {
            this.equip = equip;
            this.mensagem = mensagem;
        }
        
        @Override
        public void itemStateChanged(ItemEvent e) {
            lamp02Cliente.publicarMensagem(mensagem);
            
        }
        
    }
    
}
