/*
 * Copyright (C) 2015 Allsoft
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * the Free Software Foundation, either version 3 of the License, or
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
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Classe que insere valores no banco de dados.
 *
 * @author Luana
 */
public class JDBCInsere {

    static Connection con = null;
    static String nomeTabela;

    /**
     * Método que insere um novo cliente no banco de dados.
     *
     * @param cliente objeto do tipo ClienteDAO com informações do cliente a ser
     * inserido.
     * @param usuarioId ID do usuário que está inserindo o cliente
     * @throws SQLException
     */
    public static void inserirCliente(ClienteDAO cliente, int usuarioId) throws SQLException {
        nomeTabela = ClsBD.getTblCliente();

        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);
        String sql = "insert into " + nomeTabela + "(" + ClsBD.getCliNome() + ", " + ClsBD.getCliCpf() + ", "
                + ClsBD.getCliNasc() + ", " + ClsBD.getCliTel() + ", " + ClsBD.getCliSexo() + ", " + ClsBD.getCliIdUsuario()
                + ") values (?,?,?,?,?, ?)";

        PreparedStatement stmt = con.prepareStatement(sql);
        // preenche os valores
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
            //salva modificações na tabela auditoria
            AuditoriaInsere.inserirCliente(FrmLogin.usuario, cliente, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCInsere.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que insere um novo representante no banco de dados.
     *
     * @param representante objeto do tipo ClienteDAO com informações do
     * representante a ser inserido.
     * @return ID do representante
     * @throws SQLException
     */
    public static int inserirRepresentante(RepresentanteDAO representante) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();

        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);

        String sql = "insert into " + nomeTabela + "(" + ClsBD.getRepnome() + ", " + ClsBD.getRepCpf() + ", "
                + ClsBD.getRepNasc() + ", " + ClsBD.getRepTel() + ", " + ClsBD.getRepSexo()
                + ") values (?,?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // preenche os valores
        stmt.setString(1, representante.getNome());
        stmt.setString(2, representante.getCpf());
        stmt.setDate(3, representante.getNascimento());
        stmt.setString(4, representante.getTel());
        stmt.setBoolean(5, representante.isFeminino());
        
        sql = stmt.toString();

