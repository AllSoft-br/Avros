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
package br.com.allsoft.avros.relatorios;

import br.com.allsoft.avros.factory.ConexaoMySQL;
import br.com.allsoft.avros.interfaces.FrmPrincipal;
import java.awt.Dimension;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe para auxiliar a utilização do Jasper Reports.
 *
 * @author Luana Nascimento
 */
public class Relatorio {

    private Connection con = null;
    
    public void criaRelatorio(String cpf, int idSessao, String nomeJasper) throws SQLException, JRException {
        con = ConexaoMySQL.getConexaoMySQL();

        //Path to your .jasper file in your package
        URL reportName = getClass().getResource("/br/com/allsoft/avros/relatorios/" + nomeJasper + ".jasper");
                
        //Get a stream to read the file
        JasperReport url = (JasperReport) JRLoader.loadObject(reportName);

        try {
            HashMap hm = new HashMap();
            hm.put("cpf_cliente", cpf);
            hm.put("id_sessao", idSessao);
            
            //Fill the report with parameter, connection and the stream reader     
            JasperPrint jp = JasperFillManager.fillReport(url, hm, con);

            //Viewer for Relatorio
            JRViewer jv = new JRViewer(jp);
            
            //Insert viewer to a JFrame to make it showable
            JInternalFrame jf = new JInternalFrame();
            FrmPrincipal.addFrame(jf);
            jf.getContentPane().add(jv);
            jf.validate();
            jf.setVisible(true);
            jf.setSize(new Dimension(800, 600));
            jf.setLocation(300, 100);
            jf.setClosable(true);
            jf.setIconifiable(true);
            
        } catch (JRException ex) {
            System.out.println(ex);
            con.close();
        } finally {
            con.close();
        }
    }

    public static void main(String[] args) throws SQLException, JRException {
        Relatorio ma = new Relatorio();
        ma.criaRelatorio("99547502743", 1, "sessaoAgend");
    }
}
