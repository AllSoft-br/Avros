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
import br.com.allsoft.avros.modelo.Cliente;
import br.com.allsoft.avros.modelo.ClsBD;
import br.com.allsoft.avros.modelo.Representante;
import java.sql.Connection;
import java.sql.Date;
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
 * Classe DAO para clientes.
 *
 * @author Luana
 */
public class ClienteDAO {
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
     * Método que pesquisa um cliente no banco de dados pelo ID
     *
     * @param id ID do representante a ser pesquisado
     * @return objeto ClienteDAO
     */
    public static Cliente cclienteId(int id) throws SQLException {
        Cliente cliente = new Cliente();
        abreCon();
        nomeTabela = ClsBD.getTblCliente();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getCliId() + " = " + id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            cliente.setNome(rs.getString(ClsBD.getCliNome()));
            cliente.setId(rs.getInt(ClsBD.getCliId()));
            cliente.setIdUsuario(rs.getInt(ClsBD.getCliIdUsuario()));
            cliente.setCpf(rs.getString(ClsBD.getCliCpf()));
            cliente.setNascimento(rs.getDate(ClsBD.getCliNasc()));
            cliente.setFeminino(rs.getBoolean(ClsBD.getCliSexo()));
            cliente.setTel(rs.getString(ClsBD.getCliTel()));
        }
        return cliente;
    }

    /**
     * Retorna todos os representantes cadastrados
     *
     * @return List de ClienteDAO
     */
    public static List cclienteTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        abreCon();
        nomeTabela = ClsBD.getTblCliente();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setNome(rs.getString(ClsBD.getCliNome()));
            cliente.setId(rs.getInt(ClsBD.getCliId()));
            cliente.setIdUsuario(rs.getInt(ClsBD.getCliIdUsuario()));
            cliente.setCpf(rs.getString(ClsBD.getCliCpf()));
            cliente.setFeminino(rs.getBoolean(ClsBD.getCliSexo()));
            cliente.setNascimento(rs.getDate(ClsBD.getCliNasc()));
            cliente.setTel(rs.getString(ClsBD.getCliTel()));
            clientes.add(cliente);
        }
        return clientes;
    }

    /**
     * Método que pesquisa um representante no banco de dados pelo CPF
     *
     * @param cpf CPF do representante a ser pesquisado
     * @return objeto ClienteDAO caso o representante exista
     */
    public static Cliente cclienteCpf(String cpf) throws SQLException {
        Cliente cliente = new Cliente();
        cpf = cpf.trim();
        abreCon();
        nomeTabela = ClsBD.getTblCliente();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getCliCpf() + " = '" + cpf + "'");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            cliente.setNome(rs.getString(ClsBD.getCliNome()));
            cliente.setId(rs.getInt(ClsBD.getCliId()));
            cliente.setIdUsuario(rs.getInt(ClsBD.getCliIdUsuario()));
            cliente.setCpf(rs.getString(ClsBD.getCliCpf()));
            cliente.setNascimento(rs.getDate(ClsBD.getCliNasc()));
            cliente.setFeminino(rs.getBoolean(ClsBD.getCliSexo()));
            cliente.setTel(rs.getString(ClsBD.getCliTel()));
        }
        return cliente;
    }

    /**
     * Procura representantes com o nome parametrizado no BD
     *
     * @param nome nome do representante
     * @return UsuarioDAO encontrado
     */
    public static List cclienteNome(String nome) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        nome = nome.trim();
        abreCon();
        nomeTabela = ClsBD.getTblCliente();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getCliNome() + " like '" + nome + "%'" + " OR " + ClsBD.getCliNome() + " like '%" + nome + "'" + " OR " + ClsBD.getCliNome() + " like '%" + nome + "%'");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setNome(rs.getString(ClsBD.getCliNome()));
            cliente.setId(rs.getInt(ClsBD.getCliId()));
            cliente.setIdUsuario(rs.getInt(ClsBD.getCliIdUsuario()));
            cliente.setCpf(rs.getString(ClsBD.getCliCpf()));
            cliente.setNascimento(rs.getDate(ClsBD.getCliNasc()));
            cliente.setFeminino(rs.getBoolean(ClsBD.getCliSexo()));
            cliente.setTel(rs.getString(ClsBD.getCliTel()));
            clientes.add(cliente);
        }
        return clientes;
    }

    /**
     * Método que insere um novo cliente no banco de dados.
     *
     * @param cliente objeto do tipo Cliente com informações do cliente a ser
     * inserido.
     * @param usuarioId ID do usuário que está inserindo o cliente
     * @throws SQLException
     */
    public static void inserirCliente(Cliente cliente, int usuarioId) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();
        cliente.setNome(cliente.getNome().trim());
        cliente.setCpf(cliente.getCpf().trim());
        cliente.setTel(cliente.getTel().trim());
        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);
        String sql = "insert into " + nomeTabela + "(" + ClsBD.getCliNome() + ", " + ClsBD.getCliCpf() + ", " + ClsBD.getCliNasc() + ", " + ClsBD.getCliTel() + ", " + ClsBD.getCliSexo() + ", " + ClsBD.getCliIdUsuario() + ") values (?,?,?,?,?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setDate(3, cliente.getNascimento());
        stmt.setString(4, cliente.getTel());
        stmt.setBoolean(5, cliente.isFeminino());
        stmt.setInt(6, usuarioId);
        sql = stmt.toString();
        stmt.execute();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            cliente.setId(rs.getInt(1));
        }
        stmt.close();
        con.commit();
        con.close();
        try {
            AuditoriaInsere.inserirCliente(FrmLogin.usuario, cliente, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que insere um novo responsavel no banco de dados.
     *
     * @param responsavel objeto do tipo Representante com informações do
     * responsavel a ser inserido.
     * @param menor cliente menor de idade que será cadastrado, do tipo
     * Cliente
     * @param parentescoId ID do parentesco, que é referenciado tanto no BD
     * quanto na caixa de seleção da tela de cadastro de responsável
     * @throws SQLException
     */
    public static void inserirClienteMenor(Representante responsavel, Cliente menor, int parentescoId, int usuarioId) throws SQLException {
        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);
        responsavel.setNome(responsavel.getNome().trim());
        responsavel.setCpf(responsavel.getCpf().trim());
        responsavel.setTel(responsavel.getTel().trim());
        menor.setNome(menor.getNome().trim());
        menor.setCpf(menor.getCpf().trim());
        menor.setTel(menor.getTel().trim());
        String sqlR = "insert into " + ClsBD.getTblRepresentante() + " (" + ClsBD.getRepnome() + ", " + ClsBD.getRepCpf() + ", " + ClsBD.getRepNasc() + ", " + ClsBD.getRepTel() + ", " + ClsBD.getRepSexo() + ") " + "values (?,?,?,?,?);";
        PreparedStatement stmtR = con.prepareStatement(sqlR, Statement.RETURN_GENERATED_KEYS);
        stmtR.setString(1, responsavel.getNome());
        stmtR.setString(2, responsavel.getCpf());
        stmtR.setDate(3, responsavel.getNascimento());
        stmtR.setString(4, responsavel.getTel());
        stmtR.setBoolean(5, responsavel.isFeminino());
        sqlR = stmtR.toString();
        stmtR.execute();
        String sqlM = "insert into " + ClsBD.getTblCliente() + "(" + ClsBD.getCliNome() + ", " + ClsBD.getCliCpf() + ", " + ClsBD.getCliNasc() + ", " + ClsBD.getCliTel() + ", " + ClsBD.getCliSexo() + ", " + ClsBD.getCliIdUsuario() + ") values (?,?,?,?,?,?)";
        PreparedStatement stmtM = con.prepareStatement(sqlM, Statement.RETURN_GENERATED_KEYS);
        stmtM.setString(1, menor.getNome());
        stmtM.setString(2, menor.getCpf());
        stmtM.setDate(3, menor.getNascimento());
        stmtM.setString(4, menor.getTel());
        stmtM.setBoolean(5, menor.isFeminino());
        stmtM.setInt(6, usuarioId);
        sqlM = stmtM.toString();
        stmtM.execute();
        ResultSet rs1 = stmtM.getGeneratedKeys();
        if (rs1 != null && rs1.next()) {
            menor.setId(rs1.getInt(1));
        }
        ResultSet rs2 = stmtR.getGeneratedKeys();
        if (rs2 != null && rs2.next()) {
            responsavel.setId(rs2.getInt(1));
        }
        con.commit();
        String sql = "insert into " + ClsBD.getTblRel() + "(" + ClsBD.getRelClienteId() + ", " + ClsBD.getRelParentescoId() + ", " + ClsBD.getRelRepresentanteId() + ") values(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, menor.getId());
        stmt.setInt(2, parentescoId);
        stmt.setInt(3, responsavel.getId());
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        stmtM.close();
        stmtR.close();
        con.commit();
        con.close();
        Representante parentesco = RepresentanteDAO.vparentesco(menor);
        try {
            AuditoriaInsere.inserirCliente(FrmLogin.usuario, menor, sqlM);
            AuditoriaInsere.inserirRespresentante(FrmLogin.usuario, responsavel, sqlR);
            AuditoriaInsere.inserirRel(FrmLogin.usuario, menor, responsavel, parentesco.getGrau(), sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda o numero de telefone do cliente
     *
     * @param tel novo telefone
     * @param id ID do cliente
     * @throws SQLException
     */
    public static void uclienteTel(String tel, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();
        Cliente cliente = ClienteDAO.cclienteId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getCliTel() + "= ? " + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, tel);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaClienteTel(FrmLogin.usuario, cliente, tel, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda o sexo do cliente
     *
     * @param feminino se o sexo é feminino ou não
     * @param id ID do cliente a mudar
     * @throws SQLException
     */
    public static void uclienteSexo(Boolean feminino, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();
        Cliente cliente = ClienteDAO.cclienteId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getCliSexo() + "= ? " + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setBoolean(1, feminino);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaClienteSexo(FrmLogin.usuario, cliente, feminino, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda a data de nascimento do cliente
     *
     * @param data nova data
     * @param id ID do cliente
     * @throws SQLException
     */
    public static void uclienteNasc(Date data, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();
        Cliente cliente = ClienteDAO.cclienteId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getCliNasc() + "= ? " + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setDate(1, data);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaClienteNasc(FrmLogin.usuario, cliente, data, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Modifica o nome de um cliente.
     *
     * @param nome novo nome do cliente
     * @param id ID do cliente a mudar o nome
     * @throws SQLException
     */
    public static void uclienteNome(String nome, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();
        Cliente cliente = ClienteDAO.cclienteId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getCliNome() + "= ? " + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.modificaNomeCliente(FrmLogin.usuario, cliente, nome, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Deleta um cliente.
     *
     * @param cliente a ser deletado
     * @throws SQLException
     */
    public static void dcliente(Cliente cliente) throws SQLException {
        con = ConexaoMySQL.getConexaoMySQL();
        
        String sql = "CALL " + ClsBD.procDelCli + "(?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setInt(1, cliente.getId());
        
        sql = stmt.toString();
        
        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaDelete.cliente(FrmLogin.usuario, cliente, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
