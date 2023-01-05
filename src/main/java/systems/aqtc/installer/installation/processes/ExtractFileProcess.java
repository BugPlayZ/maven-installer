package systems.aqtc.installer.installation.processes;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.zip.ZipFile;

public class ExtractFileProcess {

  public ExtractFileProcess(ZipFile zipFile, String destination) {
    File destinationFile = new File(destination);
    if (!destinationFile.exists()) {
      destinationFile.mkdirs();
    }

    zipFile.stream().forEach(zipEntry -> {
      try (InputStream inputStream = zipFile.getInputStream(zipEntry)) {
        File file = new File(destination + File.separator + zipEntry.getName());
        if (zipEntry.isDirectory()) {
          file.mkdirs();
        } else {
          file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file);

        int read;
        while ((read = inputStream.read()) != -1) {
          fileWriter.write(read);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
