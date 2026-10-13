package pizzariamanoel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    public void cadastrarCliente(Cliente cliente) {
        ConexaoDAO conexaoDao = new ConexaoDAO();
        Connection conn = conexaoDao.conectar();
        
        if (conn == null) {
            return; // Se a conexão falhar, interrompe
        }
        
        String sql = "INSERT INTO cliente (nome, telefone, endereco) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getTelefone());
            pstm.setString(3, cliente.getEndereco());
            
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso no Banco de Dados!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage());
        } finally {
            conexaoDao.desconectar();
        }
    }
}