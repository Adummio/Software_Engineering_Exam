package Boundary;


import Controller.ISistema;
import ControllerImpl.PersistenceException;
import Entity.Video;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UtenteAbbonatoConsoleBoundary {

  private final Scanner input;
  private final ISistema sistemaVideo;

  public UtenteAbbonatoConsoleBoundary(InputStream in, ISistema sistemaVideo) {
    this.input = new Scanner(in);
    this.sistemaVideo = sistemaVideo;
  }


  private void displayOptions() throws IOException {
      System.out.println("\nMenu\n");
      System.out.println("\n0. Esci dal programma.\n");
      System.out.println("1. Ricerca video\n");
      System.out.println("Scelta: ");
  }

  public void showBoundary() throws IOException{
    while (true) {
        displayOptions();

        String selection = input.nextLine();

        try {
          int optionValue = Integer.parseInt(selection);
          switch (optionValue) {
            case 0:
              input.close();
              return;
            case 1:
              ricercaTestuale();
              break;
            default:
              System.out.print("Nessuna funzione associata alla selezione! Riprova.\n\n");
          }
        } catch (NumberFormatException e) {
          System.err.println("Valore non valido. Riprova.\n");
        } catch (PersistenceException pe) {
          System.err.println("Impossibile accedere ai video. Riprova. \n");
        } 
        
        System.out.println("Premere qualunque tasto per continuare...");
        System.in.read();
    }
  }

  private void ricercaTestuale() throws PersistenceException {
    System.out.println("Inserire il nome del video da cercare: ");
    String testo = input.nextLine();

    List<Video> videoList = sistemaVideo.ricercaTestuale(testo);
    visualizzaContenuto(videoList);
  }
  

  private void visualizzaContenuto(List<Video> videoList) {
    if(videoList.isEmpty())
    {
      System.out.println("\nNessun video trovato.");
    } else {
      System.out.println("Lista dei video trovati :");

      for (Video v : videoList) {
        System.out.println(
            "ID Video : "
                + v.getID()
                + " - Nome : "
                + v.getNome()
                + " - Sport : "
                + v.getSport()
                + " - Data Registrazione : "
                + v.getDataRegistrazione());
      }
    }
  }

}



