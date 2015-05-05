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

import java.util.InputMismatchException;

/**
 * Classe para validação de CPF.
 *
 * @author Luana Nascimento
 */
public class Cpf {

    /**
     * Método verificador de CPF.
     * 
     * @param cpf String com o CPF, apenas os números
     * @return true para CPF existente, false para inesistente
     */
    public static boolean isCpf(String cpf) {
        boolean retorno = false;

        // considera-se erro cpf's formados por uma sequencia de numeros iguais 
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999") || (cpf.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int) 
        try {
            // Calculo do 1o. Digito Verificador 
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do cpf em um numero: 
                // por exemplo, transforma o caractere '0' no inteiro 0 
                // (48 eh a posicao de '0' na tabela ASCII) 
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }
            // converte no respectivo caractere numerico 
            // Calculo do 2o. Digito Verificador 
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }
            // Verifica se os digitos calculados conferem com os digitos informados. 
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                retorno = true;
            } else {
                retorno = false;
            }
        } catch (InputMismatchException erro) {
            retorno = false;
        }

        return retorno;
    }

    /**
     * Métoodo que retorna um CPF formatado (com todos os - e .) a partir
     * de uma String apenas com seus números
     * 
     * @param cpf String com o cpf, somente números
     * @return CPF formatado em 000-000-000-00
     */
    public static String imprimeCpf(String cpf) {
        return(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
    }
}
