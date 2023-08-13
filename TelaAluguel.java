package TelaLogin;

import java.sql.*;
import Dados.ModConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class TelaAluguel extends javax.swing.JInternalFrame {

    Connection conexao;
    PreparedStatement pst;
    ResultSet rs;
    
    private String tipo;
    
    public TelaAluguel() {
        initComponents();
        conexao = ModConexao.conector();
    }

    
    private void pesquisarCliente() {
        String sql = "select * from cliente where nomec like ?";
        try {
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClipesq.getText() + "%");
            rs = pst.executeQuery();
            
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
       
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    
    private void setarCampos() {
        int setar = tblClientes.getSelectedRow();

        txtIDC.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
 }
    
    private void AlugarFerramentas() {
        
        String sql = "insert into aluguelferramentas(tipo,situacao,modelof,numdeserie,Vendedor,Ferramenta,valor,idcli) values(?,?,?,?,?,?,?,?)";
        
        try {
            
             pst = conexao.prepareStatement(sql);
               pst.setString(1,tipo);
               pst.setString(2, cboAlugSit.getSelectedItem().toString());
               pst.setString(3,txtModFer.getText());
               pst.setString(4,txtNumSerie.getText());
               pst.setString(5,txtVend.getText());
               pst.setString(6,txtNomFer.getText());
               pst.setString(7,txtVlr.getText().replace(",","."));
               pst.setString(8,txtIDC.getText());
               
               if ((txtIDC.getText().isEmpty()) || (txtModFer.getText().isEmpty()) || (txtNomFer.getText().isEmpty()) || cboAlugSit.getSelectedItem().equals(" ")) {
                   JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
               } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                   
                    JOptionPane.showMessageDialog(null, "Alugado com sucesso");
                
             txtIDC.setText(null);
             txtModFer.setText(null);
             txtNumSerie.setText(null);
             txtVend.setText(null);
             txtNomFer.setText(null);
             txtVlr.setText(null);
             
                }
                }
             
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
  
    }
    private void pesquisarAluguel() {
        String numAlug = JOptionPane.showInputDialog("Código Aluguel");
        String sql = "select * from aluguelferramentas where IDferramentas= " + numAlug;
                
        try {
            pst=conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {  
                
                txtFer.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                
                String rbtTipo=rs.getString(3);
                if (rbtTipo.equals("AluguelDeFerramentas")) {
                    rbtAluguel.setSelected(true);
                    tipo="AluguelDeFerramentas";  
                    
                } else {
                    
                    rbtOrc.setSelected(true);
                    tipo="Orçamento";
                }
                
                cboAlugSit.setSelectedItem(rs.getString(4));
                txtModFer.setText(rs.getString(5));
                txtNumSerie.setText(rs.getString(6));
                txtVlr.setText(rs.getString(7));
                txtIDC.setText(rs.getString(8));
                txtNomFer.setText(rs.getString(9));
                txtVend.setText(rs.getString(10));
                
                btnAdcAlug.setEnabled(false);
                txtClipesq.setEnabled(false);
                tblClientes.setVisible(false);
                
                   
            } else {  
                JOptionPane.showMessageDialog(null, "Aluguel Não Cadastrado!");
            }
                       
        } catch (SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "OS Inválida");

            } catch (SQLException e2) {
                JOptionPane.showMessageDialog(null, e2);
            }
        }
    
    
     private void AlterarAluguel() {
           
        String sql = "update aluguelferramentas set tipo=?,situacao=?,modelof=?,numdeserie=?,vendedor=?,Ferramenta=?,valor=? where IDferramentas=?";  
        
     try {
            
             pst = conexao.prepareStatement(sql);
               pst.setString(1,tipo);
               pst.setString(2, cboAlugSit.getSelectedItem().toString());
               pst.setString(3,txtModFer.getText());
               pst.setString(4,txtNumSerie.getText());
               pst.setString(5,txtVend.getText());
               pst.setString(6,txtNomFer.getText());
               pst.setString(7,txtVlr.getText().replace(",","."));
               pst.setString(8,txtFer.getText());
               
               if ((txtIDC.getText().isEmpty()) || (txtModFer.getText().isEmpty()) || (txtNomFer.getText().isEmpty()) || cboAlugSit.getSelectedItem().equals(" ")) {
                   JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
               } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                   
                    JOptionPane.showMessageDialog(null, "Aluguel alterado com sucesso");
                
             txtFer.setText(null); 
             txtData.setText(null);
             txtIDC.setText(null);
             txtModFer.setText(null);
             txtNumSerie.setText(null);
             txtVend.setText(null);
             txtNomFer.setText(null);
             txtVlr.setText(null);
             
             btnAdcAlug.setEnabled(true);
             txtClipesq.setEnabled(true);
             tblClientes.setVisible(true);
             
             
                }
                }
             
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
  
    }
    
    
    
     
     private void ExcluirAluguel(){
     int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir a locação?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from aluguelferramentas where IDferramentas=?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1, txtFer.getText());
             int apagado = pst.executeUpdate();
      
             if (apagado>0){
                        
             JOptionPane.showMessageDialog(null,"removido com sucesso!!");
                   
             txtFer.setText(null); 
             txtData.setText(null);
             txtIDC.setText(null);
             txtModFer.setText(null);
             txtNumSerie.setText(null);
             txtVend.setText(null);
             txtNomFer.setText(null);
             txtVlr.setText(null);
             
             btnAdcAlug.setEnabled(true);
             txtClipesq.setEnabled(true);
             tblClientes.setVisible(true);

             }           
     } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
     
     }}}
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFer = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        rbtAluguel = new javax.swing.JRadioButton();
        rbtOrc = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cboAlugSit = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtClipesq = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIDC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtModFer = new javax.swing.JTextField();
        txtNumSerie = new javax.swing.JTextField();
        txtNomFer = new javax.swing.JTextField();
        txtVlr = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtVend = new javax.swing.JTextField();
        btnAdcAlug = new javax.swing.JButton();
        btnPesqAlug = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnExclui = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("ALUGUEL");
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

        jLabel1.setText("*Código Ferramenta");

        txtFer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFerActionPerformed(evt);
            }
        });

        jLabel2.setText("Data do Aluguel");

        buttonGroup1.add(rbtAluguel);
        rbtAluguel.setText("Alugar Ferramenta");
        rbtAluguel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAluguelActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOrc);
        rbtOrc.setText("Orçamento");
        rbtOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrcActionPerformed(evt);
            }
        });

        jLabel3.setText("Situação ");

        cboAlugSit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disponível", "Alugada", "Em Manutenção", "Orçamento" }));
        cboAlugSit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAlugSitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbtAluguel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboAlugSit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbtOrc)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFer, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtAluguel)
                    .addComponent(rbtOrc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboAlugSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/xmag_search_find_export_locate_5984.png"))); // NOI18N

        txtClipesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClipesqActionPerformed(evt);
            }
        });

        jLabel5.setText("*ID");

        txtIDC.setEditable(false);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtClipesq, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIDC, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 87, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtClipesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jLabel6.setText("*Modelo Da Ferramenta");

        jLabel7.setText("Número De Série");

        jLabel8.setText("Ferramenta");

        txtNumSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumSerieActionPerformed(evt);
            }
        });

        txtVlr.setText("0");

        jLabel9.setText("Valor");

        jLabel14.setText("Vendedor");

        btnAdcAlug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/adcf.png"))); // NOI18N
        btnAdcAlug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdcAlugActionPerformed(evt);
            }
        });

        btnPesqAlug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscaf.png"))); // NOI18N
        btnPesqAlug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesqAlugActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editaf.png"))); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnExclui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletef.png"))); // NOI18N
        btnExclui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel8)
                    .addComponent(jLabel14)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVlr, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModFer, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(txtNumSerie)
                    .addComponent(txtVend)
                    .addComponent(txtNomFer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdcAlug, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExclui, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPesqAlug, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(txtModFer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtVend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtNomFer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtVlr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnEdit)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAdcAlug)
                                .addComponent(btnPesqAlug))
                            .addGap(26, 26, 26)
                            .addComponent(btnExclui))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFerActionPerformed

    private void rbtAluguelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAluguelActionPerformed
     
        tipo="AluguelDeFerramentas";
    }//GEN-LAST:event_rbtAluguelActionPerformed

    private void rbtOrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOrcActionPerformed
    
        tipo="Orçamento";
    }//GEN-LAST:event_rbtOrcActionPerformed

    private void cboAlugSitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAlugSitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAlugSitActionPerformed

    private void txtNumSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumSerieActionPerformed

    private void txtClipesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClipesqActionPerformed
         
       pesquisarCliente();
    }//GEN-LAST:event_txtClipesqActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
    
        setarCampos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        rbtOrc.setSelected(true);
        tipo="Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnAdcAlugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdcAlugActionPerformed
       
        AlugarFerramentas();
    }//GEN-LAST:event_btnAdcAlugActionPerformed

    private void btnPesqAlugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesqAlugActionPerformed
            
        pesquisarAluguel();      
    }//GEN-LAST:event_btnPesqAlugActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        AlterarAluguel();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnExcluiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluiActionPerformed
        
        ExcluirAluguel();
    }//GEN-LAST:event_btnExcluiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdcAlug;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExclui;
    private javax.swing.JButton btnPesqAlug;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboAlugSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtAluguel;
    private javax.swing.JRadioButton rbtOrc;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtClipesq;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtFer;
    private javax.swing.JTextField txtIDC;
    private javax.swing.JTextField txtModFer;
    private javax.swing.JTextField txtNomFer;
    private javax.swing.JTextField txtNumSerie;
    private javax.swing.JTextField txtVend;
    private javax.swing.JTextField txtVlr;
    // End of variables declaration//GEN-END:variables
}
