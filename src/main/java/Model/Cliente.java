/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aluno
 */
public class Cliente extends Pessoa{
    
    private int arte;

    public Cliente(int arte, int id, int cpf, String nome, String rua,int numero,String bairro,String cidade) {
        super(id, cpf, nome,rua,numero,bairro,cidade);
        this.arte = arte;
    }

    public Cliente() {
    }

    public int getArteId() {
        return arte;
    }

    public void setArteId(int arte) {
        this.arte = arte;
    }

}
