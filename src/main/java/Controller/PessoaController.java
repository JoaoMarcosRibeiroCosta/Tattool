/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.ConexaoSQLServer.conectar;
import Model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author samue
 */

public class PessoaController extends ConexaoSQLServer{

    public boolean inserirPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoa (cpf, nome, rua, numero, bairro, cidade) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = conectar(); 
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pessoa.getCpf());
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getRua());
            stmt.setInt(4, pessoa.getNumero());
            stmt.setString(5, pessoa.getBairro());
            stmt.setString(6, pessoa.getCidade());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int pessoaId = rs.getInt(1);
                pessoa.setId(pessoaId);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao inserir pessoa: " + e.getMessage());
            return false;
        }
    }

    public Pessoa buscarPessoa(int id) {
        String sql = "SELECT * FROM Pessoa WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setCpf(rs.getInt("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setRua(rs.getString("rua"));
                pessoa.setNumero(rs.getInt("numero"));
                pessoa.setBairro(rs.getString("bairro"));
                pessoa.setCidade(rs.getString("cidade"));
                return pessoa;
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarPessoa(Pessoa pessoa) {
        String sql = "UPDATE Pessoa SET nome = ?,cpf = ?, rua = ?, numero = ?, bairro = ?, cidade = ? WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getCpf());
            stmt.setString(3, pessoa.getRua());
            stmt.setInt(4, pessoa.getNumero());
            stmt.setString(5,pessoa.getBairro());
            stmt.setString(6,pessoa.getCidade());
            stmt.setInt(7, pessoa.getId());
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar pessoa: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirPessoa(int id) {
        String sql = "DELETE FROM Pessoa WHERE id = ?";
        try (
            Connection con = conectar();     
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir pessoa: " + e.getMessage());
            return false;
        }
    }
    
    
    public DefaultTableModel carregarTabela(String filtro) {
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT * FROM Pessoa "+filtro;
        try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()){

            ResultSetMetaData rsmd = rs.getMetaData();
            int colunas = rsmd.getColumnCount();

            for (int i = 1; i <= colunas; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            while (rs.next()) {
                Object[] linha = new Object[colunas];
                for (int i = 1; i <= colunas; i++) {
                    linha[i - 1] = rs.getObject(i);
                }
                model.addRow(linha);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Erro ao carregar dados de pesssoas: " + e.getMessage());
        }

        return model;
    }

}