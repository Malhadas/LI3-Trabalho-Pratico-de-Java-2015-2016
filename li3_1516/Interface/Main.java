
package li3_1516.Interface;

import Auxiliares_modulos.DadosGlobaisMeses;
import Auxiliares_modulos.DuploVendasClientes;
import Auxiliares_modulos.TriploQuantidadeVendasFaturacao;
import li3_1516.Interface.AuxiliaresInterface.Vendas.EstatisticasLeituraVendas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import li3_1516.Exceptions.ClienteInvalidoException;
import li3_1516.Exceptions.ExtensaoInvalidaException;
import li3_1516.Exceptions.GoBackException;
import li3_1516.Exceptions.PaginaInexistenteException;
import li3_1516.Exceptions.ProdutoInvalidoException;
import li3_1516.Interface.AuxiliaresInterface.*;

/**
 *
 * @author ASUS
 */
public class Main {
    
    //Declara-se este buffered reader para que o mesmo não seja 'reinstânciado sempre que se pretende obter input do terminal.
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static LigacaoLogica ll  = new LigacaoLogica();
    
    private static String ficheiro_estado = "hipermercado.dat";

    private static String nome_ultimo_ficheiro_vendas = "";
    private static double tempo_leitura_ultimo_ficheiro_vendas = 0;




private static void lerFicheiros(){
		
    /*Carregamento de ficheiros*/
    try {
        
        //Ler ficheiro de clientes
        Menu.menu(br,4); /*clientes*/
        String input = RecebeInput.lerLinha(br);
                
        if (input.equals("*")) input = "Clientes.txt";
        
        Crono.start();
        ParInsercoesTotal pit = ll.lerCatalogo(input, ValoresFixos.CAT_CLIENTES);
        double tempo = Crono.stop();
              
        //Apresentação de informação sobre a leitura
        Menu.apresentaResultadosLeitura(br, input, pit.getInsercoes(), pit.getTotal(), tempo);
        //////////////////////////////
        
        //Ler ficheiro de produtos
        Menu.menu(br,3); /*produtos*/
        input = RecebeInput.lerLinha(br);
                
        if (input.equals("*")) input = "Produtos.txt";
        
        Crono.start();
        pit = ll.lerCatalogo(input, ValoresFixos.CAT_PRODUTOS);
        tempo = Crono.stop();
        
        //Apresentação de informação sobre a leitura        
        Menu.apresentaResultadosLeitura(br, input, pit.getInsercoes(), pit.getTotal(), tempo);
	/////////////////////////
        
        //Ler ficheiro de vendas
        Menu.menu(br,13); /*vendas*/
        input = RecebeInput.lerLinha(br);
                
        if (input.equals("*")) input = "Vendas_1M.txt";
        
        Crono.start();
        EstatisticasLeituraVendas v = ll.lerVendas(input);
        tempo = Crono.stop();
        
        Main.nome_ultimo_ficheiro_vendas          = input;
        Main.tempo_leitura_ultimo_ficheiro_vendas = tempo;
        
        //Apresentação de informação sobre a leitura
        Menu.apresentaResultadosValidacao(
                                          br,
                                          v.getLinhas_validas(), v.getLinhas_totais(),
                                          input,
                                          tempo,
                                          v.getProdutos_invalidos(),
                                          v.getPrecos_invalidos(),
                                          v.getQuantidades_invalidas(),
                                          v.getRegimes_invalidos(),
                                          v.getClientes_invalidos(),
                                          v.getMeses_invalidos(),
                                          v.getFiliais_invalidas(),
                                          v.getPreco_zero()
                                        );        
        ////////////////////////////////////////////

    } catch (GoBackException ex) {
        System.out.println("ERRO: Digitou os caracteres de retrocesso \"<-\"\nNo entanto não pode cancelar a leitura dos ficheiros.");
        System.out.println("A leitura irá recomeçar.");
        ll.eliminarModulos();
        lerFicheiros();
    } catch(ExtensaoInvalidaException eie){
        System.out.println(eie.getMessage());
        System.out.println("A leitura irá recomeçar.");
        ll.eliminarModulos();
        lerFicheiros();
    }
}


