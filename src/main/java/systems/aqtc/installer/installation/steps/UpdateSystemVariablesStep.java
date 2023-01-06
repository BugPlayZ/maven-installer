package systems.aqtc.installer.installation.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import systems.aqtc.installer.installation.IInstallationStep;
import systems.aqtc.installer.utils.WindowsUtils;

@AllArgsConstructor
public class UpdateSystemVariablesStep implements IInstallationStep {

  private final String mavenPath;

  @Override
  public void displayInstructions() {
    System.out.println("Updating system variables...");

    WindowsUtils.setEnvironmentVariable("MAVEN_HOME", this.mavenPath,
        !this.mavenPath.contains("\\Users\\"));

    List<String> paths = Arrays.asList(System.getenv("PATH").split(";"));
    paths.add(this.mavenPath + "\\bin");

    WindowsUtils.setEnvironmentVariable("PATH", String.join(";", paths),
        !this.mavenPath.contains("\\Users\\"));

    System.out.println("System variables have been updated! Press enter to continue...");
  }

  @Override
  public void handleInput(String input) {
    for (int i = 0; i < 10; i++) {
      System.out.println();
    }

    System.out.println("Installation complete!");
  }
}
