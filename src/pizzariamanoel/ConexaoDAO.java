package pizzariamanoel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {
    
    private Connection conn;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/pizzariamanoel";
            String usuario = "root"; 
            String senha = "02143022"; 

            conn = DriverManager.getConnection(url, usuario, senha);
            return conn;

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "O Driver JDBC não foi encontrado: " + ex.getMessage());
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados: " + ex.getMessage());
            return null;
        }
    }
    
    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
        }
    }
}