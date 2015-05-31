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

import java.util.List;

/**
 * Classe que auxilia as tarefas repetitivas
 * das telas de consulta
 * 
 * @author Luana Nascimento
 */
public class Consulta {
    public static List excluiRepetidos(List lista){
        int qtos = lista.size();
        
        for(int i = 0; i < qtos; i++){
            Object ref = lista.get(i);
            
            for(int j = i; j < qtos; j++){
                if(ref.equals(lista.get(j))){
                    lista.remove(j);
                }
            }
        }
        
        return lista;
    }

    /**
     * Grifa uma palavra chave dentro de uma String
     *
     * @param chave palavra a ser procurada
     * @param base String onde a palavra será procurada
     * @return String grifada
     */
    public static String grifar(String chave, String base) {
        String grifado = base;
        if (chave.length() > 0) {
            String maiuscula = base.toUpperCase();
            boolean contem = maiuscula.contains(chave.toUpperCase());
            if (contem) {
                int start = maiuscula.indexOf(chave.toUpperCase());
                int end = start + chave.length();
                grifado = "<html>" + base.substring(0, start) + "<span style='background-color: #ffff00'>" + base.substring(start, end) + "</span>" + base.substring(end, base.length()) + "</html>";
            }
        }
        return grifado;
    }
}
