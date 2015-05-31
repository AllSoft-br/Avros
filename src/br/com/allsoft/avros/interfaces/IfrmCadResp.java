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

import br.com.allsoft.avros.exceptions.AuditoriaException;
import br.com.allsoft.avros.formulas.Cpf;
import br.com.allsoft.avros.modelo.Cliente;
import br.com.allsoft.avros.naoUsar.GeraCPF;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Luana
 */
public class IfrmCadResp extends javax.swing.JInternalFrame {

    Cliente menor = new Cliente();

    /**
     * Creates new form IfrmCadResp
     *
     * @param cliente cliente menor de idade
     */
    public IfrmCadResp(Cliente cliente) {
        initComponents();

        menor = cliente;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgpSexo = new javax.swing.ButtonGroup();
        ftxtNascimento = new javax.swing.JFormattedTextField();
        rdoFeminino = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rdoMasculino = new javax.swing.JRadioButton();
        lblLogo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboParentesco = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtQual = new javax.swing.JTextField();
        lblQual = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        lblAviso = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();

        setBackground(ClsEstilo.formbg);
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

        ftxtNascimento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftxtNascimento.setForeground(ClsEstilo.textoInputCor);
        ftxtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftxtNascimento.setFont(ClsEstilo.textoInputFonte);
        ftxtNascimento.setNextFocusableComponent(rdoFeminino);
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.install(ftxtNascimento);
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        bgpSexo.add(rdoFeminino);
        rdoFeminino.setText("Feminino");
        rdoFeminino.setNextFocusableComponent(rdoMasculino);
        rdoFeminino.setOpaque(false);

        jLabel5.setFont(ClsEstilo.labelFonte);
        jLabel5.setForeground(ClsEstilo.labelCor);
        jLabel5.setText("Sexo");

        jLabel6.setFont(ClsEstilo.tituloFonte);
        jLabel6.setForeground(ClsEstilo.tituloCor);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cadastrar responsável");

        bgpSexo.add(rdoMasculino);
        rdoMasculino.setText("Masculino");
        rdoMasculino.setOpaque(false);

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/logopequeno.png"))); // NOI18N

        jLabel4.setFont(ClsEstilo.labelFonte);
        jLabel4.setForeground(ClsEstilo.labelCor);
        jLabel4.setText("Telefone");

        jLabel3.setFont(ClsEstilo.labelFonte);
        jLabel3.setForeground(ClsEstilo.labelCor);
        jLabel3.setText("Nascimento");

        jLabel2.setFont(ClsEstilo.labelFonte);
        jLabel2.setForeground(ClsEstilo.labelCor);
        jLabel2.setText("CPF");

        jLabel1.setFont(ClsEstilo.labelFonte);
        jLabel1.setForeground(ClsEstilo.labelCor);
        jLabel1.setText("Nome");

        cboParentesco.setFont(ClsEstilo.labelFonte);
        cboParentesco.setForeground(ClsEstilo.labelCor);
        cboParentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "Avô", "Avó", "Tia", "Tio", "Mãe", "Pai", "Madrasta", "Padrasto", "Outro" }));
        cboParentesco.setNextFocusableComponent(btnCadastrar);
        cboParentesco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboParentescoItemStateChanged(evt);
            }
        });
        cboParentesco.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cboParentescoCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jLabel7.setFont(ClsEstilo.labelFonte);
        jLabel7.setForeground(ClsEstilo.labelCor);
        jLabel7.setText("Parentesco");

        txtQual.setFont(ClsEstilo.textoInputFonte);
        txtQual.setForeground(ClsEstilo.textoInputCor);
        txtQual.setNextFocusableComponent(btnCadastrar);
        txtQual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQualActionPerformed(evt);
            }
        });

        lblQual.setFont(ClsEstilo.labelFonte);
        lblQual.setForeground(ClsEstilo.labelCor);
        lblQual.setText("Qual?");

        txtNome.setFont(ClsEstilo.textoInputFonte);
        txtNome.setForeground(ClsEstilo.textoInputCor);
        txtNome.setFocusCycleRoot(true);
        txtNome.setNextFocusableComponent(txtCpf);

        txtTel.setFont(ClsEstilo.textoInputFonte);
        txtTel.setForeground(ClsEstilo.textoInputCor);
        txtTel.setNextFocusableComponent(cboParentesco);

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

        lblAviso.setForeground(new java.awt.Color(255, 0, 0));
        lblAviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAviso.setText("Digite um CPF válido.");

        txtCpf.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCpf.setForeground(ClsEstilo.textoInputCor);
        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(ClsEstilo.textoInputFonte);
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCpfFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCadastrar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTel)
                                            .addComponent(cboParentesco, 0, 154, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblQual))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdoFeminino)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoMasculino))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ftxtNascimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                                .addComponent(lblAviso, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQual, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
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
                        .addGap(3, 3, 3)
                        .addComponent(lblAviso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ftxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdoFeminino)
                            .addComponent(rdoMasculino))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cboParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQual)))
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCadastrar)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        lblQual.setVisible(false);
        txtQual.setVisible(false);

        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        lblAviso.setVisible(false);
    }//GEN-LAST:event_formInternalFrameOpened

    private void cboParentescoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboParentescoItemStateChanged
        //se o ultimo item (Outro) estiver selecionado, os campos Qual aparecem
        if (cboParentesco.getSelectedIndex() == (cboParentesco.getItemCount() - 1)) {
            lblQual.setVisible(true);
            txtQual.setVisible(true);
        } else {
            lblQual.setVisible(false);
            txtQual.setVisible(false);
        }
    }//GEN-LAST:event_cboParentescoItemStateChanged

    private void cboParentescoCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cboParentescoCaretPositionChanged

    }//GEN-LAST:event_cboParentescoCaretPositionChanged

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        FrmPrincipal.bResp = false;
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
       if (cboParentesco.getSelectedIndex() > 0) {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String nascimento = ftxtNascimento.getText();
            String tel = txtTel.getText();
            int parentescoId = 0;

            if (cboParentesco.getSelectedIndex() == (cboParentesco.getItemCount() - 1)) {
                try {
                    parentescoId = JDBCInsere.inserirParentesco(txtQual.getText());
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(IfrmCadResp.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                parentescoId = cboParentesco.getSelectedIndex();
            }

            if (parentescoId > 0) {
                if (Cpf.isCpf(cpf)) {
                    Representante responsavel = new Representante();
                    responsavel.setNome(nome);
                    responsavel.setCpf(cpf);
                    responsavel.setNascimento(nascimento);
                    responsavel.setTel(tel);
                    responsavel.setFeminino(rdoFeminino.isSelected());

                    if (menor.getId() < 1) {
                        try {
                            JDBCInsere.inserirClienteMenor(responsavel, menor, parentescoId, FrmLogin.usuario.getId());
                            this.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(IfrmCadResp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            responsavel.setId(JDBCInsere.inserirRepresentante(responsavel));
                            JDBCInsere.inserirRelCliRep(responsavel.getId(), menor.getId(), parentescoId);

                            JOptionPane.showMessageDialog(this, "O representante de " + menor.getNome() + " foi cadastrado com sucesso.");
                        } catch (SQLException ex) {
                            if (ex.getErrorCode() == ClsEstilo.duplicateKeyError) {
                                JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "Erro ao cadastrar.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                            System.out.println("Error code: " + ex.getErrorCode());
                            Logger.getLogger(IfrmCadResp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "CPF inválido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro na declaração de parentesco.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Escolha o grau de parentesco.");
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnCadastrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCadastrarKeyPressed
       if (evt.isAltDown() && evt.getKeyCode() == KeyEvent.VK_A) {

            txtNome.setText("Marcos da Silva");
            //txtCpf.setText("18692409103");
            txtCpf.setText(new GeraCPF().geraCPFFinal());
            ftxtNascimento.setText("14/07/1980");
            rdoMasculino.setSelected(true);
            txtTel.setText("8475-8475");

            System.out.println("Atalho ativado");
        }
       
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           btnCadastrar.doClick();
       }
    }//GEN-LAST:event_btnCadastrarKeyPressed

    private void txtQualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQualActionPerformed

    private void txtCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusLost
        String cpf = txtCpf.getText();

        if (Cpf.isCpf(cpf)) {
            lblAviso.setVisible(false);
        } else {
            lblAviso.setVisible(true);
            txtCpf.selectAll();
        }
    }//GEN-LAST:event_txtCpfFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpSexo;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JComboBox cboParentesco;
    private javax.swing.JFormattedTextField ftxtNascimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblQual;
    private javax.swing.JRadioButton rdoFeminino;
    private javax.swing.JRadioButton rdoMasculino;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtQual;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
