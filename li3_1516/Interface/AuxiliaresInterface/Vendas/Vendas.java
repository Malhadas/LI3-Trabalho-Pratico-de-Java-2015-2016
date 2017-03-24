package li3_1516.Interface.AuxiliaresInterface.Vendas;


import java.io.Serializable;
import li3_1516.Exceptions.*;
import java.util.ArrayList;
import java.util.List;
import li3_1516.Catalogo.Catalogo;
import li3_1516.Faturacao.Faturacao;
import li3_1516.Filial.Filial;
import li3_1516.Interface.AuxiliaresInterface.ValoresFixos;

/**
 *
 * Esta classe auxilia á validação das linhas do ficheiro de vendas e á criação da classe wrapper
 * EstatisticasLeituraVendas.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public final class Vendas implements Serializable, Cloneable {
    
    ///////////vars para apresentação
    private int produtos_invalidos;
    private int precos_invalidos;
    private int quantidades_invalidas;
    private int regimes_invalidos;
    private int clientes_invalidos;
    private int meses_invalidos;
    private int filiais_invalidas;
    private int preco_zero;
    
    private final int linhas_totais;
    private int linhas_validas;
    /////////////////////////////////


    
    /**CONSTRUTORES**/
    
    /**
     * Construtor vazio
     */
    public Vendas(){
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
     * Permite validar linhas argumento
     * 
     * @param linhas linhas a validar
     * @param prods
     * @param clis
     * @param f
     * @param fil
     * @param paralelismo indicação de se se pretende validar paralelamente ou não
     */
    public Vendas(ArrayList<String> linhas, Catalogo prods, Catalogo clis, Faturacao f, List<Filial> fil, boolean paralelismo){
        produtos_invalidos    = 0;
        precos_invalidos      = 0;
        quantidades_invalidas = 0;
        regimes_invalidos     = 0;
        clientes_invalidos    = 0;
        meses_invalidos       = 0;
        filiais_invalidas     = 0;
        preco_zero            = 0;
        
        linhas_totais         = linhas.size();
        linhas_validas        = linhas.size();

        if (paralelismo) validaVendasComParalelismo(linhas, prods, clis, f, fil);
        else validaVendasSemParalelismo(linhas, prods, clis, f, fil);
    }
    
    
    /**GETTERS**/
    
    /**
     * Permite obter a classe wrapper EstatisticasLeituraVendas com estatísticas
     * da última leitura para apresentação ao utilizador.
     * 
     * @return EstatisticasLeituraVendas
     */
    public EstatisticasLeituraVendas getEstatisticasLeituraVendas() {
        return new EstatisticasLeituraVendas(produtos_invalidos,
                                             precos_invalidos,
                                             quantidades_invalidas,
                                             regimes_invalidos,
                                             clientes_invalidos,
                                             meses_invalidos,
                                             filiais_invalidas,
                                             preco_zero,
                                             linhas_totais,
                                             linhas_validas
                                            );
    }

    
    
    
    //METODOS
    
    private synchronized void atualizaProdutos_invalidos(int p){
        produtos_invalidos+=p;
    }
    
    private synchronized void atualizaPrecos_invalidos(int p){
        precos_invalidos+=p;
    }
    
    private synchronized void atualizaQuantidades_invalidas(int p){
        quantidades_invalidas+=p;
    }
    
    private synchronized void atualizaRegimes_invalidos(int p){
        regimes_invalidos+=p;
    }
    
    private synchronized void atualizaClientes_invalidos(int p){
        clientes_invalidos+=p;
    }
    
    private synchronized void atualizaMeses_invalidos(int p){
        meses_invalidos+=p;
    }
    
    private synchronized void atualizaFiliais_invalidas(int p){
        filiais_invalidas+=p;
    }
    
    private synchronized void atualizaPreco_zero(int p){
        preco_zero+=p;
    }
    
    private synchronized void atualizaLinhas_validas(int p){
        linhas_validas+=p;
    }
    
    
    
    
    
    private void validaVendasComParalelismo(List<String> linhas, Catalogo prods, Catalogo clis, Faturacao faturacao, List<Filial> filiais){
        
        //validação
        
        linhas.parallelStream().forEach((compra) -> {
            String[] divisao =  compra.split(" ");
            
            try{
                    String prod  = validaProduto   (divisao[0], prods);
                    double preco = validaPreco     (divisao[1]);
                    int    quant = validaQuantidade(divisao[2]);
                    String reg   = validaRegime    (divisao[3]);
                    String cli   = validaCliente   (divisao[4], clis);
                    int    mes   = validaMes       (divisao[5]);
                    int    fil   = validaFilial    (divisao[6]);
                
                    if(fil>ValoresFixos.N_FILIAIS){ //Problema da ordem poderia ser corrigido recorrendo a um map.
                        ValoresFixos.N_FILIAIS = fil;
                        filiais.add(new Filial());
                    }
                    if(fil>ValoresFixos.N_MESES  ) ValoresFixos.N_MESES   = fil;
                
                    //se nao foi lançada nenhuma exceção, entao a venda é válida.
                    Venda venda = new Venda(prod, cli, mes, fil, quant, preco, reg);
                    if (preco==0) atualizaPreco_zero(1);
                    faturacao.add(venda);
                    filiais.get(fil-1).add(venda);   
            } catch (ProdutoInvalidoException pie){
                atualizaProdutos_invalidos(1);
                atualizaLinhas_validas(-1);
            } catch (PrecoInvalidoException pie){
                atualizaPrecos_invalidos(1);
                atualizaLinhas_validas(-1);
            } catch (QuantidadeInvalidaException qie){
                atualizaQuantidades_invalidas(1);
                atualizaLinhas_validas(-1);
            } catch (RegimeInvalidoException rie){
                atualizaRegimes_invalidos(1);
                atualizaLinhas_validas(-1);
            } catch (ClienteInvalidoException cie){
                atualizaClientes_invalidos(1);
                atualizaLinhas_validas(-1);
            } catch (MesInvalidoException mie){
                atualizaMeses_invalidos(1);
                atualizaLinhas_validas(-1);
            } catch (FilialInvalidaException fie){
                atualizaFiliais_invalidas(1);
                atualizaLinhas_validas(-1);
            }
        });
    }
    
    
    
    
    
    
    private void validaVendasSemParalelismo(List<String> linhas, Catalogo prods, Catalogo clis, Faturacao faturacao, List<Filial> filiais){
        
        //validação
        
        linhas.stream().forEach((compra) -> {
            String[] divisao =  compra.split(" ");
            
            try{
                    String prod  = validaProduto   (divisao[0], prods);
                    double preco = validaPreco     (divisao[1]);
                    int    quant = validaQuantidade(divisao[2]);
                    String reg   = validaRegime    (divisao[3]);
                    String cli   = validaCliente   (divisao[4], clis);
                    int    mes   = validaMes       (divisao[5]);
                    int    fil   = validaFilial    (divisao[6]);
                
                    if(fil>ValoresFixos.N_FILIAIS){ //Problema da ordem poderia ser corrigido recorrendo a um map.
                        ValoresFixos.N_FILIAIS = fil;
                        filiais.add(new Filial());
                    }
                    if(fil>ValoresFixos.N_MESES  ) ValoresFixos.N_MESES   = fil;
                
                    //se nao foi lançada nenhuma exceção, entao a venda é válida.
                    Venda venda = new Venda(prod, cli, mes, fil, quant, preco, reg);
                    if (preco==0) preco_zero++;
                    faturacao.add(venda);
                    filiais.get(fil-1).add(venda);   
            } catch (ProdutoInvalidoException pie){
                produtos_invalidos++;
                linhas_validas--;
            } catch (PrecoInvalidoException pie){
                precos_invalidos++;
                linhas_validas--;
            } catch (QuantidadeInvalidaException qie){
                quantidades_invalidas++;
                linhas_validas--;
            } catch (RegimeInvalidoException rie){
                regimes_invalidos++;
                linhas_validas--;
            } catch (ClienteInvalidoException cie){
                clientes_invalidos++;
                linhas_validas--;
            } catch (MesInvalidoException mie){
                meses_invalidos++;
                linhas_validas--;
            } catch (FilialInvalidaException fie){
                filiais_invalidas++;
                linhas_validas--;
            }
        });
    }
            


    private String validaProduto(String prod, Catalogo prods) throws ProdutoInvalidoException{
        if (prod==null) throw new ProdutoInvalidoException();
        if (!(prod.length()==ValoresFixos.PRODUTO_TAMANHO)) throw new ProdutoInvalidoException();
        if (!(prods.existe(prod))) throw new ProdutoInvalidoException();
      
        for(int i=0;i<prod.length();i++){
            if ((i==0||i==1)&&(!(prod.charAt(i)>='A'&&prod.charAt(i)<='Z'))) throw new ProdutoInvalidoException();
            else if ((i>1) && (!(prod.charAt(i)>='0'&&prod.charAt(i)<='9'))) throw new ProdutoInvalidoException();
	}

        return prod;
    }
    
    private double validaPreco(String preco) throws PrecoInvalidoException{
        if (preco==null) throw new PrecoInvalidoException();
        
        try {
            double precoDouble = Double.parseDouble(preco);
            if (!(precoDouble>=ValoresFixos.PRECO_MIN && precoDouble<=ValoresFixos.PRECO_MAX)) throw new PrecoInvalidoException();
            return precoDouble;
        } catch (NumberFormatException nfe){
            throw new PrecoInvalidoException();
        }
    }
    
    private int validaQuantidade(String quant) throws QuantidadeInvalidaException{
        if (quant==null) throw new QuantidadeInvalidaException();
        
        try {
            int quantInt = Integer.parseInt(quant);
            if (!(quantInt>=ValoresFixos.QUANT_MIN && quantInt<=ValoresFixos.QUANT_MAX)) throw new QuantidadeInvalidaException();
            return quantInt;
        } catch (NumberFormatException nfe){
            throw new QuantidadeInvalidaException();
        }
    }

    private String validaRegime(String regime) throws RegimeInvalidoException{
        if (regime==null) throw new RegimeInvalidoException();
        
        if (regime.equals(ValoresFixos.REGIME_N) || 
            regime.equals(ValoresFixos.REGIME_P)) 
                return regime;
        
        throw new RegimeInvalidoException(); 
    }
    
    private int validaMes(String mes) throws MesInvalidoException{
        if(mes==null) throw new MesInvalidoException();
        
        try {
            int mesInt = Integer.parseInt(mes);
            if (!(mesInt>0 /*&& mesInt<=ValoresFixos.N_MESES*/)) throw new MesInvalidoException();
            return mesInt;
        } catch (NumberFormatException nfe){
            throw new MesInvalidoException();
        }
    }

    private int validaFilial(String filial) throws FilialInvalidaException{
        if(filial==null) throw new FilialInvalidaException();
        
        try {
            int filialInt = Integer.parseInt(filial);
            if (!(filialInt>0 /*&& filialInt<=ValoresFixos.N_FILIAIS*/)) throw new FilialInvalidaException();
            return filialInt;
        } catch (NumberFormatException nfe){
            throw new FilialInvalidaException();
        }
    }
    
    private String validaCliente(String cli, Catalogo clis) throws ClienteInvalidoException{
        if (cli == null) throw new ClienteInvalidoException();
        if (!(cli.length()==ValoresFixos.CLIENTE_TAMANHO)) throw new ClienteInvalidoException();
        if (!(clis.existe(cli))) throw new ClienteInvalidoException();
        
        for(int i=0;i<cli.length();i++){
            if ((i==0)&&(!(cli.charAt(i)>='A'&&cli.charAt(i)<='Z'))) throw new ClienteInvalidoException();
            else if ((i>0)&&(!(cli.charAt(i)>='0'&&cli.charAt(i)<='9'))) throw new ClienteInvalidoException();
	}

        return cli;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.produtos_invalidos;
        hash = 73 * hash + this.precos_invalidos;
        hash = 73 * hash + this.quantidades_invalidas;
        hash = 73 * hash + this.regimes_invalidos;
        hash = 73 * hash + this.clientes_invalidos;
        hash = 73 * hash + this.meses_invalidos;
        hash = 73 * hash + this.filiais_invalidas;
        hash = 73 * hash + this.preco_zero;
        hash = 73 * hash + this.linhas_totais;
        hash = 73 * hash + this.linhas_validas;
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
        final Vendas other = (Vendas) obj;
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
        if (this.preco_zero != other.preco_zero) {
            return false;
        }
        if (this.linhas_totais != other.linhas_totais) {
            return false;
        }
        return this.linhas_validas == other.linhas_validas;
    }

    @Override
    public String toString() {
        return "Vendas{" + "produtos_invalidos=" + produtos_invalidos + ", precos_invalidos=" + precos_invalidos + ", quantidades_invalidas=" + quantidades_invalidas + ", regimes_invalidos=" + regimes_invalidos + ", clientes_invalidos=" + clientes_invalidos + ", meses_invalidos=" + meses_invalidos + ", filiais_invalidas=" + filiais_invalidas + ", preco_zero=" + preco_zero + ", linhas_totais=" + linhas_totais + ", linhas_validas=" + linhas_validas + '}';
    }
    
}