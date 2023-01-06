package systems.aqtc.installer.installation.processes;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.ZipFile;

public class ExtractFileProcess {

  public ExtractFileProcess(ZipFile zipFile, String destination) {
    File destinationFile = new File(destination);
    if (!destinationFile.exists()) {
      destinationFile.mkdirs();
    }

    zipFile.stream().forEach(zipEntry -> {
      if (zipEntry.getName().split("/").length == 1) {
        return;
      }

      String fileName = Arrays.stream(zipEntry.getName().split("/")).skip(1)
          .reduce((a, b) -> a + "/" + b).get();

      try (InputStream inputStream = zipFile.getInputStream(zipEntry)) {
        File file = new File(destination + File.separator + fileName);

        if (zipEntry.isDirectory()) {
          file.mkdirs();
        } else {
          file.createNewFile();
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
          int read;
          while ((read = inputStream.read()) != -1) {
            fileWriter.write(read);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
