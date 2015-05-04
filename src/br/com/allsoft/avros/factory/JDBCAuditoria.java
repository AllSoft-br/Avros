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
import br.com.allsoft.avros.dao.SessaoDAO;
import br.com.allsoft.avros.dao.UsuarioDAO;
import br.com.allsoft.avros.exceptions.AuditoriaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que insere e busca dados da auditoria
 *
 * @author Luana
 */
public class JDBCAuditoria {

    //Variáveis obrigatórias
    private static String tabela; //Nome da tabela alterada
    private static String acao; //Update, insert, login ou delete
    private static String descricao; //Descrição da ação ocorrida. Texto que ficará à mostra no histórico
    private static String sql = "-"; //Código sql utilizado
    private static String antes = "-"; //No caso de updates, como o campo era antes
    private static String depois = "-"; //No caso de updates, como o campo ficou no fim
    private static String campo = "-";
    private static int codDado; //ID do dado alterado
    private static int idLogin; //ID do login que fez a alteração

    static Connection con;

    /**
     * Método que grava a inserção de um novo cliente na tabela auditoria do BD,
     * quem inseriu e o horário da inserção.
     *
     * @param usuario usuário que fez o cadastro
     * @param cliente cliente que foi cadastrado
     * @param codSql
     * @throws AuditoriaException
     */
    public static void inserirCliente(UsuarioDAO usuario, ClienteDAO cliente, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblCliente();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou o cliente " + cliente.getNome();
            sql = codSql;
            codDado = cliente.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
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

    public static void inserirRel(UsuarioDAO usuario, ClienteDAO cliente, RepresentanteDAO representante, String parentesco, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblRel();
            acao = "insert";
            descricao = usuario.getNick() + " relacionou o representante " + representante.getNome() + " como " + parentesco + " do cliente " + cliente.getNome();
            sql = codSql;
            codDado = cliente.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
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
     * @throws SQLException
     */
    public static void inserirParentesco(UsuarioDAO usuario, String parentesco, int id, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblParentesco();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou um novo grau de parentesco: " + parentesco;
            sql = codSql;
            codDado = id;
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
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
     * @throws SQLException
     */
    public static void inserirSessao(UsuarioDAO usuario, SessaoDAO sessao, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblSessao();
            acao = "insert";
            descricao = usuario.getNick() + " agendou uma nova sessao no orçamento código " + String.valueOf(sessao.getIdOrcamento()) + " para o cliente " + sessao.getCliente();
            sql = codSql;
            codDado = sessao.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
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
    public static void inserirRespresentante(UsuarioDAO usuario, RepresentanteDAO responsavel, String codSql) throws AuditoriaException{
        try {
            tabela = ClsBD.getTblRepresentante();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou o representante " + responsavel.getNome();
            sql = codSql;
            codDado = responsavel.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
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
    public static void inserirUsuario(UsuarioDAO usuario, UsuarioDAO novoUsuario, String codSql) throws AuditoriaException{
        try {
            tabela = ClsBD.getTblLogin();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou o usuário " + novoUsuario.getNome();
            sql = codSql;
            codDado = usuario.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
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
    public static void inserirOrcamento(UsuarioDAO usuario, OrcamentoDAO orcamento, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblOrcamento();
            acao = "insert";
            descricao = usuario.getNick() + " cadastrou o orçamento de código " + String.valueOf(orcamento.getId());
            sql = codSql;
            codDado = orcamento.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
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
     * Método que salva a mudança de nickname do usuário
     *
     * @param resp usuario responsavel pelo update
     * @param usuario objeto UsuarioDAO do usuário antes do update
     * @param nick novo nick que o usuário escolheu
     * @param codSql
     * @throws java.sql.SQLException
     */
    public static void modificaNick(UsuarioDAO resp, UsuarioDAO usuario, String nick, String codSql) throws AuditoriaException{
        try {
            tabela = ClsBD.getTblLogin();
            acao = "update";
            descricao = resp.getNick() + " mudou o nickname de " + usuario.getNick() + " para " + nick;
            sql = codSql;
            codDado = usuario.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = usuario.getNick();
            depois = nick;
            campo = ClsBD.getUsuarionick();
            
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
     * Método que salva a mudança de nome do usuário
     *
     * @param usuario UsuarioDAO antes do update
     * @param resp usuario responsavel pela mudança
     * @param nome novo nome que o usuário escolheu
     * @param codSql
     * @throws java.sql.SQLException
     */
    public static void modificaNomeUsuario(UsuarioDAO resp, UsuarioDAO usuario, String nome, String codSql) throws AuditoriaException{
        try {
            tabela = ClsBD.getTblLogin();
            acao = "update";
            descricao = usuario.getNick() + " mudou seu nome de " + usuario.getNome() + " para " + nome;
            sql = codSql;
            codDado = usuario.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = usuario.getNome();
            depois = nome;
            campo = ClsBD.getUsuarionome();
            
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
     * Método que salva a mudança de nome do cliente
     *
     * @param usuario responsavel pelo update
     * @param cliente ClienteDAO antes do update
     * @param nome novo nome que o usuário escolheu
     * @param codSql
     */
    public static void modificaNomeCliente(UsuarioDAO usuario, ClienteDAO cliente, String nome, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblCliente();
            acao = "update";
            descricao = usuario.getNick() + " mudou o nome de um cliente de " + cliente.getNome() + " para " + nome;
            sql = codSql;
            codDado = usuario.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = cliente.getNome();
            depois = nome;
            campo = ClsBD.getCliNome();
            
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
     * Método que salva a mudança de senha do usuário
     *
     * @param usuarioResp usuario responsável pela mudança
     * @param usuarioModf usuario que foi modificado, antes do update
     * @param senha nova senha do usuário
     * @param codSql
     * @throws java.sql.SQLException
     */
    public static void modificaSenha(UsuarioDAO usuarioResp, UsuarioDAO usuarioModf, char[] senha, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblLogin();
            acao = "update";
            descricao = usuarioResp.getNick() + " modificou a senha do usuário " + usuarioModf.getNick();
            sql = codSql;
            codDado = usuarioModf.getId();
            idLogin = usuarioResp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(usuarioModf.getSenha());
            depois = String.valueOf(senha);
            campo = ClsBD.getUsuarioSenha();
            
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
     * Grava as alterações de atividade das contas na auditoria
     * 
     * @param resp usuário responsavel pelo update
     * @param usuario que foi modificado, antes do update
     * @param codSql
     * @throws SQLException 
     */
    public static void modificaUsuarioAtivo(UsuarioDAO resp, UsuarioDAO usuario, boolean ativo, String codSql) throws AuditoriaException {
        try {
            String oq, oq1;
            int id;
            if (ativo) {
                oq = "ativada";
            } else {
                oq = "desativada";
            }   if (usuario.isAtivo()) {
            oq1 = "ativada";
        } else {
            oq1 = "desativada";
        }   if(resp.getNick() == null){
            resp.setNick("sistema");
            resp.setId(usuario.getId());
        }   descricao = resp.getNick() + " atualizou a conta do usuário " + usuario.getNick() + " de " + oq1 + " para " + oq;
        tabela = ClsBD.getTblLogin();
            acao = "update";
            sql = codSql;
            codDado = usuario.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(usuario.isAtivo());
            depois = String.valueOf(ativo);
            campo = ClsBD.getUsuarioAtivo();
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
    
    public static void modificaUsuarioAdmin(UsuarioDAO resp, UsuarioDAO usuario, boolean admin, String codSql) throws AuditoriaException {
        try {
            String oq, oq1;
            if(usuario.isAdmin()){
                oq1 = "usuário administrador";
            } else {
                oq1 = "conta comum";
            }   if(admin){
            oq = "usuário administrador";
        } else {
            oq = "conta comum";
        }   tabela = ClsBD.getTblLogin();
        acao = "update";
            descricao = resp.getNick() + " modificou o tipo da conta do usuário " + usuario.getNick() + " de " + oq1 + " para " + oq;
            sql = codSql;
            codDado = usuario.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(usuario.isAdmin());
            depois = String.valueOf(admin);
            campo = ClsBD.getUsuarioAdmin();
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
    
    public static void modificaClienteSexo(UsuarioDAO resp, ClienteDAO cliente, boolean feminino, String codSql) throws AuditoriaException{
        try {
            String oq, oq1;
            if(cliente.isFeminino()){
                oq1 = "feminino";
            } else {
                oq1 = "masculino";
            }   if(feminino){
            oq = "feminino";
        } else {
            oq = "masculino";
        }   tabela = ClsBD.getTblCliente();
        acao = "update";
            descricao = resp.getNick() + " modificou sexo do cliente " + cliente.getNome() + " de " + oq1 + " para " + oq;
            sql = codSql;
            codDado = cliente.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(cliente.isFeminino());
            depois = String.valueOf(feminino);
            campo = ClsBD.getCliSexo();
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
     * Método que armazena o horário que o usuário fez login
     *
     * @param usuario usuário que fez o login
     * @param codSql codigo sql utilizado
     */
    public static void login(UsuarioDAO usuario, String codSql) throws AuditoriaException{
        try {
            tabela = ClsBD.getTblLogin();
            acao = "login";
            descricao = usuario.getNick() + " logou no sistema";
            sql = codSql;
            codDado = usuario.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
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
    public static void logout(UsuarioDAO usuario) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblCliente();
            acao = "login";
            descricao = usuario.getNick() + " saiu do sistema";
            codDado = usuario.getId();
            idLogin = usuario.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            
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
}
