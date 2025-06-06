/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Aluno
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoSQLServer {
    public static String driverJDBC = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url = "jdbc:sqlserver://localhost:1433;databaseName=teste";
    public static String user = "utfpr";
    public static String senha = "suaSenha";

    public static void main(String[] args) {
        try {
            System.out.println("Carregando o driver...");
            Class.forName(driverJDBC);
            System.out.println("Driver carregado com sucesso");
        } catch (Exception e) {
            System.out.println("Falha no carregamento do driver");
            e.printStackTrace();
        }
        
        try {
            System.out.println("Conectando ao BD...");
            Connection conexao = DriverManager.getConnection(url, user, senha);
            System.out.println("Conexão realizada com sucesso");
        } catch (Exception e) {
            System.out.println("Falha na conexão com o BD");
            e.printStackTrace();
        }
    }
}