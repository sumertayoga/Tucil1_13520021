import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class puzzle {
    public static int perbandinganCount;
    public static int totalPerbandingan = 0;

    public static void printMap(int idxBarisAwal, int idxKolomAwal, int pertambahanKolom, int pertambahanBaris, int baris, int kolom, char[][] map, int panjangKata){
        int i = 0;
        int j = 0;
        int tempPanjang = 0;
        while(i < baris){
            j = 0;
            while(j < kolom){
                if(i == idxBarisAwal && j == idxKolomAwal && tempPanjang < panjangKata){
                    System.out.print(map[i][j] + " ");
                    idxBarisAwal += pertambahanBaris;
                    idxKolomAwal += pertambahanKolom;
                    tempPanjang++;
                }
                else{
                    System.out.print("- ");
                }
                j++;
            }
            i++;
            System.out.println();
        }
    }

    private static Boolean checking(char[][] map, int idxBaris, int idxKolom ,String targetKata, int pertambahanKolom, int pertambahanBaris, int panjang, int baris, int kolom){
        boolean sama = true;
        int length = 2;
        while(sama && length < panjang){
            perbandinganCount++;
            int nowKolom = idxKolom+pertambahanKolom*length;
            int nowBaris = idxBaris+pertambahanBaris*length;
            if(nowKolom < 0 || nowKolom >= kolom || nowBaris < 0 || nowBaris >= baris || map[nowBaris][nowKolom] != targetKata.charAt(length)){
                sama = false;
            }
            else{
                length++;
            }
        }
        if(length == panjang){
            if(pertambahanBaris < 0 && pertambahanKolom < 0){
                printMap(idxBaris + pertambahanBaris*targetKata.length() + 1, idxKolom + pertambahanKolom*targetKata.length() + 1, Math.abs(pertambahanKolom), Math.abs(pertambahanBaris), baris, kolom, map, targetKata.length());
            }
            else if(pertambahanBaris < 0 && pertambahanKolom != 0){
                printMap(idxBaris + pertambahanBaris*targetKata.length() + 1, idxKolom + pertambahanKolom*targetKata.length()-1, -1*(pertambahanKolom), -1*(pertambahanBaris), baris, kolom, map, targetKata.length());
            }
            else if(pertambahanKolom < 0 && pertambahanBaris != 0){
                printMap(idxBaris , idxKolom , (pertambahanKolom), (pertambahanBaris), baris, kolom, map, targetKata.length());
            }
            else if(pertambahanKolom < 0 && pertambahanBaris == 0){
                printMap(idxBaris , idxKolom + pertambahanKolom*targetKata.length() + 1, -1*(pertambahanKolom), -1*(pertambahanBaris), baris, kolom, map, targetKata.length());
            }
            else if(pertambahanBaris < 0 && pertambahanKolom == 0){
                printMap(idxBaris + pertambahanBaris*targetKata.length() + 1, idxKolom, -1*(pertambahanKolom), -1*(pertambahanBaris), baris, kolom, map, targetKata.length());
            }
            else{     
               printMap(idxBaris, idxKolom, pertambahanKolom, pertambahanBaris, baris, kolom, map, targetKata.length()); 
            }
            return false;
        }
        else{
            return true;
        }
    }

    
    public static void puzzleFinding(int baris, int kolom, String target, char[][] map){
        int i = 0;
        int j = 0;
        boolean notFinish = true;
        perbandinganCount = 0;
        while(notFinish && i < baris){
            j = 0;
            while(notFinish && j < kolom){
                perbandinganCount++;
                if (map[i][j] == target.charAt(0)){
                    //Ke kanan
                    perbandinganCount++;
                    if (j+1 < kolom && map[i][j+1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 1, 0, target.length(), baris, kolom);
                    }
                    //Ke bawah
                    perbandinganCount++;
                    if (i+1 < baris && map[i+1][j] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 0, 1, target.length(), baris, kolom);
                    }
                    //Ke kiri
                    perbandinganCount++;
                    if (j-1 >= 0 && map[i][j-1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, -1, 0, target.length(), baris, kolom);
                    }
                    //Ke atas
                    perbandinganCount++;
                    if (i-1 >= 0 && map[i-1][j] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 0, -1, target.length(), baris, kolom);
                    }
                    //Diagonal kanan turun
                    perbandinganCount++;
                    if (j+1 < kolom && i+1 < baris && map[i+1][j+1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 1, 1, target.length(), baris, kolom);
                    }
                    //Diagonal kiri naik
                    perbandinganCount++;
                    if (j - 1 >= 0 && i - 1 >= 0 && map[i-1][j-1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, -1, -1, target.length(), baris, kolom);
                    }
                    //Diagonal kanan naik
                    perbandinganCount++;
                    if (j + 1 < kolom && i - 1 >= 0  && map[i-1][j+1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 1, -1, target.length(), baris, kolom);
                    }
                    //Diagonal kiri turun
                    perbandinganCount++;
                    if (j - 1 >= 0 && i + 1 < baris && map[i+1][j-1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, -1, 1, target.length(), baris, kolom);
                    }
                }
                j++;
            }
            i++;
        }
        if (notFinish){
            System.out.println("Maaf, kata tidak ditemukan");
        }
        totalPerbandingan += perbandinganCount;
        System.out.println("Jumlah perbandingan yang dilakukan ada sebanyak " + perbandinganCount + " kali");
        System.out.println();
    }

    public static void main(String[] args) throws IOException{
        Scanner inputFile = new Scanner(System.in);
        System.out.println("Masukkan Nama File (ex: small1.txt): ");
        String namaFile = inputFile.nextLine();
        inputFile.close();

        //Mencari banyak baris, kolom, dan banyak kata
        Scanner obj = new Scanner(new File("../test/" + namaFile));
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
        
        //Memmbentuk matriks dan listWord
        obj = new Scanner(new File("../test/" + namaFile));
        char[][] matriks = new char[baris][kolom];
        for(int i = 0; i < baris; i++){
            for(int j = 0; j < kolom; j++){
                if(obj.hasNext()){
                    matriks[i][j] = obj.next().charAt(0);
                }
            }
        }
        line = obj.nextLine();
        line = obj.nextLine();
        String[] listWord = new String[listLength];
        for(int i = 0; i < listLength; i++){
            listWord[i] = obj.nextLine();
        }
        obj.close();

        //Proses pencarian kata di puzzle
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < listWord.length; i++){
            System.out.println("Berikut adalah solusi puzzle untuk kata ke-" + (i+1));
            puzzleFinding(baris, kolom, listWord[i], matriks);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Jumlah total perbandingan dari semua kata ada sebanyak " + totalPerbandingan + " kali");
        System.out.println("Waktu eksekusi program adalah " + (endTime-startTime) + " ms");
    }
    
}