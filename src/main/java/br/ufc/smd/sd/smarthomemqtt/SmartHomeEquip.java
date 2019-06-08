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
    String valor;

    public SmartHomeEquip(String idEquip) {
        this.idEquip = idEquip;
    } //fim de construtor

    public SmartHomeEquip(String idEquip, String status) {
        this.idEquip = idEquip;
        this.status = status;
    } //fim do contrutor2
    
    public SmartHomeEquip(String idEquip, String status, String valor) {
        this.idEquip = idEquip;
        this.status = status;
        this.valor = valor;
    } //fim de construtor 3

    void executar(String msg) {
        if ("lamp01".equals(this.idEquip)){
            switch (msg) {
                case "ligar":
                    this.status = "ligado";
                    this.valor = "100%";
                    break;
                case "desligar":
                    this.status = "desligado";
                    this.valor = "0%";
                    break;
                case "meia-luz":
                    this.status = "ligado em meia luz";
                    this.valor = "50%";
                    break;
                default:
                    this.status = "deu merda";
                    this.valor = "lascou";
                    break;
            }
            
        }
        if ("lamp02".equals(this.idEquip)){
            switch (msg) {
                case "ligar":
                    this.status = "ligado";
                    this.valor = "100%";
                    break;
                case "desligar":
                    this.status = "desligado";
                    this.valor = "0%";
                    break;
                case "meia-luz":
                    this.status = "ligado em meia luz";
                    this.valor = "50%";
                    break;
                default:
                    this.status = "deu merda";
                    this.valor = "lascou";
                    break;
            }
            
        }
        System.out.println(msg);
        System.out.println(this.idEquip + ": " + this.status + " - " + this.valor);
    }
    
    
} //fim de SmartHomeEquip
