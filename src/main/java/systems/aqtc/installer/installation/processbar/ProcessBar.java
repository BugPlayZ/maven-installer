package systems.aqtc.installer.installation.processbar;

import systems.aqtc.installer.Installer;

public class ProcessBar {
  private final int totalSteps;
  private final boolean showPercentage;
  private final int maxLength;
  private int currentStep;
  private String currentStepName = null;

  public ProcessBar(int totalSteps, boolean showPercentage, int maxLength) {
    this.totalSteps = totalSteps;
    this.showPercentage = showPercentage;
    this.maxLength = maxLength;
  }

  public void nextStep(String stepName) {
    this.currentStep++;
    this.currentStepName = stepName;

    this.display();
  }

  public void nextStep(String stepName, int step) {
    this.currentStep = step;
    this.currentStepName = stepName;

    this.display();
  }

  public void display() {
    int percentage = (int) (((double) this.currentStep / (double) this.totalSteps) * 100);

    System.out.print("\r");
    System.out.print("[");

    boolean lengthLimited = this.maxLength != -1;
    int lineLength = !lengthLimited ? (this.showPercentage ? 101 : this.totalSteps) : this.maxLength;
    int currentLength = (int) (((double) this.currentStep / (double) this.totalSteps) * lineLength);

    for (int i = 0; i < this.maxLength; i++) {
      if (i < currentLength) {
        System.out.print("=");
      } else if (i == currentLength) {
        System.out.print(">");
      } else {
        System.out.print(" ");
      }
    }
    System.out.print("]");

    System.out.print(
        " " + (this.showPercentage ? percentage + "%" : this.currentStep + "/" + this.totalSteps)
            + " " + (this.currentStepName != null ? this.currentStepName : ""));
  }
}
