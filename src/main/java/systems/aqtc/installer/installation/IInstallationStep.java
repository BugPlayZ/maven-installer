package systems.aqtc.installer.installation;

import java.net.MalformedURLException;

public interface IInstallationStep {
  void displayInstructions();
  void handleInput(String input);
}