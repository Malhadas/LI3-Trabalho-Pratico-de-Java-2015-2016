/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package li3_1516.Interface.AuxiliaresInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import li3_1516.Exceptions.GoBackException;

/**
 * Classe usada para qualquer tipo de entrada de Input.
 * Seja essa entrada via terminal ou via ficheiro.
 * 
 * @author  Grupo 80
 * @version 2016
 */
public class RecebeInput implements Cloneable, Serializable{
    
public static String lerString() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     String txt = "";
     while(!ok) {
         try {
             txt = input.nextLine();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Texto Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return txt;
  } 

 
 public static int lerInt() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     int i = 0; 
     while(!ok) {
         try {
             i = input.nextInt();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Inteiro Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return i;
  } 
  
  public static double lerDouble() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     double d = 0.0; 
     while(!ok) {
         try {
             d = input.nextDouble();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Valor real Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return d;
  }  
  
   public static float lerFloat() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     float f = 0.0f; 
     while(!ok) {
         try {
             f = input.nextFloat();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Valor real Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return f;
  }  
  
   public static boolean lerBoolean() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     boolean b = false; 
     while(!ok) {
         try {
             b = input.nextBoolean();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Booleano Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return b;
  } 
  
  public static short lerShort() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     short s = 0; 
     while(!ok) {
         try {
             s = input.nextShort();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Short Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return s;
  }  
    
    public static String lerLinha(BufferedReader br) throws GoBackException{
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(RecebeInput.class.getName()).log(Level.SEVERE, null, ex);
            throw new GoBackException();
        }
        
        if (input!=null){
            if(input.equals("<-"))
                throw new GoBackException();
        }
        
        return input;
    }

    public static int lerInt(BufferedReader br) throws GoBackException{
        String input;
        int numero = 0;
        try {
            input  = br.readLine();
            numero = Integer.parseInt(input);
        } catch (NumberFormatException | IOException ex) {
            System.out.println("ERRO: Não escreveu um número.");
            throw new GoBackException();
        }
    
        return numero;
    }

    public static double lerDouble(BufferedReader br) throws GoBackException{
        String input;
        double numero = 0.0;
        try {
            input  = br.readLine();
            numero = Double.parseDouble(input);
        } catch (NumberFormatException | IOException ex) {
            System.out.println("ERRO: Não escreveu um número.");
            throw new GoBackException();
        }
    
        return numero;
    }
    
    public static void esperarEnter(BufferedReader br) {
        try{
            br.readLine();
        } catch (IOException ioe){
            Logger.getLogger(RecebeInput.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }

   /**
    * Método da autoria dos docentes da disciplina de laboratórios de Informática 3.
    * fornecido aos alunos para utilização no prejeto 'GereVendas' no ano letivo 2015/2016.
    * O método é utilizado para a leitura de ficheiros com recurso a um BufferedReader.
    * Este método é independente da origem do ficheiro de texto utilizado.
    * 
    * @param fich
    * @return Devolve um ArrayList de linhas, sendo essas linhas as linhas do ficheiro lido e do tipo String
    */
   public static ArrayList<String> readLinesWithBuff(String fich) {
        ArrayList<String> linhas = new ArrayList<>();
        BufferedReader inStream; 
        String linha;
        try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null )
				              linhas.add(linha);
        }
        catch(IOException e) { 
            out.println(e.getMessage()); return null; 
        }
        
        return linhas;  
   }

   /**
    * Método da autoria dos docentes da disciplina de laboratórios de Informática 3.
    * fornecido aos alunos para utilização no prejeto 'GereVendas' no ano letivo 2015/2016.
    * O método é utilizado para a leitura de ficheiros com recurso a um Scanner.
    * Este método é independente da origem do ficheiro de texto utilizado.
    * 
    * @param ficheiro
    * @return Devolve um ArrayList de linhas, sendo essas linhas as linhas do ficheiro lido e do tipo String 
    */
    public static ArrayList<String> readLinesArrayWithScanner(String ficheiro) {
        ArrayList<String> linhas = new ArrayList<>();
        Scanner scanFile;
        scanFile = null;
        try {
            scanFile = new Scanner(new FileReader(ficheiro));
            scanFile.useDelimiter("\n\r");
            while(scanFile.hasNext())
            linhas.add(scanFile.nextLine());
        } catch(IOException ioExc){ 
            out.println(ioExc.getMessage()); 
            return null; 
        } finally { 
            if(scanFile != null) scanFile.close(); 
        }
        
        return linhas;
 } 

    
    
}
