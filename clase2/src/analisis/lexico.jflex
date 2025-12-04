package analisis;

import java_cup.runtime.Symbol;

%%
//  Código Java que sera insertado dentro de la clase scanner
// variable para almacenar los caracteres de una cadena
%{
    
    String cadena = "";
%}

// valor inicial de lineas y columnas
%init{
    yyline = 1;
    yycolumn = 1;
%init}

%cup  // indica que trabajará con CUP
%class scanner //nombre de la clase
%public //acceso de la clase
%line //conteo de lineas
%column //conteo de columnas
%char // conteo de caracteres
%full //reconocimiento de caracteres
%debug //depuracion
%ignorecase // ignora mayúsculas/minúsculas al reconocer tokens

// estado cadena para leer texto entre comillas dobles
%state CADENA
//simbolos y expresiones regulares
PAR1 = "("
PAR2 = ")"
FINCADENA = ";"
MAS = "+"
MENOS = "-"
MULT= "*"
DIV = "/"
ENTERO = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
BLANCOS = [\ \r\t\n\f]+
// palabras reservadas
PRINT = "print"

%%

// reglas lexicas


<YYINITIAL> {PRINT} {return new Symbol(sym.PRINT, yyline, yycolumn, yytext());}
<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext());}
<YYINITIAL> {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn, yytext());}
<YYINITIAL> {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn, yytext());}
<YYINITIAL> {MAS} {return new Symbol(sym.MAS, yyline, yycolumn, yytext());}
<YYINITIAL> {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn, yytext());}
<YYINITIAL> {MULT} {return new Symbol(sym.MULT, yyline, yycolumn, yytext());}
<YYINITIAL> {DIV} {return new Symbol(sym.DIV, yyline, yycolumn, yytext());}
<YYINITIAL> {PAR1} {return new Symbol(sym.PAR1, yyline, yycolumn, yytext());}
<YYINITIAL> {PAR2} {return new Symbol(sym.PAR2, yyline, yycolumn, yytext());}
<YYINITIAL> {BLANCOS} {}


//  Detección del inicio de una cadena. Cuando encuentra una comilla, cambia al estado CADENA
// yybegin(CADENA); -> inicia el estado de la cadena
<YYINITIAL> [\"]        {cadena = ""; yybegin(CADENA);} 

<CADENA> {
    [\"]    {String tmp= cadena;  // guarda la cadena construida
            cadena="";            // reinicia la variable
            yybegin(YYINITIAL);  // regresa al estado YYInitial
            return new Symbol(sym.CADENA, yyline, yycolumn,tmp);} //regresa la cadena con su valor
    
    [^\"]   {cadena+=yytext();}
}