/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aluno
 */
public class Orcamento {
    private int id;
    private int cliente_id;
    private int tatuador_id;
    private int produto_id;
    private int contrato_id;
    private float maoDeObra;
    private float total;
    private int arte_id;

    public Orcamento(int id, int cliente_id, int tatuador_id, int produto_id, float maoDeObra, float total,int contrato_id ,int arte_id) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.tatuador_id = tatuador_id;
        this.produto_id = produto_id;
        this.maoDeObra = maoDeObra;
        this.total = total;
        this.contrato_id = contrato_id;
        this.arte_id = arte_id;
    }   

    public Orcamento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente_id;
    }

    public void setCliente(int cliente) {
        this.cliente_id = cliente;
    }

    public int getTatuador() {
        return tatuador_id;
    }

    public void setTatuador(int tatuador) {
        this.tatuador_id = tatuador;
    }

    public int getProduto() {
        return produto_id;
    }

    public void setProduto(int produto) {
        this.produto_id = produto;
    }

    public float getMaoDeObra() {
        return maoDeObra;
    }

    public void setMaoDeObra(float maoDeObra) {
        this.maoDeObra = maoDeObra;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getArte() {
        return arte_id;
    }

    public void setArte(int arte) {
        this.arte_id = arte;
    }
    public int getContratoId() {
        return contrato_id;
    }

    public void setContratoId(int contrato_id) {
        this.contrato_id = contrato_id;
    }
}
