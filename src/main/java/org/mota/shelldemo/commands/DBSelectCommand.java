package org.mota.shelldemo.commands;

import java.io.PrintWriter;
import org.springframework.shell.command.CommandContext;
import org.springframework.stereotype.Component;

@Component
public class DBSelectCommand {

  public void select(CommandContext commandContext) {
    try (PrintWriter printWriter = commandContext.getTerminal().writer()) {
      printWriter.println("selected data: []");
    }
  }

}
