class Posicao{
  private int id, linha, coluna;
  private String arquivo;

  public void setId(int a){
    this.id = a;
  }
  public int getId(){
    return this.id;
  }
  public void setLinha(int a){
    this.linha = a;
  }
  public int getLinha(){
    return this.linha;
  }
  public void setColuna(int a){
    this.coluna = a;
  }
  public int getColuna(){
    return this.coluna;
  }
  public void setArquivo(String a){
    this.arquivo = a;
  }
  public String getArquivo(){
    return this.arquivo;
  }

}
