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
    
    //--------------- Lâmpada ----------------------------
    private final JLabel lamp01Label;
    private final JLabel lamp01Status;
    private final JRadioButton lamp01Ligar;
    private final JRadioButton lamp01Desligar;
    private final ButtonGroup lamp01Grupo;    
    private final SmartHomeEquip lamp01;
    private final SmartHomeClient lamp01Cliente;
    private final SmartHomeCallback lamp01Callback;
    
    //-------------------- TV ----------------------------
    
    private final JLabel tv01Label;
    private final JLabel tv01Status;
    private final JRadioButton tv01Ligar;
    private final JRadioButton tv01Desligar;
    private final ButtonGroup tv01Grupo;   
    private final SmartHomeEquip tv01;
    private final SmartHomeClient tv01Cliente;
    private final SmartHomeCallback tv01Callback;
    
    //----------------- Sistema de som ---------------------- 
    private final JLabel som01Label;
    private final JLabel som01Status;
    private final JRadioButton som01Ligar;
    private final JRadioButton som01Desligar;
    private final ButtonGroup som01Grupo;    
    private final SmartHomeEquip som01;
    private final SmartHomeClient som01Cliente;
    private final SmartHomeCallback som01Callback;
    
    //-------------- Ar condicionado --------------------
    private final JLabel ar01Label;
    private final JLabel ar01Status;
    private final JRadioButton ar01Ligar;
    private final JRadioButton ar01Desligar;
    private final ButtonGroup ar01Grupo;
    private final SmartHomeEquip ar01;
    private final SmartHomeClient ar01Cliente;
    private final SmartHomeCallback ar01Callback;
    
    //------------- Sensor de Temperatura -----------------
    private final JLabel sensor01Label;
    private final JLabel sensor01Status;     
    private final SmartHomeSensor sensor01;
    private final SmartHomeClient sensor01Cliente;
    private final SmartHomeCallback sensor01Callback;
  
    public SmartHomeGUI() throws MqttException {
        
        super("SmartHomeGUI");
        
        //--------------- Lâmpada ----------------------
        lamp01Status = new JLabel("Desligado");
        lamp01 = new SmartHomeEquip("lamp01", "desligado", lamp01Status);
        lamp01Callback = new SmartHomeCallback(lamp01);
        lamp01Cliente = new SmartHomeClient("lamp01");
        lamp01Cliente.conectar();
        lamp01Cliente.subscrever(lamp01Callback);
               
        setLayout(new FlowLayout());
        lamp01Label = new JLabel("Lâmpada 01");
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
        
        // --------------------- TV ---------------------
        tv01Status = new JLabel("Desligado");
        tv01 = new SmartHomeEquip("TV01", "desligado", tv01Status);
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
        add(tv01Status);
        
        tv01Grupo = new ButtonGroup();
        tv01Grupo.add(tv01Ligar);
        tv01Grupo.add(tv01Desligar);
        
        tv01Ligar.addItemListener(new EquipHandler(tv01, tv01Cliente, 
                "ligar"));
        tv01Desligar.addItemListener(new EquipHandler(tv01, tv01Cliente, 
                "desligar"));
        
        //------------------ Sistema de Som -----------------------
        som01Status = new JLabel("Desligado");
        som01 = new SmartHomeEquip("som01", "desligado", som01Status);
        som01Callback = new SmartHomeCallback(som01);
        som01Cliente = new SmartHomeClient("som01");
        som01Cliente.conectar();
        som01Cliente.subscrever(som01Callback);
               
        som01Label = new JLabel("Sistema de Som 01");
        som01Ligar =  new JRadioButton("Ligar", false);
        som01Desligar = new JRadioButton("Desligar", true);
        add(som01Label);        
        add(som01Ligar);
        add(som01Desligar);
        add(som01Status);
        
        som01Grupo = new ButtonGroup();
        som01Grupo.add(som01Ligar);
        som01Grupo.add(som01Desligar);
        
        som01Ligar.addItemListener(new EquipHandler(som01, som01Cliente, 
                "ligar"));
        som01Desligar.addItemListener(new EquipHandler(som01, som01Cliente, 
                "desligar"));
        
        //------------------- Ar Condicionado ---------------------
        ar01Status = new JLabel("Desligado");
        ar01 = new SmartHomeEquip("ar01", "desligado", ar01Status);
        ar01Callback = new SmartHomeCallback(ar01);
        ar01Cliente = new SmartHomeClient("ar01");
        ar01Cliente.conectar();
        ar01Cliente.subscrever(ar01Callback);
        
        ar01Label = new JLabel("Ar Condicionado 01");
        ar01Ligar =  new JRadioButton("Ligar", false);
        ar01Desligar = new JRadioButton("Desligar", true);
        add(ar01Label);        
        add(ar01Ligar);
        add(ar01Desligar);
        add(ar01Status);
        
        ar01Grupo = new ButtonGroup();
        ar01Grupo.add(ar01Ligar);
        ar01Grupo.add(ar01Desligar);
        
        ar01Ligar.addItemListener(new EquipHandler(ar01, ar01Cliente, 
                "ligar"));
        ar01Desligar.addItemListener(new EquipHandler(ar01, ar01Cliente, 
                "desligar"));
        
        //---------------- Sensor de Temperatura -----------------
        sensor01Status = new JLabel("25 ºC");
        sensor01 = new SmartHomeSensor("sensor01", "25 ºC", sensor01Status);
        sensor01Callback = new SmartHomeCallback(sensor01);
        sensor01Cliente = new SmartHomeClient("sensor01");
        sensor01Cliente.conectar();
        sensor01Cliente.subscrever(sensor01Callback);
        sensor01Cliente.start();
               
        sensor01Label = new JLabel("Sensor de Temperatura: ");
        add(sensor01Label);        
        add(sensor01Status);
               
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
