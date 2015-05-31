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
import java.io.IOException;
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
public class RepresentanteDAO {

    //Variáveis

    public static Connection con = null;
    static String nomeTabela;
    static String view;
    static String campos;

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
     * Consulta os cdependentes de um representante
     *
     * @param id do representante
     * @return list de cdependentes
     * @throws SQLException
     */
    public static List cdependentes(int id) throws SQLException {
        List<Cliente> dependentes = new ArrayList<>();
        abreCon();
        nomeTabela = ClsBD.getViewParente();
        PreparedStatement stmt =  con.prepareStatement("select id_cli as 'ids' from " + nomeTabela + " where id_representante = " + id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int idCli = rs.getInt("ids");
            Cliente menor = ClienteDAO.cclienteId(idCli);
            dependentes.add(menor);
        }
        return dependentes;
    }

    /**
     * Conta quantos cdependentes um representante tem.
     *
     * @param id
     * @return -1 caso tenha dado erro no processo
     */
    public static int cqtdeDependentes(int id) throws SQLException {
        int retorno = -1;
         abreCon();
         nomeTabela = ClsBD.getViewParente();
        String sql = "select count(id_cli) as 'quantos' from " +  nomeTabela + " where id_representante = " + id;
        PreparedStatement stmt =  con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            retorno = rs.getInt("quantos");
        }
        return retorno;
    }

    /**
     * Procura todos os representantes no BD
     *
     * @return todos os representantes encontrados
     */
    public static List crepresentanteTodos() throws SQLException {
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
     * Procura representantes com o nome parametrizado no BD
     *
     * @param nome nome do representante
     * @return representantes encontrados
     * @throws java.sql.SQLException
     */
    public static List crepresentanteNome(String nome) throws SQLException {
        List<Representante> representantes = new ArrayList<>();
        nome = nome.trim();
        abreCon();
        nomeTabela = ClsBD.getTblRepresentante();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getRepnome() + " like '" + nome + "%'" + " OR " + ClsBD.getRepnome() + " like '%" + nome + "'" + " OR " + ClsBD.getRepnome() + " like '%" + nome + "%'");
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
     * Método que pesquisa um representante no banco de dados pelo ID
     *
     * @param id ID do representante a ser pesquisado
     * @return objeto ClienteDAO
     */
    public static Representante crepresentanteId(int id) throws SQLException {
        Representante representante = new Representante();
        abreCon();
        nomeTabela = ClsBD.getTblRepresentante();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getRepId() + " = " + id);
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
    public static Representante crepresentanteCpf(String cpf) throws SQLException {
        Representante representante = new Representante();
        cpf = cpf.trim();
        abreCon();
        nomeTabela = ClsBD.getTblRepresentante();
        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTabela + " where " + ClsBD.getRepCpf() + " = " + cpf);
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

    //Métodos
    /**
     * Remove uma relação entre menor de idade e representante
     * @param idRepresentante
     * @param idCliente
     * @throws SQLException
     */
    public static void removeRel(int idRepresentante, int idCliente) throws SQLException {
         nomeTabela = ClsBD.getTblRel();
        Cliente cliente = ClienteDAO.cclienteId(idCliente);
        Representante representante = RepresentanteDAO.crepresentanteId(idRepresentante);
         con = ConexaoMySQL.getConexaoMySQL();
        String sql = "DELETE from " +  nomeTabela + " where " + ClsBD.getRelRepresentanteId() + " = ?" + " and " + ClsBD.getRelClienteId() + " = ?";
        PreparedStatement stmt =  con.prepareStatement(sql);
        stmt.setInt(1, idRepresentante);
        stmt.setInt(2, idCliente);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
         con.close();
        try {
            AuditoriaDelete.relRepMenor(FrmLogin.usuario, representante, cliente, sql);
        } catch (AuditoriaException ex) {
            Logger.getLogger(RepresentanteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "insert into " + nomeTabela + "(" + ClsBD.getRelClienteId() + ", " + ClsBD.getRelParentescoId() + ", " + ClsBD.getRelRepresentanteId() + ") values(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, cliId);
        stmt.setInt(2, parentescoId);
        stmt.setInt(3, repId);
        sql = stmt.toString();
        stmt.execute();
        stmt.close();
        con.close();
        Cliente cliente = ClienteDAO.cclienteId(cliId);
        Representante representante = RepresentanteDAO.crepresentanteId(repId);
        String parentesco = RepresentanteDAO.vparentesco(cliente).getGrau();
        try {
            AuditoriaInsere.inserirRel(FrmLogin.usuario, cliente, representante, parentesco, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RepresentanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que insere um novo representante no banco de dados.
     *
     * @param representante objeto do tipo Cliente com informações do
     * representante a ser inserido.
     * @return ID do representante
     * @throws SQLException
     */
    public static int inserirRepresentante(Representante representante) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();
        representante.setNome(representante.getNome().trim());
        representante.setCpf(representante.getCpf().trim());
        representante.setTel(representante.getTel().trim());
        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);
        String sql = "insert into " + nomeTabela + "(" + ClsBD.getRepnome() + ", " + ClsBD.getRepCpf() + ", " + ClsBD.getRepNasc() + ", " + ClsBD.getRepTel() + ", " + ClsBD.getRepSexo() + ") values (?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
            Logger.getLogger(RepresentanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return representante.getId();
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
        parentesco = parentesco.toUpperCase().trim();
        int retorno = 0;
        con = ConexaoMySQL.getConexaoMySQL();
        con.setAutoCommit(false);
        String sql = "insert into " + nomeTabela + "(" + ClsBD.getParTipo() + ") " + "values (?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
            Logger.getLogger(RepresentanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Modifica o nome de um representante.
     *
     * @param nome novo nome do representante
     * @param id ID do representante a mudar o nome
     * @throws SQLException
     */
    public static void urepresentanteNome(String nome, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();
        Representante representante = RepresentanteDAO.crepresentanteId(id);
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
            AuditoriaUpdate.modificaRepNome(FrmLogin.usuario, representante, nome, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RepresentanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda o numero de telefone do representante
     *
     * @param tel novo telefone
     * @param id ID do representante
     * @throws SQLException
     */
    public static void urepresentanteTel(String tel, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();
        Representante representante = RepresentanteDAO.crepresentanteId(id);
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
            AuditoriaUpdate.modificaRepTel(FrmLogin.usuario, representante, tel, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RepresentanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda a data de nascimento do representante
     *
     * @param data nova data
     * @param id ID do representante
     * @throws SQLException
     */
    public static void urepresentanteNasc(Date data, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();
        Representante representante = RepresentanteDAO.crepresentanteId(id);
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
            AuditoriaUpdate.modificaRepNasc(FrmLogin.usuario, representante, data, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RepresentanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muda o sexo do representante
     *
     * @param feminino se o sexo é feminino ou não
     * @param id ID do representante a mudar
     * @throws SQLException
     */
    public static void urepresentanteSexo(Boolean feminino, int id) throws SQLException {
        nomeTabela = ClsBD.getTblRepresentante();
        Representante representante = RepresentanteDAO.crepresentanteId(id);
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
            AuditoriaUpdate.modificaRepSexo(FrmLogin.usuario, representante, feminino, sql);
        } catch (AuditoriaException ex) {
            JOptionPane.showMessageDialog(null, "Erro de auditoria.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RepresentanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retorna o ID do representante e o grau de vparentesco num objeto
    Representante
     *
     * @param menor Cliente com o menor
     * @return Representante com apenas o ID e o grau setados
     */
    public static Representante vparentesco(Cliente menor) throws SQLException {
        Representante representante = new Representante();
        con = ConexaoMySQL.getConexaoMySQL();
        view = ClsBD.getViewParente();
        int idCli = menor.getId();
        PreparedStatement stmt = con.prepareStatement("select * from " + view + " where " + ClsBD.getCliId() + " = " + idCli);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            representante.setId(rs.getInt(ClsBD.getRepId()));
            representante.setGrau(rs.getString(ClsBD.getParTipo()));
        }
        stmt.close();
        con.close();
        return representante;
    }

}
