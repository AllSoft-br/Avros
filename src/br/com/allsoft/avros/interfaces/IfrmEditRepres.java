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

import br.com.allsoft.avros.dao.Cliente;
import br.com.allsoft.avros.dao.Representante;
import br.com.allsoft.avros.factory.JDBCConsulta;
import br.com.allsoft.avros.factory.JDBCDelete;
import br.com.allsoft.avros.factory.JDBCUpdate;
import br.com.allsoft.avros.formulas.Datas;
import br.com.allsoft.avros.formulas.Cpf;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Luana
 */
public class IfrmEditRepres extends javax.swing.JInternalFrame {

    //Variáveis
    Representante representante = new Representante();
    Cliente cliente = new Cliente();
    DefaultTableModel tblDependentes = new ClsTableModel();

    Dimension frame;
    Dimension tabela;
    Dimension scroll;
    boolean sexo = false;
    int qto = 0;

    /**
     * Preenche os campos com as informações do representante
     */
    private void preencheCampos() {
        try {
            qto = JDBCConsulta.qtdeDependentes(representante.getId());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar a quantidade de dependentes.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmEditRepres.class.getName()).log(Level.SEVERE, null, ex);
        }

        String qtde = "0";
        qtde = String.valueOf(qto);

        if (qto == 0) {
            lblResp.setText("Clique aqui para adicionar um dependente.");
        } else {
            lblResp.setText("Clique aqui para visualizar dependentes.");
        }

        lblAddDep.setVisible(false);
        lblSauda.setText(representante.getNome() + " tem " + qtde + " dependente(s).");
        txtNome.setText(representante.getNome());
        txtCpf.setText(Cpf.imprimeCpf(representante.getCpf()));
        ftxtNasc.setText(Datas.sqlparaString(representante.getNascimento()));
        txtTel.setText(representante.getTel());

