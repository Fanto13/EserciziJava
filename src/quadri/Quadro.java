package quadri;

import java.sql.*;

public class Quadro {
    public String cq;
    public String autore;
    public String periodo;
    public String salaEsposizione;
    public boolean quadroEsiste;

    //faccio il costruttore con i parametri completi
    public Quadro(String cq, String autore, String periodo, String salaEsposizione) {
        this.cq = cq;
        this.autore = autore;
        this.periodo = periodo;
        this.salaEsposizione = salaEsposizione;
        this.quadroEsiste = true;

    }
    //costruttore senza sala esposizione
    public Quadro(String cq, String autore, String periodo){
        this.cq = cq;
        this.autore = autore;
        this.periodo = periodo;
        this.salaEsposizione = null;
        this.quadroEsiste = true;
    }

    public Quadro(Connection cn, String codQuadro) throws SQLException {
        String sql = "SELECT quadro.cq, " +
                " quadro.autore, " +
                " quadro.periodo " +
                " FROM quadro " +
                " WHERE quadro.cq = ?";

        PreparedStatement prstmt = cn.prepareStatement(sql);
        prstmt.setString(1,codQuadro);

        ResultSet rs = prstmt.executeQuery();

        if (rs.next()){
          //Retrieve by column name
          this.cq = rs.getString("cq");
          this.autore = rs.getString("autore");
          this.periodo = rs.getString("periodo");
          this.quadroEsiste = true;
        }
        // Clean-up environment
        prstmt.close();
        rs.close();
      }



    public void inserisciQuadro(Connection cn) throws Exception {
        String sql = "INSERT INTO quadro (cq, autore, periodo) VALUES " +
                "('" + this.cq + "', '" + this.autore + "', '" + this.periodo + "')";
        executeQuery(sql, cn);
      }
    private void executeQuery(String query, Connection cn) throws SQLException {
        PreparedStatement prstmt = null;
        String sql = "SELECT * FROM quadro";
        if(this.cq.length()>0) {
            sql = sql + " WHERE cq = ?";
            prstmt = cn.prepareStatement(sql);
            prstmt.setString(1,this.cq);
            ResultSet rs = prstmt.executeQuery();
            prstmt = cn.prepareStatement(query);
            prstmt.executeUpdate();
        }
        else System.out.println("Codice quadro non valido per l'aggiornamento...");
    }

      public void modificaQuadro(Connection cn) throws Exception {
        String sql = "UPDATE quadro SET " +
                "autore = '" + this.autore + "', " +
                "periodo = '" + this.periodo + "' " +
                "WHERE cq = '" + this.cq + "'";
        executeQuery(sql, cn);
      }

      public void eliminaQuadro(Connection cn) throws Exception {
        String sql = "DELETE FROM quadro WHERE cq = '" + this.cq + "'";
        executeQuery(sql, cn);
      }



      public String toString() {
        return "Codice quadro: " + this.cq + "\n" +
                "Autore: " + this.autore + "\n" +
                "Periodo: " + this.periodo + "\n" +
                "Sala esposizione: " + this.salaEsposizione + "\n";
      }

    }//fine classe quadro



