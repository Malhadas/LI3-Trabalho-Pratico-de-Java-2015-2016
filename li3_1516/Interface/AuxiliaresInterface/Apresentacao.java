
package li3_1516.Interface.AuxiliaresInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import li3_1516.Exceptions.PaginaInexistenteException;

/**
 * Guarda uma Colecção de strings numa lista paginada para posterior apresentação
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class Apresentacao implements Cloneable, Serializable{
    
    private List<List<String>> paginas;//Lista paginada
    
    private final int total;
    private int pagina_atual;
    
    /**CONSTRUTORES**/
    
    //vazio
    
     /**
     * Construtor vazio para Apresentacao
     * 
     */
    public Apresentacao(){
       pagina_atual = 0;
       total   = 0;
       paginas = new ArrayList<>();  
    }

    /**
     * Construtor parameterizado para apresentacao.
     * 
     * @param lista recebe lista de strings a apresentar 
     */
    public Apresentacao(Collection<String> lista){
        pagina_atual = 0;
        total        = lista.size();
        
        int nPags = total/ValoresFixos.N_ELEM_POR_PAG;
        if (total%ValoresFixos.N_ELEM_POR_PAG != 0) nPags++;
        
        paginas = new ArrayList<>(nPags);
        Iterator it      = lista.iterator();
        List<String> pag;
        
        while(it.hasNext()){
            pag = new ArrayList<>(ValoresFixos.N_ELEM_POR_PAG);
            for(int i = 0;it.hasNext() && i<ValoresFixos.N_ELEM_POR_PAG;i++){
                pag.add((String) it.next());
            }
            paginas.add(pag);
        }
    }
    
    
    /**
     * Construtor de cópia para Apresentacao
     * 
     * @param dm
     *           Recebe um objeto instânciado a partir da classe Apresentacao
     *           e apartir do mesmo instância um novo objeto dessa classe
     *           garantindo o encapsulamento de dados.
     */
    public Apresentacao(Apresentacao dm){
        pagina_atual = 0;
        total = 0;
        
        this.paginas = new ArrayList<>();
        
        this.paginas = dm.getPaginas(); //encapsulamento de dados é garantido em getPaginas()
    }

    /**GETTERS**/
    
    /**
     * Obter a lista das paginas
     * 
     * @return List lista dos dados das paginas
     */
    public List<List<String>> getPaginas(){
        List<List<String>> aux = new ArrayList<>();
        
        this.paginas.stream().forEach((m) -> {
            aux.add(new ArrayList<>(m));
        });
        
        return aux;
    }
    
    /**
     * Permite obter uma pagina
     * 
     * @param pagina numero da pagina
     * 
     * @return pagina
     * 
     * @throws PaginaInexistenteException se o indice argumento nao existir 
     */
    public List<String> getPagina(int pagina) throws PaginaInexistenteException{
        if (pagina>=this.paginas.size() || pagina<0) throw new PaginaInexistenteException();
        
        return new ArrayList<>(this.paginas.get(pagina));
    }
    
    
        /**
     * Obter elemento
     * 
     * @param pagina
     * @param linha
     * 
     * @return elemento
     * @throws PaginaInexistenteException se a linha ou a pagina argumento corresponderem a valores não presentes na lista paginada
     */
    public String getElemento(int pagina, int linha) throws PaginaInexistenteException{
        if (pagina>=this.paginas.size() || pagina<0) throw new PaginaInexistenteException();
 
        List<String> linhas = this.paginas.get(pagina);
        
        if (linha>=linhas.size() || linha<0) throw new PaginaInexistenteException();
        
        return linhas.get(linha);
    }
    
    /**SETTERS**/
    
    
    /**
     * Substitui a lista das filiais que este objeto engloba.
     * É mantido o encapsulamento de dados.
     * 
     * @param dm, Recebe a lista das filiais que irá substituir a lista que 
     *            este objeto engloba sendo mantido o encapsulamento de dados.
     */
    public void setPaginas(List<List<String>> dm){
        this.paginas = new ArrayList<>();
        
        dm.stream().forEach((m) -> {
            this.paginas.add(new ArrayList<>(m));
        });
    }

    /**
     * substitui dados sobre a filial de índice argumento englobado por este objeto.
     * É mantido o encapsulamento de dados.
     * 
     * @param pagina, índice da filial englobada por este objeto que se pretende substituir.
     *                  Note-se que a primeira filial tem o índice 0, a segunda tem
     *                  o índice 1 e por aí adiante...
     * 
     */
    public void setPagina(List<String> pagina){
        this.paginas.add(new ArrayList<>(pagina));
    }
    
    
    /**METODOS**/
    
    public void add(String elemento){
        int tam = paginas.size();
        List<String> pag;
        
        if (tam==0) { 
            pag = new ArrayList<>();
            pag.add(elemento);
            paginas.add(pag);
        }
        else{
            pag = paginas.get(tam-1);
            int tamp = pag.size();
            if (tamp == (ValoresFixos.N_ELEM_POR_PAG)-2){
                pag = new ArrayList<>();
                pag.add(elemento);
                paginas.add(pag);
            }
            else{
                pag.add(elemento);
                paginas.add(pag);
            }
        }
    }
    
    public int getNpaginas(){
       return paginas.size();
    }
    
    public int getTotal(){
        return total;
    }
    
    public int getNlinhasPpagina(){
        return ValoresFixos.N_ELEM_POR_PAG;
    }
    
    public boolean hasNext(){
        return (this.pagina_atual)<paginas.size();
    }
    
    public List<String> next() throws PaginaInexistenteException{
        int p = pagina_atual;
        p++;
        return getPagina(p);
    }
    
    public void recomecarApresentacao(){
        pagina_atual = 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.paginas);
        hash = 97 * hash + this.total;
        hash = 97 * hash + this.pagina_atual;
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
        final Apresentacao other = (Apresentacao) obj;
        if (this.total != other.total) {
            return false;
        }
        if (this.pagina_atual != other.pagina_atual) {
            return false;
        }
        return Objects.equals(this.paginas, other.paginas);
    }

    @Override
    public String toString() {
        return "Apresentacao{" + "paginas=" + paginas + ", total=" + total + ", pagina_atual=" + pagina_atual + '}';
    }
    
    @Override
    public Apresentacao clone(){
        return new Apresentacao(this);
    }
    
}
