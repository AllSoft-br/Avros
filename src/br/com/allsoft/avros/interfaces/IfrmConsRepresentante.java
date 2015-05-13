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

import br.com.allsoft.avros.dao.RepresentanteDAO;
import br.com.allsoft.avros.factory.JDBCConsulta;
import br.com.allsoft.avros.formulas.Consulta;
import br.com.allsoft.avros.formulas.Datas;
import br.com.allsoft.avros.formulas.Cpf;
import java.awt.Container;
import java.awt.Dimension;
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
public class IfrmConsRepresentante extends javax.swing.JInternalFrame {

    //Variáveis
    DefaultTableModel tblRepresentante = new ClsTableModel();
    RepresentanteDAO representante;
    Dimension tabela, scroll, form;
    String cpf = "";
    String nome = "";

    //Métodos
    /**
     * Cria um evento na tabela que é disparado quando algum item é selecionado,
     * pegando os dados do usuário mostrado naquele item
     */
    private void criaEventoSelecao() {
        jtblRepresentante.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (jtblRepresentante.getSelectedRow() > -1) {
                    int linha = jtblRepresentante.getSelectedRow();

                    int id = (int) tblRepresentante.getValueAt(linha, 0);

                    try {
                        representante = JDBCConsulta.representanteId(id);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "O representante não pôde ser carregado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(IfrmConsRepresentante.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    btnAbrir.setEnabled(true);
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
    private List<RepresentanteDAO> excluiRepetidos(List<RepresentanteDAO> lista){
        int qtos = lista.size();
        
        for(int i = 0; i < qtos; i++){
            RepresentanteDAO ref = lista.get(i);
            
            for(int j = i + 1; j < qtos; j++){
                if(ref.getId() == lista.get(j).getId()){
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
     * @param representantes lista de usuários
     * @param qtde quantidade de usuários listados
     */
    private void preencheTabela(List<RepresentanteDAO> representantes) throws SQLException {
        representantes = excluiRepetidos(representantes);
        
        int qtde = representantes.size();
        
        this.setSize(form);
        jScrollPane1.setSize(scroll);
        jtblRepresentante.setSize(tabela);
        jScrollPane1.setVisible(true);
        jtblRepresentante.setVisible(true);
        tblRepresentante.setRowCount(0);

        //Preenche ela
        for (int i = 0; i < qtde; i++) {
            tblRepresentante.addRow(new String[1]);
            String data = Datas.sqlparaString(representantes.get(i).getNascimento());
            String cliCpf = Consulta.grifar(cpf, representantes.get(i).getCpf());
            String cliNome = Consulta.grifar(nome, representantes.get(i).getNome());

            tblRepresentante.setValueAt(representantes.get(i).getId(), i, 0);
            tblRepresentante.setValueAt(cliNome, i, 1);
            tblRepresentante.setValueAt(cliCpf, i, 2);
            tblRepresentante.setValueAt(data, i, 3);
            tblRepresentante.setValueAt(representantes.get(i).getTel(), i, 4);
        }
    }

    /**
     * Creates new form IfrmConsUsuario
     */
    public IfrmConsRepresentante() {
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

        bgpPesquisa = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        lblVerTodos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblRepresentante = new javax.swing.JTable();
        lblLogo = new javax.swing.JLabel();
        btnAbrir = new javax.swing.JButton();

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
        jLabel1.setText("Pesquisar representantes");

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

        txtNome.setFont(ClsEstilo.textoInputFonte);
        txtNome.setForeground(ClsEstilo.textoInputCor);

        jLabel2.setText("Por CPF");

        jLabel4.setText("Por nome");

        jtblRepresentante.setFont(ClsEstilo.labelFonte);
        jtblRepresentante.setModel(tblRepresentante);
        jScrollPane1.setViewportView(jtblRepresentante);

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/logopequeno.png"))); // NOI18N

        btnAbrir.setFont(ClsEstilo.botaoFonte);
        btnAbrir.setForeground(ClsEstilo.botaoCor);
        btnAbrir.setText("Abrir");
        btnAbrir.setEnabled(false);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVerTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCpf)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btnPesquisar)
                                .addGap(30, 30, 30)
                                .addComponent(btnAbrir)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPesquisar)
                            .addComponent(btnAbrir))
                        .addGap(26, 26, 26)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
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
        tabela = jtblRepresentante.getSize();
        scroll = jScrollPane1.getSize();

        String[] cab = {"ID", "Nome", "CPF", "Nascimento", "Tel."};
        tblRepresentante.setColumnIdentifiers(cab);

        jtblRepresentante.setVisible(false);
        jScrollPane1.setVisible(false);
        jtblRepresentante.setGridColor(ClsEstilo.tabelaGrid);
        jtblRepresentante.setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.getViewport().setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.setBorder(null);
        jtblRepresentante.setSelectionBackground(ClsEstilo.tabelaSelec);
        jtblRepresentante.setSelectionForeground(ClsEstilo.tabelaTextoSelec);
        jtblRepresentante.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        criaEventoSelecao();

        this.setSize(365, 310);
        jScrollPane1.setSize(1, 1);

    }//GEN-LAST:event_formInternalFrameOpened

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        List<RepresentanteDAO> representantes = new ArrayList<>();

        if (!txtNome.getText().isEmpty()) {
            nome = txtNome.getText();
            try {
                representantes = JDBCConsulta.representanteNome(nome);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao pesquisar representantes pelo nome.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmConsRepresentante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (!txtCpf.getText().isEmpty()) {
            cpf = txtCpf.getText();

            if (Cpf.isCpf(cpf)) {
                RepresentanteDAO representante = new RepresentanteDAO();
                try {
                    representante = JDBCConsulta.representanteCpf(cpf);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao pesquisar clientes pelo CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(IfrmConsRepresentante.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (!(representante.getCpf() == null)) {
                    representantes.add(representante);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite um CPF válido.");
                txtCpf.selectAll();
                return;
            }
        }

        if (representantes.size() > 0) {
            try {
                preencheTabela(representantes);
            } catch (SQLException ex) {
                Logger.getLogger(IfrmConsRepresentante.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sua pesquisa não obteve resultados.");
        }

    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        FrmPrincipal.addFrame(new IfrmEditRepres(representante));
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void lblVerTodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVerTodosMouseClicked
        List<RepresentanteDAO> representantes = new ArrayList<>();
        try {
            representantes = JDBCConsulta.representanteTodos();
            preencheTabela(representantes);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro durante a exibição dos representantes pesquisados.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmConsRepresentante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblVerTodosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpPesquisa;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblRepresentante;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblVerTodos;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
