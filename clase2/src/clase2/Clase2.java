/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clase2;
import analisis.parser;
import analisis.scanner;
import java.io.BufferedReader;
import java.io.StringReader;
/**
 *
 * @author Lesther
 */
public class Clase2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            // Texto de entrada que será analizado por el scanner y luego por el parser
            String texto = "print(1+5*3/2.0);print(\"Cadena entre comillas\");";
            // Crear el parser, pasándole un scanner que leerá el texto línea por línea
            parser p = new parser(new scanner (new BufferedReader(new StringReader(texto))));
            // Ejecutar el análisis sintáctico y obtener el resultado
            var resultado = p.parse();
            // Imprimir el valor retornado por el parser
            System.out.println(resultado.value);
        }catch(Exception e){
            System.out.println("ERROR: la ejecucion no se completo");
        }
    }
    
}
