package ConnectN;

public interface IConnectN {
  /**
   * Begins a game of ConnectN. The goal is to making a connection of N chips
   * horizontally, vertically, or diagonally. Players take turns placing their own chip
   * into a column of their choice. The move is valid as long as the column is not full.
   * A placed chip immediately falls to the bottom most spot in a column. When the column
   * has chips up to the top, the column is full. If all spots on the grid are taken and
   * there is no winner, the game ends in a stalemate.
   */
  void startGame();

  /**
   * Returns whether the current game of ConnectN is over or not.
   *
   * @return Boolean state of the game, true if it is over, false otherwise.
   */
  boolean isGameOver();

  /**
   * Returns the width of the grid for this game of Connect N.
   *
   * @return an int representing the width of the grid.
   */
  int getWidth();

  /**
   * Returns the height of the grid for this game of Connect N.
   *
   * @return an int representing the height of the grid.
   */
  int getHeight();

  /**
   * Returns the connect goal for this game of ConnectN. Ex. For connect four,
   * this method would return 4.
   *
   * @return an int representing the objective goal of chips to connect.
   */
  int getGoal();

  /**
   * Returns the number of players in this game of ConnectN
   *
   * @return int number of players for this game of ConnectN
   */
  int getPlayers();

  /**
   * Returns the ID number of the player who's turn it is currently.
   *
   * @return int of the ID number of the player who will move this turn.
   */
  int getCurrentPlayer();

  /**
   * Returns the ID number of the player who goes next.
   *
   * @return int of the ID number of the player who will move next turn.
   */
  int getNextPlayer();

  /**
   * Returns the ID number of the player who went last.
   *
   * @return int of the ID number of the player who who went last turn.
   */
  int getLastPlayer();

  /**
   * Returns the ID number of the player who has won the game.
   *
   * @return int ID of the player who has won.
   * @throws IllegalArgumentException if the method is called before the game is over.
   */
  int getWinner() throws IllegalArgumentException;

  /**
   * Method that allows a player to place a chip into a column. A player can only place a chip
   * if it is their turn and if the column they want to place to is not full.
   *
   * @param player int ID of the player who is making the move.
   * @param column int column index of the column the player would like to place a chip in.
   * @throws IllegalArgumentException  if the player is not the current player or if the given
   *                                   column is full.
   * @throws IndexOutOfBoundsException if the given column is out of bounds.
   */
  void placeChip(int player, int column) throws IllegalArgumentException, IndexOutOfBoundsException;

  /**
   * Returns the ID of the player who has a chip at a given x and y coordinate on the grid.
   * If there is no chip at the xy coordinate, an IllegalArgumentException will be thrown.
   *
   * @param x int of the x location on the grid.
   * @param y int of the y location on the grid.
   * @return int ID of the player at this location
   * @throws IllegalArgumentException if there is no chip at the given location.
   */
  int getPlayerAt(int x, int y) throws IllegalArgumentException;

  /**
   * Returns the number of chips in the given column.
   *
   * @return int number of chips in the given column
   */
  int getNumChipsInColumn(int column);

  /**
   * Returns whether the given column is full.
   *
   * @return boolean, true if the column is full, false otherwise.
   */
  boolean isColumnFull(int column);

  /**
   * Method that produces a String representation of the grid and the current game state.
   *
   * @return String representing the game state.
   */
  String getGameState();

  /**
   * Produces an introductory string to the game.
   *
   * @return An introductory String to the game.
   */
  String openingMessage();
}
