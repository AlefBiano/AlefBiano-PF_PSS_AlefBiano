/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.ArrayList;

/**
 *
 * @author biano
 */
public class UtilitarioString {

    private static UtilitarioString instanciaUtilitarioString;

    private UtilitarioString() {
    }

    public static UtilitarioString getInstanciaUtilitarioString() {
        if (instanciaUtilitarioString == null) {
            return instanciaUtilitarioString = new UtilitarioString();
        } else {
            return instanciaUtilitarioString;
        }
    }

    public String substitui(String texto, ArrayList<String> palavras, String simbolo) {
        for (String palavra : palavras) {
            var tam = palavra.length();
            String teste = simbolo;
            for (int i = 0; i < tam-1; i++) {
                teste = teste+simbolo;
            }
            texto = texto.replaceAll(palavra, teste);
        }
        return texto;
    }

}
