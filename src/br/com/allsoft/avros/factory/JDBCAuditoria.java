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

import br.com.allsoft.avros.dao.ClienteDAO;
import br.com.allsoft.avros.dao.OrcamentoDAO;
import br.com.allsoft.avros.dao.RepresentanteDAO;
import br.com.allsoft.avros.dao.SessaoDAO;
import br.com.allsoft.avros.dao.UsuarioDAO;

/**
 * Classe que insere e busca dados da auditoria
 * 
 * @author Luana
 */
public class JDBCAuditoria {
    /**
     * Método que insere a modificação feita pelo usuário na
     * tabela de auditoria do BD.
     * Utilizada para modificações abstratas no sistema, pois
     * existem métodos mais específicos que podem ser utilizados
     * em seu lugar com um grau maior de eficiência.
     * 
     * @param usuario usuário que fez a modificação
     * @param modificacao o que o usuário modificiou no sistema
     */
    public static void inserirAbstrato(UsuarioDAO usuario, String modificacao){
        
    }
    
    /**
     * Método que grava a inserção de um novo cliente na
     * tabela auditoria do BD, quem inseriu e o horário da inserção.
     * 
     * @param usuario usuário que fez o cadastro
     * @param cliente cliente que foi cadastrado
     */
    public static void inserirCliente(UsuarioDAO usuario, ClienteDAO cliente){
        
    }
    
    /**
     * Método que grava a inserção de um novo responsavel de um cliente na
     * tabela auditoria do BD, quem inseriu e o horário da inserção.
     * 
     * @param usuario usuário que fez o cadastro
     * @param responsavel responsavel pelo cliente menor de idade que for cadastrar
     */
    public static void inserirRespresentante(UsuarioDAO usuario, RepresentanteDAO responsavel){
        
    }
    
    /**
     * Método que grava a inserção de um novo usuário na
     * tabela auditoria do BD quem inseriu e o horário da inserção.
     * 
     * @param usuario usuário que fez o cadastro
     * @param novoUsuario usuário que foi cadastrado
     */
    public static void inserirUsuario(UsuarioDAO usuario, UsuarioDAO novoUsuario){
        
    }
    
    /**
     * Método que grava a inserção de um novo orçamento
     * 
     * @param orcamento do tipo OrcamentoDAO
     */
    public static void inserirOrcamento(OrcamentoDAO orcamento){
        
    }
    
    /**
     * Método que salva a mudança de nickname do usuário
     * 
     * @param usuario objeto UsuarioDAO do usuário antes do update
     * @param nick novo nick que o usuário escolheu
     */
    public static void modificaNick(UsuarioDAO usuario, String nick){
        
    }
    
    /**
     * Método que salva a mudança de nome do usuário
     * 
     * @param usuario UsuarioDAO antes do update
     * @param nome novo nome que o usuário escolheu
     */
    public static void modificaNomeUsuario(UsuarioDAO usuario, String nome){
        
    }
    
    /**
     * Método que salva a mudança de nome do cliente
     * 
     * @param cliente ClienteDAO antes do update
     * @param nome novo nome que o usuário escolheu
     */
    public static void modificaNomeCliente(ClienteDAO cliente, String nome){
        
    }
    
    /**
     * Método que salva a mudança de senha do usuário
     * 
     * @param usuarioAntes objeto UsuarioDAO do usuário antes do update
     * @param senha nova senha do usuário
     */
    public static void modificaSenha(UsuarioDAO usuarioAntes, char[] senha){
        
    }
    
    /**
     * Método que salva a mudança de sexo do usuário
     * 
     * @param usuarioAntes objeto UsuarioDAO do usuário antes do update
     * @param usuarioDepois objeto UsuarioDAO do usuário depois do update
     */
    public static void modificaSexo(UsuarioDAO usuarioAntes, UsuarioDAO usuarioDepois){
        
    }
    
    /**
     * Método que armazena o horário que o usuário fez login
     * 
     * @param usuario usuário que fez o login
     */
    public static void logIn(UsuarioDAO usuario){
        
    }
    
    /**
     * Método que armazena o horário que o usuário fez logout
     * 
     * @param usuario usuário que fez o logout
     */
    public static void logOut(UsuarioDAO usuario){
        
    }
    
    /**
     * Método que salva o agendamento de uma nova sessão
     * 
     * @param usuario quem agendou
     * @param sessao sessão que foi agendada
     */
    public static void inserirSessao(UsuarioDAO usuario, SessaoDAO sessao){
        
    }
}
