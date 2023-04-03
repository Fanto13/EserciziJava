package Universita;

import java.sql.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement stmt = null;
		
		String query = "SELECT * FROM s ";
		
		String URL = "jdbc:mysql://localhost/ifts_universita";
		Properties info = new Properties( );
		info.put( "user", "root" );
		info.put( "password", "root" );
		info.put( "autoReconnect", "true" );
		info.put( "useSSL", "false" );
		info.put( "serverTimezone", "Europe/Amsterdam" );
		
	      Class.forName("com.mysql.cj.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      //conn = DriverManager.getConnection("jdbc:mysql://localhost/universita?autoReconnect=true&useSSL=false&serverTimezone=Europe/Amsterdam","root","root");
	      conn = DriverManager.getConnection(URL, info);

	      stmt = conn.createStatement();
	      ResultSet rs = stmt.executeQuery(query);
		  /*
		if (rs.next()) {
			this.codc = rs.getString("codc");
			this.nome_cliente = rs.getString("nome_cliente");
			this.citta = rs.getString("citta");
			this.clienteEsiste = true;
		} else {
			codc = "";
			nome_cliente = "";
			citta = "";
			clienteEsiste = false;
		}

		if (cliente.clienteEsiste == true) { // se lo studente esiste, lo cercherà nel database e lo manderà in output
			System.out.println("Trovato lo " + cliente);

		} else {
			System.out.println("Studente con matricola " + codc + "non trovato"); // butterà fuori un oggetto vuoto
		}

		if (!rs.next()) {
			sql = "INSERT INTO cliente(codc, nome_cliente, citta) VALUES ('" + this.codc + "'" + ",'" + this.nome_cliente + "' ," + "'" + this.citta + "');";
			stmt.executeUpdate(sql);
		} else {
			System.out.println("Cliente già esistente");
			sql = "UPDATE cliente SET  citta = '" + this.citta + "' , nome_cliente = '" + this.nome_cliente + "' WHERE codc = '" + this.codc + "' ;";
			stmt.executeUpdate(sql);
		}

		public void removeClient(Connection cn) throws Exception {
			try {
				stmt = cn.createStatement();
				sql = "SELECT codc FROM cliente WHERE codc = '" + this.codc + "'";
				rs = stmt.executeQuery(sql);
				if (!rs.next()) {
					System.out.println("Studente non trovato");
				} else {
					sql = "DELETE FROM cliente WHERE codc = '" + this.codc + "'";
					int result = stmt.executeUpdate(sql);
					if (result > 0) {
						System.out.println("Cliente eliminato con successo");
					} else {
						System.out.println("Nessun cliente eliminato");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			/*
		   */
	      while(rs.next()){
		         //Retrieve by column name
	    	  
	    	     //System.out.print(rs.getString("MATR"));
	    	     
		    	 String  matr  = rs.getString("MATR");
		         String  nome = rs.getString("SNOME");
		         String citta = rs.getString("CITTA");
		         int anno = rs.getInt("ACORSO");

		         if (anno == 1){
		         //Display values
		         System.out.print("MATR: " + matr);
		         System.out.print(", NOME: " + nome);
		         System.out.print(", Città: " + citta);
		         System.out.println(", Anno: " + anno);
			      }

//	             pw.println ("MATR: " + matr + ", NOME: " + nome + ", Citt�: " + citta + ", Anno: " + anno);
		         
		      }
	      
	      
	      query = query + " WHERE MATR = 'M11'";
	      
	      rs = stmt.executeQuery(query);

	      if(rs.next()){
          // lo studente esiste
	    	  query = "UPDATE s SET ACORSO = 2 WHERE MATR = 'M11';";
	    	  //query = "DELETE FROM s MATR = 'M11';";
	    	  
	    	  
	      }
	      else
	      {
	          // lo studente non esiste
	    	  query = "INSERT INTO s (MATR, SNOME, CITTA, ACORSO) VALUES ('M11', 'Maurizio Vincini', 'MO', 1);";
	      }
    	  int res = stmt.executeUpdate(query);
	       
	        System.out.println("Ho aggiornato M11 ! numero di tuple coivolte: " + res);
	       
	        
	        Studente s1 = new Studente(conn, "M2");
	        
	        if(s1.studenteEsiste == true)
	        	System.out.println(s1.toString());
	        else
		        System.out.println("Non ho trovato lo studente !");
	        
	        //s1.matr = "M25";
	        s1.nome = "Luca Bianchi";
	        s1.citta = "Modena";
	        s1.anno = 3;
	        
	        
	        //s1.nome = "Lucia Quaranta";
	        //s1.citta = "SA";
	        //s1.anno = 1;

	        s1.aggiornaStudente(conn);
        	System.out.println(s1.toString());

        	
	        s1.eliminaStudente(conn);
        	System.out.println(s1.toString());
        	
	        //System.out.println("Matr: " + s1.matr + ", Nome: "  + s1.nome);

	 //   	for(Esame e : s1.listaEsami){
	 //   		System.out.println("Esame: " + e.NomeCorso);
	 //   	}	

	       conn.close();
	        
	        System.out.println("Goodbye!");
		
	}

}
