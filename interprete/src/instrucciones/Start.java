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
public class Start extends Instruccion {
    private String id;
    private LinkedList<Instruccion> parametros;
   
    public Start(String id,LinkedList<Instruccion> parametros, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.parametros = parametros;
    }
    
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var busqueda  =  arbol.getFuncion(id);
        if(busqueda == null){
            return new Errores("SEMANTICO", "Funcion" + id + "no existe", this.linea, this.col);
        }
        /*
                    void suma(int x, int y){
                    }
                    start suma(5, 5);
                */
        if (busqueda instanceof Metodo metodo){
           var newTbla = new tablaSimbolos(arbol.getTablaGlobal());
           newTbla.setNombre("START");
           // verificar tamaño de parametros
           if(metodo.parametros.size()!=this.parametros.size()){
               return new Errores("SEMANTICO", "tamaño de parametros erroneo", this.linea, this.col);
           }
           // recorre los parametros y ejecuta 
           for (int i=0; i<this.parametros.size(); i++){
               var identificador = (String) metodo.parametros.get(i).get("id");
               var valor = this.parametros.get(i);
               var tipo = (Tipo) metodo.parametros.get(i).get("tipo");
               var declaracionParametros = new Declaracion(identificador, valor, tipo, this.linea, this.col);
               var resultadoDeclaracion = declaracionParametros.interpretar(arbol, newTbla);
               if (resultadoDeclaracion instanceof Errores){
                  return resultadoDeclaracion;
                }

           }
           var resultadoFuncion = metodo.interpretar(arbol, newTbla);
           if (resultadoFuncion instanceof Errores){
                return resultadoFuncion;
            }
        }
        return null;
    }
}
