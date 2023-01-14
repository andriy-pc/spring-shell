package org.mota.shelldemo.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class MyCommands {

  /*
  The programmatic model is how things are actually registered,
  even if you use annotations. The @ShellMethod and @ShellOption
  annotations are a legacy feature that we do not yet want to remove.
  CommandRegistration is the new development model where new features
  are added. We are most likely going to replace existing annotations
  with something better, to support new features in a CommandRegistration model.
  */

  @ShellMethod("Sum two integers")
  public int add(
      @ShellOption("-a") int a,
      @ShellOption("-b") int b) {
    return a + b;
  }

}
