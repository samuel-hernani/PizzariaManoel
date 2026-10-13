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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaCliente extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaCliente.class.getName());

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        configurarTela();
    }

    private void configurarTela() {
        setTitle("Pizzaria Manoel - Cadastro de Cliente");
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
        
        // Botão Voltar no Cabeçalho (Interação)
        JButton btnVoltar = new JButton("<- Voltar");
        btnVoltar.setBackground(Color.decode("#B71C1C"));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBounds(650, 25, 100, 35);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaMenu().setVisible(true); // Abre o Menu
                dispose(); // Fecha a tela atual
            }
        });
        cabecalho.add(btnVoltar);

        // Subtítulo
        JLabel subtitulo = new JLabel("Cadastrar Cliente");
        subtitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        subtitulo.setForeground(Color.decode("#212121"));
        subtitulo.setBounds(50, 120, 300, 30);
        add(subtitulo);

        // Formulário: Nome
        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblNome.setBounds(50, 180, 100, 20);
        add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(50, 200, 700, 40);
        add(txtNome);

        // Formulário: Telefone
        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTelefone.setBounds(50, 260, 100, 20);
        add(lblTelefone);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setBounds(50, 280, 700, 40);
        add(txtTelefone);

        // Formulário: Endereço
        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblEndereco.setBounds(50, 340, 100, 20);
        add(lblEndereco);

        JTextField txtEndereco = new JTextField();
        txtEndereco.setBounds(50, 360, 700, 40);
        add(txtEndereco);

        // Botão Salvar (Interação com a classe Cliente da Etapa 1)
        JButton btnSalvar = new JButton("Salvar Cliente");
        btnSalvar.setBackground(Color.decode("#2E7D32"));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnSalvar.setFocusPainted(false);
        btnSalvar.setBounds(600, 440, 150, 50);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recupera os dados digitados
                String nome = txtNome.getText();
                String telefone = txtTelefone.getText();
                String endereco = txtEndereco.getText();
                
                // Validação simples
                if(nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    return;
                }
                
                // Instancia a classe Cliente
                Cliente novoCliente = new Cliente(nome, telefone, endereco);
                
                // Grava no Banco de Dados usando o DAO
                ClienteDAO dao = new ClienteDAO();
                dao.cadastrarCliente(novoCliente);
                
                // Limpa o formulário
                txtNome.setText("");
                txtTelefone.setText("");
                txtEndereco.setText("");
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
        java.awt.EventQueue.invokeLater(() -> new TelaCliente().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
