import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class tes {
    static void plus(int y, int x) {
        System.out.println(x+y);
    }
    public static void main(String[] args) throws IOException{
        System.out.println("Hallo, Brow!");
        plus(1,5);
        int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        System.out.println(matrix[1][2]);

        // Mencari banyak kolom dan baris
        //File obj = new File("src/teks.txt");
        File obj = new File("../test/teks.txt");
        Scanner myReader = new Scanner(obj);
        int baris = 1;
        String col = myReader.nextLine();
        while(myReader.has){
            baris++;
            col = myReader.nextLine();
        }
        String[] panjang = col.split(" ");
        int kolom = panjang.length;
        myReader.close();

        System.out.println(baris);

        myReader = new Scanner(obj);
        String[][] matriks = new String[baris][kolom];
        for(int i = 0; i < baris; i++){
            for(int j = 0; j < kolom; j++){
                if(myReader.hasNext()){
                    matriks[i][j] = myReader.next();
                    System.out.print(matriks[i][j]); //Ngetes print matriksnya
                }
            }
            System.out.println(); // Ngetes print matriksnya
        }
        myReader.close();


    }
    
}