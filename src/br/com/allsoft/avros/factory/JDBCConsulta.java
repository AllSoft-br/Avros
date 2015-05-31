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

import br.com.allsoft.avros.dao.Cliente;
import br.com.allsoft.avros.dao.ClsBD;
import br.com.allsoft.avros.dao.Orcamento;
import br.com.allsoft.avros.dao.Registro;
import br.com.allsoft.avros.dao.Representante;
import br.com.allsoft.avros.dao.Sessao;
import br.com.allsoft.avros.dao.Usuario;
import br.com.allsoft.avros.exceptions.AuditoriaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para consultas gerais no BD.
 *
 * @author Luana
 */
public class JDBCConsulta {

    //Variáveis
    public static Connection con = null;
    static String nomeTabela;

    private static void abreCon() throws SQLException {
        if ((con == null) || (con.isClosed())) {
            con = ConexaoMySQL.getConexaoMySQL();
        }
    }

    /**
     * Conta quantos dependentes um representante tem.
     *
     * @param id
     * @return -1 caso tenha dado erro no processo
     */
    public static int qtdeDependentes(int id) throws SQLException {
        int retorno = -1;

        abreCon();

        nomeTabela = ClsBD.getViewParente();
        String sql = "select count(id_cli) as 'quantos' from " + nomeTabela
                + " where id_representante = " + id;
        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            retorno = rs.getInt("quantos");
        }

