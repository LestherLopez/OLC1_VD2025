/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import Simbolo.Tipo;
import abstracto.Instruccion;
import Simbolo.Arbol;
import Simbolo.tablaSimbolos;
import Simbolo.tipoDato;
/**
 *
 * @author Lesther
 */
public class Nativo extends Instruccion {
    public Object valor;

    public Nativo(Object valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.valor = valor;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        if(this.tipo.getTipo() == tipoDato.CADENA){
            String  texto =  valor.toString();
            texto = texto.replace("\\n", "\n");
            /*
                        print("hola\nmundo");
                        hola
                        mundo
                        */
            return texto;
        }
        return this.valor;
    }
    
}
