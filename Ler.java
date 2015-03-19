import java.util.Scanner;
import java.io.File;
class Ler{
	public String[] vet = new String [1000]; // vet terá o conteúdo lido do arquivo

	public void readArquivo(String[] arquivo){
		File f=null;
		Scanner s = null;
		int i=0;
		
		try{
			f = new File("arquivo.txt");
			s = new Scanner(f);
		}catch(Exception e) {}
	
		String a = s.nextLine();

		while(a != null){
			/*if(a.equals("") != true){
				vet[i] = a;
				i++;
				quan_linha++;
				a = s.nextLine();
			}
			else
				a = s.nextLine();*/
			vet[i] = a;
			a = s.nextLine();
			i++;
		}
	}

	public void showString(String[] str){
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
	}
}