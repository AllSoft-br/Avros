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

import java.sql.Timestamp;

/**
 * Classe dao para os registros da Auditoria
 * 
 * @author Luana Nascimento
 */
public class Registro {
    private int id;
    private int idDado; //ID do dado alterado
    private int idLogin; //ID do login que fez a alteração
    private String tabela; //Nome da tabela alterada
    private String acao; //Update, insert, login ou delete
    private String descricao; //Descrição da ação ocorrida. Texto que ficará à mostra no histórico
    private String sql = "-"; //Código sql utilizado
    private String antes = "-"; //No caso de updates, como o campo era antes
    private String depois = "-"; //No caso de updates, como o campo ficou no fim
    private String campo = "-";
    private Timestamp data;

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDado() {
        return idDado;
    }

    public void setIdDado(int idDado) {
        this.idDado = idDado;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getAntes() {
        return antes;
    }

    public void setAntes(String antes) {
        this.antes = antes;
    }

    public String getDepois() {
        return depois;
    }

    public void setDepois(String depois) {
        this.depois = depois;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
}
