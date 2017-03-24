
package Auxiliares_modulos;

import java.io.Serializable;

/**
 * Classe que inclui um double que representa uma faturação e um inteiro que representa uma quantidade.
 * 
 * Esta classe é usada neste projeto como wrapper, por essa razão é uma classe final.
 * 
 * Esta classe pode ser serializada, assim como pode ser colonada, por essas razões
 * implementa as seguintes interfaces: Serializble e Cloneable.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public final class DuploQuantidadeFaturacao implements Serializable, Cloneable{
    /**
     * Faturação
     */
    private double faturacao;
    /**
     * Quantidade
     */
    private int    quantidade;
    
    
    //CONSTRUTORES
    
    /**
     * Construtor vazio para DuploQuantidadeFaturacao.
     * Inicializa os valores a zero.
     */
    public DuploQuantidadeFaturacao(){
        faturacao   = 0;
        quantidade  = 0;
    }
    
    /**
     * 
     * Construtor parameterizado de DuploQuantidadeFaturacao.
     * Inicializa os valores com os indicados pelos argumentos.
     * 
     * @param v faturação a inserir
     * @param c quantidade a inserir
     */
    public DuploQuantidadeFaturacao(double v, int c){
        faturacao   = v;
        quantidade  = c;
    }
    
    /**
     * 
     * Construtor de cópia para DuploQuantidadeFaturacao.
     * 
     * @param dvc, Recebe um objecto instânciado a partir da classe DuploQuantidadeFaturacao
     *             e apartir do mesmo instancia um novo objeto dessa classe garantindo 
     *             encapsulamento de dados.
     */
    public DuploQuantidadeFaturacao(DuploQuantidadeFaturacao dvc){
        faturacao   = dvc.getFaturacao();
        quantidade  = dvc.getQuantidade();
    }

    
    //GETTERS
    
    /**
     * 
     * Permite obter a faturação
     * 
     * @return faturação
     */
    public double getFaturacao() {
        return faturacao;
    }

    /**
     * 
     * Permite obter a quantidade.
     * 
     * @return quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }
    
    
    //METODOS
    
    /**
     * 
     * Permite atualizar os valores da faturação e da quantidade.
     * Se se pretender subtrair os valores inserir argumentos negativos, caso
     * contrário argumentos positivos. Usar com cuidado, pois quebra o
     * encapsulamento de dados.
     * 
     * @param faturacao a atualizar
     * @param quantidade a atualizar
     */
    public void atualizarValores(double faturacao, int quantidade){
        this.faturacao  += faturacao;
        this.quantidade += quantidade;
    }

    /**
     * 
     * Permite atualizar os valores da faturação e da quantidade.
     * Se se pretender subtrair os valores inserir argumentos negativos, caso
     * contrário argumentos positivos. Usar com cuidado, pois quebra o
     * encapsulamento de dados.
     * 
     * @param dvc DuploQuantidadeFaturacao cujos valores serão usados para 
     *            atualizar os valores deste duplo
     */
    public void atualizarValores(DuploQuantidadeFaturacao dvc){
        this.faturacao  += dvc.getFaturacao();
        this.quantidade += dvc.getQuantidade();
    }
    
    
    //HASHCODE, EQUALS, TOSTRING, CLONE

    /**
     * Define um código hash para objetos da classe DuploQuantidadeFaturacao
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.faturacao) ^ (Double.doubleToLongBits(this.faturacao) >>> 32));
        hash = 67 * hash + this.quantidade;
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
        final DuploQuantidadeFaturacao other = (DuploQuantidadeFaturacao) obj;
        if (Double.doubleToLongBits(this.faturacao) != Double.doubleToLongBits(other.faturacao)) {
            return false;
        }
        return this.quantidade == other.quantidade;
    }

    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return String, representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
    public String toString() {
        return "DuploQuantidadeFaturacao{" + "faturacao=" + faturacao + ", quantidade=" + quantidade + '}';
    }
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado, garante encapsulamento de dados.
     * 
     * @return DadosGlobaisMeses, devolve um clone do objeto sobre o qual o método é chamado
     * 
     */ 
    @Override
    public DuploQuantidadeFaturacao clone(){
        return new DuploQuantidadeFaturacao(this);
    }
}
