
package Auxiliares_modulos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import li3_1516.Interface.AuxiliaresInterface.ValoresFixos;

/**
 * Classe que inclui dados totais e para cada mês da quantidade, faturação, vendas e número
 * de clientes compradores distintos.
 * 
 * Este tuplo quantidade, faturação, vendas e número de clientes compradores distintos torna-se
 * possível com recurso á classe deste mesmo package: TuploQuantidadeVendasFaturacaoClientes
 * 
 * Esta classe pode ser serializada, assim como pode ser colonada, por essas razões
 * implementa as seguintes interfaces: Serializble e Cloneable.
 * 
 * Esta classe é usada neste projeto para entregar informação ao Main para posterior visualização
 * por parte do utilizador.
 * 
 * Estima-se que os valores contidos nos conjuntos de meses serão consultados/modificados
 * inúmeras vezes, por esse motivo, na escolha da estrutura a usar, restringiram-se 
 * as opções até:
 * 
 *   ArrayList onde cada entrada corresponde aos dados de um mês.
 * 
 *   TreeMap ou HashMap  onde cada key corresponde ao mês.
 * e cada value aos dados do mês.
 * 
 * Como pretendemos que haja a possibilidade de os meses crescerem indefinidamente, tomamos a opção de 
 * estruturar estes dados com recurso à interface Map. Com um Map podemos tornar a nossa aplicação 
 * escalável ao ponto de poserem ser adicionados (sem nenhum trabalho extra de parte do programador) 
 * meses diferentes daqueles que se encontram no input atual. Tornamos assim o nosso projeto escalável 
 * o que não seria tão verdade se recorressemos ao uso de arrays, por exemplo. O Map tem mais uma vantagem, 
 * permite a inserção de valores não "contínuos" isto é, podemos inserir o mês 3 antes do 2 ou até o 20 se 
 * necessário, não precisamos portanto de nenhuma ordem específica no input e além disso, podemos até ter 
 * dados sobre o mês 3 sem nunca termos ouvido falar por exemplo do mês 1. Com um arrayList para conseguirmos o
 * mesmo efeito teriamos de encher os espaços entre os mêses que estão presentes no input com dados a zero, dados
 * que nunca seriam mudados e cujo propósito é somente gastar memória e tempo aquando da inserção.
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
 * pois iremos maioritáriamente querer aceder e modificar valores. concluimos então que, 
 * com um HashMap, todas as operações que este projeto irá realizar sobre esta estrutura 
 * terão Complexidade O(1) o que é objetivamente melhor do que o O(logn) que obteriamos com
 * a estrutura TreeMap.
 * 
 * 
 * @author  Grupo 80
 * @version 2016
 */
public final class DadosGlobaisMeses implements Serializable, Cloneable{
    
    /**
     * Mapeamento entre os meses e os dados relativos aos mesmos, suporta
     * um número indefinido de meses, tornando-se assim, fácilmente escalável
     */
    private final Map<Integer,TuploQuantidadeVendasFaturacaoClientes> meses;
    
    /**
     * Total de todos os meses
     */
    private final TuploQuantidadeVendasFaturacaoClientes total;
    
    
    //CONSTRUTORES
    
    /**
     * Construtor vazio para DadosGlobaisMensais
     * Inicializa as estruturas.
     */
    public DadosGlobaisMeses(){
        meses = new HashMap<>(ValoresFixos.N_MESES,1);
        total = new TuploQuantidadeVendasFaturacaoClientes();
    }
    
    
    /**
     * 
     * Construtor parameterizado para DadosGlobaisMeses.
     * Insere os dados presentes no mapeamento recebido como argumento.
     * Mantêm o encapsulamento de dados.
     * 
     * @param a Recebe mapeamento entre os mêses e os seus dados
     */
    public DadosGlobaisMeses(Map<Integer, TuploQuantidadeVendasFaturacaoClientes> a){
        meses = new HashMap<>(a.size(),1);
        total = new TuploQuantidadeVendasFaturacaoClientes();
        a.forEach((k,v) -> {
            add(v.clone(),k);
        });
    }
    
    /**
     * Construtor de cópia para DadosGlobaisMeses
     * 
     * @param dgm, Recebe um objecto instânciado a partir da classe DadosGlobaisMeses
     *             e apartir do mesmo instancia um novo objeto dessa classe garantindo 
     *             encapsulamento de dados.
     */
    public DadosGlobaisMeses(DadosGlobaisMeses dgm){
        meses = dgm.getMeses();
        total = dgm.getTotal();
    }
    
