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
 * Classe que cria a GUI e as instâncias dos equipamentos
 * @author Edvardo e Igor
 */
public class SmartHomeGUI extends JFrame{
    private final JLabel lamp01Label;
    private final JLabel lamp01Status;
    private final JRadioButton lamp01Ligar;
    private final JRadioButton lamp01Desligar;
    private final ButtonGroup lamp01Grupo;
        
    private final SmartHomeEquip lamp01;
    private final SmartHomeClient lamp01Cliente;
    private final SmartHomeCallback lamp01Callback;
    
    private final JLabel tv01Label;
    private final JRadioButton tv01Ligar;
    private final JRadioButton tv01Desligar;
    private final ButtonGroup tv01Grupo;
        
    private final SmartHomeEquip tv01;
    private final SmartHomeClient tv01Cliente;
    private final SmartHomeCallback tv01Callback;
    
    private final JLabel som01Label;
    private final JRadioButton som01Ligar;
    private final JRadioButton som01Desligar;
    private final ButtonGroup som01Grupo;
        
    private final SmartHomeEquip som01;
    private final SmartHomeClient som01Cliente;
    private final SmartHomeCallback som01Callback;
  
    public SmartHomeGUI() throws MqttException {
        
        super("SmartHomeGUI");
        
        lamp01 = new SmartHomeEquip("lamp01", "desligado");
        lamp01Callback = new SmartHomeCallback(lamp01);
        lamp01Cliente = new SmartHomeClient("lamp01");
        lamp01Cliente.conectar();
        lamp01Cliente.subscrever(lamp01Callback);
               
        setLayout(new FlowLayout());
        lamp01Label = new JLabel("Lâmpada 01");
        lamp01Status = new JLabel();
        lamp01Ligar =  new JRadioButton("Ligar", false);
        lamp01Desligar = new JRadioButton("Desligar", true);
        add(lamp01Label);        
        add(lamp01Ligar);
        add(lamp01Desligar);
        add(lamp01Status);
        
        lamp01Grupo = new ButtonGroup();
        lamp01Grupo.add(lamp01Ligar);
        lamp01Grupo.add(lamp01Desligar);
        
        lamp01Ligar.addItemListener(new EquipHandler(lamp01, lamp01Cliente, 
                "ligar"));
        lamp01Desligar.addItemListener(new EquipHandler(lamp01, lamp01Cliente, 
                "desligar"));
        
        tv01 = new SmartHomeEquip("TV01", "desligado");
        tv01Callback = new SmartHomeCallback(tv01);
        tv01Cliente = new SmartHomeClient("tv01");
        tv01Cliente.conectar();
        tv01Cliente.subscrever(tv01Callback);
               
        setLayout(new FlowLayout());
        tv01Label = new JLabel("Televisão 01");
        tv01Ligar =  new JRadioButton("Ligar", false);
        tv01Desligar = new JRadioButton("Desligar", true);
        add(tv01Label);        
        add(tv01Ligar);
        add(tv01Desligar);
        
        tv01Grupo = new ButtonGroup();
        tv01Grupo.add(tv01Ligar);
        tv01Grupo.add(tv01Desligar);
        
        tv01Ligar.addItemListener(new EquipHandler(tv01, tv01Cliente, 
                "ligar"));
        tv01Desligar.addItemListener(new EquipHandler(tv01, tv01Cliente, 
                "desligar"));
        
        som01 = new SmartHomeEquip("som01", "desligado");
        som01Callback = new SmartHomeCallback(som01);
        som01Cliente = new SmartHomeClient("som01");
        som01Cliente.conectar();
        som01Cliente.subscrever(som01Callback);
               
        //setLayout(new FlowLayout());
        som01Label = new JLabel("Sistema de Som 01");
        som01Ligar =  new JRadioButton("Ligar", false);
        som01Desligar = new JRadioButton("Desligar", true);
        add(som01Label);        
        add(som01Ligar);
        add(som01Desligar);
        
        som01Grupo = new ButtonGroup();
        som01Grupo.add(som01Ligar);
        som01Grupo.add(som01Desligar);
        
        som01Ligar.addItemListener(new EquipHandler(som01, som01Cliente, 
                "ligar"));
        som01Desligar.addItemListener(new EquipHandler(som01, som01Cliente, 
                "desligar"));
       
    } //fim do construtor
    
    private class EquipHandler implements ItemListener{
        
        SmartHomeEquip equip;
        SmartHomeClient equipCliente;
        String mensagem;

        public EquipHandler(SmartHomeEquip equip, SmartHomeClient equipCliente, 
                String mensagem) {
            this.equip = equip;
            this.equipCliente = equipCliente;
            this.mensagem = mensagem;
        } //fim do construtor

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED){
                System.out.println("Equipamento: " + equip.idEquip 
                    + " Status atual: " + equip.status
                    + " Comando: " + mensagem);
                equipCliente.publicarMensagem(mensagem);
            }
        } //fim de itemStateChanged
        
    } //fim de EquipHandler

} //fim de SmartHomeEquip
