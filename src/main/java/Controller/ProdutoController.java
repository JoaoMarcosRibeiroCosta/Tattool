/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Produto;
import Controller.ConexaoSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class ProdutoController  extends ConexaoSQLServer{

    public boolean inserirProduto(Produto produto) {
        String sql = "INSERT INTO Produto (descricao, valor, quantidade) VALUES (?, ?, ?)";
        try (Connection con = conectar(); 
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getValor());
            stmt.setInt(3, produto.getQuantidade());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }

    public Produto buscarProduto(int id) {
        String sql = "SELECT * FROM Produto WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("estoque"));
                produto.setValor(rs.getFloat("valor"));
                return produto;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarProduto(Produto produto) {
        String sql = "UPDATE Produto SET descricao = ?, valor = ?, estoque = ? WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getValor());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirProduto(int id) {
        String sql = "DELETE FROM Produto WHERE id = ?";
        try (
            Connection con = conectar();     
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
            return false;
        }
    }
    
    
    public DefaultTableModel carregarTabela(String filtro) {
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT * FROM Produto"+filtro;
        try (
            Connection con = conectar();     
            PreparedStatement stmt = con.prepareStatement(sql)) {
             
            ResultSet rs = stmt.executeQuery(sql);

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
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

        return model;
    }

}