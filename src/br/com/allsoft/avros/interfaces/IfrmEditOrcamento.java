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
import br.com.allsoft.avros.dao.OrcamentoDAO;
import br.com.allsoft.avros.exceptions.ValorInvalidoMoedaException;
import br.com.allsoft.avros.factory.JDBCConsulta;
import br.com.allsoft.avros.factory.JDBCDelete;
import br.com.allsoft.avros.factory.JDBCUpdate;
import br.com.allsoft.avros.formulas.Moeda;
import br.com.allsoft.avros.formulas.Cpf;
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
public class IfrmEditOrcamento extends javax.swing.JInternalFrame {

    //Variáveis

    OrcamentoDAO orcamento;
    ClienteDAO cliente;

    //Métodos
    private void editPagamento() throws SQLException {
        String pagamento = "Não especificado";

        if (rdoCartao.isSelected()) {
            pagamento = "Cartão";
        }
        if (rdoDinheiro.isSelected()) {
            pagamento = "Dinheiro";
        }

        JDBCUpdate.orcamentoPagamento(orcamento.getId(), pagamento);
    }

    private void editValor() throws ValorInvalidoMoedaException, SQLException {
        double valor = Moeda.retornaDouble(ftxtValor.getText());

        JDBCUpdate.orcamentoValor(orcamento.getId(), valor);
    }

