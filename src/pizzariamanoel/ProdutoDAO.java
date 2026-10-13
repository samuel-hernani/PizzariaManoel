package pizzariamanoel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    public void cadastrarProduto(Produto produto) {
        ConexaoDAO conexaoDao = new ConexaoDAO();
        Connection conn = conexaoDao.conectar();
        
        if (conn == null) {
            return;
        }
        
        String sql = "INSERT INTO produto (descricao, preco, tipo, detalhes) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, produto.getDescricao());
            pstm.setDouble(2, produto.getPreco());
            
            // Identifica se é Pizza ou Bebida para salvar o tipo corretamente
            if (produto instanceof Pizza) {
                pstm.setString(3, "Pizza");
                pstm.setString(4, ((Pizza) produto).getIngredientes());
            } else {
                pstm.setString(3, "Bebida");
                pstm.setString(4, ((Bebida) produto).getVolume());
            }
            
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso no Banco de Dados!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
        } finally {
            conexaoDao.desconectar();
        }
    }
}