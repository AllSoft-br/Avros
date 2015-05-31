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
import br.com.allsoft.avros.interfaces.FrmLogin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe para fazer updates no BD do sistema.
 *
 * @author Luana
 */
public class JDBCUpdate {

    static Connection con = null;
    static String nomeTabela;

    /**
     * Método que cria uma nova senha para o usuário
     *
     * @param senha nova senha
     * @param id código do usuário
     * @throws SQLException
     */
    public static void usuarioSenha(char[] senha, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        
        UsuarioDAO usuario = JDBCConsulta.usuarioId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarioSenha() + "= ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, String.valueOf(senha));
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();

        try {
            //salva modificações na tabela auditoria
            AuditoriaUpdate.modificaSenha(FrmLogin.usuario, usuario, senha, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

        FrmLogin.usuario.setSenha(senha);

    }
    
    /**
     * Modifica o status do usuario
     * 
     * @param ativo se esta ativo ou nao 
     * @param login do usuario
     * @throws SQLException 
     */
    public static void usuarioAtivo(boolean ativo, String login) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();

        UsuarioDAO usuario = JDBCConsulta.usuarioNick(login);
        
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarioAtivo() + "= ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setBoolean(1, ativo);
        stmt.setInt(2, usuario.getId());
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();

        try {
            AuditoriaUpdate.modificaUsuarioAtivo(FrmLogin.usuario, usuario, ativo, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Modifica o status do usuario
     * 
     * @param ativo se esta ativo ou nao 
     * @param id do usuario
     * @throws SQLException 
     */
    public static void usuarioAtivo(boolean ativo, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        
        UsuarioDAO usuario = JDBCConsulta.usuarioId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarioAtivo() + " = ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setBoolean(1, ativo);
        stmt.setInt(2, id);

        sql = stmt.toString();
        
        stmt.execute();
        stmt.close();
        con.close();

        try {
            AuditoriaUpdate.modificaUsuarioAtivo(FrmLogin.usuario, usuario, ativo, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que atualiza o nome do usuário.
     *
     * @param nome novo nome do usuário
     * @param id id do usuário que será modificado
     * @throws SQLException
     */
    public static void usuarioNome(String nome, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        
        UsuarioDAO usuario = JDBCConsulta.usuarioId(id);
        
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarionome() + " = ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, nome);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaUpdate.modificaNomeUsuario(FrmLogin.usuario, usuario, nome, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que atualiza o nick do usuário.
     *
     * @param nick novo nick do usuário
     * @param id id do usuário que será modificado
     * @throws SQLException
     */
    public static void usuarioNick(String nick, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        
        UsuarioDAO usuario = JDBCConsulta.usuarioNick(nick);
        
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarionick() + " = ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, nick);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();

        try {
            AuditoriaUpdate.modificaNick(FrmLogin.usuario, usuario, nick, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que transforma um usuário em admin ou conta comum.
     *
     * @param admin se o usuário será admin ou não
     * @param id id do usuário que será modificado
     * @throws SQLException
     */
    public static void usuarioAdmin(boolean admin, int id) throws SQLException {
        nomeTabela = ClsBD.getTblLogin();
        
        UsuarioDAO usuario = JDBCConsulta.usuarioId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getUsuarioAdmin() + " = ? "
                + "where " + ClsBD.getUsuarioId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setBoolean(1, admin);
        stmt.setInt(2, id);
        
        sql = stmt.toString();
        
        stmt.execute();
        stmt.close();
        con.close();

        
        try {
            AuditoriaUpdate.modificaUsuarioAdmin(FrmLogin.usuario, usuario, admin, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Modifica o nome de um cliente.
     *
     * @param nome novo nome do cliente
     * @param id ID do cliente a mudar o nome
     * @throws SQLException
     */
    public static void clienteNome(String nome, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();
        
        ClienteDAO cliente = JDBCConsulta.clienteId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliNome() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
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
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Muda o sexo do cliente
     *
     * @param feminino se o sexo é feminino ou não
     * @param id ID do cliente a mudar
     * @throws SQLException
     */
    public static void clienteSexo(Boolean feminino, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();
        
        ClienteDAO cliente = JDBCConsulta.clienteId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliSexo() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
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
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Muda a data de nascimento do cliente
     *
     * @param data nova data
     * @param id ID do cliente
     * @throws SQLException
     */
    public static void clienteNasc(Date data, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();

        ClienteDAO cliente = JDBCConsulta.clienteId(id);
        
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliNasc() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
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
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda o numero de telefone do cliente
     *
     * @param tel novo telefone
     * @param id ID do cliente
     * @throws SQLException
     */
    public static void clienteTel(String tel, int id) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();
        
        ClienteDAO cliente = JDBCConsulta.clienteId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliTel() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
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
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Modifica o nome de um representante.
     *
     * @param nome novo nome do representante
     * @param id ID do representante a mudar o nome
     * @throws SQLException
     */
    public static void representanteNome(String nome, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();

        RepresentanteDAO representante = JDBCConsulta.representanteId(id);
        
        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliNome() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, nome);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaUpdate.modificaRepNome(FrmLogin.usuario, representante, nome, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Muda o sexo do representante
     *
     * @param feminino se o sexo é feminino ou não
     * @param id ID do representante a mudar
     * @throws SQLException
     */
    public static void representanteSexo(Boolean feminino, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();
        
        RepresentanteDAO representante = JDBCConsulta.representanteId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliSexo() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setBoolean(1, feminino);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaUpdate.modificaRepSexo(FrmLogin.usuario, representante, feminino, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Muda a data de nascimento do representante
     *
     * @param data nova data
     * @param id ID do representante
     * @throws SQLException
     */
    public static void representanteNasc(Date data, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();
        
        RepresentanteDAO representante = JDBCConsulta.representanteId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliNasc() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setDate(1, data);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();

        try {
            AuditoriaUpdate.modificaRepNasc(FrmLogin.usuario, representante, data, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda o numero de telefone do representante
     *
     * @param tel novo telefone
     * @param id ID do representante
     * @throws SQLException
     */
    public static void representanteTel(String tel, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();
        
        RepresentanteDAO representante = JDBCConsulta.representanteId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getCliTel() + "= ? "
                + "where " + ClsBD.getCliId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, tel);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();

        try {
            AuditoriaUpdate.modificaRepTel(FrmLogin.usuario, representante, tel, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muda a forma de pagamento de uma sessao
     * 
     * @param id da sessao
     * @param pagamento novo pagamento
     * @throws SQLException 
     */
    public static void sessaoPagamento(int id, String pagamento) throws SQLException{
        nomeTabela = ClsBD.getTblSessao();
        
        SessaoDAO sessao = JDBCConsulta.sessaoId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getSesTipoPagamento() + "= ? "
                + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
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
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muda a forma de pagamento de uma sessao
     * 
     * @param id do orcamento
     * @param pagamento novo pagamento
     * @throws SQLException 
     */
    public static void orcamentoPagamento(int id, String pagamento) throws SQLException{
        nomeTabela = ClsBD.getTblOrcamento();
        
        OrcamentoDAO orcamento = JDBCConsulta.orcamento(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getOrcTipoPag() + "= ? "
                + "where " + ClsBD.getOrcId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
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
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muda a descrição de um orçamento
     * 
     * @param id do orçamento
     * @param desc nova descrição
     * @throws SQLException 
     */
    public static void orcamentoDesc(int id, String desc) throws SQLException{
        nomeTabela = ClsBD.getTblOrcamento();
        
        OrcamentoDAO orcamento = JDBCConsulta.orcamento(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getOrcDesc()+ "= ? "
                + "where " + ClsBD.getOrcId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
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
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muda o valor de um orcamento
     * 
     * @param id
     * @param valor
     * @throws SQLException 
     */
    public static void orcamentoValor(int id, double valor) throws SQLException{
        nomeTabela = ClsBD.getTblOrcamento();
        
        OrcamentoDAO orcamento = JDBCConsulta.orcamento(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getOrcValor() + "= ? "
                + "where " + ClsBD.getOrcId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setDouble(1, valor);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaUpdate.orcamentoValor(FrmLogin.usuario, orcamento, valor, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muda o número de sessões de um orcamento
     * 
     * @param id
     * @param sessoes
     * @throws SQLException 
     */
    public static void orcamentoSessoes(int id, int sessoes) throws SQLException{
        nomeTabela = ClsBD.getTblOrcamento();
        
        OrcamentoDAO orcamento = JDBCConsulta.orcamento(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getOrcNSessoes() + "= ? "
                + "where " + ClsBD.getOrcId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setInt(1, sessoes);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();
        
        try  {
            AuditoriaUpdate.orcamentoSessoes(FrmLogin.usuario, orcamento, sessoes, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Muda a data de uma sessao
     * 
     * @param id da sessao
     * @param data nova data
     * @throws SQLException 
     */
    public static void sessaoData(int id, Date data) throws SQLException{
        nomeTabela = ClsBD.getTblSessao();
        
        SessaoDAO sessao = JDBCConsulta.sessaoId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getSesData() + "= ? "
                + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setDate(1, data);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaUpdate.sessaoData(FrmLogin.usuario, sessao, data, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muda a forma de pagamento de uma sessao
     * 
     * @param id da sessao
     * @param hora novo horario
     * @throws SQLException 
     */
    public static void sessaoHora(int id, Time hora) throws SQLException{
        nomeTabela = ClsBD.getTblSessao();
        
        SessaoDAO sessao = JDBCConsulta.sessaoId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getSesHora() + "= ? "
                + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setTime(1, hora);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaUpdate.sessaoHora(FrmLogin.usuario, sessao, hora, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muda o valor de desconto de uma sessao
     * 
     * @param id da sessao
     * @param desconto novo desconto
     * @throws SQLException 
     */
    public static void sessaoDesconto(int id, double desconto) throws SQLException{
        nomeTabela = ClsBD.getTblSessao();
        
        SessaoDAO sessao = JDBCConsulta.sessaoId(id);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getSesDesconto() + " = ? "
                + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setDouble(1, desconto);
        stmt.setInt(2, id);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();
        
        try {
            AuditoriaUpdate.sessaoDesconto(FrmLogin.usuario, sessao, desconto, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muda o status de uma sessao
     * 
     * @param idSes da sessao
     * @param status true = paga, false = não pagou
     * @throws SQLException 
     */
    public static void sessaoConcluida(int idSes, boolean status, int idOrc) throws SQLException{
        nomeTabela = ClsBD.getTblSessao();
        
        SessaoDAO sessao = JDBCConsulta.sessaoId(idSes);

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "UPDATE " + nomeTabela
                + " set " + ClsBD.getSesConcluida() + "= ? "
                + "where " + ClsBD.getSesId() + " = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
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
            Logger.getLogger(JDBCUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
