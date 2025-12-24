/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Simbolo.*;
import Simbolo.tablaSimbolos;
import abstracto.Instruccion;
import excepciones.Errores;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lesther
 */
public class DeclaracionList extends Instruccion {
    public String identificador;

    public DeclaracionList(String identificador, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
    }
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        List<Object> valores =new ArrayList<>();
        Simbolo s = new Simbolo(this.tipo, this.identificador, valores);
        boolean creacion = tabla.setVariables(s);
        if(!creacion){
            return new Errores("semantico",
                        "variable ya existente '" + this.identificador + "'",
                        this.linea, this.col);
        }
        return null;
    }
}