        return retorno;
    }

    /**
     * Consulta os dependentes de um representante
     *
     * @param id do representante
     * @return list de dependentes
     * @throws SQLException
     */
    public static List<Cliente> dependentes(int id) throws SQLException {
        List<Cliente> dependentes = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getViewParente();

        PreparedStatement stmt = con.prepareStatement("select id_cli as 'ids' from " + nomeTabela
                + " where id_representante = " + id);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int idCli = rs.getInt("ids");
            Cliente menor = clienteId(idCli);
            dependentes.add(menor);
        }

        return dependentes;
    }

    /**
     * Método para fazer login
     *
     * @param login nickname do usuário
     * @param senha senha do usuário
     * @return objeto UsuarioDAO com todas as informações do usuário que logou
     * no sistema
     */
    public static Usuario login(String login, char[] senha) throws SQLException {
        Usuario usuario = new Usuario();

        abreCon();
        nomeTabela = ClsBD.getTblLogin();

        String sql = "select * from " + nomeTabela
                + " where " + ClsBD.getUsuarionick() + " = '" + login
                + "' and " + ClsBD.getUsuarioSenha() + " = '" + String.valueOf(senha)
                + "' and ativo = true";
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
                Logger.getLogger(JDBCConsulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return usuario;
    }

    /**
     * Procura um usuários com o nome parametrizado no BD
     *
     * @param nome nome do usuarioCpf
     * @return UsuarioDAO encontrado
     */
    public static List<Usuario> usuarioNome(String nome) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getTblLogin();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getUsuarionome() + " like '" + nome + "%'"
                + " OR " + ClsBD.getUsuarionome() + " like '%" + nome + "'"
                + " OR " + ClsBD.getUsuarionome() + " like '%" + nome + "%'");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
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
    public static List<Usuario> usuarioTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getTblLogin();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " order by " + ClsBD.getUsuarionome());

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
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
     * Procura o usuário no BD pelo CPF
     *
     * @param cpf CPF do usuário
     * @return UsuarioDAO encontrado
     */
    public static Usuario usuarioCpf(String cpf) throws SQLException {
        Usuario usuario = new Usuario();

        abreCon();
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
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setAtivo(rs.getBoolean(ClsBD.getUsuarioAtivo()));

        }

        return usuario;
    }

    /**
     * Procura o representante no BD pelo CPF
     *
     * @param cpf CPF do usuário
     * @return UsuarioDAO encontrado
     */
    public static Usuario usuarioId(int id) throws SQLException {
        Usuario usuario = new Usuario();

        abreCon();
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
            usuario.setAdmin(rs.getBoolean(ClsBD.getUsuarioAdmin()));
            usuario.setAtivo(rs.getBoolean(ClsBD.getUsuarioAtivo()));
        }

        return usuario;
    }

    /**
     * Procura o usuário no BD pelo Nickname
     *
     * @param nick nickname do usuário
     * @return UsuarioDAO encontrado
     */
    public static Usuario usuarioNick(String nick) throws SQLException {
        Usuario usuario = new Usuario();

        nick = nick.trim();
        abreCon();
        nomeTabela = ClsBD.getTblLogin();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getUsuarionick() + " = ?");

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
     * Método que pesquisa um representante no banco de dados pelo CPF
     *
     * @param cpf CPF do representante a ser pesquisado
     * @return objeto ClienteDAO caso o representante exista
     */
    public static Cliente clienteCpf(String cpf) throws SQLException {
        Cliente cliente = new Cliente();
        cpf = cpf.trim();

        abreCon();
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
    public static List<Cliente> clienteTodos() throws SQLException {
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
     * Método que pesquisa um cliente no banco de dados pelo ID
     *
     * @param id ID do representante a ser pesquisado
     * @return objeto ClienteDAO
     */
    public static Cliente clienteId(int id) throws SQLException {
        Cliente cliente = new Cliente();

        abreCon();
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
    public static List<Cliente> clienteNome(String nome) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        nome = nome.trim();

        abreCon();
        nomeTabela = ClsBD.getTblCliente();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getCliNome() + " like '" + nome + "%'"
                + " OR " + ClsBD.getCliNome() + " like '%" + nome + "'"
                + " OR " + ClsBD.getCliNome() + " like '%" + nome + "%'");

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
     * Método que pesquisa um representante no banco de dados pelo ID
     *
     * @param id ID do representante a ser pesquisado
     * @return objeto ClienteDAO
     */
    public static Representante representanteId(int id) throws SQLException {
        Representante representante = new Representante();

        abreCon();
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
    public static Representante representanteCpf(String cpf) throws SQLException {
        Representante representante = new Representante();
        cpf = cpf.trim();

        abreCon();
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
    public static List<Representante> representanteNome(String nome) throws SQLException {
        List<Representante> representantes = new ArrayList<>();
        nome = nome.trim();

        abreCon();
        nomeTabela = ClsBD.getTblRepresentante();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getRepnome() + " like '" + nome + "%'"
                + " OR " + ClsBD.getRepnome() + " like '%" + nome + "'"
                + " OR " + ClsBD.getRepnome() + " like '%" + nome + "%'");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Representante representante = new Representante();
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
    public static List<Representante> representanteTodos() throws SQLException {
        List<Representante> representantes = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getTblRepresentante();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Representante representante = new Representante();
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
     * Método que pesquisa orçamento pelo ID do representante, e retorna uma
     * lista com todos os orçamentos que ele possui.
     *
     * @param id ID do cliente
     * @return ArrayList com os orçamentos encontrados
     */
    public static List<Orcamento> orcamentoIdCli(int id) throws SQLException {
        List<Orcamento> orcamentos = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getTblOrcamento();
        String cpfCli = ClsBD.getOrcClienteId();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + cpfCli + " = '" + id + "'");

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Orcamento orcamento = new Orcamento();

            orcamento.setId(rs.getInt(ClsBD.getOrcId()));
            orcamento.setCriacao(rs.getDate(ClsBD.getOrcCriacao()));
            orcamento.setIdCliente(rs.getInt(ClsBD.getOrcClienteId()));
            orcamento.setSessoes(rs.getInt(ClsBD.getOrcNSessoes()));
            orcamento.setTipoPagamento(rs.getString(ClsBD.getOrcTipoPag()));
            orcamento.setValor(rs.getDouble(ClsBD.getOrcValor()));
            orcamento.setDescricao(rs.getString(ClsBD.getOrcDesc()));

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
    public static Orcamento orcamento(int id) throws SQLException {
        Orcamento orcamento = new Orcamento();

        abreCon();
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
            orcamento.setDescricao(rs.getString(ClsBD.getOrcDesc()));
        }

        return orcamento;
    }

    /**
     * Método que pesquisa sessão pelo ID do cliente, e retorna uma lista com
     * todos as sessões que ele possui.
     *
     * @param id ID do cliente
     * @return ArrayList com os orçamentos encontrados
     * @throws java.sql.SQLException
     */
    public static List<Sessao> sessaoIdCli(int id) throws SQLException {
        List<Sessao> sessoes = new ArrayList<>();
        List<Orcamento> orcamentos = new ArrayList<>();

        abreCon();
        orcamentos = orcamentoIdCli(id);

        int qtde = orcamentos.size();
        for (int i = 0; i < qtde; i++) {
            nomeTabela = ClsBD.getTblSessao();

            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                    + " where " + ClsBD.getSesIdOrc() + " = '" + orcamentos.get(i).getId() + "'");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Sessao sessao = new Sessao();

                sessao.setId(rs.getInt(ClsBD.getSesId()));
                sessao.setConcluida(rs.getBoolean(ClsBD.getSesConcluida()));
                sessao.setData(rs.getDate(ClsBD.getSesData()));
                sessao.setDesconto(rs.getInt(ClsBD.getSesDesconto()));
                sessao.setHora(rs.getTime(ClsBD.getSesHora()));
                sessao.setIdOrcamento(rs.getInt(ClsBD.getSesIdOrc()));
                sessao.setPagamento(rs.getString(ClsBD.getSesTipoPagamento()));
                sessao.setValor(rs.getInt(ClsBD.getSesValor()));

                sessoes.add(sessao);
            }

        }

        return sessoes;
    }

    /**
     * Método que pesquisa sessão pelo ID do orçamento, e retorna uma lista com
     * todos as sessões que ele possui.
     *
     * @param id ID do orçamento
     * @return ArrayList com as sessoes encontradas
     * @throws java.sql.SQLException
     */
    public static List<Sessao> sessaoIdOrc(int id) throws SQLException {
        List<Sessao> sessoes = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getTblSessao();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getSesIdOrc() + " = '" + id + "'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Sessao sessao = new Sessao();

            sessao.setId(rs.getInt(ClsBD.getSesId()));
            sessao.setConcluida(rs.getBoolean(ClsBD.getSesConcluida()));
            sessao.setData(rs.getDate(ClsBD.getSesData()));
            sessao.setDesconto(rs.getInt(ClsBD.getSesDesconto()));
            sessao.setHora(rs.getTime(ClsBD.getSesHora()));
            sessao.setIdOrcamento(rs.getInt(ClsBD.getSesIdOrc()));
            sessao.setPagamento(rs.getString(ClsBD.getSesTipoPagamento()));
            sessao.setValor(rs.getInt(ClsBD.getSesValor()));

            sessoes.add(sessao);
        }

        return sessoes;
    }

    /**
     * Pesquisa uma sessão pelo ID
     *
     * @param id da sessao
     * @return sessao
     * @throws SQLException
     */
    public static Sessao sessaoId(int id) throws SQLException {
        Sessao sessao = new Sessao();

        abreCon();
        nomeTabela = ClsBD.getTblSessao();

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela
                + " where " + ClsBD.getSesId() + " = '" + id + "'");

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            sessao.setId(rs.getInt(ClsBD.getSesId()));
            sessao.setConcluida(rs.getBoolean(ClsBD.getSesConcluida()));
            sessao.setData(rs.getDate(ClsBD.getSesData()));
            sessao.setDesconto(rs.getInt(ClsBD.getSesDesconto()));
            sessao.setHora(rs.getTime(ClsBD.getSesHora()));
            sessao.setIdOrcamento(rs.getInt(ClsBD.getSesIdOrc()));
            sessao.setPagamento(rs.getString(ClsBD.getSesTipoPagamento()));
            sessao.setValor(rs.getInt(ClsBD.getSesValor()));
        }

        return sessao;
    }

    /**
     * Retorna todos os registros de login da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List<Registro> auditLogin() throws SQLException {

        List<Registro> registros = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();

        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'login'");

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
    public static List<Registro> auditLogin(String nick) throws SQLException {

        List<Registro> registros = new ArrayList<>();
        Usuario usuario = JDBCConsulta.usuarioNick(nick);

        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData();

        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'login'"
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

        return registros;
    }
    
    /**
     * Retorna todos os registros de insert da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List<Registro> auditCad(String nick) throws SQLException {

        List<Registro> registros = new ArrayList<>();
        Usuario usuario = JDBCConsulta.usuarioNick(nick);

        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", " 
                + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();

        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'insert'"
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

        return registros;
    }
    
    /**
     * Retorna todos os registros de update da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List<Registro> auditEdit(String nick) throws SQLException {

        List<Registro> registros = new ArrayList<>();
        Usuario usuario = JDBCConsulta.usuarioNick(nick);

        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() + ", "
                + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " +  ClsBD.getAudDesc() + ", " + ClsBD.getAudData()
                + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();

        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'update'"
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

        return registros;
    }
    
    /**
     * Retorna todos os registros de delete da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List<Registro> auditDel(String nick) throws SQLException {

        List<Registro> registros = new ArrayList<>();
        Usuario usuario = JDBCConsulta.usuarioNick(nick);

        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() 
                + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudDesc() 
                + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();

        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'delete'"
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

        return registros;
    }

    
    /**
     * Retorna todos os registros de insert da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List<Registro> auditCad() throws SQLException {

        List<Registro> registros = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() 
                + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudDesc() 
                + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();

        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'insert'");

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
     * Retorna todos os registros de update da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List<Registro> auditEdit() throws SQLException {

        List<Registro> registros = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() 
                + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudDesc() 
                + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef() 
                + ", " + ClsBD.getAudAntes() + ", " + ClsBD.getAudDepois();

        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'update'");

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

        return registros;
    }
    
    /**
     * Retorna todos os registros de delete da auditoria
     *
     * @return
     * @throws SQLException
     */
    public static List<Registro> auditDel() throws SQLException {

        List<Registro> registros = new ArrayList<>();

        abreCon();
        nomeTabela = ClsBD.getTblAuditoria();
        String campos = ClsBD.getAudId() + ", " + ClsBD.getAudIdLogin() + ", " + ClsBD.getAudAcao() 
                + ", " + ClsBD.getAudDesc() + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudDesc() 
                + ", " + ClsBD.getAudData() + ", " + ClsBD.getAudTabela() + ", " + ClsBD.getAudRef();

        PreparedStatement stmt = JDBCConsulta.con.prepareStatement("select " + campos + " from " + nomeTabela + " where " + ClsBD.getAudAcao() + " = 'delete'");

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