    private static void cicloPrincipal(){
        String opcao = "";
        
        while(true){

            try {
                Menu.menu(br,1);
                
                opcao = RecebeInput.lerLinha(br);
                if (opcao==null || opcao.length()==0){
                    System.out.println("ERRO: Inseriu uma opção para o menu inválida.");
                    System.out.println("Escreva 'h' para consultar as intruções.");
                    System.out.println("Por favor tente novamente.");
                    System.out.println("");
                    continue;
                }
                switch(opcao.toLowerCase().charAt(0)){
                    
                    case 'h' :
                        Menu.menu(br,5);
                        break;
                        
                    case 's' :
                        Menu.menu(br,8);
                        return;
                        
                    case 'r' :
                        ll.eliminarModulos();
                        lerFicheiros();
                        break;
                        
                    case 'l' :
                        Menu.menu(br,7);
                        break;
                        
                    case 't' :
                        Menu.menu(br,0);
                        break;
                        
                    case 'a' :
                        Menu.menu(br,6);
                        break;
                    
                    case 'g' :
                        guardarEstado();
                        break;
                
                    case 'c' :
                        ll = carregarEstado();
                        break;
                        
                    case 'm'  :
                        System.out.println("Indique o nome do ficheiro de configuração que pretende, por favor.");
                        ficheiro_estado = RecebeInput.lerLinha(br);
                        break;
                        
                    case 'q':
                        int q = Integer.parseInt(opcao.substring(1));
                            
                        if (q>=1 && q<=11) queries(q);
                        else System.out.print("\nERRO: Selecionou uma query/funcionalidade que não existe.\nConsulte a lista de queries/funcionalidades escrevendo 'l'.\n");
                        break;
                        
                    default :
                        System.out.println("ERRO: Inseriu uma opção para o menu inválida.");
                        System.out.println("Escreva 'h' para consultar as intruções.");
                        System.out.println("Por favor tente novamente.");
                        System.out.println("");
                        break;
                }
            } catch (GoBackException ex) {
                System.out.println("\nA voltar ao menu principal..\n");
            } catch (NumberFormatException nfe){
                System.out.println("\nERRO: Começou a sua opção com \""+opcao.charAt(0)+"\", no entanto não escolheu nenhum número válido para nenhuma query/funcionalidade de seguida.\nConsulte a lista de queries/funcionalidades escrevendo 'l'.");
            }

	}
    }
    
    private static void guardarEstado(){
        try{
            System.out.println("A tentar guardar o estado no ficheiro " + ficheiro_estado + "..");
            ll.guardarEstado(ficheiro_estado);
            System.out.println("Estado guardado com sucesso.\n");
       } catch (IOException fnfe){
            System.out.println("ERRO: Falha ao guardar estado no ficheiro "+ficheiro_estado);
       }
    }
                
    private static LigacaoLogica carregarEstado(){
        LigacaoLogica i = new LigacaoLogica();
        System.out.println("A tentar carregar o estado do ficheiro " + ficheiro_estado + "..");
        
        try {
            i = ll.carregarEstado(ficheiro_estado);
            System.out.println("Estado carregado com sucesso.\n");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERRO: Falha ao carregar estado no ficheiro "+ficheiro_estado);
        }
        
        return i;
    }
    
