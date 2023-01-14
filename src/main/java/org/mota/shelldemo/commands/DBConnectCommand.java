package org.mota.shelldemo.commands;

import org.springframework.stereotype.Component;

@Component
public class DBConnectCommand {

  private boolean connected;

  public boolean connectToDb(String host, Integer port, String user, String password) {
    System.out.printf("Host: %s, port: %d, user: %s, password: ****", host, port, user);
    //logic is omitted
    connected = true;
    return true;
  }

  public boolean isConnected() {
    return connected;
  }

}
