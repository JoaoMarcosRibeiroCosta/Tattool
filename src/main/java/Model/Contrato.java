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
    private int id;
    private int cliente_id;
    private String dados;

    public Contrato(int id, int cliente_id, String dados) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.dados = dados;
    }

    public Contrato() {
    }

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return cliente_id;
    }

    public String getDados() {
        return dados;
    }

    public void setId(int numero) {
        this.id = id;
    }

    public void setClienteId(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }
    
    
}
