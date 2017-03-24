
package li3_1516.Interface.AuxiliaresInterface;

import java.io.Serializable;

/**
 *
 * par que contêm dois inteiros, inserções e total.
 * Usado para guardar o número de linhas total e o
 * número realmente inserido (validado)
 * 
 * @author  Grupo 80
 * @version 2016
 */
public final class ParInsercoesTotal implements Cloneable, Serializable{
    private final int insercoes;
    private final int total;
    
    
    /**CONSTRUTORES**/
    
    /**
     * construtor vazio
     */
    public ParInsercoesTotal(){
        insercoes = 0;
        total     = 0;
    }
    
    /**
     * construtor parameterizado
     * 
     * @param i linhas validadas
     * @param t total de linhas
     */
    public ParInsercoesTotal(int i, int t){
        insercoes = i;
        total     = t;
    }
    
    /**
     * Construtor de cópia
     * 
     * @param pit instância a copiar 
     */
    public ParInsercoesTotal(ParInsercoesTotal pit){
        insercoes = pit.getInsercoes();
        total     = pit.getTotal();
    }

    /**GETTERS & SETTERS**/
    
    /**
     * Obter as inserções
     * @return linhas inseridas
     */
    public int getInsercoes() {
        return insercoes;
    }

    /**
     * Obter o total
     * @return total de linhas
     */
    public int getTotal() {
        return total;
    }

    
    /**EQUALS HASHCODE TOSTRING CLONE**/

   /**
     * Define um código hash para objetos da classe ParInsercoesTotal
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.insercoes;
        hash = 29 * hash + this.total;
        return hash;
    }

    /**
     * Verifica se o Objeto sobre o qual o método é chamado e o objeto argumento
     * são iguais sobre os seguintes critérios:
     *                           ou são exatamente o mesmo objeto.
     *                           ou têm conteúdo igual.
     * 
     * @param obj, Recebe o objeto com o qual se irá verifica a igualdade.
     * @return boolean, devolve true se o objeto argumento for igual ao objeto onde é chamado
     *                  devolve false no caso contrário.
     *
     */ 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParInsercoesTotal other = (ParInsercoesTotal) obj;
        if (this.insercoes != other.insercoes) {
            return false;
        }
        return this.total == other.total;
    }

    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
    public String toString() {
        return "ParInsercoesTotal{" + "insercoes=" + insercoes + ", total=" + total + '}';
    }
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado.
     * 
     * @return ParInsercoesTotal, devolve um clone do objeto sobre o qual o método é chamado
     */ 
    @Override
    public ParInsercoesTotal clone(){
        return new ParInsercoesTotal(this);
    }
}