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
public class Start extends Instruccion {
    private String id;

    public Start(String id, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
    }
    
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var busqueda  =  arbol.getFuncion(id);
        if(busqueda == null){
            return new Errores("SEMANTICO", "Funcion" + id + "no existe", this.linea, this.col);
        }
        if (busqueda instanceof Metodo metodo){
           var newTbla = new tablaSimbolos(arbol.getTablaGlobal());
           newTbla.setNombre("START");
           var resultadoFuncion = metodo.interpretar(arbol, newTbla);
           if (resultadoFuncion instanceof Errores){
                return resultadoFuncion;
            }
        }
        return null;
    }
}
