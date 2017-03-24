
package li3_1516.Exceptions;


/**
 * Poderá ser lançada durante a validação de um cliente
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class ClienteInvalidoException extends Exception{
 
    /**
     * COnstrutor para objetos da classe ClienteInvalidoException
     */
    public ClienteInvalidoException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe ClienteInvalidoException
     * @param mensagem
     */
    public ClienteInvalidoException(String mensagem){
        super(mensagem);
    }
}