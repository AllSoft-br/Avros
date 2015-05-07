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
import static br.com.allsoft.avros.factory.JDBCAuditoria.acao;
import static br.com.allsoft.avros.factory.JDBCAuditoria.con;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Luana Nascimento
 */
public class AuditoriaInsere extends JDBCAuditoria{
    
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
    public static void inserirRel(UsuarioDAO usuario, ClienteDAO cliente, RepresentanteDAO representante, String parentesco, String codSql) throws AuditoriaException {
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
    public static void inserirParentesco(UsuarioDAO usuario, String parentesco, int id, String codSql) throws AuditoriaException {
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
    public static void inserirSessao(UsuarioDAO usuario, SessaoDAO sessao, String codSql) throws AuditoriaException {
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
    public static void inserirRespresentante(UsuarioDAO usuario, RepresentanteDAO responsavel, String codSql) throws AuditoriaException {
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
    public static void inserirUsuario(UsuarioDAO usuario, UsuarioDAO novoUsuario, String codSql) throws AuditoriaException {
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
    public static void inserirOrcamento(UsuarioDAO usuario, OrcamentoDAO orcamento, String codSql) throws AuditoriaException {
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
}
