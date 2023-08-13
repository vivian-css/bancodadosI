package Dados;


import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Vivian
 */
public class ModConexao {
    
   public static Connection conector() {      
        Connection conexao = null;
        
   
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3307/casadeferramentas";
        String user = "root";
        String password = "vc13578400";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (ClassNotFoundException | SQLException e) {
           // System.out.println(e);
            return null;
        }}

    public static Connection conectar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}