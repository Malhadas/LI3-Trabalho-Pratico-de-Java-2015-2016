/*
 * Dados das vendas, quantidade vendida e faturação relativamente a um mês específico
 */
package Auxiliares_modulos;

import java.io.Serializable;

/**
 * Classe que implementa um triplo de dois inteiros e um double.
 * Os dois inteiros representam quantidade comprada e vendas realizadas,
 * o double representa faturação obtida.
 * 
 * esta classe pode ser serializada, assim como pode ser colonada, por essas razões
 * implementa as seguintes interfaces: Serializble e Cloneable.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class TriploQuantidadeVendasFaturacao implements Serializable, Cloneable {
    
    /**
     * quantidade
     */
    private int    quantidade;
    
    /**
     * vendas
     */
    private int    vendas;
    
    /**
     * faturacao
     */
    private double faturacao;

    
    /**CONSTRUTORES**/
    
    /**
     * Construtor vazio para TriploQuantidadeVendasFaturacao.
     * Inicializa todos os campos do triplo a 0.
     */
    public TriploQuantidadeVendasFaturacao(){
        this.quantidade = 0;
        this.vendas     = 0;
        this.faturacao  = 0.0;
    }
    
    /**
     * Construtor parameterizado para TriploQuantidadeVendasFaturacao
     * 
     * @param quantidade quantidade comprada neste mês
     * @param vendas     quantidade de vendas neste mês
     * @param faturacao  saldo obtido neste mês
     */
    public TriploQuantidadeVendasFaturacao(int vendas, int quantidade, double faturacao) {
        this.quantidade = quantidade;
        this.vendas     = vendas;
        this.faturacao  = faturacao;
    }
    
    /**
     * Construtor de cópia para TriploQuantidadeVendasFaturacao
     * 
     * @param dm Recebe um objecto instânciado a partir da classe TriploQuantidadeVendasFaturacao
     *           e apartir do mesmo instância um novo objeto dessa classe
     *           garantindo o encapsulamento de dados.
     */
    public TriploQuantidadeVendasFaturacao(TriploQuantidadeVendasFaturacao dm){
        this.quantidade = dm.getQuantidade();
        this.vendas     = dm.getVendas();
        this.faturacao  = dm.getFaturacao();
    }

    /**GETTERS**/
    
    
    /**
     * Obter o número de vendas realizadas para este triplo
     * 
     * @return int Retorna o número de vendas realizadas
     */
    public int getVendas() {
        return vendas;
    }
    
    /**
     * Obter a quantidade comprada neste triplo
     * 
     * @return int Retorna a quantidade comprada
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Obter a faturação relativa a este triplo
     * 
     * @return double retorna a faturação do triplo em questão
     */
    public double getFaturacao() {
        return faturacao;
    }
    
    /**SETTERS**/
    
    /**
     * substitui o valor da quantidade comprada para este triplo
     * 
     * @param quantidade a quantidade que se pretende ser a quantidade comprada
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * substitui o número de vendas realizado neste triplo
     * 
     * @param vendas o número que se pretende que seja o número de vendas
     */
    public void setVendas(int vendas) {
        this.vendas = vendas;
    }

    /**
     * substitui o valor da faturação obtida para este triplo
     * 
     * @param faturacao a faturação que se pretende ser a faturação obtida neste triplo
     */
    public void setFaturacao(double faturacao) {
        this.faturacao = faturacao;
    }
    
    /**METODOS**/
    
    /**
     * Adiciona à quantidade vendida neste triplo um determinado valor argumento.
     * 
     * @param quantidade a quantidade que se pretende adicionar
     */
    public void addQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    /**
     * Adiciona ao número de vendas neste triplo um determinado valor argumento.
     * 
     * @param vendas o número de vendas que se pretende adicionar
     */
    public void addVendas(int vendas) {
        this.vendas += vendas;
    }

    /**
     * Adiciona à faturação deste triplo um determinado valor argumento.
     * 
     * @param faturacao o valor de faturação que se pretende adicionar
     */
    public void addFaturacao(double faturacao) {
        this.faturacao += faturacao;
    }
    
    /**
     * Subtrai à quantidade vendida neste triplo um determinado valor argumento.
     * 
     * @param quantidade a quantidade que se pretende subtrair
     */
    public void removerQuantidade(int quantidade) {
        this.quantidade -= quantidade;
    }

    /**
     * Subtrei ao número de vendas neste triplo um determinado valor argumento.
     * 
     * @param vendas o número de vendas que se pretende subtrair
     */
    public void removerVendas(int vendas) {
        this.vendas -= vendas;
    }

    /**
     * Subtrai à faturação deste triplo um determinado valor argumento.
     * 
     * @param faturacao o valor de faturação que se pretende subtrair
     */
    public void removerFaturacao(double faturacao) {
        this.faturacao -= faturacao;
    }
    
    /**
     * 
     * Permite atualizar os valores das vendas, da quantidade e da faturaçãp no
     * triplo. Se se pretender subtrair os valores, inserir argumentos negativos, 
     * caso contrário argumentos positivos. Usar com cuidado, pois quebra o
     * encapsulamento de dados.
     * 
     * @param vendas     vendas a atualizar
     * @param quantidade quantidade a atualizar
     * @param faturacao  faturação a atualizar
     */
    public void atualizarValores(int vendas, int quantidade, double faturacao){
        this.addQuantidade(quantidade);
        this.addVendas(vendas);
        this.addFaturacao(faturacao);
        
        if (this.getQuantidade()<0) this.setQuantidade(0);
        if (this.getVendas()<0)     this.setVendas(0);
        if (this.getFaturacao()<0.0)  this.setFaturacao(0.0);
        
    }

    /**
     * 
     * Permite atualizar os valores das vendas, da quantidade e da faturaçãp no
     * triplo. Se se pretender subtrair os valores, inserir argumentos negativos, 
     * caso contrário argumentos positivos. Usar com cuidado, pois quebra o
     * encapsulamento de dados.
     * 
     * @param tqvf TriploQuantidadeVendasFaturacao cujos valores serão usados para 
     *             atualizar os valores deste triplo
     */
    public void atualizarValores(TriploQuantidadeVendasFaturacao tqvf){
        atualizarValores(tqvf.getVendas(), tqvf.getQuantidade(), tqvf.getFaturacao()); 
    }
    
    /*EQUALS, CLONE, HASHCODE, TOSTRING*/

    /**
     * Define um código hash para objetos da classe TriploQuantidadeVendasFaturacao
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.quantidade;
        hash = 53 * hash + this.vendas;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.faturacao) ^ (Double.doubleToLongBits(this.faturacao) >>> 32));
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
        final TriploQuantidadeVendasFaturacao other = (TriploQuantidadeVendasFaturacao) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (this.vendas != other.vendas) {
            return false;
        }
        return Double.doubleToLongBits(this.faturacao) == Double.doubleToLongBits(other.faturacao);
    }

    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return String, representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
    public String toString() {
        return "TriploQuantidadeVendasFaturacao{" + "quantidade=" + quantidade + ", vendas=" + vendas + ", faturacao=" + faturacao + '}';
    }
    
    public TriploQuantidadeVendasFaturacao getTriplo(){
        return clone();
    }
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado, garante encapsulamento de dados.
     * 
     * @return TriploQuantidadeVendasFaturacao, devolve um clone do objeto sobre o qual o método é chamado
     * 
     */  
    @Override
    public TriploQuantidadeVendasFaturacao clone(){
        return new TriploQuantidadeVendasFaturacao(this);
    }

}

