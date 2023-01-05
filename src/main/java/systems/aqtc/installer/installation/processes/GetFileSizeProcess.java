package systems.aqtc.installer.installation.processes;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetFileSizeProcess {
  private final URL url;

  public long get() {
    URLConnection conn = null;
    try {
      conn = url.openConnection();

      if (conn instanceof HttpURLConnection) {
        ((HttpURLConnection) conn).setRequestMethod("HEAD");
      }

      conn.getInputStream();
      return conn.getContentLength();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (conn instanceof HttpURLConnection) {
        ((HttpURLConnection) conn).disconnect();
      }
    }
  }
}
