package quadri;

import esposizioni.Mostra;
import esposizioni.Quadro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    //importo il driver per il database
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {
        //inizializzo le variabili per la mostra
        String cm = null;
        String nome = null;
        int anno = 0;
        String organizzatore = null;

        // dichiaro le variabili per la connessione al database
        try{

            Connection conn = null;
            Statement stmt = null;
            // dichiaro la variabile per la connessione al database
            String URL = "jdbc:mysql://localhost/ifts_2023_esposizioni  ";
            //
            Properties info = new Properties( );
            info.put( "user", "root" );
            info.put( "password", "root" );
            info.put( "autoReconnect", "true" );
            info.put( "useSSL", "false" );
            info.put( "serverTimezone", "Europe/Amsterdam" );

            //STEP2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP3: Open a connection
            System.out.println("Tentativo di connessione al database...");
            conn = DriverManager.getConnection(URL, info);
            System.out.println("Connessione stabilita\n");

            // dichiaro i dati dello studente ricercato
            System.out.println("inserisci il codice della mostra da cercare");
            Scanner input = new Scanner(System.in);
            cm = input.nextLine();
            System.out.println("inserisci il nome da cercare");
            nome = input.nextLine();
            System.out.println("inserisci l'anno da cercare");
            anno = input.nextInt();
            System.out.println("inserisci l'organizzatore");
            organizzatore = input.nextLine();

            // dichiaro se voglio eliminare la mostra trovata, o se voglio solo modificarne i dati
            boolean eliminaMostra = false;

            //creo l'oggetto Mostra
            Mostra mostra = new Mostra(conn, cm);
            //verifico l'esistenza della mostra
            if(mostra.mostraEsiste){
                System.out.println("Trovata la "+mostra);
                //se la variabile eliminaMostra è true, elimino la mostra
                if (eliminaMostra){
                    System.out.println("Procedo con la sua eliminazione...");
                    boolean eliminationResult = mostra.eliminaMostra(conn);
                    //se la variabile eliminationResult è true, la mostra è stata eliminata
                    if(eliminationResult){
                        System.out.print("Eliminata la ");
                    }else{
                        System.out.print("ERRORE!!! Con dei quadri esposti e' impossibile eliminare la ");
                    }
                }else{
                //aggiorna i dati in locale
                    System.out.println("Procedo con l'aggiornamento dei dati...");
                    mostra.cm = cm;
                    mostra.nome = nome;
                    mostra.anno = anno;
                    mostra.organizzatore = organizzatore;
                    mostra.aggiornaMostra(conn);
                    System.out.print("Aggiornati i dati della ");
                }
            }else {
                System.out.println("Mostra non trovata: procedo con l'inserimento dei dati");
                mostra.cm = cm;
                mostra.nome = nome;
                mostra.anno = anno;
                mostra.organizzatore = organizzatore;
                mostra.inserisciMostra(conn);
                System.out.print("Inserita la nuova ");
            }
            System.out.println(mostra);
            System.out.println("inserisci il codice quadro (CQ), l'autore e il periodo");
            String cq = input.nextLine();
            String autore = input.nextLine();
            String periodo = input.nextLine();
            /* String cq = "Q12";
            String autore = "Autore2";
            String periodo = "Periodo5";
            */
            // dichiaro se voglio eliminare la mostra trovata, o se voglio solo modificarne i dati
            boolean eliminaQuadro = false;

            // qui vado a creare l'oggetto Quadro quadro
            esposizioni.Quadro quadro = new Quadro(conn, cq);

            if(quadro.quadroEsiste){
                System.out.println("Trovato il "+quadro);
                if (eliminaQuadro){
          /*
          System.out.println("Procedo con la sua eliminazione...");
          boolean eliminationResult = quadro.eliminaQuadro(conn);
          if(eliminationResult){
            System.out.print("Eliminato il ");
          }else{
            System.out.print("ERRORE!!! Impossibile eliminare il quadro ");
          }*/
                    System.out.print("ERRORE!!! Impossibile eliminare il quadro ");
                }else{
                    System.out.print("ERRORE!!! Impossibile inserire il quadro perché già esistente");
          /*
          System.out.println("Procedo con l'aggiornamento dei dati...");
          quadro.cq = cm;
          quadro.autore = autore;
          quadro.periodo = periodo;
          quadro.aggiornaQuadro(conn);
          System.out.print("Aggiornati i dati del ");*/
                }
            }else {
                System.out.println("Quadro non trovato: procedo con l'inserimento dei dati");
                quadro.cq = cq;
                quadro.autore = autore;
                quadro.periodo = periodo;
                quadro.inserisciQuadro(conn);
                System.out.print("Inserito il nuovo ");
            }
            System.out.println(quadro);

        } catch (Exception e) {
            System.out.println("Errore: " + e);
        }

    }
}
