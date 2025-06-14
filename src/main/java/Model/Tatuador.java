/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aluno
 */
public class Tatuador extends Pessoa {
    private String estilo; // estilo do tatuador
    private float TotalVenda; // o total de vendas

    public Tatuador(String estilo, float TotalVenda, int id, int cpf, String nome, int endereco) {
        super(id, cpf, nome, endereco);
        this.estilo = estilo;
        this.TotalVenda = TotalVenda;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public float getTotalVenda() {
        return TotalVenda;
    }

    public void setTotalVenda(float TotalVenda) {
        this.TotalVenda = TotalVenda;
    }
  
    
}
