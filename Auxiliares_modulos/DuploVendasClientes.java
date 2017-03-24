/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares_modulos;

import java.io.Serializable;

/**
 * Classe que inclui um inteiro que representa vendas e outro que representa um número de clientes.
 * 
 * Esta classe é usada neste projeto como wrapper, por essa razão é uma classe final.
 * 
 * Esta classe pode ser serializada, assim como pode ser colonada, por essas razões
 * implementa as seguintes interfaces: Serializble e Cloneable.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public final class DuploVendasClientes implements Serializable, Cloneable {
    
    /**
     * vendas
     */
    private int vendas;
    
    /**
     * clientes
     */
    private int clientes;
    
    
    //CONSTRUTORES
    
    /**
     * 
     * Construtor vazio para a classe DuploVendasClientes
     * Inicializa os valores do duplo a zero.
     * 
     */
    public DuploVendasClientes(){
        vendas   = 0;
        clientes = 0;
    }
    
    /**
     * 
     * Construtor parameterizado para a classe DuploVendasClientes.
     * inicializa os valores com os argumentos recebidos.
     * 
     * @param v número de vendas
     * @param c número de clientes
     */
    public DuploVendasClientes(int v, int c){
        vendas   = v;
        clientes = c;
    }
    
    /**
     * 
     * Construtor de cópia para DuploVendasClientes.
     * 
     * @param dvc, Recebe um objecto instânciado a partir da classe DuploVendasClientes
     *             e apartir do mesmo instancia um novo objeto dessa classe.
     */
    public DuploVendasClientes(DuploVendasClientes dvc){
        vendas   = dvc.getVendas();
        clientes = dvc.getClientes();
    }

    
    //GETTERS
    
    /**
     * 
     * Permite obter o número de vendas.
     * 
     * @return o número de vendas presente no duplo 
     */
    public int getVendas() {
        return vendas;
    }

    /**
     * 
     * Permite obter o número de clientes.
     * 
     * @return o número de clientes presente no duplo 
     */
    public int getClientes() {
        return clientes;
    }

    
    //METODOS
    
    /**
     * 
     * Permite atualizar os valores das vendas e dos clientes no duplo.
     * Se se pretender subtrair os valores, inserir argumentos negativos, caso
     * contrário argumentos positivos. Usar com cuidado, pois quebra o
     * encapsulamento de dados.
     * 
     * @param vendas   vendas a atualizar
     * @param clientes clientes a atualizar
     */
    public void atualizarValores(int vendas, int clientes){
        this.vendas   += vendas;
        this.clientes += clientes;
    }

    /**
     * 
     * Permite atualizar os valores das vendas e dos clientes.
     * Se se pretender subtrair os valores, inserir argumentos negativos, caso
     * contrário argumentos positivos. Usar com cuidado, pois quebra o
     * encapsulamento de dados.
     * 
     * @param dvc DuploVendasClientes cujos valores serão usados para 
     *            atualizar os valores deste duplo
     */
    public void atualizarValores(DuploVendasClientes dvc){
        this.vendas   += dvc.getVendas();
        this.clientes += dvc.getClientes();
    }

    
    //HASHCODE, EQUALS, TOSTRING, CLONE
    
    /**
     * Define um código hash para objetos da classe DuploVendasClientes
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.vendas;
        hash = 59 * hash + this.clientes;
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
        final DuploVendasClientes other = (DuploVendasClientes) obj;
        if (this.vendas != other.vendas) {
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
        return "DuploVendasClientes{" + "vendas=" + vendas + ", clientes=" + clientes + '}';
    }
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado, garante encapsulamento de dados.
     * 
     * @return DadosGlobaisMeses, devolve um clone do objeto sobre o qual o método é chamado
     * 
     */ 
    @Override
    public DuploVendasClientes clone(){
        return new DuploVendasClientes(this);
    }
}
