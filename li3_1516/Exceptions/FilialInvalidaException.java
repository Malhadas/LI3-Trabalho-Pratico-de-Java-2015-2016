
package li3_1516.Exceptions;


/**
 * Poderá ser lançada durante a validação de uma filial
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class FilialInvalidaException extends Exception{
 
    /**
     * COnstrutor para objetos da classe FilialInvalidaException
     */
    public FilialInvalidaException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe FilialInvalidaException
     * @param mensagem
     */
    public FilialInvalidaException(String mensagem){
        super(mensagem);
    }
}