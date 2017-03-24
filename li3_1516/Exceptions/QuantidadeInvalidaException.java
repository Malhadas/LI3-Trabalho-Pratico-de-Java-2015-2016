/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li3_1516.Exceptions;


/**
 * Poderá ser lançada durante a validação de uma quantidade
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class QuantidadeInvalidaException extends Exception{
 
    /**
     * COnstrutor para objetos da classe QuantidadeInvalidaException
     */
    public QuantidadeInvalidaException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe QuantidadeInvalidaException
     * @param mensagem
     */
    public QuantidadeInvalidaException(String mensagem){
        super(mensagem);
    }
}