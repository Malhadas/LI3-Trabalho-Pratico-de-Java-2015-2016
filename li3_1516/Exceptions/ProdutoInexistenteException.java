/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li3_1516.Exceptions;


/**
 * Poderá ser lançada ao tentar realizar alguma operação com um código de produto que não se encontra inserido no
 * módulo.
 * 
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class ProdutoInexistenteException extends Exception{
 
    /**
     * COnstrutor para objetos da classe ProdutoInexistenteException
     */
    public ProdutoInexistenteException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe ProdutoInexistenteException
     * @param mensagem
     */
    public ProdutoInexistenteException(String mensagem){
        super(mensagem);
    }
}