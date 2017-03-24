
package li3_1516.Filial;

import Auxiliares_modulos.DadosFilial;
import Auxiliares_modulos.DuploQuantidadeFaturacao;
import Auxiliares_modulos.DuploVendasClientes;
import Auxiliares_modulos.TriploQuantidadeVendasFaturacao;
import Auxiliares_modulos.TuploQuantidadeVendasFaturacaoClientes;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import li3_1516.Interface.AuxiliaresInterface.Vendas.Venda;


/**
 * Gestão de filial: módulo de dados que, a partir dos ficheiros lidos, conterá as
 * estruturas de dados adequadas à representação dos relacionamentos,
 * fundamentais para a aplicação, entre produtos e clientes, ou seja, para cada
 * produto, saber quais os clientes que o compraram, quantas unidades cada um
 * comprou, em que mês e em que filial.
 * Para a estruturação optimizada dos dados deste módulo de dados será crucial
 * analisar as queries que a aplicação deverá implementar, tendo sempre em
 * atenção que pretendemos ter o histórico de vendas organizado por filiais para
 * uma melhor análise, não esquecendo que existem 3 filiais nesta cadeia. 
 * 
 * De notar que esta classe extende a classe DadosFilial, sendo que nesta relação 
 * sub-classe/super-classe, a super-classe DadosFilial representa os dados globais
 * das vendas, quantidade, faturação, etc em cada regime, para cada mês, nesta filial.
 * 
 * Estima-se que os produtos e os seus respetivos dados serão consultados/modificados
 * inúmeras vezes, por esse motivo, na escolha da estrutura a usar, restringiram-se 
 * as opções até:
 * 
 * estruturas à base de ArrayList ou à base de HashMap
 * 
 * No entanto, depois de realizarmos os testes de eficiência contidos
 * na package de testes concluímos os seguintes dados:
 * 
 * ArrayList
 * ---------
 * 
 *  Complexidade O(1) para adicionar um novo elemento (no fim da lista).
 *  Complexidade O(n) para aceder a um elemento qualquer da lista do qual não se sabe o índice.
 * 
 * HashMap
 * -------
 * 
 *  Complexidade O(1) para inserir um elemento qualquer no map.
 *  Complexidade O(1) para aceder a um elemento qualquer do map.
 * 
 * Com esta informação vemos que a melhor hipótese será a utilização da estrutura HashMap 
 * visto que, se usassemos um ArrayList, para encontrar um determinado produto ou cliente,
 * teriamos que iterar por essa List até encontrarmos o elemento que pretendemos sempre que
 * quisessemos aceder a algum. A complexidade disto poderia ser tão má quanto O(n), portanto
 * Um HashMap com o código do produto ou cliente como key e os dados do mesmo como value será 
 * em média mais eficiente, com complexidade de O(1).
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class Filial extends DadosFilial implements Serializable, Cloneable {

    /**
     * clientes.
     * Para cada cliente, os produtos que comprou e dados sobre eles.
     */
    private Map<String, Map<String,TriploQuantidadeVendasFaturacao>> clientes;
    
    /**
     * produtos.
     * para cada produtos, os clientes que o compraram e dados sobre eles.
     */
    private Map<String, ProdutoFilial> produtos;
    
    /**
     * dados mensais.
     * Mapeamento entre cada mês e um mapeamento entre os clientes e os produtos que compraram e os seus dados.
     */
    private Map<Integer, Map<String, Map<String, DuploQuantidadeFaturacao>>> dados_mensais;
    
    
    //CONSTRUTORES
    
    /**
     * 
     * Construtor vazio para Filial
     * Inicializa as estruturas e os contadores a zero.
     * 
     */
    public Filial(){
        super();
        dados_mensais = new HashMap<>();
        clientes      = new HashMap<>();
        produtos      = new HashMap<>();
    }
    
    /**
     * 
     * Construtor parameterizado para Filial
     * recebe o número incial de produtos e o número inicial
     * de clientes. Aumentando assim a eficiência e reduzindo 
     * o tempo de espera na inserção dos mesmos.
     * 
     * @param tamClientes número inicial de clientes a inserir
     * @param tamProdutos número inicial de produtos a inserir
     */
    public Filial(int tamClientes, int tamProdutos){
        super();
        dados_mensais = new HashMap<>();
        clientes      = new HashMap<>(tamClientes,1);
        produtos      = new HashMap<>(tamProdutos,1);
    }
    
    /**
     * 
     * Construtor parameterizado para Filial
     * Recebe mapeamentos entre clientes e pordutos e produtos e clientes para inserir.
     * 
     * @param prods Mapeamento entre produtos e clientes
     * @param clis  Mapeamento entre clientes e produtos
     */
    public Filial(Map<String, ProdutoFilial> prods, Map<String, Map<String,TriploQuantidadeVendasFaturacao>> clis){
        super();
        
        produtos = new HashMap<>(prods.size(), 1);
        prods.forEach((k,v) -> {
            produtos.put(k,v.clone());
        });
        
        clientes = new HashMap<>(clis.size(), 1);
        clis.forEach((k,v) -> {
            Map<String,TriploQuantidadeVendasFaturacao> aux = new HashMap<>(v.size(), 1);
            v.forEach((i,t) -> {
                aux.put(i, t.clone());
            });
            clientes.put(k, aux);
        });
        
        dados_mensais = new HashMap<>();
    }
    
    /**
     * 
     * Construtor de copia para Filial. Garante encapsulamento de dados.
     * 
     * @param f  Recebe um objecto instânciado a partir da classe Filial
     *           e apartir do mesmo instância um novo objeto dessa classe
     */
    public Filial(Filial f){
        super(f);
        produtos = f.getProdutos(); //Getters garantem encapsulamento.
        clientes = f.getClientesMap();
        dados_mensais = new HashMap<>();
    }
    
    
    //GETTERS
   
    /**
     * 
     * Permite obter um mapeamento entre os clientes inseridos na filial,
     * os produtos que comprou e dados sobre cada compra em forma de
     * TriploQuantidadeVendasFaturacao. Mantem o encapsulamento de dados.
     * 
     * @return um mapeamento entre os clientes inseridos na filial,
     *         os produtos que comprou e dados sobre cada compra em forma de
     *         TriploQuantidadeVendasFaturacao
     */
    public Map<String, Map<String,TriploQuantidadeVendasFaturacao>> getClientesMap(){
        Map<String, Map<String,TriploQuantidadeVendasFaturacao>> clis = new HashMap<>(clientes.size(), 1);
        clientes.forEach((k,v) -> {
            Map<String,TriploQuantidadeVendasFaturacao> aux = new HashMap<>(v.size(), 1);
            v.forEach((i,t) -> {
                aux.put(i, t.clone());
            });
            clis.put(k, aux);
        });
        
        return clis;
    }
    
    /**
     * 
     * Permite obter um mapeamento entre os produtos inseridos na filial,
     * os clientes que o compraram e dados mensais sobre cada uma dessas
     * compras. Mantem o encapsulamento de dados.
     * 
     * @return um mapeamento entre os produtos inseridos na filial,
     *         os clientes que o compraram e dados mensais sobre cada uma dessas
     *         compras
     */    
    public Map<String, ProdutoFilial> getProdutos(){
        Map<String, ProdutoFilial> aux = new HashMap<>(produtos.size(),1);
        
        this.produtos.forEach((k,v)-> {
            aux.put(k, v.clone());
        });
        
        return aux;
    }  

    /**
     * 
     * Permite obter o número de clientes que são compradores nesta filial.
     * 
     * @return número de clientes compradores nesta filial
     */
    public int getNumeroClientesCompradoresFilial(){
        return clientes.values().stream().mapToInt(e -> {
            if (e.isEmpty()) return 1;
            return 0;
        }).sum();
    }
    
    /**
     * 
     * Permite obter os clientes inseridos na filial, sem repetições.
     * 
     * @return clientes inseridos na filial, sem repetições
     */
    public Collection<String> getClientes(){
        return new HashSet<>(clientes.keySet());
    }
    
    /**
     * 
     * Permite obter os clientes inseridos na filial que são compradores, sem repetições.
     * 
     * @return clientes inseridos na filial que são compradores, sem repetições
     */
    public Collection<String> getClientesCompradores(){
        return clientes.entrySet().stream().filter(e -> (!(e.getValue().isEmpty()))).map(e -> e.getKey()).collect(Collectors.toList());
    }
    
    /**
     * 
     * Permite obter uma coleção dos produtos que foram comprados nesta filial.
     * 
     * @return coleção dos produtos que foram comprados nesta filial
     */
    public Collection<String> getProdutosComprados(){
        return produtos.entrySet().stream().filter(e -> (!(e.getValue().isEmpty()))).map(e -> e.getKey()).collect(Collectors.toList());
    }
    
    /**
     * 
     * Permite obter um mapeamento entre os clientes compradores e a faturação gasta 
     * pelos mesmos nesta filial.
     * 
     * @return mapeamento entre os clientes compradores e a faturação gasta 
     *         pelos mesmos nesta filial
     */
    public Map<String,Double> getCompradoresFaturacao(){
        Map<String,Double> aux = new HashMap<>(clientes.size());
        clientes.forEach((k,v)->{
            aux.put(k, v.values().stream().mapToDouble(t -> t.getFaturacao()).sum());
        });
        return aux;
    }

    /**
     * 
     * Permite obter um mapeamento entre os clientes compradores e o número
     * de produtos distintos comprados pelo mesmo.
     *
     * @return mapeamento entre os clientes compradores e o número
     *         de produtos distintos comprados pelo mesmo
     */
    public Map<String, Integer> getMapClientesNProdutosDistintos(){
        Map<String,Integer> aux = new HashMap<>(clientes.size());
        clientes.forEach((k,v) -> aux.put(k,v.size()));
        return aux;
    }
    
    /**
     * 
     * Permite obter um mapeamento entre os clientes que compraram o produto argumento
     * e a quantidade do mesmo que compraram.
     *
     * @param prod Produto sobre o qual se vai ver a quantidade comprada por cada cliente
     * @return mapeamento entre os clientes que compraram o produto argumento
     *         e a quantidade do mesmo que compraram
     */
    public Map<String, TriploQuantidadeVendasFaturacao> getMapClientesQuantidadeProduto(String prod){
        Map<String,TriploQuantidadeVendasFaturacao> aux = new HashMap<>(clientes.size());
        clientes.forEach((k,v) -> {
                    if(v.containsKey(prod)) aux.put(k, v.get(prod).clone());
                });
        return aux;
    }
    
    /**
     * 
     * Permite obter, para um determinado produto um mapeamento entre cada mês
     * e um triplo TriploQuantidadeVendasFaturacao relativo a esse mesmo mês e
     * produto que contêm a quantidade comprada, o número de vendas por clientes
     * distintos (número de clientes compradores distintos) e a faturação gerada.
     * 
     * @param prod produto sobre o qual se pretende obter o mapeamento
     * @return mapeamento entre cada mês e um triplo TriploQuantidadeVendasFaturacao 
     *         relativo a esse mesmo mês e o produto argumento que contêm a quantidade 
     *         comprada, o número de vendas por clientes distintos (número de clientes 
     *         compradores distintos) e a faturação gerada
     */
    public Map<Integer, TriploQuantidadeVendasFaturacao> getDadosMensaisProduto(String prod){
        Map<Integer, TriploQuantidadeVendasFaturacao> ret = new ConcurrentHashMap<>(dados_mensais.size());
        dados_mensais.forEach((k,v) -> {
            v.values()
             .stream()
             .forEach(i -> {
                if (i.containsKey(prod)) {
                    DuploQuantidadeFaturacao dqf = i.get(prod);
                    if (!(ret.containsKey(k)))
                         ret.put(k, new TriploQuantidadeVendasFaturacao(i.size(), dqf.getQuantidade(), dqf.getFaturacao()));
                    else ret.get(k).atualizarValores(i.size(), dqf.getQuantidade(), dqf.getFaturacao());
                }
            });
        });
        return ret;
    }
    
    /**
     * 
     * Permite obter, para um determinado cliente um mapeamento entre cada mês
     * e um triplo TriploQuantidadeVendasFaturacao relativo a esse mesmo mês e
     * cliente que contêm a quantidade comprada, o número de vendas sobre produtos
     * distintos (número de produtos comprados distintos) e a faturação gerada.
     * 
     * @param cli cliente sobre o qual se pretende obter o mapeamento
     * @return mapeamento entre cada mês e um triplo TriploQuantidadeVendasFaturacao 
     *         relativo a esse mesmo mês e ao cliente argumento que contêm a quantidade 
     *         comprada, o número de vendas sobre produtos distintos (número de produtos 
     *         comprados distintos) e a faturação gerada
     */
    public Map<Integer, TriploQuantidadeVendasFaturacao> getDadosMensaisCliente(String cli){
        Map<Integer, TriploQuantidadeVendasFaturacao> ret = new ConcurrentHashMap<>(dados_mensais.size());
        dados_mensais.forEach((k,v) -> {
            if(v.containsKey(cli)){
                v.get(cli)
                 .values()
                 .stream()
                 .forEach(i -> {
                        if (!(ret.containsKey(k)))
                             ret.put(k, new TriploQuantidadeVendasFaturacao(v.get(cli).size(), i.getQuantidade(), i.getFaturacao()));
                        else ret.get(k).atualizarValores(v.get(cli).size(), i.getQuantidade(), i.getFaturacao());                      
                });
            }
        });
        return ret;
    }
   
    /**
     * 
     * Permite obter os clientes que compraram determinado produto
     * 
     * @param prod Produto sobre o queal se pretende saber os clientes compradores
     * @return Set de clientes que compraram o produto argumento
     */
    public Set<String> getCompradoresProduto(String prod){
        return new HashSet<>(produtos.get(prod).getClientes());
    }

    /**
     * 
     * Permite obter um mapeamento entre os produtos e os dados das suas vendas.
     * Este método NÃO mantêm o encapsulamento de dados por isso deve ser usado
     * com muito cuidado e apenas se estritamente necessário.
     * 
     * @return mapeamento entre os produtos e os dados das suas vendas
     */
    public Map<String, ProdutoFilial> getMapProdutosDadosFilialSemEncapsulamento(){
        return produtos;
    }
    
    /**
     * 
     * Permite obter um mapeamento entre os produtos e os dados das suas vendas
     * mantendo o encapsulamento de dados.
     * 
     * @return mapeamento entre os produtos e os dados das suas vendas
     */
    public Map<String, ProdutoFilial> getMapProdutosDadosFilial(){
        Map<String, ProdutoFilial> m = new HashMap<>(clientes.size());
        produtos.forEach((k,v) -> m.put(k, v.clone()));
        return m;
    }
    
    /**
     * 
     * Permite obter um duplo com o número de vandas e o número de compradores
     * distintos para um determinado mês argumento.
     * 
     * @param mes Mês argumento sobre o qual se pretende obter o duplo
     * @return duplo com o número de vandas e o número de compradores
     *         distintos para um determinado mês argumento
     */
    public DuploVendasClientes getDuploVendasClientes(int mes){
        return new DuploVendasClientes(getVendas(mes),getNumCompradoresMes(mes));
    }
    
    /**
     * 
     * Permite obter o número de clientes compradores para um determinado mês.
     * 
     * @param mes Mês sobre o qual se pretende saber o número de clientes compradores
     * @return Devolve o número de clientes compradores no mês argumento
     */
    public int getNumCompradoresMes(int mes){
        if (!(dados_mensais.containsKey(mes))) return 0;
        return dados_mensais.get(mes).size();
    }
    
    /**
     * 
     * Permite obter o mapeamento entre os produtos comprados e um TriploQuantidadeVendasFaturacao
     * para o cliente argumento. Não mantêm o encapsulamento de dados por isso usar com cuidado
     * 
     * @param cli cliente sobre o qual se pretende obter o mapeamento
     * @return mapeamento entre os produtos comprados e um TriploQuantidadeVendasFaturacao
     *         para o cliente argumento
     */
    public Map<String, TriploQuantidadeVendasFaturacao> getProdutosComprados(String cli){
        if (!(clientes.containsKey(cli))) return new HashMap<>();
        return clientes.get(cli);
    }
    
    /**
     * 
     * Permite obter um tuplo com as vendas totais desta filial, a quantidade total,
     * a faturação total e o número de clientes compradores distintos para um
     * determinado mês argumento.
     * 
     * @param mes mês sobre o qual se pretende obter o tuplo
     * @return tuplo com as vendas totais desta filial, a quantidade total,
     *         a faturação total e o número de clientes compradores distintos para um
     *         determinado mês argumento
     */
    public TuploQuantidadeVendasFaturacaoClientes getTuploQuantidadeVendasFaturacaoClientes(int mes){
        TriploQuantidadeVendasFaturacao tqvf = getTriplo(mes);
        return new TuploQuantidadeVendasFaturacaoClientes(tqvf.getQuantidade(),tqvf.getVendas(),tqvf.getFaturacao(),getNumCompradoresMes(mes));
    }
    
    //SETTERS

    /**
     * Substitui o mapeamento entre os clientes inseridos na filial,
     * os produtos que comprou e dados sobre cada compra em forma de
     * TriploQuantidadeVendasFaturacao pelo mapeamento argumento. É 
     * mantido o encapsulamento de dados.
     * 
     * @param clis  Recebe o mapeamento entre os clientes inseridos na filial,
     *              os produtos que comprou e dados sobre cada compra em forma de
     *              TriploQuantidadeVendasFaturacao que se irá usar para substituir 
     *              o mapeamento presente nesta faturação.
     */
    public void setClientes(Map<String, Map<String,TriploQuantidadeVendasFaturacao>> clis){
        clientes = new HashMap<>(clis.size(), 1);
        clis.forEach((k,v) -> {
            Map<String,TriploQuantidadeVendasFaturacao> aux = new HashMap<>(v.size(), 1);
            v.forEach((i,t) -> {
                aux.put(i, t.clone());
            });
            clientes.put(k, aux);
        });
    }
    
    /**
     * Substitui o mapeamento entre os produtos inseridos na filial,
     * os clientes que o compraram e dados mensais sobre cada uma dessas
     * compras pelo mapeamento argumento. É mantido o encapsulamento de dados.
     * 
     * @param m  Recebe o mapeamento entre os produtos inseridos na filial,
     *           os clientes que o compraram e dados mensais sobre cada uma dessas
     *           compras que se irá usar para substituir o mapeamento presente nesta faturação.
     */
    public void setProdutos(Map<String, ProdutoFilial> m){
        produtos = new HashMap<>(m.size(),1);
        
        m.forEach((k,v)-> {
            produtos.put(k, v.clone());
        });
        
    }  
    
    //METODOS
    
    /**
     * 
     * Elimina tudo o que foi inserido nesta filial
     * 
     */
    public void clear(){
        this.produtos.clear();
        this.clientes.clear();
        this.dados_mensais.clear();
    }
  
    /**
     * 
     * Adiciona um cliente
     * 
     * @param c Cliente a adicionar
     */
    public synchronized void addCliente(String c){
        this.clientes.put(c,new HashMap<>());
    }
    
    /**
     * Adiciona os dados de uma venda a esta filial.
     * Se o produto não existir é inserido.
     * Se o cliente não existir é inserido.
     * Se o mês não existir é inserido
     * Caso contrário, os dados são atualizados com esta nova venda.
     * 
     * @param v Recebe uma venda que identifica os dados a inserir.
     */
    public synchronized void add(Venda v){
        String prod   = v.getProduto();
        String cli    = v.getCliente();
        int mes       = v.getMes();
        String regime = v.getRegime();
        int quant     = v.getQuantidade();
        double preco  = v.getPreco();
        Map<String,TriploQuantidadeVendasFaturacao> aux;
        
        if(!(dados_mensais.containsKey(mes))){
                    Map<String, DuploQuantidadeFaturacao> c = new HashMap<>();
                    c.put(prod, new DuploQuantidadeFaturacao(quant*preco, quant));
                    Map<String, Map<String, DuploQuantidadeFaturacao>> p = new HashMap<>();
                    p.put(cli, c);
                    dados_mensais.put(mes, p);
        }
        else {
            Map<String, Map<String, DuploQuantidadeFaturacao>> p = dados_mensais.get(mes);
            if(!(p.containsKey(cli))){
                    Map<String, DuploQuantidadeFaturacao> c = new HashMap<>();
                    c.put(prod, new DuploQuantidadeFaturacao(quant*preco, quant));
                    p.put(cli, c);
            }
            else {
                Map<String, DuploQuantidadeFaturacao> c = p.get(cli);
                if(!(c.containsKey(prod))) c.put(prod, new DuploQuantidadeFaturacao(quant*preco, quant));
                else c.get(prod).atualizarValores(quant*preco, quant);
            }
        }
        
        super.atualizarValores(regime, mes, 1, quant, quant*preco);
        
        //Map cliente, produtos que comprou
        if(!(clientes.containsKey(cli))){
            aux = new HashMap<>();
            aux.put(prod, new TriploQuantidadeVendasFaturacao(1,quant, quant*preco));
            clientes.put(cli, aux);
        }
        else {
            aux = this.clientes.get(cli);
            if(!(aux.containsKey(prod))){
                aux.put(prod, new TriploQuantidadeVendasFaturacao(1,quant, quant*preco));
            }
            else aux.get(prod).atualizarValores(1, quant, quant*preco);
        }
       
        //Map produto, clientes que compraram
        if(!(produtos.containsKey(prod))){
            ProdutoFilial pf = new ProdutoFilial();
            pf.add(cli);
            pf.atualizarValores(quant);
            produtos.put(prod, pf);
        }
        else {
            ProdutoFilial pf = this.produtos.get(prod);
            pf.add(cli);
            pf.atualizarValores(quant);
        }
    }

    
    /**
     * 
     * Adiciona um produto a esta filial
     * 
     * @param prod produto a inserir 
     */
    public synchronized void addProduto(String prod){
            produtos.put(prod, new ProdutoFilial());
    }

    
    //HASHCODE, TOSTRING, EQUALS, CLONE
    
    /**
     * Define um código hash para objetos da classe Filial
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.clientes);
        hash = 59 * hash + Objects.hashCode(this.produtos);
        hash = 59 * hash + Objects.hashCode(this.dados_mensais);
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
        final Filial other = (Filial) obj;
        if (!Objects.equals(this.clientes, other.clientes)) {
            return false;
        }
        if (!Objects.equals(this.produtos, other.produtos)) {
            return false;
        }
        return Objects.equals(this.dados_mensais, other.dados_mensais);
    }
    
    
    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return String, representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
    public String toString(){
        
      StringBuilder sb = new StringBuilder();
                    sb.append("      _____________________________FILIAL______________________________\n");  
                    sb.append("     /                                                                 \\\n");
                    sb.append("    | Número de Clientes:             ");sb.append(clientes.size());sb.append("\n");
                    sb.append("    | Número de Clientes Compradores: ");sb.append(getNumeroClientesCompradoresFilial());sb.append("\n");
                    sb.append("    | -----------------------------------------------------------------\n");
                    sb.append("    | Número de Produtos:             ");sb.append(produtos.size());sb.append("\n");
                    sb.append("    | Número de Produtos Comprados:   ");sb.append(getNumeroClientesCompradoresFilial());sb.append("\n");
                    sb.append("     \\__________________________________________________________________/\n");
                    
      return sb.toString();
    }
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado.
     * 
     * @return Filial, devolve um clone do objeto sobre o qual o método é chamado
     */ 
    @Override
    public Filial clone(){
        return new Filial(this);
    }
    
}
