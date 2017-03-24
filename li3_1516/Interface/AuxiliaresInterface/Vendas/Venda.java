package li3_1516.Interface.AuxiliaresInterface.Vendas;


import java.io.Serializable;
import java.util.Objects;




/**
 * Classe wrapper para uma venda obtida através do ficheiro de vendas
 * 
 * @author  Grupo 80
 * @version 2016
 */
public final class Venda implements Serializable, Cloneable{
    // variáveis de instância 
    private final String produto;
    private final String cliente;
    private final int    mes;
    private final int    filial;
    private final int    quantidade;
    private final double preco;
    private final String regime;
    
    /**CONSTRUTORES*************************************************************************************/
    
    /**
     * Construtor vazio para objetos da classe Venda
     */
    public Venda(){
       // inicializa variáveis de instância
       this.produto    = "";
       this.cliente    = "";
       this.mes        = 0;
       this.filial     = 0;
       this.quantidade = 0;
       this.preco      = 0.0;
       this.regime     = "_";
    }

    /**
     * Construtor de cópia para objetos da classe Venda
     * @param v
     */
    public Venda(Venda v){
       // inicializa variáveis de instância
       this.produto    = v.getProduto();
       this.cliente    = v.getCliente();
       this.mes        = v.getMes();
       this.filial     = v.getFilial();
       this.quantidade = v.getQuantidade();
       this.preco      = v.getPreco();
       this.regime     = v.getRegime();
    }

    /**
     * Construtor parameterizado para objetos da classe Venda
     * @param produto
     * @param cliente
     * @param mes
     * @param filial
     * @param quantidade
     * @param preco
     */
    public Venda(String produto, String cliente, int mes, int filial, int quantidade, double preco, String regime){
       // inicializa variáveis de instância
       this.produto    = produto;
       this.cliente    = cliente;
       this.mes        = mes;
       this.filial     = filial;
       this.quantidade = quantidade;
       this.preco      = preco;
       this.regime     = regime;
    }
    
    /***************************************************************************************************/
    
    /**GETTERS******************************************************************************************/
    
    /**
     * getProduto
     * 
     * @return o produto
     */
    public String getProduto(){
        return this.produto;
    }

    /**
     * getCliente
     * 
     * @return o cliente
     */
    public String getCliente(){
        return this.cliente;
    }
    
    /**
     * getMes
     * 
     * @return o mes
     */
    public int getMes(){
        return this.mes;
    }    

    /**
     * getFilial
     * 
     * @return a filial
     */
    public int getFilial(){
        return this.filial;
    }    
    
    /**
     * getQuantidade
     * 
     * @return a quantidade
     */
    public int getQuantidade(){
        return this.quantidade;
    }    
    
    /**
     * getPreco
     * 
     * @return o preco
     */
    public double getPreco(){
        return this.preco;
    }    
    
    /**
     * getRegime
     * 
     * @return o regime
     */
    public String getRegime(){
        return this.regime;
    }
    
    /***************************************************************************************************/
    
    /**MÉTODOS******************************************************************************************/
    
    /**
     * Verifica se o Objeto sobre o qual o método é chamado e o objeto argumento
     * são iguais sobre os seguintes critérios:
     *                           ou são exatamente o mesmo objeto.
     *                           ou têm conteúdo igual.
     * 
     * @param o Recebe o objeto com o qual se irá verifica a igualdade.
     * @return boolean, devolve true se o objeto argumento for igual ao objeto onde é chamado
     *                  devolve false no caso contrário.
     *
     */ 
    @Override
    public boolean equals (Object o){
        if(this==o) return true;
        if(this==null || o==null || this.getClass()!=o.getClass()) return false;
        
        Venda v = (Venda) o;
        return this.produto.equals(v.getProduto())  &&
               this.cliente.equals(v.getCliente())  &&
               this.mes        == v.getMes()        &&
               this.quantidade == v.getQuantidade() &&
               this.preco      == v.getPreco()      &&
               this.filial     == v.getFilial();
    }

    /**
     * Define um código hash para objetos da classe Venda
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.produto);
        hash = 67 * hash + Objects.hashCode(this.cliente);
        hash = 67 * hash + this.mes;
        hash = 67 * hash + this.filial;
        hash = 67 * hash + this.quantidade;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.preco) ^ (Double.doubleToLongBits(this.preco) >>> 32));
        return hash;
    }
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado.
     * 
     * @return Venda, devolve um clone do objeto sobre o qual o método é chamado
     */ 
    @Override
    public Venda clone(){
        return new Venda(this);
    }

    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
   public String toString(){
       StringBuilder s = new StringBuilder();
                s.append("\t  __________VENDA__________\n");
                s.append("\t /                         \\\n");
                s.append("\t| Produto    ").append(this.produto).append("\n");
                s.append("\t| Cliente    ").append(this.cliente).append("\n");
                s.append("\t| Mês        ").append(this.mes).append("\n");
                s.append("\t| Quantidade ").append(this.quantidade).append("\n");
                s.append("\t| Preco      ").append(this.preco).append("\n");
                s.append("\t \\_________________________/");
        
        return s.toString();
   }
    
   /************************************************************************************/
   
}
