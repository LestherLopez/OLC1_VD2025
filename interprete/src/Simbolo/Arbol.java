/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simbolo;
import abstracto.Instruccion;
import java.util.LinkedList;
import excepciones.Errores;
import instrucciones.Metodo;
/**
 *
 * @author Lesther
 */
public class Arbol {
    private LinkedList<Instruccion> instrucciones;
    private tablaSimbolos tablaGlobal;
    private LinkedList<Errores> errores;
    private String consolas;
    private LinkedList<Instruccion> funciones;
    private LinkedList<Instruccion> vectores;
    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.tablaGlobal = new tablaSimbolos();
        this.errores = new LinkedList<>();
        this.consolas = "";
        this.funciones = new LinkedList<>();
        this.vectores =  new LinkedList<>();
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public tablaSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public void setTablaGlobal(tablaSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }

    public String getConsolas() {
        return consolas;
    }

    public void setConsolas(String consolas) {
        this.consolas = consolas;
    }
    
    public void Print(String valor){
        this.consolas += valor + "\n";
    }

    public LinkedList<Instruccion> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<Instruccion> funciones) {
        this.funciones = funciones;
    }
    public void addFunciones(Instruccion funcion){
        this.funciones.add(funcion);
    }
    public Instruccion getFuncion(String id){
        for(var i:this.funciones){
            if (i instanceof Metodo metodo){
                if (metodo.id.equalsIgnoreCase(id)){
                    return i;
                }
            }
        }
        return null;
    }
}
