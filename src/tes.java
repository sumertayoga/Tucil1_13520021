import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class tes {
    public static void printMap(int idxBarisAwal, int idxKolomAwal, int pertambahanKolom, int pertambahanBaris, int baris, int kolom, char[][] map, int panjang){
        int i = 0;
        int j = 0;
        int length = 0;
        while(i < baris){
            j = 0;
            while(j < kolom){
                if(i == idxBarisAwal && j == idxKolomAwal && length < panjang){
                    System.out.print(map[i][j] + " ");
                    idxBarisAwal += pertambahanBaris;
                    idxKolomAwal += pertambahanKolom;
                    length++;
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

    private static Boolean checking(char[][] map, int idxBaris, int idxKolom ,String target, int pertambahanKolom, int pertambahanBaris, int panjang, int baris, int kolom){
        boolean sama = true;
        int length = 2;
        while(sama && length < panjang){
            if(map[idxBaris+pertambahanBaris*length][idxKolom+pertambahanKolom*length] != target.charAt(length)){
                sama = false;
            }
            else{
                length++;
            }
        }
        if(length == panjang){
            if(pertambahanBaris < 0 && pertambahanKolom < 0){
                printMap(idxBaris + pertambahanBaris*target.length() + 1, idxKolom + pertambahanKolom*target.length() + 1, Math.abs(pertambahanKolom), Math.abs(pertambahanBaris), baris, kolom, map, target.length());
            }
            else if(pertambahanBaris < 0 && pertambahanKolom != 0){
                printMap(idxBaris + pertambahanBaris*target.length() + 1, idxKolom + pertambahanKolom*target.length()-1, -1*(pertambahanKolom), -1*(pertambahanBaris), baris, kolom, map, target.length());
            }
            else if(pertambahanKolom < 0 && pertambahanBaris != 0){
                printMap(idxBaris , idxKolom , (pertambahanKolom), (pertambahanBaris), baris, kolom, map, target.length());
            }
            else if(pertambahanKolom < 0 && pertambahanBaris == 0){
                printMap(idxBaris , idxKolom + pertambahanKolom*target.length() + 1, -1*(pertambahanKolom), -1*(pertambahanBaris), baris, kolom, map, target.length());
            }
            else if(pertambahanBaris < 0 && pertambahanKolom == 0){
                printMap(idxBaris + pertambahanBaris*target.length() + 1, idxKolom, -1*(pertambahanKolom), -1*(pertambahanBaris), baris, kolom, map, target.length());
            }
            else{     
               printMap(idxBaris, idxKolom, pertambahanKolom, pertambahanBaris, baris, kolom, map, target.length()); 
            }
            return false;
        }
        else{
            return true;
        }
    }

    public static void puzelFinding(int baris, int kolom, String target, char[][] map){
        int i = 0;
        int j = 0;
        boolean notFinish = true;
        while(notFinish && i < baris){
            j = 0;
            while(notFinish && j < kolom){
                if (map[i][j] == target.charAt(0)){
                    //Ke kanan
                    if (kolom - j >= target.length()-1 && map[i][j+1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 1, 0, target.length(), baris, kolom);
                    }
                    //Ke bawah
                    if (baris - i >= target.length()-1 && map[i+1][j] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 0, 1, target.length(), baris, kolom);
                    }
                    //Ke kiri
                    if (j >= target.length()-1 && map[i][j-1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, -1, 0, target.length(), baris, kolom);
                    }
                    //Ke atas
                    if (i >= target.length()-1 && map[i-1][j] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 0, -1, target.length(), baris, kolom);
                    }
                    //Diagonal kanan turun
                    if (kolom - j >= target.length()-1 && baris - i >= target.length()-1 && map[i+1][j+1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 1, 1, target.length(), baris, kolom);
                    }
                    //Diagonal kiri naik
                    if (j >= target.length()-1 && i >= target.length()-1 && map[i-1][j-1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, -1, -1, target.length(), baris, kolom);
                    }
                    //Diagonal kanan naik
                    if (kolom - j >= target.length()-1 && i >= target.length()-1 && map[i-1][j+1] == target.charAt(1) && notFinish){
                        notFinish = checking(map, i, j, target, 1, -1, target.length(), baris, kolom);
                    }
                    //Diagonal kiri turun
                    if (j >= target.length()-1 && baris - i >= target.length()-1 && map[i+1][j-1] == target.charAt(1) && notFinish){
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
    }

    public static void main(String[] args) throws IOException{
        Scanner obj = new Scanner(new File("../test/big1.txt"));
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

        
        obj = new Scanner(new File("../test/big1.txt"));
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

        //System.out.println(listWord[9]);
        //puzelFinding(baris, kolom, listWord[9], matriks);
        for(int i = 0; i < listWord.length; i++){
            System.out.println("Berikut adalah solusi puzzle untuk kata ke-" + (i+1));
            puzelFinding(baris, kolom, listWord[i], matriks);
        }

        /*int i = 0;
        int j = 0;
        boolean notFinish = true;
        while(notFinish && i < baris){
            j = 0;
            while(notFinish && j < kolom){
                if (matriks[i][j] == listWord[4].charAt(0)){
                    if (matriks[i][j+1] == listWord[4].charAt(1)){
                        notFinish = checking(matriks, i, j, listWord[4], 1, 0, listWord[4].length(), baris, kolom);
                        boolean sama = true;
                        int l = 2;
                        while (sama && l < 7){
                            if(matriks[i][j+l] != listWord[4].charAt(l)){
                                sama = false;
                            }
                            else{
                                l++;
                            }
                        }
                        if(l == 7){
                            notFinish = false;
                            printMap(i, j, 1, 0, baris, kolom, matriks, listWord[4].length());
                        }
                    }
                }
                j++;
            }
            i++;
        }*/
    }
    
}