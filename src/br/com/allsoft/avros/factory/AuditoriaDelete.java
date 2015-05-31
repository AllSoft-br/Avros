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
import br.com.allsoft.avros.dao.Representante;
import br.com.allsoft.avros.dao.Sessao;
import br.com.allsoft.avros.dao.Usuario;
import br.com.allsoft.avros.exceptions.AuditoriaException;
import static br.com.allsoft.avros.factory.JDBCAuditoria.tabela;
import br.com.allsoft.avros.formulas.Cpf;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe para salvar deletes na auditoria
 * 
 * @author Luana Nascimento
 */
public class AuditoriaDelete extends JDBCAuditoria{
    /**
     * Grava a ação de deletar uma relação entre responsavel e cliente menor na auditoria
     *
     * @param resp usuário responsável pelo update
     * @param representante
     * @param menor
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void relRepMenor(Usuario resp, Representante representante, Cliente menor, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblRel();
            acao = "delete";
            descricao = resp.getNick() + " deletou a relação entre o representante " + representante.getNome() + ", CPF " + Cpf.imprimeCpf(representante.getCpf()) + " e o cliente " + menor.getNome() + ", CPF " + Cpf.imprimeCpf(menor.getCpf()) + ", de " + String.valueOf(menor.idade()) + " anos.";
            sql = codSql;
            codDado = menor.getId();
            idLogin = resp.getId();
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
     * Grava delete de uma sessão na auditoria
     * @param resp usuário responsável
     * @param sessao sessão deletada, com nome do cliente e id 
     * de orçamento setados
     * @param codSql SQL utilizado
     * @throws AuditoriaException 
     */
    public static void sessao(Usuario resp, Sessao sessao, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblSessao();
            acao = "delete";
            descricao = resp.getNick() + " deletou a sessão de ID " + sessao.getId() + " do cliente " + sessao.getCliente() + ", orçamento " + sessao.getIdOrcamento();
            sql = codSql;
            codDado = sessao.getId();
            idLogin = resp.getId();
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
     * Grava delete de um orçamento na auditoria
     * @param resp usuário responsável
     * @param orcamento orçamento deletado, com nome do cliente setado 
     * @param codSql SQL utilizado
     * @throws AuditoriaException 
     */
    public static void orcamento(Usuario resp, Orcamento orcamento, String codSql) throws AuditoriaException {
        try {
            Cliente cliente = JDBCConsulta.clienteId(orcamento.getIdCliente());
            
            tabela = ClsBD.getTblOrcamento();
            acao = "delete";
            descricao = resp.getNick() + " deletou o orçamento de ID " + orcamento.getId() + " e suas sessões, do cliente " + cliente.getNome();
            sql = codSql;
            codDado = orcamento.getId();
            idLogin = resp.getId();
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
