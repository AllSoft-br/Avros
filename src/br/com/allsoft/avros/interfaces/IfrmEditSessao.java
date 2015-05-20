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
import br.com.allsoft.avros.exceptions.ValorInvalidoMoedaException;
import br.com.allsoft.avros.factory.JDBCDelete;
import br.com.allsoft.avros.factory.JDBCUpdate;
import br.com.allsoft.avros.formulas.Moeda;
import br.com.allsoft.avros.formulas.Cpf;
import br.com.allsoft.avros.relatorios.Relatorio;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Luana
 */
public class IfrmEditSessao extends javax.swing.JInternalFrame {

    //Variáveis
    OrcamentoDAO orcamento;
    ClienteDAO cliente;
    SessaoDAO sessao;

    double vsessao;
    String pagamento;

    //Métodos
    /**
     * Atualiza o label de valor da sessão de acordo com modificações
     */
    private void atualizaValor() {
        int sessoes = orcamento.getSessoes();
        double valor = orcamento.getValor();

        //para facilitar a visualização do valor, trocamos
        //os pontos por virgula (padrão brasileiro
        DecimalFormat df = new DecimalFormat("0.00");
        vsessao = valor / sessoes;

        if (ftxtDesconto.isVisible()) {
            double desconto;
            desconto = Double.parseDouble(ftxtDesconto.getText().replace(",", "."));

            vsessao = vsessao - desconto;
        }

        String sessao = df.format(vsessao);

        sessao = sessao.replace(".", ",");

        lblValor.setText("R$ " + sessao);
    }

    private void preencheCampos() {
        lblValorDesconto.setVisible(false);
        ftxtDesconto.setVisible(false);

        java.util.Date data = new java.util.Date(sessao.getHora().getTime());

        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            data = format.parse(data.toString());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "O horário da sessão não pôde ser carregado.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtDesc.setText(orcamento.getDescricao());
        lblOrcCod.setText(String.valueOf(orcamento.getId()));
        lblCodSes.setText(String.valueOf(sessao.getId()));
        lblNome.setText(cliente.getNome());
        lblCpf.setText(Cpf.imprimeCpf(cliente.getCpf()));
        dateData.setDate(sessao.getData());
        spnHorario.setValue(data);
        ftxtDesconto.setText(Moeda.padraoVirgula(sessao.getDesconto()));

        if ("Cartão".equalsIgnoreCase(sessao.getPagamento())) {
            rdoCartao.setSelected(true);
        } else if (sessao.getPagamento().equalsIgnoreCase("Dinheiro")) {
            rdoDinheiro.setSelected(true);
        }

        if (sessao.isConcluida()) {
            lblStatus.setForeground(Color.green);
            lblStatus.setText("Pagamento efetuado");
            lblEditarStatus.setText("Marcar como 'Pagamento pendente'");
        } else {
            lblStatus.setForeground(Color.red);
            lblStatus.setText("Pagamento pendente");
            lblEditarStatus.setText("Marcar como 'Pagamento efetuado'");
        }

        atualizaValor();

        sessao.setCliente(cliente.getNome());
        sessao.setIdOrcamento(orcamento.getId());
        sessao.setCpf(cliente.getCpf());
    }

    private void editCartao() throws SQLException {
        if (rdoCartao.isSelected()) {
            sessao.setPagamento("Cartão");
        } else if (rdoDinheiro.isSelected()) {
            sessao.setPagamento("Dinheiro");
        } else {
            sessao.setPagamento("Não especificado");
        }

        JDBCUpdate.sessaoPagamento(sessao.getId(), sessao.getPagamento());
    }

    private void editData() throws SQLException {
        Date dataUtil = dateData.getDate();

        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

        sessao.setData(dataSql);
        JDBCUpdate.sessaoData(sessao.getId(), dataSql);
    }

