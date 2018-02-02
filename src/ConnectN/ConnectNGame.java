package ConnectN;

import java.io.InputStreamReader;
import java.util.Arrays;

public class ConnectNGame {

  public static void main(String[] args) {
    IConnectN game = new ConnectNModel();
    IConnectN game2 = new ConnectNModel(3, 10, 10, 10);
    IConnectNController controller = new ConnectNController(new InputStreamReader(System.in),
            System.out);
    controller.startGame(game2);
  }
}
