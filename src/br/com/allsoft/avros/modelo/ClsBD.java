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
package br.com.allsoft.avros.modelo;

/**
 * Classe para guardar os nomes das tabelas do banco de dados
 *
 * @author Luana Nascimento
 */
public class ClsBD {

    //Tabelas

    protected static final String tblRepresentante = "tbl_representante";
    protected static final String tblRel = "tbl_rel";
    protected static final String tblCliente = "tbl_cliente";
    protected static final String tblLogin = "tbl_login";
    protected static final String tblParentesco = "tbl_parentesco";
    protected static final String tblOrcamento = "tbl_orcamento";
    protected static final String tblSessao = "tbl_sessao";
    protected static final String tblAuditoria = "tbl_registro";

    //Views
    protected static final String viewParente = "cliente_menor";
    protected static final String viewAudit24h = "auditoria_24h";
    protected static final String viewAudit3d = "auditoria_3d";
    protected static final String viewAudit7d = "auditoria_7d";
    protected static final String viewAudit1m = "auditoria_1m";

    //Campos das tabelas
    //Tabela relacionadora
    protected static final String relClienteId = "fk_id_cli";
    protected static final String relParentescoId = "fk_id_parentesco";
    protected static final String relRepresentanteId = "fk_id_representante";
    protected static final String relId = "id_rel";

    //Tabela Representante
    protected static final String repCpf = "CPF";
    protected static final String repSexo = "sexo";
    protected static final String repId = "id_representante";
    protected static final String repnome = "nome";
    protected static final String repNasc = "data_nasc";
    protected static final String repTel = "tel";

    //Tabela Cliente
    protected static final String cliSexo = "sexo";
    protected static final String cliId = "id_cli";
    protected static final String cliIdUsuario = "fk_id_login";
    protected static final String cliNome = "nome";
    protected static final String cliNasc = "data_nasc";
    protected static final String cliTel = "tel";
    protected static final String cliCpf = "CPF";

    //Tabela usuario
    protected static final String usuarioId = "id_login";
    protected static final String usuarioAtivo = "ativo";
    protected static final String usuarionome = "nome";
    protected static final String usuarioAdmin = "admin";
    protected static final String usuarioCpf = "CPF";
    protected static final String usuarionick = "login";
    protected static final String usuarioSenha = "senha";

    //Tabela parentesco
    protected static final String parId = "id_parentesco";
    protected static final String parTipo = "tipo_parentesco";

    //Tabela Orçamento
    protected static final String orcId = "id_orc";
    protected static final String orcCriacao = "criado_em";
    protected static final String orcTipoPag = "Tipo_pagamento";
    protected static final String orcValor = "valor_total";
    protected static final String orcClienteId = "fk_id_cli_orc";
    protected static final String orcNSessoes = "qntd_sessao";
    protected static final String orcDesc = "desc_tattoo";

    //Tabela Sessão
    protected static final String sesId = "id_sessao";
    protected static final String sesConcluida = "concluida";
    protected static final String sesValor = "valor_sessao";
    protected static final String sesTipoPagamento = "tipo_pagamento";
    protected static final String sesData = "data_agendada";
    protected static final String sesHora = "hora_agendada";
    protected static final String sesDesconto = "desconto";
    protected static final String sesIdOrc = "fk_id_orc";

    //Views da auditoria
    protected static final String audId = "id_reg";
    protected static final String audTabela = "tabela_alt";
    protected static final String audRef = "cod_ref";
    protected static final String audAcao = "acao";
    protected static final String audDesc = "desc_acao";
    protected static final String audSql = "cod_sql";
    protected static final String audAntes = "dado_ant";
    protected static final String audDepois = "dado_novo";
    protected static final String audCampo = "campo";
    protected static final String audData = "data_alt";
    protected static final String audIdLogin = "fk_id_login";
    
    //Procedures
    
    /**
     * Procedure que deleta o representante e a sua respectiva relação
     * O parâmetro de entrada é o ID do representante a ser excluido.
     */
    public static final String procDelRep = "del_representante";
    /**
     * Procedure que deleta o cliente
     * O parâmetro de entrada é o ID do cliente a ser excluido.
     */
    public static final String procDelCli = "del_cliente";
    
