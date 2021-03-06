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
package br.com.allsoft.avros.interfaces;

import br.com.allsoft.avros.dao.UsuarioDAO;
import br.com.allsoft.avros.formulas.Cpf;
import br.com.allsoft.avros.modelo.Usuario;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luana
 */
public class IfrmEditUsuario extends javax.swing.JInternalFrame {

    //Variáveis
    Usuario usuario = new Usuario();

    //Métodos
    private void preencheCampos() {

        String tipo;
        String ativo = "";
        if (usuario.isAdmin()) {
            tipo = "administrador";
            rdoAdmin.setSelected(true);
        } else {
            tipo = "comum";
            rdoComum.setSelected(true);
        }
        
        if (usuario.isAtivo()) {
            ativo = "Ativo";
            lblAtivo.setForeground(Color.green);
            btnExclui.setText("Desativar");
        } else {
            ativo = "Desativado";
            lblAtivo.setForeground(Color.red);
            btnExclui.setText("Ativar");
        }

        lblAtivo.setText(ativo);
        lblSauda.setText("Usuário do tipo " + tipo + ".");
        txtNome.setText(usuario.getNome());
        txtNickname.setText(usuario.getNick());
        txtCpf.setText(Cpf.imprimeCpf(usuario.getCpf()));
    }

