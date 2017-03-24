/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li3_1516.Interface.AuxiliaresInterface;

import Auxiliares_modulos.DadosGlobaisMeses;
import Auxiliares_modulos.DuploVendasClientes;
import Auxiliares_modulos.TriploQuantidadeVendasFaturacao;
import java.io.BufferedReader;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;
import li3_1516.Exceptions.GoBackException;
import li3_1516.Exceptions.PaginaInexistenteException;

/**
 * 
 * Classe utilizada para apresentação dos menus
 * 
 * @author Grupo 80
 */
public class Menu implements Cloneable, Serializable{
    
public static void menu (BufferedReader br, int opcao){
	switch(opcao) {

		case 0: 
			/*Logótipo*/
	System.out.print("\n");
	System.out.print("\n      ________________________________________________________");
	System.out.print("\n     /                                                        \\");                                                             
	System.out.print("\n     |   _____            __       __            _            |");
	System.out.print("\n     |  / ____|           \\ \\     / /           | |           |");
	System.out.print("\n     | | |  __  ___ _ __ __\\ \\   / /__ _ __   __| | __ _ ___  |");
	System.out.print("\n     | | | |_ |/ _ \\ '__/ _ \\ \\_/ / _ \\ '_ \\ / _` |/ _` / __| |");
	System.out.print("\n     | | |__| |  __/ | |  __/\\   /  __/ | | | (_| | (_| \\__ \\ |");
	System.out.print("\n     |  \\_____|\\___|_|  \\___| \\_/ \\___|_| |_|\\__,_|\\__,_|___/ |");
	System.out.print("\n     |                                                        |");
	System.out.print("\n     |                 Prima Enter para continuar             |");
	System.out.print("\n     |                 --------------------------             |");
	System.out.print("\n     | LI3 2015/2016                                Grupo: 80 |");
	System.out.print("\n     \\________________________________________________________/");
	System.out.print("\n");
	System.out.print("\n");
	RecebeInput.esperarEnter(br);

	/*    _____            __   ^   __            _           
	 *   / ____|           \ \  |  / /           | |          
         *  | |  __  ___ _ __ __\ \   / /__ _ __   __| | __ _ ___ 
         *  | | |_ |/ _ \ '__/ _ \ \_/ / _ \ '_ \ / _` |/ _` / __|
         *  | |__| |  __/ | |  __/\   /  __/ | | | (_| | (_| \__ \  font: Big
         *   \_____|\___|_|  \___| \_/ \___|_| |_|\__,_|\__,_|___/
         */                                             
                                                      
			break;

                                                      
		case 8:
			/*Texto de saída*/
	System.out.print("\n      _______________________VOLTE SEMPRE_______________________");
	System.out.print("\n     /                            |                             \\");
	System.out.print("\n     |        A sair..            |           2015/2016         |");
	System.out.print("\n     |____________________________|_____________________________|");
	System.out.print("\n     |  Grupo 80                  |        Docentes             |");
	System.out.print("\n     |                            |                             |");
 	System.out.print("\n     |  - Daniel Malhadas A72293  |   - Fernando Martins        |");
	System.out.print("\n     |  - Joel   Morais   A70841  |   - Vitor    Fonte          |");
	System.out.print("\n     |  - Rui    Miranda  A75488  |                             |");
	System.out.print("\n     |____________________________|_____________________________|");
	System.out.print("\n     |                                                          |");
	System.out.print("\n     |         Obrigado por utilizar o nosso GereVendas!        |");
	System.out.print("\n     |                                                          |");
	System.out.print("\n     |                 Prima Enter para terminar                |");
	System.out.print("\n     |                 -------------------------                |");
	System.out.print("\n     |                                                          |");
	System.out.print("\n     \\__________________________________________________________/");
	System.out.print("\n");
	RecebeInput.esperarEnter(br);
			break;

                                                                                                                  
		case 1:
			/*Menu principal*/
	System.out.print("\n      ______________MENU PRINCIPAL____________");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     | Menu principal:                        |");
	System.out.print("\n     |-----------------                       |");
	System.out.print("\n     |                      GereVendas - LI3  |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     | Escreva a letra 'h' para consultar     |");
	System.out.print("\n     | as instruções.                         |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
			break;

		case 5:
			/*Instruções Menu principal*/
	System.out.print("\n      _________________________MENU PRINCIPAL________________________");
	System.out.print("\n     /                                                               \\");
	System.out.print("\n     | Instruções:                                                    |");
	System.out.print("\n     |-------------                                                   |");
	System.out.print("\n     |                                              GereVendas - LI3  |");
	System.out.print("\n     |----------------------------------------------------------------|");
	System.out.print("\n     | 1 - Escreva 'l' para ver uma lista de funcionalidades que pode |");
	System.out.print("\n     |     executar.                                                  |");
	System.out.print("\n     | -------------------------------------------------------------- |");
	System.out.print("\n     | 2 - Escreva 'h' para ver de novo  esta página de instruções.   |");
	System.out.print("\n     | -------------------------------------------------------------- |");
	System.out.print("\n     | 3 - Escreva 't' para ver de novo o logótipo do projeto.        |");
	System.out.print("\n     | -------------------------------------------------------------- |");
	System.out.print("\n     | 4 - Escreva 'a' para ver a página 'sobre' onde estão           |");
	System.out.print("\n     |     informações relativas ao projeto.                          |");
	System.out.print("\n     | -------------------------------------------------------------- |");
	System.out.print("\n     | 5 - Escreva a letra 's' para sair da aplicação.                |");
	System.out.print("\n     | -------------------------------------------------------------- |");
	System.out.print("\n     | 6 - Escreva a letra 'r' para poder re-inserir os ficheiros.    |");
	System.out.print("\n     | -------------------------------------------------------------- |");
        System.out.print("\n     | 7 - Escreva a letra 'g' para guardar o estado da aplicação     |");
        System.out.print("\n     |     num ficheiro.                                              |");
        System.out.print("\n     | -------------------------------------------------------------- |");
        System.out.print("\n     | 8 - Escreva a letra 'c' para carregar o estado da aplicação    |");
        System.out.print("\n     |     préviamente guardado num ficheiro.                         |");
	System.out.print("\n     | -------------------------------------------------------------- |");
        System.out.print("\n     | 9 - Escreva a letra 'm' para mudar o nome do ficheiro onde     |");
	System.out.print("\n     |     guarda/carrega o estado da aplicação.                      |");
	System.out.print("\n     |----------------------------------------------------------------|");
	System.out.print("\n     |                                                                |");
	System.out.print("\n     |                   Prima Enter para continuar                   |");
	System.out.print("\n     |                   --------------------------                   |");
	System.out.print("\n     |                                                                |");
	System.out.print("\n     \\________________________________________________________________/");
	System.out.print("\n");
	System.out.print("\n");
	RecebeInput.esperarEnter(br);
	break;

		case 7:
			/*Funcionalidades/Queries*/
	System.out.print("\n      _________________________________MENU PRINCIPAL______________________________");
	System.out.print("\n     /     |                                                                       \\");
	System.out.print("\n     | Funcionalidades/Queries:                                                    |");
	System.out.print("\n     |--------------------------                                                   |");
	System.out.print("\n     |     |                                                     GereVendas - LI3  |");
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");
	System.out.print("\n     | 1.1 | Escreva 'q1' para obter os dados referentes ao último ficheiro de     |");/*Feito*/
        System.out.print("\n     |     | vendas lido.                                                          |");
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");
	System.out.print("\n     | 1.2 | Escreva 'q2' para obter dados gerais respeitantes aos dados atuais    |");/*Feito*/
	System.out.print("\n     |     | já registados nas estruturas.                                         |");
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");
	System.out.print("\n     | 2.1 | Escreva 'q3' para obter a lista global dos produtos que nunca foram   |");/*Feito*/
	System.out.print("\n     |     | comprados.                                                            |");
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");
	System.out.print("\n     | 2.2 | Escreva 'q4' para obter, para um determinado mês, o número total      |");/*Feito*/
	System.out.print("\n     |     | global de vendas realizadas e o número de clientes compradores.       |");
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");
	System.out.print("\n     | 2.3 | Escreva 'q5' para obter, para um determinado cliente, dados mensais   |");/*Feito*/
        System.out.print("\n     |     | sobre o mesmo.                                                        |");
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");
	System.out.print("\n     | 2.4 | Escreva 'q6' para obter, para um determinado produto, dados mensais   |");/*Feito*/
        System.out.print("\n     |     | sobre o mesmo.                                                        |");
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");
	System.out.print("\n     | 2.5 | Escreva 'q7' para obter todos os produtos comprados por determinado   |");/*Feito*/
	System.out.print("\n     |     | cliente em determinada filial ordenados por quantidade.               |");/*Adicionada funcionalidade esxtra:*/
	System.out.print("\n     |-----------------------------------------------------------------------------|");/*Possibilidade de escolher todas as filiais*/
	System.out.print("\n     |                                                                             |");
	System.out.print("\n     |                          Prima Enter para ver mais                          |");
	System.out.print("\n     |                          -------------------------                          |");
	System.out.print("\n     |                                                                             |");
	System.out.print("\n     \\____________________________________________________________________________/");
	System.out.print("\n");
	System.out.print("\n");
	RecebeInput.esperarEnter(br);
	System.out.print("\n      _________________________________MENU PRINCIPAL______________________________");
	System.out.print("\n     /     |                                                                       \\");
	System.out.print("\n     | Funcionalidades/Queries:                                                    |");
	System.out.print("\n     |--------------------------                                                   |");
	System.out.print("\n     |     |                                                     GereVendas - LI3  |");/*Feito*/
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");/*Adicionada funcionalidade extra*/
	System.out.print("\n     | 2.6 | Escreva 'q8' para obter a lista dos X produtos mais vendidos.         |");/*Possibilidade de ver a lista não só global mas também de cada filial*/
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");/*Feito*/
	System.out.print("\n     | 2.7 | Escreva 'q9' para obter o top X compradores para cada filial.         |");/*Adicionada funcionalidade extra*/
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");/*Possibilidade de ver a lista não só global mas também de cada filial e de um top quão grande quanto se quiser*/
	System.out.print("\n     | 2.8 | Escreva 'q10' para obter a lista X clientes que compraram mais        |");/*Feito*/
        System.out.print("\n     |     | produtos distintos.                                                   |");/*Adicionada funcionalidade extra*/
	System.out.print("\n     |-----|-----------------------------------------------------------------------|");/*Possibilidade de ver a lista não só global mas também de cada filial*/
	System.out.print("\n     | 2.9 | Escreva 'q11' para ver a lista dos X clientes que mais compraram      |");/*Feito*/
        System.out.print("\n     |     | determinado produto.                                                  |");/*Adicionada funcionalidade extra*/
	System.out.print("\n     |-----------------------------------------------------------------------------|");/*Possibilidade de ver a lista não só global mas também de cada filial*/
	System.out.print("\n     |                                                                             |");
	System.out.print("\n     |                          Prima Enter para continuar                         |");
	System.out.print("\n     |                          --------------------------                         |");
	System.out.print("\n     |                                                                             |");
	System.out.print("\n     \\____________________________________________________________________________/");
	System.out.print("\n");
	System.out.print("\n");
	RecebeInput.esperarEnter(br);
	break;

	case 6:
	/*About*/
	System.out.print("\n      __________________________SOBRE__________________________");
	System.out.print("\n     /                            |                             \\");
	System.out.print("\n     |     GereVendas - LI3       |      2015/2016              |");
	System.out.print("\n     |____________________________|_____________________________|");
	System.out.print("\n     |  Grupo 80                  |        Docentes             |");
	System.out.print("\n     |                            |                             |");
 	System.out.print("\n     |  - Daniel Malhadas A72293  |   - Fernando Martins        |");
	System.out.print("\n     |  - Joel   Morais   A70841  |   - Vitor    Fonte          |");
	System.out.print("\n     |  - Rui    Miranda  A75488  |                             |");
	System.out.print("\n     |____________________________|_____________________________|");
	System.out.print("\n     |                                                          |");
	System.out.print("\n     |                 Prima Enter para continuar               |");
	System.out.print("\n     |                 --------------------------               |");
	System.out.print("\n     |                                                          |");
	System.out.print("\n     \\__________________________________________________________/");
	System.out.print("\n");
	RecebeInput.esperarEnter(br);

			break;

		case 2:
			/*Apresentacao de lista*/
	System.out.print("\n      ______________APRESENTAÇÃO______________");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     | Instruções:                            |");
	System.out.print("\n     |-------------                           |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     | 1 - Escreva a letra 'p' seguida de     |");
	System.out.print("\n     |     um número para avançar para a      |");
	System.out.print("\n     |     página desse número específico.    |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     | 2 - Escreva '>' para avançar para a    |");
	System.out.print("\n     |     página seguinte.                   |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     | 3 - Escreva '<' para retroceder para   |");
	System.out.print("\n     |     a página anterior.                 |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     | 4 - Escreva '<-' para voltar ao menu   |");
	System.out.print("\n     |     principal.                         |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     | 5 - Escreva a letra 'h' para poder     |");
	System.out.print("\n     |     Consultar as instruções.           |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |----------------------------------------|");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |       Prima Enter para continuar       |");
	System.out.print("\n     |       --------------------------       |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
	System.out.print("\n");
	RecebeInput.esperarEnter(br);
	break;

		case 3:
	System.out.print("\n");
	System.out.print("\n      ________CARREGAMENTO DE FICHEIROS_______");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     |   Ficheiro de produtos                 |");
	System.out.print("\n     |------------------------                |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Forneça um nome para o ficheiro       |");
	System.out.print("\n     |  de produtos.                          |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Caso não queira escreva '*' e         |");
	System.out.print("\n     |  será usado o nome 'Produtos.txt'      |");
	System.out.print("\n     |  por defeito.                          |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
	System.out.print("\n     Nome do ficheiro: ");
			break;


		case 4:
	System.out.print("\n");
	System.out.print("\n      ________CARREGAMENTO DE FICHEIROS_______");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     |   Ficheiro de clientes                 |");
	System.out.print("\n     |------------------------                |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Forneça um nome para o ficheiro       |");
	System.out.print("\n     |  de produtos.                          |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Caso não queira escreva '*' e         |");
	System.out.print("\n     |  será usado o nome 'Clientes.txt'      |");
	System.out.print("\n     |  por defeito.                          |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
	System.out.print("\n     Nome do ficheiro: ");
			break;

	
	case 11:
	System.out.print("\n");
	System.out.print("\n      ________________________________________");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     | Escolha do Mês                         |");
	System.out.print("\n     |----------------                        |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Forneça o mês                         |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
	System.out.print("\n     Escreva o mês: ");
	break;

	case 12:
	System.out.print("\n");
	System.out.print("\n      ________________________________________");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     | Escolha da Filial                      |");
	System.out.print("\n     |-------------------                     |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Forneça a filial                      |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Escreva '0' para obter resultados     |");
	System.out.print("\n     |  globais, de todas as filiais          |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
	System.out.print("\n     Escreva filial: ");
	break;

	case 13:
	System.out.print("\n");
	System.out.print("\n      ________CARREGAMENTO DE FICHEIROS_______");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     |   Ficheiro de vendas                   |");
	System.out.print("\n     |----------------------                  |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Forneça um nome para o ficheiro       |");
	System.out.print("\n     |  de vendas.                            |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Caso não queira escreva '*' e         |");
	System.out.print("\n     |  será usado o nome 'Vendas_1M.txt'     |");
	System.out.print("\n     |  por defeito.                          |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
	System.out.print("\n     Nome do ficheiro: ");
			break;

	case 15:
	System.out.print("\n");
	System.out.print("\n      ________________________________________");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     | Escolha do Top                         |");
	System.out.print("\n     |----------------                        |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Forneça o numero do Top               |");
	System.out.print("\n     |  (exemplo: 3 para Top3; 10 para        |");
	System.out.print("\n     |  Top 10; etc...)                       |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
	System.out.print("\n     Escreva o número do top: ");
	break;

	case 18:
	System.out.print("\n");
	System.out.print("\n      ________________________________________");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     | Escolha do Cliente                     |");
	System.out.print("\n     |--------------------                    |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Forneça o cliente                     |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
	System.out.print("\n     Escreva o cliente: ");
	break;

	case 23:
	System.out.print("\n");
	System.out.print("\n      ________________________________________");
	System.out.print("\n     /                                        \\");
	System.out.print("\n     | Escolha do Produto                     |");
	System.out.print("\n     |--------------------                    |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     |  Forneça o produto                     |");
	System.out.print("\n     |                                        |");
	System.out.print("\n     \\________________________________________/");
	System.out.print("\n");
	System.out.print("\n     Escreva o produto: ");
	break;
     
    }

}

    public static void apresentaResultadosValidacao(
            
                                           //bufferedReader já inicializado para input do terminal
                                           BufferedReader br,
                                          //linhas lidas
                                          int lidas, int totais,
            
                                          // Nome do ficheiro lido
                                          String ficheiro,
            
                                          // tempo da leitura do ficheiro de vendas
                                          double tempo_leitura,
            
                                          // contadores
                                          int produtos_invalidos,
                                          int precos_invalidos,
                                          int quantidades_invalidas,
                                          int regimes_invalidos,
                                          int clientes_invalidos,
                                          int meses_invalidos,
                                          int filiais_invalidas,
                                          int precos_zero
                        ){
        
        System.out.println("");
        System.out.print("\n      ______________________________________LEITURA DAS VENDAS______________________________________");
        System.out.print("\n     /                                                                                              \\");
        System.out.print("\n     |_______________________________________________________________________________________________|");
	System.out.print("\n     | Ficheiro lido:       "+ficheiro);
	System.out.print("\n     | ---------------------------------------------------------------------------------------------");
        System.out.print("\n     | Tempo de Leitura:    "+(tempo_leitura*1000)+"ms / "+tempo_leitura+"s");
        System.out.print("\n     | ---------------------------------------------------------------------------------------------");
        System.out.print("\n     | Tempo por Inserção:  "+((tempo_leitura*1000)/lidas)+"ms / "+(tempo_leitura/lidas)+"s");
        System.out.print("\n     | ---------------------------------------------------------------------------------------------");
        System.out.print("\n     | Linhas válidas:   "+lidas+" de "+totais);
        System.out.print("\n     | ---------------------------------------------------------------------------------------------");
	System.out.print("\n     | Linhas com Produtos inválidos:         "+produtos_invalidos);
	System.out.print("\n     | Linhas com Preços inválidos:           "+precos_invalidos);
        System.out.print("\n     | Linhas com Quantidades inválidas:      "+quantidades_invalidas);
	System.out.print("\n     | Linhas com Regimes inválidos:          "+regimes_invalidos);
	System.out.print("\n     | Linhas com Clientes inválidos:         "+clientes_invalidos);
	System.out.print("\n     | Linhas com Meses inválidos:            "+meses_invalidos);
	System.out.print("\n     | Linhas com Filiais inválidas:          "+filiais_invalidas);
        System.out.print("\n     | Linhas válidas com Preços com valor 0: "+precos_zero);
	System.out.print("\n     |_______________________________________________________________________________________________|");
	System.out.print("\n     |                                                                                               |");
	System.out.print("\n     |                                     Prima Enter para continuar                                |");
	System.out.print("\n     |                                     --------------------------                                |");
	System.out.print("\n     |                                                                                               |");
	System.out.print("\n     \\_______________________________________________________________________________________________/");
        RecebeInput.esperarEnter(br);
    }

    public static void apresentaEstatisticas(
            
                                          //bufferedReader já inicializado para input do terminal
                                          BufferedReader br,
                                          //linhas lidas
                                          int lidas, int totais,
            
                                          // Nome do ficheiro lido
                                          String ficheiro,
            
                                          // tempo da leitura do ficheiro de vendas
                                          double tempo_leitura,
            
                                          // contadores
                                          int produtos_invalidos,
                                          int precos_invalidos,
                                          int quantidades_invalidas,
                                          int regimes_invalidos,
                                          int clientes_invalidos,
                                          int meses_invalidos,
                                          int filiais_invalidas,
                                          int precos_zero,

                                          int total_clis,  int clis_compradores, int clis_nao_compradores,
                                          int total_prods, int prods_comprados,  int prods_nao_comprados,
                                          double faturacao_total
    ){
        System.out.println("");
        System.out.print("\n      ______________________________________LEITURA DAS VENDAS______________________________________");
        System.out.print("\n     /                                                                                              \\");
        System.out.print("\n     |______________________________________________________________________________________________|");
	System.out.print("\n     | Ficheiro lido:       "+ficheiro);
	System.out.print("\n     | --------------------------------------------------------------------------------------------");
        System.out.print("\n     | Tempo de Leitura:    "+(tempo_leitura*1000)+"ms / "+tempo_leitura+"s");
        System.out.print("\n     | --------------------------------------------------------------------------------------------");
        System.out.print("\n     | Tempo por Inserção:  "+((tempo_leitura*1000)/lidas)+"ms / "+(tempo_leitura/lidas)+"s");
        System.out.print("\n     | --------------------------------------------------------------------------------------------");
        System.out.print("\n     | Linhas válidas:   "+lidas+" de "+totais);
        System.out.print("\n     | --------------------------------------------------------------------------------------------");
	System.out.print("\n     | Linhas com Produtos inválidos:         "+produtos_invalidos);
	System.out.print("\n     | Linhas com Preços inválidos:           "+precos_invalidos);
        System.out.print("\n     | Linhas com Quantidades inválidas:      "+quantidades_invalidas);
	System.out.print("\n     | Linhas com Regimes inválidos:          "+regimes_invalidos);
	System.out.print("\n     | Linhas com Clientes inválidos:         "+clientes_invalidos);
	System.out.print("\n     | Linhas com Meses inválidos:            "+meses_invalidos);
	System.out.print("\n     | Linhas com Filiais inválidas:          "+filiais_invalidas);
        System.out.print("\n     | Linhas válidas com Preços com valor 0: "+precos_zero);
        System.out.print("\n     | -------------------------------------------------------------------------------------------- |");
        System.out.print("\n     | Clientes | Total: "+total_clis+"; Compradores: "+clis_compradores+"; Não compradores: "+clis_nao_compradores);
        System.out.print("\n     | Produtos | Total: "+total_prods+"; Comprados: "+prods_comprados+"; Não comprados: "+prods_nao_comprados);
        System.out.print("\n     | -------------------------------------------------------------------------------------------- |");
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        System.out.print("\n     |                               FATURACAO TOTAL: "+df.format(faturacao_total));
	System.out.print("\n     |______________________________________________________________________________________________|");
	System.out.print("\n     |                                                                                              |");
	System.out.print("\n     |                                     Prima Enter para continuar                               |");
	System.out.print("\n     |                                     --------------------------                               |");
	System.out.print("\n     |                                                                                              |");
	System.out.print("\n     \\______________________________________________________________________________________________/");
        RecebeInput.esperarEnter(br);
    }
    
    /**
     *
     * @param br
     * @param ficheiro
     * @param totais
     * @param insercoes
     * @param tempo
     */
    public static void apresentaResultadosLeitura(BufferedReader br, String ficheiro, int totais, int insercoes, double tempo){ 
        System.out.print("      _____________________________________LEITURA DO CATALOGO______________________________________\n");
        System.out.print("     /                                                                                              \\\n");
        System.out.print("     |_______________________________________________________________________________________________|\n");
	System.out.print("     | Ficheiro utilizado:              "+ficheiro+"\n");
	System.out.print("     | ---------------------------------------------------------------------------------------------\n");
   	System.out.print("     | Total de elementos inseridos:    "+insercoes+" de "+totais+"\n");
	System.out.print("     | ---------------------------------------------------------------------------------------------\n");
	System.out.print("     | Tempo total a inserir elementos: "+(tempo*1000)+"ms / "+tempo+"s\n");
	System.out.print("     | ---------------------------------------------------------------------------------------------\n");
	System.out.print("     | Tempo médio por inserção:        "+((tempo*1000)/insercoes)+"ms / "+(tempo/insercoes)+"s\n");
	System.out.print("     |_______________________________________________________________________________________________|\n");
	System.out.print("     |                                                                                               |\n");
	System.out.print("     |                                     Prima Enter para continuar                                |\n");
	System.out.print("     |                                     --------------------------                                |\n");
	System.out.print("     |                                                                                               |\n");
	System.out.print("     \\_______________________________________________________________________________________________/\n");
	System.out.println("");
	RecebeInput.esperarEnter(br);
    }
    
    public static void apresentaDados(BufferedReader br, Collection<DadosGlobaisMeses> c){
        
        int filial_atual = 1;
        int totalQ, totalV, totalC;
        double totalF = 0.0;
        totalQ = totalV = totalC = 0;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        
        System.out.print("      ______________________________________________DADOS GLOBAIS_____________________________________________\n");
        System.out.print("     /                                                                                                        \\\n");
        for(DadosGlobaisMeses dgm : c){
            System.out.print("     |________________________________________________FILIAL "+filial_atual+"________________________________________________\n");
            System.out.print("     |\n");
            filial_atual++;
            for (int mes = 1; mes<=ValoresFixos.N_MESES; mes++){
                System.out.print("     | Mês "+mes+" - Quantidade: "+dgm.getQuantidadeMes(mes)+"\t| Vendas: "+dgm.getVendasMes(mes)+"\t| Faturacao: "+df.format(dgm.getFaturacaoMes(mes))+"\t| Compradores distintos: "+dgm.getClientesMes(mes)+"\n");
            }
            totalQ+=dgm.getQuantidadeTotal(); totalV+=dgm.getVendasTotal(); totalC+=dgm.getClientesTotal(); totalF+=dgm.getFaturacaoTotal();
            System.out.print("     | TOTAL - Quantidade: "+dgm.getQuantidadeTotal()+"\t| Vendas: "+dgm.getVendasTotal()+"| Faturacao: "+df.format(dgm.getFaturacaoTotal())+"\t| Compradores distintos: "+dgm.getClientesTotal()+"\n");
        }
        System.out.print("     |_________________________________________________________________________________________________________\n");
        System.out.print("     |\n");
        System.out.print("     | TOTAL GLOBAL - Quantidade: "+totalQ+" | Vendas: "+totalV+" | Faturacao: "+df.format(totalF)+"\n");
	System.out.print("     |________________________________________________________________________________________________________\n");
	System.out.print("     |                                                                                                        |\n");
	System.out.print("     |                                              Prima Enter para continuar                                |\n");
	System.out.print("     |                                              --------------------------                                |\n");
	System.out.print("     |                                                                                                        |\n");
	System.out.print("     \\________________________________________________________________________________________________________/\n");
	System.out.println("");
	RecebeInput.esperarEnter(br);
    }
    
    public static void apresentaDadosMensaisCliente(BufferedReader br, String cliente,  Collection<Map<Integer, TriploQuantidadeVendasFaturacao>> lista){
                
        int filial_atual = 1;
        int quantidade, comprados;
        double faturacao;
        int totalQ, totalV;
        double totalF = 0.0;
        totalQ = totalV = 0;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        
        System.out.print("      ______________________________________________DADOS GLOBAIS_____________________________________________\n");
        System.out.print("     /                                                                                                        \\\n");
        System.out.println("     | Dados Mensais do Cliente: "+cliente);
        for(Map<Integer, TriploQuantidadeVendasFaturacao> dgm : lista){
            System.out.print("     |________________________________________________FILIAL "+filial_atual+"________________________________________________\n");
            System.out.print("     |\n");
            filial_atual++;
            int    qfilialT = 0;
            int    vfilialT = 0;
            double ffilialT = 0;
            for (int mes = 1; mes<=ValoresFixos.N_MESES; mes++){
                if(dgm.containsKey(mes)){
                    TriploQuantidadeVendasFaturacao tqvf = dgm.get(mes);
                    quantidade = tqvf.getQuantidade();
                    faturacao  = tqvf.getFaturacao();
                    comprados  = tqvf.getVendas();
                } else{
                    quantidade = 0;
                    faturacao  = 0.0;
                    comprados  = 0;
                }
                qfilialT += quantidade;
                vfilialT += comprados;
                ffilialT += faturacao;
                System.out.print("     | Mês "+mes+" - Quantidade: "+quantidade+"\t| Produtos Distintos Comprados: "+comprados+"\t| Faturacao: "+df.format(faturacao)+"\n");
            }
            totalQ+=qfilialT; 
            totalF+=ffilialT;
            System.out.print("     | TOTAL - Quantidade: "+qfilialT+"\t| Produtos Distintos Comprados: "+vfilialT+"\t| Faturacao: "+df.format(ffilialT)+"\n");
        }
        System.out.print("     |_________________________________________________________________________________________________________\n");
        System.out.print("     |\n");
        System.out.print("     | TOTAL GLOBAL - Quantidade: "+totalQ+" | Faturacao: "+df.format(totalF)+"\n");
	System.out.print("     |________________________________________________________________________________________________________\n");
	System.out.print("     |                                                                                                        |\n");
	System.out.print("     |                                              Prima Enter para continuar                                |\n");
	System.out.print("     |                                              --------------------------                                |\n");
	System.out.print("     |                                                                                                        |\n");
	System.out.print("     \\________________________________________________________________________________________________________/\n");
	System.out.println("");
	RecebeInput.esperarEnter(br);
    }

    public static void apresentaDadosMensaisProduto(BufferedReader br, String produto,  Collection<Map<Integer, TriploQuantidadeVendasFaturacao>> lista){
                
        int filial_atual = 1;
        int quantidade, comprados;
        double faturacao;
        int totalQ, totalV;
        double totalF = 0.0;
        totalQ = totalV = 0;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        
        System.out.print("      ______________________________________________DADOS GLOBAIS_____________________________________________\n");
        System.out.print("     /                                                                                                        \\\n");
        System.out.println("     | Dados Mensais do Produto: "+produto);
        for(Map<Integer, TriploQuantidadeVendasFaturacao> dgm : lista){
            System.out.print("     |________________________________________________FILIAL "+filial_atual+"________________________________________________\n");
            System.out.print("     |\n");
            filial_atual++;
            int    qfilialT = 0;
            int    vfilialT = 0;
            double ffilialT = 0;
            for (int mes = 1; mes<=ValoresFixos.N_MESES; mes++){
                if(dgm.containsKey(mes)){
                    TriploQuantidadeVendasFaturacao tqvf = dgm.get(mes);
                    quantidade = tqvf.getQuantidade();
                    faturacao  = tqvf.getFaturacao();
                    comprados  = tqvf.getVendas();
                } else{
                    quantidade = 0;
                    faturacao  = 0.0;
                    comprados  = 0;
                }
                qfilialT += quantidade;
                vfilialT += comprados;
                ffilialT += faturacao;
                System.out.print("     | Mês "+mes+" - Quantidade: "+quantidade+"\t| Compradores Distintos: "+comprados+"\t| Faturacao: "+df.format(faturacao)+"\n");
            }
            totalQ+=qfilialT; 
            totalF+=ffilialT;
            System.out.print("     | TOTAL - Quantidade: "+qfilialT+"\t| Compradores Distintos: "+vfilialT+"\t| Faturacao: "+df.format(ffilialT)+"\n");
        }
        System.out.print("     |_________________________________________________________________________________________________________\n");
        System.out.print("     |\n");
        System.out.print("     | TOTAL GLOBAL - Quantidade: "+totalQ+" | Faturacao: "+df.format(totalF)+"\n");
	System.out.print("     |________________________________________________________________________________________________________\n");
	System.out.print("     |                                                                                                        |\n");
	System.out.print("     |                                              Prima Enter para continuar                                |\n");
	System.out.print("     |                                              --------------------------                                |\n");
	System.out.print("     |                                                                                                        |\n");
	System.out.print("     \\________________________________________________________________________________________________________/\n");
	System.out.println("");
	RecebeInput.esperarEnter(br);
    }
    
    public static void apresentaDados(BufferedReader br, DuploVendasClientes dvc) {
	System.out.print("\n");
	System.out.print("\n      _________________DADOS MÊS__________________");
	System.out.print("\n     /                                            \\");
	System.out.print("\n     | Dados Globais Mês:                         |");
	System.out.print("\n     |-------------------                         |");
	System.out.print("\n     |                                            |");
	System.out.print("\n     | ------------------------------------------ |");
	System.out.print("\n     |  Vendas Realizadas:     "+dvc.getVendas());
        System.out.print("\n     |  Compradores Distintos: "+dvc.getClientes());
	System.out.print("\n     | ------------------------------------------ |");
	System.out.print("\n     |                                            |");
	System.out.print("\n     |         Prima Enter para continuar         |");
	System.out.print("\n     |         --------------------------         |");
	System.out.print("\n     |                                            |");
	System.out.print("\n     \\____________________________________________/");
	System.out.println("");
        RecebeInput.esperarEnter(br);
    }
    
    
    public static void apresenta(BufferedReader br, Apresentacao a) throws PaginaInexistenteException{
      
	/*Imprimir instruções*/
	menu(br,2);

        int z = a.getNpaginas();
        int w = a.getTotal();
        int i = 1;
        int r = 1;
	int y = a.getNlinhasPpagina();
        
        while(true){

            System.out.print("\n      _____________________");
            System.out.print("\n     /                     \\");
            System.out.print("\n     | PÁGINA "+i+" de "+z);
            System.out.print("\n     | --------------------");
            System.out.print("\n     | TOTAL "+w);
            System.out.print("\n     \\_____________________/");
            System.out.print("\n     /                     \\\n");

            for(String s : a.getPagina(i-1)){
                System.out.println("     | "+r+" - "+s);
                r++;
            }

            System.out.println("     \\_____________________/");
                
            String opcao;
            try {
                opcao = RecebeInput.lerLinha(br);
            } catch (GoBackException ex) {
                System.out.println("\nA voltar ao Menu Principal.."); 
		return;	
            }

            if (opcao==null || opcao.length()==0){
                System.out.println("ERRO: Inseriu uma opção inválida para a apresentacao.");
                System.out.println("Escreva 'h' para consultar as intruções.");
                System.out.println("A voltar à página "+i);
                System.out.println("Por favor tente novamente.");
                System.out.println("");
                if (i==z){
                    r-=a.getPagina(i-1).size();
                    continue;
                }
                r-=y;
                continue;
            }
            
            switch(opcao.toLowerCase().charAt(0)) {

		case 'p' :
                    int p = Integer.parseInt(opcao.substring(1));
                    if(p>0 && p<=z){
			i = p;
			r = y*i-(y-1);
			break;
		    }
		    System.out.println("\nERRO: Inseriu uma página inválida: "+opcao);
	            System.out.println("A voltar à página "+i);
		    r-=y;
		    break;

		case '>' :
		    if (i==z) return;
		    i++;
		    break;

		case '<' :
		    if (i<=1) {
                        if (r<=ValoresFixos.N_ELEM_POR_PAG+1) {
                            r=1;
                            break;
                        }
                        r-=y*2;
                        break;
		    }
                    if (i==z){
                        r-=a.getPagina(i-1).size()+ValoresFixos.N_ELEM_POR_PAG;
                        i--;
                        break;
                    }
		    i--;
		    r-=y*2;
		    break;		
		

		case 'h' :
		    Menu.menu(br,2);
		    r-=y;
		    break;			


		default :
                    System.out.println("ERRO: Inseriu uma opção inválida para a apresentacao.");
                    System.out.println("Escreva 'h' para consultar as intruções.");
                    System.out.println("A voltar à página "+i);
                    System.out.println("Por favor tente novamente.");
                    System.out.println("");
                    if (i==z) r-=a.getPagina(i-1).size();
                    else r-=y;
            }
	}
    }
}
