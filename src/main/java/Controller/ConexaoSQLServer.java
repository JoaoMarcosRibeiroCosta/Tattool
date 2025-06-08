/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class ConexaoSQLServer {
    public static String driverJDBC = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url = "jdbc:sqlserver://localhost:1433;databaseName=Tattool;encrypt=true;trustServerCertificate=true";
    public static String user = "sa";
    public static String senha = "sam123456";
    static Connection con = null;
    static Statement st = null;

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
    public static Connection conectar() throws Exception {
        Class.forName(driverJDBC);
        return DriverManager.getConnection(url, user, senha);
    }
    
    public void Cadastrar() {
        String insert = EscolherEntrada();
        try{
            Class.forName(driverJDBC);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo dados...");
            st = con.createStatement();
            st.executeUpdate(insert);
            System.out.println("Dados inseridos com sucesso!");
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        ResultSet rs = null;
    }
    
    public abstract String EscolherEntrada();
}