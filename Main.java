import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

class Main{
	public static void main(String[] arg){
		Analisador lexico = new Analisador();
		lexico.Analisar("arquivo.txt");
		//lexico.Analisar("a.txt");
		//lexico.Analisar("b.txt");
		lexico.gerarSaida();
	}
}
