package analisis;

import java_cup.runtime.Symbol;
import java.util.LinkedList;
import excepciones.Errores;
%%
// codigo de usuario
%{
   public LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
%init}


%cup
%class scanner
%public 
%line
%char
%column
%full
%ignorecase


//simbolos del sistema
PAR1 = "("
PAR2 = ")"
MAS = "+"
MENOS = "-"
IGUAL = "="
EQUALS = "=="
_llaveizq = "{"
_llaveder = "}"
_menorq = "<"
DOSPUNTOS = ":"
TRUE="true"
FALSE="false"
FINCADENA = ";"

BLANCOS = [\ \r\t\f\n]+
ENTERO = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
CHAR = \'([^\'\\]|\\.)\'
CADENA = [\"]([^\"])*[\"]
ID = [a-zA-Z_][a-zA-Z0-9_]*
// palabras reservadas
_break ="break"
_while = "while"
_if = "if"
_else = "else"
PRINT = "print"
VAR = "var"
INT = "int"
DOUBLE = "double"
STRING = "string"
%%
<YYINITIAL> {BLANCOS} { }
<YYINITIAL> {PRINT} {return new Symbol(sym.PRINT, yyline, yycolumn, yytext());}
<YYINITIAL> {TRUE} {return new Symbol(sym.TRUE, yyline, yycolumn,yytext());}
<YYINITIAL> {FALSE} {return new Symbol(sym.FALSE, yyline, yycolumn,yytext());}
<YYINITIAL> {_if} {return new Symbol(sym._if, yyline, yycolumn,yytext());}
<YYINITIAL> {_else} {return new Symbol(sym._else, yyline, yycolumn,yytext());}
<YYINITIAL> {_while} {return new Symbol(sym._while, yyline, yycolumn,yytext());}
<YYINITIAL> {_break} {return new Symbol(sym._break, yyline, yycolumn,yytext());}
<YYINITIAL> {VAR} {return new Symbol(sym.VAR, yyline, yycolumn,yytext());}
<YYINITIAL> {INT} {return new Symbol(sym.INT, yyline, yycolumn,yytext());}
<YYINITIAL> {STRING} {return new Symbol(sym.STRING, yyline, yycolumn,yytext());}
<YYINITIAL> {DOUBLE} {return new Symbol(sym.DOUBLE, yyline, yycolumn,yytext());}
<YYINITIAL> {ID} {return new Symbol(sym.ID, yyline, yycolumn,yytext());}
<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext());}
<YYINITIAL> {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn, yytext());} 
<YYINITIAL> {CHAR} {
    String texto = yytext();       // 'A'
    char contenido = texto.charAt(1);  // A
    return new Symbol(sym.CHAR, yyline, yycolumn, contenido);
}
<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);
    }

<YYINITIAL> {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn, yytext());}
<YYINITIAL> {DOSPUNTOS} {return new Symbol(sym.DOSPUNTOS, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR1} {return new Symbol(sym.PAR1, yyline, yycolumn, yytext());}
<YYINITIAL> {PAR2} {return new Symbol(sym.PAR2, yyline, yycolumn, yytext());}
<YYINITIAL> {_llaveizq} {return new Symbol(sym._llaveizq, yyline, yycolumn, yytext());}
<YYINITIAL> {_llaveder} {return new Symbol(sym._llaveder, yyline, yycolumn, yytext());}
<YYINITIAL> {_menorq} {return new Symbol(sym._menorq, yyline, yycolumn, yytext());}
<YYINITIAL> {MAS} {return new Symbol(sym.MAS, yyline, yycolumn, yytext());}
<YYINITIAL> {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn, yytext());}
<YYINITIAL> {EQUALS} {return new Symbol(sym.EQUALS, yyline, yycolumn, yytext());}
<YYINITIAL> {IGUAL} {return new Symbol(sym.IGUAL, yyline, yycolumn, yytext());}
<YYINITIAL> . {
            listaErrores.add(new Errores("LEXICO", "El carcater "+yytext()+" No pertenece al lenguaje", yyline, yycolumn));

}