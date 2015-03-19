import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

class PalavrasReservadas{
  private int id;
  private String token;

  public PalavrasReservadas(){
    id = 0;
    token = new String();
  }

  public void setId(int a){
    this.id = a;
  }
  public int getId(){
    return this.id;
  }
  public void setToken(String a){
    this.token = a;
  }
  public String getToken(){
    return this.token;
  }

  public int buscaPalavra(String palavra){
    

    return id;
  }

  public ArrayList<PalavrasReservadas> carregaPalavrasReservadas(ArrayList<PalavrasReservadas> palavras){
    int i = 0;

  	File f = null;
  	Scanner s = null;

    try{
      f = new File("reservadas.txt");
      s = new Scanner(f);
    }catch(Exception e) {}


    while(true){
      try{
        palavras.add(new PalavrasReservadas());
        palavras.get(i).setId(s.nextInt());
        palavras.get(i).setToken(s.next());
      // System.out.println("Id = "+palavras.get(i).id+" operador = "+palavras.get(i).token);
        i++;
      }catch(NoSuchElementException exp){
        System.out.println("Loading table of reserv words...");
        break;
      }
    }
    return palavras;
  }

}
