/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Simbolo.Arbol;
import Simbolo.Simbolo;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;
import abstracto.Instruccion;
import excepciones.Errores;

/**
 *
 * @author Lesther
 */
public class Declaracion extends Instruccion {
    public String identificador;
    public Instruccion valor;

    public Declaracion(String identificador, Instruccion valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.valor = valor;
    }
    
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        
        
        Object valorInterpretado = null;

        // Si la declaración trae asignación
        if (this.valor != null) {
            valorInterpretado = this.valor.interpretar(arbol, tabla);
            if (valorInterpretado instanceof Errores){
                return valorInterpretado;
            }

            // Validación de tipo
            if (this.valor.tipo.getTipo() != this.tipo.getTipo()){
                return new Errores("semantico",
                        "Tipos erroneos en la declaración de '" + this.identificador + "'",
                        this.linea, this.col);
            }
        }

        // Si NO trae asignación → asignar valor por defecto
        else {
            // valor por defecto según el tipo
            switch(this.tipo.getTipo()){
                case ENTERO: valorInterpretado = 0; break;
                case DECIMAL: valorInterpretado = 0.0; break;
                case BOOLEANO: valorInterpretado = false; break;
                case CADENA: valorInterpretado = ""; break;
                default: valorInterpretado = null; break;
            }
        }
        Simbolo s = new Simbolo(this.tipo, this.identificador, valorInterpretado);
        boolean creacion = tabla.setVariables(s);
        if(!creacion){
            return new Errores("semantico", "Variable ya existente", this.linea, this.col);
        }
        return null;
    }
}
