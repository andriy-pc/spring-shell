package org.mota.shelldemo.config;

import java.io.IOException;
import java.io.Writer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.CommandContext;
import org.springframework.shell.command.CommandRegistration;

@Configuration
public class CommandsConfig {

  @Bean
  CommandRegistration commandRegistration() {
    return CommandRegistration.builder()
        .command("multiply")
        .description("multiply two numbers")
        .withTarget().consumer(this::sum)
        .and()
        .withOption().shortNames('a').description("first number to multiply").and()
        .withOption().shortNames('b').description("second number to multiply").and()
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
