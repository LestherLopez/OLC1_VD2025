/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;
import Simbolo.tipoDato;
import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;

/**
 *
 * @author Lesther
 */
public class For extends Instruccion {
    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;

    public For(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }
    @Override
    
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        //for(i=0; i<5; i++)
        var newTabla = new tablaSimbolos(tabla);
        // ejecutar asignacion o declaracion
        var res1 = this.asignacion.interpretar(arbol, newTabla);
        if (res1 instanceof Errores){
            return res1;
        } 
        // ejecucion de condicional
        var cond = this.condicion.interpretar(arbol, newTabla);
        if (cond instanceof Errores){
            return cond;
        } 
        // validar que la condicion sea bool
        while ((boolean)cond){
            // tabla para bloque de instrucciones
            var tablaInstrucciones = new tablaSimbolos(newTabla);
            // ejecutan instrucciones en for
            // revisar si viene break o continue
            // ejecutar actualizacion con newTabla
        }
        return null;
    }
    
}
