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
        String descricao = "teste";
        double valor = 10.99;
        String entrada = String.format("INSERT INTO Produto(descricao, valor) VALUES('%s', %.2f)", descricao, valor);
        return entrada;
    }
}