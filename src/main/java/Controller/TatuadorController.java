/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.ConexaoSQLServer.conectar;
import Model.Tatuador;
import Model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author samue
 */
public class TatuadorController extends PessoaController{
    public boolean inserirTatuador(Tatuador tatuador) {
        String sql = "INSERT INTO Tatuador (pessoa_id, estilo, total_vendas) VALUES (?, ?, ?)";
        try (Connection con = conectar(); 
            PreparedStatement stmt = con.prepareStatement(sql)) {
            
            boolean pessoaInserida = super.inserirPessoa(tatuador);
            if (!pessoaInserida) {
                return false;
            }
            stmt.setInt(1,tatuador.getId());
            stmt.setString(2,tatuador.getEstilo());
            stmt.setFloat(3,tatuador.getTotalVenda());
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir tatuador: " + e.getMessage());
            return false;
        }
    }

    public Tatuador buscarTatuador(int id) {
        String sql = "SELECT * FROM Tatuador WHERE pessoa_id = ?";
        try (Connection con = conectar(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            Pessoa pessoa = super.buscarPessoa(id);
            if (pessoa == null) {
                System.out.println("pessoa vazia em tatuador");
                 return null;
            }
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String estilo =(rs.getString("estilo"));
                Float totalVendas = (rs.getFloat("total_vendas"));
                Tatuador tatuador = new Tatuador(estilo, totalVendas, id ,pessoa.getCpf(), pessoa.getNome(), pessoa.getRua(), 
                        pessoa.getNumero(), pessoa.getBairro(),pessoa.getCidade());
                return tatuador;
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Erro ao buscar tatuador: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarTatuador(Tatuador tatuador) {
        String sqlTatuador = "UPDATE Tatuador SET estilo = ?, total_vendas = ? WHERE pessoa_id = ?";
        String sqlPessoa = """
            UPDATE Pessoa SET nome = ?, cpf = ?, rua = ?, numero = ?, bairro = ?, cidade = ?
            WHERE id = ?
        """;
        try (Connection con = conectar(); 
            PreparedStatement stmtTatuador = con.prepareStatement(sqlTatuador);
            PreparedStatement stmtPessoa = con.prepareStatement(sqlPessoa))
        {
            stmtTatuador.setString(1, tatuador.getEstilo());
            stmtTatuador.setFloat(2, tatuador.getTotalVenda());
            stmtTatuador.setInt(3, tatuador.getId());
            stmtTatuador.executeUpdate();
            
            stmtPessoa.setString(1, tatuador.getNome());
            stmtPessoa.setInt(2, tatuador.getCpf());
            stmtPessoa.setString(3, tatuador.getRua());
            stmtPessoa.setInt(4, tatuador.getNumero());
            stmtPessoa.setString(5, tatuador.getBairro());
            stmtPessoa.setString(6, tatuador.getCidade());
            stmtPessoa.setInt(7, tatuador.getId());
            stmtPessoa.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar tatuador: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirTatuador(int id) {
        String sql = "DELETE FROM Tatuador WHERE pessoa_id = ?";
        try (
            Connection con = conectar();     
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir tatuador: " + e.getMessage());
            return false;
        }
    }
    
    
    public DefaultTableModel carregarTabela(String filtro) {
        DefaultTableModel model = new DefaultTableModel();
        String sql ="""
            SELECT t.pessoa_id, p.cpf, p.nome, p.rua, p.numero, p.bairro, p.cidade, t.estilo, t.total_vendas
            FROM Tatuador t
            INNER JOIN Pessoa p ON t.pessoa_id = p.id
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
            System.out.println("Erro ao carregar dados de tatuador: " + e.getMessage());
        }

        return model;
    }
}