    private void editHorario() throws ParseException, SQLException {
        Date horaUtil = new Date();

        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        try {
            horaUtil = format.parse(spnHorario.getValue().toString());
        } catch (ParseException ex) {
            Logger.getLogger(IfrmCadSessao.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.sql.Time horaSql = new java.sql.Time(horaUtil.getTime());

        sessao.setHora(horaSql);

        JDBCUpdate.sessaoHora(sessao.getId(), horaSql);
    }

    private void editDesconto() throws ValorInvalidoMoedaException, SQLException {
        double desconto = Moeda.retornaDouble(ftxtDesconto.getText());
        sessao.setDesconto(desconto);

        JDBCUpdate.sessaoDesconto(sessao.getId(), desconto);
    }

    /**
     * Creates new form ifrmPagamentos
     *
     * @param orcamento orçamento do qual a sessão faz parte
     * @param cliente cliente que fará a sessão
     * @param sessao sessao a ser editada
     */
    public IfrmEditSessao(OrcamentoDAO orcamento, ClienteDAO cliente, SessaoDAO sessao) {
        this.orcamento = orcamento;
        this.cliente = cliente;
        this.sessao = sessao;

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
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jXGraph1 = new org.jdesktop.swingx.JXGraph();
        imagePainter1 = new org.jdesktop.swingx.painter.ImagePainter();
        compoundPainter1 = new org.jdesktop.swingx.painter.CompoundPainter();
        checkerboardPainter1 = new org.jdesktop.swingx.painter.CheckerboardPainter();
        jXCollapsiblePane1 = new org.jdesktop.swingx.JXCollapsiblePane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXEditorPane1 = new org.jdesktop.swingx.JXEditorPane();
        jXRootPane1 = new org.jdesktop.swingx.JXRootPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jXTree1 = new org.jdesktop.swingx.JXTree();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdoCartao = new javax.swing.JRadioButton();
        rdoDinheiro = new javax.swing.JRadioButton();
        lblDesconto = new javax.swing.JLabel();
        lblValorDesconto = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        lblOrcCod = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        ftxtDesconto = new javax.swing.JFormattedTextField();
        btnSalvar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        dateData = new org.jdesktop.swingx.JXDatePicker();
        jLabel9 = new javax.swing.JLabel();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel();
        spnHorario = new javax.swing.JSpinner(sm);
        jSeparator1 = new javax.swing.JSeparator();
        lblEditarPagam = new javax.swing.JLabel();
        lblEditarData = new javax.swing.JLabel();
        lblEditarHora = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblCodSes = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblEditarStatus = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        scrollDesc2 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextPane();
        jLabel13 = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        jScrollPane1.setViewportView(jXTable1);

        javax.swing.GroupLayout jXGraph1Layout = new javax.swing.GroupLayout(jXGraph1);
        jXGraph1.setLayout(jXGraph1Layout);
        jXGraph1Layout.setHorizontalGroup(
            jXGraph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
        );
        jXGraph1Layout.setVerticalGroup(
            jXGraph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jXEditorPane1);

        jScrollPane3.setViewportView(jXTree1);

        jXLabel1.setText("jXLabel1");

        jLabel12.setText("jLabel12");

        setClosable(true);
        setIconifiable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/Clock.png"))); // NOI18N
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
        jLabel1.setText("Editar sessão");

        jLabel2.setFont(ClsEstilo.labelFonte);
        jLabel2.setForeground(ClsEstilo.labelCor);
        jLabel2.setText("Nome");

        jLabel3.setFont(ClsEstilo.labelFonte);
        jLabel3.setForeground(ClsEstilo.labelCor);
        jLabel3.setText("CPF");
        jLabel3.setToolTipText("");

        jLabel4.setFont(ClsEstilo.labelFonte);
        jLabel4.setForeground(ClsEstilo.labelCor);
        jLabel4.setText("Orçamento código");

        jLabel5.setFont(ClsEstilo.labelDestaqueFonte);
        jLabel5.setForeground(ClsEstilo.labelDestaqueCor);
        jLabel5.setText("Valor da sessão: ");
        jLabel5.setToolTipText("");

        jLabel7.setFont(ClsEstilo.labelFonte);
        jLabel7.setForeground(ClsEstilo.labelCor);
        jLabel7.setText("Pagamento");

        rdoCartao.setFont(ClsEstilo.labelFonte);
        rdoCartao.setForeground(ClsEstilo.labelCor);
        rdoCartao.setText("Cartão");
        rdoCartao.setEnabled(false);

        rdoDinheiro.setFont(ClsEstilo.labelFonte);
        rdoDinheiro.setForeground(ClsEstilo.labelCor);
        rdoDinheiro.setText("Dinheiro");
        rdoDinheiro.setEnabled(false);

        lblDesconto.setBackground(ClsEstilo.formbg);
        lblDesconto.setFont(ClsEstilo.linkFonte);
        lblDesconto.setForeground(ClsEstilo.linkCor);
        lblDesconto.setText("Editar desconto");
        lblDesconto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDesconto.setOpaque(true);
        lblDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDescontoMouseClicked(evt);
            }
        });

