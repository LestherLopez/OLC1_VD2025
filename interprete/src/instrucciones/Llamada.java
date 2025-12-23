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

/*
    int suma(int x, int y){
        print("hola")
        return x+y;
    }
    suma(5,5);
*/

public class Llamada extends Instruccion {
    private String id;
    private LinkedList<Instruccion> parametros;

    public Llamada(String id, LinkedList<Instruccion> parametros, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.parametros = parametros;
    }
    
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var busqueda  =  arbol.getFuncion(id);
        if(busqueda == null){
            return new Errores("SEMANTICO", "Funcion" + id + "no existe", this.linea, this.col);
        }
        if (busqueda instanceof Metodo metodo){
            var newTabla =new tablaSimbolos(arbol.getTablaGlobal());
            newTabla.setNombre("Llamada de metodo "+this.id);
            // verificar tamaño de parametros
           if(metodo.parametros.size()!=this.parametros.size()){
               return new Errores("SEMANTICO", "tamaño de parametros erroneo", this.linea, this.col);
           }
           
           /*
                        int global;
                        int factorial(int numero){
                                
                                factorial(numero);
                                return numero;
                        }
                        
                        void principal(){
                            int x = 5;
                           factorial(x);
                            print(factorial(5));
                            
                        }
                        
                        start principal(),
                    */
           for (int i=0; i<this.parametros.size(); i++){
               var identificador = (String) metodo.parametros.get(i).get("id");
               var valor = this.parametros.get(i);
               var tipo = (Tipo) metodo.parametros.get(i).get("tipo");
               var declaracionParametros = new Declaracion(identificador, tipo, this.linea, this.col);
               var resultadoDeclaracion = declaracionParametros.interpretar(arbol, newTabla);
               if (resultadoDeclaracion instanceof Errores){
                  return resultadoDeclaracion;
               }
               var valorInterpretad = valor.interpretar(arbol, tabla);
               if (valorInterpretad instanceof Errores){
                  return valorInterpretad;
               }
               var variable = newTabla.getVariable(identificador);
               if(variable == null){
                   return new Errores("SEMANTICO", "Error en declaracion de parametros", this.linea, this.col);
               }
               if (variable.getTipo().getTipo()!= valor.tipo.getTipo()){
                   return new Errores("SEMANTICO", "Error en el tipo de los parametros", this.linea, this.col);
               }
               
               variable.setValor(valorInterpretad);
           }
           var resultadoFuncion = metodo.interpretar(arbol, newTabla);
           if (resultadoFuncion instanceof Errores){
                return resultadoFuncion;
           }
        }
        return null;
        
        
        
    }
    
}
