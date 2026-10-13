/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pizzariamanoel;

/**
 *
 * @author samhs
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TelaProduto extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaProduto.class.getName());

    /**
     * Creates new form TelaProduto
     */
    public TelaProduto() {
        initComponents();
        configurarTela();
    }
    
private void configurarTela() {
        setTitle("Pizzaria Manoel - Cadastro de Produto");
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.decode("#F5F5F5"));

        // Cabeçalho e Título
        JPanel cabecalho = new JPanel();
        cabecalho.setBackground(Color.decode("#B71C1C"));
        cabecalho.setBounds(0, 0, 800, 80);
        cabecalho.setLayout(null);
        add(cabecalho);

        JLabel tituloCabecalho = new JLabel("Pizzaria Manoel");
        tituloCabecalho.setForeground(Color.WHITE);
        tituloCabecalho.setFont(new Font("SansSerif", Font.BOLD, 28));
        tituloCabecalho.setBounds(30, 20, 300, 40);
        cabecalho.add(tituloCabecalho);
        
        // Botão Voltar (Interação)
        JButton btnVoltar = new JButton("<- Voltar");
        btnVoltar.setBackground(Color.decode("#B71C1C"));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBounds(650, 25, 100, 35);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaMenu().setVisible(true);
                dispose();
            }
        });
        cabecalho.add(btnVoltar);

        // Subtítulo
        JLabel subtitulo = new JLabel("Cadastrar Produto");
        subtitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        subtitulo.setForeground(Color.decode("#212121"));
        subtitulo.setBounds(50, 100, 300, 30);
        add(subtitulo);

        // Formulário: Descrição
        JLabel lblDesc = new JLabel("Descrição (Ex: Pizza de Calabresa)");
        lblDesc.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDesc.setBounds(50, 150, 400, 20);
        add(lblDesc);

        JTextField txtDesc = new JTextField();
        txtDesc.setBounds(50, 170, 500, 40);
        add(txtDesc);

        // Formulário: Preço
        JLabel lblPreco = new JLabel("Preço (R$)");
        lblPreco.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPreco.setBounds(600, 150, 100, 20);
        add(lblPreco);

        JTextField txtPreco = new JTextField();
        txtPreco.setBounds(600, 170, 150, 40);
        add(txtPreco);

        // Formulário: Tipo (Radio Buttons)
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTipo.setBounds(50, 230, 100, 20);
        add(lblTipo);

        JRadioButton rbPizza = new JRadioButton("Pizza", true);
        rbPizza.setBackground(Color.decode("#F5F5F5"));
        rbPizza.setBounds(50, 250, 100, 30);
        
        JRadioButton rbBebida = new JRadioButton("Bebida");
        rbBebida.setBackground(Color.decode("#F5F5F5"));
        rbBebida.setBounds(150, 250, 100, 30);

        // Agrupar os Radio Buttons para permitir apenas uma seleção
        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(rbPizza);
        grupoTipo.add(rbBebida);
        add(rbPizza);
        add(rbBebida);

        // Formulário: Detalhes
        JLabel lblDetalhes = new JLabel("Detalhes (Ingredientes para Pizza ou Volume para Bebida)");
        lblDetalhes.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDetalhes.setBounds(50, 300, 500, 20);
        add(lblDetalhes);

        JTextField txtDetalhes = new JTextField();
        txtDetalhes.setBounds(50, 320, 700, 40);
        add(txtDetalhes);

        // Botão Cadastrar Produto
        JButton btnSalvar = new JButton("Cadastrar Produto");
        btnSalvar.setBackground(Color.decode("#2E7D32"));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnSalvar.setFocusPainted(false);
        btnSalvar.setBounds(550, 400, 200, 50);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = txtDesc.getText();
                String detalhes = txtDetalhes.getText();
                String precoStr = txtPreco.getText();
                
                if(descricao.isEmpty() || detalhes.isEmpty() || precoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    return;
                }

                try {
                    double preco = Double.parseDouble(precoStr.replace(",", "."));
                    Produto novoProduto;

                    // Aplica a Herança baseada na seleção do Radio Button
                    if (rbPizza.isSelected()) {
                        novoProduto = new Pizza(descricao, preco, detalhes, "Grande"); 
                    } else {
                        novoProduto = new Bebida(descricao, preco, detalhes);
                    }
                    
                    // Grava no Banco de Dados usando o DAO (Etapa 4)
                    ProdutoDAO dao = new ProdutoDAO();
                    dao.cadastrarProduto(novoProduto);

                    // Limpa os campos
                    txtDesc.setText("");
                    txtPreco.setText("");
                    txtDetalhes.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Insira um valor numérico válido para o preço (ex: 45.00)");
                }
            }
        });
        add(btnSalvar);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaProduto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
