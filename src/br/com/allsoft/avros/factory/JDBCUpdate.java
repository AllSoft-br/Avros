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
import br.com.allsoft.avros.interfaces.FrmLogin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe para fazer updates no BD do sistema.
 *
 * @author Luana
 */
public class JDBCUpdate {

    static Connection con = null;
    static String nomeTabela;

    /**
     * Método que cria uma nova senha para o usuário
     *
     * @param senha nova senha
     * @param id código do usuário
     * @throws SQLException
     */
    public static void usuarioSenha(char[] senha, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarioSenha() + "= ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, String.valueOf(senha));
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

        //salva modificações na tabela auditoria
        JDBCAuditoria.modificaSenha(FrmLogin.usuario, senha);

        FrmLogin.usuario.setSenha(senha);

        JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso.");

    }

    /**
     * Método que atualiza o nome do usuário.
     *
     * @param nome novo nome do usuário
     * @param id id do usuário que será modificado
     * @throws SQLException
     */
    public static void usuarioNome(String nome, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarionome() + " = ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, nome);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Método que atualiza o nick do usuário.
     *
     * @param nick novo nick do usuário
     * @param id id do usuário que será modificado
     * @throws SQLException
     */
    public static void usuarioNick(String nick, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarionick() + " = ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, nick);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Método que transforma um usuário em admin ou conta comum.
     *
     * @param admin se o usuário será admin ou não
     * @param id id do usuário que será modificado
     * @throws SQLException
     */
    public static void usuarioAdmin(boolean admin, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarioAdmin() + " = ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setBoolean(1, admin);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Modifica o nome de um cliente.
     *
     * @param nome novo nome do cliente
     * @param id ID do cliente a mudar o nome
     * @throws SQLException
     */
    public static void clienteNome(String nome, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliNome() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, nome);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Muda o sexo do cliente
     *
     * @param feminino se o sexo é feminino ou não
     * @param id ID do cliente a mudar
     * @throws SQLException
     */
    public static void clienteSexo(Boolean feminino, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliSexo() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setBoolean(1, feminino);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Muda a data de nascimento do cliente
     *
     * @param data nova data
     * @param id ID do cliente
     * @throws SQLException
     */
    public static void clienteNasc(Date data, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliNasc() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setDate(1, data);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Muda o numero de telefone do cliente
     *
     * @param tel novo telefone
     * @param id ID do cliente
     * @throws SQLException
     */
    public static void clienteTel(String tel, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliTel() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, tel);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Modifica o nome de um representante.
     *
     * @param nome novo nome do representante
     * @param id ID do representante a mudar o nome
     * @throws SQLException
     */
    public static void representanteNome(String nome, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliNome() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, nome);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Muda o sexo do representante
     *
     * @param feminino se o sexo é feminino ou não
     * @param id ID do representante a mudar
     * @throws SQLException
     */
    public static void representanteSexo(Boolean feminino, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliSexo() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setBoolean(1, feminino);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Muda a data de nascimento do representante
     *
     * @param data nova data
     * @param id ID do representante
     * @throws SQLException
     */
    public static void representanteNasc(Date data, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliNasc() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setDate(1, data);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }

    /**
     * Muda o numero de telefone do representante
     *
     * @param tel novo telefone
     * @param id ID do representante
     * @throws SQLException
     */
    public static void representanteTel(String tel, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliTel() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, tel);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        con.close();

    }
}
