package ConnectN;

/**
 * Interface representing a controller for a game of ConnectN.
 */
public interface  IConnectNController {

  /**
   * Begins a game of ConnectN using a ConnectN modelallowing for user input of the column they
   * would like to place a chip in.
   * @param model The ConnectNModel the game is played on.
   * @throws NullPointerException if the given model is null.
   */
  void startGame(IConnectN model) throws NullPointerException;
}
