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
package br.com.allsoft.avros.backup;

import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que realiza o backup do banco de dados do programa
 *
 * @author Luana Nascimento
 */
public class MySQLBackup {

    // Constantes da classe
    protected static String VERSION = "1.0";
    protected static String SEPARATOR = File.separator;
    protected static String MYSQL_PATH
            = "C:" + SEPARATOR
            + "xampp" + SEPARATOR
            + "mysql" + SEPARATOR
            + "bin" + SEPARATOR;

    // Lista dos bancos de dados a serem "backupeados"; se desejar adicionar mais,
    // basta colocar o nome separado por espaços dos outros nomes
    protected static String DATABASES = "bd_estudio";

    protected static List<String> dbList = new ArrayList<String>();
    protected static String user = "root";
    protected static String password = "";



    public static void main(String[] args) {
        try {
            Runnable recuperar = new RecuperaBackup();
            new Thread(recuperar).start();
        } catch (Exception ex) {
            Logger.getLogger(MySQLBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
