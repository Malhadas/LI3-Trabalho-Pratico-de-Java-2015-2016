
package li3_1516.Exceptions;


/**
 * Classe que irá permitir cancelar qualquer acção nos menus.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class GoBackException extends Exception{
 
    /**
     * COnstrutor para objetos da classe GoBackException
     */
    public GoBackException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe GoBackException
     * @param mensagem
     */
    public GoBackException(String mensagem){
        super(mensagem);
    }
}