    private void editTipo() {
        if (rdoAdmin.isSelected()) {
            usuario.setAdmin(true);
            try {
                UsuarioDAO.uusuarioAdmin(true, usuario.getId());
                lblSauda.setText("Usuário do tipo administrador.");
            } catch (SQLException ex) {
                Logger.getLogger(IfrmEditUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (rdoComum.isSelected()) {
            usuario.setAdmin(false);
            try {
                UsuarioDAO.uusuarioAdmin(false, usuario.getId());
                lblSauda.setText("Usuário do tipo comum.");
            } catch (SQLException ex) {
                Logger.getLogger(IfrmEditUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Creates new form ifrmConta
     *
     * @param usuario
     */
    public IfrmEditUsuario(Usuario usuario) {
        initComponents();

        this.usuario = usuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        bgpTipo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        lblSauda = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNickname = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        lblEditarNick = new javax.swing.JLabel();
        lblEditarNome = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        btnSenha = new javax.swing.JButton();
        rdoComum = new javax.swing.JRadioButton();
        rdoAdmin = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        btnExclui = new javax.swing.JButton();
        lblAtivo = new javax.swing.JLabel();
        lblEditarTipo = new javax.swing.JLabel();

        jLabel8.setText("jLabel8");

        setClosable(true);
        setIconifiable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/Users 2.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setFont(ClsEstilo.tituloFonte);
        jLabel1.setForeground(ClsEstilo.tituloCor);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editar usuário");

        lblSauda.setFont(ClsEstilo.labelDestaqueFonte);
        lblSauda.setForeground(ClsEstilo.labelDestaqueCor);
        lblSauda.setText("Usuário do tipo administrador");

        jLabel3.setFont(ClsEstilo.labelFonte);
        jLabel3.setForeground(ClsEstilo.labelCor);
        jLabel3.setText("Nome");

        jLabel4.setFont(ClsEstilo.labelFonte);
        jLabel4.setForeground(ClsEstilo.labelCor);
        jLabel4.setText("Nickname");

        txtNickname.setFont(ClsEstilo.textoInputFonte);
        txtNickname.setForeground(ClsEstilo.textoInputCor);
        txtNickname.setText("Fulano_Silva");
        txtNickname.setEnabled(false);

        txtNome.setFont(ClsEstilo.textoInputFonte);
        txtNome.setForeground(ClsEstilo.textoInputCor);
        txtNome.setText("Fulano da Silva");
        txtNome.setEnabled(false);

        btnSalvar.setFont(ClsEstilo.botaoFonte);
        btnSalvar.setForeground(ClsEstilo.botaoCor);
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        lblEditarNick.setBackground(ClsEstilo.formbg);
        lblEditarNick.setFont(ClsEstilo.linkFonte);
        lblEditarNick.setForeground(ClsEstilo.linkCor);
        lblEditarNick.setText("Editar");
        lblEditarNick.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblEditarNick.setOpaque(true);
        lblEditarNick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarNickMouseClicked(evt);
            }
        });

        lblEditarNome.setBackground(ClsEstilo.formbg);
        lblEditarNome.setFont(ClsEstilo.linkFonte);
        lblEditarNome.setForeground(ClsEstilo.linkCor);
        lblEditarNome.setText("Editar");
        lblEditarNome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblEditarNome.setOpaque(true);
        lblEditarNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarNomeMouseClicked(evt);
            }
        });

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/logopequeno.png"))); // NOI18N

        jLabel2.setFont(ClsEstilo.labelFonte);
        jLabel2.setForeground(ClsEstilo.labelCor);
        jLabel2.setText("CPF");

        txtCpf.setEditable(false);
        txtCpf.setFont(ClsEstilo.textoInputFonte);
        txtCpf.setForeground(ClsEstilo.textoInputCor);

        btnSenha.setText("Resetar senha");
        btnSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSenhaActionPerformed(evt);
            }
        });

        bgpTipo.add(rdoComum);
        rdoComum.setFont(ClsEstilo.labelFonte);
        rdoComum.setForeground(ClsEstilo.labelCor);
        rdoComum.setText("Comum");
        rdoComum.setEnabled(false);

        bgpTipo.add(rdoAdmin);
        rdoAdmin.setFont(ClsEstilo.labelFonte);
        rdoAdmin.setForeground(ClsEstilo.labelCor);
        rdoAdmin.setText("Administrador");
        rdoAdmin.setEnabled(false);

        jLabel5.setFont(ClsEstilo.labelFonte);
        jLabel5.setForeground(ClsEstilo.labelCor);
        jLabel5.setText("Tipo de usuário:");

        btnExclui.setText("Desativar");
        btnExclui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluiActionPerformed(evt);
            }
        });

        lblAtivo.setFont(ClsEstilo.labelFonte);
        lblAtivo.setForeground(ClsEstilo.labelCor);
        lblAtivo.setText("Ativo");

        lblEditarTipo.setBackground(ClsEstilo.formbg);
        lblEditarTipo.setFont(ClsEstilo.linkFonte);
        lblEditarTipo.setForeground(ClsEstilo.linkCor);
        lblEditarTipo.setText("Editar");
        lblEditarTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblEditarTipo.setOpaque(true);
        lblEditarTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarTipoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSauda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSalvar)
                                .addGap(30, 30, 30)
                                .addComponent(btnSenha)
                                .addGap(18, 18, 18)
                                .addComponent(btnExclui)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                                    .addComponent(txtNickname, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblEditarNome)
                                                    .addComponent(lblEditarNick)))
                                            .addComponent(lblAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdoComum)
                                                .addGap(42, 42, 42)
                                                .addComponent(rdoAdmin))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblEditarTipo)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSauda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAtivo)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEditarNick))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblEditarNome))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblLogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblEditarTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoComum)
                    .addComponent(rdoAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSenha)
                    .addComponent(btnSalvar)
                    .addComponent(btnExclui))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        preencheCampos();
    }//GEN-LAST:event_formInternalFrameOpened

    private void lblEditarNickMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarNickMouseClicked
        txtNickname.setEnabled(true);
    }//GEN-LAST:event_lblEditarNickMouseClicked

    private void lblEditarNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarNomeMouseClicked
        txtNome.setEnabled(true);
    }//GEN-LAST:event_lblEditarNomeMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        //variáveis que indicam se os updates estão em ordem, começam verdadeiras
        boolean bnome = true;
        boolean bnick = true;

        if (txtNome.isEnabled()) {
            //Se o usuário decidir fazer o update, o processo começa deixando em falso
            bnome = false;

            try {
                String nome = txtNome.getText();

                UsuarioDAO.uusuarioNome(nome, usuario.getId());

                usuario.setNome(nome);

                //Se o processo for concluído com sucesso, volta para true
                bnome = true;

            } catch (SQLException ex) {
                Logger.getLogger(IfrmEditUsuario.class.getName()).log(Level.SEVERE, null, ex);

                //Se houver erro no processo, fica false de novo
                bnome = false;
            }
        }

        if (txtNickname.isEnabled()) {
            //Se o usuário decidir fazer o update, o processo começa deixando em falso
            bnick = false;

            try {
                String nick = txtNickname.getText();

                UsuarioDAO.uusuarioNick(nick, usuario.getId());

                usuario.setNome(nick);

                //Se o processo for concluído com sucesso, volta para true
                bnick = true;
            } catch (SQLException ex) {
                if (ex.getErrorCode() == ClsEstilo.duplicateKeyError) {
                    JOptionPane.showMessageDialog(this, "Este nickname já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                System.out.println("Error code: " + ex.getErrorCode());
                Logger.getLogger(IfrmEditUsuario.class.getName()).log(Level.SEVERE, null, ex);

                //Se houver erro no processo, fica false de novo
                bnick = false;
            }
        }

        if (rdoComum.isEnabled()) {
            editTipo();
        }

        if (bnome && bnick) {
            JOptionPane.showMessageDialog(null, "Modificações atualizadas com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a atualização de suas modificações. Por favor tente novamente mais tarde.");
        }

        try {
            usuario = UsuarioDAO.cusuarioId(usuario.getId());
            preencheCampos();
        } catch (SQLException ex) {
            Logger.getLogger(IfrmEditUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        FrmPrincipal.bConta = false;
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSenhaActionPerformed
        FrmPrincipal.addFrame(new IfrmResetSenha(usuario));
    }//GEN-LAST:event_btnSenhaActionPerformed

    private void btnExcluiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluiActionPerformed
        try {
            UsuarioDAO.uusuarioAtivo(!usuario.isAtivo(), usuario.getId());
            preencheCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao mudar o status do usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmEditUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluiActionPerformed

    private void lblEditarTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarTipoMouseClicked
        rdoComum.setEnabled(true);
        rdoAdmin.setEnabled(true);
    }//GEN-LAST:event_lblEditarTipoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpTipo;
    private javax.swing.JButton btnExclui;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblAtivo;
    private javax.swing.JLabel lblEditarNick;
    private javax.swing.JLabel lblEditarNome;
    private javax.swing.JLabel lblEditarTipo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblSauda;
    private javax.swing.JRadioButton rdoAdmin;
    private javax.swing.JRadioButton rdoComum;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtNickname;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
