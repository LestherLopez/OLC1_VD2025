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
public class If extends Instruccion {
    private Instruccion expresion;
    private LinkedList<Instruccion> instrucciones;
    private LinkedList<Instruccion> instruccioneselseif;
    private LinkedList<Instruccion> instruccioneselse;

    public If(Instruccion expresion, LinkedList<Instruccion> instrucciones,  int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
        this.instrucciones = instrucciones;
    }
    public If(Instruccion expresion, LinkedList<Instruccion> instrucciones, LinkedList<Instruccion> instruccioneselse,int linea, int col){
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
        this.instrucciones = instrucciones;
        this.instruccioneselse = instruccioneselse;  
    }
    public If(Instruccion expresion, LinkedList<Instruccion> instrucciones, LinkedList<Instruccion> instruccioneselseif,LinkedList<Instruccion> instruccioneselse,int linea, int col){
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
        this.instrucciones = instrucciones;
        this.instruccioneselseif = instruccioneselseif;
        this.instruccioneselse = instruccioneselse;  
    }
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var condicion = this.expresion.interpretar(arbol, tabla);
        if (condicion instanceof Errores){
            return condicion;
        }
        // se verifica el condicional es de tipo booleano
        if(!(condicion instanceof Boolean)){
            return new Errores("Semantico", "La condicion del if tiene que devolver un valor booleano", this.linea, this.col);
        }
        boolean ejecutarIf = (Boolean) condicion;
        // bloque para manejar if
        // ejecutar el bloque de instrucciones if
        if(ejecutarIf){
            //se va ejecutar el bloque de instrucciones if
            var nuevaTabla = new tablaSimbolos(tabla);
            for (var inst: instrucciones){
                var res = inst.interpretar(arbol, nuevaTabla);
                if (res instanceof Errores){
                    return res;
                }
            }
            return true; // ejecuto if
        }
        /*
                    if(){
                        if(){
                        }
                    }
        
                */
        // bloque para manejar elseif
        if (instruccioneselseif != null){
            for (Instruccion elseif: instruccioneselseif){
                var res  = elseif.interpretar(arbol, tabla);
                if (res instanceof Errores){
                    return res;
                }
                if (res instanceof Boolean && (Boolean) res){
                    return true;
                }
            }
        }
        
        // bloque para manejar else
        if (instruccioneselse != null){
            var nuevaTabla = new tablaSimbolos(tabla);
            for(var instElse: instruccioneselse){
                var res = instElse.interpretar(arbol, nuevaTabla);
                if (res instanceof Errores){
                    return res;
                }
            }
            return true;
        }
        return false;
        
    }
}
