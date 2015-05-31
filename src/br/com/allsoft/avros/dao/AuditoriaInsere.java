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

import br.com.allsoft.avros.modelo.Cliente;
import br.com.allsoft.avros.modelo.ClsBD;
import br.com.allsoft.avros.modelo.Orcamento;
import br.com.allsoft.avros.modelo.Representante;
import br.com.allsoft.avros.modelo.Sessao;
import br.com.allsoft.avros.modelo.Usuario;
import br.com.allsoft.avros.exceptions.AuditoriaException;
import br.com.allsoft.avros.factory.ConexaoMySQL;
import br.com.allsoft.avros.modelo.JDBCAuditoria;
import br.com.allsoft.avros.modelo.Registro;
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
public class AuditoriaInsere extends JDBCAuditoria{
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
     * Método que grava a inserção de um novo cliente na tabela auditoria do BD,
     * quem inseriu e o horário da inserção.
     *
     * @param usuario usuário que fez o cadastro
     * @param cliente cliente que foi cadastrado
     * @param codSql
     * @throws AuditoriaException
     */
    public static void inserirCliente(Usuario usuario, Cliente cliente, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblCliente();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou o cliente " + cliente.getNome();
            sql = codSql;
            codDado = cliente.getId();
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
     * Grava a inserção de uma relação representante - menor na auditoria
     *
     * @param usuario que inseriu a relacao
     * @param cliente menor de idade
     * @param representante representante do cliente
     * @param parentesco grau de parentesco
     * @param codSql código SQL utilizado
     * @throws AuditoriaException
     */
    public static void inserirRel(Usuario usuario, Cliente cliente, Representante representante, String parentesco, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblRel();
            acao = "insert";
            descricao = usuario.getNick() + " relacionou o representante " + representante.getNome() + " como " + parentesco + " do cliente " + cliente.getNome();
            sql = codSql;
            codDado = cliente.getId();
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
     * Grava insersão de um novo grau de parentesco no banco de dados
     *
     * @param usuario que inseriu
     * @param parentesco descrição do grau
     * @param id do novo grau de parentesco
     * @param codSql codigo sql utilizado
     * @throws br.com.allsoft.avros.exceptions.AuditoriaException
     */
    public static void inserirParentesco(Usuario usuario, String parentesco, int id, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblParentesco();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou um novo grau de parentesco: " + parentesco;
            sql = codSql;
            codDado = id;
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
     * Grava a criação de uma nova sessao na auditoria
     *
     * @param usuario que inseriu
     * @param sessao inserida (com nome do cliente e id do orcamento setados)
     * @param codSql utilizado
     * @throws br.com.allsoft.avros.exceptions.AuditoriaException
     */
    public static void inserirSessao(Usuario usuario, Sessao sessao, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblSessao();
            acao = "insert";
            descricao = usuario.getNick() + " agendou uma nova sessao no orçamento código " + String.valueOf(sessao.getIdOrcamento()) + " para o cliente " + sessao.getCliente();
            sql = codSql;
            codDado = sessao.getId();
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
     * Método que grava a inserção de um novo responsavel de um cliente na
     * tabela auditoria do BD, quem inseriu e o horário da inserção.
     *
     * @param usuario usuário que fez o cadastro
     * @param responsavel responsavel pelo cliente menor de idade que for
     * cadastrar
     */
    public static void inserirRespresentante(Usuario usuario, Representante responsavel, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblRepresentante();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou o representante " + responsavel.getNome();
            sql = codSql;
            codDado = responsavel.getId();
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
     * Método que grava a inserção de um novo usuário na tabela auditoria do BD
     * quem inseriu e o horário da inserção.
     *
     * @param usuario usuário que fez o cadastro
     * @param novoUsuario usuário que foi cadastrado
     */
    public static void inserirUsuario(Usuario usuario, Usuario novoUsuario, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblLogin();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou o usuário " + novoUsuario.getNome();
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
     * Método que grava a inserção de um novo orçamento
     *
     * @param usuario que cadastrou
     * @param orcamento do tipo OrcamentoDAO
     * @param codSql codigo sql utilizado
     */
    public static void inserirOrcamento(Usuario usuario, Orcamento orcamento, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblOrcamento();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou o orçamento de código " + String.valueOf(orcamento.getId());
            sql = codSql;
            codDado = orcamento.getId();
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
     * Retorna todos os registros de insert da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List cauditCad() throws SQLException {
        List<Registro> registros = new ArrayList<>();
        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt =  con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'insert'");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Registro registro = new Registro();
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registros.add(registro);
        }
        return registros;
    }

    /**
     * Retorna todos os registros de insert da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List cauditCad(String nick) throws SQLException {
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt =  con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'insert'" + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Registro registro = new Registro();
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registros.add(registro);
        }
        return registros;
    }
}
