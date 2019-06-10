/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.smd.sd.smarthomemqtt;

import javax.swing.JLabel;

/**
 *
 * @author Edvardo e Igor
 */
public class SmartHomeEquip {
    
    String idEquip;
    String status;
    JLabel equipStatus;
    
    public SmartHomeEquip(String idEquip, String status, JLabel equipStatus) {
        this.idEquip = idEquip;
        this.status = status;
        this.equipStatus = equipStatus;
    } //fim do contrutor2
    
    public void executar(String msg) {
        if (null == msg){
            this.status = "sem mensagem";
        } else switch (msg) {
            case "ligar":
                this.status = "ligado";
                this.equipStatus.setText("Ligado");
                break;
            case "desligar":
                this.status = "desligado";
                this.equipStatus.setText("Desligado");
                break;
            default:
                this.status = "deu merda";
                break;
        }
        
        System.out.println(this.idEquip + ": " + this.status);
        System.out.println();
    } //fim de executar
    
} //fim de SmartHomeEquip