    private void editSessoes() throws SQLException {
        int qtd = (int) spnSessoes.getValue();

        int j = JDBCConsulta.sessaoIdOrc(orcamento.getId()).size();

        if (qtd < j) {
            JOptionPane.showMessageDialog(this, "A quantidade de sessões inserida é menor que a quantidade de sessões já cadastrada neste orçamento. Por favor escolha um número maior.", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Quantidade de sessões digitada menor que a quantidade cadastrada.");
        } else if (qtd == j) {
            JDBCUpdate.orcamentoSessoes(orcamento.getId(), qtd);
            lblValSessao.setText(Moeda.padraoVirgula(orcamento.getValor() / qtd));
        }
    }
    
    private void editDesc() throws SQLException{
        JDBCUpdate.orcamentoDesc(orcamento.getId(), txtDesc.getText());
    }

    /**
     * Creates new form ifrmPesqOrcamento
     *
     * @param orca orçamento
     * @param cli cliente
     */
    public IfrmEditOrcamento(OrcamentoDAO orca, ClienteDAO cli) {
        orcamento = orca;
        cliente = cli;
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
        jLabel7 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        lblValSessao = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ftxtValor = new javax.swing.JFormattedTextField();
        rdoCartao = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rdoDinheiro = new javax.swing.JRadioButton();
        spnSessoes = new javax.swing.JSpinner();
        lblCpf = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblEditarPag = new javax.swing.JLabel();
        lblEditarValor = new javax.swing.JLabel();
        lblEditarSessoes = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        scrollDesc2 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextPane();
        lblEditarDesc = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/Caixa.PNG"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
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
        jLabel1.setText("Editar orçamento");

        jLabel7.setFont(ClsEstilo.labelFonte);
        jLabel7.setForeground(ClsEstilo.labelCor);
        jLabel7.setText("Valor por sessão:");

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/logopequeno.png"))); // NOI18N

        btnSalvar.setFont(ClsEstilo.botaoFonte);
        btnSalvar.setForeground(ClsEstilo.botaoCor);
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        lblValSessao.setFont(ClsEstilo.labelFonte);
        lblValSessao.setForeground(ClsEstilo.labelDinheiroCor);
        lblValSessao.setText("R$ 0,00");

        btnImprimir.setFont(ClsEstilo.botaoFonte);
        btnImprimir.setForeground(ClsEstilo.botaoCor);
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel2.setFont(ClsEstilo.labelFonte);
        jLabel2.setForeground(ClsEstilo.labelCor);
        jLabel2.setText("Nome");

        jLabel3.setFont(ClsEstilo.labelFonte);
        jLabel3.setForeground(ClsEstilo.labelCor);
        jLabel3.setText("CPF");

        jLabel4.setFont(ClsEstilo.labelFonte);
        jLabel4.setForeground(ClsEstilo.labelCor);
        jLabel4.setText("Tipo de pagamento:");

        jLabel5.setFont(ClsEstilo.labelFonte);
        jLabel5.setForeground(ClsEstilo.labelCor);
        jLabel5.setText("Valor total");

        ftxtValor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftxtValor.setForeground(ClsEstilo.textoInputCor);
        ftxtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        ftxtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftxtValor.setEnabled(false);
        ftxtValor.setFont(ClsEstilo.textoInputFonte);

        rdoCartao.setFont(ClsEstilo.labelFonte);
        rdoCartao.setForeground(ClsEstilo.labelCor);
        rdoCartao.setText("Cartão");
        rdoCartao.setEnabled(false);

        jLabel6.setFont(ClsEstilo.labelFonte);
        jLabel6.setForeground(ClsEstilo.labelCor);
        jLabel6.setText("Sessões");

        jLabel8.setFont(ClsEstilo.textoInputFonte);
        jLabel8.setForeground(ClsEstilo.textoInputCor);
        jLabel8.setText("R$");

        rdoDinheiro.setFont(ClsEstilo.labelFonte);
        rdoDinheiro.setForeground(ClsEstilo.labelCor);
        rdoDinheiro.setText("Dinheiro");
        rdoDinheiro.setEnabled(false);

        spnSessoes.setModel(new javax.swing.SpinnerNumberModel(1, 1, 20, 1));
        spnSessoes.setEnabled(false);
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

        lblCpf.setFont(ClsEstilo.labelFonte);
        lblCpf.setForeground(ClsEstilo.textoInputCor);
        lblCpf.setText("000.000.000-00");

        lblNome.setFont(ClsEstilo.labelFonte);
        lblNome.setForeground(ClsEstilo.textoInputCor);
        lblNome.setText("Fulano da Silva");

        lblEditarPag.setBackground(ClsEstilo.formbg);
        lblEditarPag.setFont(ClsEstilo.linkFonte);
        lblEditarPag.setForeground(ClsEstilo.linkCor);
        lblEditarPag.setText("Editar");
        lblEditarPag.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblEditarPag.setOpaque(true);
        lblEditarPag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarPagMouseClicked(evt);
            }
        });

        lblEditarValor.setBackground(ClsEstilo.formbg);
        lblEditarValor.setFont(ClsEstilo.linkFonte);
        lblEditarValor.setForeground(ClsEstilo.linkCor);
        lblEditarValor.setText("Editar");
        lblEditarValor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblEditarValor.setOpaque(true);
        lblEditarValor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarValorMouseClicked(evt);
            }
        });

        lblEditarSessoes.setBackground(ClsEstilo.formbg);
        lblEditarSessoes.setFont(ClsEstilo.linkFonte);
        lblEditarSessoes.setForeground(ClsEstilo.linkCor);
        lblEditarSessoes.setText("Editar");
        lblEditarSessoes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblEditarSessoes.setOpaque(true);
        lblEditarSessoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarSessoesMouseClicked(evt);
            }
        });

        btnExcluir.setFont(ClsEstilo.botaoFonte);
        btnExcluir.setForeground(ClsEstilo.botaoCor);
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        txtDesc.setEditable(false);
        txtDesc.setFont(ClsEstilo.textoInputFonte);
        txtDesc.setForeground(ClsEstilo.textoInputCor);
        txtDesc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescFocusGained(evt);
            }
        });
        scrollDesc2.setViewportView(txtDesc);

        lblEditarDesc.setBackground(ClsEstilo.formbg);
        lblEditarDesc.setFont(ClsEstilo.linkFonte);
        lblEditarDesc.setForeground(ClsEstilo.linkCor);
        lblEditarDesc.setText("Editar");
        lblEditarDesc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblEditarDesc.setOpaque(true);
        lblEditarDesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarDescMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(spnSessoes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblEditarSessoes))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdoCartao)
                                                .addGap(22, 22, 22)
                                                .addComponent(rdoDinheiro)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblEditarPag))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblValSessao)))
                                        .addGap(0, 36, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ftxtValor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblEditarValor))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCpf)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(scrollDesc2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNome)
                                        .addGap(114, 114, 114))
                                    .addComponent(lblEditarDesc, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImprimir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvar)
                            .addComponent(btnImprimir)
                            .addComponent(btnExcluir)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCpf)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblNome))
                        .addGap(12, 12, 12)
                        .addComponent(lblEditarDesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollDesc2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoCartao)
                            .addComponent(rdoDinheiro)
                            .addComponent(lblEditarPag))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ftxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(lblEditarValor))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(spnSessoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEditarSessoes))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblValSessao))))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        lblCpf.setText(Cpf.imprimeCpf(cliente.getCpf()));
        lblNome.setText(cliente.getNome());
        ftxtValor.setText(Moeda.padraoVirgula(orcamento.getValor()));
        spnSessoes.setValue(orcamento.getSessoes());
        lblValSessao.setText(Moeda.calculaSessao(orcamento.getValor(), orcamento.getSessoes()));
        
        if (orcamento.getDescricao() == null) {
        txtDesc.setText("Sem descrição.");
        } else {
        txtDesc.setText(orcamento.getDescricao());
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed

    }//GEN-LAST:event_formInternalFrameClosed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        boolean certo = true;

        if (rdoCartao.isEnabled()) {
            try {
                editPagamento();
            } catch (SQLException ex) {
                certo = false;
                Logger.getLogger(IfrmEditOrcamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ftxtValor.isEnabled()) {
            try {
                editValor();
            } catch (ValorInvalidoMoedaException | SQLException ex) {
                certo = false;
                Logger.getLogger(IfrmEditOrcamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (spnSessoes.isEnabled()) {
            try {
                editSessoes();
            } catch (SQLException ex) {
                certo = false;
                Logger.getLogger(IfrmEditOrcamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(txtDesc.isEditable()){
            try {
                editDesc();
            } catch (SQLException ex) {
                certo = false;
                Logger.getLogger(IfrmEditOrcamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (certo) {
            JOptionPane.showMessageDialog(this, "Modificações salvas com sucesso");
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível salvar todas as informações.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void spnSessoesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSessoesStateChanged
        try {
            int sessoes = (Integer) spnSessoes.getValue();
            lblValSessao.setText(Moeda.calculaSessao(ftxtValor.getText(), sessoes));
        } catch (ValorInvalidoMoedaException ex) {
            Logger.getLogger(IfrmCadOrcamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_spnSessoesStateChanged

    private void spnSessoesCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_spnSessoesCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spnSessoesCaretPositionChanged

    private void lblEditarPagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarPagMouseClicked
        rdoCartao.setEnabled(true);
        rdoDinheiro.setEnabled(true);
    }//GEN-LAST:event_lblEditarPagMouseClicked

    private void lblEditarValorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarValorMouseClicked
        ftxtValor.setEnabled(true);
    }//GEN-LAST:event_lblEditarValorMouseClicked

    private void lblEditarSessoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarSessoesMouseClicked
        spnSessoes.setEnabled(true);
    }//GEN-LAST:event_lblEditarSessoesMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int j = JOptionPane.showConfirmDialog(this, "Ao excluir um orçamento, todas as suas sessões cadastradas também serão excluidas. Você realmente deseja excluir este orçamento?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (j == JOptionPane.YES_OPTION) {
            try {
                JDBCDelete.orcamento(orcamento);
                JOptionPane.showMessageDialog(this, "O orçamento foi excluído com sucesso.");
                this.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível excluir este orçamento.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtDescFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescFocusGained

    }//GEN-LAST:event_txtDescFocusGained

    private void lblEditarDescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarDescMouseClicked
        txtDesc.setEditable(true);
        txtDesc.selectAll();
        txtDesc.getCaret().setVisible(true);
    }//GEN-LAST:event_lblEditarDescMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
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
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblEditarDesc;
    private javax.swing.JLabel lblEditarPag;
    private javax.swing.JLabel lblEditarSessoes;
    private javax.swing.JLabel lblEditarValor;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblValSessao;
    private javax.swing.JRadioButton rdoCartao;
    private javax.swing.JRadioButton rdoDinheiro;
    private javax.swing.JScrollPane scrollDesc2;
    private javax.swing.JSpinner spnSessoes;
    private javax.swing.JTextPane txtDesc;
    // End of variables declaration//GEN-END:variables
}
