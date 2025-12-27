/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;
import Simbolo.*;
import abstracto.*;
import excepciones.Errores;
/**
 *
 * @author Lesther
 */
public class Return extends Instruccion {
    private Instruccion expresion;
    public Object valor;
    public Return(Instruccion expresion,int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
    }
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        Object resultado = expresion.interpretar(arbol, tabla);
        if (resultado instanceof Errores){
            return resultado;
        }
        this.valor = resultado;
        return this;
        
    }
}
