package Universita;

import java.sql.*;
import java.util.*;


import java.sql.*;
import java.util.Properties;

public class Main {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	public static void main(String[] args) throws Exception {
		try {
			Connection con = null;
			Statement stmt = null;
			Class.forName(JDBC_DRIVER);
			String URL = "jdbc:mysql://localhost/ifts_universita"; //il nome del tuo database
			Properties info = new Properties();
			info.put("user", "root"); // il tuo username di MySQL
			info.put("password", "root"); // la tua password sempre di MySql
			info.put("autoReconnect", "true");
			info.put("useSSL", "false");
			info.put("serverTimezone", "Europe/Amsterdam");
			con = DriverManager.getConnection(URL, info); //inizializzazione della connessione
			System.out.println("Creating statement..."); // da qui si crea lo Statement
			stmt = con.createStatement();
			String sql;
			String m = "M2";
			sql = "SELECT studente.matr," +
					" studente.s_nome, " +
					" studente.corsocitta, " +
					" studente.a_corso " +
					" FROM studente ";
			ResultSet rs = stmt.executeQuery(sql); //esegue la query
			while (rs.next()) {
				String matr = rs.getString("matr");
				String nome = rs.getString("s_nome");
				String citta = rs.getString("corsocitta");
				Integer corso = rs.getInt("a_corso");

				System.out.println("Matr: " + matr);
				System.out.println("Nome: " + nome);
				System.out.println("Città: " + citta);
				System.out.println("Corso: " + corso);

			}
			Studente stud = new Studente(con, m); // creazione dell'oggetto Studente, per accorciare le righe di sopra
			System.out.println(stud);
			if (stud.studenteEsiste == true) { // se lo studente esiste, lo cercherà nel database e lo manderà in output
				System.out.println("Trovato lo " + stud);

			} else {
				System.out.println("Studente con matricola " + m + "non trovato"); // butterà fuori un oggetto vuoto
			}
			stud.matr = "M18";
			stud.nome = "Oronzo Canà";
			stud.citta = "FE";
			stud.anno = 2;

			stud.inserisciStudente(con);
			Studente stud1 = new Studente(con, stud.matr);
			if (stud1.studenteEsiste == true) {

				System.out.println("Inserito" + stud1);
			} else {

				System.out.println("Non trovato" + stud1.matr);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}


