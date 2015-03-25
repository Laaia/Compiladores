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
  int indice;

  public Analisador(){
    Operadores operador = new Operadores();
    PalavrasReservadas palavra = new PalavrasReservadas();

    tabelaOperadores = operador.carregaOperadores(tabelaOperadores);
    tabelaPalavras = palavra.carregaPalavrasReservadas(tabelaPalavras);
    indice = 59;
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
      indice++;
      String[] token = linha.split(" ");
      token[1] = token[1].replace(";", "");
      for(int i = 0; i < tabelaPalavras.size(); i++){
        if(token[0].equals(tabelaPalavras.get(i).getToken())){
          id = tabelaPalavras.get(i).getId();
          break;
        }
      }

      addPosicao( nLinha, 1, nArquivo);
      addToken(id, "P. Reservada", tabelaPosicao.size());
      addSimbolo(indice, token[0], token[1], "Variável", "Local");
      addPosicao(nLinha, 2, nArquivo);
      addToken(indice, "Variável", tabelaPosicao.size());
      addPosicao(nLinha, 3, nArquivo);
      for(int i = 0; i < tabelaOperadores.size(); i++){
        if(";".equals(tabelaOperadores.get(i).getOperador())){
          addToken(i, "Operador", tabelaPosicao.size());
          break;
        }
      }
      System.out.println(linha);

    }//else if(linha.matches("^(int(\\s)+|float(\\s)+|double(\\s)+|char(\\s)+)([a-zA-Z])+([0-9])*(\\s)*;$){

    //}

  }

  public void addPosicao(int nLinha, int coluna, String nArquivo){

    tabelaPosicao.add(new Posicao());
    tabelaPosicao.get(tabelaPosicao.size() - 1).setId(tabelaPosicao.size());
    tabelaPosicao.get(tabelaPosicao.size() - 1).setLinha(nLinha);
    tabelaPosicao.get(tabelaPosicao.size() - 1).setColuna(coluna);
    tabelaPosicao.get(tabelaPosicao.size() - 1).setArquivo(nArquivo);
  }

  public void addToken(int id, String tipo, int size){
    tabelaTokens.add(new Tokens());
    tabelaTokens.get(tabelaTokens.size() - 1).setId(id);
    tabelaTokens.get(tabelaTokens.size() - 1).setTipo(tipo);
    tabelaTokens.get(tabelaTokens.size() - 1).setPosicao(size);
  }
  public void addSimbolo(int indice, String tipo, String nome, String categoria, String escopo){
    tabelaSimbolos.add(new Simbolos());
    tabelaSimbolos.get(tabelaSimbolos.size() - 1).setId(indice);
    tabelaSimbolos.get(tabelaSimbolos.size() - 1).setTipo(tipo);
    tabelaSimbolos.get(tabelaSimbolos.size() - 1).setNome(nome);
    tabelaSimbolos.get(tabelaSimbolos.size() - 1).setCategoria(categoria);
    tabelaSimbolos.get(tabelaSimbolos.size() - 1).setEscopo(escopo);
  }


  public void gerarSaida(){
    System.out.println("\n.::Tabela de Tokens::.");
    System.out.println("Id\tTipo\t\tPosição");
    for(int i = 0 ; i < tabelaTokens.size(); i++){
      System.out.println(tabelaTokens.get(i).getId()+ "\t"+ tabelaTokens.get(i).getTipo() + "\t" + tabelaTokens.get(i).getPosicao());
    }

    System.out.println("\n.::Tabela de Posições::.");
    System.out.println("Id\tLinha\tColuna\tArquivo");
    for(int i = 0 ; i < tabelaPosicao.size(); i++){
      System.out.println(tabelaPosicao.get(i).getId()+ "\t"+ tabelaPosicao.get(i).getLinha() + "\t" + tabelaPosicao.get(i).getColuna()+ "\t" + tabelaPosicao.get(i).getArquivo());
    }

    System.out.println("\n.::Tabela de Símbolos::.");
    System.out.println("Id\tNome\tCategoria\tTipo\tEscopo");
    for(int i = 0 ; i < tabelaSimbolos.size(); i++){
      System.out.println(tabelaSimbolos.get(i).getId()+ "\t"+ tabelaSimbolos.get(i).getNome() + "\t" + tabelaSimbolos.get(i).getCategoria()+ "\t" + tabelaSimbolos.get(i).getTipo() + "\t" + tabelaSimbolos.get(i).getEscopo());
    }


  }
}
