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

import br.com.allsoft.avros.dao.ClienteDAO;
import br.com.allsoft.avros.exceptions.AuditoriaException;
import br.com.allsoft.avros.factory.JDBCInsere;
import br.com.allsoft.avros.naoUsar.GeraCPF;
import br.com.allsoft.avros.formulas.Cpf;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Luana
 */
public class IfrmCadCliente extends javax.swing.JInternalFrame {

    //Metodos
    private void limpaCampos() {
        ftxtNascimento.setValue(null);
        ftxtNascimento.setText("00/00/0000");
        txtCpf.setText("");
        txtNome.setText("");
        txtTel.setText("");
    }

    /**
     * Creates new form ifrmCadCliente
     */
    public IfrmCadCliente() {
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

        label5 = new java.awt.Label();
        bgpSexo = new javax.swing.ButtonGroup();
        lblLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rdoFeminino = new javax.swing.JRadioButton();
        rdoMasculino = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        ftxtNascimento = new javax.swing.JFormattedTextField();
        txtCpf = new javax.swing.JFormattedTextField();
        btnCadastrar = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();

        label5.setText("label5");

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/Users.png"))); // NOI18N
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

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/logopequeno.png"))); // NOI18N

        jLabel1.setFont(ClsEstilo.labelFonte);
        jLabel1.setForeground(ClsEstilo.labelCor);
        jLabel1.setText("Nome");

        jLabel2.setFont(ClsEstilo.labelFonte);
        jLabel2.setForeground(ClsEstilo.labelCor);
        jLabel2.setText("CPF");

        jLabel3.setFont(ClsEstilo.labelFonte);
        jLabel3.setForeground(ClsEstilo.labelCor);
        jLabel3.setText("Nascimento");

        jLabel4.setFont(ClsEstilo.labelFonte);
        jLabel4.setForeground(ClsEstilo.labelCor);
        jLabel4.setText("Telefone");

        jLabel5.setFont(ClsEstilo.labelFonte);
        jLabel5.setForeground(ClsEstilo.labelCor);
        jLabel5.setText("Sexo");

        bgpSexo.add(rdoFeminino);
        rdoFeminino.setText("Feminino");
        rdoFeminino.setOpaque(false);

        bgpSexo.add(rdoMasculino);
        rdoMasculino.setText("Masculino");
        rdoMasculino.setNextFocusableComponent(txtTel);
        rdoMasculino.setOpaque(false);

        jLabel6.setFont(ClsEstilo.tituloFonte);
        jLabel6.setForeground(ClsEstilo.tituloCor);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cadastrar cliente");

        ftxtNascimento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftxtNascimento.setForeground(ClsEstilo.textoInputCor);
        ftxtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftxtNascimento.setFont(ClsEstilo.textoInputFonte);
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('0') ;
            dateMask.install(ftxtNascimento);
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        txtCpf.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCpf.setForeground(ClsEstilo.textoInputCor);
        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(ClsEstilo.textoInputFonte);

        btnCadastrar.setFont(ClsEstilo.botaoFonte);
        btnCadastrar.setForeground(ClsEstilo.botaoCor);
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setFocusCycleRoot(true);
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        btnCadastrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCadastrarKeyPressed(evt);
            }
        });

        txtNome.setFont(ClsEstilo.textoInputFonte);
        txtNome.setForeground(ClsEstilo.textoInputCor);
        txtNome.setFocusCycleRoot(true);
        txtNome.setNextFocusableComponent(txtCpf);
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });

        txtTel.setFont(ClsEstilo.textoInputFonte);
        txtTel.setForeground(ClsEstilo.textoInputCor);
        txtTel.setFocusCycleRoot(true);
        txtTel.setNextFocusableComponent(btnCadastrar);
        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCadastrar)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(rdoFeminino)
                            .addGap(18, 18, 18)
                            .addComponent(rdoMasculino)))
                    .addComponent(ftxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(lblLogo)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ftxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdoFeminino)
                            .addComponent(rdoMasculino))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(btnCadastrar)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        setBounds(0, 0, 515, 353);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);
        
        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        FrmPrincipal.bCliente = false;
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnCadastrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCadastrarKeyPressed
        if (evt.isAltDown() && evt.getKeyCode() == KeyEvent.VK_A) {

            txtNome.setText("Maria da Silva");
            //txtCpf.setText("18692409103");
            txtCpf.setText(new GeraCPF().geraCPFFinal());
            ftxtNascimento.setText("14/07/1997");
            rdoFeminino.setSelected(true);
            txtTel.setText("8475-8475");

            System.out.println("Atalho ativado");
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnCadastrar.doClick();
        }
    }//GEN-LAST:event_btnCadastrarKeyPressed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        int idade = 0;
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String nascimento = ftxtNascimento.getText();
        String tel = txtTel.getText();

        ClienteDAO cliente = new ClienteDAO();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setNascimento(nascimento);
        cliente.setTel(tel);
        cliente.setFeminino(rdoFeminino.isSelected());

        //Se os campos do formulário não estiverem em branco, insere o cliente
        if (!("".equals(nome)) && !("".equals(cpf)) && !("".equals(nascimento)) && !("".equals(tel))) {
            if (Cpf.isCpf(cpf)) {
                try {
                    idade = cliente.idade();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                if (idade < 18) {
                    FrmPrincipal.addFrame(new IfrmCadResp(cliente));
                    limpaCampos();
                } else {
                    try {
                        JDBCInsere.inserirCliente(cliente, FrmLogin.usuario.getId());
                        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso.");
                        limpaCampos();
                    } catch (SQLException ex) {
                        if (ex.getErrorCode() == ClsEstilo.duplicateKeyError) {
                            JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Erro ao cadastrar o cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        System.out.println("Error code: " + ex.getErrorCode());
                        Logger.getLogger(IfrmCadCliente.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "CPF inválido.");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Por favor, preencha todos os campos.");
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed

    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped
       
    }//GEN-LAST:event_txtNomeKeyTyped

    private void txtTelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelKeyPressed

    private void txtTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpSexo;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JFormattedTextField ftxtNascimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private java.awt.Label label5;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JRadioButton rdoFeminino;
    private javax.swing.JRadioButton rdoMasculino;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
