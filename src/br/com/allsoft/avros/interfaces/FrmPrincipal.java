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

import br.com.allsoft.avros.dao.AuditoriaLogin;
import br.com.allsoft.avros.exceptions.AuditoriaException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

/**
 * Esta é a classe do formulário principal do projeto Avros
 *
 * @author Luana
 */
public class FrmPrincipal extends javax.swing.JFrame {

    //Variáveis
    //Verificam se já existe um frame aberto de cada tipo
    public static boolean bConta = false;
    public static boolean bCliente = false;
    public static boolean bResp = false;
    public static boolean bUsuario = false;
    public static boolean bNovoOrcamento = false;
    public static boolean bPagamentos = false;
    public static boolean bPagarSessao = false;
    public static boolean bPreAgendarSessao = false;
    public static boolean bPesqOrcamento = false;
    public static boolean bSenha = false;
    public static boolean bHistorico = false;
    public static boolean bBackup = false;
    public static boolean bConsRep = false;
    public static boolean bConsUsuario = false;
    public static boolean bConsSessao = false;
    public static boolean bConsOrcamento = false;
    public static boolean bConsCliente = false;

    /**
     *
     * @param obj
     */
    public static void addFrame(JInternalFrame obj) {
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }

    /**
     * Creates new form FraPrincipal
     */
    public FrmPrincipal() {
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

        deskPrincipal = new ClsDesktopComImagem(ClsEstilo.imgDesk);
        menuBar = new javax.swing.JMenuBar();
        mnuPrincipal = new javax.swing.JMenu();
        mniConta = new javax.swing.JMenuItem();
        mniSenha = new javax.swing.JMenuItem();
        mniSair = new javax.swing.JMenuItem();
        mnuCadastro = new javax.swing.JMenu();
        mniCliente = new javax.swing.JMenuItem();
        mniUsuario = new javax.swing.JMenuItem();
        mniRepresentante = new javax.swing.JMenuItem();
        mnuOrcamento = new javax.swing.JMenu();
        mniNovo = new javax.swing.JMenuItem();
        mniPesquisar = new javax.swing.JMenuItem();
        mnuSessao = new javax.swing.JMenu();
        mniAgendar = new javax.swing.JMenuItem();
        mniPagar = new javax.swing.JMenuItem();
        mnuConsultar = new javax.swing.JMenu();
        mniVerCliente = new javax.swing.JMenuItem();
        mniVerOrcamento = new javax.swing.JMenuItem();
        mniVerSessoes = new javax.swing.JMenuItem();
        mniVerUsuario = new javax.swing.JMenuItem();
        mniVerRepresentantes = new javax.swing.JMenuItem();
        mnuAvancado = new javax.swing.JMenu();
        mniHistorico = new javax.swing.JMenuItem();
        mniBackup = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(601, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        deskPrincipal.setBackground(new java.awt.Color(212, 224, 239));
        deskPrincipal.setMinimumSize(new java.awt.Dimension(601, 451));
        deskPrincipal.setName(""); // NOI18N

        javax.swing.GroupLayout deskPrincipalLayout = new javax.swing.GroupLayout(deskPrincipal);
        deskPrincipal.setLayout(deskPrincipalLayout);
        deskPrincipalLayout.setHorizontalGroup(
            deskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 891, Short.MAX_VALUE)
        );
        deskPrincipalLayout.setVerticalGroup(
            deskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        menuBar.setBackground(new java.awt.Color(153, 255, 204));
        menuBar.setBorder(null);
        menuBar.setBorderPainted(false);
        menuBar.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N

        mnuPrincipal.setText("Avros");
        mnuPrincipal.setFont(ClsEstilo.labelMenuFonte);

        mniConta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        mniConta.setFont(ClsEstilo.labelMenuFonte);
        mniConta.setText("Minha conta");
        mniConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniContaActionPerformed(evt);
            }
        });
        mnuPrincipal.add(mniConta);

        mniSenha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mniSenha.setFont(ClsEstilo.labelMenuFonte);
        mniSenha.setText("Alterar Senha");
        mniSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSenhaActionPerformed(evt);
            }
        });
        mnuPrincipal.add(mniSenha);

        mniSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        mniSair.setFont(ClsEstilo.labelMenuFonte);
        mniSair.setText("Sair");
        mniSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSairActionPerformed(evt);
            }
        });
        mnuPrincipal.add(mniSair);

        menuBar.add(mnuPrincipal);

        mnuCadastro.setText("Cadastro");
        mnuCadastro.setFont(ClsEstilo.labelMenuFonte);

        mniCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mniCliente.setFont(ClsEstilo.labelMenuFonte);
        mniCliente.setText("Cliente");
        mniCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniClienteActionPerformed(evt);
            }
        });
        mnuCadastro.add(mniCliente);

        mniUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        mniUsuario.setFont(ClsEstilo.labelMenuFonte);
        mniUsuario.setForeground(ClsEstilo.labelCor);
        mniUsuario.setText("Usuário");
        mniUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUsuarioActionPerformed(evt);
            }
        });
        if(FrmLogin.usuario.isAdmin()){
            mnuCadastro.add(mniUsuario);
        }

        mniRepresentante.setFont(ClsEstilo.labelMenuFonte);
        mniRepresentante.setText("Representante");
        mniRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRepresentanteActionPerformed(evt);
            }
        });
        mnuCadastro.add(mniRepresentante);

        menuBar.add(mnuCadastro);

        mnuOrcamento.setText("Orçamento");
        mnuOrcamento.setFont(ClsEstilo.labelMenuFonte);

        mniNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mniNovo.setFont(ClsEstilo.labelMenuFonte);
        mniNovo.setText("Novo");
        mniNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNovoActionPerformed(evt);
            }
        });
        mnuOrcamento.add(mniNovo);

        mniPesquisar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mniPesquisar.setFont(ClsEstilo.labelMenuFonte);
        mniPesquisar.setText("Pesquisar");
        mniPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPesquisarActionPerformed(evt);
            }
        });
        mnuOrcamento.add(mniPesquisar);

        menuBar.add(mnuOrcamento);

        mnuSessao.setText("Sessão");
        mnuSessao.setFont(ClsEstilo.labelMenuFonte);

        mniAgendar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mniAgendar.setFont(ClsEstilo.labelMenuFonte);
        mniAgendar.setText("Agendar");
        mniAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAgendarActionPerformed(evt);
            }
        });
        mnuSessao.add(mniAgendar);

        mniPagar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        mniPagar.setFont(ClsEstilo.labelMenuFonte);
        mniPagar.setText("Pagar");
        mniPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPagarActionPerformed(evt);
            }
        });
        mnuSessao.add(mniPagar);

        menuBar.add(mnuSessao);

        mnuConsultar.setText("Consultar");
        mnuConsultar.setFont(ClsEstilo.labelMenuFonte);

        mniVerCliente.setFont(ClsEstilo.labelMenuFonte);
        mniVerCliente.setText("Cliente");
        mniVerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVerClienteActionPerformed(evt);
            }
        });
        mnuConsultar.add(mniVerCliente);

        mniVerOrcamento.setFont(ClsEstilo.labelMenuFonte);
        mniVerOrcamento.setText("Orçamento");
        mniVerOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVerOrcamentoActionPerformed(evt);
            }
        });
        mnuConsultar.add(mniVerOrcamento);

        mniVerSessoes.setFont(ClsEstilo.labelMenuFonte);
        mniVerSessoes.setText("Sessões");
        mniVerSessoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVerSessoesActionPerformed(evt);
            }
        });
        mnuConsultar.add(mniVerSessoes);

        mniVerUsuario.setFont(ClsEstilo.labelMenuFonte);
        mniVerUsuario.setForeground(ClsEstilo.labelCor);
        mniVerUsuario.setText("Usuários");
        mniVerUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVerUsuarioActionPerformed(evt);
            }
        });
        if(FrmLogin.usuario.isAdmin()){
            mnuConsultar.add(mniVerUsuario);
        }

        mniVerRepresentantes.setFont(ClsEstilo.labelMenuFonte);
        mniVerRepresentantes.setText("Representantes");
        mniVerRepresentantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVerRepresentantesActionPerformed(evt);
            }
        });
        mnuConsultar.add(mniVerRepresentantes);

        menuBar.add(mnuConsultar);

        mnuAvancado.setForeground(ClsEstilo.labelCor);
        mnuAvancado.setText("Avançado");
        mnuAvancado.setFont(ClsEstilo.labelMenuFonte);

        mniHistorico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        mniHistorico.setFont(ClsEstilo.labelMenuFonte);
        mniHistorico.setForeground(ClsEstilo.labelCor);
        mniHistorico.setText("Histórico");
        mniHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHistoricoActionPerformed(evt);
            }
        });
        mnuAvancado.add(mniHistorico);

        mniBackup.setFont(ClsEstilo.labelMenuFonte);
        mniBackup.setForeground(ClsEstilo.labelCor);
        mniBackup.setText("Backup");
        mniBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBackupActionPerformed(evt);
            }
        });
        mnuAvancado.add(mniBackup);

        if(FrmLogin.usuario.isAdmin()){

            menuBar.add(mnuAvancado);
        }

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniContaActionPerformed
        if (!bConta) {
            IfrmEditConta obj = new IfrmEditConta();
            deskPrincipal.add(obj);
            obj.setVisible(true);

            bConta = true;
        }
    }//GEN-LAST:event_mniContaActionPerformed

    private void mniUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUsuarioActionPerformed
        if (!bUsuario) {
            IfrmCadUsuario obj = new IfrmCadUsuario();
            deskPrincipal.add(obj);
            obj.setVisible(true);

            bUsuario = true;
        }
    }//GEN-LAST:event_mniUsuarioActionPerformed

    private void mniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniClienteActionPerformed
        if (!bCliente) {
            IfrmCadCliente obj = new IfrmCadCliente();
            deskPrincipal.add(obj);
            obj.setVisible(true);

            bCliente = true;
        }
    }//GEN-LAST:event_mniClienteActionPerformed

    private void mniNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNovoActionPerformed
        if (!bNovoOrcamento) {
            IfrmCadOrcamento obj = new IfrmCadOrcamento();
            deskPrincipal.add(obj);
            obj.setVisible(true);

            bNovoOrcamento = true;
        }
    }//GEN-LAST:event_mniNovoActionPerformed

    private void mniPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPesquisarActionPerformed
        if (!bPesqOrcamento) {
            IfrmConsOrcamento obj = new IfrmConsOrcamento();
            deskPrincipal.add(obj);
            obj.setVisible(true);

            bPesqOrcamento = true;
        }
    }//GEN-LAST:event_mniPesquisarActionPerformed

    private void mniAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAgendarActionPerformed
        if (!bPreAgendarSessao) {
            IfrmPreSessao obj = new IfrmPreSessao();
            deskPrincipal.add(obj);
            obj.setVisible(true);

            bPreAgendarSessao = true;
        }
    }//GEN-LAST:event_mniAgendarActionPerformed

    private void mniSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSenhaActionPerformed
        if (!bSenha) {
            IfrmEditSenha obj = new IfrmEditSenha();
            deskPrincipal.add(obj);
            obj.setVisible(true);

            bSenha = true;
        }
    }//GEN-LAST:event_mniSenhaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        URL url = this.getClass().getResource("/br/com/allsoft/avros/img/logo.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);

        this.setIconImage(imagemTitulo);
    }//GEN-LAST:event_formWindowOpened

    private void mniPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPagarActionPerformed
        if (!bPagarSessao) {
            IfrmConsSessao obj = new IfrmConsSessao();
            deskPrincipal.add(obj);
            obj.setVisible(true);

            bPagarSessao = true;
        }
    }//GEN-LAST:event_mniPagarActionPerformed

    private void mniSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSairActionPerformed
        try {
            AuditoriaLogin.logout(FrmLogin.usuario);
        } catch (AuditoriaException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        new FrmLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mniSairActionPerformed

    private void mniVerUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVerUsuarioActionPerformed
        IfrmConsUsuario obj = new IfrmConsUsuario();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniVerUsuarioActionPerformed

    private void mniVerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVerClienteActionPerformed
        IfrmConsCliente obj = new IfrmConsCliente();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniVerClienteActionPerformed

    private void mniVerRepresentantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVerRepresentantesActionPerformed
        if (!bConsRep) {
            IfrmConsRepresentante obj = new IfrmConsRepresentante();
            deskPrincipal.add(obj);
            obj.setVisible(true);
            bConsRep = true;
        }
    }//GEN-LAST:event_mniVerRepresentantesActionPerformed

    private void mniRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRepresentanteActionPerformed
        IfrmCadResp1 obj = new IfrmCadResp1();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniRepresentanteActionPerformed

    private void mniVerOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVerOrcamentoActionPerformed
        IfrmConsOrcamento obj = new IfrmConsOrcamento();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniVerOrcamentoActionPerformed

    private void mniVerSessoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVerSessoesActionPerformed
        IfrmConsSessao obj = new IfrmConsSessao();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniVerSessoesActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            AuditoriaLogin.logout(FrmLogin.usuario);
        } catch (AuditoriaException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void mniHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHistoricoActionPerformed
        if (!bHistorico) {
            IfrmHistorico obj = new IfrmHistorico();
            deskPrincipal.add(obj);
            obj.setVisible(true);
            bHistorico = true;
        }
    }//GEN-LAST:event_mniHistoricoActionPerformed

    private void mniBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBackupActionPerformed
        if (!bBackup) {
            IfrmBackup obj = new IfrmBackup();
            deskPrincipal.add(obj);
            obj.setVisible(true);
            bBackup = true;
        }
    }//GEN-LAST:event_mniBackupActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JDesktopPane deskPrincipal;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mniAgendar;
    private javax.swing.JMenuItem mniBackup;
    private javax.swing.JMenuItem mniCliente;
    private javax.swing.JMenuItem mniConta;
    private javax.swing.JMenuItem mniHistorico;
    private javax.swing.JMenuItem mniNovo;
    private javax.swing.JMenuItem mniPagar;
    private javax.swing.JMenuItem mniPesquisar;
    private javax.swing.JMenuItem mniRepresentante;
    private javax.swing.JMenuItem mniSair;
    private javax.swing.JMenuItem mniSenha;
    private javax.swing.JMenuItem mniUsuario;
    private javax.swing.JMenuItem mniVerCliente;
    private javax.swing.JMenuItem mniVerOrcamento;
    private javax.swing.JMenuItem mniVerRepresentantes;
    private javax.swing.JMenuItem mniVerSessoes;
    private javax.swing.JMenuItem mniVerUsuario;
    private javax.swing.JMenu mnuAvancado;
    private javax.swing.JMenu mnuCadastro;
    private javax.swing.JMenu mnuConsultar;
    private javax.swing.JMenu mnuOrcamento;
    private javax.swing.JMenu mnuPrincipal;
    private javax.swing.JMenu mnuSessao;
    // End of variables declaration//GEN-END:variables
}
