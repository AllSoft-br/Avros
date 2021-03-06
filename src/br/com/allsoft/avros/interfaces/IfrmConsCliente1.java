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
import br.com.allsoft.avros.formulas.Consulta;
import br.com.allsoft.avros.formulas.Cpf;
import br.com.allsoft.avros.formulas.Datas;
import br.com.allsoft.avros.modelo.Cliente;
import br.com.allsoft.avros.modelo.Representante;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
 * @author Luana
 */
public class IfrmConsCliente1 extends javax.swing.JInternalFrame {

    //Variáveis
    DefaultTableModel tblCliente = new ClsTableModel();
    Cliente cliente = new Cliente();
    Representante representante;
    Dimension tabela, scroll, form;
    String cpf = "";
    String nome = "";
    int parentesco;

    //Métodos
    /**
     * Cria um evento na tabela que é disparado quando algum item é selecionado,
     * pegando os dados do usuário mostrado naquele item
     */
    private void criaEventoSelecao() {
        jtblCliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (jtblCliente.getSelectedRow() > -1) {
                    int linha = jtblCliente.getSelectedRow();

                    int id = (int) tblCliente.getValueAt(linha, 0);

                    try {
                        cliente = ClienteDAO.cclienteId(id);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "O cliente não pôde ser carregado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(IfrmConsCliente1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (cboParentesco.getSelectedIndex() > 0) {
                        btnAbrir.setEnabled(true);
                    }
                }
            }
        });
    }

    /**
     * Exclui dados repetidos da lista
     *
     * @param lista lista a verificar
     * @return lista sem dados repetidos
     */
    private List<Cliente> excluiRepetidos(List<Cliente> lista) {
        int qtos = lista.size();

        for (int i = 0; i < qtos; i++) {
            Cliente ref = lista.get(i);

            for (int j = i + 1; j < qtos; j++) {
                if (ref.getId() == lista.get(j).getId()) {
                    lista.remove(j);
                    qtos = lista.size();
                }
            }
        }

        return lista;
    }

    /**
     * Da corpo a tabela e a cria com os usuários listados
     *
     * @param clientes lista de usuários
     * @param qtde quantidade de usuários listados
     */
    private void preencheTabela(List<Cliente> clientes) throws SQLException {
        clientes = excluiRepetidos(clientes);

        int qtde = clientes.size();

        this.setSize(form);
        jScrollPane1.setSize(scroll);
        jtblCliente.setSize(tabela);
        jScrollPane1.setVisible(true);
        jtblCliente.setVisible(true);
        tblCliente.setRowCount(0);

        //Preenche ela
        for (int i = 0; i < qtde; i++) {
            tblCliente.addRow(new String[1]);
            String data = Datas.sqlparaString(clientes.get(i).getNascimento());
            String cliCpf = Consulta.grifar(cpf, clientes.get(i).getCpf());
            String cliNome = Consulta.grifar(nome, clientes.get(i).getNome());

            String tipo = "-";
            if (clientes.get(i).idade() < 18) {
                tipo = "Menor";
            }

            tblCliente.setValueAt(clientes.get(i).getId(), i, 0);
            tblCliente.setValueAt(cliNome, i, 1);
            tblCliente.setValueAt(cliCpf, i, 2);
            tblCliente.setValueAt(data, i, 3);
            tblCliente.setValueAt(clientes.get(i).getTel(), i, 4);
            tblCliente.setValueAt(tipo, i, 5);
        }
    }

    /**
     * Creates new form IfrmConsUsuario
     *
     * @param rep representante do cliente
     */
    public IfrmConsCliente1(Representante rep) {
        initComponents();

        representante = rep;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgpPesquisa = new javax.swing.ButtonGroup();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblVerTodos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblCliente = new javax.swing.JTable();
        lblLogo = new javax.swing.JLabel();
        btnAbrir = new javax.swing.JButton();
        lblQual = new javax.swing.JLabel();
        txtQual = new javax.swing.JTextField();
        cboParentesco = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();

        jLabel7.setFont(ClsEstilo.labelFonte);
        jLabel7.setForeground(ClsEstilo.labelCor);
        jLabel7.setText("Parentesco");

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/Users.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
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
        jLabel1.setText("Adicionar dependente cadastrado");

        lblVerTodos.setBackground(ClsEstilo.formbg);
        lblVerTodos.setFont(ClsEstilo.linkFonte);
        lblVerTodos.setForeground(ClsEstilo.linkCor);
        lblVerTodos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVerTodos.setText("Clique aqui e veja todos os clientes cadastrados");
        lblVerTodos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVerTodos.setOpaque(true);
        lblVerTodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVerTodosMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ou");

        txtCpf.setFont(ClsEstilo.textoInputFonte);
        txtCpf.setForeground(ClsEstilo.textoInputCor);

        btnPesquisar.setFont(ClsEstilo.botaoFonte);
        btnPesquisar.setForeground(ClsEstilo.botaoCor);
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        btnPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPesquisarKeyPressed(evt);
            }
        });

        txtNome.setFont(ClsEstilo.textoInputFonte);
        txtNome.setForeground(ClsEstilo.textoInputCor);

        jLabel2.setText("Por CPF");

        jLabel4.setText("Por nome");

        jtblCliente.setFont(ClsEstilo.labelFonte);
        jtblCliente.setModel(tblCliente);
        jScrollPane1.setViewportView(jtblCliente);

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/logopequeno.png"))); // NOI18N

        btnAbrir.setFont(ClsEstilo.botaoFonte);
        btnAbrir.setForeground(ClsEstilo.botaoCor);
        btnAbrir.setText("Selecionar");
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

        lblQual.setFont(ClsEstilo.labelFonte);
        lblQual.setForeground(ClsEstilo.labelCor);
        lblQual.setText("Qual?");

        txtQual.setFont(ClsEstilo.textoInputFonte);
        txtQual.setForeground(ClsEstilo.textoInputCor);

        cboParentesco.setFont(ClsEstilo.labelFonte);
        cboParentesco.setForeground(ClsEstilo.labelCor);
        cboParentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "Avô", "Avó", "Tia", "Tio", "Mãe", "Pai", "Madrasta", "Padrasto", "Outro" }));
        cboParentesco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboParentescoItemStateChanged(evt);
            }
        });
        cboParentesco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboParentescoActionPerformed(evt);
            }
        });

        jLabel8.setFont(ClsEstilo.labelFonte);
        jLabel8.setForeground(ClsEstilo.labelCor);
        jLabel8.setText("Parentesco");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVerTodos, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCpf)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnPesquisar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAbrir))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblQual)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtQual))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboParentesco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbrir)
                            .addComponent(btnPesquisar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cboParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQual)
                            .addComponent(txtQual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVerTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        form = this.getSize();
        tabela = jtblCliente.getSize();
        scroll = jScrollPane1.getSize();

        String[] cab = {"ID", "Nome", "CPF", "Nascimento", "Tel.", "Obs."};
        tblCliente.setColumnIdentifiers(cab);

        jtblCliente.setVisible(false);
        jScrollPane1.setVisible(false);
        jtblCliente.setGridColor(ClsEstilo.tabelaGrid);
        jtblCliente.setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.getViewport().setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.setBorder(null);
        jtblCliente.setSelectionBackground(ClsEstilo.tabelaSelec);
        jtblCliente.setSelectionForeground(ClsEstilo.tabelaTextoSelec);
        jtblCliente.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lblQual.setVisible(false);
        txtQual.setVisible(false);

        criaEventoSelecao();

        this.setSize(365, 310);
        jScrollPane1.setSize(1, 1);

    }//GEN-LAST:event_formInternalFrameOpened

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        List<Cliente> clientes = new ArrayList<>();

        if (!txtNome.getText().isEmpty()) {
            nome = txtNome.getText();
            try {
                clientes = ClienteDAO.cclienteNome(nome);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao pesquisar clientes pelo nome.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmConsCliente1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (!txtCpf.getText().isEmpty()) {
            cpf = txtCpf.getText();

            if (Cpf.isCpf(cpf)) {
                Cliente cliente = new Cliente();
                try {
                    cliente = ClienteDAO.cclienteCpf(cpf);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao pesquisar clientes pelo CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(IfrmConsCliente1.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (!(cliente.getCpf() == null)) {
                    clientes.add(cliente);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite um CPF válido.");
                txtCpf.selectAll();
                return;
            }
        }

        if (clientes.size() > 0) {
            try {
                preencheTabela(clientes);
            } catch (SQLException ex) {
                Logger.getLogger(IfrmConsCliente1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sua pesquisa não obteve resultados.");
        }

    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (lblQual.isVisible()) {
            try {
                parentesco = RepresentanteDAO.inserirParentesco(txtQual.getText());
            } catch (SQLException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível criar o novo grau de parentesco.");
                Logger.getLogger(IfrmConsCliente1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            parentesco = cboParentesco.getSelectedIndex();
        }

        if (parentesco > -1) {
            try {
                RepresentanteDAO.inserirRelCliRep(representante.getId(), cliente.getId(), parentesco);
                JOptionPane.showMessageDialog(this, "Novo dependente adicionado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível associar o dependente ao responsável desejado.");
                Logger.getLogger(IfrmConsCliente1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecione o grau de parentesco.");

        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void lblVerTodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVerTodosMouseClicked
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = ClienteDAO.cclienteTodos();
            preencheTabela(clientes);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a exibição dos clientes pesquisados.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmConsCliente1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblVerTodosMouseClicked

    private void cboParentescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboParentescoActionPerformed

    }//GEN-LAST:event_cboParentescoActionPerformed

    private void cboParentescoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboParentescoItemStateChanged
        //se o ultimo item (Outro) estiver selecionado, os campos Qual aparecem
        if (cboParentesco.getSelectedIndex() == (cboParentesco.getItemCount() - 1)) {
            lblQual.setVisible(true);
            txtQual.setVisible(true);
        } else {
            lblQual.setVisible(false);
            txtQual.setVisible(false);
        }
        
        if(cboParentesco.getSelectedIndex() > 0){
            if (jtblCliente.getSelectedRow() > -1) {
                btnAbrir.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cboParentescoItemStateChanged

    private void btnPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPesquisarKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnPesquisar.doClick();
        }
    }//GEN-LAST:event_btnPesquisarKeyPressed

    private void btnAbrirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAbrirKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnAbrir.doClick();
        }
    }//GEN-LAST:event_btnAbrirKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpPesquisa;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox cboParentesco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblCliente;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblQual;
    private javax.swing.JLabel lblVerTodos;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtQual;
    // End of variables declaration//GEN-END:variables
}
