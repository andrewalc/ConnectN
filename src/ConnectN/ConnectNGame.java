package ConnectN;

import java.io.InputStreamReader;
import java.util.Arrays;

public class ConnectNGame {

  public static void main(String[] args) {
    IConnectNController controller = new ConnectNController(new InputStreamReader(System.in),
            System.out);
    IConnectN game = new ConnectNModel(controller.getN(), controller.getWidth(),
            controller.getHeight(), controller.getPlayers());

    controller.startGame(game);
  }
}
