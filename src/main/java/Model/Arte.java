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
    private int codigo;
    private String imagem; //url

    public Arte(int codigo, String imagem) {
        this.codigo = codigo;
        this.imagem = imagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
