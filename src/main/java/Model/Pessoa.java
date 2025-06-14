/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
/**
 *
 * @author Aluno
 */
public class Pessoa {
    private int id;
    private int cpf;
    private String nome;
    private int endereco;

    public Pessoa(int id,int cpf, String nome, int endereco) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Pessoa() {
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }
    
    
}
