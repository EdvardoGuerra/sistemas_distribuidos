/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.smd.sd.smarthomemqtt;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 *
 * @author Edvardo e Igor
 */
public class SmartHomeClient {
    
    String idEquip;
    String topico;
    String broker = "tcp://iot.eclipse.org:1883";
    String clienteID = String.valueOf(System.nanoTime());
    private MqttClient cliente;

    public SmartHomeClient(String idEquip) throws MqttException {
        this.idEquip = idEquip;
        cliente = new MqttClient(broker, clienteID);
    } //fim do construtor
     
    public void conectar() throws MqttException, MqttSecurityException {
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setKeepAliveInterval(1000);
        connectOptions.setCleanSession(true);
        cliente.connect(connectOptions);
    } //fim de conectar
    
    public void subscrever(MqttCallback callback) throws MqttException{
        cliente.setCallback(callback);
        topico = "SmartHomeSD_" + this.idEquip;
        cliente.subscribe(topico);
    } //fim de subscrever
    
    public void publicarMensagem(String comando) {
        MqttMessage mensagem = new MqttMessage();
        mensagem.setPayload(comando.getBytes());
        MqttTopic mqttTopic = cliente.getTopic(topico);
        try {
            mqttTopic.publish(mensagem);
        } catch (MqttException e) {
            e.printStackTrace();
        } //fim de try-catch
    } //fim de publicarMensagem
    
} //fim de SmartHomeClient
