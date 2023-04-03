package Universita;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Studente {
	public String matr, nome, citta;
	public int anno;
	public ArrayList<Esame> listaEsami;
	public boolean studenteEsiste;

	public Studente() { // primo costruttore costruisce lo studente vuoto, OGGETTO VUOTO
		matr = "";
		nome = "";
		citta = "";
		anno = 0;
		studenteEsiste = false;
	}

	public void inserisciStudente(Connection cn) throws Exception {

		try {
			Statement stmt = null;
			stmt = cn.createStatement();
			String sql;
			sql = " SELECT studente.matr FROM studente WHERE studente.matr = '" + this.matr + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				sql = "INSERT INTO studente(matr, s_nome, corsocitta, a_corso) VALUES ('" + this.matr + "'" + ",'" + this.nome + "' ," + "'" + this.citta + "' ," + "'" + this.anno + "')";
				stmt.executeUpdate(sql);
			} else {
				System.out.println("Studente già esistente");
				sql = "UPDATE studente SET s_nome = '" + this.nome + "' , corsocitta = '" + this.citta + "' , a_corso = '" + this.anno + "' WHERE matr = '" + this.matr + "'";
				stmt.executeUpdate(sql);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	@Override
	public String toString() { // metodo to String per mandare in output tutto il contenuto
		return "Studente{" +
				"Matr= '" + matr + '\'' +
				", Nome= '" + nome + '\'' +
				", Città= '" + citta + '\'' +
				", Anno di corso= " + anno +
				'}';
	}

	public Studente(Connection cn, String matricola) throws Exception { // Questo è il secondo costruttore che si connette al database, in modo da creare un Oggetto sempre a portata di mano invece che costruire la connessione sul main principale
		Statement stmt = null;
		stmt = cn.createStatement();
		String sql;

		sql = "SELECT studente.matr," +  // lo spazio è importante
				" studente.s_nome, " +
				" studente.corsocitta, " +
				" studente.a_corso " +
				" FROM studente " +
				" WHERE studente.matr = '" + matricola + "'";

		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			this.matr = rs.getString("matr");
			this.nome = rs.getString("s_nome");
			this.citta = rs.getString("corsocitta");
			this.anno = rs.getInt("a_corso");
			this.studenteEsiste = true;
		} else {
			matr = "";
			nome = "";
			citta = "";
			anno = 0;
			studenteEsiste = false;
		}
		rs.close();
		sql = "SELECT esame.matr," +  // nuova query
				" esame.cc, " +
				" esame.data, " +
				" esame.voto, " +
				" corso.c_nome " +
				" FROM esame, corso " +
				" WHERE corso.cc = esame.cc AND " +
				" esame.matr = '" + matricola + "'";
		rs = stmt.executeQuery(sql);
		this.listaEsami = new ArrayList<Esame>(); // inizializzazione arrayList
		while (rs.next()) {  // creiamo un nuovo oggetto Esame, lanciandogli tutti i campi oggetto della query
			Esame e = new Esame(rs.getString("matr"), rs.getString("cc"), rs.getString("c_nome"), rs.getInt("data"), rs.getString("voto"));
			System.out.println(e);
		}
		rs.close();
		stmt.close();
	}
}