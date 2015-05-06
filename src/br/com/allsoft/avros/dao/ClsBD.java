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

/**
 * Classe para guardar os nomes das tabelas do banco de dados
 * 
 * @author Luana Nascimento
 */ 
 

public class ClsBD {
    //Tabelas
    private static final String tblRepresentante = "tbl_representante";
    private static final String tblRel = "tbl_rel";
    private static final String tblCliente = "tbl_cliente";
    private static final String tblLogin = "tbl_login";
    private static final String tblParentesco = "tbl_parentesco";
    private static final String tblOrcamento = "tbl_orcamento";
    private static final String tblSessao = "tbl_sessao";
    
    //Campos das tabelas
    
    //Tabela relacionadora
    private static final String relClienteId = "fk_id_cli";
    private static final String relParentescoId = "fk_id_parentesco";
    private static final String relRepresentanteId = "fk_id_representante";
    private static final String relId = "id_rel";
    
    //Tabela Representante
    private static final String repCpf = "CPF";
    private static final String repSexo = "sexo"; 
    private static final String repId = "id_representante";
    private static final String repnome = "nome";
    private static final String repNasc = "data_nasc";
    private static final String repTel = "tel";
    
    //Tabela Cliente
    private static final String cliSexo = "sexo";
    private static final String cliId = "id_cli";
    private static final String cliIdUsuario = "fk_id_login";
    private static final String cliNome = "nome";
    private static final String cliNasc = "data_nasc";
    private static final String cliTel = "tel";
    private static final String cliCpf = "CPF";
    
    //Tabela usuario
    private static final String usuarioId = "id_login";
    private static final String usuarioAtivo = "ativo";
    private static final String usuarionome = "nome";
    private static final String usuarioAdmin = "admin";
    private static final String usuarioCpf = "CPF";
    private static final String usuarionick = "login";
    private static final String usuarioSenha = "senha";
    
    //Tabela parentesco
    private static final String parId = "id_parentesco";
    private static final String parTipo = "tipo_parentesco";
    
    //Tabela Orçamento
    private static final String orcId = "cod_orc";
    private static final String orcCriacao = "criado_em";
    private static final String orcTipoPag = "Tipo_pagamento";
    private static final String orcValor = "valor_total";
    private static final String orcClienteId = "fk_id_cli_orc";
    private static final String orcNSessoes = "qntd_sessao";
    
    //Tabela Sessão
    private static final String sesId = "id_sessao";
    private static final String sesConcluida = "concluida";
    private static final String sesValor = "valor_sessao";
    private static final String sesTipoPagamento = "tipo_pagamento";
    private static final String sesData = "data_agendada";
    private static final String sesHora = "hora_agendada";
    private static final String sesDesconto = "desconto";
    private static final String sesIdOrc = "fk_cod_orc";

    //Views
    private static final String viewParente = "cliente_menor";
    private static final String viewAudit24h = "auditoria24h";
    private static final String viewAudit3d = "auditoria3d";
    private static final String viewAudit7d = "auditoria7d";
    private static final String viewAudit1m = "auditoria1m";

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
