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
package br.com.allsoft.avros.factory.modelo;

import br.com.allsoft.avros.factory.ConexaoMySQL;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe para consultas gerais no BD.
 *
 * @author Luana
 */
public class JDBCConsulta {

    //Variáveis

    static Connection con = null;
    static String nomeTabela;

    /**
     * Método para fazer login
     *
     * @param login nickname do usuário
     * @param senha senha do usuário
     * @return objeto UsuarioDAO com todas as informações do usuário que logou
     * no sistema
     */
    public static UsuarioDAO login(String login, char[] senha) {
        UsuarioDAO usuario = new UsuarioDAO();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblLogin();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + ClsBD.getUsuarionick() + " = '" + login + "' and " + ClsBD.getUsuarioSenha() + " = '" + String.valueOf(senha) + "'");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
                usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
                usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
                usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
                usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
                usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));

            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
                usuario = null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        return usuario;
    }
    
    /**
     * Procura um usuários com o nome parametrizado no BD
     * 
     * @param nome nome do usuarioCpf
     * @return UsuarioDAO encontrado
     */
    public static List<UsuarioDAO> usuarioNome(String nome) {
        List<UsuarioDAO> usuarios = new ArrayList<>();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblLogin();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + ClsBD.getUsuarionome() + " like '" + nome + "%'"
                    + " OR " + ClsBD.getUsuarionome() + " like '%" + nome + "'"
                    + " OR " + ClsBD.getUsuarionome() + " like '%" + nome + "%'"); 

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioDAO usuario = new UsuarioDAO();
                usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
                usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
                usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
                usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
                usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
                usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        return usuarios;
    }
    
    /**
     * Retorna todos os usuários cadastrados
     * 
     * @return List de UsuarioDAO
     */
    public static List<UsuarioDAO> usuarioTodos() {
        List<UsuarioDAO> usuarios = new ArrayList<>();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblLogin();
            
            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela); 
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioDAO usuario = new UsuarioDAO();
                usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
                usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
                usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
                usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
                usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
                usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));
                
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return usuarios;
    }
    
    /**
     * Procura o usuário no BD pelo CPF
     * 
     * @param cpf CPF do usuário
     * @return UsuarioDAO encontrado
     */
    public static UsuarioDAO usuarioCpf(String cpf) {
        UsuarioDAO usuario = new UsuarioDAO();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblLogin();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + ClsBD.getUsuarioCpf() + " = '" + cpf + "'");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
                usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
                usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
                usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
                usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
                usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));

            } else {
                usuario = null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        return usuario;
    }
    
    /**
     * Procura o cliente no BD pelo CPF
     * 
     * @param cpf CPF do usuário
     * @return UsuarioDAO encontrado
     */
    public static UsuarioDAO usuarioId(int id) {
        UsuarioDAO usuario = new UsuarioDAO();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblLogin();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + ClsBD.getUsuarioId() + " = '" + id + "'");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
                usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
                usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
                usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
                usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
                usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));

            } else {
                usuario = null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        return usuario;
    }
    
    /**
     * Procura o usuário no BD pelo Nickname
     * 
     * @param nick nickname do usuário
     * @return UsuarioDAO encontrado
     */
    public static UsuarioDAO usuarioNick(String nick) {
        UsuarioDAO usuario = new UsuarioDAO();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblLogin();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + ClsBD.getUsuarionick() + " = '" + nick + "'");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setNome(rs.getString(ClsBD.getUsuarionome()));
                usuario.setNick(rs.getString(ClsBD.getUsuarionick()));
                usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
                usuario.setSenha(rs.getString(ClsBD.getUsuarioSenha()).toCharArray());
                usuario.setId(rs.getInt(ClsBD.getUsuarioId()));
                usuario.setCpf(rs.getString(ClsBD.getUsuarioCpf()));

            } else {
                usuario = null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        return usuario;
    }

    /**
     * Método que pesquisa um cliente no banco de dados pelo CPF
     *
     * @param cpf CPF do cliente a ser pesquisado
     * @return objeto ClienteDAO caso o cliente exista, null caso não exista
     */
    public static ClienteDAO clienteCpf(String cpf) {
        ClienteDAO cliente = new ClienteDAO();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblCliente();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + ClsBD.getCliCpf() + " = '" + cpf + "'");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cliente.setNome(rs.getString(ClsBD.getCliNome()));
                cliente.setId(rs.getInt(ClsBD.getCliId()));
                cliente.setIdUsuario(rs.getInt(ClsBD.getCliIdUsuario()));
                cliente.setCpf(rs.getString(ClsBD.getCliCpf()));
                cliente.setNascDate(rs.getDate(ClsBD.getCliNasc()));
                cliente.setFeminino(rs.getBoolean(ClsBD.getCliSexo()));
                cliente.setTel(rs.getString(ClsBD.getCliTel()));

            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                cliente = null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            cliente = null;
        }

        return cliente;
    }
    
    /**
     * Retorna todos os clientes cadastrados
     * 
     * @return List de ClienteDAO
     */
    public static List<ClienteDAO> clienteTodos() {
        List<ClienteDAO> clientes = new ArrayList<>();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblCliente();
            
            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela); 
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ClienteDAO cliente = new ClienteDAO();
                cliente.setNome(rs.getString(ClsBD.getCliNome()));
                cliente.setId(rs.getInt(ClsBD.getCliId()));
                cliente.setIdUsuario(rs.getInt(ClsBD.getCliIdUsuario()));
                cliente.setCpf(rs.getString(ClsBD.getCliCpf()));
                cliente.setFeminino(rs.getBoolean(ClsBD.getCliSexo()));
                cliente.setNascDate(rs.getDate(ClsBD.getCliNasc()));
                cliente.setTel(rs.getString(ClsBD.getCliTel()));
                
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return clientes;
    }
    
        /**
     * Método que pesquisa um orcamento no banco de dados pelo ID
     *
     * @param id ID do cliente a ser pesquisado
     * @return objeto ClienteDAO caso o cliente exista, null caso não exista
     */
    public static ClienteDAO clienteId(int id) {
        ClienteDAO cliente = new ClienteDAO();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblCliente();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + ClsBD.getCliId() + " = '" + id + "'");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cliente.setNome(rs.getString(ClsBD.getCliNome()));
                cliente.setId(rs.getInt(ClsBD.getCliId()));
                cliente.setIdUsuario(rs.getInt(ClsBD.getCliIdUsuario()));
                cliente.setCpf(rs.getString(ClsBD.getCliCpf()));
                cliente.setNascDate(rs.getDate(ClsBD.getCliNasc()));
                cliente.setFeminino(rs.getBoolean(ClsBD.getCliSexo()));
                cliente.setTel(rs.getString(ClsBD.getCliTel()));

            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                cliente = null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            cliente = null;
        }

        return cliente;
    }
    
    /**
     * Procura clientes com o nome parametrizado no BD
     * 
     * @param nome nome do cliente
     * @return UsuarioDAO encontrado
     */
    public static List<ClienteDAO> clienteNome(String nome) {
        List<ClienteDAO> clientes = new ArrayList<>();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblCliente();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + ClsBD.getCliNome() + " like '" + nome + "%'"
                    + " OR " + ClsBD.getCliNome() + " like '%" + nome + "'"
                    + " OR " + ClsBD.getCliNome() + " like '%" + nome + "%'"); 

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ClienteDAO cliente = new ClienteDAO();
                cliente.setNome(rs.getString(ClsBD.getCliNome()));
                cliente.setId(rs.getInt(ClsBD.getCliId()));
                cliente.setIdUsuario(rs.getInt(ClsBD.getCliIdUsuario()));
                cliente.setCpf(rs.getString(ClsBD.getCliCpf()));
                cliente.setNascDate(rs.getDate(ClsBD.getCliNasc()));
                cliente.setFeminino(rs.getBoolean(ClsBD.getCliSexo()));
                cliente.setTel(rs.getString(ClsBD.getCliTel()));

                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        return clientes;
    }

    /**
     * Método que pesquisa orçamento pelo CPF do cliente, e retorna uma lista
     * com todos os orçamentos que ele possui.
     *
     * @param id ID do cliente
     * @return ArrayList com os orçamentos encontrados
     */
    public static List<OrcamentoDAO> orcamentos(int id) {
        List<OrcamentoDAO> orcamentos = new ArrayList<>();

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblOrcamento();
            String cpfCli = ClsBD.getOrcClienteId();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + cpfCli + " = '" + id + "'");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                OrcamentoDAO orcamento = new OrcamentoDAO();

                orcamento.setId(rs.getInt(ClsBD.getOrcId()));
                orcamento.setCriacao(rs.getDate(ClsBD.getOrcCriacao()));
                orcamento.setIdCliente(rs.getInt(ClsBD.getOrcClienteId()));
                orcamento.setSessoes(rs.getInt(ClsBD.getOrcNSessoes()));
                orcamento.setTipoPagamento(rs.getString(ClsBD.getOrcTipoPag()));
                orcamento.setValor(rs.getDouble(ClsBD.getOrcValor()));

                orcamentos.add(orcamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        return orcamentos;
    }

    /**
     * Método que procura orçamentos pelo ID do orçamento
     * 
     * @param id ID do orçamento
     * @return OrcamentoDAO com o orçamento encontrado
     */
    public static OrcamentoDAO orcamento(int id) {
        OrcamentoDAO orcamento = new OrcamentoDAO();
        try {
            con = ConexaoMySQL.getConexaoMySQL();
            nomeTabela = ClsBD.getTblOrcamento();
            String bdid = ClsBD.getOrcId();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + bdid + " = '" + id + "'");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                orcamento.setId(id);
                orcamento.setCriacao(rs.getDate(ClsBD.getOrcCriacao()));
                orcamento.setIdCliente(rs.getInt(ClsBD.getOrcClienteId()));
                orcamento.setSessoes(rs.getInt(ClsBD.getOrcNSessoes()));
                orcamento.setTipoPagamento(rs.getString(ClsBD.getOrcTipoPag()));
                orcamento.setValor(rs.getDouble(ClsBD.getOrcValor()));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            orcamento = null;
        }

        return orcamento;
    }
}
