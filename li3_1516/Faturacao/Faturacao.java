
package li3_1516.Faturacao;

import Auxiliares_modulos.DadosFilial;
import Auxiliares_modulos.TriploQuantidadeVendasFaturacao;
import li3_1516.Exceptions.ProdutoInexistenteException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;
import java.util.List;
import li3_1516.Interface.AuxiliaresInterface.ValoresFixos;
import li3_1516.Interface.AuxiliaresInterface.Vendas.Venda;

/**
 * Facturação Global: módulo de dados que irá conter as estruturas de dados
 * responsáveis pela resposta eficiente a questões quantitativas que relacionam
 * os produtos às suas vendas mensais, em modo Normal (N) ou em Promoção
 * (P), para cada um dos casos guardando o número de vendas e o valor total de
 * facturação de cada um destes tipos. Este módulo deve referenciar todos os
 * produtos, mesmo os que nunca foram vendidos.
 * Este módulo não contém qualquer referência a clientes, mas deve ser capaz de
 * distinguir os valores obtidos em cada filial; 
 * 
 * De notar que esta classe extende a classe TriploQuantidadeVendasFaturacao, sendo
 * que nesta relação sub-classe/super-classe, a super-classe TriploQuantidadeVendasFaturacao
 * representa um triplo com os dados globais da quantidade, vendas e faturação.
 * 
 * Estima-se que os produtos e os seus respetivos dados serão consultados/modificados
 * inúmeras vezes, por esse motivo, na escolha da estrutura a usar, restringiram-se 
 * as opções até:
 * 
 *   ArrayList onde cada entrada corresponde aos dados de um produto.
 *   HashMap   onde cada key corresponde ao código identificador do produto.
 *               E cada value corresponde aos dados do produto em questão.
 * 
 *   ---------------------------------------------------------------------
 * 
 * No entanto, depois de realizarmos os testes de eficiência contidos
 * na package de testes concluímos os seguintes dados:
 * 
 * ArrayList
 * ---------
 * 
 *  Complexidade O(1) para adicionar um novo elemento (no fim da lista).
 *  Complexidade O(n) para adicionar um elemento em qualquer outro local da lista.
 *  Complexidade O(n) para aceder a um elemento qualquer da lista do qual não se sabe o índice.
 * 
 * HashMap
 * -------
 * 
 *  Complexidade O(1) para inserir um elemento qualquer no map.
 *  Complexidade O(1) para aceder a um elemento qualquer do map.
 * 
 * Com esta informação vemos que a melhor hipótese será a utilização da estrutura HashMap 
 * visto que, se usassemos um ArrayList com cada entrada a ser os dados de um produto então
 * teriamos que iterar por essa List até encontrarmos o produto que pretendemos sempre que
 * quisessemos aceder a algum. A complexidade disto poderia ser tão má quanto O(n), portanto
 * Um HashMap com o código do produto como key e os dados do mesmo como value será em média
 * mais eficiente, com complexidade de O(1).
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class Faturacao extends TriploQuantidadeVendasFaturacao implements Serializable, Cloneable {
     
    
    /**
     * Mapeamento entre cada produto e um mapeamento entre cada
     * filial e os seus dados nessa filial.
     */
    private Map<String,Map<Integer,DadosFilial>> produtos;
    
    /**
     * Número de produtos não comprados.
     * 
     * serve para melhorar a eficiencia sabendo quantos 
     * elementos terá a lista dos produtos nao comprados.
     */
    private int produtos_nao_comprados = 0;
    
    
    /*CONSTRUTORES*/
    
    //vazio
    
    /**
     * 
     * Construtor vazio para Faturacao
     * 
     */
    public Faturacao(){
       super();
       produtos = new HashMap<>();        
    }


    //parametrizado
    
    /**
     * 
     * Construtor parameterizado para Faturacao
     * 
     * @param tamanho recebe o tamanho inicial (número de produtos) da faturação em questão
     */
    public Faturacao(int tamanho){
       super();
       produtos     = new HashMap<>(tamanho,1);  
    }
    
    /**
     * 
     * Construtor parametrizado para Faturacao
     * Garante encapsulamento de dados.
     * 
     * @param prods Recebe um Map que relaciona o código de produtos com os seus respetivos dados.
     * @param tqvf  Recebe um triplo com os valores globais da quantidade, vendas e faturação
     */
    public Faturacao(Map<String,Map<Integer,DadosFilial>> prods, TriploQuantidadeVendasFaturacao tqvf){
        super(tqvf);
        
        produtos = new HashMap<>(prods.size(), 1);
        
        prods.forEach((k,v) -> {
            Map<Integer,DadosFilial> aux = new HashMap<>(v.size(), 1);
            v.forEach((i,t) -> {
                aux.put(i,t.clone());
            });
            produtos.put(k, aux);
        });
    }
    
    //copia
    
    /**
     * 
     * Construtor de copia para Faturacao. Garante encapsulamento de dadoss.
     * 
     * @param f  Recebe um objecto instânciado a partir da classe Faturacao
     *           e apartir do mesmo instância um novo objeto dessa classe
     *           garantindo encapsulamento de dados.
     */
    public Faturacao(Faturacao f){
        super(f);
        produtos = f.getProdutos(); //encapsulamento é garantido por getProdutos()
    }
    

