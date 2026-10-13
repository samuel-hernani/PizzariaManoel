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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaPedido extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPedido.class.getName());

    /**
     * Creates new form TelaPedido
     */
    public TelaPedido() {
        initComponents();
        configurarTela();
    }
    
private void configurarTela() {
        setTitle("Pizzaria Manoel - Novo Pedido");
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.decode("#F5F5F5"));

        // Cabeçalho e Botão Voltar
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

        JLabel subtitulo = new JLabel("Novo Pedido");
        subtitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        subtitulo.setForeground(Color.decode("#212121"));
        subtitulo.setBounds(50, 100, 300, 30);
        add(subtitulo);

        // JComboBox de Cliente (Buscando dados reais do Banco de Dados)
        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblCliente.setBounds(50, 140, 100, 20);
        add(lblCliente);

        JComboBox<String> cbCliente = new JComboBox<>();
        cbCliente.setBounds(50, 160, 700, 35);
        cbCliente.setBackground(Color.WHITE);
        cbCliente.addItem("Selecione o Cliente...");
        carregarClientesDoBanco(cbCliente);
        add(cbCliente);

        // JComboBox de Produto (Buscando dados reais do Banco de Dados)
        JLabel lblProduto = new JLabel("Produto");
        lblProduto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblProduto.setBounds(50, 210, 100, 20);
        add(lblProduto);

        JComboBox<String> cbProduto = new JComboBox<>();
        cbProduto.setBounds(50, 230, 500, 35);
        cbProduto.setBackground(Color.WHITE);
        cbProduto.addItem("Selecione o Produto...");
        carregarProdutosDoBanco(cbProduto);
        add(cbProduto);

        // Botão Adicionar Item
        JButton btnAdd = new JButton("+ Adicionar Item");
        btnAdd.setBackground(Color.decode("#2E7D32"));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(570, 230, 180, 35);
        add(btnAdd);

        // Área de Texto para listar os itens adicionados
        JLabel lblItens = new JLabel("Itens do pedido");
        lblItens.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblItens.setBounds(50, 280, 200, 20);
        add(lblItens);

        JTextArea txtItens = new JTextArea();
        txtItens.setEditable(false); // O utilizador não pode digitar aqui, apenas ver
        txtItens.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(txtItens);
        scrollPane.setBounds(50, 310, 700, 120);
        add(scrollPane);

        // Rótulo de Total e Botão de Finalizar
        JLabel lblTotal = new JLabel("Total: R$ 0.00");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTotal.setForeground(Color.decode("#212121"));
        lblTotal.setBounds(50, 450, 300, 30);
        add(lblTotal);

        JButton btnFinalizar = new JButton("Finalizar Pedido");
        btnFinalizar.setBackground(Color.decode("#2E7D32"));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setBounds(550, 440, 200, 50);
        add(btnFinalizar);

        // LÓGICA DE INTERAÇÃO (Simulando adição à lista do Pedido)
        final double[] valorTotal = {0.0}; // Array para guardar o valor fora do listener

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbProduto.getSelectedIndex() > 0) {
                    String produtoSelecionado = cbProduto.getSelectedItem().toString();
                    txtItens.append("- " + produtoSelecionado + "\n");
                    
                    // Lê o preço de forma dinâmica direto da string do produto do banco (ex: "... - R$ 45.00")
                    try {
                        String precoPart = produtoSelecionado.split("R\\$")[1].trim();
                        double precoItem = Double.parseDouble(precoPart.replace(",", "."));
                        valorTotal[0] += precoItem;
                        lblTotal.setText(String.format("Total: R$ %.2f", valorTotal[0]));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao calcular o preço do item.");
                    }
                }
            }
        });
        
        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbCliente.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione o Cliente antes de fechar o pedido!");
                    return;
                }
                if(valorTotal[0] == 0) {
                    JOptionPane.showMessageDialog(null, "Adicione pelo menos um item ao pedido!");
                    return;
                }
                
                JOptionPane.showMessageDialog(null, "Pedido do cliente " + cbCliente.getSelectedItem() + " finalizado com sucesso!\nValor Total: R$ " + valorTotal[0]);
                
                // Limpar tela para novo pedido
                cbCliente.setSelectedIndex(0);
                cbProduto.setSelectedIndex(0);
                txtItens.setText("");
                valorTotal[0] = 0.0;
                lblTotal.setText("Total: R$ 0.00");
            }
        });
    }

    // Método para buscar os clientes reais do MySQL
    private void carregarClientesDoBanco(JComboBox<String> combo) {
        ConexaoDAO conexaoDao = new ConexaoDAO();
        Connection conn = conexaoDao.conectar();
        if (conn == null) return;

        String sql = "SELECT nome, telefone FROM cliente";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                combo.addItem(nome + " - " + telefone);
            }
            rs.close();
            pstm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar clientes: " + ex.getMessage());
        } finally {
            conexaoDao.desconectar();
        }
    }

    // Método para buscar os produtos reais do MySQL
    private void carregarProdutosDoBanco(JComboBox<String> combo) {
        ConexaoDAO conexaoDao = new ConexaoDAO();
        Connection conn = conexaoDao.conectar();
        if (conn == null) return;

        String sql = "SELECT descricao, preco FROM produto";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
                combo.addItem(descricao + " - R$ " + String.format("%.2f", preco));
            }
            rs.close();
            pstm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + ex.getMessage());
        } finally {
            conexaoDao.desconectar();
        }
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
        java.awt.EventQueue.invokeLater(() -> new TelaPedido().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