    //GETTERS
    
    /**
     * 
     * Permite obter um tuplo com a quantidade, número de vendas
     * faturação e número de clientes compradores distintos relativo
     * a todos os meses (total).
     * 
     * @return Tuplo com a quantidade, número de vendas faturação e 
     *         número de clientes compradores distintos relativo a 
     *         todos os meses (total)
     */
    public TuploQuantidadeVendasFaturacaoClientes getTotal(){
        return total.clone();
    }
    
    /**
     * 
     * Permite obter um mapeamento entre os mêses e um tuplo com a quantidade, número de vendas
     * faturação e número de clientes compradores distintos relativo a esse mês.
     * 
     * @return mapeamento entre os mêses e um tuplo com a quantidade, número de vendas
     *         faturação e número de clientes compradores distintos relativo a esse mês
     */
    public Map<Integer,TuploQuantidadeVendasFaturacaoClientes> getMeses(){
        Map<Integer,TuploQuantidadeVendasFaturacaoClientes> a = new HashMap<>(meses.size(),1);
        meses.forEach((k,v) -> {
            a.put(k,v.clone());
        });
        return a;
    }
    
    /**
     * 
     * Permite obter a quantidade relativa a um determinado
     * mês argumento.
     * 
     * @param mes mês sobre o qual se pretende saber a quantidade
     * @return devolve a quantidade no mês argumento
     */
    public int getQuantidadeMes(int mes){
        return meses.get(mes).getQuantidade();
    }
    
    /**
     * 
     * Permite obter a faturação relativa a um determinado
     * mês argumento.
     * 
     * @param mes mês sobre o qual se pretende saber a faturação
     * @return devolve a faturação no mês argumento
     */
    public double getFaturacaoMes(int mes){
        return meses.get(mes).getFaturacao();
    }
        
    /**
     * 
     * Permite obter o número de clientes compradores distintos relativo 
     * a um determinado mês argumento.
     * 
     * @param mes mês sobre o qual se pretende saber o número de clientes compradores distintos
     * @return devolve o número de clientes compradores distintos no mês argumento
     */
    public int getClientesMes(int mes){
        return meses.get(mes).getClientes();
    }
    
    /**
     * 
     * Permite obter o número de vendas relativo a um determinado
     * mês argumento.
     * 
     * @param mes mês sobre o qual se pretende saber o número de vendas
     * @return devolve o número de vendas no mês argumento
     */
    public int getVendasMes(int mes){
        return meses.get(mes).getVendas();
    }
    
    /**
     * 
     * Permite obter a quantidade total.
     * 
     * @return a quantidade total 
     */
    public int getQuantidadeTotal(){
        return total.getQuantidade();
    }
    
    /**
     * 
     * Permite obter a faturação total.
     * 
     * @return a faturação total 
     */
    public double getFaturacaoTotal(){
        return total.getFaturacao();
    }

    /**
     * 
     * Permite obter o número de clientes compradores distintos total.
     * 
     * @return o número de clientes compradores distintos total 
     */
    public int getClientesTotal(){
        return total.getClientes();
    }
    
    /**
     * 
     * Permite obter o número de vendas total.
     * 
     * @return o número de vendas total 
     */
    public int getVendasTotal(){
        return total.getVendas();
    }
    
    //Métodos
    
    /**
     * 
     * Permite atualizar valores para um determinado mês.
     * 
     */
    public void add(TuploQuantidadeVendasFaturacaoClientes tqvfc, int mes){
        if(meses.containsKey(mes)) meses.get(mes).atualizarValores(tqvfc);
        else                       meses.put(mes, tqvfc.clone());
        total.atualizarValores(tqvfc);
    }
    
    //HASHCODE, EQUALS, TOSTRING, CLONE

    /**
     * Define um código hash para objetos da classe DadosGlobaisMeses
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.meses);
        hash = 43 * hash + Objects.hashCode(this.total);
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
        final DadosGlobaisMeses other = (DadosGlobaisMeses) obj;
        if (!Objects.equals(this.meses, other.meses)) {
            return false;
        }
        return Objects.equals(this.total, other.total);
    }

    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return String, representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
    public String toString() {
        return "DadosGlobaisMeses{" + "meses=" + meses + ", total=" + total + '}';
    }
    
    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado, garante encapsulamento de dados.
     * 
     * @return DadosGlobaisMeses, devolve um clone do objeto sobre o qual o método é chamado
     * 
     */  
    @Override
    public DadosGlobaisMeses clone(){
        return new DadosGlobaisMeses(this);
    }
    
}