    private static void queries(int i) throws GoBackException {
        try {
            double tempo;
            int    mes;
            int    f;
            int    X;
            String prod;
            List<String> fl;
            
            switch(i){
                
                case 1 :
                    
                    Crono.start();
                    EstatisticasLeituraVendas elv = ll.getEstatisticasLeituraVendas();
                    int total_clis          = ll.getTotalClis();
                    int clis_compradores    = ll.getClisCompradores();
                    int total_prods         = ll.getTotalProds();
                    int prods_nao_comprados = ll.getNumProdsNaoComprados();
                    double faturacao_total  = ll.getFaturacaoTotal();
                    tempo = Crono.stop();
                    
                    System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");
                    
                    //Apresentação de informação sobre a leitura
                    Menu.apresentaEstatisticas(
                            br,
                            elv.getLinhas_validas(), elv.getLinhas_totais(),
                            nome_ultimo_ficheiro_vendas,
                            tempo_leitura_ultimo_ficheiro_vendas,
                            elv.getProdutos_invalidos(),
                            elv.getPrecos_invalidos(),
                            elv.getQuantidades_invalidas(),
                            elv.getRegimes_invalidos(),
                            elv.getClientes_invalidos(),
                            elv.getMeses_invalidos(),
                            elv.getFiliais_invalidas(),
                            elv.getPreco_zero(),
                            total_clis,  clis_compradores,                total_clis-clis_compradores,
                            total_prods, total_prods-prods_nao_comprados, prods_nao_comprados,
                            faturacao_total
                    );
                    ////////////////////////////////////////////
                    break;
                    
                case 2 :
                    
                    Crono.start();
                    List<DadosGlobaisMeses> dgms = new ArrayList<>(ValoresFixos.N_FILIAIS);
                    for(f = 0; f<ValoresFixos.N_FILIAIS; f++){
                        dgms.add(ll.getDadosGlobaisMeses(f));
                    }
                    tempo = Crono.stop();
                    
                    System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");
                    Menu.apresentaDados(br, dgms);
                    break;
                    
                case 3 :
                    Crono.start();
                    Collection<String> lista = ll.produtosNuncaComprados();
                    tempo = Crono.stop();
                    
                    System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");
                    
                    Menu.apresenta(br, new Apresentacao(lista));
                    break;
                    
                case 4  :
                    while(true){
                        Menu.menu(br, 11);
                        mes = RecebeInput.lerInt(br);
                        
                        if (mes<1 || mes>ValoresFixos.N_MESES) {
                            System.out.println("\nERRO: Forneceu um mês inválido, por favor tente novamente.\n");
                            continue;
                        }
                        break;
                    }
                    Crono.start();
                    DuploVendasClientes dvc = ll.getDuploVendasClientesMes(mes);
                    tempo = Crono.stop();
                    
                    System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");
                    
                    Menu.apresentaDados(br, dvc);
                    break;
                    
                case 5  :
                    
                    List<Map<Integer, TriploQuantidadeVendasFaturacao>> ldmc = new ArrayList<>(ValoresFixos.N_FILIAIS);
                    
                    Menu.menu(br,18);
                    String cli = RecebeInput.lerLinha(br);
                    
                    Crono.start();
                    for(i=1; i<=ValoresFixos.N_FILIAIS; i++)
                        ldmc.add(ll.getDadosMensaisCliente(cli, i));
                    tempo = Crono.stop();
                
                    System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");
                    Menu.apresentaDadosMensaisCliente(br, cli, ldmc);
                    
                    break;
                                
                case 6  :
                    
                    List<Map<Integer, TriploQuantidadeVendasFaturacao>> ldmp = new ArrayList<>(ValoresFixos.N_FILIAIS);
                    
                    Menu.menu(br,23);
                    String pr = RecebeInput.lerLinha(br);
                    
                    Crono.start();
                    for(i=1; i<=ValoresFixos.N_FILIAIS; i++)
                        ldmp.add(ll.getDadosMensaisProduto(pr, i));
                    tempo = Crono.stop();
                
                    System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");
                    Menu.apresentaDadosMensaisProduto(br, pr, ldmp);
                    
                    break;
                                
                case 7  :
                    
                    Menu.menu(br, 18);                    
                    String c = RecebeInput.lerLinha(br);
                    List<String> a;
                    
                    while(true){
                        Menu.menu(br, 12);
                        f = RecebeInput.lerInt(br);
                    
                        if (f<0 || f>ValoresFixos.N_FILIAIS){
                            System.out.println("\nERRO: Forneceu uma filial inválida, por favor tente novamente.\n");
                            continue;
                        }
                        break;
                    }
                    
                    try {
                        Crono.start();
                        a = ll.getMaisComprados(c, f);
                        tempo = Crono.stop();
                
                        System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");
                    } catch (ClienteInvalidoException ex) {
                        System.out.println("\nERRO: Cliente não comprou na filial especificada.");
                        throw new GoBackException();
                    }
                    Menu.apresenta(br, new Apresentacao(a));
                    break;
                    
                case 8  :
                    
                    while(true){
                        Menu.menu(br, 12);
                        f = RecebeInput.lerInt(br);
                    
                        if (f<0 || f>ValoresFixos.N_FILIAIS){
                            System.out.println("\nERRO: Forneceu uma filial inválida, por favor tente novamente.\n");
                            continue;
                        }
                        break;
                    }
                    
                    while(true){
                        Menu.menu(br,15);
                        X = RecebeInput.lerInt(br);
                    
                        if(X<1){
                            System.out.println("\nERRO: Forneceu um valor inválido para o top.\nPor favor forneça um valor maior que 0.\n");
                            continue;
                        }
                        break;
                    }
                    
                    Crono.start();
                    fl = ll.getProdutosMaisCompradosQuantidade(f,X);
                    tempo = Crono.stop();
                
                    System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");

                    Menu.apresenta(br, new Apresentacao(fl));
                    break;
                    
                case 9  :
                    
                    while(true){
                        Menu.menu(br, 12);
                        f = RecebeInput.lerInt(br);
                    
                        if (f<0 || f>ValoresFixos.N_FILIAIS){
                            System.out.println("\nERRO: Forneceu uma filial inválida, por favor tente novamente.\n");
                            continue;
                        }
                        break;
                    }
                    
                    while(true){
                        Menu.menu(br,15);
                        X = RecebeInput.lerInt(br);
                    
                        if(X<1){
                            System.out.println("\nERRO: Forneceu um valor inválido para o top.\nPor favor forneça um valor maior que 0.\n");
                            continue;
                        }
                        break;
                    }
                    
                    Crono.start();
                    fl = ll.getMaioresCompradores(f,X);
                    tempo = Crono.stop();
                
                    System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");

                    Menu.apresenta(br, new Apresentacao(fl));
                    break;
                                
                case 10 :
                    
                    while(true){
                        Menu.menu(br, 12);
                        f = RecebeInput.lerInt(br);
                    
                        if (f<0 || f>ValoresFixos.N_FILIAIS){
                            System.out.println("\nERRO: Forneceu uma filial inválida, por favor tente novamente.\n");
                            continue;
                        }
                        break;
                    }
                    
                    while(true){
                        Menu.menu(br,15);
                        X = RecebeInput.lerInt(br);
                    
                        if(X<1){
                            System.out.println("\nERRO: Forneceu um valor inválido para o top.\nPor favor forneça um valor maior que 0.\n");
                            continue;
                        }
                        break;
                    }
                    
                    Crono.start();
                    fl = ll.getCompradoresMaiorNumeroComprasDiferentes(f,X);
                    tempo = Crono.stop();
                
                    System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");

                    Menu.apresenta(br, new Apresentacao(fl));
                    break;
                    
                case 11 :
                    while(true){
                        Menu.menu(br, 12);
                        f = RecebeInput.lerInt(br);
                    
                        if (f<0 || f>ValoresFixos.N_FILIAIS){
                            System.out.println("\nERRO: Forneceu uma filial inválida, por favor tente novamente.\n");
                            continue;
                        }
                        break;
                    }
                    
                    while(true){
                        Menu.menu(br,15);
                        X = RecebeInput.lerInt(br);
                    
                        if(X<1){
                            System.out.println("\nERRO: Forneceu um valor inválido para o top.\nPor favor forneça um valor maior que 0.\n");
                            continue;
                        }
                        break;
                    }
                    
                    Menu.menu(br,23);
                    prod = RecebeInput.lerLinha(br);

                    try {
                        Crono.start();
                        fl = ll.getMaioresCompradoresProduto(prod, f,X);
                        tempo = Crono.stop();
                
                        System.out.println("\n     Querie realizada em "+(tempo*1000)+"ms / "+tempo+"s\n");

                        Menu.apresenta(br, new Apresentacao(fl));
                    } catch (ProdutoInvalidoException ex) {
                        System.out.println("\nERRO: O produto não foi comprado na filial específicada.\n");
                    }
                    break;
            }
        } catch (PaginaInexistenteException ex) {
            System.out.print("\nApresentação paginada vazia.");
            throw new GoBackException();
        }
    }
 
    
    public static void main(String[] args) {
        //Método main deve ser simples e conciso.
        Menu.menu(br,0);    //Apresentar titulo
        lerFicheiros();    //Leitura dos ficheiros
        cicloPrincipal(); //Menu principal
        //fim
    }
}
