package systems.aqtc.installer.installation.steps;

import java.io.File;
import java.util.zip.ZipFile;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import systems.aqtc.installer.Installer;
import systems.aqtc.installer.installation.IInstallationStep;
import systems.aqtc.installer.installation.processes.ExtractFileProcess;

@AllArgsConstructor
public class ExtractMavenStep implements IInstallationStep {

  private final File file;

  @Override
  public void displayInstructions() {
    System.out.println("Where do you wish to extract Maven to? (Default: C:\\maven)");
  }

  @SneakyThrows
  @Override
  public void handleInput(String input) {
    if (input.isEmpty()) {
      input = "C:\\maven";
    }

    System.out.println("Extracting Maven to " + input + "...");
    try (ZipFile zipFile = new ZipFile(file)) {
      new ExtractFileProcess(zipFile, input);
    }

    System.out.println("Maven has been extracted to " + input + "!");
    this.file.delete();
    Installer.getInstance().triggerStep(new UpdateSystemVariablesStep(input));
  }
}