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
import br.com.allsoft.avros.dao.RegistroDAO;
import br.com.allsoft.avros.dao.RepresentanteDAO;
import br.com.allsoft.avros.dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para consultas em views
 * 
 * @author Luana Nascimento
 */
public class JDBCViews {
    //Variáveis
    static Connection con = null;
    static String view;
    static String campos;

    /**
     * Retorna o ID do representante e o grau de parentesco num objeto
     * RepresentanteDAO
     *
     * @param menor ClienteDAO com o menor
     * @return RepresentanteDAO com apenas o ID e o grau setados
     */
    public static RepresentanteDAO parentesco(ClienteDAO menor) throws SQLException {
        RepresentanteDAO representante = new RepresentanteDAO();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewParente();
        
        int idCli = menor.getId();
        
        PreparedStatement stmt = con.prepareStatement("select * from " + view + " where " + ClsBD.getCliId() + " = " + idCli);
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            representante.setId(rs.getInt(ClsBD.getRepId()));
            representante.setGrau(rs.getString(ClsBD.getParTipo()));
        }
        
        stmt.close();
        con.close();
        
        return representante;
    }
    
    /**
     * Consulta registros de logins feitos no sistema nas últimas 24h
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditLogin24h() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'";
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    
    /**
     * Consulta registros de logins feitos nos últimos 3 dias
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditLogin3d() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'");
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de logins feitos nos últimos 7 dias
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditLogin7d() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'");
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de logins feitos nos último mês
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditLogin1m() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'");
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de logins feitos no sistema nas últimas 24h
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditLogin24h(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login' "
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId();
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    
    /**
     * Consulta registros de logins feitos nos últimos 3 dias
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditLogin3d(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de logins feitos nos últimos 7 dias
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditLogin7d(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de logins feitos nos último mês
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditLogin1m(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de cadastros feitos no sistema nas últimas 24h
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditCad24h() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " 
                + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'";
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        
        return registros;
    }
    
    
    /**
     * Consulta registros de cadastros feitos nos últimos 3 dias
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditCad3d() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " 
                + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'");
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
     * Consulta registros de cadastros feitos nos últimos 7 dias
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditCad7d() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() 
                + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'");
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
     * Consulta registros de cadastros feitos nos último mês
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditCad1m() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() 
                + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'");
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
     * Consulta registros de cadastros feitos no sistema nas últimas 24h
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditCad24h(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert' "
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId();
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
     * Consulta registros de cadastros feitos nos últimos 3 dias
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditCad3d(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
     * Consulta registros de cadastros feitos nos últimos 7 dias
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditCad7d(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
     * Consulta registros de cadastros feitos no último mês
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditCad1m(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
     * Consulta registros de updates feitos no sistema nas últimas 24h
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditEdit24h() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'";
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setAntes(rs.getString(ClsBD.getAudAntes()));
            registro.setDepois(rs.getString(ClsBD.getAudDepois()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    
    /**
     * Consulta registros de updates feitos nos últimos 3 dias
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditEdit3d() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'");
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setAntes(rs.getString(ClsBD.getAudAntes()));
            registro.setDepois(rs.getString(ClsBD.getAudDepois()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de updates feitos nos últimos 7 dias
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditEdit7d() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'");
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setAntes(rs.getString(ClsBD.getAudAntes()));
            registro.setDepois(rs.getString(ClsBD.getAudDepois()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de updates feitos nos último mês
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditEdit1m() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'");
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setAntes(rs.getString(ClsBD.getAudAntes()));
            registro.setDepois(rs.getString(ClsBD.getAudDepois()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de updates feitos no sistema nas últimas 24h
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditEdit24h(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update' "
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId();
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setAntes(rs.getString(ClsBD.getAudAntes()));
            registro.setDepois(rs.getString(ClsBD.getAudDepois()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    
    /**
     * Consulta registros de updates feitos nos últimos 3 dias
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditEdit3d(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setAntes(rs.getString(ClsBD.getAudAntes()));
            registro.setDepois(rs.getString(ClsBD.getAudDepois()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de updates feitos nos últimos 7 dias
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditEdit7d(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setAntes(rs.getString(ClsBD.getAudAntes()));
            registro.setDepois(rs.getString(ClsBD.getAudDepois()));
            
            registros.add(registro);
        }
        
        stmt.close();
        con.close();
        
        return registros;
    }
    
    /**
     * Consulta registros de updates feitos nos último mês
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditEdit1m(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setAntes(rs.getString(ClsBD.getAudAntes()));
            registro.setDepois(rs.getString(ClsBD.getAudDepois()));
            
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
    public static List<RegistroDAO> auditDel24h() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'";
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
    public static List<RegistroDAO> auditDel3d() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'");
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
    public static List<RegistroDAO> auditDel7d() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'");
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
    public static List<RegistroDAO> auditDel1m() throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'");
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<RegistroDAO> auditDel24h(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete' "
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId();
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
    public static List<RegistroDAO> auditDel3d(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
    public static List<RegistroDAO> auditDel7d(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
    public static List<RegistroDAO> auditDel1m(String nick) throws SQLException {
        
        List<RegistroDAO> registros = new ArrayList<>();
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            RegistroDAO registro = new RegistroDAO();
            
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
