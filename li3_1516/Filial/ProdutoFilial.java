/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li3_1516.Filial;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe auxiliar a Filial, contêm os dados relativos às compras de um produto
 * 
 * @author Grupo 80
 */
public class ProdutoFilial implements Cloneable, Serializable{
    
    /**
     * Clientes que compraram
     */
    private Set<String> clientes;
    
    /**
     * Quantidade comprada
     */
    private int quantidade;
    
    /**
     * Construtor vazio
     */
    public ProdutoFilial(){
        quantidade = 0;
        clientes   = new HashSet<>();
    }
    
    /**
     * Construtor parameterizado
     * 
     * @param clis
     * @param quant 
     */
    public ProdutoFilial(Collection<String> clis, int quant){
        quantidade = quant;
        clientes   = new HashSet<>(clis.size());
        clientes.addAll(clis);
    }
    
    /**
     * Construtor de cópia
     * ´
     * @param pf 
     */
    public ProdutoFilial(ProdutoFilial pf){
        quantidade = pf.getQuantidade();
        clientes   = new HashSet<>(pf.getClientesCompradoresSemEncapsulamento());
    }
    
    /**
     * Permite obter a quantidade
     * @return a quantidade
     */
    public int getQuantidade(){
        return quantidade;
    }
    
    /**
     * Permite obter os clientes
     * @return clientes que compraram
     */
    public Set<String> getClientes(){
        return new HashSet<>(this.clientes);
    }
    
    /**
     * Permite obte rnúmero de clientes compradores
     * @return número de clientes compradores
     */
    public int getNumeroCompradores(){
        return clientes.size();
    }
    
    /**
     * Verifica se algum cliente comprou
     * @return true se clientes tiverem comprado false no caso contrário
     */
    public boolean isEmpty(){
        return this.clientes.isEmpty();
    }
    
    /**
     * Adiciona cliente comprador
     * @param cli cliente comprador a inserir
     */
    public void add(String cli){
        this.clientes.add(cli);
    }
    
    /**
     * Atualiza valores de acordo com os valores noutra instância desta classe
     * @param pf outra instância desta classe
     */
    public void atualizarValores(ProdutoFilial pf){
        quantidade+=pf.getQuantidade();
        this.clientes.addAll(pf.getClientesCompradoresSemEncapsulamento());  
    }
    
    /**
     * Atualiza a quantidade somando-a com a argumento
     * @param quant quantidade a somar
     */
    public void atualizarValores(int quant){
        quantidade+=quant;
    }
    
    /**
     * Permite obter os clientes compradores sem encapsular
     * 
     * @return 
     */
    protected Set<String> getClientesCompradoresSemEncapsulamento(){
        return clientes;
    }
    
    /**
     * Permite substituir os clientes compradores pelos argumento
     * @param c clientes compradores
     */
    public void setClientes(Collection<String> c){
        this.clientes = new HashSet(c);
    }
    
    @Override
    public ProdutoFilial clone(){
        return new ProdutoFilial(this);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.clientes);
        hash = 97 * hash + this.quantidade;
        return hash;
    }

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
        final ProdutoFilial other = (ProdutoFilial) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        return Objects.equals(this.clientes, other.clientes);
    }

    @Override
    public String toString() {
        return "ProdutoFilial{" + "clientes=" + clientes + ", quantidade=" + quantidade + '}';
    }
    
    
}
