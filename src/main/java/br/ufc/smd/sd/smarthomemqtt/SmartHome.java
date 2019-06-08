/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.smd.sd.smarthomemqtt;

import javax.swing.JFrame;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author Edvardo e Igo
 */
public class SmartHome {
    
    public static void main(String[] args) throws MqttException {
        
        SmartHomeGUI smartHomeGUI = new SmartHomeGUI();
        smartHomeGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        smartHomeGUI.setSize(350, 200);
        smartHomeGUI.setVisible(true);     
                
    } //fim de main 
} //fim de SmartHome
