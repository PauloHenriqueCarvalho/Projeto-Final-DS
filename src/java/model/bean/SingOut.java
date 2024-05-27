/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Senai
 */
public class SingOut {
    private static boolean sair;

    public SingOut() {
    }

    public static boolean isSair() {
        return sair;
    }

    public static void setSair(boolean sair) {
        SingOut.sair = sair;
    }

    
    
    
}
