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
import br.com.allsoft.avros.dao.Cliente;
import br.com.allsoft.avros.factory.JDBCConsulta;
import br.com.allsoft.avros.dao.Orcamento;
import br.com.allsoft.avros.formulas.Moeda;
import br.com.allsoft.avros.formulas.Cpf;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luana Nascimento
 */
public class IfrmConsOrcamento extends javax.swing.JInternalFrame {

    //Variáveis
    DefaultTableModel tblOrc = new ClsTableModel();
    private Orcamento orcamento = new Orcamento();
    private Cliente cliente = new Cliente();

    //Métodos
    /**
     * Cria um evento na tabela que é disparado quando algum item é selecionado,
     * pegando os dados do orçamento mostrado naquele item
     */
    private void criaEventoSelecao() {
        jtblOrc.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (jtblOrc.getSelectedRow() > -1) {
                    int linha = jtblOrc.getSelectedRow();
                    String svalor = (String) tblOrc.getValueAt(linha, 4);
                    double dvalor = 0;
                    try {
                        dvalor = Moeda.retornaDouble(svalor);
                    } catch (ValorInvalidoMoedaException ex) {
                        Logger.getLogger(IfrmConsOrcamento.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    orcamento.setId((int) tblOrc.getValueAt(linha, 0));
                    orcamento.setCriacao((Date) tblOrc.getValueAt(linha, 1));
                    orcamento.setSessoes((int) tblOrc.getValueAt(linha, 2));
                    orcamento.setValor(dvalor);
                    orcamento.setTipoPagamento((String) tblOrc.getValueAt(linha, 5));

                    btnAbrir.setEnabled(true);
                }
            }
        });
    }

    /**
     * Da corpo a tabela e a cria com os orçamentos listados
     *
     * @param orcamentos lista de ojava.sql.Date dataSql =
 orcamentoIdCli.get(i).getCriacao(); SimpleDateFormat format = new
 SimpleDateFormat("dd/MM/yyyy"); format.format(dataSql);rçamentos
     * @param qtde quantidade de orçamentos listados
     */
    private void preencheTabela(List<Orcamento> orcamentos, int qtde) {
        //Preenche ela
        for (int i = 0; i < qtde; i++) {
            tblOrc.addRow(new String[1]);
            int sessoes = orcamentos.get(i).getSessoes();
            String valorTot = String.valueOf(orcamentos.get(i).getValor());
            String valorSes = String.valueOf(orcamentos.get(i).getValor() / sessoes);
            java.sql.Date dataSql = orcamentos.get(i).getCriacao();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.format(dataSql);

            try {
                valorTot = Moeda.padraoBr(orcamentos.get(i).getValor());
                valorSes = Moeda.calculaSessao(valorTot, sessoes);
            } catch (ValorInvalidoMoedaException ex) {
                Logger.getLogger(IfrmConsOrcamento.class.getName()).log(Level.SEVERE, null, ex);
            }

            tblOrc.setValueAt(orcamentos.get(i).getId(), i, 0);
            tblOrc.setValueAt(dataSql, i, 1);
            tblOrc.setValueAt(sessoes, i, 2);
            tblOrc.setValueAt(valorSes, i, 3);
            tblOrc.setValueAt(valorTot, i, 4);
            tblOrc.setValueAt(orcamentos.get(i).getTipoPagamento(), i, 5);
        }
    }

    /**
     * Creates new form IfrmPreSessao
     */
    public IfrmConsOrcamento() {
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

        btgBuscar = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        txtOrcamento = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        rdoCpf = new javax.swing.JRadioButton();
        rdoOrca = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblOrc = new javax.swing.JTable();
        btnAbrir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/orcapesq.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
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
        jLabel1.setText("Buscar orçamento");

        btnBuscar.setFont(ClsEstilo.botaoFonte);
        btnBuscar.setForeground(ClsEstilo.botaoCor);
        btnBuscar.setText("Buscar");
        btnBuscar.setEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        btnBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarKeyPressed(evt);
            }
        });

        txtOrcamento.setFont(ClsEstilo.textoInputFonte);
        txtOrcamento.setForeground(ClsEstilo.textoInputCor);
        txtOrcamento.setEnabled(false);
        txtOrcamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOrcamentoKeyTyped(evt);
            }
        });

        txtCpf.setFont(ClsEstilo.textoInputFonte);
        txtCpf.setForeground(ClsEstilo.textoInputCor);
        txtCpf.setEnabled(false);
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCpfKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpfKeyTyped(evt);
            }
        });

        btgBuscar.add(rdoCpf);
        rdoCpf.setFont(ClsEstilo.labelFonte);
        rdoCpf.setForeground(ClsEstilo.labelCor);
        rdoCpf.setText("Busque pelo CPF do cliente:");
        rdoCpf.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoCpfStateChanged(evt);
            }
        });

        btgBuscar.add(rdoOrca);
        rdoOrca.setFont(ClsEstilo.labelFonte);
        rdoOrca.setForeground(ClsEstilo.labelCor);
        rdoOrca.setText("Busque pelo código do orçamento:");
        rdoOrca.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoOrcaStateChanged(evt);
            }
        });

        jtblOrc.setFont(ClsEstilo.labelFonte);
        jtblOrc.setForeground(ClsEstilo.textoCor);
        jtblOrc.setModel(tblOrc);
        jScrollPane1.setViewportView(jtblOrc);

        btnAbrir.setText("Abrir");
        btnAbrir.setEnabled(false);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        btnAbrir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAbrirKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoOrca)
                    .addComponent(rdoCpf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtOrcamento)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(81, 81, 81)
                .addComponent(btnAbrir)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoCpf))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrcamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoOrca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnAbrir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //Pesquisa por CPF
        if (rdoCpf.isSelected()) {
            String cpf = txtCpf.getText();

            if (Cpf.isCpf(cpf)) {
                cliente.setCpf(cpf);
                try {
                    cliente = JDBCConsulta.clienteCpf(cpf);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar informações do representante.", "Erro", JOptionPane.ERROR_MESSAGE);
                    cliente = null;
                    Logger.getLogger(IfrmConsOrcamento.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (!(cliente == null)) {
                    List<Orcamento> orcamentos = new ArrayList<>();

                    try {
                        orcamentos = JDBCConsulta.orcamentoIdCli(cliente.getId());

                        int qtde = orcamentos.size();

                        if (qtde > 0) {
                            //Abre a Tabela
                            jScrollPane1.setVisible(true);
                            jScrollPane1.setSize(472, 327);
                            jtblOrc.setVisible(true);
                            jtblOrc.setSize(472, 327);
                            this.setSize(508, 536);

                            criaEventoSelecao();
                            preencheTabela(orcamentos, qtde);
                            cliente = JDBCConsulta.clienteCpf(cpf);

                        } else {
                            if (cliente.getCpf() == null) {
                                JOptionPane.showMessageDialog(this, "O cliente não está cadastrado no sistema.");
                            } else {
                                JOptionPane.showMessageDialog(this, cliente.getNome() + " não possui orçamentos salvos no sistema.");
                            }
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar orçamento.", "Erro", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(IfrmConsOrcamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (rdoOrca.isSelected()) {
            int codigo = Integer.valueOf(txtOrcamento.getText());

            try {
                orcamento = JDBCConsulta.orcamento(codigo);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar o orçamento.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmConsOrcamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                cliente = JDBCConsulta.clienteId(orcamento.getIdCliente());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar o cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmConsOrcamento.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!(orcamento == null) && !(cliente == null)) {
                btnAbrir.setEnabled(true);
            }
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtCpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyTyped
        btnBuscar.setEnabled(true);
    }//GEN-LAST:event_txtCpfKeyTyped

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        this.setSize(508, 205);

        String[] cab = {"Código", "Data", "Sessões", "Valor", "Total", "Pagamento"};
        tblOrc.setColumnIdentifiers(cab);

        jtblOrc.setVisible(false);
        jScrollPane1.setVisible(false);
        jtblOrc.setGridColor(ClsEstilo.tabelaGrid);
        jtblOrc.setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.getViewport().setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.setBorder(null);
        jtblOrc.setSelectionBackground(ClsEstilo.tabelaSelec);
        jtblOrc.setSelectionForeground(ClsEstilo.tabelaTextoSelec);
        jtblOrc.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtCpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyPressed

    }//GEN-LAST:event_txtCpfKeyPressed

    private void rdoCpfStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoCpfStateChanged
        if (rdoCpf.isSelected()) {
            txtCpf.setEnabled(true);
            txtOrcamento.setEnabled(false);
        };
    }//GEN-LAST:event_rdoCpfStateChanged

    private void rdoOrcaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoOrcaStateChanged
        if (rdoOrca.isSelected()) {
            txtCpf.setEnabled(false);
            txtOrcamento.setEnabled(true);
        };
    }//GEN-LAST:event_rdoOrcaStateChanged

    private void txtOrcamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrcamentoKeyTyped
        btnBuscar.setEnabled(true);
    }//GEN-LAST:event_txtOrcamentoKeyTyped

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        FrmPrincipal.addFrame(new IfrmEditOrcamento(orcamento, cliente));
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        FrmPrincipal.bPesqOrcamento = false;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnBuscar.doClick();
        }
    }//GEN-LAST:event_btnBuscarKeyPressed

    private void btnAbrirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAbrirKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnAbrir.doClick();
        }
    }//GEN-LAST:event_btnAbrirKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgBuscar;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblOrc;
    private javax.swing.JRadioButton rdoCpf;
    private javax.swing.JRadioButton rdoOrca;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtOrcamento;
    // End of variables declaration//GEN-END:variables
}
