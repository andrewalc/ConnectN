package ConnectN;

import java.io.InputStreamReader;
import java.util.Arrays;

public class ConnectNGame {

  public static void main(String[] args) {
    IConnectN game = new ConnectNModel();
    IConnectNController controller = new ConnectNController(new InputStreamReader(System.in),
            System.out);
    IConnectN game2 = new ConnectNModel(controller.getN(), controller.getWidth(),
            controller.getHeight(), controller.getPlayers());

    controller.startGame(game2);
  }
}
