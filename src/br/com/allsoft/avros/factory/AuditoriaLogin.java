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

import br.com.allsoft.avros.dao.ClsBD;
import br.com.allsoft.avros.dao.UsuarioDAO;
import br.com.allsoft.avros.exceptions.AuditoriaException;
import static br.com.allsoft.avros.factory.JDBCAuditoria.tabela;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Luana Nascimento
 */
public class AuditoriaLogin extends JDBCAuditoria {

    /**
     * Método que armazena o horário que o usuário fez login
     *
     * @param usuario usuário que fez o login
     * @param codSql codigo sql utilizado
     */
    public static void login(UsuarioDAO usuario, String codSql) throws AuditoriaException {
        try {

            tabela = ClsBD.getTblLogin();
            acao = "login";
            descricao = usuario.getNick() + " logou no sistema";
            sql = codSql;
            codDado = usuario.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();

            antes = "-"; //No caso de updates, como o campo era antes
            depois = "-"; //No caso de updates, como o campo ficou no fim
            campo = "-"; //Campo alterado

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
        } catch (SQLException ex) {
            throw new AuditoriaException(ex);
        }
    }

    /**
     * Método que armazena o horário que o usuário fez logout
     *
     * @param usuario usuário que fez o logout
     * @throws AuditoriaException
     */
    public static void logout(UsuarioDAO usuario) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblLogin();
            acao = "login";
            descricao = usuario.getNick() + " saiu do sistema";
            codDado = usuario.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
            antes = "-"; //No caso de updates, como o campo era antes
            depois = "-"; //No caso de updates, como o campo ficou no fim
            campo = "-"; //Campo alterado

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
        } catch (SQLException ex) {
            throw new AuditoriaException(ex);
        }
    }
}
