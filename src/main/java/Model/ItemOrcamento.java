/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author samue
 */
public class ItemOrcamento {
    int id;
    int produto_id;
    int orcamento_id;

    public ItemOrcamento(int id, int produto_id, int orcamento_id) {
        this.id = id;
        this.produto_id = produto_id;
        this.orcamento_id = orcamento_id;
    }

    public ItemOrcamento(){
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getOrcamento_id() {
        return orcamento_id;
    }

    public void setOrcamento_id(int orcamento_id) {
        this.orcamento_id = orcamento_id;
    }
}
