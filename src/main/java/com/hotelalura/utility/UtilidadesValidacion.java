package com.hotelalura.utility;

/**
 * @author jdmon on 4/08/2023.
 * @project CurrencyConverter
 * The utils class used for validates and The ending message
 */
public class UtilidadesValidacion {
    /**
     * The method that validates a double
     * @param input Parameter entered by the user
     * @return a boolean to decide if it is a double
     */
    public static boolean validarNumero(String input){
        try {
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException numberFormatException){
            return false;
        }
    }

    public static boolean validarNuloVacio(String cadena){
        return cadena==null || cadena.isEmpty();
    }

}
