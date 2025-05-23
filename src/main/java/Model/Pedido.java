/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aluno
 */
public class Pedido {
    private int numero;
    private Cliente cliente;
    private Tatuador tatuador;
    private Produto produto;
    private float maoDeObra;
    private float total;
    private Arte arte;
    private Contrato contrato;

    public Pedido(int numero, Cliente cliente, Tatuador tatuador, Produto produto, float maoDeObra, float total, Arte arte, Contrato contrato) {
        this.numero = numero;
        this.cliente = cliente;
        this.tatuador = tatuador;
        this.produto = produto;
        this.maoDeObra = maoDeObra;
        this.total = total;
        this.arte = arte;
        this.contrato = contrato;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tatuador getTatuador() {
        return tatuador;
    }

    public void setTatuador(Tatuador tatuador) {
        this.tatuador = tatuador;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public Arte getArte() {
        return arte;
    }

    public void setArte(Arte arte) {
        this.arte = arte;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
}