    public static String getOrcDesc() {
        return orcDesc;
    }

    public static String getTblAuditoria() {
        return tblAuditoria;
    }

    public static String getAudId() {
        return audId;
    }

    public static String getAudTabela() {
        return audTabela;
    }

    public static String getAudRef() {
        return audRef;
    }

    public static String getAudAcao() {
        return audAcao;
    }

    public static String getAudDesc() {
        return audDesc;
    }

    public static String getAudSql() {
        return audSql;
    }

    public static String getAudAntes() {
        return audAntes;
    }

    public static String getAudDepois() {
        return audDepois;
    }

    public static String getAudCampo() {
        return audCampo;
    }

    public static String getAudData() {
        return audData;
    }

    public static String getAudIdLogin() {
        return audIdLogin;
    }

    public static String getViewAudit24h() {
        return viewAudit24h;
    }

    public static String getViewAudit3d() {
        return viewAudit3d;
    }

    public static String getViewAudit7d() {
        return viewAudit7d;
    }

    public static String getViewAudit1m() {
        return viewAudit1m;
    }

    public static String getUsuarioAtivo() {
        return usuarioAtivo;
    }

    public static String getViewParente() {
        return viewParente;
    }

    public static String getSesId() {
        return sesId;
    }

    public static String getTblSessao() {
        return tblSessao;
    }

    public static String getSesConcluida() {
        return sesConcluida;
    }

    public static String getSesValor() {
        return sesValor;
    }

    public static String getSesTipoPagamento() {
        return sesTipoPagamento;
    }

    public static String getSesData() {
        return sesData;
    }

    public static String getSesHora() {
        return sesHora;
    }

    public static String getSesDesconto() {
        return sesDesconto;
    }

    public static String getSesIdOrc() {
        return sesIdOrc;
    }

    public static String getOrcNSessoes() {
        return orcNSessoes;
    }

    public static String getTblOrcamento() {
        return tblOrcamento;
    }

    public static String getOrcId() {
        return orcId;
    }

    public static String getOrcCriacao() {
        return orcCriacao;
    }

    public static String getOrcTipoPag() {
        return orcTipoPag;
    }

    public static String getOrcValor() {
        return orcValor;
    }

    public static String getOrcClienteId() {
        return orcClienteId;
    }

    public static String getTblParentesco() {
        return tblParentesco;
    }

    public static String getCliIdUsuario() {
        return cliIdUsuario;
    }

    public static String getUsuarioCpf() {
        return usuarioCpf;
    }

    public static String getParId() {
        return parId;
    }

    public static String getParTipo() {
        return parTipo;
    }

    public static String getTblRepresentante() {
        return tblRepresentante;
    }

    public static String getRelParentescoId() {
        return relParentescoId;
    }

    public static String getTblRel() {
        return tblRel;
    }

    public static String getTblCliente() {
        return tblCliente;
    }

    public static String getRelRepresentanteId() {
        return relRepresentanteId;
    }

    public static String getTblLogin() {
        return tblLogin;
    }

    public static String getRelClienteId() {
        return relClienteId;
    }

    public static String getRepCpf() {
        return repCpf;
    }

    public static String getRepSexo() {
        return repSexo;
    }

    public static String getRelId() {
        return relId;
    }

    public static String getRepId() {
        return repId;
    }

    public static String getRepnome() {
        return repnome;
    }

    public static String getRepNasc() {
        return repNasc;
    }

    public static String getRepTel() {
        return repTel;
    }

    public static String getCliSexo() {
        return cliSexo;
    }

    public static String getCliId() {
        return cliId;
    }

    public static String getCliNome() {
        return cliNome;
    }

    public static String getCliNasc() {
        return cliNasc;
    }

    public static String getCliTel() {
        return cliTel;
    }

    public static String getCliCpf() {
        return cliCpf;
    }

    public static String getUsuarioId() {
        return usuarioId;
    }

    public static String getUsuarionome() {
        return usuarionome;
    }

    public static String getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public static String getUsuarionick() {
        return usuarionick;
    }

    public static String getUsuarioSenha() {
        return usuarioSenha;
    }
}
