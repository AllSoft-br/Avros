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
    public static Color formbg = new Color(255,255,255);
    public static Color formSombra = new Color(228,234,234);
    
    static Color labelCor = new Color(0,102,204);
    static Color linkCor = new Color(0,102,102);
    static Color textoCor = new Color(0,0,0);
    static Color textoInputCor = new Color(100,100,100);
    static Color tituloCor = new Color(0,195,217);
    static Color botaoCor = new Color(0,0,0);
    static Color labelDestaqueCor = new Color(0, 102, 102);
    static Color labelDinheiroCor = new Color(0,102,0);
    
    static Color tabelaBg = new Color(255, 255, 255);
    static Color tabelaGrid = new Color(255, 255, 255);
    static Color tabelaSelec = new Color(172,195,217);
    static Color tabelaTextoSelec = new Color(2,7,59);
    
    //Fontes
    static Font labelFonte = new java.awt.Font("Century Gothic", 1, 14);
    static Font labelMenuFonte = new java.awt.Font("Century Gothic", 0, 12);
    static Font linkFonte = new java.awt.Font("Century Gothic", 0, 14);
    static Font tituloFonte = new java.awt.Font("Century Gothic", 1, 24);
    static Font textoInputFonte = new java.awt.Font("Arial", 1, 14);
    static Font botaoFonte = new java.awt.Font("Century Gothic", 0, 14);
    static Font labelDestaqueFonte = new java.awt.Font("Century Gothic", 0, 18);
    
    //Strings - caminhos
    static String imgDesk = "/br/com/allsoft/avros/img/logo.png";
    
    public static int duplicateKeyError = 1062;
}
