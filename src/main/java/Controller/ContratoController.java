/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author samue
 */

import Model.Contrato;
import Controller.ConexaoSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class ContratoController extends ConexaoSQLServer{
    
public boolean inserirContrato(Contrato contrato) {
        String sql = "INSERT INTO Contrato (cliente_id, dados) VALUES (?, ?)";
        try (Connection con = conectar(); 
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, contrato.getClienteId());
            stmt.setString(2, contrato.getDados());   
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir Contrato: " + e.getMessage());
            return false;
        }
    }

    public Contrato buscarContrato(int id) {
        String sql = "SELECT * FROM Contrato WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Contrato contrato = new Contrato();
                contrato.setId(rs.getInt("id"));
                contrato.setClienteId(rs.getInt("cliente_id"));
                contrato.setDados(rs.getString("dados"));
                return contrato;
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Erro ao buscar Contrato: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarContrato(Contrato contrato) {
        String sql = "UPDATE Contrato SET cliente_id = ?, dados = ? WHERE id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, contrato.getClienteId());
            stmt.setString(2, contrato.getDados());
            stmt.setInt(3, contrato.getId());
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar Contrato: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirContrato(int id) {
        String sql = "DELETE FROM Contrato WHERE id = ?";
        try (
            Connection con = conectar();     
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir Contrato: " + e.getMessage());
            return false;
        }
    }
    
    
    public DefaultTableModel carregarTabela(String filtro) {
        DefaultTableModel model = new DefaultTableModel();
        String sql = """
            SELECT *
            FROM Contrato con
            
        """ + filtro;

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