/**GETTERS**/
    
    /**
     * Obter um mapeamento entre os códigos de produtos inseridos na faturação e os seus
     * respetivos dados. É mantido o encapsulamento de dados.
     * 
     * @return Map, mapeamentee entre os códigos de produtos inseridos na faturação e os seus
     *              respetivos dados mantendo o encapsulamento de dados.   
     */    
    public Map<String, Map<Integer,DadosFilial>> getProdutos() {
        Map<String, Map<Integer,DadosFilial>> prods = new HashMap<>(produtos.size(), 1);
        
        produtos.forEach((k,v) -> {
            Map<Integer,DadosFilial> aux = new HashMap<>(v.size(), 1);
            v.forEach((i,t) -> {
                aux.put(i,t.clone());
            });
            prods.put(k, aux);
        });
        
        return prods;
    }

    /**
     * Obter dados sobre o produto de código argumento englobados por esta faturação.
     * na filial argumento.
     * É mantido o encapsulamento de dados.
     * 
     * @param  produto código do produto englobado por esta faturação cujos dados se pretendem obter
     * @param  filial  filial de onde se pretende obter dados
     * @return DadosFilial Retorna dados sobre o produto de código argumento englobado por esta faturação
     *                     na filial argumento
     * @throws ProdutoInexistenteException esta exceção é lançada se o código de produto argumento não 
     *                                     estiver inserido na faturação
     */
    public DadosFilial getDadosProduto(String produto, int filial) throws ProdutoInexistenteException{
        if (!(this.produtos.containsKey(produto))) throw new ProdutoInexistenteException();
        
        return this.produtos.get(produto).get(filial).clone();
    }
   
    
    /**SETTERS**/
    
    /**
     * Substitui o mapeamento entre os códigos de produtos inseridos na faturação e os seus
     * respetivos dados. É mantido o encapsulamento de dados.
     * 
     * @param prods  Recebe o mapeamento entre os códigos de produtos e os seus respetivos dados 
     *            que se irá usar para substituir o mapeamento presente nesta faturação.
     */
    public void setProdutos(Map<String, Map<Integer,DadosFilial>> prods) {
        produtos = new HashMap<>(prods.size(), 1);
        
        prods.forEach((k,v) -> {
            Map<Integer,DadosFilial> aux = new HashMap<>(v.size(), 1);
            v.forEach((i,t) -> {
                aux.put(i,t.clone());
            });
            produtos.put(k, aux);
        });
    }

    
    /*METODOS*/
    
    /**
     * Adiciona um código de produto á faturação. como apenas o código é entregue em argumento,
     * todos os contadores dos seus respetivos dados (quantidade vendida, vendas, etc...) são
     * inicializados a zero.
     * 
     * @param produto Recebe uma String que identifica o produto a inserir.
     */
    public synchronized void add(String produto){
        this.produtos_nao_comprados++;
        produtos.put(produto, new HashMap<>(ValoresFixos.N_FILIAIS,1));
    }
    
    /**
     * Remove um produto e todos os seus respetivos dados (quantidade evendida, vendas, etc...)
     * da faturação em questão.
     * 
     * @param produto Recebe uma String de um código que identifica o produto a remover.
     * 
     * @throws ProdutoInexistenteException se se verificar que o produto com o código argumento não
     *                                     se encontra inserido nesta faturação.
     */
    public synchronized void remove(String produto) throws ProdutoInexistenteException{
        if (!(produtos.containsKey(produto))) throw new ProdutoInexistenteException();
        produtos.remove(produto);
    }
    
    /**
     * Adiciona os dados de uma venda á faturação.
     * Se o produto não existir é inserido.
     * Se o mês não existir é inserido
     * Se dados da filial em questão não existirem então a filial é inserida.
     * Caso contrário, os dados são atualizados com esta nova venda.
     * 
     * @param v Recebe uma venda que identifica os dados a inserir.
     */
    public synchronized void add(Venda v){
        String prod  = v.getProduto();
        int quant    = v.getQuantidade();
        double preco = v.getPreco();
        int filial   = v.getFilial();
        
        Map<Integer,DadosFilial> aux = this.produtos.get(prod);
        
        super.atualizarValores(1, quant, quant*preco);
        
        if (aux.isEmpty())
            this.produtos_nao_comprados--; //Nunca antes tinha sido comprado 
        
        if(!(aux.containsKey(filial))){
            aux.put(filial, new DadosFilial(v.getRegime(), v.getMes(), 1, quant, quant*preco));
        }
        else aux.get(filial).atualizarValores(v.getRegime(), v.getMes(), 1, quant, quant*preco);    
    }
    
    /**
     * 
     * Permite obter o número de vendas global de um determinado produto
     * 
     * @param prod Produto sobre o qual se quer saber o número de vendas
     * @return O número de vendas global do produto argumento
     */
    public int getVendas(String prod){
        return this.produtos.get(prod).values().stream().mapToInt(DadosFilial::getVendas).sum();
    }
    
    /**
     * 
     * Permite saber se um produto foi ou não comprado.
     * 
     * @param  prod Produto que se pretende saber se foi ou não comprado.
     * @return true se nunca foi comprado, false caso contrário
     */
    public boolean naoComprado(String prod){
        return this.produtos.get(prod).isEmpty();
    }
    
    public boolean naoCompradoStream(String prod){
        return (this.produtos.get(prod).values().stream().mapToInt(DadosFilial::getVendas).sum() == 0);
    }
    
    private boolean naoComprado(Map<Integer,DadosFilial> a){
        return a.isEmpty();
    }
    
    private boolean naoCompradoStream(Map<Integer,DadosFilial> a){
        return (a.values().stream().mapToInt(DadosFilial::getVendas).sum() == 0);
    }
    
    private int getVendas(Map<Integer,DadosFilial> a){
        return a.values().stream().mapToInt(DadosFilial::getVendas).sum();
    }
    
    /**
     * Remove uma venda de um produto produto e todos os seus respetivos dados (quantidade evendida, vendas, etc...)
     * da faturação em questão. Resumindo, retrocede o que foi feito ao inserir esta venda. Assume-se que a venda
     * foi realmente inserida, caso não tenha sido pode lançar NullPointerException. Os valores nunca descem
     * abixo de zero, sendo sempre truncados a zero.
     * 
     * @param v Recebe uma Venda que identifica os dados inseridos a remover.
     */
    public synchronized void remove(Venda v){
        String prod  = v.getProduto();
        int quant    = v.getQuantidade();
        double preco = v.getPreco();
        int filial   = v.getFilial();
        
        Map<Integer,DadosFilial> aux = this.produtos.get(prod);
        
        super.atualizarValores(-1, (-1)*quant, (-1)*quant*preco);
        
        aux.get(filial).atualizarValores(v.getRegime(), v.getMes(), -1, (-1)*quant, (-1)*quant*preco);    
    
        if(aux.get(filial).getVendas()==0) aux.remove(filial);
        
        if (aux.isEmpty())
            this.produtos_nao_comprados++;
    }

    
    /**
     * 
     * Permite obter uma cloeção de todos os códigos de produtos nunca comprados
     * globalmente ordenados por ordem lexicográfica.
     * 
     * @return coleção dos códigos nunca comprados globalmente
     */
    public Collection<String> getNaoComprados(){
        List<String> aux = new ArrayList<>(this.produtos_nao_comprados);
        
        produtos.forEach((k,v) -> {
            if(naoComprado(v)) aux.add(k);
        });
        
        aux.sort((produto, outroProduto) -> produto.compareTo(outroProduto));
        
        return aux;
    }
    
    /**
     * Elimina a faturação e reinicializa os contadores a zero.
     */
    public void clear(){
        super.atualizarValores((-1)*this.getVendas(), (-1)*this.getQuantidade(), (-1)*this.getFaturacao());
        this.produtos.clear();
        this.produtos_nao_comprados = 0;
    }
    
    /**
     * Permite obter o número de produtos que nunca foram comprados.
     * 
     * @return o número de produtos nunca comprados 
     */
    public int getNumNaoComprados(){
        return produtos_nao_comprados;
    }
    
    
    //HASHCODE, TOSTRING, EQUALS, CLONE
    
    /**
     * Define um código hash para objetos da classe Faturacao
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + super.hashCode();
        hash = 89 * hash + Objects.hashCode(this.produtos);
        hash = 89 * hash + Objects.hashCode(this.produtos_nao_comprados);
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
        final Faturacao other = (Faturacao) obj;
        if (!Objects.equals(this.produtos, other.produtos)) {
            return false;
        }
        return super.equals(obj);
    }
    
    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return String, representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
    public String toString() {
        return super.toString()+"\nFaturacao{" + "Número de produtos produtos=" + produtos.size() 
                                               + ", Número de produtos não comprados=" + produtos_nao_comprados 
                               + '}';
    }
   
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado.
     * 
     * @return Faturacao, devolve um clone do objeto sobre o qual o método é chamado
     */    
    @Override
    public Faturacao clone(){
        return new Faturacao(this);
    }
}
