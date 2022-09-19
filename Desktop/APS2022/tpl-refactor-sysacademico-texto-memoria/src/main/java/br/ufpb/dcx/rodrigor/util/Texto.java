package br.ufpb.dcx.rodrigor.util;

import java.util.Arrays;
import java.util.Scanner;

public class Texto {

    private static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {
        printCabecalho("Sistema Controle Acadêmico",30);



    }


    public static String selecionarOpcao(String[] opcoes){
        return selecionarOpcao("Escolha uma opção ",opcoes);
    }

    public static String selecionarOpcao(String texto,String[] opcoes){
        String textoCompleto = texto+Arrays.toString(opcoes)+": ";
        boolean repete = true;
        String selecao = null;
        while(repete) {
            System.out.print(textoCompleto);
            String entrada = in.nextLine();
            if (Arrays.asList(opcoes).contains(entrada)){
                selecao = entrada;
                repete = false;
            } else{
                System.out.println("Opção inválida! "+entrada+"\n");
            }
        }
        return selecao;

    }

    public static void printMargem(int margem,String texto){
        System.out.println(espacos(margem)+texto);
    }

    public static String espacos(int n){
        return " ".repeat(n);
    }

    public static void printLinhasBranco(int n){
        if(n <=0) throw new IllegalArgumentException("n tem que ser maior ou igual a zero");
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }


    public static int readInt(String texto) {
        try {
            return Integer.parseInt(readString(texto));
        }catch (NumberFormatException e){
            return -1;
        }
    }
    public static String readString(String texto){
        System.out.print(texto+"");
        return in.nextLine();
    }

    public static boolean confirmaSN(String texto){
        boolean repete = true;
        boolean confirma = true;
        while(repete) {
            System.out.print(texto + "(s/n) ");
            String entrada = in.nextLine();
            if (entrada.equalsIgnoreCase("s") | entrada.equalsIgnoreCase("n")){
                confirma = entrada.equalsIgnoreCase("s")?true:false;
                repete = false;
            } else{
                System.out.println("Entrada inválida! "+entrada+"\n");
            }
        }
        return confirma;
    }


    public static void printCabecalho(String cabecalho, int tam){
        if(tam < cabecalho.length())
            throw new IllegalArgumentException("tam tem que ser >= ao tamanho do cabeçalho: tam:"+tam+" cabecalho: "+cabecalho.length());
        // --------------
        // |  cabecalho |
        // --------------
        printLine(tam);
        System.out.println(centraliza(cabecalho,tam));
        printLine(tam);
    }

    public static void printLine(int length){
        StringBuffer line = new StringBuffer();
        for(int i = 0; i<length; i++){
            line.append('-');
        }
        System.out.println(line);
    }

    public static String centraliza(String texto, int tamanho){
        boolean tamPar = ((texto.length()+tamanho) % 2) == 0;

        StringBuffer linha = new StringBuffer();
        int margemEsq = (tamanho/2) - (texto.length()/2);
        int margemDir = tamPar ? margemEsq : margemEsq-1;
        for (int i = 0; i < margemEsq; i++) {
            linha.append(' ');
        }
        linha.append(texto);
        for (int i = 0; i < margemDir; i++) {
            linha.append(' ');
        }
        return linha.toString();
    }



}
