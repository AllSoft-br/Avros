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
import br.com.allsoft.avros.modelo.ClsBD;
import br.com.allsoft.avros.modelo.JDBCAuditoria;
import br.com.allsoft.avros.modelo.Registro;
import br.com.allsoft.avros.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luana Nascimento
 */
public class AuditoriaLogin extends JDBCAuditoria {

    //Variáveis
    public static Connection con = null;
    static String nomeTabela;

    //Métodos
    
    /**
     * Abre uma conexão e fecha a antiga
     * @throws SQLException 
     */
    private static void abreCon() throws SQLException {
        if ((con == null) || (con.isClosed())) {
            con = ConexaoMySQL.getConexaoMySQL();
        }
    }
    
    /**
     * Método que armazena o horário que o usuário fez login
     *
     * @param usuario usuário que fez o login
     * @param codSql codigo sql utilizado
     */
    public static void login(Usuario usuario, String codSql) throws AuditoriaException {
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
    public static void logout(Usuario usuario) throws AuditoriaException {
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
            sql = "-";

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
     * Grava o armazenamento de backup na auditoria
     * 
     * @param usuario que armazenou
     * @throws AuditoriaException 
     */
    public static void salvaBackup(Usuario usuario) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblLogin();
            acao = "login";
            descricao = usuario.getNick() + " atualizou o arquivo de backup";
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
     * Grava o armazenamento de backup na auditoria
     * 
     * @param usuario que armazenou
     * @throws AuditoriaException 
     */
    public static void recuperaBackup(Usuario usuario) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblLogin();
            acao = "login";
            descricao = usuario.getNick() + " recuperou as informações a partir do backup";
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
     * Retorna todos os registros de login da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List cauditLogin(String nick) throws SQLException {
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'login'" + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Registro registro = new Registro();
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registros.add(registro);
        }
        return registros;
    }

    /**
     * Retorna todos os registros de login da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List cauditLogin() throws SQLException {
        List<Registro> registros = new ArrayList<>();
        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'login'");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Registro registro = new Registro();
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registros.add(registro);
        }
        return registros;
    }
}
