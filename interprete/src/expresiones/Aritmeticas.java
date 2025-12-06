/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;
import abstracto.Instruccion;
import Simbolo.*;
import excepciones.Errores;
/**
 *
 * @author Lesther
 */
public class Aritmeticas extends Instruccion {
    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operaciones;
    private Instruccion operandoUnico;
    // negacion
    public Aritmeticas(OperadoresAritmeticos operaciones, Instruccion operandoUnico, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operaciones = operaciones;
        this.operandoUnico = operandoUnico;
    }
    // aritmetica 
    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operaciones, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operaciones = operaciones;
    }
    
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        Object opIzq = null, opDer = null, Unico = null;
        if (this.operandoUnico!=null){
            Unico = this.operandoUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
            }
        }else{
            opIzq = this.operando1.interpretar(arbol, tabla);
            if (opIzq instanceof Errores){
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Errores){
                return opDer;
            }
        }
        return switch (operaciones){
            case SUMA ->
                this.suma(opIzq, opDer);
            case NEGACION ->
                this.negacion(Unico);
            default -> 
                new Errores("ERROR semantico", "operando inexistente", this.linea, this.col);
                 
        };
        
        
    }
    
    public Object suma(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch(tipo1) {
            case ENTERO -> {
                switch (tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int)op1 + (double) op2;
                    }
                    case CADENA ->{
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                      
                    }
                    default -> {
                        return new Errores("ERROR semantico", "suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2){
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double)op1 + (double) op2;
                    }
                    case CADENA ->{
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                      
                    }
                    default -> {
                        return new Errores("ERROR semantico", "suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2){
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int)((Character) op1) + (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double)((Character) op1) + (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("ERROR semantico", "suma erronea", this.linea, this.col);
                    }
                }
            }
            case CADENA ->{
                this.tipo.setTipo(tipoDato.CADENA);
                return op1.toString() + op2.toString();
            }
            
            default -> {
                return new Errores("ERROR semantico", "suma erronea", this.linea, this.col);
            }
        }
        
        
    }
    public Object negacion(Object op1){
            var opU = this.operandoUnico.tipo.getTipo();
            switch (opU){
                case ENTERO->{
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) op1 * -1;
                }
                case DECIMAL ->{
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 * -1;
                }
                default ->{
                   return new Errores("ERROR semantico", "negacion erronea", this.linea, this.col);
                }
            }
        }
}

