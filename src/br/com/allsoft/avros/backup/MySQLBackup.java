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

import br.com.allsoft.avros.dao.AuditoriaLogin;
import br.com.allsoft.avros.factory.ConexaoMySQL;
import br.com.allsoft.avros.factory.ScriptRunner;
import br.com.allsoft.avros.interfaces.FrmLogin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
    private static String VERSION = "1.0";
    private static String SEPARATOR = File.separator;
    private static String MYSQL_PATH
            = "C:" + SEPARATOR
            + "xampp" + SEPARATOR
            + "mysql" + SEPARATOR
            + "bin" + SEPARATOR;

    // Lista dos bancos de dados a serem "backupeados"; se desejar adicionar mais,
    // basta colocar o nome separado por espaços dos outros nomes
    private static String DATABASES = "bd_estudio";

    private static List<String> dbList = new ArrayList<String>();
    private static String user = "root";
    private static String password = "";

    public static void salvaBackup() throws Exception {

        String command = MYSQL_PATH + "mysqldump.exe";
        String[] databases = DATABASES.split(" ");

        for (int i = 0; i < databases.length; i++) {
            dbList.add(databases[i]);
        }

        System.out.println("Iniciando backups...\n\n");

        // Contador
        int i = 1;

        // Tempo
        long time1, time2, time;

        // Início
        time1 = System.currentTimeMillis();

        for (String dbName : dbList) {
            ProcessBuilder pb = new ProcessBuilder(
                    command,
                    "--user=" + user,
                    "--password=" + password,
                    dbName,
                    "--result-file=" + "." + SEPARATOR + "Backup" + SEPARATOR + dbName + ".sql");

            try {
                System.out.println("Backup do banco de dados (" + i + "): " + dbName + " ...");
                pb.start();

            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e);
            }
            i++;
        }

        // Fim
        time2 = System.currentTimeMillis();

        // Tempo total da operação
        time = time2 - time1;

        // Avisa do sucesso
        System.out.println("\nBackups realizados com sucesso.\n\n");

        System.out.println("Tempo total de processamento: " + time + " ms\n");

        System.out.println("Finalizando...");

        AuditoriaLogin.salvaBackup(FrmLogin.usuario);
    }

    public static void recuperaBackup() throws IOException, Exception {
        

        try {
            Connection con = ConexaoMySQL.getConexaoMySQL();
            con.setAutoCommit(false);

            ScriptRunner runner = new ScriptRunner(con, false, true);
            runner.runScript(new BufferedReader(new FileReader("backup/bd_estudio.sql")));

            con.commit();
            con.close();
            AuditoriaLogin.recuperaBackup(FrmLogin.usuario);

        } catch (Exception ex) {
            String serverName = "localhost:3306"; //caminho do servidor do BD, ip da máquina do servido
            String url = "jdbc:mysql://" + serverName;
            String username = "root"; //nome de uduário de ser BD
            String password = ""; //sua senha de acesso

            Logger.getLogger(MySQLBackup.class.getName()).log(Level.SEVERE, null, ex);
            Connection con = DriverManager.getConnection(url, username, password);

            Statement s = con.createStatement();
            int result = s.executeUpdate("create database bd_estudio");

            con.close();
            throw new Exception("O sistema tentou recriar o banco de dados. Tente recuperar o backup novamente e contate"
                    + " os desenvolvedores do sistema o mais rápido possível.");

        }
    }

    public static void main(String[] args) {
        try {
            recuperaBackup();
        } catch (Exception ex) {
            Logger.getLogger(MySQLBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
