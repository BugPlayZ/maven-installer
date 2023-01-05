package systems.aqtc.installer.installation;

public interface IInstallationStep {
  void displayInstructions();
  void handleInput(String input);
}