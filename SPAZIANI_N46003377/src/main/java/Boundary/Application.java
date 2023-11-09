package Boundary;

import Boundary.ApplicationConsoleBoundary.TerminationState;

public class Application {

  public static void main(String[] args) {
    ApplicationConsoleBoundary applicationConsoleBoundary = new ApplicationConsoleBoundary(System.in, System.out);

    TerminationState exitStatus = applicationConsoleBoundary.runApplication();

    if (exitStatus == TerminationState.CORRECT_TERMINATION) {
      System.exit(0);
    } else {
      System.exit(-1);
      }
  }

}
