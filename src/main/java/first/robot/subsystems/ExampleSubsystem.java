// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot.subsystems;

import org.wpilib.command2.Command;
import org.wpilib.command2.PrintCommand;
import org.wpilib.command2.SubsystemBase;
import org.wpilib.system.Timer;
import org.wpilib.tunable.Selectable;
import org.wpilib.tunable.Tunables;
import org.wpilib.util.Alert;
import org.wpilib.util.Alert.Level;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.Pigeon2;

public class ExampleSubsystem extends SubsystemBase {

  private final Pigeon2 pigeon;
  private final Alert alert1;
  private final Alert alert2;
  private final Alert alert3;
  private final Alert alert4;
  private double timestamp1;
  private double timestamp2;
  private double timestamp3;
  private double timestamp4;

  private final Selectable<Command> autoChooser = new Selectable<Command>();

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    pigeon = new Pigeon2(0, new CANBus("can_s2"));
    alert1 = new Alert("alert-1", "Alert 1!", Level.LOW);
    alert1.set(true);
    timestamp1 = Timer.getTimestamp();
    alert2 = new Alert("alert-2", "Alert 2!", Level.MEDIUM);
    alert2.set(true);
    timestamp2 = Timer.getTimestamp();
    alert3 = new Alert("alert-3", "Alert 3!", Level.HIGH);
    alert3.set(true);
    timestamp3 = Timer.getTimestamp();
    alert4 = new Alert("alert-4", "Alert 4!", Level.LOW);
    alert4.set(true);
    timestamp4 = Timer.getTimestamp();

    autoChooser.addDefault("Default Auto", new PrintCommand("Default Auto"));
    autoChooser.add("Option 1", new PrintCommand("Option 1"));
    autoChooser.add("Option 2", new PrintCommand("Option 2"));
    Tunables.publish("Auto Chooser", autoChooser);
  }

  public void toggleAlert1() {
    alert1.set(!alert1.get());
    timestamp1 = Timer.getTimestamp();
  }

  public void toggleAlert2() {
    alert2.set(!alert2.get());
    timestamp2 = Timer.getTimestamp();
  }

  public void toggleAlert3() {
    alert3.set(!alert3.get());
    timestamp3 = Timer.getTimestamp();
  }

  public void toggleAlert4() {
    alert4.set(!alert4.get());
    timestamp4 = Timer.getTimestamp();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    final double currentTime = Timer.getTimestamp();
    if (currentTime > timestamp1 + 8.0) {
      toggleAlert1();
    }
    if (currentTime > timestamp2 + 4.0) {
      toggleAlert2();
    }
    if (currentTime > timestamp3 + 2.0) {
      toggleAlert3();
    }
    if (currentTime > timestamp4 + 1.0) {
      toggleAlert4();
    }
  }
}
