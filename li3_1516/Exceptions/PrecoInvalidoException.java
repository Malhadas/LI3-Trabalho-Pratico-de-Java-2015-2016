/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li3_1516.Exceptions;


/**
 * Poderá ser lançada durante a validação de um preço
 * 
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class PrecoInvalidoException extends Exception{
 
    /**
     * COnstrutor para objetos da classe PrecoInvalidoException
     */
    public PrecoInvalidoException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe PrecoInvalidoException
     * @param mensagem
     */
    public PrecoInvalidoException(String mensagem){
        super(mensagem);
    }
}