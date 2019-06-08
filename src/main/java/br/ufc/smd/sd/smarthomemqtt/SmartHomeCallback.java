/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.smd.sd.smarthomemqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author Edvardo e Igor
 */
public class SmartHomeCallback implements MqttCallback{
    
    private SmartHomeEquip smartHomeEquip;

    public SmartHomeCallback(SmartHomeEquip smartHomeEquip) {
        this.smartHomeEquip = smartHomeEquip;
    } //fim do construtor

    @Override
    public void connectionLost(Throwable thrwbl) {
        System.err.println(thrwbl);
    }

    @Override
    public void messageArrived(String topico, MqttMessage mensagem) throws Exception {
        String msg = mensagem.toString();
        System.out.println(msg);
        smartHomeEquip.executar(msg);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
    }
    
}
