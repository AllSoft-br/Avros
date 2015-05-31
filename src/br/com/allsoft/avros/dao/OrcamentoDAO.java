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
import java.io.IOException;
import java.sql.Connection;
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
 * Classe DAO para os orçamentos criados
 *
 * @author Luana Nascimento
 */
public class OrcamentoDAO {

//Variáveis

    public static Connection con = null;
    static String nomeTabela;

//Métodos
    /**
     * Abre uma conexão e fecha a antiga
     *
     * @throws SQLException
     */
    private static void abreCon() throws SQLException {
        if ((con == null) || (con.isClosed())) {
            con = ConexaoMySQL.getConexaoMySQL();
        }
    }

    /**
     * Método que procura orçamentos pelo ID do orçamento
     *
     * @param id ID do orçamento
     * @return OrcamentoDAO com o orçamento encontrado
     */
    public static Orcamento corcamento(int id) throws SQLException {
        Orcamento orcamento = new Orcamento();
        abreCon();
        nomeTabela = ClsBD.getTblOrcamento();
        String bdid = ClsBD.getOrcId();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + bdid + " = '" + id + "'");
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
     * Método que pesquisa orçamento pelo ID do representante, e retorna uma
     * lista com todos os orçamentos que ele possui.
     *
     * @param id ID do cliente
     * @return ArrayList com os orçamentos encontrados
     */
    public static List corcamentoIdCli(int id) throws SQLException {
        List<Orcamento> orcamentos = new ArrayList<>();
        abreCon();
        nomeTabela = ClsBD.getTblOrcamento();
        String cpfCli = ClsBD.getOrcClienteId();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + cpfCli + " = '" + id + "'");
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
     * Deleta um orçamento
     *
     * @param orcamento com nome do cliente setado
     * @throws SQLException
     */
    public static void dorcamento(Orcamento orcamento) throws SQLException {
         nomeTabela = ClsBD.getTblSessao();
        int id = orcamento.getId();
         con = ConexaoMySQL.getConexaoMySQL();
        String sql = "call del_orcamento(?)";
        PreparedStatement stmt =  con.prepareStatement(sql);
        stmt.setInt(1, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
         con.close();
        try {
            AuditoriaDelete.orcamento(FrmLogin.usuario, orcamento, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int inserirOrcamento(Orcamento orcamento) throws SQLException, IOException {
        nomeTabela = ClsBD.getTblOrcamento();
        int cod = 0;
        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);
        String sql = "insert into " + nomeTabela + "(" + ClsBD.getOrcClienteId() + ", " + ClsBD.getOrcTipoPag() + ", " + ClsBD.getOrcValor() + ", " + ClsBD.getOrcNSessoes() + ", " + ClsBD.getOrcDesc() + ") values (?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, orcamento.getIdCliente());
        stmt.setString(2, orcamento.getTipoPagamento());
        stmt.setDouble(3, orcamento.getValor());
        stmt.setDouble(4, orcamento.getSessoes());
        stmt.setString(5, orcamento.getDescricao());
        sql = stmt.toString();
        stmt.execute();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            cod = rs.getInt(1);
        }
        stmt.close();
        con.commit();
        con.close();
        orcamento.setId(cod);
        try {
            AuditoriaInsere.inserirOrcamento(FrmLogin.usuario, orcamento, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cod;
    }

    /**
     * Muda o valor de um corcamento
     *
     * @param id
     * @param valor
     * @throws SQLException
     */
    public static void uorcamentoValor(int id, double valor) throws SQLException {
        nomeTabela = ClsBD.getTblOrcamento();
        Orcamento orcamento = OrcamentoDAO.corcamento(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getOrcValor() + "= ? " + "where " + ClsBD.getOrcId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setDouble(1, valor);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.orcamentoValor(FrmLogin.usuario, orcamento, valor, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda o número de sessões de um corcamento
     *
     * @param id
     * @param sessoes
     * @throws SQLException
     */
    public static void uorcamentoSessoes(int id, int sessoes) throws SQLException {
        nomeTabela = ClsBD.getTblOrcamento();
        Orcamento orcamento = OrcamentoDAO.corcamento(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getOrcNSessoes() + "= ? " + "where " + ClsBD.getOrcId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, sessoes);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.orcamentoSessoes(FrmLogin.usuario, orcamento, sessoes, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda a forma de pagamento de uma sessao
     *
     * @param id do corcamento
     * @param pagamento novo pagamento
     * @throws SQLException
     */
    public static void uorcamentoPagamento(int id, String pagamento) throws SQLException {
        nomeTabela = ClsBD.getTblOrcamento();
        Orcamento orcamento = OrcamentoDAO.corcamento(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getOrcTipoPag() + "= ? " + "where " + ClsBD.getOrcId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, pagamento);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.orcamentoPagamento(FrmLogin.usuario, orcamento, pagamento, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda a descrição de um orçamento
     *
     * @param id do orçamento
     * @param desc nova descrição
     * @throws SQLException
     */
    public static void uorcamentoDesc(int id, String desc) throws SQLException {
        nomeTabela = ClsBD.getTblOrcamento();
        Orcamento orcamento = OrcamentoDAO.corcamento(id);
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela + " set " + ClsBD.getOrcDesc() + "= ? " + "where " + ClsBD.getOrcId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, desc);
        stmt.setInt(2, id);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        try {
            AuditoriaUpdate.orcamentoDesc(FrmLogin.usuario, orcamento, desc, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
