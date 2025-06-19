/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aluno
 */
public class Arte {
    private int id;
    private String imagem;
    private String nome;

    public Arte(int id, String imagem, String nome) {
        this.id = id;
        this.imagem = imagem;
        this.nome = nome;
    }

    public Arte(){
    }
    public int getId() {
        return id;
    }

    public String getImagem() {
        return imagem;
    }
    
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setId(int codigo) {
        this.id = codigo;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
