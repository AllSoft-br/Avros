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

import br.com.allsoft.avros.exceptions.ValorInvalidoMoedaException;
import br.com.allsoft.avros.dao.ClienteDAO;
import br.com.allsoft.avros.factory.JDBCConsulta;
import br.com.allsoft.avros.factory.JDBCInsere;
import br.com.allsoft.avros.dao.OrcamentoDAO;
import br.com.allsoft.avros.formulas.Moeda;
import br.com.allsoft.avros.formulas.VerificaCpf;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Luana
 */
public class IfrmCadOrcamento extends javax.swing.JInternalFrame {

    //Variáveis
    ClienteDAO cliente = new ClienteDAO();
    public static OrcamentoDAO orcamento = new OrcamentoDAO();
    boolean continuar = false;

    /**
     * Creates new form ifrmNovoOrcamento
     */
    public IfrmCadOrcamento() {
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

        bgpPagamento = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rbtnCartao = new javax.swing.JRadioButton();
        rbtnDinheiro = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        spnSessoes = new javax.swing.JSpinner();
        txtNome = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        lblLogo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblValSessao = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        ftxtValor = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        lblAviso = new javax.swing.JLabel();

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

        jLabel1.setFont(ClsEstilo.tituloFonte);
        jLabel1.setForeground(ClsEstilo.tituloCor);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Novo Orçamento");

        jLabel2.setFont(ClsEstilo.labelFonte);
        jLabel2.setForeground(ClsEstilo.labelCor);
        jLabel2.setText("Nome");

        jLabel3.setFont(ClsEstilo.labelFonte);
        jLabel3.setForeground(ClsEstilo.labelCor);
        jLabel3.setText("CPF");

        jLabel4.setFont(ClsEstilo.labelFonte);
        jLabel4.setForeground(ClsEstilo.labelCor);
        jLabel4.setText("Tipo de pagamento:");

        bgpPagamento.add(rbtnCartao);
        rbtnCartao.setFont(ClsEstilo.labelFonte);
        rbtnCartao.setForeground(ClsEstilo.labelCor);
        rbtnCartao.setText("Cartão");

        bgpPagamento.add(rbtnDinheiro);
        rbtnDinheiro.setFont(ClsEstilo.labelFonte);
        rbtnDinheiro.setForeground(ClsEstilo.labelCor);
        rbtnDinheiro.setText("Dinheiro");

        jLabel5.setFont(ClsEstilo.labelFonte);
        jLabel5.setForeground(ClsEstilo.labelCor);
        jLabel5.setText("Valor total");

        jLabel6.setFont(ClsEstilo.labelFonte);
        jLabel6.setForeground(ClsEstilo.labelCor);
        jLabel6.setText("Sessões");

        spnSessoes.setModel(new javax.swing.SpinnerNumberModel(1, 1, 20, 1));
        spnSessoes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnSessoesStateChanged(evt);
            }
        });
        spnSessoes.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                spnSessoesCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        txtNome.setFont(ClsEstilo.textoInputFonte);
        txtNome.setForeground(ClsEstilo.textoInputCor);
        txtNome.setNextFocusableComponent(ftxtValor);

        txtCpf.setFont(ClsEstilo.textoInputFonte);
        txtCpf.setForeground(ClsEstilo.textoInputCor);
        txtCpf.setNextFocusableComponent(txtNome);
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCpfFocusLost(evt);
            }
        });

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/logopequeno.png"))); // NOI18N

        jLabel7.setFont(ClsEstilo.labelFonte);
        jLabel7.setForeground(ClsEstilo.labelCor);
        jLabel7.setText("Valor por sessão:");

        lblValSessao.setFont(ClsEstilo.labelFonte);
        lblValSessao.setForeground(ClsEstilo.labelDinheiroCor);
        lblValSessao.setText("R$ 0,00");

        btnSalvar.setFont(ClsEstilo.botaoFonte);
        btnSalvar.setForeground(ClsEstilo.botaoCor);
        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnImprimir.setFont(ClsEstilo.botaoFonte);
        btnImprimir.setForeground(ClsEstilo.botaoCor);
        btnImprimir.setText("Imprimir");

        MaskFormatter dateMask = new MaskFormatter();
        dateMask.setPlaceholderCharacter('0') ;
        dateMask.install(ftxtValor);
        ftxtValor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftxtValor.setForeground(ClsEstilo.textoInputCor);
        ftxtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        ftxtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftxtValor.setFont(ClsEstilo.textoInputFonte);

        jLabel8.setFont(ClsEstilo.textoInputFonte);
        jLabel8.setForeground(ClsEstilo.textoInputCor);
        jLabel8.setText("R$");

        lblAviso.setForeground(new java.awt.Color(255, 0, 0));
        lblAviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAviso.setText("Digite um CPF válido.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtnCartao)
                        .addGap(22, 22, 22)
                        .addComponent(rbtnDinheiro))
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ftxtValor))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(spnSessoes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValSessao)))
                .addGap(205, 205, 205))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome)
                                    .addComponent(txtCpf)))
                            .addComponent(lblAviso, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnImprimir))
                            .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvar)
                            .addComponent(btnImprimir)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAviso)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtnCartao)
                            .addComponent(rbtnDinheiro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ftxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(spnSessoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblValSessao))))
                .addContainerGap(59, Short.MAX_VALUE))
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

    private void spnSessoesCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_spnSessoesCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spnSessoesCaretPositionChanged

    private void spnSessoesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSessoesStateChanged
        try {
            int sessoes = (Integer) spnSessoes.getValue();
            lblValSessao.setText(Moeda.calculaSessao(ftxtValor.getText(), sessoes));
        } catch (ValorInvalidoMoedaException ex) {
            Logger.getLogger(IfrmCadOrcamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_spnSessoesStateChanged

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        FrmPrincipal.bNovoOrcamento = false;
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusLost
        String cpf = txtCpf.getText();

        if (VerificaCpf.isCpf(cpf)) {
            try {
                cliente = JDBCConsulta.clienteCpf(cpf);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar o cliente pelo CPF.", "Tente outra vez", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmCadOrcamento.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (cliente.getCpf() == null) {
                lblAviso.setText("O cliente não está cadastrado.");
                lblAviso.setForeground(Color.RED);
                lblAviso.setVisible(true);
            } else {
                lblAviso.setText("Cliente encontrado!");
                lblAviso.setForeground(Color.GREEN);
                lblAviso.setVisible(true);

                txtNome.setText(cliente.getNome());

                btnSalvar.setEnabled(true);
            }
        } else {
            lblAviso.setForeground(Color.red);
            lblAviso.setText("Digite um CPF válido.");
            lblAviso.setVisible(true);

            txtCpf.requestFocus();
            txtCpf.selectAll();
        }
    }//GEN-LAST:event_txtCpfFocusLost

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (rbtnCartao.isSelected() | rbtnDinheiro.isSelected()) {
            continuar = true;
        }

        if (continuar) {
            //Garante que o valor nunca tenha mais que 2 algarismos
            //depois da vírgula
            double vtotal = Double.parseDouble(ftxtValor.getText().replace(",", "."));

            String pagamento = "Não especificado";
            if (rbtnCartao.isSelected()) {
                pagamento = "Cartão";
            } else if (rbtnDinheiro.isSelected()) {
                pagamento = "Dinheiro";
            }

            orcamento.setTipoPagamento(pagamento);
            orcamento.setIdCliente(cliente.getId());
            orcamento.setValor(vtotal);
            orcamento.setSessoes((Integer) spnSessoes.getValue());
            try {
                orcamento.setId(JDBCInsere.inserirOrcamento(orcamento));

                int j = JOptionPane.showConfirmDialog(this, "Orçamento salvo com sucesso. Deseja agendar uma sessão?", "Orçamento salvo", JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    FrmPrincipal.addFrame(new IfrmCadSessao(orcamento, cliente));
                }

                rbtnCartao.setSelected(false);
                rbtnDinheiro.setSelected(false);
                ftxtValor.setText("0,00");
                lblValSessao.setText("R$ 0,00");
                
                JOptionPane.showMessageDialog(this, "Orçamento cadastrado com sucesso.");
            } catch (SQLException | IOException ex) {
                Logger.getLogger(IfrmCadOrcamento.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o orçamento.", "Tente outra vez", JOptionPane.ERROR_MESSAGE);
                
            }

        } else {
            int decisao = JOptionPane.showConfirmDialog(this, "O tipo de pagamento não foi especificado. Deseja continuar?", "Aviso", JOptionPane.OK_CANCEL_OPTION);
            if (decisao == JOptionPane.OK_OPTION) {
                continuar = true;
                btnSalvar.doClick();
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpPagamento;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JFormattedTextField ftxtValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblValSessao;
    private javax.swing.JRadioButton rbtnCartao;
    private javax.swing.JRadioButton rbtnDinheiro;
    private javax.swing.JSpinner spnSessoes;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
