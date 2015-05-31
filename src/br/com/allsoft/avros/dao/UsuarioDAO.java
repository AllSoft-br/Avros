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
package br.com.allsoft.avros.dao;

import br.com.allsoft.avros.exceptions.AuditoriaException;
import br.com.allsoft.avros.factory.ConexaoMySQL;
import br.com.allsoft.avros.interfaces.FrmLogin;
import br.com.allsoft.avros.modelo.ClsBD;
import br.com.allsoft.avros.modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe DAO para o usuário do sistema
 *
 * @author Luana
 */
public class UsuarioDAO {

    //Variáveis
    public static Connection con = null;
    static String nomeTabela;

//Métodos
    /**
     * Abre uma conexão e fecha a antiga
     *
     * @throws SQLException
     */
    private static void abreCon() throws SQLException {
        if ((con == null) || (con.isClosed())) {
            con = ConexaoMySQL.getConexaoMySQL();
        }
    }

    /**
     * Procura o usuário no BD pelo Nickname
     *
     * @param nick nickname do usuário
     * @return UsuarioDAO encontrado
     */
    public static br.com.allsoft.avros.modelo.Usuario cusuarioNick(String nick) throws SQLException {
        br.com.allsoft.avros.modelo.Usuario usuario = new br.com.allsoft.avros.modelo.Usuario();
        nick = nick.trim();
        abreCon();
        nomeTabela = ClsBD.getTblLogin();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getUsuarionick() + " = ?");
        stmt.setString(1, nick);
        ResultSet rs = stmt.executeQuery();
        int q = 0;
        while (rs.next()) {
            usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
            usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
            usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
            usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setAtivo(rs.getBoolean(ClsBD.getUsuarioAtivo()));
        }
        return usuario;
    }

    /**
     * Procura o representante no BD pelo CPF
     *
     * @param id do usuario
     * @return UsuarioDAO encontrado
     */
    public static br.com.allsoft.avros.modelo.Usuario cusuarioId(int id) throws SQLException {
        br.com.allsoft.avros.modelo.Usuario usuario = new br.com.allsoft.avros.modelo.Usuario();
        abreCon();
        nomeTabela = ClsBD.getTblLogin();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getUsuarioId() + " = '" + id + "'");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
            usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
            usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
            usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setAtivo(rs.getBoolean(ClsBD.getUsuarioAtivo()));
        }
        return usuario;
    }

    /**
     * Método para fazer login
     *
     * @param login nickname do usuário
     * @param senha senha do usuário
     * @return objeto UsuarioDAO com todas as informações do usuário que logou
     * no sistema
     */
    public static br.com.allsoft.avros.modelo.Usuario login(String login, char[] senha) throws SQLException {
        br.com.allsoft.avros.modelo.Usuario usuario = new br.com.allsoft.avros.modelo.Usuario();
        abreCon();
        nomeTabela = ClsBD.getTblLogin();
        String sql = "select * from " + nomeTabela + " where " + ClsBD.getUsuarionick() + " = '" + login + "' and " + ClsBD.getUsuarioSenha() + " = '" + String.valueOf(senha) + "' and ativo = true";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
            usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
            usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
            usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));
            usuario.setAtivo(true);
            try {
                AuditoriaLogin.login(usuario, sql);
            } catch (AuditoriaException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }

    /**
     * Procura o usuário no BD pelo CPF
     *
     * @param cpf CPF do usuário
     * @return UsuarioDAO encontrado
     */
    public static br.com.allsoft.avros.modelo.Usuario cusuarioCpf(String cpf) throws SQLException {
        br.com.allsoft.avros.modelo.Usuario usuario = new br.com.allsoft.avros.modelo.Usuario();
        abreCon();
        nomeTabela = ClsBD.getTblLogin();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getUsuarioCpf() + " = '" + cpf + "'");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
            usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
            usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
            usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setAtivo(rs.getBoolean(ClsBD.getUsuarioAtivo()));
        }
        return usuario;
    }

    /**
     * Procura um usuários com o nome parametrizado no BD
     *
     * @param nome nome do cusuarioCpf
     * @return UsuarioDAO encontrado
     */
    public static List cusuarioNome(String nome) throws SQLException {
        List<br.com.allsoft.avros.modelo.Usuario> usuarios = new ArrayList<>();
        abreCon();
        nomeTabela = ClsBD.getTblLogin();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getUsuarionome() + " like '" + nome + "%'" + " OR " + ClsBD.getUsuarionome() + " like '%" + nome + "'" + " OR " + ClsBD.getUsuarionome() + " like '%" + nome + "%'");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            br.com.allsoft.avros.modelo.Usuario usuario = new br.com.allsoft.avros.modelo.Usuario();
            usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
            usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
            usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
            usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setAtivo(rs.getBoolean(ClsBD.getUsuarioAtivo()));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    /**
     * Retorna todos os usuários cadastrados
     *
     * @return List de UsuarioDAO
     */
    public static List cusuarioTodos() throws SQLException {
        List<br.com.allsoft.avros.modelo.Usuario> usuarios = new ArrayList<>();
        abreCon();
        nomeTabela = ClsBD.getTblLogin();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " order by " + ClsBD.getUsuarionome());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            br.com.allsoft.avros.modelo.Usuario usuario = new br.com.allsoft.avros.modelo.Usuario();
            usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
            usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
            usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
            usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setAtivo(rs.getBoolean(ClsBD.getUsuarioAtivo()));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    /**
     * Método que insere um novo usuário
     *
     * @param usuario objeto do tipo Usuario com as informações a serem
     * adicionadas
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public static void inserirUsuario(Usuario usuario) throws IOException, SQLException {
        nomeTabela = ClsBD.getTblLogin();
        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);
        usuario.setNome(usuario.getNome().trim());
        usuario.setCpf(usuario.getCpf().trim());
        usuario.setNick(usuario.getNick().trim());
        String sql = "insert into " + nomeTabela + "(" + ClsBD.getUsuarionome() + ", " + ClsBD.getUsuarionick() + ", " + ClsBD.getUsuarioSenha() + ", " + ClsBD.getUsuarioAdmin() + ", " + ClsBD.getUsuarioCpf() + ") " + "values (?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getNick());
        stmt.setString(3, String.valueOf(usuario.getSenha()));
        stmt.setBoolean(4, usuario.isAdmin());
        stmt.setString(5, usuario.getCpf());
        sql = stmt.toString();
        stmt.execute();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            usuario.setId(rs.getInt(1));
        }
        stmt.close();
        con.commit();
        con.close();
        try {
            AuditoriaInsere.inserirUsuario(FrmLogin.usuario, usuario, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que transforma um usuário em admin ou conta comum.
     *
     * @param admin se o usuário será admin ou não
     * @param id id do usuário que será modificado
     * @throws SQLException
     */
    public static void uusuarioAdmin(boolean admin, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        Usuario usuario = UsuarioDAO.cusuarioId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getUsuarioAdmin() + " = ? " + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setBoolean(1, admin);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaUsuarioAdmin(FrmLogin.usuario, usuario, admin, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que atualiza o nome do usuário.
     *
     * @param nome novo nome do usuário
     * @param id id do usuário que será modificado
     * @throws SQLException
     */
    public static void uusuarioNome(String nome, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        Usuario usuario = UsuarioDAO.cusuarioId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getUsuarionome() + " = ? " + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaNomeUsuario(FrmLogin.usuario, usuario, nome, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que cria uma nova senha para o usuário
     *
     * @param senha nova senha
     * @param id código do usuário
     * @throws SQLException
     */
    public static void uusuarioSenha(char[] senha, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        Usuario usuario = UsuarioDAO.cusuarioId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getUsuarioSenha() + "= ? " + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, String.valueOf(senha));
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaSenha(FrmLogin.usuario, usuario, senha, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        FrmLogin.usuario.setSenha(senha);
    }

    /**
     * Método que atualiza o nick do usuário.
     *
     * @param nick novo nick do usuário
     * @param id id do usuário que será modificado
     * @throws SQLException
     */
    public static void uusuarioNick(String nick, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getUsuarionick() + " = ? " + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nick);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaNick(FrmLogin.usuario, usuario, nick, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Modifica o status do usuario
     *
     * @param ativo se esta ativo ou nao
     * @param login do usuario
     * @throws SQLException
     */
    public static void uusuarioAtivo(boolean ativo, String login) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        Usuario usuario = UsuarioDAO.cusuarioNick(login);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getUsuarioAtivo() + "= ? " + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setBoolean(1, ativo);
        stmt.setInt(2, usuario.getId());
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaUsuarioAtivo(FrmLogin.usuario, usuario, ativo, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Modifica o status do usuario
     *
     * @param ativo se esta ativo ou nao
     * @param id do usuario
     * @throws SQLException
     */
    public static void uusuarioAtivo(boolean ativo, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        Usuario usuario = UsuarioDAO.cusuarioId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getUsuarioAtivo() + " = ? " + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setBoolean(1, ativo);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaUsuarioAtivo(FrmLogin.usuario, usuario, ativo, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
