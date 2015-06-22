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
import br.com.allsoft.avros.formulas.Cpf;
import br.com.allsoft.avros.modelo.Cliente;
import br.com.allsoft.avros.modelo.ClsBD;
import br.com.allsoft.avros.modelo.JDBCAuditoria;
import br.com.allsoft.avros.modelo.Orcamento;
import br.com.allsoft.avros.modelo.Registro;
import br.com.allsoft.avros.modelo.Representante;
import br.com.allsoft.avros.modelo.Sessao;
import br.com.allsoft.avros.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para salvar deletes na auditoria
 * 
 * @author Luana Nascimento
 */
public class AuditoriaDelete extends JDBCAuditoria{
    //Variáveis
    public static Connection con = null;
    static String nomeTabela;
    static String view;
    static String campos;

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
     * Grava a ação de deletar uma relação entre responsavel e cliente menor na auditoria
     *
     * @param resp usuário responsável pelo update
     * @param representante
     * @param menor
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void relRepMenor(Usuario resp, Representante representante, Cliente menor, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblRel();
            acao = "delete";
            descricao = resp.getNick() + " deletou a relação entre o representante " + representante.getNome() + ", CPF " + Cpf.imprimeCpf(representante.getCpf()) + " e o cliente " + menor.getNome() + ", CPF " + Cpf.imprimeCpf(menor.getCpf()) + ", de " + String.valueOf(menor.idade()) + " anos.";
            sql = codSql;
            codDado = menor.getId();
            idLogin = resp.getId();
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
     * Grava delete de uma sessão na auditoria
     * @param resp usuário responsável
     * @param sessao sessão deletada, com nome do cliente e id 
     * de orçamento setados
     * @param codSql SQL utilizado
     * @throws AuditoriaException 
     */
    public static void sessao(Usuario resp, Sessao sessao, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblSessao();
            acao = "delete";
            descricao = resp.getNick() + " deletou a sessão de ID " + sessao.getId() + " do cliente " + sessao.getCliente() + ", orçamento " + sessao.getIdOrcamento();
            sql = codSql;
            codDado = sessao.getId();
            idLogin = resp.getId();
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
     * Grava delete de um orçamento na auditoria
     * @param resp usuário responsável
     * @param orcamento orçamento deletado, com nome do cliente setado 
     * @param codSql SQL utilizado
     * @throws AuditoriaException 
     */
    public static void orcamento(Usuario resp, Orcamento orcamento, String codSql) throws AuditoriaException {
        try {
            Cliente cliente = ClienteDAO.cclienteId(orcamento.getIdCliente());
            
            tabela = ClsBD.getTblOrcamento();
            acao = "delete";
            descricao = resp.getNick() + " deletou o orçamento de ID " + orcamento.getId() + " e suas sessões, do cliente " + cliente.getNome();
            sql = codSql;
            codDado = orcamento.getId();
            idLogin = resp.getId();
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
     * Grava delete de um orçamento na auditoria
     * @param resp usuário responsável 
     * @param representante 
     * @param codSql SQL utilizado
     * @throws AuditoriaException 
     */
    public static void representante(Usuario resp, Representante representante, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblRepresentante();
            acao = "delete";
            descricao = resp.getNick() + " deletou o representante " + representante.getNome();
            sql = codSql;
            codDado = representante.getId();
            idLogin = resp.getId();
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
     * Grava delete de um orçamento na auditoria
     * @param resp usuário responsável 
     * @param cliente 
     * @param codSql SQL utilizado
     * @throws AuditoriaException 
     */
    public static void cliente(Usuario resp, Cliente cliente, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblCliente();
            acao = "delete";
            descricao = resp.getNick() + " deletou o cliente " + cliente.getNome();
            sql = codSql;
            codDado = cliente.getId();
            idLogin = resp.getId();
            
            antes = "-"; //No caso de updates, como o campo era antes
            depois = "-"; //No caso de updates, como o campo ficou no fim
            campo = "-"; //Campo alterado
            
            insereRegistro();
            
        } catch (SQLException ex) {
            throw new AuditoriaException(ex);
        }
    }
    
    /**
     * Retorna todos os registros de delete da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List cauditDel() throws SQLException {
        List<Registro> registros = new ArrayList<>();
        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'delete'");
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
     * Retorna todos os registros de delete da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List cauditDel(String nick) throws SQLException {
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'delete'" + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
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
     * Consulta registros de exclusoes feitos no sistema nas últimas 24h
     *
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException
     */
    public static List vauditDel24h(String nick) throws SQLException {
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete' " + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId();
        PreparedStatement stmt = con.prepareStatement(sql);
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
        stmt.close();
        con.close();
        return registros;
    }

    /**
     * Consulta registros de exclusoes feitos no sistema nas últimas 24h
     *
     * @return List com os registros encontrados
     * @throws SQLException
     */
    public static List vauditDel24h() throws SQLException {
        List<Registro> registros = new ArrayList<>();
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'";
        PreparedStatement stmt = con.prepareStatement(sql);
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
        stmt.close();
        con.close();
        return registros;
    }

    /**
     * Consulta registros de exclusoes feitos nos últimos 7 dias
     *
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException
     */
    public static List vauditDel7d(String nick) throws SQLException {
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'" + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
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
        stmt.close();
        con.close();
        return registros;
    }

    /**
     * Consulta registros de exclusoes feitos nos últimos 7 dias
     *
     * @return List com os registros encontrados
     * @throws SQLException
     */
    public static List vauditDel7d() throws SQLException {
        List<Registro> registros = new ArrayList<>();
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'");
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
        stmt.close();
        con.close();
        return registros;
    }

    /**
     * Consulta registros de exclusoes feitos nos último mês
     *
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException
     */
    public static List vauditDel1m(String nick) throws SQLException {
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'" + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
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
        stmt.close();
        con.close();
        return registros;
    }

    /**
     * Consulta registros de exclusoes feitos nos últimos 3 dias
     *
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException
     */
    public static List vauditDel3d(String nick) throws SQLException {
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'" + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
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
        stmt.close();
        con.close();
        return registros;
    }

    /**
     * Consulta registros de exclusoes feitos nos último mês
     *
     * @return List com os registros encontrados
     * @throws SQLException
     */
    public static List vauditDel1m() throws SQLException {
        List<Registro> registros = new ArrayList<>();
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'");
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
        stmt.close();
        con.close();
        return registros;
    }

    /**
     * Consulta registros de exclusoes feitos nos últimos 3 dias
     *
     * @return List com os registros encontrados
     * @throws SQLException
     */
    public static List vauditDel3d() throws SQLException {
        List<Registro> registros = new ArrayList<>();
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'");
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
        stmt.close();
        con.close();
        return registros;
    }
}
