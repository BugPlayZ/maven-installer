package systems.aqtc.installer.installation.processes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class LoadMavenInfoProcess {
  private String mavenVersion = null;

  public LoadMavenInfoProcess() {
    String mavenUrl = "https://maven.apache.org/download.cgi";
    try {
      URL url = new URL(mavenUrl);
      URLConnection connection = url.openConnection();

      try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
          if (line.contains(("Downloading Apache Maven"))) {
            line = line.replaceAll("<\\/?\\w[^>]*>|&\\w+", "");

            this.mavenVersion = line.split(" ")[3];
            System.out.println(mavenVersion);

            break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String get() {
    return this.mavenVersion;
  }
}
