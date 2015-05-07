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

import br.com.allsoft.avros.dao.RegistroDAO;
import br.com.allsoft.avros.dao.UsuarioDAO;
import br.com.allsoft.avros.event.ComboBoxItemListener;
import br.com.allsoft.avros.factory.JDBCConsulta;
import br.com.allsoft.avros.factory.JDBCViews;
import br.com.allsoft.avros.formulas.Datas;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Luana
 */
public class IfrmHistorico extends javax.swing.JInternalFrame {

    //Variáveis
    DefaultTableModel tblLogin = new DefaultTableModel();
    DefaultTableModel tblCad = new DefaultTableModel();
    DefaultTableModel tblEdit = new DefaultTableModel();
    DefaultTableModel tblDel = new DefaultTableModel();

    String[] cabLog = {"Cód.", "Usuário", "Descrição", "Horário"};
    String[] cabCad = {"Cód.", "Item", "Usuário", "Descrição", "ID do item"};
    String[] cabDel = {"Cód.", "Item", "Usuário", "Descrição", "ID do item"};
    String[] cabEdit = {"Cód.", "Item", "Usuário", "Descrição", "ID do item", "Antes", "Depois"};

    String nick = "todos";
    String periodo = "24 horas";

    //métodos
    private void preencheTabelaLog(List<RegistroDAO> registros) throws SQLException {
        tblLogin.setColumnIdentifiers(cabLog);
        jtblLogins.setModel(tblLogin);
        tblLogin.setRowCount(0);
        int q = registros.size();

        for (int i = 0; i < q; i++) {
            tblLogin.addRow(new String[1]);

            UsuarioDAO usuario = JDBCConsulta.usuarioId(registros.get(i).getIdLogin());
            String horario = Datas.timestrampParaString(registros.get(i).getData());

            tblLogin.setValueAt(registros.get(i).getId(), i, 0);
            tblLogin.setValueAt(usuario.getNome(), i, 1);
            tblLogin.setValueAt(registros.get(i).getDescricao(), i, 2);
            tblLogin.setValueAt(horario, i, 3);
        }
    }

    private void loginClick() {
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "cardLogin");

        pnlLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(1));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnlEdicoes.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnlExclusoes.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        try {
            List<UsuarioDAO> usuarios = JDBCConsulta.usuarioTodos();
            int s = usuarios.size();

            for (int i = 0; i < s; i++) {
                cbxLoginLog.addItem(usuarios.get(i).getNick());
            }

        } catch (SQLException ex) {
            Logger.getLogger(IfrmHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }

        jtblLogins.setGridColor(ClsEstilo.tabelaGrid);
        jtblLogins.setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.getViewport().setBackground(ClsEstilo.tabelaBg);
        jScrollPane1.setBorder(null);
        jtblLogins.setSelectionBackground(ClsEstilo.tabelaSelec);
        jtblLogins.setSelectionForeground(ClsEstilo.tabelaTextoSelec);
        jtblLogins.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        try {
            login24h();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar os registros.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cadClick() {
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "cardCad");

        pnlLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(1));
        pnlEdicoes.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnlExclusoes.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        try {
            List<UsuarioDAO> usuarios = JDBCConsulta.usuarioTodos();
            int s = usuarios.size();

            for (int i = 0; i < s; i++) {
                cbxLoginCad.addItem(usuarios.get(i).getNick());
            }

        } catch (SQLException ex) {
            Logger.getLogger(IfrmHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }

        jtblCad.setVisible(false);
        jScrollPane2.setVisible(false);
        jtblCad.setGridColor(ClsEstilo.tabelaGrid);
        jtblCad.setBackground(ClsEstilo.tabelaBg);
        jScrollPane2.getViewport().setBackground(ClsEstilo.tabelaBg);
        jScrollPane2.setBorder(null);
        jtblCad.setSelectionBackground(ClsEstilo.tabelaSelec);
        jtblCad.setSelectionForeground(ClsEstilo.tabelaTextoSelec);
        jtblCad.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void editClick() {
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "cardEdit");

        pnlLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnlEdicoes.setBorder(javax.swing.BorderFactory.createBevelBorder(1));
        pnlExclusoes.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        try {
            List<UsuarioDAO> usuarios = JDBCConsulta.usuarioTodos();
            int s = usuarios.size();

            for (int i = 0; i < s; i++) {
                cbxLoginEdit.addItem(usuarios.get(i).getNick());
            }

        } catch (SQLException ex) {
            Logger.getLogger(IfrmHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }

        jtblEdit.setVisible(false);
        jScrollPane3.setVisible(false);
        jtblEdit.setGridColor(ClsEstilo.tabelaGrid);
        jtblEdit.setBackground(ClsEstilo.tabelaBg);
        jScrollPane3.getViewport().setBackground(ClsEstilo.tabelaBg);
        jScrollPane3.setBorder(null);
        jtblEdit.setSelectionBackground(ClsEstilo.tabelaSelec);
        jtblEdit.setSelectionForeground(ClsEstilo.tabelaTextoSelec);
        jtblEdit.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void deleteClick() {
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "cardDelete");

        pnlLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnlEdicoes.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnlExclusoes.setBorder(javax.swing.BorderFactory.createBevelBorder(1));

        try {
            List<UsuarioDAO> usuarios = JDBCConsulta.usuarioTodos();
            int s = usuarios.size();

            for (int i = 0; i < s; i++) {
                cbxLoginDelete.addItem(usuarios.get(i).getNick());
            }

        } catch (SQLException ex) {
            Logger.getLogger(IfrmHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }

        jtblDelete.setVisible(false);
        jScrollPane4.setVisible(false);
        jtblDelete.setGridColor(ClsEstilo.tabelaGrid);
        jtblDelete.setBackground(ClsEstilo.tabelaBg);
        jScrollPane4.getViewport().setBackground(ClsEstilo.tabelaBg);
        jScrollPane4.setBorder(null);
        jtblDelete.setSelectionBackground(ClsEstilo.tabelaSelec);
        jtblDelete.setSelectionForeground(ClsEstilo.tabelaTextoSelec);
        jtblDelete.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void addEvents() {
        cbxPeriodo.addItemListener(new ComboBoxItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    periodo = e.getItem().toString();
                    atualizaTabelaLog();
                }
            }
        });

        cbxLoginLog.addItemListener(new ComboBoxItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    nick = e.getItem().toString();
                    atualizaTabelaLog();
                }
            }
        });
    }

    private void atualizaTabelaLog() {
        try {
            if (periodo.equalsIgnoreCase("24 horas")) {
                if (nick.equalsIgnoreCase("todos")) {
                    login24h();
                } else {
                    login24h(nick);
                }
            } else if (periodo.equalsIgnoreCase("3 dias")) {
                if (nick.equalsIgnoreCase("todos")) {
                    login3d();
                } else {
                    login3d(nick);
                }
            } else if (periodo.equalsIgnoreCase("1 mês")) {
                if (nick.equalsIgnoreCase("todos")) {
                    login1m();
                } else {
                    login1m(nick);
                }
            } else if (periodo.equalsIgnoreCase("todos")) {
                if (nick.equalsIgnoreCase("todos")) {
                    loginTodos();
                } else {
                    loginTodos(nick);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar os registros.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void login24h() throws SQLException {
        List<RegistroDAO> registros = JDBCViews.auditLogin24h();
        preencheTabelaLog(registros);
    }

    private void login3d() throws SQLException {
        List<RegistroDAO> registros = JDBCViews.auditLogin3d();
        preencheTabelaLog(registros);
    }

    private void login7d() throws SQLException {
        List<RegistroDAO> registros = JDBCViews.auditLogin7d();
        preencheTabelaLog(registros);
    }

    private void login1m() throws SQLException {
        List<RegistroDAO> registros = JDBCViews.auditLogin1m();
        preencheTabelaLog(registros);
    }

    private void loginTodos() throws SQLException {
        List<RegistroDAO> registros = JDBCConsulta.auditLogin();
        preencheTabelaLog(registros);
    }

    private void login24h(String nick) throws SQLException {
        List<RegistroDAO> registros = JDBCViews.auditLogin24h(nick);
        preencheTabelaLog(registros);
    }

    private void login3d(String nick) throws SQLException {
        List<RegistroDAO> registros = JDBCViews.auditLogin3d(nick);
        preencheTabelaLog(registros);
    }

    private void login7d(String nick) throws SQLException {
        List<RegistroDAO> registros = JDBCViews.auditLogin7d(nick);
        preencheTabelaLog(registros);
    }

    private void login1m(String nick) throws SQLException {
        List<RegistroDAO> registros = JDBCViews.auditLogin1m(nick);
        preencheTabelaLog(registros);
    }

    private void loginTodos(String nick) throws SQLException {
        List<RegistroDAO> registros = JDBCConsulta.auditLogin(nick);
        preencheTabelaLog(registros);
    }

    /**
     * Creates new form IfrmHistorico
     */
    public IfrmHistorico() {
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

        jLabel6 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        card1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cardLogin = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblDesconto = new javax.swing.JLabel();
        cbxPeriodo = new javax.swing.JComboBox();
        lblPeriodo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblLogins = new javax.swing.JTable();
        cbxLoginLog = new javax.swing.JComboBox();
        cardCad = new javax.swing.JPanel();
        cbxLoginCad = new javax.swing.JComboBox();
        lblUsuario1 = new javax.swing.JLabel();
        lblDesconto1 = new javax.swing.JLabel();
        cbxPeriodo1 = new javax.swing.JComboBox();
        lblPeriodo1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblCad = new javax.swing.JTable();
        lblPeriodo2 = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox();
        cardEdit = new javax.swing.JPanel();
        cbxLoginEdit = new javax.swing.JComboBox();
        lblUsuario2 = new javax.swing.JLabel();
        lblDesconto2 = new javax.swing.JLabel();
        cbxPeriodo2 = new javax.swing.JComboBox();
        lblPeriodo3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblEdit = new javax.swing.JTable();
        lblPeriodo4 = new javax.swing.JLabel();
        cbxTipo1 = new javax.swing.JComboBox();
        cardDelete = new javax.swing.JPanel();
        cbxLoginDelete = new javax.swing.JComboBox();
        lblUsuario3 = new javax.swing.JLabel();
        lblDesconto3 = new javax.swing.JLabel();
        cbxPeriodo3 = new javax.swing.JComboBox();
        lblPeriodo5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtblDelete = new javax.swing.JTable();
        pnlMenu = new javax.swing.JPanel();
        pnlLogin = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pnlCadastro = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pnlEdicoes = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlExclusoes = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 153));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
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

        jLabel6.setFont(ClsEstilo.tituloFonte);
        jLabel6.setForeground(ClsEstilo.tituloCor);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Histórico do sistema");

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.CardLayout());

        card1.setBackground(ClsEstilo.formbg);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quais registros você");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("deseja visualizar?");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ao lado.");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<--- Escolha no menu");

        javax.swing.GroupLayout card1Layout = new javax.swing.GroupLayout(card1);
        card1.setLayout(card1Layout);
        card1Layout.setHorizontalGroup(
            card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE))
                .addContainerGap())
        );
        card1Layout.setVerticalGroup(
            card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(54, 54, 54)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        mainPanel.add(card1, "card1");

        cardLogin.setBackground(ClsEstilo.formbg);

        lblUsuario.setFont(ClsEstilo.labelFonte);
        lblUsuario.setForeground(ClsEstilo.labelCor);
        lblUsuario.setText("Usuário");

        lblDesconto.setBackground(ClsEstilo.formbg);
        lblDesconto.setFont(ClsEstilo.linkFonte);
        lblDesconto.setForeground(ClsEstilo.linkCor);
        lblDesconto.setText("Filtrar resultados");
        lblDesconto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblDesconto.setOpaque(true);
        lblDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDescontoMouseClicked(evt);
            }
        });

        cbxPeriodo.setFont(ClsEstilo.textoInputFonte);
        cbxPeriodo.setForeground(ClsEstilo.textoInputCor);
        cbxPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24 horas", "3 dias", "1 semana", "1 mês", "todos" }));
        cbxPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPeriodoItemStateChanged(evt);
            }
        });
        cbxPeriodo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxPeriodoPropertyChange(evt);
            }
        });

        lblPeriodo.setFont(ClsEstilo.labelFonte);
        lblPeriodo.setForeground(ClsEstilo.labelCor);
        lblPeriodo.setText("Período");

        jtblLogins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtblLogins);

        cbxLoginLog.setEditable(true);
        cbxLoginLog.setFont(ClsEstilo.textoInputFonte);
        cbxLoginLog.setForeground(ClsEstilo.textoInputCor);
        cbxLoginLog.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));
        cbxLoginLog.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLoginLogItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout cardLoginLayout = new javax.swing.GroupLayout(cardLogin);
        cardLogin.setLayout(cardLoginLayout);
        cardLoginLayout.setHorizontalGroup(
            cardLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addGroup(cardLoginLayout.createSequentialGroup()
                        .addGroup(cardLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardLoginLayout.createSequentialGroup()
                                .addComponent(lblUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLoginLog, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPeriodo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDesconto))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cardLoginLayout.setVerticalGroup(
            cardLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardLoginLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblDesconto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(cbxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPeriodo)
                    .addComponent(cbxLoginLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        AutoCompleteDecorator.decorate(cbxLoginLog);

        mainPanel.add(cardLogin, "cardLogin");

        cardCad.setBackground(ClsEstilo.formbg);

        cbxLoginCad.setEditable(true);
        cbxLoginCad.setFont(ClsEstilo.textoInputFonte);
        cbxLoginCad.setForeground(ClsEstilo.textoInputCor);
        cbxLoginCad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        lblUsuario1.setFont(ClsEstilo.labelFonte);
        lblUsuario1.setForeground(ClsEstilo.labelCor);
        lblUsuario1.setText("Usuário");

        lblDesconto1.setBackground(ClsEstilo.formbg);
        lblDesconto1.setFont(ClsEstilo.linkFonte);
        lblDesconto1.setForeground(ClsEstilo.linkCor);
        lblDesconto1.setText("Filtrar resultados");
        lblDesconto1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblDesconto1.setOpaque(true);
        lblDesconto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDesconto1MouseClicked(evt);
            }
        });

        cbxPeriodo1.setFont(ClsEstilo.textoInputFonte);
        cbxPeriodo1.setForeground(ClsEstilo.textoInputCor);
        cbxPeriodo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24 horas", "3 dias", "1 semana", "1 mês", "todos" }));

        lblPeriodo1.setFont(ClsEstilo.labelFonte);
        lblPeriodo1.setForeground(ClsEstilo.labelCor);
        lblPeriodo1.setText("Período");

        jtblCad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtblCad);

        lblPeriodo2.setFont(ClsEstilo.labelFonte);
        lblPeriodo2.setForeground(ClsEstilo.labelCor);
        lblPeriodo2.setText("Tipo");

        cbxTipo.setFont(ClsEstilo.textoInputFonte);
        cbxTipo.setForeground(ClsEstilo.textoInputCor);
        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Cliente", "Orçamento", "Responsável", "Sessão", "Usuário" }));

        javax.swing.GroupLayout cardCadLayout = new javax.swing.GroupLayout(cardCad);
        cardCad.setLayout(cardCadLayout);
        cardCadLayout.setHorizontalGroup(
            cardCadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardCadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardCadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardCadLayout.createSequentialGroup()
                        .addComponent(lblDesconto1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(cardCadLayout.createSequentialGroup()
                        .addGroup(cardCadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardCadLayout.createSequentialGroup()
                                .addComponent(lblUsuario1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLoginCad, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPeriodo2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPeriodo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 38, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
        );
        cardCadLayout.setVerticalGroup(
            cardCadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardCadLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblDesconto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardCadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardCadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPeriodo1))
                    .addGroup(cardCadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPeriodo2))
                    .addGroup(cardCadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxLoginCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblUsuario1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        AutoCompleteDecorator.decorate(cbxLoginCad);

        mainPanel.add(cardCad, "cardCad");

        cardEdit.setBackground(ClsEstilo.formbg);

        cbxLoginEdit.setEditable(true);
        cbxLoginEdit.setFont(ClsEstilo.textoInputFonte);
        cbxLoginEdit.setForeground(ClsEstilo.textoInputCor);
        cbxLoginEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        lblUsuario2.setFont(ClsEstilo.labelFonte);
        lblUsuario2.setForeground(ClsEstilo.labelCor);
        lblUsuario2.setText("Usuário");

        lblDesconto2.setBackground(ClsEstilo.formbg);
        lblDesconto2.setFont(ClsEstilo.linkFonte);
        lblDesconto2.setForeground(ClsEstilo.linkCor);
        lblDesconto2.setText("Filtrar resultados");
        lblDesconto2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblDesconto2.setOpaque(true);
        lblDesconto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDesconto2MouseClicked(evt);
            }
        });

        cbxPeriodo2.setFont(ClsEstilo.textoInputFonte);
        cbxPeriodo2.setForeground(ClsEstilo.textoInputCor);
        cbxPeriodo2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24 horas", "3 dias", "1 semana", "1 mês", "todos" }));

        lblPeriodo3.setFont(ClsEstilo.labelFonte);
        lblPeriodo3.setForeground(ClsEstilo.labelCor);
        lblPeriodo3.setText("Período");

        jtblEdit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jtblEdit);

        lblPeriodo4.setFont(ClsEstilo.labelFonte);
        lblPeriodo4.setForeground(ClsEstilo.labelCor);
        lblPeriodo4.setText("Tipo");

        cbxTipo1.setFont(ClsEstilo.textoInputFonte);
        cbxTipo1.setForeground(ClsEstilo.textoInputCor);
        cbxTipo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Cliente", "Orçamento", "Responsável", "Sessão", "Usuário" }));

        javax.swing.GroupLayout cardEditLayout = new javax.swing.GroupLayout(cardEdit);
        cardEdit.setLayout(cardEditLayout);
        cardEditLayout.setHorizontalGroup(
            cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardEditLayout.createSequentialGroup()
                        .addComponent(lblDesconto2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(cardEditLayout.createSequentialGroup()
                        .addGroup(cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardEditLayout.createSequentialGroup()
                                .addComponent(lblUsuario2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLoginEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPeriodo4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxTipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPeriodo3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxPeriodo2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        cardEditLayout.setVerticalGroup(
            cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardEditLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblDesconto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxLoginEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario2)
                    .addComponent(cbxPeriodo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPeriodo3)
                    .addComponent(cbxTipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPeriodo4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AutoCompleteDecorator.decorate(cbxLoginEdit);

        mainPanel.add(cardEdit, "cardEdit");

        cardDelete.setBackground(ClsEstilo.formbg);

        cbxLoginDelete.setEditable(true);
        cbxLoginDelete.setFont(ClsEstilo.textoInputFonte);
        cbxLoginDelete.setForeground(ClsEstilo.textoInputCor);
        cbxLoginDelete.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        lblUsuario3.setFont(ClsEstilo.labelFonte);
        lblUsuario3.setForeground(ClsEstilo.labelCor);
        lblUsuario3.setText("Usuário");

        lblDesconto3.setBackground(ClsEstilo.formbg);
        lblDesconto3.setFont(ClsEstilo.linkFonte);
        lblDesconto3.setForeground(ClsEstilo.linkCor);
        lblDesconto3.setText("Filtrar resultados");
        lblDesconto3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblDesconto3.setOpaque(true);
        lblDesconto3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDesconto3MouseClicked(evt);
            }
        });

        cbxPeriodo3.setFont(ClsEstilo.textoInputFonte);
        cbxPeriodo3.setForeground(ClsEstilo.textoInputCor);
        cbxPeriodo3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24 horas", "3 dias", "1 semana", "1 mês", "todos" }));

        lblPeriodo5.setFont(ClsEstilo.labelFonte);
        lblPeriodo5.setForeground(ClsEstilo.labelCor);
        lblPeriodo5.setText("Período");

        jtblDelete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jtblDelete);

        javax.swing.GroupLayout cardDeleteLayout = new javax.swing.GroupLayout(cardDelete);
        cardDelete.setLayout(cardDeleteLayout);
        cardDeleteLayout.setHorizontalGroup(
            cardDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addGroup(cardDeleteLayout.createSequentialGroup()
                        .addGroup(cardDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardDeleteLayout.createSequentialGroup()
                                .addComponent(lblUsuario3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLoginDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPeriodo5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxPeriodo3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDesconto3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cardDeleteLayout.setVerticalGroup(
            cardDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDeleteLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblDesconto3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxLoginDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario3)
                    .addComponent(cbxPeriodo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPeriodo5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AutoCompleteDecorator.decorate(cbxLoginDelete);

        mainPanel.add(cardDelete, "cardDelete");

        pnlMenu.setBackground(ClsEstilo.formSombra);
        pnlMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        pnlLogin.setBackground(new java.awt.Color(255, 255, 255));
        pnlLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLoginMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlLoginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlLoginMouseReleased(evt);
            }
        });

        jLabel5.setFont(ClsEstilo.labelDestaqueFonte);
        jLabel5.setForeground(ClsEstilo.labelDestaqueCor);
        jLabel5.setText("Logins");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel5MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCadastro.setBackground(new java.awt.Color(255, 255, 255));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCadastroMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlCadastroMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlCadastroMouseReleased(evt);
            }
        });

        jLabel8.setFont(ClsEstilo.labelDestaqueFonte);
        jLabel8.setForeground(ClsEstilo.labelDestaqueCor);
        jLabel8.setText("Cadastros");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel8MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlEdicoes.setBackground(new java.awt.Color(255, 255, 255));
        pnlEdicoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlEdicoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlEdicoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlEdicoesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlEdicoesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlEdicoesMouseReleased(evt);
            }
        });

        jLabel9.setFont(ClsEstilo.labelDestaqueFonte);
        jLabel9.setForeground(ClsEstilo.labelDestaqueCor);
        jLabel9.setText("Edições");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel9MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlEdicoesLayout = new javax.swing.GroupLayout(pnlEdicoes);
        pnlEdicoes.setLayout(pnlEdicoesLayout);
        pnlEdicoesLayout.setHorizontalGroup(
            pnlEdicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEdicoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        pnlEdicoesLayout.setVerticalGroup(
            pnlEdicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEdicoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlExclusoes.setBackground(new java.awt.Color(255, 255, 255));
        pnlExclusoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlExclusoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlExclusoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlExclusoesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlExclusoesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlExclusoesMouseReleased(evt);
            }
        });

        jLabel10.setFont(ClsEstilo.labelDestaqueFonte);
        jLabel10.setForeground(ClsEstilo.labelDestaqueCor);
        jLabel10.setText("Exclusões");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel10MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlExclusoesLayout = new javax.swing.GroupLayout(pnlExclusoes);
        pnlExclusoes.setLayout(pnlExclusoesLayout);
        pnlExclusoesLayout.setHorizontalGroup(
            pnlExclusoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExclusoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlExclusoesLayout.setVerticalGroup(
            pnlExclusoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExclusoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEdicoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlExclusoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlEdicoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlExclusoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "card1");

        addEvents();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        pnlLogin.setBackground(ClsEstilo.formSombra);
    }//GEN-LAST:event_jLabel5MousePressed

    private void pnlLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLoginMousePressed
        pnlLogin.setBackground(ClsEstilo.formSombra);
    }//GEN-LAST:event_pnlLoginMousePressed

    private void pnlLoginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLoginMouseReleased
        pnlLogin.setBackground(ClsEstilo.formbg);
    }//GEN-LAST:event_pnlLoginMouseReleased

    private void jLabel5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseReleased
        pnlLogin.setBackground(ClsEstilo.formbg);
    }//GEN-LAST:event_jLabel5MouseReleased

    private void pnlCadastroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCadastroMousePressed
        pnlCadastro.setBackground(ClsEstilo.formSombra);
    }//GEN-LAST:event_pnlCadastroMousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        pnlCadastro.setBackground(ClsEstilo.formSombra);
    }//GEN-LAST:event_jLabel8MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        pnlEdicoes.setBackground(ClsEstilo.formSombra);
    }//GEN-LAST:event_jLabel9MousePressed

    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
        pnlExclusoes.setBackground(ClsEstilo.formSombra);
    }//GEN-LAST:event_jLabel10MousePressed

    private void pnlCadastroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCadastroMouseReleased
        pnlCadastro.setBackground(ClsEstilo.formbg);
    }//GEN-LAST:event_pnlCadastroMouseReleased

    private void jLabel8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseReleased
        pnlCadastro.setBackground(ClsEstilo.formbg);
    }//GEN-LAST:event_jLabel8MouseReleased

    private void pnlEdicoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEdicoesMousePressed
        pnlEdicoes.setBackground(ClsEstilo.formSombra);
    }//GEN-LAST:event_pnlEdicoesMousePressed

    private void pnlExclusoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExclusoesMousePressed
        pnlExclusoes.setBackground(ClsEstilo.formSombra);
    }//GEN-LAST:event_pnlExclusoesMousePressed

    private void pnlEdicoesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEdicoesMouseReleased
        pnlEdicoes.setBackground(ClsEstilo.formbg);
    }//GEN-LAST:event_pnlEdicoesMouseReleased

    private void jLabel9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseReleased
        pnlEdicoes.setBackground(ClsEstilo.formbg);
    }//GEN-LAST:event_jLabel9MouseReleased

    private void pnlExclusoesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExclusoesMouseReleased
        pnlExclusoes.setBackground(ClsEstilo.formbg);
    }//GEN-LAST:event_pnlExclusoesMouseReleased

    private void jLabel10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseReleased
        pnlExclusoes.setBackground(ClsEstilo.formbg);
    }//GEN-LAST:event_jLabel10MouseReleased

    private void pnlLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLoginMouseClicked
        loginClick();
    }//GEN-LAST:event_pnlLoginMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        loginClick();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void lblDescontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDescontoMouseClicked

    }//GEN-LAST:event_lblDescontoMouseClicked

    private void lblDesconto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDesconto1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDesconto1MouseClicked

    private void pnlCadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCadastroMouseClicked
        cadClick();
    }//GEN-LAST:event_pnlCadastroMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        cadClick();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void lblDesconto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDesconto2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDesconto2MouseClicked

    private void pnlEdicoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEdicoesMouseClicked
        editClick();
    }//GEN-LAST:event_pnlEdicoesMouseClicked

    private void lblDesconto3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDesconto3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDesconto3MouseClicked

    private void pnlExclusoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExclusoesMouseClicked
        deleteClick();
    }//GEN-LAST:event_pnlExclusoesMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        deleteClick();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void cbxPeriodoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxPeriodoPropertyChange

    }//GEN-LAST:event_cbxPeriodoPropertyChange

    private void cbxPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPeriodoItemStateChanged

    }//GEN-LAST:event_cbxPeriodoItemStateChanged

    private void cbxLoginLogItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLoginLogItemStateChanged
        System.out.println(cbxLoginLog.getSelectedItem());
    }//GEN-LAST:event_cbxLoginLogItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel card1;
    private javax.swing.JPanel cardCad;
    private javax.swing.JPanel cardDelete;
    private javax.swing.JPanel cardEdit;
    private javax.swing.JPanel cardLogin;
    private javax.swing.JComboBox cbxLoginCad;
    private javax.swing.JComboBox cbxLoginDelete;
    private javax.swing.JComboBox cbxLoginEdit;
    private javax.swing.JComboBox cbxLoginLog;
    private javax.swing.JComboBox cbxPeriodo;
    private javax.swing.JComboBox cbxPeriodo1;
    private javax.swing.JComboBox cbxPeriodo2;
    private javax.swing.JComboBox cbxPeriodo3;
    private javax.swing.JComboBox cbxTipo;
    private javax.swing.JComboBox cbxTipo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jtblCad;
    private javax.swing.JTable jtblDelete;
    private javax.swing.JTable jtblEdit;
    private javax.swing.JTable jtblLogins;
    private javax.swing.JLabel lblDesconto;
    private javax.swing.JLabel lblDesconto1;
    private javax.swing.JLabel lblDesconto2;
    private javax.swing.JLabel lblDesconto3;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblPeriodo1;
    private javax.swing.JLabel lblPeriodo2;
    private javax.swing.JLabel lblPeriodo3;
    private javax.swing.JLabel lblPeriodo4;
    private javax.swing.JLabel lblPeriodo5;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuario1;
    private javax.swing.JLabel lblUsuario2;
    private javax.swing.JLabel lblUsuario3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlEdicoes;
    private javax.swing.JPanel pnlExclusoes;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables
}
