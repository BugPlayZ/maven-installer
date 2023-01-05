package systems.aqtc.installer.installation.steps;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipFile;
import lombok.SneakyThrows;
import systems.aqtc.installer.Installer;
import systems.aqtc.installer.installation.IInstallationStep;
import systems.aqtc.installer.installation.processbar.ProcessBar;
import systems.aqtc.installer.installation.processes.GetFileSizeProcess;
import systems.aqtc.installer.installation.processes.LoadMavenInfoProcess;

public class LoadMavenInfoStep implements IInstallationStep {

  private String fileVersion = null;

  public void displayInstructions() {
    System.out.println("Loading Maven info...");
    this.fileVersion = new LoadMavenInfoProcess().get();
    System.out.println("Maven version: " + this.fileVersion);
    System.out.println("Press enter to continue...");
  }

  @SneakyThrows
  @Override
  public void handleInput(String input) {
    System.out.println("Loading Download Info...");
    ProcessBar processBar = new ProcessBar(2, true, 50);
    long size = new GetFileSizeProcess(
        new URL(Installer.BASE_URL.replace("{VERSION}", fileVersion))).get();

    processBar.nextStep("Download size: " + size / 1024 / 1024 + "MB");
    processBar.nextStep("Download URL: " + Installer.BASE_URL.replace("{VERSION}", fileVersion));
    System.out.println();

    processBar = new ProcessBar((int) size, true, 50);

    processBar.nextStep("Downloading...");

    byte[] buffer = new byte[2048];
    int bytesRead;
    long totalBytesRead = 0;

    try (InputStream in = new URL(Installer.BASE_URL.replace("{VERSION}",
        fileVersion)).openStream(); FileOutputStream fileOutputStream = new FileOutputStream(
        "maven.zip")) {

      while ((bytesRead = in.read(buffer)) != -1) {
        totalBytesRead += bytesRead;

        fileOutputStream.write(buffer, 0, bytesRead);
        processBar.nextStep("Downloaded " + totalBytesRead + " bytes", (int) totalBytesRead);
      }
    }

    System.out.println();
    Installer.getInstance().triggerStep(new ExtractMavenStep(new File("maven.zip")));
  }
}
