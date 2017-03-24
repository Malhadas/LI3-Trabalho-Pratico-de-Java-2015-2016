/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public final class TuploQuantidadeVendasFaturacaoClientes implements Serializable, Cloneable{
    
    /**
     * quantidade
     */
    private int    quantidade;
    
    /**
     * vendas
     */
    private int    vendas;
    
    /**
     * faturação
     */
    private double faturacao;
    
    /**
     * clientes
     */
    private int    clientes;
    
    
    //CONSTRUTORES
    
    /**
     * 
     * Construtor vazio para TuploQuantidadeVendasFaturacaoClientes.
     * Inicializa todos os valores a zero.
     * 
     */
    public TuploQuantidadeVendasFaturacaoClientes(){
        quantidade = vendas = clientes = 0;
        faturacao  = 0.0;
    }
    
    /**
     * 
     * Construtor parameterizado para TuploQuantidadeVendasFaturacaoClientes.
     * Inicializa as variáveis de instância com os valores recebidos como argumento.
     * 
     * @param q quantidade a inserir
     * @param v vendas a inserir
     * @param f faturação a inserir
     * @param c número de clientes a inserir
     */
    public TuploQuantidadeVendasFaturacaoClientes(int q, int v, double f, int c){
        quantidade = q;
        vendas     = v;
        faturacao  = f;
        clientes   = c;
    }

    /**
     * Construtor de cópia para TuploQuantidadeVendasFaturacaoClientes
     * 
     * @param tqvfc  Recebe um objecto instânciado a partir da classe TuploQuantidadeVendasFaturacaoClientes
     *               e apartir do mesmo instância um novo objeto dessa classe
     */
    public TuploQuantidadeVendasFaturacaoClientes(TuploQuantidadeVendasFaturacaoClientes tqvfc){
        quantidade = tqvfc.getQuantidade();
        vendas     = tqvfc.getVendas();
        faturacao  = tqvfc.getFaturacao();
        clientes   = tqvfc.getClientes();
    }
    
    
    //GETTERS
    
    /**
     * 
     * Permite obter a quantidade presente no tuplo.
     * 
     * @return quantidade presente no tuplo 
     */
    public int getQuantidade(){
        return quantidade;
    }

    /**
     * 
     * Permite obter o número de vendas presente no tuplo.
     * 
     * @return o número de vendas presente no tuplo 
     */    
    public int getVendas(){
        return vendas;
    }
    
    /**
     * 
     * Permite obter a faturação presente no tuplo.
     * 
     * @return faturação presente no tuplo 
     */
    public double getFaturacao(){
        return faturacao;
    }
    
    /**
     * 
     * Permite obter o número de clientes presente no tuplo.
     * 
     * @return o número de clientes presente no tuplo 
     */
    public int getClientes(){
        return clientes;
    }
    
    
    //METODOS
    
    /**
     * 
     * Permite atualizar os valores das vendas, dos clientes, da faturação e da
     * quantidade no tuplo. Se se pretender subtrair os valores, inserir argumentos 
     * negativos, caso contrário argumentos positivos. Usar com cuidado, pois quebra o
     * encapsulamento de dados.
     * 
     * @param tqvfc TuploQuantidadeVendasFaturacaoClientes cujos valores serão usados para 
     *              atualizar os valores deste tuplo
     */
    public void atualizarValores(TuploQuantidadeVendasFaturacaoClientes tqvfc){
        quantidade += tqvfc.getQuantidade();
        vendas     += tqvfc.getVendas();
        faturacao  += tqvfc.getFaturacao();
        clientes   += tqvfc.getClientes();
    }
    
    
    //HASHCODE, TOSTRING, EQUALS e CLONE

    /**
     * Define um código hash para objetos da classe TuploQuantidadeVendasFaturacaoClientes
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.quantidade;
        hash = 23 * hash + this.vendas;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.faturacao) ^ (Double.doubleToLongBits(this.faturacao) >>> 32));
        hash = 23 * hash + this.clientes;
        return hash;
    }

    /**
     * Verifica se o Objeto sobre o qual o método é chamado e o objeto argumento
     * são iguais sobre os seguintes critérios:
     *                           ou são exatamente o mesmo objeto.
     *                           ou têm conteúdo igual.
     * 
     * @param obj Recebe o objeto com o qual se irá verifica a igualdade.
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
        final TuploQuantidadeVendasFaturacaoClientes other = (TuploQuantidadeVendasFaturacaoClientes) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (this.vendas != other.vendas) {
            return false;
        }
        if (Double.doubleToLongBits(this.faturacao) != Double.doubleToLongBits(other.faturacao)) {
            return false;
        }
        return this.clientes == other.clientes;
    }

    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return String, representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
    public String toString() {
        return "TuploQuantidadeVendasFaturacaoClientes{" + "quantidade=" + quantidade + ", vendas=" + vendas + ", faturacao=" + faturacao + ", clientes=" + clientes + '}';
    }
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado, garante encapsulamento de dados.
     * 
     * @return TuploQuantidadeVendasFaturacaoClientes, devolve um clone do objeto sobre o qual o método é chamado
     * 
     */ 
    @Override
    public TuploQuantidadeVendasFaturacaoClientes clone(){
        return new TuploQuantidadeVendasFaturacaoClientes(this);
    }
}
