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

package br.com.allsoft.avros.factory.modelo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe DAO para clientes.
 * 
 * @author Luana
 */
public class ClienteDAO {
    //Variáveis
    private int idCliente;
    private String nome;
    private String rg;
    private Date nascimento;
    private String tel;
    
    //Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getNascimento() {
        return nascimento;
    }

    /**
     * Insere data de nascimento, passando do formato String para sql.Date
     * 
     * @param strData data de nascimento formato dd/MM/yyyy
     */
    public void setNascimento(String strData) {
        //cria variável pra formatar datas de dia-mes-ano
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        //inicializa variável de Data estilo ano-mes-dia
        java.sql.Date data = null;
 
        //tenta converter as datas
        try {
            data = new java.sql.Date(format.parse(strData).getTime());
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        
        this.nascimento = data;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
}
