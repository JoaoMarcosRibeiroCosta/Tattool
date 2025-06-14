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
    private String imagem; //url

    public Arte(int id, String imagem) {
        this.id = id;
        this.imagem = imagem;
    }

    public Arte(){
    }
    public int getId() {
        return id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setId(int codigo) {
        this.id = codigo;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
