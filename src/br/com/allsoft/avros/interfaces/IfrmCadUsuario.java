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
import br.com.allsoft.avros.exceptions.AuditoriaException;
import br.com.allsoft.avros.factory.JDBCInsere;
import br.com.allsoft.avros.formulas.Cpf;
import br.com.allsoft.avros.naoUsar.GeraCPF;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luana
 */
public class IfrmCadUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form ifrmSenha
     */
    public IfrmCadUsuario() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btgAdmin = new javax.swing.ButtonGroup();
        rdoAdmin = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtNick = new java.awt.TextField();
        jLabel3 = new javax.swing.JLabel();
        pswConf = new javax.swing.JPasswordField();
        btnCadastra = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        pswSenha = new javax.swing.JPasswordField();
        rdoComum = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        lblAviso = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNome = new java.awt.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

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

        btgAdmin.add(rdoAdmin);
        rdoAdmin.setText("Administrador");

        jLabel4.setFont(ClsEstilo.labelFonte);
        jLabel4.setForeground(ClsEstilo.labelCor);
        jLabel4.setText("Confirma senha");

        txtNick.setFont(ClsEstilo.textoInputFonte);
        txtNick.setForeground(ClsEstilo.textoInputCor);

        jLabel3.setFont(ClsEstilo.labelFonte);
        jLabel3.setForeground(ClsEstilo.labelCor);
        jLabel3.setText("Senha");

        pswConf.setForeground(ClsEstilo.textoInputCor);

        btnCadastra.setFont(ClsEstilo.botaoFonte);
        btnCadastra.setForeground(ClsEstilo.botaoCor);
        btnCadastra.setText("Cadastrar");
        btnCadastra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastraActionPerformed(evt);
            }
        });
        btnCadastra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCadastraKeyPressed(evt);
            }
        });

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/logopequeno.png"))); // NOI18N

        pswSenha.setForeground(ClsEstilo.textoInputCor);

        btgAdmin.add(rdoComum);
        rdoComum.setSelected(true);
        rdoComum.setText("Usuário comum");

        jLabel5.setFont(ClsEstilo.tituloFonte);
        jLabel5.setForeground(ClsEstilo.tituloCor);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cadastrar novo usuário");

        lblAviso.setFont(ClsEstilo.labelFonte);
        lblAviso.setForeground(new java.awt.Color(255, 0, 0));
        lblAviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAviso.setText("As senhas digitadas não combinam.");

        jLabel6.setFont(ClsEstilo.labelFonte);
        jLabel6.setForeground(ClsEstilo.labelCor);
        jLabel6.setText("Nome");

        txtNome.setFont(ClsEstilo.textoInputFonte);
        txtNome.setForeground(ClsEstilo.textoInputCor);

        jLabel2.setFont(ClsEstilo.labelFonte);
        jLabel2.setForeground(ClsEstilo.labelCor);
        jLabel2.setText("Nickname");

        jLabel7.setFont(ClsEstilo.labelFonte);
        jLabel7.setForeground(ClsEstilo.labelCor);
        jLabel7.setText("CPF");

        txtCpf.setColumns(11);
        txtCpf.setFont(ClsEstilo.textoInputFonte);
        txtCpf.setForeground(ClsEstilo.textoInputCor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pswSenha)
                                    .addComponent(pswConf)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdoComum)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoAdmin))
                                    .addComponent(txtCpf)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(btnCadastra)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pswConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoComum)
                            .addComponent(rdoAdmin))
                        .addGap(24, 24, 24)))
                .addComponent(lblAviso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadastra)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        lblAviso.setVisible(false);
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnCadastraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastraActionPerformed

        char[] senha = pswSenha.getPassword();
        char[] conf = pswConf.getPassword();

        if (Arrays.equals(senha, conf)) {
            String nome = txtNome.getText();
            String nick = txtNick.getText();
            String cpf = txtCpf.getText();

            if (Cpf.isCpf(cpf)) {
                lblAviso.setVisible(false);
                UsuarioDAO usuario = new UsuarioDAO();
                usuario.setNick(nick);
                usuario.setNome(nome);
                usuario.setCpf(cpf);
                usuario.setSenha(senha);
                usuario.setAdmin(rdoAdmin.isSelected());

                try {
                    JDBCInsere.inserirUsuario(usuario);
                    pswSenha.setText("");
                    pswConf.setText("");
                    txtNome.setText("");
                    txtCpf.setText("");
                    txtNick.setText("");

                    JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso.");
                } catch (IOException ex) {
                    Logger.getLogger(IfrmCadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    if (ex.getErrorCode() == ClsEstilo.duplicateKeyError) {
                        JOptionPane.showMessageDialog(this, "Nick/CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao cadastrar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println("Error code: " + ex.getErrorCode());
                    Logger.getLogger(IfrmCadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                lblAviso.setText("CPF inválido.");
                lblAviso.setVisible(true);
            }

        } else {
            lblAviso.setVisible(true);
        }
    }//GEN-LAST:event_btnCadastraActionPerformed

    private void btnCadastraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCadastraKeyPressed
        if (evt.isAltDown() && evt.getKeyCode() == KeyEvent.VK_A) {

            txtNome.setText("Maria da Silva");
            txtNick.setText("MariaSilva");
            txtCpf.setText(new GeraCPF().geraCPFFinal());
            pswSenha.setText("12345");
            pswConf.setText("12345");

            System.out.println("Atalho ativado");
        }
    }//GEN-LAST:event_btnCadastraKeyPressed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        FrmPrincipal.bUsuario = false;
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgAdmin;
    private javax.swing.JButton btnCadastra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPasswordField pswConf;
    private javax.swing.JPasswordField pswSenha;
    private javax.swing.JRadioButton rdoAdmin;
    private javax.swing.JRadioButton rdoComum;
    private javax.swing.JTextField txtCpf;
    private java.awt.TextField txtNick;
    private java.awt.TextField txtNome;
    // End of variables declaration//GEN-END:variables
}
