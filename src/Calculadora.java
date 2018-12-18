import java.io.FileReader;
import java.io.IOException;
import java.io.FileReader;

public class Calculadora {

private static Symbol simbolo;
private static Yylex lex;

private static Symbol leer() {
	try {
		simbolo = lex.yylex();
		if (simbolo == null) {
			simbolo = new Symbol(Symbol.EOF, null);
		}
	} catch (IOException e) {
		System.out.println("IOException");
		System.exit(0);
	}
	return simbolo;
}

private static void error() {
	System.out.println("ERROR: Ultimo token leido: "+ simbolo.lexema());
	System.exit(1);
}


public static void main(String[] arg) {
    if (arg.length>0) {
        try {
			lex = new Yylex(new FileReader(arg[0]));
			simbolo = leer();
			while (simbolo.token()!=Symbol.EOF) {
				while (simbolo.token()==Symbol.EOLN){
					simbolo = leer();
				}
				if(simbolo.token()!=Symbol.EOF){
					int res = E();
					System.out.println(res);
				}
			}
        } catch (IOException e) {
        } 
    }
}

public static int E(){
	int s = -1;
	int SD = simbolo.token();
	if (SD == Symbol.NUMERO || SD == Symbol.AP || SD == Symbol.MENOS){
		int v = T();
		int h = Ep(v);
		s = h;
	} else {
		error();
	}
	return s;
}

public static int Ep(int h1){
	
	int v, s = -1, h2;
	int SD = simbolo.token();
	switch(SD){
		case Symbol.MAS:
			simbolo = leer();//consumo +
			v = T();
			s = Ep(h1 + v);
			break;
		case Symbol.MENOS:
			simbolo = leer();//consumo -
			v = T();
			s = Ep(h1 - v);
			break;
		case Symbol.CP:
			simbolo = leer();//consumo )
		case Symbol.EOF:
		case Symbol.EOLN:
			h2 = h1;
			s = h2;
			break;
		default:
			error();
	}
	return s;
}

public static int T(){
	int v = -1;
	switch(simbolo.token()){
		case Symbol.AP:
		case Symbol.NUMERO:
		case Symbol.MENOS:
			v = F();
			int s = Tp(v);
			v = s;
		break;
		default:
			error();
	}
	
	return v;
}

public static int Tp(int h1){
	
	int v, s = -1, h2;
	int SD = simbolo.token();
		switch(SD){
			case Symbol.POR:
				simbolo = leer();//Consumo *
				v = F();
				s = Tp(h1 * v);
				break;
			case Symbol.DIV:
				simbolo = leer();//Consumo /
				v = F();
				s = Tp(h1 / v);
				break;
			case Symbol.MAS:
			case Symbol.EOF:
			case Symbol.MENOS:
			case Symbol.CP:
			case Symbol.EOLN:
				h2 = h1;
				s = h2;
				break;
			default:
				error();
		}
	return s;
}

public static int F(){
	
	int v = -1;
	Symbol SD = simbolo;
	switch(SD.token()){
		case Symbol.AP:
			simbolo = leer();//Consumo el (
			v = E();
			break;
		case Symbol.NUMERO: 
			v = Integer.parseInt(SD.lexema());
			simbolo = leer();//Consumo el n
			break;
		case Symbol.MENOS:
			simbolo = leer();//Consumo el -
			v = -F();
			break;
		default:
			error();
	}
	return v;
}

}