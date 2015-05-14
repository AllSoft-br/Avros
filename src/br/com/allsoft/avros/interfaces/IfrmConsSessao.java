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
import br.com.allsoft.avros.dao.SessaoDAO;
import br.com.allsoft.avros.factory.JDBCConsulta;
import br.com.allsoft.avros.formulas.Datas;
import br.com.allsoft.avros.formulas.Moeda;
import br.com.allsoft.avros.formulas.Cpf;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
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
public class IfrmConsSessao extends javax.swing.JInternalFrame {

    //Variáveis
    DefaultTableModel tblSes = new ClsTableModel();
    private OrcamentoDAO orcamento = new OrcamentoDAO();
    private ClienteDAO cliente = new ClienteDAO();
    private SessaoDAO sessao = new SessaoDAO();
    Dimension form, tabela, scroll;

    //Métodos
    /**
     * Cria um evento na tabela que é disparado quando algum item é selecionado,
     * pegando os dados do orçamento mostrado naquele item
     */
    private void criaEventoSelecao() {
        jtblSes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (jtblSes.getSelectedRow() > -1) {
                    int linha = jtblSes.getSelectedRow();
                    
                    sessao.setId((int) tblSes.getValueAt(linha, 0));
                    sessao.setIdOrcamento((int) tblSes.getValueAt(linha, 1));
                    sessao.setCpf((String) tblSes.getValueAt(linha, 4));
                    
                    try {
                        orcamento = JDBCConsulta.orcamento(sessao.getIdOrcamento());
                        cliente = JDBCConsulta.clienteCpf(sessao.getCpf());
                        sessao = JDBCConsulta.sessaoId(sessao.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(IfrmConsSessao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!(cliente.getNome() == null)) {
                        if (!(orcamento.getTipoPagamento() == null)) {
                            btnAbrir.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocorreu ao carregar as informações do orçamento.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocorreu ao carregar as informações do cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    /**
     * Da corpo a tabela e a cria com os orçamentos listados
     *
     * @param sessoes lista de ojava.sql.Date dataSql =
     * sessoes.get(i).getCriacao(); SimpleDateFormat format = new
     * SimpleDateFormat("dd/MM/yyyy"); format.format(dataSql);rçamentos
     * @param qtde quantidade de sessões listados
     */
    private void preencheTabela(List<SessaoDAO> sessoes, int qtde) {

        this.setSize(form);
        jtblSes.setSize(tabela);
        jScrollPane1.setSize(scroll);
        jScrollPane1.setVisible(true);
        jtblSes.setVisible(true);
        tblSes.setRowCount(0);

        //Preenche ela
        for (int i = 0; i < qtde; i++) {
            tblSes.addRow(new String[1]);

            tblSes.setValueAt(sessoes.get(i).getId(), i, 0);
            tblSes.setValueAt(sessoes.get(i).getIdOrcamento(), i, 1);
            tblSes.setValueAt(Datas.sqlparaString(sessoes.get(i).getData()), i, 2);
            tblSes.setValueAt(sessoes.get(i).getCliente(), i, 3);
            tblSes.setValueAt(sessoes.get(i).getCpf(), i, 4);
            tblSes.setValueAt(Moeda.padraoBr(sessoes.get(i).getValor()), i, 5);
            tblSes.setValueAt(sessoes.get(i).getPagamento(), i, 6);
            tblSes.setValueAt(Moeda.padraoBr(sessoes.get(i).getDesconto()), i, 7);
        }
    }

    /**
     * Creates new form IfrmPreSessao
     */
    public IfrmConsSessao() {
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
        jtblSes = new javax.swing.JTable();
        btnAbrir = new javax.swing.JButton();
        rdoSessao = new javax.swing.JRadioButton();
        txtSessao = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
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
        jLabel1.setText("Buscar sessão");

        btnBuscar.setFont(ClsEstilo.botaoFonte);
        btnBuscar.setForeground(ClsEstilo.botaoCor);
        btnBuscar.setText("Buscar");
        btnBuscar.setEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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
        rdoCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rdoCpfKeyPressed(evt);
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
        rdoOrca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rdoOrcaKeyPressed(evt);
            }
        });

        jtblSes.setFont(ClsEstilo.labelFonte);
        jtblSes.setForeground(ClsEstilo.textoCor);
        jtblSes.setModel(tblSes);
        jScrollPane1.setViewportView(jtblSes);

        btnAbrir.setText("Abrir");
        btnAbrir.setEnabled(false);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btgBuscar.add(rdoSessao);
        rdoSessao.setFont(ClsEstilo.labelFonte);
        rdoSessao.setForeground(ClsEstilo.labelCor);
        rdoSessao.setText("Busque pelo código da sessão:");
        rdoSessao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoSessaoStateChanged(evt);
            }
        });
        rdoSessao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rdoSessaoKeyPressed(evt);
            }
        });

        txtSessao.setFont(ClsEstilo.textoInputFonte);
        txtSessao.setForeground(ClsEstilo.textoInputCor);
        txtSessao.setEnabled(false);
        txtSessao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSessaoKeyTyped(evt);
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
                    .addComponent(rdoCpf)
                    .addComponent(rdoSessao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSessao, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtOrcamento)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrcamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoOrca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSessao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoSessao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
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
                    JOptionPane.showMessageDialog(this, "Ocorreu ao carregar as informações do cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                    cliente = null;
                    Logger.getLogger(IfrmConsSessao.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (!(cliente.getCpf() == null)) {
                    List<SessaoDAO> sessoes = new ArrayList<>();

                    try {
                        sessoes = JDBCConsulta.sessaoIdCli(cliente.getId());
                        int qtde = sessoes.size();

                        for (int i = 0; i < qtde; i++) {
                            sessoes.get(i).setCliente(cliente.getNome());
                            sessoes.get(i).setCpf(cliente.getCpf());
                        }

                        if (qtde > 0) {
                            //Abre a Tabela
                            jScrollPane1.setVisible(true);
                            jScrollPane1.setSize(472, 327);
                            jtblSes.setVisible(true);
                            jtblSes.setSize(472, 327);
                            this.setSize(508, 536);

                            criaEventoSelecao();
                            preencheTabela(sessoes, qtde);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Ocorreu um erro ao buscar orçamento.", "Erro", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(IfrmConsSessao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "O cliente não está cadastrado no sistema.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Digite um CPF válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else if (rdoOrca.isSelected()) {
            int codigo = Integer.valueOf(txtOrcamento.getText());
            List<SessaoDAO> sessoes = new ArrayList<>();

            try {
                orcamento = JDBCConsulta.orcamento(codigo);
                sessoes = JDBCConsulta.sessaoIdOrc(codigo);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar o orçamento.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmConsSessao.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                cliente = JDBCConsulta.clienteId(orcamento.getIdCliente());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar o cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmConsSessao.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!(orcamento.getTipoPagamento() == null) && !(cliente.getCpf() == null)) {
                int qtde = sessoes.size();
                System.out.println(qtde);
                for (int i = 0; i < qtde; i++) {
                    sessoes.get(i).setCliente(cliente.getNome());
                    sessoes.get(i).setCpf(cliente.getCpf());
                }

                criaEventoSelecao();
                preencheTabela(sessoes, qtde);
            } else {
                JOptionPane.showMessageDialog(this, "Este orçamento não existe.");
            }
        } else if (rdoSessao.isSelected()) {
            int id = Integer.valueOf(txtSessao.getText());

            try {
                sessao = JDBCConsulta.sessaoId(id);
                orcamento.setId(sessao.getIdOrcamento());
                orcamento = JDBCConsulta.orcamento(orcamento.getId());
                cliente = JDBCConsulta.clienteId(orcamento.getIdCliente());

                btnAbrir.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Sessão encontrada!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar a sessão.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmConsSessao.class.getName()).log(Level.SEVERE, null, ex);
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

        form = this.getSize();
        tabela = jtblSes.getSize();
        scroll = jScrollPane1.getSize();

        this.setSize(508, 305);

        String[] cab = {"Código", "Orçamento", "Data", "Cliente", "CPF", "Valor", "Pagamento", "Desconto"};
        tblSes.setColumnIdentifiers(cab);

        jtblSes.setVisible(false);
        jScrollPane1.setVisible(false);
        jtblSes.setGridColor(ClsEstilo.tabelaGrid);
        jtblSes.setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.getViewport().setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.setBorder(null);
        jtblSes.setSelectionBackground(ClsEstilo.tabelaSelec);
        jtblSes.setSelectionForeground(ClsEstilo.tabelaTextoSelec);
        jtblSes.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtCpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyPressed

    }//GEN-LAST:event_txtCpfKeyPressed

    private void rdoCpfStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoCpfStateChanged
        if (rdoCpf.isSelected()) {
            txtCpf.setEnabled(true);
        } else {
            txtCpf.setEnabled(false);
        }
    }//GEN-LAST:event_rdoCpfStateChanged

    private void rdoOrcaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoOrcaStateChanged
        if (rdoOrca.isSelected()) {
            txtOrcamento.setEnabled(true);
        } else {
            txtOrcamento.setEnabled(false);
        }
    }//GEN-LAST:event_rdoOrcaStateChanged

    private void txtOrcamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrcamentoKeyTyped
        btnBuscar.setEnabled(true);
    }//GEN-LAST:event_txtOrcamentoKeyTyped

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        FrmPrincipal.addFrame(new IfrmEditSessao(orcamento, cliente, sessao));
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        FrmPrincipal.bPreAgendarSessao = false;
        FrmPrincipal.bPagarSessao = false;
    }//GEN-LAST:event_formInternalFrameClosing

    private void rdoSessaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoSessaoStateChanged
        if (rdoSessao.isSelected()) {
            txtSessao.setEnabled(true);
        } else {
            txtSessao.setEnabled(false);
        }
    }//GEN-LAST:event_rdoSessaoStateChanged

    private void txtSessaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSessaoKeyTyped
        btnBuscar.setEnabled(true);
    }//GEN-LAST:event_txtSessaoKeyTyped

    private void rdoCpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdoCpfKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            rdoCpf.setSelected(rdoCpf.isSelected());
        }
    }//GEN-LAST:event_rdoCpfKeyPressed

    private void rdoOrcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdoOrcaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            rdoOrca.setSelected(rdoOrca.isSelected());
        }
    }//GEN-LAST:event_rdoOrcaKeyPressed

    private void rdoSessaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdoSessaoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            rdoSessao.setSelected(rdoSessao.isSelected());
        }
    }//GEN-LAST:event_rdoSessaoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgBuscar;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblSes;
    private javax.swing.JRadioButton rdoCpf;
    private javax.swing.JRadioButton rdoOrca;
    private javax.swing.JRadioButton rdoSessao;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtOrcamento;
    private javax.swing.JTextField txtSessao;
    // End of variables declaration//GEN-END:variables
}
