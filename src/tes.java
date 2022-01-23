import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class tes {
    public static void main(String[] args) throws IOException{
        Scanner obj = new Scanner(new File("../test/teks.txt"));
        int baris = 0;
        int kolom;
        String line = obj.nextLine();
        String[] col = line.split(" ");
        kolom = col.length;
        while(!line.isEmpty()){
            baris++;
            line = obj.nextLine();
        }
        int listLength = 0;
        while(obj.hasNextLine()){
            listLength++;
            line = obj.nextLine();
        }
        obj.close();

        
        obj = new Scanner(new File("../test/teks.txt"));
        String[][] matriks = new String[baris][kolom];
        for(int i = 0; i < baris; i++){
            for(int j = 0; j < kolom; j++){
                if(obj.hasNext()){
                    matriks[i][j] = obj.next();
                    System.out.print(matriks[i][j]);
                }
            }
            System.out.println();
        }
        line = obj.nextLine();
        line = obj.nextLine();
        String[] listWord = new String[listLength];
        for(int i = 0; i < listLength; i++){
            listWord[i] = obj.nextLine();
            System.out.println(listWord[i]);
        }
        obj.close();
        

    }
    
}