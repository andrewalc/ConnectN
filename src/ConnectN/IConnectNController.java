package ConnectN;

/**
 * Interface representing a controller for a game of ConnectN.
 */
public interface IConnectNController {

  /**
   * Begins a game of ConnectN using a ConnectN modelallowing for user input of the column they
   * would like to place a chip in.
   *
   * @param model The ConnectNModel the game is played on.
   * @throws NullPointerException if the given model is null.
   */
  void startGame(IConnectN model) throws NullPointerException;

  /**
   * Returns the N goal.
   * @return  N goal amount.
   */
  int getN();

  /**
   * Returns the player defined width of the grid.
   * @return Width of the grid.
   */
  int getWidth();

  /**
   * Returns the player defined height of the grid.
   * @return Width of the grid.
   */
  int getHeight();

  /**
   * Returns the player defined number of players.
   * @return Number of players in this game.
   */
  int getPlayers();

}
