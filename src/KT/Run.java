package KT;

import java.sql.SQLException;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws SQLException {
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);
        String menu, imie, nazwisko, telefon;
        int index;

        while(true){
            System.out.println("D-dodaj, P-pokaż, U-usuń, Z-zmień, K-koniec");
            menu = sc.nextLine().toUpperCase();

            if(menu.equals("D")){
                System.out.println("Podaj imię:");
                imie = sc.nextLine();
                System.out.println("Podaj nazwisko:");
                nazwisko = sc.nextLine();
                System.out.println("Podaj numer telefonu:");
                telefon = sc.nextLine();
                controller.dodaj(imie, nazwisko, telefon);
            } else if(menu.equals("P")){
                controller.pokaz();
            } else if(menu.equals("U")){
                System.out.println("Podaj numer ID osoby, którą chcesz usunąć:");
                index = sc.nextInt();
                sc.nextLine();
                controller.usun(index);
            } else if(menu.equals("Z")){
                System.out.println("Podaj numer ID osoby, którą chcesz usunąć:");
                index = sc.nextInt();
                sc.nextLine();
                System.out.println("Podaj nowe imię:");
                imie = sc.nextLine();
                System.out.println("Podaj nowe nazwisko:");
                nazwisko = sc.nextLine();
                System.out.println("Podaj nowy numer telefonu:");
                telefon = sc.nextLine();
                controller.zmien(index, imie, nazwisko, telefon);
            } else if(menu.equals("K")){
                System.out.println("Koniec pracy aplikacji");
                break;
            } else{
                System.out.println("Nierozpoznana opcja menu.");
            }
        }
    }
}
