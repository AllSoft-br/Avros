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

/**
 * Esta é a classe do formulário principal do projeto Avros
 * 
 * @author Luana
 */
public class FrmPrincipal extends javax.swing.JFrame {


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

        deskPrincipal = new ClsDesktopComImagem("/br/com/allsoft/avros/img/logo.png");
        menuBar = new javax.swing.JMenuBar();
        mnuPrincipal = new javax.swing.JMenu();
        mniConta = new javax.swing.JMenuItem();
        mniSenha = new javax.swing.JMenuItem();
        mnuCadastro = new javax.swing.JMenu();
        mniCliente = new javax.swing.JMenuItem();
        mniUsuario = new javax.swing.JMenuItem();
        mnuOrcamento = new javax.swing.JMenu();
        mniNovo = new javax.swing.JMenuItem();
        mniPesquisar = new javax.swing.JMenuItem();
        mnuSessao = new javax.swing.JMenu();
        mniAgendar = new javax.swing.JMenuItem();
        mniPagar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(601, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        deskPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        deskPrincipal.setMinimumSize(new java.awt.Dimension(601, 451));
        deskPrincipal.setName(""); // NOI18N

        javax.swing.GroupLayout deskPrincipalLayout = new javax.swing.GroupLayout(deskPrincipal);
        deskPrincipal.setLayout(deskPrincipalLayout);
        deskPrincipalLayout.setHorizontalGroup(
            deskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
        );
        deskPrincipalLayout.setVerticalGroup(
            deskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
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

        menuBar.add(mnuPrincipal);

        mnuCadastro.setText("Cadastro");
        mnuCadastro.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

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
        mniUsuario.setText("Usuário");
        mniUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUsuarioActionPerformed(evt);
            }
        });
        mnuCadastro.add(mniUsuario);

        menuBar.add(mnuCadastro);

        mnuOrcamento.setText("Orçamento");
        mnuOrcamento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

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
        mnuSessao.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        mniAgendar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        mniAgendar.setFont(ClsEstilo.labelMenuFonte);
        mniAgendar.setText("Agendar");
        mniAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAgendarActionPerformed(evt);
            }
        });
        mnuSessao.add(mniAgendar);

        mniPagar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        mniPagar.setText("Pagar");
        mniPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPagarActionPerformed(evt);
            }
        });
        mnuSessao.add(mniPagar);

        menuBar.add(mnuSessao);

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
        IfrmConta obj = new IfrmConta();
        deskPrincipal.add(obj);
        obj.setVisible(true);

    }//GEN-LAST:event_mniContaActionPerformed

    private void mniUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUsuarioActionPerformed
        IfrmCadUsuario obj = new IfrmCadUsuario();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniUsuarioActionPerformed

    private void mniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniClienteActionPerformed
        IfrmCadCliente obj = new IfrmCadCliente();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniClienteActionPerformed

    private void mniNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNovoActionPerformed
        IfrmNovoOrcamento obj = new IfrmNovoOrcamento();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniNovoActionPerformed

    private void mniPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPesquisarActionPerformed
        IfrmPesqOrcamento obj = new IfrmPesqOrcamento();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniPesquisarActionPerformed

    private void mniAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAgendarActionPerformed
        IfrmAgendarSessao obj = new IfrmAgendarSessao();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniAgendarActionPerformed

    private void mniSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSenhaActionPerformed
        IfrmSenha obj = new IfrmSenha();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniSenhaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      
    }//GEN-LAST:event_formWindowOpened

    private void mniPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPagarActionPerformed
        IfrmPagarSessao obj = new IfrmPagarSessao();
        deskPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_mniPagarActionPerformed

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
    private javax.swing.JDesktopPane deskPrincipal;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mniAgendar;
    private javax.swing.JMenuItem mniCliente;
    private javax.swing.JMenuItem mniConta;
    private javax.swing.JMenuItem mniNovo;
    private javax.swing.JMenuItem mniPagar;
    private javax.swing.JMenuItem mniPesquisar;
    private javax.swing.JMenuItem mniSenha;
    private javax.swing.JMenuItem mniUsuario;
    private javax.swing.JMenu mnuCadastro;
    private javax.swing.JMenu mnuOrcamento;
    private javax.swing.JMenu mnuPrincipal;
    private javax.swing.JMenu mnuSessao;
    // End of variables declaration//GEN-END:variables
}
