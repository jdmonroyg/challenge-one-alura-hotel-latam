package com.hotelalura.controller;

import javax.swing.*;

/**
 * @author jdmon on 7/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class SalidaController {
    public static void confirmarSalida(){
        int response = JOptionPane.showConfirmDialog(null, "¿Quieres salir del programa?");
        if(response==0){
            System.exit(0);
        }
    }
}
