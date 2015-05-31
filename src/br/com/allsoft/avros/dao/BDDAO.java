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

import br.com.allsoft.avros.modelo.ClsBD;

/**
 * Classe para guardar os nomes das tabelas do banco de dados
 *
 * @author Luana Nascimento
 */
public class BDDAO extends ClsBD{
    /**
     * Retorna a item do item que é armazenado em determinada item
     *
     * @param item a ser examinado
     * @return tipo de item
     */
    public static String itemTipo(String item) {
        String retorno = "desconhecido";
        
        if (!(item == null)) {
            if (item.equalsIgnoreCase("login")) {
                retorno = tblLogin;
            }
            if (item.equalsIgnoreCase("auditoria")) {
                retorno = tblAuditoria;
            }
            if (item.equalsIgnoreCase("cliente")) {
                retorno = tblCliente;
            }
            if (item.equalsIgnoreCase("orçamento")) {
                retorno = tblOrcamento;
            }
            if (item.equalsIgnoreCase("parentesco")) {
                retorno = tblParentesco;
            }
            if (item.equalsIgnoreCase("relação")) {
                retorno = tblRel;
            }
            if (item.equalsIgnoreCase("representante")) {
                retorno = tblRepresentante;
            }
            if (item.equalsIgnoreCase("sessão")) {
                retorno = tblSessao;
            }
        }
        return retorno;
    }
    
    /**
     * Retorna o tipo de item que é armazenado em determinada tabela
     *
     * @param tabela a ser examinada
     * @return tipo de item
     */
    public static String tipoItem(String tabela) {
        String retorno = "desconhecido";
        
        if (!(tabela == null)) {
            if (tabela.equalsIgnoreCase(tblLogin)) {
                retorno = "usuário";
            }
            if (tabela.equalsIgnoreCase(tblAuditoria)) {
                retorno = "registro";
            }
            if (tabela.equalsIgnoreCase(tblCliente)) {
                retorno = "cliente";
            }
            if (tabela.equalsIgnoreCase(tblOrcamento)) {
                retorno = "orçamento";
            }
            if (tabela.equalsIgnoreCase(tblParentesco)) {
                retorno = "grau de parentesco";
            }
            if (tabela.equalsIgnoreCase(tblRel)) {
                retorno = "relação maior/dependente";
            }
            if (tabela.equalsIgnoreCase(tblRepresentante)) {
                retorno = "representante";
            }
            if (tabela.equalsIgnoreCase(tblSessao)) {
                retorno = "sessão";
            }
        }
        return retorno;
    }
}
