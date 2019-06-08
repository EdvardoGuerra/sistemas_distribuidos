package br.ufc.smd.sd.smarthomesockets;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {


    public static void main(String[] args) {
        try {

            ServerSocket servidor = new ServerSocket(80);
            serverInfo(servidor);
            inicializaGUI();


            while (true) {


                String msgCliente;
                Socket cliente = servidor.accept();

                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                DataInputStream entrada = new DataInputStream(cliente.getInputStream());

                msgCliente = entrada.readLine();

                System.out.println("MUDANÇA DE STATUS: " + msgCliente);
                System.out.println("--------------------------------------");
                entrada.close();
                cliente.close();

            }
        } catch (Exception e) {

            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);

        } finally {

        }
    }


    public static void serverInfo(ServerSocket server) {

        InetAddress inet = server.getInetAddress();
        System.out.println("---------- SERVER INFO ----------");
        System.out.println("HostAddress = " + inet.getHostAddress());
        System.out.println("HostName = " + inet.getHostName());
        System.out.println("HostName = " + inet.getHostName());
        System.out.println("Porta = " + server.getLocalPort());
        System.out.println("---------- SERVER INFO ----------");
    }

    public static void inicializaGUI() {
        SmartHomeGUI smartHomeGUI = new SmartHomeGUI();
        smartHomeGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        smartHomeGUI.setSize(400, 400);
        smartHomeGUI.setVisible(true);
    }


}

