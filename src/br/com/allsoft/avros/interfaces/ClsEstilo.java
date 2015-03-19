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

package br.com.allsoft.avros.interfaces;

import java.awt.Color;
import java.awt.Font;

/**
 * Classe para seleção de cores e estilos do sistema
 * 
 * @author Luana
 * 
 */
public class ClsEstilo {
    //Cores
    static Color formbg = new Color(255,255,255);
    
    static Color labelCor = new Color(0,102,204);
    static Color linkCor = new Color(0,102,102);
    static Color textoCor = new Color(0,0,0);
    static Color textoInputCor = new Color(102,102,102);
    static Color tituloCor = new Color(0,195,217);
    static Color botaoCor = new Color(0,0,0);
    
    //Fontes
    static Font labelFonte = new java.awt.Font("Century Gothic", 1, 14);
    static Font labelMenuFonte = new java.awt.Font("Century Gothic", 0, 12);
    static Font linkFonte = new java.awt.Font("Century Gothic", 0, 14);
    static Font tituloFonte = new java.awt.Font("Century Gothic", 1, 24);
    static Font textoInputFonte = new java.awt.Font("Arial", 1, 14);
    static Font botaoFonte = new java.awt.Font("Century Gothic", 0, 14);
    
}
