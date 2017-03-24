
package Auxiliares_modulos.Compators;

import Auxiliares_modulos.TriploQuantidadeVendasFaturacao;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import li3_1516.Filial.ProdutoFilial;

/**
 * Esta classe implementa um Comparator e define o metodo compare
 * de acordo com os seguintes critérios:
 * 
 * O objecto a receber deve ser do tipo Map.Entry
 * 
 * Se o value do Map.Entry a comparar for uma instância de TriploQuantidadeVendasFaturacao ou
 * de ProdutoFilial então obtemos a quantidade de cada um e usamos essa quantidade
 * como critério de compraração. (Caso não seja instância de nenhuma destas classes então será 
 * utilizada uma quantidade com valor zero, sendo sempre iguais por esse critério).
 * 
 * Na situação em que a quantidade é igual, recorre-se ao método compareTo da classe String
 * e comparam-se as keys da Entry de acordo com esse critério.
 * 
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class ComparadorProdutoQuantidade implements Serializable, Comparator<Object>{

    @Override
    public int compare(Object o1, Object o2) {
            Map.Entry m1 = (Map.Entry) o1;
            Map.Entry m2 = (Map.Entry) o2;
            int quant1 = 0;
            int quant2 = 0;
            
            if(m1.getValue() instanceof TriploQuantidadeVendasFaturacao){
                quant1 = ((TriploQuantidadeVendasFaturacao)m1.getValue()).getQuantidade();
                quant2 = ((TriploQuantidadeVendasFaturacao)m2.getValue()).getQuantidade();
            }
            if(m1.getValue() instanceof ProdutoFilial){
                quant1 = ((ProdutoFilial)m1.getValue()).getQuantidade();
                quant2 = ((ProdutoFilial)m2.getValue()).getQuantidade();
            }
            
            if (quant1 < quant2) return 1;
            if (quant1 > quant2) return -1;
            
            return ((String)m1.getKey()).compareTo((String) m2.getKey());
    }
    
}
