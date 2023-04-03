package Vendita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;

        String URL = "jdbc:mysql://localhost/vendite";
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "root");
        info.put("autoReconnect", "true");
        info.put("useSSL", "false");
        info.put("serverTimezone", "Europe/Amsterdam");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection connection = ds.getConnection();
        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(URL, info);

        //
        System.out.println("Connected to database correctly");

        // creazione oggetto Vendita.Prodotto con codice P1
        Prodotto prodotto = new Prodotto("P1");

        System.out.println(prodotto.visualizza(conn));


        // esecuzione Query B e visualizzazione risultati
        try {
            //faccio la query B
            System.out.println("Ecco ora l'output della query B:");
            String sql = "SELECT codf, anno, SUM(qty) AS totale_qty FROM fornisce GROUP BY anno, codf";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int anno = rs.getInt("anno");
                String codf = rs.getString("codf");
                int totale_qty = rs.getInt("totale_qty");
                System.out.println("Codice fornitore: " + codf + " Anno: " + anno + ", Quantità totale: " + totale_qty);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
    }
}



