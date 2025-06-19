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
    private String estilo;
    private float TotalVenda;

    public Tatuador(String estilo, float TotalVenda, int id, int cpf, String nome, String rua,int numero,String bairro,String cidade) {
        super(id, cpf, nome,rua,numero,bairro,cidade);
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
