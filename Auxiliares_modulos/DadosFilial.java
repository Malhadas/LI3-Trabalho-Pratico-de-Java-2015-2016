
package Auxiliares_modulos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import li3_1516.Interface.AuxiliaresInterface.ValoresFixos;

/**
 * Classe que inclui dados das vendas, quantidade vendida e faturação em cada regime relativamente a um conjunto de
 * conjuntos de meses numa determinada filial.
 * 
 * Esta classe pode ser serializada, assim como pode ser colonada, por essas razões
 * implementa as seguintes interfaces: Serializble e Cloneable.
 * 
 * Estima-se que os valores contidos nos conjuntos de meses serão consultados/modificados
 * inúmeras vezes, por esse motivo, na escolha da estrutura a usar, restringiram-se 
 * as opções até:
 * 
 *   ArrayList onde cada entrada corresponde aos dados de um regime.
 * 
 *  TreeMap ou HashMap  onde cada key corresponde ao caracter identificador do regime.
 * e cada value aos dados do regime.
 * 
 * Como pretendemos que haja a possibilidade de tanto os regimes, como os meses, etc possam crescer
 * indefinidamente, tomamos a opção de estruturar estes dados com recurso à interface Map.
 * Com um Map podemos tornar a nossa aplicação escalável ao ponto de poserem ser adicionados (sem nenhum
 * trabalho extra de parte do programador) regimes, meses, filiais, diferentes daqueles(as) que se
 * encontram no input atual. Tornamos assim o nosso projeto escalável o que não seria tão verdade se
 * recorressemos ao uso de arrays, por exemplo. O Map tem mais uma vantagem, permite a inserção de
 * valores não "contínuos" isto é, podemos inserir a filial 3 antes da 2 ou até a 20 se necessário,
 * não precisamos portanto de nenhuma ordem específica no input e além disso, podemos até ter dados sobre
 * a filial 3 sem nunca termos ouvido falar por exemplo da filial 1. Com um arrayList para conseguirmos o
 * mesmo efeito teriamos de encher os espaços entre as filiais que estão presentes no input com dados
 * a zero, dados que nunca seriam mudados e cujo propósito é somente gastar memória e tempo aquando da inserção.
 * 
 * Surgiu então a dúvida se deveriamos usar um TreeMap ou um HashMap
 * na package de testes concluímos os seguintes dados:
 * 
 * HashMap
 * ---------
 * 
 *  Complexidade O(1) para adicionar um novo elemento.
 *  Complexidade O(1) para aceder a um elemento qualquer da lista.
 * 
 * TreeMap
 * -------
 * 
 *  Complexidade O(logn) para inserir um elemento qualquer no map.
 *  Complexidade O(logn) para aceder a um elemento qualquer do map.
 * 
 * Com esta informação vemos que a melhor hipótese será a utilização da estrutura HashMap
 * pois iremos maioritáriamente querer aceder e modificar valores (situações que vão 
 * acontecer na inserção dos valores do ficheiro de texto das vendas), daí para a frente o 
 * projeto apenas irá aceder a valores. concluimos então que, com um HashMap, todas as 
 * operações que este projeto irá realizar sobre esta estrutura terão Complexidade O(1) o 
 * que é objetivamente melhor do que o O(logn) que obteriamos com
 * a estrutura TreeMap.
 * 
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class DadosFilial implements Serializable, Cloneable{
    
    /**
     * mapeamento entre a String do regime e um mapeamento
     * entre o mês e um triplo que idica a quantidade comprada, número de compras
     * e faturação.
     */
    private Map<String,Map<Integer,TriploQuantidadeVendasFaturacao>> dados;  
        
    
    //CONSTRUTORES
    
    /**
     * Construtor vazio para DadosFilial.
     * Inicializa as estruturas.
     */
    public DadosFilial(){
        dados  = new HashMap<>(ValoresFixos.N_REGIMES,1);
    }
   
    /**
     * Construtor parameterizado para DadosFilial
     * 
     * @param a, Recebe um mapeamento entre a String do regime e um mapeamento
     * entre o mês e um triplo que idica a quantidade comprada, número de compras
     * e faturação.
     */
    public DadosFilial(Map<String, Map<Integer,TriploQuantidadeVendasFaturacao>> a){
        dados = new HashMap<>(a.size(),1);
        a.forEach((k,v)->{
            Map<Integer, TriploQuantidadeVendasFaturacao> mt = new HashMap<>(v.size(),1);
            v.forEach((i,t) -> {
                mt.put(i, t.clone());
            });
            dados.put(k, mt);
        });
    }
    
    /**
     * Construtor parameterizado para DadosFilial, recebe já dados
     * relativos a uma venda e insere-os de imediato.
     * 
     * @param regime     Regime onde se pretende inserir informação
     * @param mes        Mês onde se pretende inserir informação
     * @param vendas     Número de vendas que se pretende inserir
     * @param quantidade Quantidade que se pretende inserir
     * @param faturacao  Faturacao que se pretende inserir
     */
    public DadosFilial(String regime, int mes, int vendas, int quantidade, double faturacao){
        dados  = new HashMap<>(ValoresFixos.N_REGIMES,1);
        Map<Integer, TriploQuantidadeVendasFaturacao> meses  = new HashMap<>(ValoresFixos.N_MESES,1);
        meses.put(mes, new TriploQuantidadeVendasFaturacao(vendas,quantidade,faturacao));
        dados.put(regime, meses);
    }
    
    /**
     * Construtor de cópia para DadosFilial
     * 
     * @param df Recebe um objecto instânciado a partir da classe DadosFilial
     * e apartir do mesmo instância um novo objeto dessa classe garantindo 
     * encapsulamento de dados.
     */
    public DadosFilial(DadosFilial df){
        dados = df.getDados();
    }
    
    //GETTERS
    
    /**
     * Método utilizado para obter os dados desta filial em específico.
     * 
     * @return Devolve um mapeamento entre a String do regime e um mapeamento
     * entre o mês e um triplo que idica a quantidade comprada, número de compras
     * e faturação mantendo o encapsulamento de dados.
     */
    public Map<String,Map<Integer,TriploQuantidadeVendasFaturacao>> getDados(){
        Map<String,Map<Integer,TriploQuantidadeVendasFaturacao>> a = new HashMap<>(dados.size(),1);
        dados.forEach((k,v)->{
            Map<Integer, TriploQuantidadeVendasFaturacao> mt = new HashMap<>(v.size(),1);
            v.forEach((i,t) -> {
                mt.put(i, t.clone());
            });
            a.put(k, mt);
        });
        return a;
    }
    
    /**
     * 
     * Permite obter um triplo com a quantidade, vendas e faturação
     * relativos a um determinado mês argumento.
     * 
     * @param mes, recebe mês sobre o qual se pretende obter dados
     * @return devolve um triplo com a quantidade, vendas e faturação
     */
    public TriploQuantidadeVendasFaturacao getTriplo(int mes){
        final TriploQuantidadeVendasFaturacao tqvf = new TriploQuantidadeVendasFaturacao();
        
        dados.values().stream().forEach(t -> {
            if(t.containsKey(mes)){
                TriploQuantidadeVendasFaturacao aux = t.get(mes);
                tqvf.atualizarValores(aux.getVendas(), aux.getQuantidade(), aux.getFaturacao());
            }      
        });
        
        return tqvf;
    }
    
    
    /**
     * 
     * Permite obter o número de vendas total nesta filial.
     * 
     * @return Número de vendas total nesta filial 
     */
    public int getVendas(){
        int i, ret;
        for(i=1, ret = 0; i<=ValoresFixos.N_MESES; i++) ret+=getVendas(i);
        return ret;
    }
    
    /**
     * 
     * Permite obter a faturação total nesta filial.
     * 
     * @return Faturação total nesta filial
     */
    public double getFaturacao(){
        int i;
        double ret;
        for(i=1, ret = 0; i<=ValoresFixos.N_MESES; i++) ret+=getFaturacao(i);
        return ret;
    }
    
    /**
     * 
     * Permite obter a quantidade total nesta filial.
     * 
     * @return Quantidade total nesta filial
     */
    public int getQuantidade(){
        int i, ret;
        for(i=1, ret = 0; i<=ValoresFixos.N_MESES; i++) ret+=getQuantidade(i);
        return ret;
    }
    
    /**
     * 
     * Permite obter o número de vendas total para um determinado mês desta filial.
     * Devolve 0 se o mês não estiver inserido.
     * 
     * @param mes recebe o mês onde se pretende saber o número de vendas
     * @return Número de vendas no mês argumento.
     */
    public int getVendas(int mes){
        return dados.values().stream().mapToInt(t -> {
            if(!(t.containsKey(mes))) return 0;
            else return t.get(mes).getVendas();
        }).sum();
    }
    
    /**
     * 
     * Permite obter a faturação total para um determinado mês desta filial.
     * Devolve 0 se o mês não estiver inserido.
     * 
     * @param mes recebe o mês onde se pretende saber a faturação
     * @return Faturação no mês argumento.
     */
    public double getFaturacao(int mes){
        return dados.values().stream().mapToDouble(t -> {
            if(!(t.containsKey(mes))) return 0.0;
            else return t.get(mes).getFaturacao();
        }).sum();
    }

    /**
     * 
     * Permite obter a quantidade total para um determinado mês desta filial.
     * Devolve 0 se o mês não estiver inserido.
     * 
     * @param mes recebe o mês onde se pretende saber a quantidade
     * @return Quantidade no mês argumento.
     */    
    public int getQuantidade(int mes){
        return dados.values().stream().mapToInt(t -> {
            if(!(t.containsKey(mes))) return 0;
            else return t.get(mes).getQuantidade();
        }).sum();
    }
    
    /**
     * 
     * Permite obter as vendas para total para um determinado regime desta filial.
     * 
     * @param regime recebe o regime onde se pretende saber as vendas
     * @return Vendas no regime argumento
     */
    public int getVendas(String regime){
        return  dados.get(regime).values().stream().mapToInt(TriploQuantidadeVendasFaturacao::getVendas).sum();
    }
    
    private int getVendas(Map<Integer,TriploQuantidadeVendasFaturacao> a){
        return  a.values().stream().mapToInt(TriploQuantidadeVendasFaturacao::getVendas).sum();
    }


    /**SETTERS**/    
    
    /**
     * 
     * Substitui o mapeamento dos regimes que esta filial engloba com os seus dados.
     * É mantido o encapsulamento de dados.
     * 
     * @param a, Recebe o mapeamento dos regimes que irá substituir o mapeamento que 
     *            esta filial engloba sendo mantido o encapsulamento de dados.
     */
    public void setDados(Map<String, Map<Integer,TriploQuantidadeVendasFaturacao>> a){
        dados = new HashMap<>(a.size(),1);
        a.forEach((k,v)->{
            Map<Integer, TriploQuantidadeVendasFaturacao> mt = new HashMap<>(v.size(),1);
            v.forEach((i,t) -> {
                mt.put(i, t.clone());
            });
            dados.put(k, mt);
        });
    }
    
    
    /**METODOS**/
    
    /**
     * 
     * Permite atualizar os valores inseridos nos dados desta filial.
     * 
     * @param regime     Regime que se pretende atualizar, se não estiver ainda inserido
     *                   então será atualizado
     * @param mes        Mês que se pretende atualizar, se não estiver ainda inserido então
     *                   será atualizado
     * @param vendas     vendas a adicionar, deverá ser negativa se se pretende subtrair 
     *                   ou positiva se se pretende adicionar
     * @param quantidade quantidade a atualizar, deverá ser negativa se se pretende subtrair 
     *                   ou positiva se se pretende adicionar
     * @param faturacao  faturação a adicionar, deverá ser negativa se se pretende subtrair 
     *                   ou positiva se se pretende adicionar
     */
    public void atualizarValores(String regime, int mes, int vendas, int quantidade, double faturacao){
        if (!(dados.containsKey(regime))){
            Map<Integer, TriploQuantidadeVendasFaturacao> meses  = new HashMap<>(ValoresFixos.N_MESES,1);
            meses.put(mes, new TriploQuantidadeVendasFaturacao(vendas,quantidade,faturacao));
            dados.put(regime, meses);
        }
        else {
            Map<Integer,TriploQuantidadeVendasFaturacao> aux = dados.get(regime);
            if(!(aux.containsKey(mes))){
                aux.put(mes, new TriploQuantidadeVendasFaturacao(vendas, quantidade, faturacao));
            }
            else aux.get(mes).atualizarValores(vendas, quantidade, faturacao);
        }
    }
    
    /*EQUALS, CLONE, HASHCODE, TOSTRING*/

    /**
     * Define um código hash para objetos da classe DadosFilial
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + super.hashCode();
        hash = 13 * hash + Objects.hashCode(this.dados);
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
        if (this==obj) return true;
        if(this==null || obj==null || this.getClass()!=obj.getClass()) return false;
        final DadosFilial other = (DadosFilial) obj;
        return (Objects.equals(this.dados, other.dados));
    }

    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return String, representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
    public String toString() {
        return super.toString()+"\nDadosFilial{" + "regimes=" + dados + '}';
    }


    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado, garante encapsulamento de dados.
     * 
     * @return DadosFilial, devolve um clone do objeto sobre o qual o método é chamado
     * 
     */  
    @Override
    public DadosFilial clone(){
        return new DadosFilial(this);
    }
    
}