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
import br.com.allsoft.avros.exceptions.AuditoriaException;
import br.com.allsoft.avros.interfaces.FrmLogin;
import br.com.allsoft.avros.interfaces.IfrmBackup;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class SalvaBackup extends MySQLBackup implements Runnable {

    public void run(){
        IfrmBackup.lblCarregando.setVisible(true);
        
        String command = MySQLBackup.MYSQL_PATH + "mysqldump.exe";
        String[] databases = MySQLBackup.DATABASES.split(" ");
        for (int i = 0; i < databases.length; i++) {
            MySQLBackup.dbList.add(databases[i]);
        }
        System.out.println("Iniciando backups...\n\n");
        // Contador
        int i = 1;
        // Tempo
        long time1;
        long time2;
        long time;
        time1 = System.currentTimeMillis();
        for (String dbName : MySQLBackup.dbList) {
            ProcessBuilder pb = new ProcessBuilder(command, "--user=" + MySQLBackup.user, "--password=" + MySQLBackup.password, dbName, "--result-file=" + "." + MySQLBackup.SEPARATOR + "Backup" + MySQLBackup.SEPARATOR + dbName + ".sql");
            try {
                System.out.println("Backup do banco de dados (" + i + "): " + dbName + " ...");
                pb.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        time2 = System.currentTimeMillis();
        time = time2 - time1;
        System.out.println("\nBackups realizados com sucesso.\n\n");
        System.out.println("Tempo total de processamento: " + time + " ms\n");
        System.out.println("Finalizando...");
        try {
            AuditoriaLogin.salvaBackup(FrmLogin.usuario);
        } catch (AuditoriaException ex) {
            Logger.getLogger(SalvaBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        IfrmBackup.lblCarregando.setVisible(false);
    }
    
}
