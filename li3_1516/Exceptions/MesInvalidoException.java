
package li3_1516.Exceptions;


/**
 * Poderá ser lançada durante a validação de um mes
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class MesInvalidoException extends Exception{
 
    /**
     * COnstrutor para objetos da classe MesInvalidoException
     */
    public MesInvalidoException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe MesInvalidoException
     * @param mensagem
     */
    public MesInvalidoException(String mensagem){
        super(mensagem);
    }
}