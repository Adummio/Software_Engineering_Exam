package Boundary;


import ControllerImpl.SistemaImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class ApplicationConsoleBoundary {

  private final InputStream in;
  private PrintWriter consoleWriter;

  public ApplicationConsoleBoundary(InputStream in, OutputStream out) {
    this.in = in;
    this.consoleWriter = new PrintWriter(out);
  }

  /**
   * @throws IOException
   *
   */
  private void Login() {

  }

  /**
   *
   */
  private void Logout() {
  }

  /**
   * @return
   * @throws IOException
   */
  public TerminationState runApplication()  {
    UtenteAbbonatoConsoleBoundary utenteAbbonatoConsoleBoundary = new UtenteAbbonatoConsoleBoundary(in, SistemaImpl.getInstance());

    try {
      utenteAbbonatoConsoleBoundary.showBoundary();
      return TerminationState.CORRECT_TERMINATION;
    } catch (IOException e) {
      handleException(e);
      return TerminationState.ABNORMAL_TERMINATION;
    }
  }

  /**
   * @param e
   */
  public void handleException(Exception e) {
    consoleWriter.format("Attenzione: Errore fatale nell'esecuzione: %s", e.getMessage());
    consoleWriter.flush();
  }

  public static enum TerminationState {
    CORRECT_TERMINATION,
    ABNORMAL_TERMINATION
  };

}