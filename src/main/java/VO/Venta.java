/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author LUIS
 */
public class Venta {

    private int id_venta;
    private double valor;
    private List<Item_vent> item_vents;
    private Cliente cliente;
    private Vendedor vendedor;
    private Caja caja;
    private String data;

    public List<Item_vent> getItem_vents() {
        return item_vents;
    }

    public void setItem_vents(List<Item_vent> item_vents) {
        this.item_vents = item_vents;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
  
    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Venta() {
    }

    public Venta(int id_venta, double valor) {
        this.id_venta = id_venta;
        this.valor = valor;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

   

}
