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
import br.com.allsoft.avros.dao.RepresentanteDAO;
import br.com.allsoft.avros.factory.JDBCInsere;
import br.com.allsoft.avros.formulas.VerificaCpf;
import br.com.allsoft.avros.naoUsar.GeraCPF;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
public class IfrmCadClienteMenor extends javax.swing.JInternalFrame {

    //Variáveis
    RepresentanteDAO representante;
    int parentescoId;

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
     *
     * @param rep representante deste cliente menor
     */
    public IfrmCadClienteMenor(RepresentanteDAO rep) {
        representante = rep;
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
        txtNome = new java.awt.TextField();
        txtTel = new java.awt.TextField();
        btnCadastrar = new java.awt.Button();
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
        lblQual = new javax.swing.JLabel();
        cboParentesco = new javax.swing.JComboBox();
        txtQual = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        label5.setText("label5");

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
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

        txtNome.setFont(ClsEstilo.textoInputFonte);
        txtNome.setForeground(ClsEstilo.textoInputCor);

        txtTel.setFont(ClsEstilo.textoInputFonte);
        txtTel.setForeground(ClsEstilo.textoInputCor);

        btnCadastrar.setFont(ClsEstilo.botaoFonte);
        btnCadastrar.setForeground(ClsEstilo.botaoCor);
        btnCadastrar.setLabel("Cadastrar");
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
        rdoMasculino.setOpaque(false);

        jLabel6.setFont(ClsEstilo.tituloFonte);
        jLabel6.setForeground(ClsEstilo.tituloCor);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cadastrar cliente");

        ftxtNascimento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
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

        txtCpf.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtCpf.setForeground(ClsEstilo.textoInputCor);
        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(ClsEstilo.textoInputFonte);

        lblQual.setFont(ClsEstilo.labelFonte);
        lblQual.setForeground(ClsEstilo.labelCor);
        lblQual.setText("Qual?");

        cboParentesco.setFont(ClsEstilo.labelFonte);
        cboParentesco.setForeground(ClsEstilo.labelCor);
        cboParentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "Avô", "Avó", "Madrasta", "Mãe", "Padrasto", "Pai", "Outro" }));
        cboParentesco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboParentescoItemStateChanged(evt);
            }
        });
        cboParentesco.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cboParentescoCaretPositionChanged(evt);
            }
        });

        txtQual.setFont(ClsEstilo.textoInputFonte);
        txtQual.setForeground(ClsEstilo.textoInputCor);

        jLabel7.setFont(ClsEstilo.labelFonte);
        jLabel7.setForeground(ClsEstilo.labelCor);
        jLabel7.setText("Selecione o grau de parentesco do representante");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdoFeminino)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoMasculino)
                                                .addGap(0, 78, Short.MAX_VALUE))
                                            .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(33, 33, 33))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ftxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(lblLogo)
                                .addGap(39, 39, 39))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(lblQual)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQual, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                            .addComponent(rdoMasculino))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        setBounds(0, 0, 515, 401);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

    }//GEN-LAST:event_formInternalFrameOpened

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

        if (cboParentesco.getSelectedIndex() == (cboParentesco.getItemCount() - 1)) {
            try {
                parentescoId = JDBCInsere.inserirParentesco(txtQual.getText());
            } catch (SQLException | IOException ex) {
                Logger.getLogger(IfrmCadResp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            parentescoId = cboParentesco.getSelectedIndex();
        }

        //Se os campos do formulário não estiverem em branco, insere o cliente
        if (!("".equals(nome)) && !("".equals(cpf)) && !("".equals(nascimento)) && !("".equals(tel))) {
            if (VerificaCpf.isCpf(cpf)) {
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
                        JDBCInsere.inserirRelCliRep(representante.getId(), cliente.getId(), parentescoId);

                        JOptionPane.showMessageDialog(this, "Cliente menor de idade cadastrado com sucesso.");
                        limpaCampos();
                    } catch (SQLException ex) {
                        Logger.getLogger(IfrmCadClienteMenor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "CPF inválido.");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Por favor, preencha todos os campos.");
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

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
    }//GEN-LAST:event_btnCadastrarKeyPressed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        FrmPrincipal.bCliente = false;
    }//GEN-LAST:event_formInternalFrameClosed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpSexo;
    private java.awt.Button btnCadastrar;
    private javax.swing.JComboBox cboParentesco;
    private javax.swing.JFormattedTextField ftxtNascimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private java.awt.Label label5;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblQual;
    private javax.swing.JRadioButton rdoFeminino;
    private javax.swing.JRadioButton rdoMasculino;
    private javax.swing.JFormattedTextField txtCpf;
    private java.awt.TextField txtNome;
    private javax.swing.JTextField txtQual;
    private java.awt.TextField txtTel;
    // End of variables declaration//GEN-END:variables
}
