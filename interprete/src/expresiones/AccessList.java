/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;
import Simbolo.tipoDato;
import abstracto.Instruccion;
import excepciones.Errores;

/**
 *
 * @author Lesther
 */
public class AccessList extends Instruccion {
    private String id;
    private Instruccion exp;

    public AccessList(String id, Instruccion exp,  int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.exp = exp;
    }
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var valor = tabla.getVariable(this.id);
        if (valor == null){
            return new Errores("semantico",
                        "lista no existe",
                        this.linea, this.col);
        }
        //5
        var newValor = this.exp.interpretar(arbol, tabla);
        if(valor.getValorlista()!=null){
            if((int)newValor >=0 &&(int)newValor < valor.getValorlista().size()){               
                return valor.getValorlista().get((int) newValor);
            }else{
                return new Errores("semantico",
                        "indice esta fuera de rango",
                        this.linea, this.col);
            }
        }else{
            return new Errores("semantico",
                        "La variable no es una lista",
                        this.linea, this.col);
        }
    }
    
}