        lblValorDesconto.setFont(ClsEstilo.labelFonte);
        lblValorDesconto.setForeground(ClsEstilo.labelCor);
        lblValorDesconto.setText("Valor:    R$ ");

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/allsoft/avros/img/logopequeno.png"))); // NOI18N

        lblValor.setFont(ClsEstilo.labelDestaqueFonte);
        lblValor.setForeground(ClsEstilo.labelDinheiroCor);
        lblValor.setText("R$ 0,00");

        lblOrcCod.setFont(ClsEstilo.labelFonte);
        lblOrcCod.setForeground(ClsEstilo.textoInputCor);
        lblOrcCod.setText("000");

        lblNome.setFont(ClsEstilo.labelFonte);
        lblNome.setForeground(ClsEstilo.textoInputCor);
        lblNome.setText("Fulano da Silva");

        lblCpf.setFont(ClsEstilo.labelFonte);
        lblCpf.setForeground(ClsEstilo.textoInputCor);
        lblCpf.setText("000.000.000-00");

        MaskFormatter dateMask = new MaskFormatter();
        dateMask.setPlaceholderCharacter('0') ;
        dateMask.install(ftxtDesconto);
        ftxtDesconto.setForeground(ClsEstilo.textoInputCor);
        ftxtDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        ftxtDesconto.setText("0,00");
        ftxtDesconto.setFont(ClsEstilo.textoInputFonte);
        ftxtDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ftxtDescontoKeyPressed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel8.setText("Data");

        dateData.setEnabled(false);

        jLabel9.setText("Horário");

        JSpinner.DateEditor de = new JSpinner.DateEditor(spnHorario, "HH:mm");
        de.getTextField().setEditable( false );
        spnHorario.setEditor(de);
        spnHorario.setEnabled(false);

