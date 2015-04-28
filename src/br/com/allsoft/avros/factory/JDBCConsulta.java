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
import br.com.allsoft.avros.dao.OrcamentoDAO;
import br.com.allsoft.avros.dao.RepresentanteDAO;
import br.com.allsoft.avros.dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
     * Retorna o ID do representante e o grau de parentesco num objeto
     * RepresentanteDAO
     *
     * @param menor ClienteDAO com o menor
     * @return RepresentanteDAO com apenas o ID e o grau setados
     */
    public static RepresentanteDAO parentesco(ClienteDAO menor) throws SQLException {
        RepresentanteDAO representante = new RepresentanteDAO();

        con = ConexaoMySQL.getConexaoMySQL();
        nomeTabela = ClsBD.getViewParente();
        int idCli = menor.getId();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getCliId() + " = " + idCli);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            representante.setId(rs.getInt(ClsBD.getRepId()));
            representante.setGrau(ClsBD.getParTipo());
        }

        return representante;
    }

    /**
     * Conta quantos dependentes um representante tem.
     *
     * @param id
     * @return -1 caso tenha dado erro no processo
     */
    public static int qtdeDependentes(int id) throws SQLException {
        int retorno = -1;

        con = ConexaoMySQL.getConexaoMySQL();
        nomeTabela = ClsBD.getViewParente();

        PreparedStatement stmt = con.prepareStatement("select count(" + ClsBD.getCliId() + ") as 'quantos' from " + nomeTabela
                + " where " + ClsBD.getRepId() + " = " + id);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            retorno = rs.getInt("quantos");
        }

        return retorno;
    }

    /**
     * Método para fazer login
     *
     * @param login nickname do usuário
     * @param senha senha do usuário
     * @return objeto UsuarioDAO com todas as informações do usuário que logou
     * no sistema
     */
    public static UsuarioDAO login(String login, char[] senha) throws SQLException {
        UsuarioDAO usuario = new UsuarioDAO();

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

        }

        return usuario;
    }

    /**
     * Procura um usuários com o nome parametrizado no BD
     *
     * @param nome nome do usuarioCpf
     * @return UsuarioDAO encontrado
     */
    public static List<UsuarioDAO> usuarioNome(String nome) throws SQLException {
        List<UsuarioDAO> usuarios = new ArrayList<>();

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

        return usuarios;
    }

    /**
     * Retorna todos os usuários cadastrados
     *
     * @return List de UsuarioDAO
     */
    public static List<UsuarioDAO> usuarioTodos() throws SQLException {
        List<UsuarioDAO> usuarios = new ArrayList<>();

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

        return usuarios;
    }

    /**
     * Procura o usuário no BD pelo CPF
     *
     * @param cpf CPF do usuário
     * @return UsuarioDAO encontrado
     */
    public static UsuarioDAO usuarioCpf(String cpf) throws SQLException {
        UsuarioDAO usuario = new UsuarioDAO();

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

        }

        return usuario;
    }

    /**
     * Procura o representante no BD pelo CPF
     *
     * @param cpf CPF do usuário
     * @return UsuarioDAO encontrado
     */
    public static UsuarioDAO usuarioId(int id) throws SQLException {
        UsuarioDAO usuario = new UsuarioDAO();

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

        }

        return usuario;
    }

    /**
     * Procura o usuário no BD pelo Nickname
     *
     * @param nick nickname do usuário
     * @return UsuarioDAO encontrado
     */
    public static UsuarioDAO usuarioNick(String nick) throws SQLException {
        UsuarioDAO usuario = new UsuarioDAO();

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

        }

        return usuario;
    }

    /**
     * Método que pesquisa um representante no banco de dados pelo CPF
     *
     * @param cpf CPF do representante a ser pesquisado
     * @return objeto ClienteDAO caso o representante exista
     */
    public static ClienteDAO clienteCpf(String cpf) throws SQLException {
        ClienteDAO cliente = new ClienteDAO();

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
    public static List<ClienteDAO> clienteTodos() throws SQLException {
        List<ClienteDAO> clientes = new ArrayList<>();

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
            cliente.setNascimento(rs.getDate(ClsBD.getCliNasc()));
            cliente.setTel(rs.getString(ClsBD.getCliTel()));

            clientes.add(cliente);
        }

        return clientes;
    }

    /**
     * Método que pesquisa um orcamento no banco de dados pelo ID
     *
     * @param id ID do representante a ser pesquisado
     * @return objeto ClienteDAO
     */
    public static ClienteDAO clienteId(int id) throws SQLException {
        ClienteDAO cliente = new ClienteDAO();

        con = ConexaoMySQL.getConexaoMySQL();
        nomeTabela = ClsBD.getTblCliente();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getCliId() + " = " + id);

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
    public static List<ClienteDAO> clienteNome(String nome) throws SQLException {
        List<ClienteDAO> clientes = new ArrayList<>();

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
            cliente.setNascimento(rs.getDate(ClsBD.getCliNasc()));
            cliente.setFeminino(rs.getBoolean(ClsBD.getCliSexo()));
            cliente.setTel(rs.getString(ClsBD.getCliTel()));

            clientes.add(cliente);
        }

        return clientes;
    }

    /**
     * Método que pesquisa um representante no banco de dados pelo ID
     *
     * @param id ID do representante a ser pesquisado
     * @return objeto ClienteDAO
     */
    public static RepresentanteDAO representanteId(int id) throws SQLException {
        RepresentanteDAO representante = new RepresentanteDAO();

        con = ConexaoMySQL.getConexaoMySQL();
        nomeTabela = ClsBD.getTblRepresentante();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getRepId() + " = " + id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            representante.setNome(rs.getString(ClsBD.getRepnome()));
            representante.setId(rs.getInt(ClsBD.getRepId()));
            representante.setCpf(rs.getString(ClsBD.getRepCpf()));
            representante.setNascimento(rs.getDate(ClsBD.getRepNasc()));
            representante.setFeminino(rs.getBoolean(ClsBD.getRepSexo()));
            representante.setTel(rs.getString(ClsBD.getRepTel()));

        }

        return representante;
    }
    
    /**
     * Método que pesquisa um representante no banco de dados pelo ID
     *
     * @param cpf cpf do representante a ser pesquisado
     * @return objeto ClienteDAO
     */
    public static RepresentanteDAO representanteCpf(String cpf) throws SQLException {
        RepresentanteDAO representante = new RepresentanteDAO();

        con = ConexaoMySQL.getConexaoMySQL();
        nomeTabela = ClsBD.getTblRepresentante();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getRepCpf() + " = " + cpf);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            representante.setNome(rs.getString(ClsBD.getRepnome()));
            representante.setId(rs.getInt(ClsBD.getRepId()));
            representante.setCpf(rs.getString(ClsBD.getRepCpf()));
            representante.setNascimento(rs.getDate(ClsBD.getRepNasc()));
            representante.setFeminino(rs.getBoolean(ClsBD.getRepSexo()));
            representante.setTel(rs.getString(ClsBD.getRepTel()));

        }

        return representante;
    }
    
    /**
     * Procura representantes com o nome parametrizado no BD
     *
     * @param nome nome do representante
     * @return representantes encontrados
     * @throws java.sql.SQLException
     */
    public static List<RepresentanteDAO> representanteNome(String nome) throws SQLException {
        List<RepresentanteDAO> representantes = new ArrayList<>();

        con = ConexaoMySQL.getConexaoMySQL();
        nomeTabela = ClsBD.getTblRepresentante();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getRepnome() + " like '" + nome + "%'"
                + " OR " + ClsBD.getRepnome() + " like '%" + nome + "'"
                + " OR " + ClsBD.getRepnome() + " like '%" + nome + "%'");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            RepresentanteDAO representante = new RepresentanteDAO();
            representante.setNome(rs.getString(ClsBD.getRepnome()));
            representante.setId(rs.getInt(ClsBD.getRepId()));
            representante.setCpf(rs.getString(ClsBD.getRepCpf()));
            representante.setNascimento(rs.getDate(ClsBD.getRepNasc()));
            representante.setFeminino(rs.getBoolean(ClsBD.getRepSexo()));
            representante.setTel(rs.getString(ClsBD.getRepTel()));

            representantes.add(representante);
        }

        return representantes;
    }
    
    /**
     * Procura todos os representantes no BD
     * 
     * @return todos os representantes encontrados
     */
    public static List<RepresentanteDAO> representanteTodos() throws SQLException {
        List<RepresentanteDAO> representantes = new ArrayList<>();

        con = ConexaoMySQL.getConexaoMySQL();
        nomeTabela = ClsBD.getTblRepresentante();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            RepresentanteDAO representante = new RepresentanteDAO();
            representante.setNome(rs.getString(ClsBD.getRepnome()));
            representante.setId(rs.getInt(ClsBD.getRepId()));
            representante.setCpf(rs.getString(ClsBD.getRepCpf()));
            representante.setNascimento(rs.getDate(ClsBD.getRepNasc()));
            representante.setFeminino(rs.getBoolean(ClsBD.getRepSexo()));
            representante.setTel(rs.getString(ClsBD.getRepTel()));

            representantes.add(representante);
        }

        return representantes;
    }

    /**
     * Método que pesquisa orçamento pelo CPF do representante, e retorna uma
     * lista com todos os orçamentos que ele possui.
     *
     * @param id ID do representante
     * @return ArrayList com os orçamentos encontrados
     */
    public static List<OrcamentoDAO> orcamentos(int id) throws SQLException {
        List<OrcamentoDAO> orcamentos = new ArrayList<>();

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

        return orcamentos;
    }

    /**
     * Método que procura orçamentos pelo ID do orçamento
     *
     * @param id ID do orçamento
     * @return OrcamentoDAO com o orçamento encontrado
     */
    public static OrcamentoDAO orcamento(int id) throws SQLException {
        OrcamentoDAO orcamento = new OrcamentoDAO();

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

        return orcamento;
    }
}
