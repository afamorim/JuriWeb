package br.com.vortice.ijuri.core.abstracao.util;

/**
 * Classe Responsável por auxiliar a manipulação de strings  
 * @author Amadeu
 */

public class StringUtil {
    
    /**
     * Recebe uma string e preenche a esquerda com um caracter por uma determinada quantidade de vezes. 
     * @param string
     * @param length (quantidade de vezes a serem prenchidos o caracter)
     * @param character
     * @return
     */
    public static String preencherEsquerda(String string, int length,char character){
        StringBuffer numFormatado = new StringBuffer(string);
        while(numFormatado.length()<length){
            numFormatado.insert(0,character);
        }
        return numFormatado.toString();
    }   
}
