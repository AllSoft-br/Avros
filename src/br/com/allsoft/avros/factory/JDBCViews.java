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
import br.com.allsoft.avros.dao.RegistroDAO;
import br.com.allsoft.avros.dao.RepresentanteDAO;
import br.com.allsoft.avros.dao.UsuarioDAO;
import br.com.allsoft.avros.modelo.Cliente;
import br.com.allsoft.avros.modelo.ClsBD;
import br.com.allsoft.avros.modelo.Registro;
import br.com.allsoft.avros.modelo.Representante;
import br.com.allsoft.avros.modelo.Usuario;
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
     * Consulta registros de logins feitos no sistema nas últimas 24h
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditLogin24h() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'";
        PreparedStatement stmt = con.prepareStatement(sql);
        
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
    public static List<Registro> vauditLogin3d() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'");
        
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
    public static List<Registro> vauditLogin7d() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'");
        
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
    public static List<Registro> vauditLogin1m() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'");
        
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
    public static List<Registro> vauditLogin24h(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login' "
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId();
        PreparedStatement stmt = con.prepareStatement(sql);
        
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
    public static List<Registro> vauditLogin3d(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
    public static List<Registro> vauditLogin7d(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
    public static List<Registro> vauditLogin1m(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'login'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
    public static List<Registro> vauditCad24h() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " 
                + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'";
        PreparedStatement stmt = con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
       
        
        while (rs.next()) {
            Registro registro = new Registro();
            
            registro.setTabela(rs.getString(ClsBD.getAudTabela()));
            registro.setIdDado(rs.getInt(ClsBD.getAudRef()));
            registro.setId(rs.getInt(ClsBD.getAudId()));
            registro.setIdLogin(rs.getInt(ClsBD.getAudIdLogin()));
            registro.setAcao(rs.getString(ClsBD.getAudAcao()));
            registro.setDescricao(rs.getString(ClsBD.getAudDesc()));
            registro.setData(rs.getTimestamp(ClsBD.getAudData()));
            
            
            registros.add(registro);
            System.out.println("oi");
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
    public static List<Registro> vauditCad3d() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " 
                + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'");
        
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
     * Consulta registros de cadastros feitos nos últimos 7 dias
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditCad7d() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() 
                + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'");
        
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
     * Consulta registros de cadastros feitos nos último mês
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditCad1m() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() 
                + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'");
        
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
     * Consulta registros de cadastros feitos no sistema nas últimas 24h
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditCad24h(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert' "
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId();
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
     * Consulta registros de cadastros feitos nos últimos 3 dias
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditCad3d(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
     * Consulta registros de cadastros feitos nos últimos 7 dias
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditCad7d(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
     * Consulta registros de cadastros feitos no último mês
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditCad1m(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'insert'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
     * Consulta registros de updates feitos no sistema nas últimas 24h
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditEdit24h() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'";
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
    public static List<Registro> vauditEdit3d() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'");
        
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
    public static List<Registro> vauditEdit7d() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'");
        
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
    public static List<Registro> vauditEdit1m() throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'");
        
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
    public static List<Registro> vauditEdit24h 
        (String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update' "
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId();
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
    public static List<Registro> vauditEdit3d(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
    public static List<Registro> vauditEdit7d(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
    public static List<Registro> vauditEdit1m(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'update'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
    public static List<Registro> vauditDel24h() throws SQLException {
        
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
     * Consulta registros de exclusoes feitos nos últimos 3 dias
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditDel3d() throws SQLException {
        
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
    
    /**
     * Consulta registros de exclusoes feitos nos últimos 7 dias
     * 
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditDel7d() throws SQLException {
        
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
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditDel1m() throws SQLException {
        
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
     * Consulta registros de exclusoes feitos no sistema nas últimas 24h
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditDel24h(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit24h();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        String sql = "select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete' "
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId();
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
     * Consulta registros de exclusoes feitos nos últimos 3 dias
     * 
     * @param nick do usuário a ser visualizado
     * @return List com os registros encontrados
     * @throws SQLException 
     */
    public static List<Registro> vauditDel3d(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit3d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
    public static List<Registro> vauditDel7d(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit7d();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
    public static List<Registro> vauditDel1m(String nick) throws SQLException {
        
        List<Registro> registros = new ArrayList<>();
        Usuario usuario = UsuarioDAO.cusuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewAudit1m();
        campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();
        
        PreparedStatement stmt = con.prepareStatement("select " + campos + " from " + view + " where " + ClsBD.getAudAcao() + " = 'delete'"
                + "and " + ClsBD.getAudIdLogin() + " = " + usuario.getId());
        
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
