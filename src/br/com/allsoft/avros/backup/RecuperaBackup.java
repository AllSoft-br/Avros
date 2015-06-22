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
import br.com.allsoft.avros.interfaces.IfrmBackup;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class RecuperaBackup extends MySQLBackup implements Runnable {

    @Override
    public void run() {
        try {
            IfrmBackup.lblCarregando.setVisible(true);
            
            Connection con = ConexaoMySQL.getConexaoMySQL();
            con.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(con, false, true);
            runner.runScript(new BufferedReader(new FileReader("backup/bd_estudio.sql")));
            con.commit();
            con.close();
            AuditoriaLogin.recuperaBackup(FrmLogin.usuario);
            
            IfrmBackup.lblCarregando.setVisible(false);
            
            JOptionPane.showMessageDialog(null, "Dados recuperados com êxito.");
        } catch (Exception ex) {
            try {
                IfrmBackup.lblCarregando.setVisible(true);
                
                String serverName = "localhost:3306";
                String url = "jdbc:mysql://" + serverName;
                String username = "root";
                String password = "";
                Logger.getLogger(MySQLBackup.class.getName()).log(Level.SEVERE, null, ex);
                Connection con = DriverManager.getConnection(url, username, password);
                Statement s = con.createStatement();
                int result = s.executeUpdate("create database bd_estudio");
                con.close();
                
                IfrmBackup.lblCarregando.setVisible(false);
            } catch (SQLException ex1) {
                Logger.getLogger(RecuperaBackup.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
    }
}
