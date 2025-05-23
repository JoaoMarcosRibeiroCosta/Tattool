/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aluno
 */
public class Contrato {
    private int numero;
    private Cliente cliente;
    private String dados; // dados a serem escritos pelo usuario

    public Contrato(int numero, Cliente cliente, String dados) {
        this.numero = numero;
        this.cliente = cliente;
        this.dados = dados;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDados() {
        return dados;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }
    
    
}
