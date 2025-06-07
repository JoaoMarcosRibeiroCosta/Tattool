/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
/**
 *
 * @author samue
 */
public class ProdutoController extends ConexaoSQLServer{

    @Override
    public String EscolherEntrada() {
        String entrada = "INSERT INTO Produto(descricao, valor) VALUES('Tinta',25)";
        return entrada;
    }
}