        if (representante.isFeminino()) {
            rdoF.setSelected(true);
        } else {
            rdoM.setSelected(true);
        }
    }

    private void escondeTabela() {
        frame = this.getSize(); //505, 296
        tabela = jtblDependentes.getSize();// 469, 3
        scroll = jScrollPane2.getSize();

        this.setSize(600, 340);

        String[] cab = {"ID", "CPF", "Nome", "Nascimento", "Tel."};
        tblDependentes.setColumnIdentifiers(cab);

        jtblDependentes.setGridColor(ClsEstilo.tabelaGrid);
        jtblDependentes.setBackground(ClsEstilo.tabelaBg);
        jScrollPane2.getViewport().setBackground(ClsEstilo.tabelaBg);
        jScrollPane2.setBorder(null);
        jtblDependentes.setSelectionBackground(ClsEstilo.tabelaSelec);
        jtblDependentes.setSelectionForeground(ClsEstilo.tabelaTextoSelec);
        jtblDependentes.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtblDependentes.setVisible(false);
        jScrollPane2.setVisible(false);
    }

    private void preencheTabela(List<Cliente> clientes) {
        this.setSize(frame);
        jtblDependentes.setSize(tabela);
        jScrollPane2.setSize(scroll);
        jScrollPane2.setVisible(true);
        jtblDependentes.setVisible(true);
        tblDependentes.setRowCount(0);
        lblAddDep.setVisible(true);

        int qtde = clientes.size();

        //Preenche ela
        for (int i = 0; i < qtde; i++) {
            tblDependentes.addRow(new String[1]);

            tblDependentes.setValueAt(clientes.get(i).getId(), i, 0);
            tblDependentes.setValueAt(clientes.get(i).getCpf(), i, 1);
            tblDependentes.setValueAt(clientes.get(i).getNome(), i, 2);
            tblDependentes.setValueAt(Datas.sqlparaString(clientes.get(i).getNascimento()), i, 3);
            tblDependentes.setValueAt(clientes.get(i).getTel(), i, 4);
        }
    }

    //Métodos
    /**
     * Cria um evento na tabela que é disparado quando algum item é selecionado,
     * pegando os dados do usuário mostrado naquele item
     */
    private void criaEventoSelecao() {
        jtblDependentes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (jtblDependentes.getSelectedRow() > -1) {
                    int linha = jtblDependentes.getSelectedRow();

                    int id = (int) tblDependentes.getValueAt(linha, 0);

                    try {
                        cliente = JDBCConsulta.clienteId(id);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "O cliente não pôde ser carregado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(IfrmConsCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
    }

    /**
     * Creates new form ifrmConta
     *
     * @param representante representante a ser visualizado
     */
    public IfrmEditRepres(Representante representante) {
        initComponents();

        this.representante = representante;
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
        menu = new javax.swing.JPopupMenu();
        ver = new javax.swing.JMenuItem();
        cad = new javax.swing.JMenuItem();
        pesquisar = new javax.swing.JMenuItem();
        remover = new javax.swing.JMenuItem();
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
        lblResp = new javax.swing.JLabel();
        ftxtNasc = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblDependentes = new javax.swing.JTable();
        lblAddDep = new javax.swing.JLabel();

        jLabel8.setText("jLabel8");

        ver.setText("Ver dependente selecionado");
        ver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verMouseClicked(evt);
            }
        });
        ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verActionPerformed(evt);
            }
        });
        menu.add(ver);
        if(!(jtblDependentes.getSelectedRow() > -1)){
            ver.setEnabled(false);
        }

        cad.setText("Cadastrar novo dependente");
        cad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadMouseClicked(evt);
            }
        });
        cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadActionPerformed(evt);
            }
        });
        menu.add(cad);

        pesquisar.setText("Procurar novo dependente");
        pesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pesquisarMouseClicked(evt);
            }
        });
        pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarActionPerformed(evt);
            }
        });
        menu.add(pesquisar);

        remover.setText("Remover dependente selecionado");
        remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerActionPerformed(evt);
            }
        });
        menu.add(remover);
        if(!(jtblDependentes.getSelectedRow() > -1)){
            remover.setEnabled(false);
        }

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
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jLabel1.setFont(ClsEstilo.tituloFonte);
        jLabel1.setForeground(ClsEstilo.tituloCor);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editar representante");

        lblSauda.setFont(ClsEstilo.labelDestaqueFonte);
        lblSauda.setForeground(ClsEstilo.labelDestaqueCor);
        lblSauda.setText("Fulano da silva tem 0 dependentes.");

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

        txtCpf.setEditable(false);
        txtCpf.setFont(ClsEstilo.textoInputFonte);
        txtCpf.setForeground(ClsEstilo.textoInputCor);

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
        btnSenha1.setText("Excluir");
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

        lblResp.setBackground(ClsEstilo.formbg);
        lblResp.setFont(ClsEstilo.linkFonte);
        lblResp.setForeground(ClsEstilo.linkCor);
        lblResp.setText("Clique aqui para visualizar os dependentes.");
        lblResp.setToolTipText("");
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

        jtblDependentes.setFont(ClsEstilo.labelFonte);
        jtblDependentes.setForeground(ClsEstilo.textoCor);
        jtblDependentes.setModel(tblDependentes);
        jtblDependentes.setOpaque(false);
        jtblDependentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtblDependentesMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jtblDependentes);

        lblAddDep.setBackground(ClsEstilo.formbg);
        lblAddDep.setFont(ClsEstilo.linkFonte);
        lblAddDep.setForeground(ClsEstilo.linkCor);
        lblAddDep.setText("Adicionar dependentes");
        lblAddDep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAddDep.setOpaque(true);
        lblAddDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddDepMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSauda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(98, 98, 98))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdoF)
                                                .addGap(34, 34, 34)
                                                .addComponent(rdoM))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtTel)
                                                    .addComponent(ftxtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblEditarNasc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblEditarNome1)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtCpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblEditarNome))))
                                    .addComponent(lblResp)
                                    .addComponent(btnSenha1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(btnSalvar)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblAddDep)))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSauda)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblLogo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAddDep))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblResp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalvar)
                            .addComponent(btnSenha1))
                        .addGap(24, 24, 24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        escondeTabela();
        preencheCampos();

    }//GEN-LAST:event_formInternalFrameOpened

    private void lblEditarNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarNomeMouseClicked
        txtNome.setEnabled(true);
    }//GEN-LAST:event_lblEditarNomeMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        int id = representante.getId();
        Cliente editado = new Cliente();

        if (txtNome.isEnabled()) {
            editado.setNome(txtNome.getText());

            try {
                JDBCUpdate.clienteNome(editado.getNome(), id);
            } catch (SQLException ex) {
                Logger.getLogger(IfrmEditRepres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ftxtNasc.isEnabled()) {
            editado.setNascimento(ftxtNasc.getText());
            try {
                JDBCUpdate.clienteNasc(editado.getNascimento(), id);
            } catch (SQLException ex) {
                Logger.getLogger(IfrmEditRepres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (txtTel.isEnabled()) {
            editado.setTel(txtTel.getText());
            try {
                JDBCUpdate.clienteTel(editado.getTel(), id);
            } catch (SQLException ex) {
                Logger.getLogger(IfrmEditRepres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (sexo) {
            boolean feminino = false;

            if (rdoF.isSelected()) {
                feminino = true;
            }

            try {
                JDBCUpdate.clienteSexo(feminino, id);
            } catch (SQLException ex) {
                Logger.getLogger(IfrmEditRepres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        if (qto == 0) {
            int j = JOptionPane.showConfirmDialog(this, "Este responsável não possui nenhum, deseja cadastrar um agora?");
            if (j == JOptionPane.YES_OPTION) {
                FrmPrincipal.addFrame(new IfrmCadClienteMenor(representante));
            }
        } else {
            try {
                List<Cliente> dependentes = JDBCConsulta.dependentes(representante.getId());
                criaEventoSelecao();
                preencheTabela(dependentes);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível carregar os dependentes.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmEditRepres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblRespMouseClicked

    private void jtblDependentesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblDependentesMouseReleased
        if (SwingUtilities.isRightMouseButton(evt)) {
            if (jtblDependentes.getSelectedRow() > -1) {
                ver.setEnabled(true);
                remover.setEnabled(true);
            } else {
                ver.setEnabled(false);
                remover.setEnabled(false);
            }
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtblDependentesMouseReleased

    private void verMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verMouseClicked

    }//GEN-LAST:event_verMouseClicked

    private void lblAddDepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddDepMouseClicked
        FrmPrincipal.addFrame(new IfrmCadClienteMenor(representante));
    }//GEN-LAST:event_lblAddDepMouseClicked

    private void cadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadMouseClicked

    }//GEN-LAST:event_cadMouseClicked

    private void pesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesquisarMouseClicked

    }//GEN-LAST:event_pesquisarMouseClicked

    private void verActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verActionPerformed
        if (cliente.getCpf() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um dependente para poder visualizá-lo.");
        } else {
            FrmPrincipal.addFrame(new IfrmEditCliente(cliente));
        }
    }//GEN-LAST:event_verActionPerformed

    private void cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadActionPerformed
        FrmPrincipal.addFrame(new IfrmCadClienteMenor(representante));
    }//GEN-LAST:event_cadActionPerformed

    private void pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarActionPerformed
        FrmPrincipal.addFrame(new IfrmConsCliente1(representante));
    }//GEN-LAST:event_pesquisarActionPerformed

    private void removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerActionPerformed
        int j = JOptionPane.showConfirmDialog(this, "Você realmente deseja remover este dependente? Ao fazer isso o dependente ficará sem responvável.");
        if (j == JOptionPane.YES_OPTION) {
            try {
                JDBCDelete.removeRel(representante.getId(), cliente.getId());
                int q = jtblDependentes.getSelectedRow();
                tblDependentes.removeRow(q);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível remover o dependente.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmEditRepres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_removerActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        preencheCampos();
        try {
            List<Cliente> dependentes = JDBCConsulta.dependentes(representante.getId());
            preencheTabela(dependentes);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar a lista de dependentes.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmEditRepres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpTipo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSenha1;
    private javax.swing.JMenuItem cad;
    private javax.swing.JFormattedTextField ftxtNasc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtblDependentes;
    private javax.swing.JLabel lblAddDep;
    private javax.swing.JLabel lblEditarNasc;
    private javax.swing.JLabel lblEditarNome;
    private javax.swing.JLabel lblEditarNome1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblResp;
    private javax.swing.JLabel lblSauda;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JMenuItem pesquisar;
    private javax.swing.JRadioButton rdoF;
    private javax.swing.JRadioButton rdoM;
    private javax.swing.JMenuItem remover;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTel;
    private javax.swing.JMenuItem ver;
    // End of variables declaration//GEN-END:variables
}
