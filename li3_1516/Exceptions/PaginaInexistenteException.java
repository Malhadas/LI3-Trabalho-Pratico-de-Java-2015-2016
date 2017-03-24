
package li3_1516.Exceptions;

/**
 * 
 * Para ser lançada quando se tenta aceder a uma página não existente de uma
 * apresentação paginada.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class PaginaInexistenteException extends Exception{
 
    /**
     * COnstrutor para objetos da classe PaginaInexistenteException
     */
    public PaginaInexistenteException(){
        super();
    }

    /**
     * COnstrutor para objetos da classe PaginaInexistenteException
     * @param mensagem
     */
    public PaginaInexistenteException(String mensagem){
        super(mensagem);
    }
}
