package systems.aqtc.installer.installation.steps;

import systems.aqtc.installer.installation.IInstallationStep;
import systems.aqtc.installer.installation.processbar.ProcessBar;
import systems.aqtc.installer.installation.processes.LoadMavenInfoProcess;

public class LoadMavenInfoStep implements IInstallationStep {

  public void displayInstructions() {
    System.out.println("Loading Maven info...");
    String mavenVersion = new LoadMavenInfoProcess().get();
    System.out.println("Maven version: " + mavenVersion);
    System.out.println("Press enter to continue...");
  }

  @Override
  public void handleInput(String input) {
    if (input.equals("")) {
      ProcessBar processBar = new ProcessBar(100, true);
    }
  }
}
