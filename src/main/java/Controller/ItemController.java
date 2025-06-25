/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author samue
 */
import Model.ItemOrcamento;
import Controller.ConexaoSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
public class ItemController extends ConexaoSQLServer{
    public boolean inserirItem(ItemOrcamento item) {
        String sql = "INSERT INTO item_orcamento (produto_id, orcamento_id) VALUES (?, ?)";
        try (Connection con = conectar(); 
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, item.getProduto_id());
            stmt.setInt(2, item.getOrcamento_id());   
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir ItemOrcamento: " + e.getMessage());
            return false;
        }
    }

    public ItemOrcamento buscarItem(int id) {
        String sql = "SELECT * FROM item_orcamento WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ItemOrcamento item = new ItemOrcamento();
                item.setId(rs.getInt("id"));
                item.setOrcamento_id(rs.getInt("orcamento_id"));
                item.setProduto_id(rs.getInt("produto_id"));
                return item;
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Erro ao buscar ItemOrcamento: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarItem(ItemOrcamento item) {
        String sql = "UPDATE item_orcamento SET produto_id = ?, orcamento_id = ? WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, item.getProduto_id());
            stmt.setInt(2, item.getOrcamento_id());
            stmt.setInt(3, item.getId());
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar ItemOrcamento: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirItem(int id) {
        String sql = "DELETE FROM item_orcamento WHERE id = ?";
        try (
            Connection con = conectar();     
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir ItemOrcamento: " + e.getMessage());
            return false;
        }
    }
    
    
    public DefaultTableModel carregarTabela(String filtro) {
        DefaultTableModel model = new DefaultTableModel();
        String sql = """
                    SELECT 
                    i.id As ID,
                    i.produto_id AS ID Produto,
                    p.nome AS Produto,
                    p.valor AS Valor,
                    i.quantidade As Quantidade
                    FROM ItemOrcamento id
                    INNER JOIN Produto p ON i.produto_id = p.id
                            """+filtro;
        
        
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
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

        return model;
    }

}
