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

import br.com.allsoft.avros.factory.ConexaoMySQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe DAO para clientes.
 * 
 * @author Luana
 */
public class RepresentanteDAO {
    //Variáveis
    private int id;
    private String nome; 
    private String cpf;
    private Date nascimento;
    private String tel;
    private String grau;
    private boolean feminino; //verdadeiro = feminino, falso = masculino

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFeminino() {
        return feminino;
    }

    public void setFeminino(boolean feminino) {
        this.feminino = feminino;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
    
    //Métodos
    
    /**
     * Método que retorna a idade do responsável
     * 
     * @return idade do cliente
     * @throws SQLException 
     */
    public int idade() throws SQLException{
        int retorno = 843983948;
        
        if(!(this.getNascimento() == null)){
            Connection con = null;
        
        try {
            con = ConexaoMySQL.getConexaoMySQL();
            String sql = "select "
                    + "YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS('" + this.nascimento + "'))) AS idade";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                retorno = rs.getInt("idade");
            }
            
            con.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.close();
        }
        }
        
        return retorno;
    }
    
}
