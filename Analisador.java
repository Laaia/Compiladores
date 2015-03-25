import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

class Analisador{
  ArrayList<String> vet = new ArrayList<String>();
  ArrayList<Operadores> tabelaOperadores = new ArrayList<Operadores>();
  ArrayList<PalavrasReservadas> tabelaPalavras = new ArrayList<PalavrasReservadas>();
  ArrayList<Tokens> tabelaTokens = new ArrayList<Tokens>();
  ArrayList<Simbolos> tabelaSimbolos = new ArrayList<Simbolos>();
  ArrayList<Posicao> tabelaPosicao = new ArrayList<Posicao>();

  public Analisador(){
    Operadores operador = new Operadores();
    PalavrasReservadas palavra = new PalavrasReservadas();

    tabelaOperadores = operador.carregaOperadores(tabelaOperadores);
    tabelaPalavras = palavra.carregaPalavrasReservadas(tabelaPalavras);
  }

  public void Analisar(String nomeArquivo){
		File f = null;
		Scanner s = null;
    int nLinha = 1;

	try{
			f = new File(nomeArquivo);
			s = new Scanner(f);
		}catch(Exception e) {}

		String linha = s.nextLine();

		while(true){
		//System.out.println(linha);
      analisa(linha, nLinha, nomeArquivo);
			vet.add(linha);

			try{
				linha = s.nextLine();
        nLinha++;
			}catch(NoSuchElementException exp){
				System.out.println("Lexically analyzing the file: "+nomeArquivo);
				break;
			}
		}
	}
  public void analisa(String linha, int nLinha, String nArquivo){
    int id = 0;
    if(linha.matches("^(int(\\s)+|float(\\s)+|double(\\s)+|char(\\s)+)([a-zA-Z])+([0-9])*(\\s)*;$")){

      String[] token = linha.split(" ");
      token[1] = token[1].replace(";", "");
      for(int i = 0; i < tabelaPalavras.size(); i++){
        if(token[0].equals(tabelaPalavras.get(i).getToken())){
          id = tabelaPalavras.get(i).getId();
          break;
        }
      }

      tabelaPosicao.add(new Posicao());
      tabelaPosicao.get(tabelaPosicao.size() - 1).setId(tabelaPosicao.size());
      tabelaPosicao.get(tabelaPosicao.size() - 1).setLinha(nLinha);
      tabelaPosicao.get(tabelaPosicao.size() - 1).setColuna(1);
      tabelaPosicao.get(tabelaPosicao.size() - 1).setArquivo(nArquivo);

      tabelaTokens.add(new Tokens());
      tabelaTokens.get(tabelaTokens.size() - 1).setId(id);
      tabelaTokens.get(tabelaTokens.size() - 1).setTipo("Palavra reservada");
      tabelaTokens.get(tabelaTokens.size() - 1).setPosicao(tabelaPosicao.size());

      System.out.println(linha);
    }//lascou-se

  }

  public void gerarSaida(){
    System.out.println("\n.::Tabela de Tokens::.");
    System.out.println("Id         Tipo           Posição");
    for(int i = 0 ; i < tabelaTokens.size(); i++){
      System.out.println(tabelaTokens.get(i).getId()+ "   "+ tabelaTokens.get(i).getTipo() + "     " + tabelaTokens.get(i).getPosicao());
    }

    System.out.println("\n.::Tabela de Posições::.");
    System.out.println("Id  Linha   Coluna       Arquivo");
    for(int i = 0 ; i < tabelaPosicao.size(); i++){
      System.out.println(tabelaPosicao.get(i).getId()+ "    "+ tabelaPosicao.get(i).getLinha() + "         " + tabelaPosicao.get(i).getColuna()+ "       " + tabelaPosicao.get(i).getArquivo());
    }

  }
}
