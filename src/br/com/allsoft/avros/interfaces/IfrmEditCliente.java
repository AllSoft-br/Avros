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
import br.com.allsoft.avros.factory.JDBCConsulta;
import br.com.allsoft.avros.factory.JDBCUpdate;
import br.com.allsoft.avros.dao.RepresentanteDAO;
import br.com.allsoft.avros.factory.JDBCViews;
import br.com.allsoft.avros.formulas.Datas;
import br.com.allsoft.avros.formulas.Cpf;
import java.awt.Container;
import java.awt.Dimension;
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
public class IfrmEditCliente extends javax.swing.JInternalFrame {

    //Variáveis
    ClienteDAO cliente = new ClienteDAO();
    boolean sexo = false;
    
    //Métodos
    private void atualiza(){
        lblResp.setVisible(false);
        String tipo = "não especificado";

        try {
            if (cliente.idade() < 18) {
                tipo = "menor de idade";
                lblResp.setVisible(true);
            } else {
                tipo = "maior de idade";
            }
        } catch (SQLException ex) {
            Logger.getLogger(IfrmEditCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblSauda.setText(cliente.getNome() + " é " + tipo + ".");
        txtNome.setText(cliente.getNome());
        txtCpf.setText(Cpf.imprimeCpf(cliente.getCpf()));
        txtUsuario.setEditable(false);
        ftxtNasc.setText(Datas.sqlparaString(cliente.getNascimento()));
        txtTel.setText(cliente.getTel());

        if (cliente.isFeminino()) {
            rdoF.setSelected(true);
        } else {
            rdoM.setSelected(true);
        }

        try {
            txtUsuario.setText(JDBCConsulta.usuarioId(cliente.getIdUsuario()).getNick());
        } catch (SQLException ex) {
            this.dispose();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar informações do cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmEditCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates new form ifrmConta
     *
     * @param cliente cliente a ser visualizado
     */
    public IfrmEditCliente(ClienteDAO cliente) {
        initComponents();

        this.cliente = cliente;
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
        txtNome = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        lblEditarNome = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        rdoF = new javax.swing.JRadioButton();
        rdoM = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        btnSenha1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblEditarNasc = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        lblEditarNome1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblResp = new javax.swing.JLabel();
        ftxtNasc = new javax.swing.JFormattedTextField();

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
        jLabel1.setText("Editar cliente");

        lblSauda.setFont(ClsEstilo.labelDestaqueFonte);
        lblSauda.setForeground(ClsEstilo.labelDestaqueCor);
        lblSauda.setText("Fulano da silva é um menor de idade.");

        jLabel3.setFont(ClsEstilo.labelFonte);
        jLabel3.setForeground(ClsEstilo.labelCor);
        jLabel3.setText("Nome");

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

        lblEditarNome.setBackground(ClsEstilo.formbg);
        lblEditarNome.setFont(ClsEstilo.linkFonte);
        lblEditarNome.setForeground(ClsEstilo.linkCor);
        lblEditarNome.setText("Editar");
        lblEditarNome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        txtCpf.setFont(ClsEstilo.textoInputFonte);
        txtCpf.setForeground(ClsEstilo.textoInputCor);
        txtCpf.setEnabled(false);

        bgpTipo.add(rdoF);
        rdoF.setFont(ClsEstilo.labelFonte);
        rdoF.setForeground(ClsEstilo.labelCor);
        rdoF.setText("Feminino");
        rdoF.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoFStateChanged(evt);
            }
        });

        bgpTipo.add(rdoM);
        rdoM.setFont(ClsEstilo.labelFonte);
        rdoM.setForeground(ClsEstilo.labelCor);
        rdoM.setText("Masculino");
        rdoM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoMStateChanged(evt);
            }
        });

        jLabel5.setFont(ClsEstilo.labelFonte);
        jLabel5.setForeground(ClsEstilo.labelCor);
        jLabel5.setText("Sexo:");

        btnSenha1.setFont(ClsEstilo.botaoFonte);
        btnSenha1.setForeground(ClsEstilo.botaoCor);
        btnSenha1.setText("Excluir cliente");
        btnSenha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSenha1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(ClsEstilo.labelFonte);
        jLabel4.setForeground(ClsEstilo.labelCor);
        jLabel4.setText("Nascimento");

        lblEditarNasc.setBackground(ClsEstilo.formbg);
        lblEditarNasc.setFont(ClsEstilo.linkFonte);
        lblEditarNasc.setForeground(ClsEstilo.linkCor);
        lblEditarNasc.setText("Editar");
        lblEditarNasc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEditarNasc.setOpaque(true);
        lblEditarNasc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarNascMouseClicked(evt);
            }
        });

        txtTel.setFont(ClsEstilo.textoInputFonte);
        txtTel.setForeground(ClsEstilo.textoInputCor);
        txtTel.setText("858385-8485");
        txtTel.setEnabled(false);

        lblEditarNome1.setBackground(ClsEstilo.formbg);
        lblEditarNome1.setFont(ClsEstilo.linkFonte);
        lblEditarNome1.setForeground(ClsEstilo.linkCor);
        lblEditarNome1.setText("Editar");
        lblEditarNome1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEditarNome1.setOpaque(true);
        lblEditarNome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarNome1MouseClicked(evt);
            }
        });

        jLabel6.setFont(ClsEstilo.labelFonte);
        jLabel6.setForeground(ClsEstilo.labelCor);
        jLabel6.setText("Telefone");

        jLabel7.setFont(ClsEstilo.labelFonte);
        jLabel7.setForeground(ClsEstilo.labelCor);
        jLabel7.setText("Usuário responsável");

        txtUsuario.setFont(ClsEstilo.textoInputFonte);
        txtUsuario.setForeground(ClsEstilo.textoInputCor);

        lblResp.setBackground(ClsEstilo.formbg);
        lblResp.setFont(ClsEstilo.linkFonte);
        lblResp.setForeground(ClsEstilo.linkCor);
        lblResp.setText("Clique aqui para ver o responsável");
        lblResp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblResp.setOpaque(true);
        lblResp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRespMouseClicked(evt);
            }
        });

        ftxtNasc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        ftxtNasc.setForeground(ClsEstilo.textoInputCor);
        ftxtNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftxtNasc.setEnabled(false);
        ftxtNasc.setFont(ClsEstilo.textoInputFonte);
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('0') ;
            dateMask.install(ftxtNasc);
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSauda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblResp, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel5)))
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdoF)
                                                .addGap(34, 34, 34)
                                                .addComponent(rdoM))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtCpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblEditarNome))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtTel, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                                    .addComponent(ftxtNasc))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblEditarNasc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblEditarNome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSalvar)
                                        .addGap(34, 34, 34)
                                        .addComponent(btnSenha1)
                                        .addGap(32, 32, 32)))
                                .addGap(0, 22, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLogo))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSauda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblResp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEditarNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblEditarNasc)
                            .addComponent(ftxtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEditarNome1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoM)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoF)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnSenha1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);
        
        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        atualiza();
    }//GEN-LAST:event_formInternalFrameOpened

    private void lblEditarNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarNomeMouseClicked
        txtNome.setEnabled(true);
    }//GEN-LAST:event_lblEditarNomeMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        int id = cliente.getId();
        boolean certo = true;
        ClienteDAO editado = new ClienteDAO();

        if (txtNome.isEnabled()) {
            editado.setNome(txtNome.getText());

            try {
                JDBCUpdate.clienteNome(editado.getNome(), id);
                cliente.setNome(editado.getNome());
            } catch (SQLException ex) {
                certo = false;
                Logger.getLogger(IfrmEditCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ftxtNasc.isEnabled()) {
            editado.setNascimento(ftxtNasc.getText());
            try {
                JDBCUpdate.clienteNasc(editado.getNascimento(), id);
                cliente.setNascimento(editado.getNascimento());
            } catch (SQLException ex) {
                certo = false;
                Logger.getLogger(IfrmEditCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (txtTel.isEnabled()) {
            editado.setTel(txtTel.getText());
            try {
                JDBCUpdate.clienteTel(editado.getTel(), id);
                cliente.setTel(editado.getTel());
            } catch (SQLException ex) {
                certo = false;
                Logger.getLogger(IfrmEditCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (sexo) {
            boolean feminino = false;

            if (rdoF.isSelected()) {
                feminino = true;
            }

            try {
                JDBCUpdate.clienteSexo(feminino, id);
                cliente.setFeminino(feminino);
            } catch (SQLException ex) {
                certo = false;
                Logger.getLogger(IfrmEditCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(certo){
            JOptionPane.showMessageDialog(this, "Informações salvas com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Algumas informações não puderam ser salvas.");
        }
        atualiza();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed

    }//GEN-LAST:event_formInternalFrameClosed

    private void btnSenha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSenha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSenha1ActionPerformed

    private void lblEditarNascMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarNascMouseClicked
        ftxtNasc.setEnabled(true);
    }//GEN-LAST:event_lblEditarNascMouseClicked

    private void lblEditarNome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarNome1MouseClicked
        txtTel.setEnabled(true);
    }//GEN-LAST:event_lblEditarNome1MouseClicked

    private void rdoFStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoFStateChanged
        sexo = !sexo;
    }//GEN-LAST:event_rdoFStateChanged

    private void rdoMStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoMStateChanged
        sexo = !sexo;
    }//GEN-LAST:event_rdoMStateChanged

    private void lblRespMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRespMouseClicked
        try {
            RepresentanteDAO representante = new RepresentanteDAO();

            //Busca o grau de parentesco e ID do representante
            representante = JDBCViews.parentesco(cliente);
            representante = JDBCConsulta.representanteId(representante.getId());

            if(representante.getCpf() == null){
                int j = JOptionPane.showConfirmDialog(this, "Este cliente não possui representante cadastrado. Deseja cadastrar agora?", "Representante não encontrado", JOptionPane.OK_CANCEL_OPTION);
                if(j == JOptionPane.OK_OPTION){
                    FrmPrincipal.addFrame(new IfrmCadResp(cliente));
                }
            } else {
                FrmPrincipal.addFrame(new IfrmEditRepres(representante));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar informações do representante.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmEditCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblRespMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpTipo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSenha1;
    private javax.swing.JFormattedTextField ftxtNasc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblEditarNasc;
    private javax.swing.JLabel lblEditarNome;
    private javax.swing.JLabel lblEditarNome1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblResp;
    private javax.swing.JLabel lblSauda;
    private javax.swing.JRadioButton rdoF;
    private javax.swing.JRadioButton rdoM;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
