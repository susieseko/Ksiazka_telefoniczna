package KT;

import java.sql.*;
import java.util.Scanner;

public class Controller {
    DBConnect dao = new DBConnect();

    public void dodaj(String imie, String nazwisko, String telefon) throws SQLException {
        String sql = "INSERT INTO kontakty(imie, nazwisko, telefon) VALUES (?, ?, ?)";
        PreparedStatement ps = dao.getCon().prepareStatement(sql);
        ps.setString(1, imie);
        ps.setString(2, nazwisko);
        ps.setString(3, telefon);
        ps.execute();
        ps.close();

        System.out.println("Czy zatwierdzasz wprowadzenie nowych danych?");
        Scanner sc = new Scanner(System.in);
        String decyzja = sc.nextLine().toUpperCase();
        if(decyzja.equals("TAK")){
            dao.getCon().commit();
        } else{
            dao.getCon().rollback();
        }
    }

    public void pokaz() throws SQLException {
        String sql = "SELECT * FROM kontakty";
        Statement st = dao.getCon().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            System.out.println("ID: " + rs.getInt("id_kontaktu") + " Imię: " + rs.getString("imie") + " Nazwisko: " + rs.getString("nazwisko") + " Telefon: " + rs.getString("telefon"));
        }
    }

    public void usun(int index) throws SQLException {
       String sql = "DELETE FROM kontakty WHERE id_kontaktu = " + index;
        PreparedStatement ps = dao.getCon().prepareStatement(sql);
        ps.execute();
        ps.close();

        System.out.println("Czy zatwierdzasz usunięcie danej osoby?");
        Scanner sc = new Scanner(System.in);
        String decyzja = sc.nextLine().toUpperCase();
        if(decyzja.equals("TAK")){
            dao.getCon().commit();
        } else{
            dao.getCon().rollback();
        }
    }

    public void zmien(int index, String imie, String nazwisko, String telefon) throws SQLException {
        String sql = "SELECT * FROM kontakty WHERE id_kontaktu = " + index;
        Statement st = dao.getCon().createStatement();
        ResultSet rs = st.executeQuery(sql);
        String dotImie = "", dotNazwisko = "", dotTelefon = "";
        int dotID = 0;

        while(rs.next()){
            dotImie = rs.getString("imie");
            dotNazwisko = rs.getString("nazwisko");
            dotTelefon = rs.getString("telefon");
            dotID = rs.getInt("id_kontaktu");
        }

        st.close();

        String sqlUpdate = "UPDATE kontakty SET imie = ?, nazwisko = ?, telefon = ? WHERE id_kontaktu = ?";
        PreparedStatement ps = dao.getCon().prepareStatement(sqlUpdate);

        ps.setInt(1, dotID);
        if(!imie.equals("")){
            ps.setString(2, imie);
        } else{
            ps.setString(2, dotImie);
        } if(!nazwisko.equals("")){
            ps.setString(3, nazwisko);
        } else{
            ps.setString(3, dotNazwisko);
        } if(!telefon.equals("")){
            ps.setString(4, telefon);
        } else{
            ps.setString(4, dotTelefon);
        }
        ps.execute();
        ps.close();

        System.out.println("Czy zatwierdzasz zmodyfikowane dane?");
        Scanner sc = new Scanner(System.in);
        String decyzja = sc.nextLine().toUpperCase();
        if(decyzja.equals("TAK")){
            dao.getCon().commit();
        } else{
            dao.getCon().rollback();
        }
    }
}
