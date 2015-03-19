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
import javax.swing.JPanel;

/**
 * Classe que permite que um jDesktopPane possua uma imagem como background.
 *
 * @author Luana
 */
public class ClsPanelComImagem extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image img;
    boolean centraliza;

    /**
     * Método construtor. Você deve utilizá-lo para inserir imagens no
     * background de um jPane.
     *
     * @param caminho caminho da imagem no projeto
     * @param centralizado se a imagem será horizontalmente centralizada
     */
    public ClsPanelComImagem(String caminho, boolean centralizado) {
        img = Toolkit.getDefaultToolkit().createImage(getClass().getResource(caminho));
        centraliza = centralizado;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE); // define a cor de fundo branco    
        g.fillRect(0, 0, getWidth(), getHeight()); // define a cor de fundo branco

        if (centraliza) {
            Dimension dimension = this.getSize();
            int x = (int) (dimension.getWidth() - img.getWidth(this)) / 2;
            g.drawImage(img, x, 0, null);
        } else {
            g.drawImage(img, 0, 0, null);
        }
    }
}
