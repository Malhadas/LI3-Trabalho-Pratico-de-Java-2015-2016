
package li3_1516.Catalogo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

/**
 * Classe que inclui todos os códigos de clientes ou de produtos.
 * 
 * Como não se pode permitir a existência de códigos repetidos utilizaremos,
 * para os guardar, um Set.
 * 
 * Como esta classe será maioritáriamente utilizada para inserir ou verificar
 * a existência de códigos, utilizaremos um HashSet, pois este tem complexidade
 * O(1) na inserção e O(1) na procura.
 * 
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class Catalogo implements Serializable, Cloneable{
    
    /**
     * codigos.
     * Usado um Set pois NÂO iremos tolerar códigos repetidos.
     */
    private Set<String> codigos;
    
    /*CONSTRUTORES*/
    
    //vazio
    
     /**
     * Construtor vazio para Catalogo
     */
    public Catalogo(){
       codigos = new HashSet<>(); 
    }
    
    /**
     * Construtor parameterizado para Catalogo.
     * recebe o tamanho inicial que o catálogo terá. Aumentando
     * assim a eficiência e reduzindo o tempo de espera na inserção.
     * 
     * @param tamanho recebe o tamanho inicial que o catálogo terá
     */
    public Catalogo(int tamanho){
       codigos = new HashSet<>(tamanho,1); 
    }

    /**
     * Construtor parameterizado para Catalogo
     * 
     * @param cat Recebe um Set que contenha os códigos a adicionar
     */
    public Catalogo(Set<String> cat){
        codigos = new HashSet<>(cat);
    }
    
    //copia
    
    /**
     * Construtor de cópia para Catalogo
     * 
     * @param c  Recebe um objecto instânciado a partir da classe Catalogo
     *           e apartir do mesmo instancia um novo objeto dessa classe
     *           garantindo encapsulamento de dados.
     */
    public Catalogo(Catalogo c){
        codigos = new HashSet<>(c.getCodigos());
    }
    
    
    /*GETTERS*/

    /**
     * Getter dos códigos presentes no catálogo.
     * É garantido o encapsulamento de dados.
     * 
     * @return Devolve um set com os códigos do catálogo em questão.
     */    
    public HashSet<String> getCodigos(){
        return new HashSet<>(codigos);
    }
    
    /*SETTERS*/

    /**
     * Setter dos códigos presentes no catálogo.
     * É garantido o encapsulamento de dados.
     * 
     * @param cat Recebe um Set que contenha os códigos a substituir
     */   
    public void setCodigos(Set<String> cat){
        codigos = new HashSet<>(cat);
    }
    
    /*METODOS*/
    
    /**
     * Adiciona um código ao catálogo
     * 
     * @param cod Recebe um código para adicionar ao catálogo.
     * 
     */  
    public synchronized void add(String cod){
        this.codigos.add(cod);
    }
    
    /**
     * Remove um código do catálogo
     * 
     * @param cod Recebe um código para remover do catálogo.
     * 
     */ 
    public synchronized void remove(String cod){
        this.codigos.remove(cod);
    }
    
    /**
     * Verifica a existência de um código no catálogo
     * 
     * @param cod Recebe um código para procurar no catálogo.
     * @return boolean, devolve true se o código argumento existir, false caso contrário.
     * 
     */ 
    public boolean existe(String cod){
        return this.codigos.contains(cod);
    }

    /**
     * Devolve o tamanho do catálogo. 
     * Entende-se por tamanho como sendo o número de códigos (elementos) presentes no mesmo.
     * 
     * @return int, devolve o número de códigos (elementos) presentes no catalogo.
     * 
     */ 
    public int size(){
        return this.codigos.size();
    }
    
    /**
     * Elimina o catálogo
     */
    public void clear(){
        this.codigos.clear();
    }
    
    /*EQUALS, TOSTRING, HASHCODE, CLONE*/
    
    /**
     * Define um código hash para objetos da classe Catalogo
     * com o intuito de melhorar a eficiência da inserção
     * de instâncias desta classe em Collections.
     * 
     * @return int, devolve o código hash.
     * 
     */ 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.codigos);
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
        final Catalogo other = (Catalogo) obj;
        return Objects.equals(this.codigos, other.codigos);
    }

    /**
     * Permite obter um clone do objeto sobre o qual o método é chamado, garante encapsulamento de dados.
     * 
     * @return Catalogo, devolve um clone do objeto sobre o qual o método é chamado
     *                   Garante encapsulamento de dados.
     * 
     */    
    @Override
    public Catalogo clone(){
        return new Catalogo(this);
    }

    /**
     * Permite obter uma representação do objeto em questão sobre forma de uma instância da classe String.
     * 
     * @return String, representação do objeto em questão sobre forma de uma instância da classe String
     */
    @Override
    public String toString() {
        return "Catalogo{" + "Número de Códigos=" + codigos.size() + '}';
    }
    
}
