/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Simbolo.*;
import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;

/**
 *
 * @author Lesther
 */
public class While extends Instruccion {
    private Instruccion expresion;
    private LinkedList<Instruccion> instrucciones;

    public While(Instruccion expresion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
        this.instrucciones = instrucciones;
    }
    @Override 
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        // se crear una tabla para el while
               // se ejecuta condicion inicial del while
        Object condicion = this.expresion.interpretar(arbol, tabla);
        // validamos que la condicion no tenga errores
        if (condicion instanceof Errores){
            return condicion;
        } 
        // se ejecutan instrucciones mientras condicion sea verdadero
        while ((boolean)condicion){
            tablaSimbolos tablaWhile = new tablaSimbolos(tabla);

            for (var ins: instrucciones){
                if (ins instanceof Break){
                   return ins; 
                }
                Object resultado = ins.interpretar(arbol, tablaWhile);
                // validamos que la instruccion no traiga un errore
                if (resultado instanceof Errores){
                    return resultado;
                } 
                
            }
            // se ejecuta condicion nuevamente despues del bloque de instrucciones
            condicion = this.expresion.interpretar(arbol, tablaWhile);
            // validamos que la condicion no tenga errores
            if (condicion instanceof Errores){
                return condicion;
            } 
            // verificar que condicion sea un booleano despues del bloque de instrucciones
            if(!(condicion instanceof Boolean)){
                return new Errores("Semantico", "La condicion del while tiene que devolver un valor booleano", this.linea, this.col);
            }
        }
        return null;
    }
}
