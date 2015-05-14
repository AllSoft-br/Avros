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

package br.com.allsoft.avros.factory;

import br.com.allsoft.avros.dao.ClienteDAO;
import br.com.allsoft.avros.dao.ClsBD;
import br.com.allsoft.avros.dao.OrcamentoDAO;
import br.com.allsoft.avros.dao.RepresentanteDAO;
import br.com.allsoft.avros.dao.SessaoDAO;
import br.com.allsoft.avros.exceptions.AuditoriaException;
import br.com.allsoft.avros.interfaces.FrmLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para realizar os deletes do sistema
 * 
 * @author Luana Nascimento
 */
public class JDBCDelete {
    //Variáveis
    static Connection con = null;
    static String nomeTabela;
    
    //Métodos
    
    /**
     * Remove uma relação entre menor de idade e representante
     * @param idRepresentante
     * @param idCliente
     * @throws SQLException 
     */
    public static void removeRel(int idRepresentante, int idCliente) throws SQLException {
        nomeTabela = ClsBD.getTblRel();
        
        ClienteDAO cliente = JDBCConsulta.clienteId(idCliente);
        RepresentanteDAO representante = JDBCConsulta.representanteId(idRepresentante);
        
        con = ConexaoMySQL.getConexaoMySQL();
        
        String sql = "DELETE from " + nomeTabela + " where " + ClsBD.getRelRepresentanteId() + " = ?" + " and " + ClsBD.getRelClienteId() + " = ?";
        System.out.println(idRepresentante);
        System.out.println(idCliente);
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setInt(1, idRepresentante);
        stmt.setInt(2, idCliente);
        
        sql = stmt.toString();
        
        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaDelete.relRepMenor(FrmLogin.usuario, representante, cliente, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deleta uma sessao 
     * 
     * @param sessao com nome do cliente e id do orçamento setados
     * @throws SQLException 
     */
    public static void sessao(SessaoDAO sessao) throws SQLException {
        nomeTabela = ClsBD.getTblSessao();
        
        int id = sessao.getId();
        
        con = ConexaoMySQL.getConexaoMySQL();
        
        String sql = "DELETE from " + nomeTabela + " where " + ClsBD.getSesId()+ " = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setInt(1, id);
        
        sql = stmt.toString();
        
        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaDelete.sessao(FrmLogin.usuario, sessao, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Deleta uma sessao 
     * 
     * @param orcamento com nome do cliente setado
     * @throws SQLException 
     */
    public static void orcamento(OrcamentoDAO orcamento) throws SQLException {
        nomeTabela = ClsBD.getTblSessao();
        
        int id = orcamento.getId();
        
        con = ConexaoMySQL.getConexaoMySQL();
        
        String sql = "call del_orcamento(?)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setInt(1, id);
        
        sql = stmt.toString();
        
        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaDelete.orcamento(FrmLogin.usuario, orcamento, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
