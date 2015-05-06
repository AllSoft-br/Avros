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
import br.com.allsoft.avros.dao.RepresentanteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe para consultas em views
 * 
 * @author Luana Nascimento
 */
public class JDBCViews {
    //Variáveis
    static Connection con = null;
    static String nomeTabela;

    /**
     * Retorna o ID do representante e o grau de parentesco num objeto
     * RepresentanteDAO
     *
     * @param menor ClienteDAO com o menor
     * @return RepresentanteDAO com apenas o ID e o grau setados
     */
    public static RepresentanteDAO parentesco(ClienteDAO menor) throws SQLException {
        RepresentanteDAO representante = new RepresentanteDAO();
        JDBCConsulta.con = ConexaoMySQL.getConexaoMySQL();
        JDBCConsulta.nomeTabela = ClsBD.getViewParente();
        int idCli = menor.getId();
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select * from " + JDBCConsulta.nomeTabela + " where " + ClsBD.getCliId() + " = " + idCli);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            representante.setId(rs.getInt(ClsBD.getRepId()));
            representante.setGrau(ClsBD.getParTipo());
        }
        return representante;
    }
    
    public static RepresentanteDAO auditLogin24h(ClienteDAO menor) throws SQLException {
        RepresentanteDAO representante = new RepresentanteDAO();
        JDBCConsulta.con = ConexaoMySQL.getConexaoMySQL();
        JDBCConsulta.nomeTabela = ClsBD.getViewParente();
        int idCli = menor.getId();
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select * from " + JDBCConsulta.nomeTabela + " where " + ClsBD.getCliId() + " = " + idCli);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            representante.setId(rs.getInt(ClsBD.getRepId()));
            representante.setGrau(ClsBD.getParTipo());
        }
        return representante;
    }
}