        lblEditarPagam.setBackground(ClsEstilo.formbg);
        lblEditarPagam.setFont(ClsEstilo.linkFonte);
        lblEditarPagam.setForeground(ClsEstilo.linkCor);
        lblEditarPagam.setText("Editar");
        lblEditarPagam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEditarPagam.setOpaque(true);
        lblEditarPagam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarPagamMouseClicked(evt);
            }
        });

        lblEditarData.setBackground(ClsEstilo.formbg);
        lblEditarData.setFont(ClsEstilo.linkFonte);
        lblEditarData.setForeground(ClsEstilo.linkCor);
        lblEditarData.setText("Editar");
        lblEditarData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEditarData.setOpaque(true);
        lblEditarData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarDataMouseClicked(evt);
            }
        });

        lblEditarHora.setBackground(ClsEstilo.formbg);
        lblEditarHora.setFont(ClsEstilo.linkFonte);
        lblEditarHora.setForeground(ClsEstilo.linkCor);
        lblEditarHora.setText("Editar");
        lblEditarHora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEditarHora.setOpaque(true);
        lblEditarHora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarHoraMouseClicked(evt);
            }
        });

        jLabel10.setFont(ClsEstilo.labelFonte);
        jLabel10.setForeground(ClsEstilo.labelCor);
        jLabel10.setText("Sessão código");

        lblCodSes.setFont(ClsEstilo.labelFonte);
        lblCodSes.setForeground(ClsEstilo.textoInputCor);
        lblCodSes.setText("000");

        jLabel11.setFont(ClsEstilo.labelDestaqueFonte);
        jLabel11.setForeground(ClsEstilo.labelDestaqueCor);
        jLabel11.setText("Status:");
        jLabel11.setToolTipText("");

        lblStatus.setFont(ClsEstilo.labelDestaqueFonte);
        lblStatus.setForeground(ClsEstilo.labelDinheiroCor);
        lblStatus.setText("Pagamento pendente");

        lblEditarStatus.setBackground(ClsEstilo.formbg);
        lblEditarStatus.setFont(ClsEstilo.linkFonte);
        lblEditarStatus.setForeground(ClsEstilo.linkCor);
        lblEditarStatus.setText("Marcar como \"Pagamento efetuado\"");
        lblEditarStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEditarStatus.setOpaque(true);
        lblEditarStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarStatusMouseClicked(evt);
            }
        });

        btnImprimir.setFont(ClsEstilo.botaoFonte);
        btnImprimir.setForeground(ClsEstilo.botaoCor);
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
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

        scrollDesc2.setBorder(null);
        scrollDesc2.setOpaque(false);

        txtDesc.setEditable(false);
        txtDesc.setBorder(null);
        txtDesc.setFont(ClsEstilo.textoInputFonte);
        txtDesc.setForeground(ClsEstilo.textoInputCor);
        txtDesc.setOpaque(false);
        txtDesc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescFocusGained(evt);
            }
        });
        scrollDesc2.setViewportView(txtDesc);

        jLabel13.setFont(ClsEstilo.labelFonte);
        jLabel13.setForeground(ClsEstilo.labelCor);
        jLabel13.setText("Descrição do orçamento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollDesc2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblValor))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNome))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblOrcCod))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCpf)))
                                .addGap(55, 55, 55)
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImprimir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDesconto)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCodSes))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEditarStatus))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblValorDesconto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftxtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoCartao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoDinheiro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEditarPagam))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEditarData))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEditarHora)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblCodSes))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblOrcCod))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblCpf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollDesc2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCartao)
                    .addComponent(rdoDinheiro)
                    .addComponent(lblEditarPagam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(dateData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEditarData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(spnHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEditarHora))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDesconto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorDesconto)
                    .addComponent(ftxtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblValor)
                    .addComponent(btnSalvar)
                    .addComponent(btnImprimir)
                    .addComponent(btnExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblStatus)
                    .addComponent(lblEditarStatus))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Container a = this.getContentPane();
        a.setBackground(ClsEstilo.formbg);

        Dimension dim = this.getParent().getSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 + 50);

        preencheCampos();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        FrmPrincipal.bPreAgendarSessao = false;
    }//GEN-LAST:event_formInternalFrameClosed

    private void lblDescontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDescontoMouseClicked
        lblValorDesconto.setVisible(true);
        ftxtDesconto.setVisible(true);
    }//GEN-LAST:event_lblDescontoMouseClicked

    private void ftxtDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftxtDescontoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            atualizaValor();
            ftxtDesconto.transferFocus();
        }
    }//GEN-LAST:event_ftxtDescontoKeyPressed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        boolean funcionou = true;

        if (rdoCartao.isEnabled()) {
            try {
                editCartao();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "O método de pagamento não pôde ser salvo.", "Erro", JOptionPane.ERROR_MESSAGE);
                funcionou = false;
                Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (dateData.isEnabled()) {
            try {
                editData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "A nova data da sessão não pôde ser salva.", "Erro", JOptionPane.ERROR_MESSAGE);
                funcionou = false;
                Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (spnHorario.isEnabled()) {
            try {
                editHorario();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "O horário selecionado não é válido. Por favor escolha outro.", "Erro", JOptionPane.ERROR_MESSAGE);
                funcionou = false;
                Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "O novo horário não pôde ser salvo.", "Erro", JOptionPane.ERROR_MESSAGE);
                funcionou = false;
                Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ftxtDesconto.isVisible()) {
            try {
                editDesconto();
            } catch (ValorInvalidoMoedaException ex) {
                JOptionPane.showMessageDialog(this, "O valor de desconto selecionado não é válido. Por favor escolha um valor diferente.", "Erro", JOptionPane.ERROR_MESSAGE);
                funcionou = false;
                Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "O novo valor de desconto não pôde ser salvo.", "Erro", JOptionPane.ERROR_MESSAGE);
                funcionou = false;
                Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (funcionou) {
                try {
                    int j = JOptionPane.showConfirmDialog(this, "Informações salvas com sucesso! Deseja imprimir o comprovante?");
                    if (j == JOptionPane.YES_OPTION) {
                        Relatorio relatorio = new Relatorio();
                        relatorio.criaRelatorio(cliente.getCpf(), sessao.getId(), "sessaoAgend");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JRException ex) {
                    Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void lblEditarPagamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarPagamMouseClicked
        rdoCartao.setEnabled(true);
        rdoDinheiro.setEnabled(true);
    }//GEN-LAST:event_lblEditarPagamMouseClicked

    private void lblEditarDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarDataMouseClicked
        dateData.setEnabled(true);
    }//GEN-LAST:event_lblEditarDataMouseClicked

    private void lblEditarHoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarHoraMouseClicked
        spnHorario.setEnabled(true);
    }//GEN-LAST:event_lblEditarHoraMouseClicked

    private void lblEditarStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarStatusMouseClicked
        try {
            JDBCUpdate.sessaoConcluida(sessao.getId(), !sessao.isConcluida(), sessao.getIdOrcamento());
            sessao.setConcluida(!sessao.isConcluida());

            if (sessao.isConcluida()) {
                lblStatus.setForeground(Color.green);
                lblStatus.setText("Pagamento efetuado");
                lblEditarStatus.setText("Marcar como 'Pagamento pendente'");
            } else {
                lblStatus.setForeground(Color.red);
                lblStatus.setText("Pagamento pendente");
                lblEditarStatus.setText("Marcar como 'Pagamento efetuado'");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível atualizar o status da sessão. Por favor tente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblEditarStatusMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            Relatorio relatorio = new Relatorio();
            relatorio.criaRelatorio(cliente.getCpf(), sessao.getId(), "sessaoAgend");
        } catch (SQLException | JRException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível gerar o relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int j = JOptionPane.showConfirmDialog(this, "Você realmente deseja excluir esta sessão?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (j == JOptionPane.YES_OPTION) {
            try {
                JDBCDelete.sessao(sessao);
                JOptionPane.showMessageDialog(this, "A sessão foi excluída com sucesso.");
                this.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível excluir esta sessão.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(IfrmEditSessao.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtDescFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescFocusGained

    }//GEN-LAST:event_txtDescFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalvar;
    private org.jdesktop.swingx.painter.CheckerboardPainter checkerboardPainter1;
    private org.jdesktop.swingx.painter.CompoundPainter compoundPainter1;
    private org.jdesktop.swingx.JXDatePicker dateData;
    private javax.swing.JFormattedTextField ftxtDesconto;
    private org.jdesktop.swingx.painter.ImagePainter imagePainter1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private org.jdesktop.swingx.JXCollapsiblePane jXCollapsiblePane1;
    private org.jdesktop.swingx.JXEditorPane jXEditorPane1;
    private org.jdesktop.swingx.JXGraph jXGraph1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXRootPane jXRootPane1;
    private org.jdesktop.swingx.JXTable jXTable1;
    private org.jdesktop.swingx.JXTree jXTree1;
    private javax.swing.JLabel lblCodSes;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblDesconto;
    private javax.swing.JLabel lblEditarData;
    private javax.swing.JLabel lblEditarHora;
    private javax.swing.JLabel lblEditarPagam;
    private javax.swing.JLabel lblEditarStatus;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblOrcCod;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblValorDesconto;
    private javax.swing.JRadioButton rdoCartao;
    private javax.swing.JRadioButton rdoDinheiro;
    private javax.swing.JScrollPane scrollDesc2;
    private javax.swing.JSpinner spnHorario;
    private javax.swing.JTextPane txtDesc;
    // End of variables declaration//GEN-END:variables
}
