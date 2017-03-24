
package li3_1516.Exceptions;


/**
 * Poderá ser lançada ao tentar ler um ficheiro com uma extensão inválida.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class ExtensaoInvalidaException extends Exception{
 
    /**
     * COnstrutor para objetos da classe ExtensaoInvalidaException
     */
    public ExtensaoInvalidaException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe ExtensaoInvalidaException
     * @param mensagem
     */
    public ExtensaoInvalidaException(String mensagem){
        super(mensagem);
    }
}