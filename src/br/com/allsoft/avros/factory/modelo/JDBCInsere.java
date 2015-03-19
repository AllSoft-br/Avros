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

package br.com.allsoft.avros.factory.modelo;

import br.com.allsoft.avros.factory.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe que insere valores no banco de dados.
 * 
 * @author Luana
 */
public class JDBCInsere {
    static Connection con = null;
    static String nomeTabela;
    
    /**
     * Método que insere um novo cliente no banco de dados.
     * 
     * @param cliente objeto do tipo ClienteDAO com informações do cliente a ser inserido.
     * @throws SQLException 
     */
    public static void inserirCliente(ClienteDAO cliente) throws SQLException{
        nomeTabela = "cliente";
        
        try {
            con = ConexaoMySQL.getConexaoMySQL();
            String sql = "insert into " + nomeTabela
                    + "(nome, RG, data_nasc, tel) "
                    + "values (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            // preenche os valores
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getRg());
            stmt.setDate(3, cliente.getNascimento());
            stmt.setString(4, cliente.getTel());
            
            stmt.execute();
            stmt.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.close();
        }
    }
}
