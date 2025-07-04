/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Aluno
 */

import static Controller.ConexaoSQLServer.conectar;
import Model.Cliente;
import Model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class ClienteController extends PessoaController{
    public boolean inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (pessoa_id, arte_anterior_id) VALUES (?, ?)";
        try (Connection con = conectar(); 
            PreparedStatement stmt = con.prepareStatement(sql)) {
            
            boolean pessoaInserida = super.inserirPessoa(cliente);
            if (!pessoaInserida) {
                return false;
            }
            stmt.setInt(1,cliente.getId());
            
            if (cliente.getArteId() != 0) {
                stmt.setInt(2, cliente.getArteId());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
            return false;
        }
    }

    public Cliente buscarCliente(int id) {
        String sql = "SELECT * FROM Cliente WHERE pessoa_id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            Pessoa pessoa = super.buscarPessoa(id);
            if (pessoa == null) {
                 return null;
            }
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int arte =(rs.getInt("arte_anterior_id"));
                Cliente cliente = new Cliente(arte, id ,pessoa.getCpf(), pessoa.getNome(), pessoa.getRua(), pessoa.getNumero(), pessoa.getBairro(),pessoa.getCidade());
                return cliente;
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarCliente(Cliente cliente) {
        String sqlCliente = "UPDATE Cliente SET arte_anterior_id = ? WHERE pessoa_id = ?";
        String sqlPessoa = """
            UPDATE Pessoa SET nome = ?, cpf = ?, rua = ?, numero = ?, bairro = ?, cidade = ?
            WHERE id = ?
        """;

        try (Connection con = conectar();
             PreparedStatement stmtCliente = con.prepareStatement(sqlCliente);
             PreparedStatement stmtPessoa = con.prepareStatement(sqlPessoa)) {

            // Atualiza Cliente
            stmtCliente.setInt(1, cliente.getArteId());
            stmtCliente.setInt(2, cliente.getId());
            stmtCliente.executeUpdate();

            // Atualiza Pessoa
            stmtPessoa.setString(1, cliente.getNome());
            stmtPessoa.setInt(2, cliente.getCpf());
            stmtPessoa.setString(3, cliente.getRua());
            stmtPessoa.setInt(4, cliente.getNumero());
            stmtPessoa.setString(5, cliente.getBairro());
            stmtPessoa.setString(6, cliente.getCidade());
            stmtPessoa.setInt(7, cliente.getId());
            stmtPessoa.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirCliente(int id) {
        String sql = "DELETE FROM Cliente WHERE pessoa_id = ?";
        try (
            Connection con = conectar();     
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            return false;
        }
    }
    
    
    public DefaultTableModel carregarTabela(String filtro) {
        DefaultTableModel model = new DefaultTableModel();
        String sql = """
            SELECT c.pessoa_id, p.cpf, p.nome, p.rua, p.numero, p.bairro, p.cidade 
            FROM Cliente c
            INNER JOIN Pessoa p ON c.pessoa_id = p.id
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
            System.out.println("Erro ao carregar dados de cliente: " + e.getMessage());
        }

        return model;
    }
}