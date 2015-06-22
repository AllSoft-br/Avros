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
package br.com.allsoft.avros.modelo;

import static br.com.allsoft.avros.dao.AuditoriaDelete.con;
import br.com.allsoft.avros.factory.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe que insere e busca dados da auditoria
 *
 * @author Luana
 */
public class JDBCAuditoria {

    //Variáveis obrigatórias
    protected static String tabela; //Nome da tabela alterada
    protected static String acao; //Update, insert, login ou delete
    protected static String descricao; //Descrição da ação ocorrida. Texto que ficará à mostra no histórico
    protected static String sql = "-"; //Código sql utilizado
    protected static String antes = "-"; //No caso de updates, como o campo era antes
    protected static String depois = "-"; //No caso de updates, como o campo ficou no fim
    protected static String campo = "-"; //Campo alterado
    protected static int codDado; //ID do dado alterado
    protected static int idLogin; //ID do login que fez a alteração

    protected static Connection con;

    protected static void insereRegistro() throws SQLException {
        con = ConexaoMySQL.getConexaoMySQL();
        
        String query = "call insere_registro(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, tabela);
        stmt.setInt(2, codDado);
        stmt.setString(3, acao);
        stmt.setString(4, descricao);
        stmt.setInt(5, idLogin);
        stmt.setString(6, sql);
        stmt.setString(7, antes);
        stmt.setString(8, depois);
        stmt.setString(9, campo);

        stmt.execute();
        stmt.close();
        con.close();
    }
}
