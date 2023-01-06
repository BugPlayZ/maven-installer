package systems.aqtc.installer.utils;

import lombok.SneakyThrows;

public class WindowsUtils {

  public static boolean isWindows() {
    return System.getProperty("os.name").toLowerCase().contains("win");
  }

  @SneakyThrows
  public static void setEnvironmentVariable(String key, String value, boolean machine) {
    Runtime.getRuntime().exec("cmd.exe /c setx " + key + " " + value + (machine ? " /M" : ""));
  }

  private static String[] convertToString(short[] array) {
    String[] parts = new String[array.length];
    for (int i = 0; i < array.length; i++) {
      parts[i] = String.valueOf(array[i]);
    }
    return parts;
  }
}
