package KT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Controller {
    DBConnect dao = new DBConnect();

    public void dodaj(String imie, String nazwisko, int telefon) throws SQLException {
        String sql = "INSERT INTO kontakty(imie, nazwisko, telefon) VALUES (?, ?, ?)";
        PreparedStatement ps = dao.getCon().prepareStatement(sql);
        ps.setString(1, imie);
        ps.setString(2, nazwisko);
        ps.setInt(3, telefon);
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

    public void usun(String imie, String nazwisko) throws SQLException {
       String sql = "DELETE FROM kontakty WHERE imie = " + imie + " AND nazwisko = " + nazwisko;
       
    }
}
