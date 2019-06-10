package br.ufc.smd.sd.smarthomesockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SmartHomeClient extends Equipamento {

    String clienteID = String.valueOf(System.nanoTime());


    public SmartHomeClient(String id, String nome, String status) {
        this.id = id;
        this.status = status;
        this.nome = nome;
    }


    public SmartHomeClient(String id, String nome, String status, int valor) {
        this.id = id;
        this.status = status;
        this.nome = nome;
        this.valor = valor;
    }


    public void conectarServer(SmartHomeClient equipamento, String novoStatus) throws IOException {

        String message = equipamento.getStatus() + " -> " + novoStatus;
        this.setStatus(novoStatus);

        System.out.println("Cliente: " + clienteID + " Conectando-se ao: " + equipamento.getNome());

        Socket clientSocket = new Socket("localhost", 80);

        DataOutputStream saida = new DataOutputStream(clientSocket.getOutputStream());

        saida.writeInt(message.length());
        saida.writeBytes(message);
        saida.close();

        clientSocket.close();
    }

    public void conectarServerTemperatura(SmartHomeClient equipamento, int novaTemperatura) throws IOException {

        String message = equipamento.getValor() + " -> " + novaTemperatura;
        this.setValor(novaTemperatura);

        System.out.println("Sensor conectando-se ao servidor...");

        Socket clientSocket = new Socket("localhost", 80);

        DataOutputStream saida = new DataOutputStream(clientSocket.getOutputStream());

        saida.writeInt(message.length());
        saida.writeBytes(message);
        saida.close();

        clientSocket.close();
    }


}
