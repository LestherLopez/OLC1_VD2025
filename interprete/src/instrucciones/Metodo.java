/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Simbolo.*;
import abstracto.Instruccion;
import excepciones.Errores;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Lesther
 */
public class Metodo extends Instruccion {
    public String id;
    public LinkedList<HashMap> parametros;
    private LinkedList<Instruccion> instrucciones;
   
    public Metodo(String id, LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
       
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        for (var i : this.instrucciones){
            var resultado = i.interpretar(arbol, tabla);
            if (resultado instanceof Errores){
                return resultado;
            }
            
        }
        return null;
    }
    
}
