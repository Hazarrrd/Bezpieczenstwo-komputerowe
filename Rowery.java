
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class Rowery {

    static int number= 30;
    static int[][] codes = new int[number][2];
    static int[][] przestawienia =new int[30][4];
    static int counter0=0,counter1=0,counter2=0,counter3=0;
    static int ccounter0=0;  static int ccounter1=0;  static int ccounter2=0; static int ccounter3=0;

    public static void readC() throws FileNotFoundException {
        Scanner sc;
        String txt;
        int i=0;
        int[] temp = new int[2];
        sc = new Scanner(new File("danerowery.txt"));
        while(sc.hasNext()) {
            txt = sc.nextLine();
            temp[0]=Integer.parseInt(txt.split("-")[0]);
            temp[1]=Integer.parseInt(txt.split("-")[1]);
            codes[i][0]=temp[0];
            codes[i][1]=temp[1];
            i++;
        }
    }
    public static void main(String[] args) {
        try{
            readC();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

      //  for(int i=0;i<30;i++)
         //   System.out.println(codes[i][0] + " " + codes[i][1]);
    
        System.out.println("!!!!!!!!!!! Srednia ilosc przestawien dla danego roweru z. elektroniczne : " +roznice(0));
        System.out.println();
        System.out.println();
        System.out.println("!!!!!!!!!!! Srednia ilosc przestawien dla danego roweru z. mechaniczne : " +  roznice(15));
        
        for(int i=0; i<15;i++){
            if(przestawienia[i][0]==0)
                counter0++;
            if(przestawienia[i][1]==0)
            counter1++;
            if(przestawienia[i][3]==0)
            counter3++;
            if(przestawienia[i][2]==0)
            counter2++;
        }
        System.out.println("Dla elektronikow kolejno tyle razy pola byly nie ruszane : " + counter0 + " " + counter1 + " " + counter2 + " " + counter3 );
        
        for(int i=15; i<30;i++){
            if(przestawienia[i][0]==0)
                ccounter0++;
            if(przestawienia[i][1]==0)
            ccounter1++;
            if(przestawienia[i][3]==0)
            ccounter3++;
            if(przestawienia[i][2]==0)
            ccounter2++;
        }
        System.out.println("Dla lancuchow kolejno tyle razy pola byly nie ruszane : " + ccounter0 + " " + ccounter1 + " " + ccounter2 + " " + ccounter3 );
        ileprawidlowo();
        entropia();
    }
    
    public static int roznice(int starter) {
        int suma2=0;
        double avgNiePrzestawionych=0;
        for(int i=0+starter;i<15+starter;i++){
            int dzielnik=1;
            int liczba1;
            int liczba2;
            int cyfra1;
            int cyfra2;
            int suma=0;
            int ileNiePrzestawionych=0;
            for(int j=0;j<4;j++){
                liczba1=codes[i][0];
                liczba2=codes[i][1];
                cyfra1=liczba1/dzielnik;
                cyfra1=cyfra1%10;
                cyfra2=liczba2/dzielnik;
                cyfra2=cyfra2%10;
                dzielnik=dzielnik*10;
                int counter=0;
                if((cyfra1-cyfra2)*(cyfra1-cyfra2)>=25){
                        if(cyfra1>cyfra2){
                            while(cyfra1!=cyfra2){
                                cyfra1=(cyfra1+1)%10;
                                counter++;
                            }
                        }
                        else{
                            while(cyfra1!=cyfra2){
                                cyfra2=(cyfra2+1)%10;
                                counter++;
                            }
                        }
                }
                else{
                    while(cyfra1!=cyfra2){
                        if(cyfra1>cyfra2){
                            cyfra1=(cyfra1-1);
                        }
                        else{
                            cyfra2=(cyfra2-1);
                        }
                        counter++;
                    }
                }
                if(counter==0)
                    ileNiePrzestawionych++;
                else
                    suma=suma+counter;
                przestawienia[i][3-j]=counter;
                System.out.println("ilosc przestawien dla " + (4-j) + " cyfry : " + counter);
            }
            suma2=suma2+suma;
            System.out.println(" W sumie przestawien dla danego roweru: " + suma +" w tym " + ileNiePrzestawionych + " zostalo nienaruszone");
            avgNiePrzestawionych+=ileNiePrzestawionych;
            ileNiePrzestawionych=0;
        }
       // double wynik =
        System.out.println("Srednio w kazdym rowerze " + avgNiePrzestawionych/15.0 + " cyfr nie przestawionych");
        return (suma2/15);
    }

    public static void ileprawidlowo() {
        
    }
    
    public static void entropia() {
        double ed = 3;
        double eg = 12;
        double ld = 9;
        double lg = 6;
        double entrophye=0;
        double entrophyl=0;
        double pr1e = (9.0/15.0)*(11.0/15.0)*(11.0/15.0)*(8.0/15.0)*(8.0/15.0)*(12.0/15.0)*(12.0/15.0); 
        double pr2e = (9.0/15.0)*(11.0/15.0)*(11.0/15.0)*(8.0/15.0)*(6.0/15.0)*(12.0/15.0)*(12.0/15.0); 
        double pr1l = (9.0/15.0)*(9.0/15.0)*(9.0/15.0)*(11.0/15.0)*(11.0/15.0)*(11.0/15.0)*(8.0/15.0)*(8.0/15.0)*(8.0/15.0)*(9.0/15.0); 
        double pr2l = (9.0/15.0)*(9.0/15.0)*(9.0/15.0)*(11.0/15.0)*(11.0/15.0)*(11.0/15.0)*(8.0/15.0)*(9.0/15.0)*(8.0/15.0)*(9.0/15.0); 
        System.out.println("Prawdopodobienstwa elektroniczne : " + pr1e + " i " + pr2e );
        System.out.println("Prawdopodobienstwa lancuchowe : " + pr1l + " i " + pr2l );
        entrophye = - ((pr1e*log2(pr1e))+(pr2e*log2(pr2e)));
        entrophyl = - ((pr1l*log2(pr1l))+(pr2l*log2(pr2l)));
        System.out.println("Entropia elektroniczna : " +  entrophye );
        System.out.println("Entropia lancuchowa : " + entrophyl );
    }
    public static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }
}