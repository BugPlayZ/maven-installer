package systems.aqtc.installer;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;
import systems.aqtc.installer.installation.IInstallationStep;
import systems.aqtc.installer.installation.steps.LicenseAgreementStep;

@Getter
public class Installer {
  public static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

  @Getter
  private static Installer instance;

  public void start() {
    instance = this;

    System.out.println("                               \n"
        + " _____ _____ _____ _____ _____ \n"
        + "|     |  _  |  |  |   __|   | |\n"
        + "| | | |     |  |  |   __| | | |\n"
        + "|_|_|_|__|__|\\___/|_____|_|___|\n"
        + "                               ");
    this.triggerStep(new LicenseAgreementStep());
  }

  public void triggerStep(IInstallationStep step) {
    step.displayInstructions();

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    step.handleInput(input);
  }
}
