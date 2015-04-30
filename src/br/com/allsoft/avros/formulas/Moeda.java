/*
 * Copyright (C) 2015 Allsoft
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.allsoft.avros.formulas;

import br.com.allsoft.avros.exceptions.ValorInvalidoMoedaException;
import java.text.DecimalFormat;

/**
 * Classe para fazer cálculos envolvendo dinheiro.
 * 
 * @author Luana Nascimento
 */
public class Moeda {
    
    /**
     * Calcula a o preço das sessões a partir de uma String com o valor total.
     * 
     * @param valor String com o valor total do orçamento
     * @param sessoes número de sessões
     * @return valor de cada sessão no formato R$ 0,00
     * @throws ValorInvalidoMoedaException caso a String valor contenha
     * caracteres que não são números nem ' ', '.', ',', 'R', 'S' ou '$'
     */
    public static String calculaSessao(String valor, int sessoes) throws ValorInvalidoMoedaException{
        valor = valor.replace(",", ".");
        valor = valor.replace("R", "");
        valor = valor.replace("$", "");
        valor = valor.replace("S", "");
        valor = valor.replace(" ", "");
        
        if((!valor.matches(".*\\d.*")) && (!valor.matches("."))){
            throw new ValorInvalidoMoedaException();
        }
        
        //para poder armazenar o valor em forma de double,
        //trocamos as virgulas por ponto (padrão americano de moeda)
        double dvalor = Double.parseDouble(valor);

        //para facilitar a visualização do valor, trocamos
        //novamente os pontos por virgula (padrão brasileiro
        DecimalFormat df = new DecimalFormat("0.00");
        String sessao = df.format(dvalor / sessoes);
        sessao = sessao.replace(".", ",");

        return "R$ " + sessao;
    }
    
    /**
     * Calcula a o preço das sessões a partir de um double com o valor total.
     * 
     * @param valor double com o valor total do orçamento
     * @param sessoes número de sessões
     * @return valor de cada sessão no formato R$ 0,00
     */
    public static String calculaSessao(double valor, int sessoes){
        //para facilitar a visualização do valor, trocamos
        //os pontos por virgula (padrão brasileiro
        DecimalFormat df = new DecimalFormat("0.00");
        String sessao = df.format(valor / sessoes);
        sessao = sessao.replace(".", ",");

        return "R$ " + sessao;
    }
    
    /**
     * Converte um valor para o padrão brasileiro de moedas a
     * partir de um double.
     * 
     * @param valor double com o valor a ser convertido
     * @return valor no padrão brasileiro R$ 0,00
     */
    public static String padraoBr(double valor){
        //para facilitar a visualização do valor, trocamos
        //os pontos por virgula (padrão brasileiro
        DecimalFormat df = new DecimalFormat("0.00");
        String br = df.format(valor);
        br = br.replace(".", ",");

        return "R$ " + br;
    }
    
    /**
     * Pega uma String com um valor (com ou sem prefixo) e retorna
     * um valor no formato brasileiro de moeda.
     * @param valor String com o valor a ser convertido
     * @return valor no formato R$ 0,00
     * @throws ValorInvalidoMoedaException caso a String valor contenha
     * caracteres que não são números nem ' ', '.', ',', 'R', 'S' ou '$'
     */
    public static String padraoBr(String valor) throws ValorInvalidoMoedaException{
        valor = valor.replace(",", ".");
        valor = valor.replace("R", "");
        valor = valor.replace("$", "");
        valor = valor.replace("S", "");
        valor = valor.replace(" ", "");
        
        if((!valor.matches(".*\\d.*")) && (!valor.matches("."))){
            throw new ValorInvalidoMoedaException();
        }
        
        //para poder armazenar o valor em forma de double,
        //trocamos as virgulas por ponto (padrão americano de moeda)
        double dvalor = Double.parseDouble(valor);

        //para facilitar a visualização do valor, trocamos
        //novamente os pontos por virgula (padrão brasileiro
        DecimalFormat df = new DecimalFormat("0.00");
        String br = df.format(dvalor);
        br = br.replace(".", ",");

        return "R$ " + br;
    }
    
    /**
     * Converte um valor para o padrão brasileiro de moedas a
     * partir de um double.
     * 
     * @param valor double com o valor a ser convertido
     * @return valor no padrão brasileiro 0,00
     */
    public static String padraoVirgula(double valor){
        //para facilitar a visualização do valor, trocamos
        //os pontos por virgula (padrão brasileiro
        DecimalFormat df = new DecimalFormat("0.00");
        String br = df.format(valor);
        br = br.replace(".", ",");

        return br;
    }
    
    /**
     * Pega uma String com um valor (com ou sem prefixo) e retorna
     * um valor no formato brasileiro de moeda.
     * @param valor String com o valor a ser convertido
     * @return valor no formato 0,00
     * @throws ValorInvalidoMoedaException caso a String valor contenha
     * caracteres que não são números nem ' ', '.', ',', 'R', 'S' ou '$'
     */
    public static String padraoVirgula(String valor) throws ValorInvalidoMoedaException{
        valor = valor.replace(",", ".");
        valor = valor.replace("R", "");
        valor = valor.replace("$", "");
        valor = valor.replace("S", "");
        valor = valor.replace(" ", "");
        
        if((!valor.matches(".*\\d.*")) && (!valor.matches("."))){
            throw new ValorInvalidoMoedaException();
        }
        
        //para poder armazenar o valor em forma de double,
        //trocamos as virgulas por ponto (padrão americano de moeda)
        double dvalor = Double.parseDouble(valor);

        //para facilitar a visualização do valor, trocamos
        //novamente os pontos por virgula (padrão brasileiro
        DecimalFormat df = new DecimalFormat("0.00");
        String br = df.format(dvalor);
        br = br.replace(".", ",");

        return br;
    }
    
    /**
     * Método que toma como parâmetro um valor monetário no formato R$ 0,00 e
     * converte de volta para double
     * 
     * @param valor no formato R$ 0,00.
     * @return quantia representante em forma de double
     * @throws ValorInvalidoMoedaException caso o parametro possua caracteres diferentes de números,
     * 'R', 'S', '$', '.', ' ' e ','.
     */
    public static double retornaDouble(String valor) throws ValorInvalidoMoedaException{
        double retorno;
        
        valor = valor.replace(",", ".");
        valor = valor.replace("R", "");
        valor = valor.replace("$", "");
        valor = valor.replace("S", "");
        valor = valor.replace(" ", "");
        
        if((!valor.matches(".*\\d.*")) && (!valor.matches("."))){
            throw new ValorInvalidoMoedaException();
        }
        
        //para poder armazenar o valor em forma de double,
        //trocamos as virgulas por ponto (padrão americano de moeda)
        retorno = Double.parseDouble(valor);

        return retorno;
    }
}
