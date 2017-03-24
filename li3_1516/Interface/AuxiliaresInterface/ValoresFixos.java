
package li3_1516.Interface.AuxiliaresInterface;

import java.io.Serializable;

/**
 * Como o java não tem um pré-compilador que permita a definição de macros (ao contrário da linguagem C) é necessário, se queremos definir
 * valores de constantes definir os mesmos como final e static. Static pois não queremos que seja possível instânciá-los e final pois não
 * queremos que o seu valor mude nunca.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class ValoresFixos implements Cloneable, Serializable{
    
    public static final int PRODUTO_TAMANHO = 6;
    public static final int CLIENTE_TAMANHO = 5;
    
    public static final double PRECO_MAX = 999.99; /*Maior preço possível de uma compra válida*/
    public static final double PRECO_MIN =   0.0; /*Menor peço possível de uma compra válida */

    public static final int QUANT_MAX = 200;  /*Quantidade máxima de produto comprado por compra válida*/
    public static final int QUANT_MIN =   0; /*Quantidade mínima de produto comprado por compra válida*/

    public static int N_FILIAIS = 3; /*Número máximo de filiais - Não é final para poder variar com o ficheiro de vendas inserido*/

    public static int N_MESES = 12; /* Número máximo de meses 
                                           * pode ser aumentado caso se queira guardar dados ao longo de vários anos
                                           * pode ser diminuído para um espaço de tempo mais pequeno
                                           * - Não é final para poder variar com o ficheiro de vendas inserido
                                           */
    
    public static int N_REGIMES = 2;
    
    public static final String REGIME_N = "N";
    public static final String REGIME_P = "P";
    
    //Inteiros para identificação dos módulos
    public static final int CAT_CLIENTES = 0;
    public static final int CAT_PRODUTOS = 1;
    public static final int FATURACAO    = 2;
    public static final int FILIAL       = 3;
    /////////////////////////////////////////
    
    //Valores Iniciais para os tamanhos dos modulos
    public static final int FATURACAO_TAMANHO_INICIAL         = 200000;
    public static final int CATALOGO_PRODUTOS_TAMANHO_INICIAL = 200000;
    public static final int CATALOGO_CLIENTES_TAMANHO_INICIAL = 20000;   
    public static final int FILIAL_PRODUTOS_TAMANHO_INICIAL   = 200000;   
    public static final int FILIAL_CLIENTES_TAMANHO_INICIAL   = 20000;      
    //////////////////////////////////////////////
    
    //APRESENTACAO
    public static final int N_ELEM_POR_PAG = 20;
   
}