        stmt.execute();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            representante.setId(rs.getInt(1));
        }

        stmt.close();
        con.commit();
        con.close();

        try {
            AuditoriaInsere.inserirRespresentante(FrmLogin.usuario, representante, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCInsere.class.getName()).log(Level.SEVERE, null, ex);
        }

        return representante.getId();
    }

    /**
     * Método que insere um novo responsavel no banco de dados.
     *
     * @param responsavel objeto do tipo RepresentanteDAO com informações do
     * responsavel a ser inserido.
     * @param menor cliente menor de idade que será cadastrado, do tipo
     * ClienteDAO
     * @param parentescoId ID do parentesco, que é referenciado tanto no BD
     * quanto na caixa de seleção da tela de cadastro de responsável
     * @throws SQLException
     */
    public static void inserirClienteMenor(RepresentanteDAO responsavel, ClienteDAO menor, int parentescoId, int usuarioId) throws SQLException {

        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);

        //Insere o responsável primeiro (R)
        String sqlR = "insert into " + ClsBD.getTblRepresentante() + " (" + ClsBD.getRepnome() + ", " + ClsBD.getRepCpf()
                + ", " + ClsBD.getRepNasc() + ", " + ClsBD.getRepTel() + ", " + ClsBD.getRepSexo() + ") "
                + "values (?,?,?,?,?);";
        //String sqlR = "insert into tbl_representante (nome, CPF, data_nasc, tel, sexo) "
        //        + "values (?,?,?,?,?)";

        PreparedStatement stmtR = con.prepareStatement(sqlR, Statement.RETURN_GENERATED_KEYS);
        // preenche os valores
        stmtR.setString(1, responsavel.getNome());
        stmtR.setString(2, responsavel.getCpf());
        stmtR.setDate(3, responsavel.getNascimento());
        stmtR.setString(4, responsavel.getTel());
        stmtR.setBoolean(5, responsavel.isFeminino());
        
        sqlR = stmtR.toString();

        stmtR.execute();

        //Agora, insere o cliente (M)
        String sqlM = "insert into " + ClsBD.getTblCliente() + "(" + ClsBD.getCliNome() + ", " + ClsBD.getCliCpf() + ", "
                + ClsBD.getCliNasc() + ", " + ClsBD.getCliTel() + ", " + ClsBD.getCliSexo() + ", " + ClsBD.getCliIdUsuario()
                + ") values (?,?,?,?,?,?)";

        PreparedStatement stmtM = con.prepareStatement(sqlM, Statement.RETURN_GENERATED_KEYS);
        // preenche os valores
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

        //Cria a relação entre o cliente, o grau de parentesco e o responsável 
        String sql = "insert into " + ClsBD.getTblRel()
                + "(" + ClsBD.getRelClienteId() + ", "
                + ClsBD.getRelParentescoId() + ", "
                + ClsBD.getRelRepresentanteId() + ") values(?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql);
        // preenche os valores 
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

        RepresentanteDAO parentesco = JDBCConsulta.parentesco(menor);

        try {
            AuditoriaInsere.inserirCliente(FrmLogin.usuario, menor, sqlM);
            AuditoriaInsere.inserirRespresentante(FrmLogin.usuario, responsavel, sqlR);
            AuditoriaInsere.inserirRel(FrmLogin.usuario, menor, responsavel, parentesco.getGrau(), sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCInsere.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Relaciona um cliente menor com um responsável.
     *
     * @param repId ID do representante
     * @param cliId ID do cliente menor de idade
     * @param parentescoId ID do tipo de parentesco
     * @throws SQLException
     */
    public static void inserirRelCliRep(int repId, int cliId, int parentescoId) throws SQLException {
        nomeTabela = ClsBD.getTblRel();

        con = ConexaoMySQL.getConexaoMySQL();
        String sql = "insert into " + nomeTabela
                + "(" + ClsBD.getRelClienteId() + ", "
                + ClsBD.getRelParentescoId() + ", "
                + ClsBD.getRelRepresentanteId() + ") values(?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql);
        // preenche os valores 
        stmt.setInt(1, cliId);
        stmt.setInt(2, parentescoId);
        stmt.setInt(3, repId);
        
        sql = stmt.toString();

        stmt.execute();
        stmt.close();
        con.close();

        ClienteDAO cliente = JDBCConsulta.clienteId(cliId);
        RepresentanteDAO representante = JDBCConsulta.representanteId(repId);
        String parentesco = JDBCConsulta.parentesco(cliente).getGrau();

        try {
            AuditoriaInsere.inserirRel(FrmLogin.usuario, cliente, representante, parentesco, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCInsere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que insere um novo usuário
     *
     * @param usuario objeto do tipo UsuarioDAO com as informações a serem
     * adicionadas
     * @throws java.io.IOException
     */
    public static void inserirUsuario(UsuarioDAO usuario) throws ConstraintViolationException, IOException {
        nomeTabela = ClsBD.getTblLogin();
        try {
            con = ConexaoMySQL.getConexaoMySQL();
            con.setAutoCommit(false);

            String sql = "insert into " + nomeTabela
                    + "(" + ClsBD.getUsuarionome() + ", " + ClsBD.getUsuarionick() + ", " + ClsBD.getUsuarioSenha()
                    + ", " + ClsBD.getUsuarioAdmin() + ", " + ClsBD.getUsuarioCpf() + ") "
                    + "values (?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            // preenche os valores
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getNick());
            stmt.setString(3, String.valueOf(usuario.getSenha()));
            stmt.setBoolean(4, usuario.isAdmin());
            stmt.setString(5, usuario.getCpf());
            
            sql = stmt.toString();

            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                usuario.setId(rs.getInt(1));
            }

            stmt.close();
            con.commit();
            con.close();

            try {
                AuditoriaInsere.inserirUsuario(FrmLogin.usuario, usuario, sql);
            } catch (AuditoriaException ex) {
                JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(JDBCInsere.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            throw new ConstraintViolationException("O nickname/CPF já existe.", ex, "login");
        }
    }

    /**
     * Método que insere um novo tipo de parentesco e retorna o ID do dado
     * inserido
     *
     * @param parentesco tipo de parentesco a ser inserido
     * @return int com o ID do parentesco
     * @throws SQLException
     * @throws IOException
     */
    public static int inserirParentesco(String parentesco) throws SQLException, IOException {
        nomeTabela = ClsBD.getTblParentesco();
        parentesco = parentesco.toUpperCase();
        int retorno = 0;

        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);

        String sql = "insert into " + nomeTabela
                + "(" + ClsBD.getParTipo() + ") "
                + "values (?)";

        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // preenche os valores
        stmt.setString(1, parentesco);
        
        sql = stmt.toString();

        stmt.execute();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            retorno = rs.getInt(1);
        }

        stmt.close();
        con.commit();
        con.close();

        try {
            AuditoriaInsere.inserirParentesco(FrmLogin.usuario, parentesco, retorno, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBCInsere.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public static int inserirOrcamento(OrcamentoDAO orcamento) throws SQLException, IOException {
        nomeTabela = ClsBD.getTblOrcamento();
        int cod = 0;
        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);

        String sql = "insert into " + nomeTabela
                + "(" + ClsBD.getOrcClienteId() + ", "
                + ClsBD.getOrcTipoPag() + ", " + ClsBD.getOrcValor() + ", " + ClsBD.getOrcNSessoes()
                + ") values (?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // preenche os valores
        stmt.setInt(1, orcamento.getIdCliente());
        stmt.setString(2, orcamento.getTipoPagamento());
        stmt.setDouble(3, orcamento.getValor());
        stmt.setDouble(4, orcamento.getSessoes());
        
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
            Logger.getLogger(JDBCInsere.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cod;
    }

    /**
     * Método para agendar uma nova sessão
     *
     * @param sessao objeto SessaoDAO
     * @return ID da sessão criada
     * @throws SQLException
     * @throws IOException
     */
    public static int inserirSessao(SessaoDAO sessao) throws SQLException, IOException {
        nomeTabela = ClsBD.getTblSessao();
        int retorno = 0;

        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);
        String sql = "insert into " + nomeTabela
                + "(" + ClsBD.getSesData() + ", " + ClsBD.getSesDesconto() + ", " + ClsBD.getSesHora()
                + ", " + ClsBD.getSesIdOrc() + ", " + ClsBD.getSesTipoPagamento() + ", " + ClsBD.getSesValor()
                + ") values (?,?,?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // preenche os valores
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
            Logger.getLogger(JDBCInsere.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }
}
