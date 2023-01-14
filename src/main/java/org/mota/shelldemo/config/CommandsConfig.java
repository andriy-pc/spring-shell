package org.mota.shelldemo.config;

import static org.springframework.shell.Availability.available;
import static org.springframework.shell.Availability.unavailable;

import java.io.IOException;
import java.io.Writer;
import org.mota.shelldemo.commands.DBConnectCommand;
import org.mota.shelldemo.commands.DBSelectCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.CommandContext;
import org.springframework.shell.command.CommandRegistration;

@Configuration
public class CommandsConfig {

  @Autowired
  private DBConnectCommand dbConnectCommand;
  @Autowired
  private DBSelectCommand dbSelectCommand;

  @Bean
  CommandRegistration multiplyCommandRegistration() {
    return CommandRegistration.builder()
        .command("multiply")
        .description("multiply two numbers")
        .withTarget().consumer(this::sum)
        .and()
        .withOption().shortNames('a').description("first number to multiply").and()
        .withOption().shortNames('b').description("second number to multiply")
        .and()
        .build();
  }

  @Bean
  CommandRegistration dbConnectCommandRegistration() {
    return CommandRegistration.builder()
        .command("db-connect")
        .description("dummy stub: connects to DB")
        .withTarget().method(dbConnectCommand, "connectToDb",
            new Class[]{String.class, Integer.class, String.class, String.class})
        .and()
        .withOption().longNames("host").shortNames('h').description("host of DB server").required()
        .defaultValue("localhost").and()
        .withOption().longNames("port").shortNames('p').type(Integer.class)
        .description("port of DB server").required().and()
        .withOption().longNames("username").shortNames('U').description("DB username").required()
        .and()
        .withOption().longNames("password").shortNames('P').description("DB user's password")
        .required().and()
        .build();
  }

  @Bean
  CommandRegistration dbSelectCommandRegistration() {
    return CommandRegistration.builder()
        .command("db-select")
        .description("dummy stub: select users to DB")
        .withTarget().consumer(context -> dbSelectCommand.select(context)).and()
        .availability(() -> dbConnectCommand.isConnected() ? available()
            : unavailable("You should connect to DB first (check `db-connect` command)"))
        .build();
  }

  public void sum(CommandContext commandContext) {
    int a = commandContext.getOptionValue("a");
    int b = commandContext.getOptionValue("b");
    try (Writer writer = commandContext.getTerminal().writer()) {
      writer.write("a * b = " + (a + b));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
