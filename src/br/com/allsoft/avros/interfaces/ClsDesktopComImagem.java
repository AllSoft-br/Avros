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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;

/**
 * Classe que permite que um jDesktopPane possua uma imagem em seu centro
 * 
 * @author Luana
 */
public class ClsDesktopComImagem extends JDesktopPane {

    private static final long serialVersionUID = 1L;
    private Image img;

    /**
     * Método construtor.
     * Você deve utilizá-lo para inserir imagens no background de um jDesktopPane.
     * 
     * @param caminho caminho da imagem no projeto
     */
    public ClsDesktopComImagem(String caminho) {
        img = Toolkit.getDefaultToolkit().getImage(getClass().getResource(caminho));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            Dimension dimension = this.getSize();
            int x = (int) (dimension.getWidth() - img.getWidth(this)) / 2;
            int y = (int) (dimension.getHeight() - img.getHeight(this)) / 2;

            g.setColor(ClsEstilo.formbg); // define a cor de fundo branco    
            g.fillRect(0, 0, getWidth(), getHeight()); // define a cor de fundo branco
            
            g.drawImage(img, x, y, img.getWidth(this), img.getHeight(this), this);
        } else {
            g.drawString("Imagem nao encontrada", 50, 50);
        }
        
    }
}
