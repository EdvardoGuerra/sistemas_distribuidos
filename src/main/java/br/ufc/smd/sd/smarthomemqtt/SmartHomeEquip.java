/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.smd.sd.smarthomemqtt;

/**
 *
 * @author Edvardo e Igor
 */
public class SmartHomeEquip {
    
    String idEquip;
    String status;

    public SmartHomeEquip(String idEquip, String status) {
        this.idEquip = idEquip;
        this.status = status;
    } //fim do contrutor2
    
    void executar(String msg) {
        if (null == msg){
            this.status = "sem mensagem";
        } else switch (msg) {
            case "ligar":
                this.status = "ligado";
                break;
            case "desligar":
                this.status = "desligado";
                break;
            default:
                this.status = "deu merda";
                break;
        }
        
        System.out.println(this.idEquip + ": " + this.status);
        System.out.println();
    }
    
    
} //fim de SmartHomeEquip
