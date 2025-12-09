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
/**
 *
 * @author Lesther
 */
public class AsignacionVar extends Instruccion{
    private String id;
    public Instruccion valor;

    public AsignacionVar(String id, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.valor = valor;
    }
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var variable = tabla.getVariable(id);
        if (variable == null){
            return new Errores("semantico", "Variable no existente", this.linea, this.col);
        }
        var newValor = this.valor.interpretar(arbol, tabla);
        if (newValor instanceof Errores){
            return newValor;
        }
        if(variable.getTipo().getTipo() != this.valor.tipo.getTipo()){
            return new Errores("semantico", "Tipos erroneos", this.linea, this.col);
            
        }
        variable.setValor(newValor);
        return null;
    }
}
