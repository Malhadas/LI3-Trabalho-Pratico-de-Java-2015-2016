/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li3_1516.Exceptions;


/**
 * Poderá ser lançada durante a validação de um regime
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class RegimeInvalidoException extends Exception{
 
    /**
     * COnstrutor para objetos da classe RegimeInvalidoException
     */
    public RegimeInvalidoException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe RegimeInvalidoException
     * @param mensagem
     */
    public RegimeInvalidoException(String mensagem){
        super(mensagem);
    }
}