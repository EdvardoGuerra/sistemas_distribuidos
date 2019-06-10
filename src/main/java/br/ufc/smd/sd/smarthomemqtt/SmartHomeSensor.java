/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.smd.sd.smarthomemqtt;

import javax.swing.JLabel;

/**
 *
 * @author Edvardo
 */
public class SmartHomeSensor extends SmartHomeEquip{
    
    public SmartHomeSensor(String idEquip, String status, JLabel equipStatus) {
        super(idEquip, status, equipStatus);
    }

    @Override
    public void executar(String msg) {
        if (null == msg){
            this.status = "sem mensagem";
        } else {
            this.status = msg + " °C";
            this.equipStatus.setText(msg + " °C");
        }
        
        System.out.println(this.idEquip + ": " + this.status);
        System.out.println();
    } //fim de executar    
}
