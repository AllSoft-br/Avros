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
import br.com.allsoft.avros.modelo.ClsBD;
import br.com.allsoft.avros.modelo.Orcamento;
import br.com.allsoft.avros.modelo.Sessao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe DAO para as sessões de tatuagem
 * 
 * @author Luana Nascimento
 */


public class SessaoDAO {
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
     * Pesquisa uma sessão pelo ID
     *
     * @param id da sessao
     * @return sessao
     * @throws SQLException
     */
    public static Sessao csessaoId(int id) throws SQLException {
        Sessao sessao = new Sessao();
        abreCon();
        nomeTabela = ClsBD.getTblSessao();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getSesId() + " = '" + id + "'");
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
     * Método que pesquisa sessão pelo ID do cliente, e retorna uma lista com
     * todos as sessões que ele possui.
     *
     * @param id ID do cliente
     * @return ArrayList com os orçamentos encontrados
     * @throws java.sql.SQLException
     */
    public static List csessaoIdCli(int id) throws SQLException {
        List<Sessao> sessoes = new ArrayList<>();
        List<Orcamento> orcamentos = new ArrayList<>();
        abreCon();
        orcamentos = OrcamentoDAO.corcamentoIdCli(id);
        int qtde = orcamentos.size();
        for (int i = 0; i < qtde; i++) {
            nomeTabela = ClsBD.getTblSessao();
            PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getSesIdOrc() + " = '" + orcamentos.get(i).getId() + "'");
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
    public static List csessaoIdOrc(int id) throws SQLException {
        List<Sessao> sessoes = new ArrayList<>();
        abreCon();
        nomeTabela = ClsBD.getTblSessao();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getSesIdOrc() + " = '" + id + "'");
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
     * Deleta uma dsessao
     *
     * @param sessao com nome do cliente e id do orçamento setados
     * @throws SQLException
     */
    public static void dsessao(Sessao sessao) throws SQLException {
        nomeTabela = ClsBD.getTblSessao();
        int id = sessao.getId();
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "DELETE from " + nomeTabela + " where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaDelete.sessao(FrmLogin.usuario, sessao, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para agendar uma nova sessão
     *
     * @param sessao objeto Sessao
     * @return ID da sessão criada
     * @throws SQLException
     * @throws IOException
     */
    public static int inserirSessao(Sessao sessao) throws SQLException, IOException {
        nomeTabela = ClsBD.getTblSessao();
        int retorno = 0;
        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);
        String sql = "insert into " + nomeTabela + "(" + ClsBD.getSesData() + ", " + ClsBD.getSesDesconto() + ", " + ClsBD.getSesHora() + ", " + ClsBD.getSesIdOrc() + ", " + ClsBD.getSesTipoPagamento() + ", " + ClsBD.getSesValor() + ") values (?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setDate(1, sessao.getData());
        stmt.setDouble(2, sessao.getDesconto());
        stmt.setTime(3, sessao.getHora());
        stmt.setInt(4, sessao.getIdOrcamento());
        stmt.setString(5, sessao.getPagamento());
        stmt.setDouble(6, sessao.getValor());
        sql = stmt.toString();
        stmt.execute();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            retorno = rs.getInt(1);
        }
        stmt.close();
        con.commit();
        con.close();
        sessao.setId(retorno);
        try {
            AuditoriaInsere.inserirSessao(FrmLogin.usuario, sessao, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Muda a data de uma sessao
     *
     * @param id da sessao
     * @param data nova data
     * @throws SQLException
     */
    public static void usessaoData(int id, Date data) throws SQLException {
        nomeTabela = ClsBD.getTblSessao();
        Sessao sessao = SessaoDAO.csessaoId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getSesData() + "= ? " + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setDate(1, data);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.sessaoData(FrmLogin.usuario, sessao, data, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda a forma de pagamento de uma sessao
     *
     * @param id da sessao
     * @param pagamento novo pagamento
     * @throws SQLException
     */
    public static void usessaoPagamento(int id, String pagamento) throws SQLException {
        nomeTabela = ClsBD.getTblSessao();
        Sessao sessao = SessaoDAO.csessaoId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getSesTipoPagamento() + "= ? " + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, pagamento);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.sessaoPagamento(FrmLogin.usuario, sessao, pagamento, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda o valor de desconto de uma sessao
     *
     * @param id da sessao
     * @param desconto novo desconto
     * @throws SQLException
     */
    public static void usessaoDesconto(int id, double desconto) throws SQLException {
        nomeTabela = ClsBD.getTblSessao();
        Sessao sessao = SessaoDAO.csessaoId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getSesDesconto() + " = ? " + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setDouble(1, desconto);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.sessaoDesconto(FrmLogin.usuario, sessao, desconto, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda a forma de pagamento de uma sessao
     *
     * @param id da sessao
     * @param hora novo horario
     * @throws SQLException
     */
    public static void usessaoHora(int id, Time hora) throws SQLException {
        nomeTabela = ClsBD.getTblSessao();
        Sessao sessao = SessaoDAO.csessaoId(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getSesHora() + "= ? " + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setTime(1, hora);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.sessaoHora(FrmLogin.usuario, sessao, hora, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda o status de uma sessao
     *
     * @param idSes da sessao
     * @param status true = paga, false = não pagou
     * @throws SQLException
     */
    public static void usessaoConcluida(int idSes, boolean status, int idOrc) throws SQLException {
        nomeTabela = ClsBD.getTblSessao();
        Sessao sessao = SessaoDAO.csessaoId(idSes);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getSesConcluida() + "= ? " + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setBoolean(1, status);
        stmt.setInt(2, idSes);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        String sql2 = "call orcamento_concluido(?)";
        PreparedStatement stmt2 = con.prepareStatement(sql2);
        stmt2.setInt(1, idOrc);
        stmt2.execute();
        stmt2.close();
        con.close();
        try {
            AuditoriaUpdate.sessaoConcluida(FrmLogin.usuario, sessao, status, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
