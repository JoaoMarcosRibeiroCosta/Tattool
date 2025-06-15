/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author samue
 */

import Model.Orcamento;
import Controller.ConexaoSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
public class OrcamentoController extends ConexaoSQLServer{

    public boolean inserirOrcamento(Orcamento orcamento) {
        String sql = "INSERT INTO Orcamento (cliente_id, tatuador_id, produto_id, arte_id, contrato_id, mao_de_obra, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conectar(); 
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, orcamento.getCliente());
            stmt.setInt(2, orcamento.getTatuador());   
            stmt.setInt(3, orcamento.getProduto());   
            stmt.setInt(4, orcamento.getArte());
            stmt.setInt(5, orcamento.getContratoId());   
            stmt.setFloat(6, orcamento.getMaoDeObra());
            stmt.setFloat(7, orcamento.getTotal());
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir Orcamento: " + e.getMessage());
            return false;
        }
    }

    public Orcamento buscarOrcamento(int id) {
        String sql = "SELECT * FROM Orcamento WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Orcamento orcamento = new Orcamento();
                orcamento.setId(rs.getInt("id"));
                orcamento.setCliente(rs.getInt("cliente_id"));
                orcamento.setTatuador(rs.getInt("tatuador_id"));
                orcamento.setProduto(rs.getInt("produto_id"));
                orcamento.setArte(rs.getInt("arte_id"));
                orcamento.setContratoId(rs.getInt("contrato_id"));
                orcamento.setMaoDeObra(rs.getFloat("mao_de_obra"));
                orcamento.setTotal(rs.getFloat("total"));
                return orcamento;
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Erro ao buscar Orcamento: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarOrcamento(Orcamento orcamento) {
        String sql = "UPDATE Orcamento SET cliente_id = ?, tatuador_id = ?, produto_id = ?, arte_id = ?, contrato_id = ?, mao_de_obra = ?, total = ? WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, orcamento.getCliente());
            stmt.setInt(2, orcamento.getTatuador());
            stmt.setInt(3, orcamento.getProduto());
            stmt.setInt(4, orcamento.getArte());
            stmt.setInt(5, orcamento.getContratoId());
            stmt.setFloat(6, orcamento.getMaoDeObra());
            stmt.setFloat(7, orcamento.getTotal());
            stmt.setInt(8, orcamento.getId());
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar Orcamento: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirOrcamento(int id) {
        String sql = "DELETE FROM Orcamento WHERE id = ?";
        try (
            Connection con = conectar();     
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir Orcamento: " + e.getMessage());
            return false;
        }
    }
    
    
    public DefaultTableModel carregarTabela(String filtro) {
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT * FROM Orcamento"+filtro;
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