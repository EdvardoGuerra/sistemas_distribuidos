package br.ufc.smd.sd.smarthomemqtt;

import javax.swing.JFrame;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Classe inicial que chama a GUI
 * @author Edvardo e Igor
 */
public class SmartHome {
    
    public static void main(String[] args) throws MqttException {
        
        SmartHomeGUI smartHomeGUI = new SmartHomeGUI();
        smartHomeGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        smartHomeGUI.setSize(300, 200);
        smartHomeGUI.setVisible(true);     
                
    } //fim de main 
} //fim de SmartHome
