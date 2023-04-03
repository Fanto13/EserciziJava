package Vendita;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Fornisce {
    private String codp;
    private String codf;
    private int quantita;
    Statement stmt = null;
    public Fornisce(String codp, String codf, int quantita) {
        this.codp = codp;
        this.codf = codf;
        this.quantita = quantita;
    }
    public String getCodp() {
        return codp;
    }
    public String getCodf() {
        return codf;
    }
    public int getQuantita() {
        return quantita;
    }
        public static void stampaForniture(String codp, Connection con) {
            try {
                //faccio la query A
                System.out.println("I dati del fornitore per il codice fornito:");
                String sql = "SELECT codp, codf, SUM(qty) AS totale_qty FROM fornisce WHERE codp = '" + codp + "' GROUP BY codp, codf";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    codp = rs.getString("codp");
                    String codf = rs.getString("codf");
                    int totale_qty = rs.getInt("totale_qty");
                    System.out.println("Codice prodotto: " + codp + " Codice fornitore: " + codf + ", Quantità totale: " + totale_qty);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

