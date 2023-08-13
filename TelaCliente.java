package TelaLogin;

import java.sql.*;
import Dados.ModConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCliente extends javax.swing.JInternalFrame {
  
    Connection conexao= null;
    PreparedStatement pst=null;
    ResultSet rs= null;  
    
    public TelaCliente() {
        initComponents();
        conexao = ModConexao.conector();
    }
private void Adicionar() {
        
            String sql = "insert into cliente(nomec,enderecoc,telefonec,emailc) values(?,?,?,?)";
        
           try {
               pst=conexao.prepareStatement(sql);
               pst.setString(1,txtCliNome.getText());
               pst.setString(2,txtCliEndereco.getText());
               pst.setString(3,txtCliTel.getText());
               pst.setString(4,txtCliEmail.getText());
                      
     if ((!txtCliNome.getText().isEmpty())&& (!txtCliTel.getText().isEmpty())) {
        
     
         int adicionado = pst.executeUpdate();
         if (adicionado > 0) { JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
            
             txtCliNome.setText(null);
             txtCliEndereco.setText(null);
             txtCliTel.setText(null);
             txtCliEmail.setText(null);
        }
         
     } else {
         JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
     }  
           } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
     }
     }
             

 private void PesquisarCliente () {
 
         String sql = "select * from cliente where nomec like ?";
        try {
           
    pst = conexao.prepareStatement(sql);
    pst.setString(1, txtCliPesquisar.getText() + "%");
    rs = pst.executeQuery();
    
   TblClientes.setModel(DbUtils.resultSetToTableModel(rs));
   
      } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
     }
}

        private void setarCampos() {
        int setar = TblClientes.getSelectedRow();

        txtIdcli.setText(TblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(TblClientes.getModel().getValueAt(setar, 1).toString());
        txtCliEndereco.setText(TblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliTel.setText(TblClientes.getModel().getValueAt(setar, 3).toString());    
        txtCliEmail.setText(TblClientes.getModel().getValueAt(setar, 4).toString());
        btnAdicionar.setEnabled(false);
             
             
        }
             
      private void Alterar() { 
         
        String sql = "update cliente set nomec=?,enderecoc=?,telefonec=?,emailc=? where idcli=?";  
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliTel.getText()); 
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtIdcli.getText());
            
            int setar = TblClientes.getSelectedRow();
            pst.setString(5, (TblClientes.getModel().getValueAt(setar, 0).toString())); 
            
            if ((!txtCliNome.getText().isEmpty())&& (!txtCliTel.getText().isEmpty())) {
        
         
         int adicionado = pst.executeUpdate();
         if (adicionado > 0) {
             JOptionPane.showMessageDialog(null, "Dados Cliente alterado com sucesso");
             
             txtCliNome.setText(null);
             txtCliEndereco.setText(null);
             txtCliTel.setText(null);
             txtCliEmail.setText(null);
             btnAdicionar.setEnabled(true);
         }
         
     } else {
         JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
     } 
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
     }
         
     }      
       private void ApagarCliente() {
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do Cliente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from cliente where idcli=?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1, txtIdcli.getText());
             int apagado = pst.executeUpdate();
      
             if (apagado>0){
                        
             JOptionPane.showMessageDialog(null,"removido com sucesso!!");
             
             
             txtCliNome.setText(null);
             txtCliEndereco.setText(null);
             txtCliTel.setText(null);
             txtCliEmail.setText(null);
             btnAdicionar.setEnabled(true);
             }           
     } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
     
     
     
     }}}      
             
             
             
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliTel = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblClientes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtIdcli = new javax.swing.JTextField();

        setToolTipText("Cliente");

        jLabel1.setText("*  Nome");

        jLabel2.setText("Endereço");

        jLabel3.setText("* Telefone");

        jLabel4.setText("Email");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/adc.png"))); // NOI18N
        btnAdicionar.setToolTipText("");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/edit.png"))); // NOI18N
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jLabel5.setText("* Campo Obrigatórios");

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/iconebusca.png"))); // NOI18N

        TblClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex , int colIndex){

                return false;
            }
        };
        TblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Endereço", "Telefone", "Email"
            }
        ));
        TblClientes.setFocusable(false);
        TblClientes.getTableHeader().setReorderingAllowed(false);
        TblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblClientes);

        jLabel7.setText("Id Cliente");

        txtIdcli.setEnabled(false);
        txtIdcli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdcliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtCliTel)
                                    .addGap(1, 1, 1))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 82, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(195, 195, 195)
                            .addComponent(btnAlterar)
                            .addGap(70, 70, 70)
                            .addComponent(btnRemover))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCliEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                .addComponent(txtCliNome)
                                .addComponent(txtIdcli, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIdcli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtCliNome)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCliTel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCliEmail, txtCliEndereco, txtCliNome, txtCliTel});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
  
        Adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased

        PesquisarCliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void TblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblClientesMouseClicked

        setarCampos();
    }//GEN-LAST:event_TblClientesMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
 
          Alterar();
          
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void txtIdcliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdcliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdcliActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
    
        ApagarCliente();
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblClientes;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliTel;
    private javax.swing.JTextField txtIdcli;
    // End of variables declaration//GEN-END:variables
}
