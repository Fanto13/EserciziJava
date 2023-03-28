package quadri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mostra {
    public String cm;
    public String nome;
    public int anno;
    public String organizzatore;
    public ArrayList<Quadro> listaQuadri;
    public boolean mostraEsiste;

    public Mostra() {
        cm = "";
        nome = "";
        anno = 0;
        organizzatore = "";
        listaQuadri = null;
        mostraEsiste = false;
    }
    //ora creo la classe mostra, che prende in ingresso la connessione e il codice della mostra
    public Mostra(Connection cn, String codMostra) throws SQLException {
        //formo la query, e mostra.cm è quello inserito dall'utente
        String sql = "SELECT mostra.cm, " +
                " mostra.nome, " +
                " mostra.anno, " +
                " mostra.organizzatore " +
                " FROM mostra  " +
                " WHERE mostra.cm = ?";


        PreparedStatement prstmt = cn.prepareStatement(sql);
        prstmt.setString(1, codMostra);//setto il primo parametro della query "?" con il valore codMostra
        ResultSet rs = prstmt.executeQuery();//eseguo la query
        if (rs.next()) {
            //Retrieve by column name
            this.cm = rs.getString("cm");
            this.nome = rs.getString("nome");
            this.anno = rs.getInt("anno");
            this.organizzatore = rs.getString("organizzatore");
            this.mostraEsiste = true;
        }
        // Clean-up environment
        prstmt.close();
        rs.close();

        //ora faccio la seconda query
        sql = "SELECT quadro.cq, quadro.autore, quadro.periodo, espone.sala " +
                "FROM quadro, espone, mostra " +
                "WHERE quadro.cq = espone.cq " +
                "AND espone.cm = mostra.cm " +
                "AND espone.cm = ?";

        //
        prstmt = cn.prepareStatement(sql);
        prstmt.setString(1, codMostra);
        rs = prstmt.executeQuery();

        this.listaQuadri = new ArrayList<>();
        while (rs.next()) {
            Quadro quadro = new Quadro(rs.getString("cq"),rs.getString("autore"),
                    rs.getString("periodo"), rs.getString("sala"));
            this.listaQuadri.add(quadro);
        }
    }
    //metodi getter, getter e tostring generati in automatico
    @Override
    public String toString() {
        return "Mostra{" +
                "cm='" + cm + '\'' +
                ", nome='" + nome + '\'' +
                ", anno=" + anno +
                ", organizzatore='" + organizzatore + '\'' +
                ", listaQuadri=" + listaQuadri +
                ", mostraEsiste=" + mostraEsiste +
                '}';
    }

    public String getCm() {
        return cm;
    }

    public void setCm(String cm) {
        this.cm = cm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(String organizzatore) {
        this.organizzatore = organizzatore;
    }

    public ArrayList<Quadro> getListaQuadri() {
        return listaQuadri;
    }

    public void setListaQuadri(ArrayList<Quadro> listaQuadri) {
        this.listaQuadri = listaQuadri;
    }

    public boolean isMostraEsiste() {
        return mostraEsiste;
    }

    public void setMostraEsiste(boolean mostraEsiste) {
        this.mostraEsiste = mostraEsiste;
    }

}
