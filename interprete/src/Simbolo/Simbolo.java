/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simbolo;

import java.util.List;

/**
 *
 * @author Lesther
 */
public class Simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;
    private List<Object>valorlista;
    public Simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }
    
    public Simbolo(Tipo tipo, String id, Object valor) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }

    public Simbolo(Tipo tipo, String id, List<Object> valorlista) {
        this.tipo = tipo;
        this.id = id;
        this.valorlista = valorlista;
    }
    
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Object> getValorlista() {
        return valorlista;
    }

    public void setValorlista(List<Object> valorlista) {
        this.valorlista = valorlista;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
}
