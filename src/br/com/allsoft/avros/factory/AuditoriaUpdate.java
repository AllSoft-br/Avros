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
import static br.com.allsoft.avros.factory.JDBCAuditoria.con;
import static br.com.allsoft.avros.factory.JDBCAuditoria.tabela;
import br.com.allsoft.avros.formulas.Datas;
import br.com.allsoft.avros.formulas.Moeda;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Luana Nascimento
 */
public class AuditoriaUpdate extends JDBCAuditoria{
    /**
     * Método que salva a mudança de nickname do usuário
     *
     * @param resp usuario responsavel pelo update
     * @param usuario objeto UsuarioDAO do usuário antes do update
     * @param nick novo nick que o usuário escolheu
     * @param codSql
     * @throws java.sql.SQLException
     */
    public static void modificaNick(Usuario resp, Usuario usuario, String nick, String codSql) throws AuditoriaException {
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
    public static void modificaNomeUsuario(Usuario resp, Usuario usuario, String nome, String codSql) throws AuditoriaException {
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
    public static void modificaNomeCliente(Usuario usuario, Cliente cliente, String nome, String codSql) throws AuditoriaException {
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
    public static void modificaSenha(Usuario usuarioResp, Usuario usuarioModf, char[] senha, String codSql) throws AuditoriaException {
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
    public static void modificaUsuarioAtivo(Usuario resp, Usuario usuario, boolean ativo, String codSql) throws AuditoriaException {
        try {
            String oq, oq1;
            int id;
            if (ativo) {
                oq = "ativada";
            } else {
                oq = "desativada";
            }
            if (usuario.isAtivo()) {
                oq1 = "ativada";
            } else {
                oq1 = "desativada";
            }
            if (resp.getNick() == null) {
                resp.setNick("sistema");
                resp.setId(usuario.getId());
            }
            descricao = resp.getNick() + " atualizou a conta do usuário " + usuario.getNick() + " de " + oq1 + " para " + oq;
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

    /**
     * Grava a modificação de permissões de um usuário na Auditoria
     *
     * @param resp usuário responsavel pelo update
     * @param usuario que foi modificado
     * @param admin se o usuário modificado será admin ou não
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void modificaUsuarioAdmin(Usuario resp, Usuario usuario, boolean admin, String codSql) throws AuditoriaException {
        try {
            String oq, oq1;
            if (usuario.isAdmin()) {
                oq1 = "usuário administrador";
            } else {
                oq1 = "conta comum";
            }
            if (admin) {
                oq = "usuário administrador";
            } else {
                oq = "conta comum";
            }
            tabela = ClsBD.getTblLogin();
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

    /**
     * Grava as modificações de sexo do cliente na auditoria
     *
     * @param resp usuário responsável pelo update
     * @param cliente que foi modificado
     * @param feminino se o cliente se tornará mulher ou não
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void modificaClienteSexo(Usuario resp, Cliente cliente, boolean feminino, String codSql) throws AuditoriaException {
        try {
            String oq, oq1;
            if (cliente.isFeminino()) {
                oq1 = "feminino";
            } else {
                oq1 = "masculino";
            }
            if (feminino) {
                oq = "feminino";
            } else {
                oq = "masculino";
            }
            tabela = ClsBD.getTblCliente();
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
     * Grava modificações de data de nascimento do cliente na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param cliente que foi modificado
     * @param data nova data de nascimento
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void modificaClienteNasc(Usuario resp, Cliente cliente, java.sql.Date data, String codSql) throws AuditoriaException {
        try {
            String nascimento = Datas.sqlparaString(data);
            String nascimentoAntes = Datas.sqlparaString(cliente.getNascimento());
            
            tabela = ClsBD.getTblCliente();
            acao = "update";
            descricao = resp.getNick() + " modificou a data de nascimento do cliente " + cliente.getNome() + " de " + nascimentoAntes + " para " + nascimento;
            sql = codSql;
            codDado = cliente.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = cliente.getNascimento().toString();
            depois = data.toString();
            campo = ClsBD.getCliNasc();
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
     * Grava modificações de telefone do cliente na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param cliente que foi modificado antes do update
     * @param telefone novo telefone
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void modificaClienteTel(Usuario resp, Cliente cliente, String telefone, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblCliente();
            acao = "update";
            descricao = resp.getNick() + " modificou o telefone do cliente " + cliente.getNome() + " de " + cliente.getTel() + " para " + telefone;
            sql = codSql;
            codDado = cliente.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = cliente.getTel();
            depois = telefone;
            campo = ClsBD.getCliTel();
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
     * @param resp usuario responsavel pela mudança
     * @param representante antes do update
     * @param nome novo nome que o usuário escolheu
     * @param codSql código sql utilizado
     * @throws br.com.allsoft.avros.exceptions.AuditoriaException
     */
    public static void modificaRepNome(Usuario resp, Representante representante, String nome, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblRepresentante();
            acao = "update";
            descricao = resp.getNick() + " mudou o nome do representante " + representante.getNome() + " para " + nome;
            sql = codSql;
            codDado = representante.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = representante.getNome();
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
     * Grava as modificações de sexo do cliente na auditoria
     *
     * @param resp usuário responsável pelo update
     * @param representante que foi modificado, antes do update
     * @param feminino se o representante se tornará mulher ou não
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void modificaRepSexo(Usuario resp, Representante representante, boolean feminino, String codSql) throws AuditoriaException {
        try {
            String oq, oq1;
            if (representante.isFeminino()) {
                oq1 = "feminino";
            } else {
                oq1 = "masculino";
            }
            if (feminino) {
                oq = "feminino";
            } else {
                oq = "masculino";
            }
            tabela = ClsBD.getTblRepresentante();
            acao = "update";
            descricao = resp.getNick() + " modificou sexo do representante " + representante.getNome() + " de " + oq1 + " para " + oq;
            sql = codSql;
            codDado = representante.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(representante.isFeminino());
            depois = String.valueOf(feminino);
            campo = ClsBD.getRepSexo();
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
     * Grava modificações de data de nascimento do representante na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param representante que foi modificado, antes do update
     * @param data nova data de nascimento
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void modificaRepNasc(Usuario resp, Representante representante, java.sql.Date data, String codSql) throws AuditoriaException {
        try {
            String nascimento = Datas.sqlparaString(data);
            String nascimentoAntes = Datas.sqlparaString(representante.getNascimento());
            
            tabela = ClsBD.getTblRepresentante();
            acao = "update";
            descricao = resp.getNick() + " modificou a data de nascimento do representante " + representante.getNome() + " de " + nascimentoAntes + " para " + nascimento;
            sql = codSql;
            codDado = representante.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = representante.getNascimento().toString();
            depois = data.toString();
            campo = ClsBD.getCliNasc();
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
     * Grava modificações de telefone do cliente na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param representante que foi modificado, antes do update
     * @param telefone novo telefone
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void modificaRepTel(Usuario resp, Representante representante, String telefone, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblRepresentante();
            acao = "update";
            descricao = resp.getNick() + " modificou o telefone do representante " + representante.getNome() + " de " + representante.getTel() + " para " + telefone;
            sql = codSql;
            codDado = representante.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = representante.getTel();
            depois = telefone;
            campo = ClsBD.getCliTel();
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
     * Grava modificações de telefone do cliente na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param sessao modificada, antes do update
     * @param pagamento nova forma de pagamento
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void sessaoPagamento(Usuario resp, Sessao sessao, String pagamento, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblSessao();
            acao = "update";
            descricao = resp.getNick() + " modificou a forma de pagamento da sessão de código " + String.valueOf(sessao.getId()) + " de " + sessao.getPagamento() + " para " + pagamento;
            sql = codSql;
            codDado = sessao.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = sessao.getPagamento();
            depois = pagamento;
            campo = ClsBD.getSesTipoPagamento();
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
     * Grava modificações de telefone do cliente na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param orcamento modificada, antes do update
     * @param pagamento nova forma de pagamento
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void orcamentoPagamento(Usuario resp, Orcamento orcamento, String pagamento, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblOrcamento();
            acao = "update";
            descricao = resp.getNick() + " modificou a forma de pagamento do orçamento de código " + String.valueOf(orcamento.getId()) + " de " + orcamento.getTipoPagamento() + " para " + pagamento;
            sql = codSql;
            codDado = orcamento.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = orcamento.getTipoPagamento();
            depois = pagamento;
            campo = ClsBD.getOrcTipoPag();
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
     * Grava modificações de telefone do cliente na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param orcamento modificada, antes do update
     * @param desc nova descrição
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void orcamentoDesc(Usuario resp, Orcamento orcamento, String desc, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblOrcamento();
            acao = "update";
            descricao = resp.getNick() + " modificou a descrição do orçamento de código " + String.valueOf(orcamento.getId()) + " de '" + orcamento.getDescricao()+ "' para '" + desc + "'";
            sql = codSql;
            codDado = orcamento.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = orcamento.getDescricao();
            depois = desc;
            campo = ClsBD.getOrcDesc();
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
     * Grava modificações de valor do orcamento na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param orcamento modificada, antes do update
     * @param valor novo valor
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void orcamentoValor(Usuario resp, Orcamento orcamento, double valor, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblOrcamento();
            acao = "update";
            descricao = resp.getNick() + " modificou o valor do orçamento de código " + String.valueOf(orcamento.getId()) + " de " + Moeda.padraoBr(orcamento.getValor()) + " para " + Moeda.padraoBr(valor);
            sql = codSql;
            codDado = orcamento.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(orcamento.getValor());
            depois = String.valueOf(valor);
            campo = ClsBD.getOrcValor();
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
     * Grava modificações do valor de desconto de uma sessao na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param sessao modificada, antes do update
     * @param valor novo valor
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void sessaoDesconto(Usuario resp, Sessao sessao, double valor, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblSessao();
            acao = "update";
            descricao = resp.getNick() + " modificou o valor de desconto da sessão de código " + String.valueOf(sessao.getId()) + " de " + Moeda.padraoBr(sessao.getDesconto()) + " para " + Moeda.padraoBr(valor);
            sql = codSql;
            codDado = sessao.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(sessao.getDesconto());
            depois = String.valueOf(valor);
            campo = ClsBD.getSesDesconto();
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
     * Grava modificações quantidade de sessoes do orcamento na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param orcamento modificada, antes do update
     * @param sessoes nova quantidade
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void orcamentoSessoes(Usuario resp, Orcamento orcamento, int sessoes, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblOrcamento();
            acao = "update";
            descricao = resp.getNick() + " modificou a quantidade de sessões do orçamento de código " + String.valueOf(orcamento.getId()) + " de " + String.valueOf(orcamento.getSessoes()) + " para " + String.valueOf(sessoes);
            sql = codSql;
            codDado = orcamento.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(orcamento.getSessoes());
            depois = String.valueOf(sessoes);
            campo = ClsBD.getOrcNSessoes();
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
     * Grava modificações de data de uma sessao na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param sessao modificada, antes do update
     * @param data nova data
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void sessaoData(Usuario resp, Sessao sessao, java.sql.Date data, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblSessao();
            acao = "update";
            descricao = resp.getNick() + " modificou a data da sessão de código " + String.valueOf(sessao.getId()) + " de " + Datas.sqlparaString(sessao.getData()) + " para " + Datas.sqlparaString(data);
            sql = codSql;
            codDado = sessao.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(sessao.getData());
            depois = String.valueOf(data);
            campo = ClsBD.getSesData();
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
     * Grava modificações de horário de uma sessao na auditoria
     *
     * @param resp usuário responsável pela modificação
     * @param sessao modificada, antes do update
     * @param hora novo horario
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void sessaoHora(Usuario resp, Sessao sessao, java.sql.Time hora, String codSql) throws AuditoriaException {
        try {
            tabela = ClsBD.getTblSessao();
            acao = "update";
            descricao = resp.getNick() + " modificou o horário da sessão de código " + String.valueOf(sessao.getId()) + " de " + Datas.timeParaString(sessao.getHora()) + " para " + Datas.timeParaString(hora);
            sql = codSql;
            codDado = sessao.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(sessao.getHora());
            depois = String.valueOf(hora);
            campo = ClsBD.getSesHora();
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
     * Grava as modificações de sexo do cliente na auditoria
     *
     * @param resp usuário responsável pelo update
     * @param sessao que foi modificada, antes do update
     * @param concluida se a sessao será atualizada para concluida ou não
     * @param codSql código sql utilizado
     * @throws AuditoriaException
     */
    public static void sessaoConcluida(Usuario resp, Sessao sessao, boolean concluida, String codSql) throws AuditoriaException {
        try {
            String oq, oq1;
            if (sessao.isConcluida()) {
                oq1 = "concluída";
            } else {
                oq1 = "pendente";
            }
            if (concluida) {
                oq = "concluída";
            } else {
                oq = "pendente";
            }
            tabela = ClsBD.getTblSessao();
            acao = "update";
            descricao = resp.getNick() + " modificou o status da sessão de código " + String.valueOf(sessao.getId()) + " de " + oq1 + " para " + oq;
            sql = codSql;
            codDado = sessao.getId();
            idLogin = resp.getId();
            con = ConexaoMySQL.getConexaoMySQL();
            antes = String.valueOf(sessao.isConcluida());
            depois = String.valueOf(concluida);
            campo = ClsBD.getRepSexo();
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
