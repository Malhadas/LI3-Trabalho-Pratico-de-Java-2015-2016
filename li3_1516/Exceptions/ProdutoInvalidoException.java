/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li3_1516.Exceptions;


/**
 * Poderá ser lançada durante a validação de um produto
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class ProdutoInvalidoException extends Exception{
 
    /**
     * COnstrutor para objetos da classe ProdutoInvalidoException
     */
    public ProdutoInvalidoException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe ProdutoInvalidoException
     * @param mensagem
     */
    public ProdutoInvalidoException(String mensagem){
        super(mensagem);
    }
}