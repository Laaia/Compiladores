import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

class Operadores{
  private int id;
  private String operador;

  public Operadores(){
    id = 0;
    operador = new String();
  }

  public void setId(int a){
    this.id = a;
  }
  public int getId(){
    return this.id;
  }
  public void setOperador(String a){
    this.operador = a;
  }
  public String getOperador(){
    return this.operador;
  }

  public ArrayList<Operadores> carregaOperadores(ArrayList<Operadores> operadores){
    int i = 0;

  	File f = null;
  	Scanner s = null;

    try{
      f = new File("operadores.txt");
      s = new Scanner(f);
    }catch(Exception e) {}


    while(true){
      try{
        operadores.add(new Operadores());
    		operadores.get(i).setId(s.nextInt());
    		operadores.get(i).setOperador(s.next());
        //System.out.println("Id = "+operadores.get(i).id+" operador = "+operadores.get(i).operador);
        i++;
      }catch(NoSuchElementException exp){
        System.out.println("Loading table of operators...");
        break;
      }
    }
    return operadores;
  }
}
