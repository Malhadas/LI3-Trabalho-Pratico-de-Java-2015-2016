/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li3_1516.Interface.AuxiliaresInterface.Vendas;

import java.io.Serializable;

/**
 *
 * Esta classe é um wrapper de vários contadores relativos á última leitura e validação
 * do ficheiro de vendas, como tal a classe é final e imutável.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public final class EstatisticasLeituraVendas implements Serializable, Cloneable {
    ///////////vars para apresentação
    private final int produtos_invalidos;
    private final int precos_invalidos;
    private final int quantidades_invalidas;
    private final int regimes_invalidos;
    private final int clientes_invalidos;
    private final int meses_invalidos;
    private final int filiais_invalidas;
    private final int preco_zero;
    
    private final int linhas_totais;
    private final int linhas_validas;
    /////////////////////////////////
    
    
    /**
     * Construtor vazio
     */
    public EstatisticasLeituraVendas(){
        produtos_invalidos    = 0;
        precos_invalidos      = 0;
        quantidades_invalidas = 0;
        regimes_invalidos     = 0;
        clientes_invalidos    = 0;
        meses_invalidos       = 0;
        filiais_invalidas     = 0;
        preco_zero            = 0;
        
        linhas_totais         = 0;
        linhas_validas        = 0;
    }
    
    /**
     * Construtor parameterizado
     * 
     * @param produtos_invalidos
     * @param precos_invalidos
     * @param quantidades_invalidas
     * @param regimes_invalidos
     * @param clientes_invalidos
     * @param meses_invalidos
     * @param filiais_invalidas
     * @param preco_zero
     * @param linhas_totais
     * @param linhas_validas 
     */
    public EstatisticasLeituraVendas(int produtos_invalidos, 
                                     int precos_invalidos, 
                                     int quantidades_invalidas, 
                                     int regimes_invalidos,
                                     int clientes_invalidos,
                                     int meses_invalidos,
                                     int filiais_invalidas,
                                     int preco_zero,
                                     int linhas_totais,
                                     int linhas_validas){

        this.produtos_invalidos    = produtos_invalidos;
        this.precos_invalidos      = precos_invalidos;
        this.quantidades_invalidas = quantidades_invalidas;
        this.regimes_invalidos     = regimes_invalidos;
        this.clientes_invalidos    = clientes_invalidos;
        this.meses_invalidos       = meses_invalidos;
        this.filiais_invalidas     = filiais_invalidas;
        this.preco_zero            = preco_zero;
        
        this.linhas_totais         = linhas_totais;
        this.linhas_validas        = linhas_validas;
    }

    
    /**
     * Construtor de cópia
     * 
     * @param elv insância de EstatisticasLeituraVendas a copiar 
     */
    EstatisticasLeituraVendas(EstatisticasLeituraVendas elv) {
        this.produtos_invalidos    = elv.getProdutos_invalidos();
        this.precos_invalidos      = elv.getPrecos_invalidos();
        this.quantidades_invalidas = elv.getQuantidades_invalidas();
        this.regimes_invalidos     = elv.getRegimes_invalidos();
        this.clientes_invalidos    = elv.getClientes_invalidos();
        this.meses_invalidos       = elv.getMeses_invalidos();
        this.filiais_invalidas     = elv.getFiliais_invalidas();
        this.preco_zero            = elv.getPreco_zero();
        
        this.linhas_totais         = elv.getLinhas_totais();
        this.linhas_validas        = elv.getLinhas_validas();
    }

    /**
     * obter linhas de produtos invalidos
     * 
     * @return linhas de produtos invalidos 
     */
     public int getProdutos_invalidos() {
        return produtos_invalidos;
    }
    
    /**
     * obter linhas com precos a zero
     * 
     * @return linhas com precos a zero
     */
    public int getPreco_zero(){
        return this.preco_zero;
    }

    /**
     * obter linhas de precos invalidos
     * 
     * @return linhas de precos invalidos 
     */    
    public int getPrecos_invalidos() {
        return precos_invalidos;
    }

    /**
     * obter linhas de quantidade invalidos
     * 
     * @return linhas de quantidade invalidos 
     */
    public int getQuantidades_invalidas() {
        return quantidades_invalidas;
    }

    /**
     * obter linhas de regimes invalidos
     * 
     * @return linhas de regimes invalidos 
     */
    public int getRegimes_invalidos() {
        return regimes_invalidos;
    }

    /**
     * obter linhas de clientes invalidos
     * 
     * @return linhas de clientes invalidos 
     */
    public int getClientes_invalidos() {
        return clientes_invalidos;
    }

    /**
     * obter linhas de meses invalidos
     * 
     * @return linhas de meses invalidos 
     */
    public int getMeses_invalidos() {
        return meses_invalidos;
    }

    /**
     * obter linhas de filiais invalidos
     * 
     * @return linhas de filiais invalidos 
     */
    public int getFiliais_invalidas() {
        return filiais_invalidas;
    }

    /**
     * obter total de linhas
     * 
     * @return total de linhas
     */
    public int getLinhas_totais() {
        return linhas_totais;
    }

    /**
     * obter linhas válidas
     * 
     * @return linhas válidas
     */
    public int getLinhas_validas() {
        return linhas_validas;
    }
    
    //HASHCODE, TOSTRING, EQUALS, CLONE
    
    /**
     * Define um código hash para objetos da classe EstatisticasLeituraVendas
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.produtos_invalidos;
        hash = 61 * hash + this.precos_invalidos;
        hash = 61 * hash + this.quantidades_invalidas;
        hash = 61 * hash + this.regimes_invalidos;
        hash = 61 * hash + this.clientes_invalidos;
        hash = 61 * hash + this.meses_invalidos;
        hash = 61 * hash + this.filiais_invalidas;
        hash = 61 * hash + this.linhas_totais;
        hash = 61 * hash + this.linhas_validas;
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
        final EstatisticasLeituraVendas other = (EstatisticasLeituraVendas) obj;
        if (this.produtos_invalidos != other.produtos_invalidos) {
            return false;
        }
        if (this.precos_invalidos != other.precos_invalidos) {
            return false;
        }
        if (this.quantidades_invalidas != other.quantidades_invalidas) {
            return false;
        }
        if (this.regimes_invalidos != other.regimes_invalidos) {
            return false;
        }
        if (this.clientes_invalidos != other.clientes_invalidos) {
            return false;
        }
        if (this.meses_invalidos != other.meses_invalidos) {
            return false;
        }
        if (this.filiais_invalidas != other.filiais_invalidas) {
            return false;
        }
        if (this.linhas_totais != other.linhas_totais) {
            return false;
        }
        return this.linhas_validas == other.linhas_validas;
    }

    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return String, representação do objeto em questão sobre forma de uma instância da classe String
     */ 
    @Override
    public String toString() {
        return "EstatisticasLeituraVendas{" + "produtos_invalidos=" + produtos_invalidos + ", precos_invalidos=" + precos_invalidos + ", quantidades_invalidas=" + quantidades_invalidas + ", regimes_invalidos=" + regimes_invalidos + ", clientes_invalidos=" + clientes_invalidos + ", meses_invalidos=" + meses_invalidos + ", filiais_invalidas=" + filiais_invalidas + ", linhas_totais=" + linhas_totais + ", linhas_validas=" + linhas_validas + '}';
    }
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado.
     * 
     * @return EstatisticasLeituraVendas, devolve um clone do objeto sobre o qual o método é chamado
     */ 
    @Override
    public EstatisticasLeituraVendas clone(){
        return new EstatisticasLeituraVendas(this);
    }
    
}
