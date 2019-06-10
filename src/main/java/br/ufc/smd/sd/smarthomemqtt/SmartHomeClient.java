package br.ufc.smd.sd.smarthomemqtt;

import java.security.SecureRandom;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 * Classe cliente para conectar, subescrever e publicar as mensagens no
 * broker iot.eclipse.org
 * @author Edvardo e Igor
 */
public class SmartHomeClient extends Thread{
    SecureRandom random = new SecureRandom();   
    int temp;
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

    @Override
    public void run() {
       
        while (true) {  
            temp = 25 + random.nextInt(10);
            
            publicarMensagem(String.valueOf(temp));
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            } //fim de try-catch
        } //fim de while
        
    }//fim de run
    
    
} //fim de SmartHomeClient
