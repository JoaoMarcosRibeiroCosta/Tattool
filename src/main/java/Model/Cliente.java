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
    
    private Arte arte;
    private Contrato contrato;

    public Cliente(Arte arte, Contrato contrato, int cpf, String nome, Endereco endereco) {
        super(cpf, nome, endereco);
        this.arte = arte;
        this.contrato = contrato;
    }

    public Arte getArte() {
        return arte;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setArte(Arte arte) {
        this.arte = arte;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
}
