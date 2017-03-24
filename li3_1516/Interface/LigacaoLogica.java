
package li3_1516.Interface;

import Auxiliares_modulos.Compators.ComparadorProdutoQuantidade;
import Auxiliares_modulos.DadosGlobaisMeses;
import Auxiliares_modulos.DuploVendasClientes;
import Auxiliares_modulos.TriploQuantidadeVendasFaturacao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import li3_1516.Catalogo.Catalogo;
import li3_1516.Exceptions.ClienteInvalidoException;
import li3_1516.Exceptions.ExtensaoInvalidaException;
import li3_1516.Exceptions.ProdutoInvalidoException;
import li3_1516.Faturacao.Faturacao;
import li3_1516.Filial.Filial;
import li3_1516.Filial.ProdutoFilial;
import li3_1516.Interface.AuxiliaresInterface.Vendas.EstatisticasLeituraVendas;
import li3_1516.Interface.AuxiliaresInterface.ParInsercoesTotal;
import li3_1516.Interface.AuxiliaresInterface.RecebeInput;
import li3_1516.Interface.AuxiliaresInterface.ValoresFixos;
import li3_1516.Interface.AuxiliaresInterface.Vendas.Vendas;

/**
 *
 * Classe que suporta a lógica da aplicação e faz a ponte entre o utilizador 
 * e cada estrutura relevante.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class LigacaoLogica implements Serializable{
    
    private Vendas    v;

    private Catalogo  catProdutos;
    private Catalogo  catClientes;
    private Faturacao faturacao;
    private List<Filial> filiais;
    
    
    public LigacaoLogica(){
        //Inicializar módulos
        catProdutos = new Catalogo(ValoresFixos.CATALOGO_PRODUTOS_TAMANHO_INICIAL);  // catalogo de produtos
        catClientes = new Catalogo(ValoresFixos.CATALOGO_CLIENTES_TAMANHO_INICIAL); // catalogo de clientes
        faturacao   = new Faturacao(ValoresFixos.FATURACAO_TAMANHO_INICIAL); // faturacao
        filiais     = new ArrayList<>(ValoresFixos.N_FILIAIS); // filiais
        
        for(int i = 0; i<ValoresFixos.N_FILIAIS;i++){
            filiais.add(new Filial(ValoresFixos.FILIAL_CLIENTES_TAMANHO_INICIAL, ValoresFixos.FILIAL_PRODUTOS_TAMANHO_INICIAL));
        }
        ////////////////////////
        v = null;
    }
    
    private boolean verificarExtensao(String ficheiro, String extensao){
        int indice = ficheiro.lastIndexOf('.');
        if (indice == -1) return false;
        return ficheiro.substring(indice).equals(".txt");
    }
    
    // Vendas:   891.108 linhas válidas
    public EstatisticasLeituraVendas lerVendas(String ficheiro) throws ExtensaoInvalidaException{

	if (!(verificarExtensao(ficheiro, "txt"))) 
            throw new ExtensaoInvalidaException("ERRO: O ficheiro tem uma extensão inválida (não é .txt).");
        
        v = new Vendas(RecebeInput.readLinesWithBuff(ficheiro), catProdutos, catClientes, faturacao, filiais, true);
        
        return v.getEstatisticasLeituraVendas();
    }
    
     // Vendas:  PARA TESTE DE PARALELISMO
    // Vendas:   891.108 linhas válidas
    public EstatisticasLeituraVendas lerVendas(String ficheiro, boolean paralelismo) throws ExtensaoInvalidaException{

	if (!(verificarExtensao(ficheiro, "txt"))) 
            throw new ExtensaoInvalidaException("ERRO: O ficheiro tem uma extensão inválida (não é .txt).");
        
        v = new Vendas(RecebeInput.readLinesWithBuff(ficheiro), catProdutos, catClientes, faturacao, filiais, paralelismo);
        
        return v.getEstatisticasLeituraVendas();
    }

     // clientes: 16.384
    // Produtos: 171.008
    public ParInsercoesTotal lerCatalogo(String ficheiro, int tipoCatalogo) throws ExtensaoInvalidaException{
        
	if (!(verificarExtensao(ficheiro, "txt"))) 
            throw new ExtensaoInvalidaException("ERRO: O ficheiro tem uma extensão inválida (não é .txt).");
        
        ArrayList<String> linhas = RecebeInput.readLinesWithBuff(ficheiro);
        
        linhas.parallelStream().forEach((s) -> {
            if (tipoCatalogo == 1){
                catProdutos.add(s);
                faturacao.add(s);
                filiais.stream().forEach((f) -> f.addProduto(s));
            }
            if (tipoCatalogo == 0) {
                catClientes.add(s);
                filiais.stream().forEach((f) -> f.addCliente(s));
            }
        });
        
        if (tipoCatalogo == 1) return new ParInsercoesTotal(catProdutos.size(), linhas.size());
        return new ParInsercoesTotal(catClientes.size(), linhas.size());
    }
    
    public Collection<String> produtosNuncaComprados(){
        return faturacao.getNaoComprados();
    }
    
    public LigacaoLogica carregarEstado(String ficheiro) throws IOException, FileNotFoundException, ClassNotFoundException{
        FileInputStream fis;
        ObjectInputStream ois;
        
        fis = new FileInputStream(ficheiro);
        ois = new ObjectInputStream(fis);
              
        LigacaoLogica l = (LigacaoLogica)ois.readObject();
        ois.close();
        
        return l;
    }
    
    public void guardarEstado(String ficheiro) throws IOException{
        FileOutputStream fos;
        ObjectOutputStream oos;
        
        fos = new FileOutputStream(ficheiro);
        oos = new ObjectOutputStream(fos);
         
        oos.writeObject(this);
        oos.close();
    }
    
    public EstatisticasLeituraVendas getEstatisticasLeituraVendas(){
        return v.getEstatisticasLeituraVendas();
    }
    
    public int getTotalClis(){
        return this.catClientes.size();
    }
    
    public int getTotalProds(){
        return this.catProdutos.size();
    }
    
    public int getClisCompradores(){
        Set<String> aux = new HashSet<>();
        filiais.stream().forEach(f->{
                                    aux.addAll(f.getClientesCompradores());
                                });
        return aux.size();
    }
    
    public Map<Integer, TriploQuantidadeVendasFaturacao> getDadosMensaisCliente(String cli, int filial){
        return filiais.get(filial-1).getDadosMensaisCliente(cli);
    }

    public Map<Integer, TriploQuantidadeVendasFaturacao> getDadosMensaisProduto(String prod, int filial){
        return filiais.get(filial-1).getDadosMensaisProduto(prod);
    }
    
    public List<String> getProdutosMaisCompradosQuantidade(int filial, int X){
        Map<String, ProdutoFilial> aux;
        
        if(filial==0) {
            aux = new ConcurrentHashMap<>(ValoresFixos.CATALOGO_PRODUTOS_TAMANHO_INICIAL);
            filiais.parallelStream()
                   .forEach( (f) -> {
                        f.getMapProdutosDadosFilial().forEach((k,t) -> {
                                if(t.getQuantidade()>0){
                                    if(aux.containsKey(k)) aux.get(k).atualizarValores(t);
                                    else aux.put(k,t);
                                }
                        });
                    });
        }
        else aux = filiais.get(filial-1).getMapProdutosDadosFilialSemEncapsulamento();
        
        List<String> list = 
           aux
           .entrySet()
           .parallelStream()
           .sorted(new ComparadorProdutoQuantidade())
           .limit(X)
           .map(e -> e.getKey()+"\n     |   o Quantidade: "+e.getValue().getQuantidade()+"; Comprado por "+e.getValue().getNumeroCompradores()+" Clientes Distintos")
           .collect(Collectors.toList());
        
        return list;
        
    }
    
    public List<String> getMaisComprados(String cli, int filtro) throws ClienteInvalidoException{
        
        Comparator<Object> cmp = new ComparadorProdutoQuantidade();
        Map<String, TriploQuantidadeVendasFaturacao> aux;
        
        if (filtro == 0){
            aux = new ConcurrentHashMap<>(ValoresFixos.CATALOGO_PRODUTOS_TAMANHO_INICIAL);
            
            filiais.stream()
                   .forEach( (f) -> {
                        f.getProdutosComprados(cli).forEach((k,t) -> {
                                if(aux.containsKey(k)) aux.get(k).atualizarValores(t);
                                else aux.put(k,t.clone());
                        });
                    });
        }
        else aux = filiais.get(filtro-1).getProdutosComprados(cli);
        
        if(aux.isEmpty()) throw new ClienteInvalidoException();
        
        return aux.entrySet()
                  .parallelStream()
                  .sorted((Comparator<? super Entry<String, TriploQuantidadeVendasFaturacao>>) cmp)
                  .map(e -> e.getKey()+" - Quantidade: "
                                      +((TriploQuantidadeVendasFaturacao)e.getValue())
                                                                          .getQuantidade())
                  .collect(Collectors.toList());
    }
    
    
    public List<String> getMaioresCompradores(int filial, int X){
        Map<String, Double> aux;
        
        if(filial==0){
           aux = new ConcurrentHashMap<>(ValoresFixos.CATALOGO_CLIENTES_TAMANHO_INICIAL);
            
            filiais.stream()
                   .forEach( (f) -> {
                        f.getCompradoresFaturacao().forEach((k,t) -> {
                                if(aux.containsKey(k)) aux.put(k,aux.get(k)+t);
                                else aux.put(k,t);
                        });
                    });
        }
        else aux = filiais.get(filial-1).getCompradoresFaturacao();
        
        List<String> list = 
            aux.entrySet()
           .parallelStream()
           .sorted((c1,c2)->c2.getValue().compareTo(c1.getValue()))
           .limit(X)
           .map(e -> e.getKey()+" - Dinheiro gasto: "+e.getValue())
           .collect(Collectors.toList());
        
        return list;
    }
    
    public List<String> getCompradoresMaiorNumeroComprasDiferentes(int filial, int X){
        Map<String, Integer> aux;
                
        if (filial == 0){
            aux = new ConcurrentHashMap<>(ValoresFixos.CATALOGO_CLIENTES_TAMANHO_INICIAL);
            
            filiais.stream()
                   .forEach( (f) -> {
                        f.getMapClientesNProdutosDistintos()
                            .forEach((k,t) -> {
                                if(aux.containsKey(k)) aux.put(k,aux.get(k)+t);
                                else aux.put(k,t);
                        });
                    });
        }
        else aux = filiais.get(filial-1).getMapClientesNProdutosDistintos();
        
        List<String> list = 
            aux.entrySet()
               .parallelStream()
               .sorted((c1,c2)->c2.getValue().compareTo(c1.getValue()))
               .limit(X)
               .map(e -> e.getKey()+" - Produtos Diferentes Comprados: "+e.getValue())
               .collect(Collectors.toList());
        
        return list;
    }
    
    public List<String> getMaioresCompradoresProduto(String prod, int filial, int X) throws ProdutoInvalidoException{
        Map<String, TriploQuantidadeVendasFaturacao> aux;
        
        if (filial == 0){
            aux = new ConcurrentHashMap<>();
            
            filiais.parallelStream()
                   .forEach( (f) -> {
                        f.getMapClientesQuantidadeProduto(prod)
                            .forEach((k,t) -> {
                                if(aux.containsKey(k)) aux.get(k).atualizarValores(t);
                                else aux.put(k,t);
                        });
                    });
        }
        else aux = filiais.get(filial-1).getMapClientesQuantidadeProduto(prod);
        
        if(aux.isEmpty()) throw new ProdutoInvalidoException();
        
        List<String> list = 
           aux
           .entrySet()
           .parallelStream()
           .sorted(new ComparadorProdutoQuantidade())
           .limit(X)
           .map(e -> e.getKey()+"\n     |   o Quantidade: "+e.getValue().getQuantidade()+"; Faturação: "+e.getValue().getFaturacao()+"; em "+e.getValue().getVendas()+" Vendas")
           .collect(Collectors.toList());
        
        return list;
    }
    
    
    
    public int getNumProdsNaoComprados(){
        return faturacao.getNumNaoComprados();
    }
    
    public double getFaturacaoTotal(){
        return faturacao.getFaturacao();
    }
    
    public DadosGlobaisMeses getDadosGlobaisMeses(int filial){
        DadosGlobaisMeses dgm = new DadosGlobaisMeses();
        
        for(int mes = 1; mes<=ValoresFixos.N_MESES; mes++)
            dgm.add(filiais.get(filial).getTuploQuantidadeVendasFaturacaoClientes(mes), mes);
        
        return dgm;
    }
    
    public DadosGlobaisMeses getDadosGlobais(){
        DadosGlobaisMeses dgm = new DadosGlobaisMeses();
        
        for(int filial = 0; filial<ValoresFixos.N_FILIAIS; filial++){
            for(int mes = 1; mes<=ValoresFixos.N_MESES; mes++)
                dgm.add(filiais.get(filial).getTuploQuantidadeVendasFaturacaoClientes(mes), mes);
        }
        
        return dgm;
    }
    
    public DuploVendasClientes getDuploVendasClientesMes(int mes){
        DuploVendasClientes dvc = new DuploVendasClientes();
        filiais.stream().forEach(f -> dvc.atualizarValores(f.getDuploVendasClientes(mes)));
        return dvc;
    }
    
    public void eliminarModulos(){          
        catClientes.clear();
        catProdutos.clear();
        faturacao.clear();
        filiais.stream().forEach(f -> f.clear());
        
        //Inicializar módulos
        catProdutos = new Catalogo(ValoresFixos.CATALOGO_PRODUTOS_TAMANHO_INICIAL);  // catalogo de produtos
        catClientes = new Catalogo(ValoresFixos.CATALOGO_CLIENTES_TAMANHO_INICIAL); // catalogo de clientes
        faturacao   = new Faturacao(ValoresFixos.FATURACAO_TAMANHO_INICIAL); // faturacao
        filiais     = new ArrayList<>(ValoresFixos.N_FILIAIS); // filiais
        
        for(int i = 0; i<ValoresFixos.N_FILIAIS;i++){
            filiais.add(new Filial(ValoresFixos.FILIAL_CLIENTES_TAMANHO_INICIAL, ValoresFixos.FILIAL_PRODUTOS_TAMANHO_INICIAL));
        }
        ////////////////////////
        v = null;
    }

    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        sb.append(catClientes.toString());
        sb.append(catProdutos.toString());
        sb.append(faturacao.toString());
        filiais.stream().forEach(f -> sb.append(f.toString()));
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.v);
        hash = 73 * hash + Objects.hashCode(this.catProdutos);
        hash = 73 * hash + Objects.hashCode(this.catClientes);
        hash = 73 * hash + Objects.hashCode(this.faturacao);
        hash = 73 * hash + Objects.hashCode(this.filiais);
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
        final LigacaoLogica other = (LigacaoLogica) obj;
        if (!Objects.equals(this.v, other.v)) {
            return false;
        }
        if (!Objects.equals(this.catProdutos, other.catProdutos)) {
            return false;
        }
        if (!Objects.equals(this.catClientes, other.catClientes)) {
            return false;
        }
        if (!Objects.equals(this.faturacao, other.faturacao)) {
            return false;
        }
        return Objects.equals(this.filiais, other.filiais);
    }
   
    
    
}