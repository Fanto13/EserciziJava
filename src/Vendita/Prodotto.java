package Vendita;

import java.sql.*;
public class Prodotto {
    private String codp;
    private String descrizione;
    private int prezzo;
    Statement stmt = null;
    public Prodotto(String codp) {
        this.codp = codp;
    }
    public Prodotto(){
        this.codp ="";
        this.descrizione="";
        this.prezzo=0;
    }
    public String getCodp() {
        return codp;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public int getPrezzo() {
        return prezzo;
    }
    public String toString() {
        return "Codice prodotto: " + codp + "\nDescrizione: " + descrizione + "\nPrezzo: " + prezzo;
    }
    public String visualizza(Connection con) {
        String result = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM prodotto WHERE codp = '" + codp + "'");
            if (rs.next()) {
                result = "Codice prodotto: " + rs.getString("codp")
                        + " | Descrizione: " + rs.getString("descrizione")
                        + " | Prezzo: " + rs.getInt("prezzo")+ "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}