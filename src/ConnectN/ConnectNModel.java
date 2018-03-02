package ConnectN;

import java.util.ArrayList;

public class ConnectNModel implements IConnectN {

  private int goal;
  private int width;
  private int height;
  private int players;
  private int currentPlayer;

  protected enum GameState {PLAYING, GAMEOVER, STALEMATE;}

  private GameState currentGameState;

  // grid represents the game grid. The top left of the grid is 0,0 and the bottom right
  // is in the positive x and positive y direction.
  private ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();

  /**
   * Default constructor for ConnectN: A standard game of Connect Four.
   */
  public ConnectNModel() {
    this.goal = 4;
    this.width = 7;
    this.height = 6;
    this.players = 2;
    this.currentPlayer = 1;
    this.currentGameState = null;
  }

  /**
   * Constructor: Constructs a custom game of Connect N.
   *
   * @param n       int sets the goal for number of chips to connect in a row.
   * @param width   int sets the width of the grid.
   * @param height  int sets the height of the grid.
   * @param players int sets the number of players.
   */
  public ConnectNModel(int n, int width, int height, int players) {
    if (players < 2) {
      throw new IllegalArgumentException("The game requires at least two players");
    }
    if (n < 2) {
      throw new IllegalArgumentException("The game requires a goal of at least 2 to win");
    }
    if (width < 3 || height < 3) {
      throw new IllegalArgumentException("The grid size must be a minimum of 3x3");
    }
    this.goal = n;
    this.width = width;
    this.height = height;
    this.players = players;
    this.currentPlayer = 1;
  }

  @Override
  public void startGame() {
    // initialize the grid, all spaces are 0
    for (int columns = 0; columns < this.width; columns++) {
      this.grid.add(new ArrayList<Integer>(1));
      for (int rows = 0; rows < this.height; rows++) {
        this.grid.get(columns).add(0);
      }
    }

    this.currentGameState = GameState.PLAYING;

  }

  @Override
  public boolean isGameOver() {
    boolean fullGrid = true;
    for (int columns = 0; columns < this.width; columns++) {
      for (int rows = 0; rows < this.height; rows++) {
        int currPlayerCheck = this.grid.get(columns).get(rows);
        // If the current spot is empty, then our grid isn't full
        if (currPlayerCheck == 0) {
          fullGrid = false;
        } else {

          //check downward win con
          boolean downConnect = true;
          if (rows + this.goal - 1 > this.height - 1) {
            downConnect = false;
          } else {
            for (int goalIndex = 1; goalIndex < this.goal; goalIndex++) {
              if (currPlayerCheck != this.grid.get(columns).get(rows + goalIndex)) {
                downConnect = false;
              }
            }
          }
          //check rightward win con
          boolean rightConnect = true;
          if (columns + this.goal - 1 > this.width - 1) {
            rightConnect = false;
          } else {
            for (int goalIndex = 1; goalIndex < this.goal; goalIndex++) {
              if (currPlayerCheck != this.grid.get(columns + goalIndex).get(rows)) {
                rightConnect = false;
              }
            }
          }

          //check diagonal downward win con
          boolean diagonalDownConnect = true;
          if (rows + (this.goal - 1) > this.height - 1 || columns + (this.goal - 1) > this.width -
                  1) {
            diagonalDownConnect = false;
          } else {
            for (int goalIndex = 1; goalIndex < this.goal; goalIndex++) {
              if (currPlayerCheck != this.grid.get(columns + goalIndex).get(rows + goalIndex)) {
                diagonalDownConnect = false;
              }
            }
          }

          //check diagonal upward win con
          boolean diagonalUpConnect = true;
          if (rows - (this.goal - 1) < 0 || columns + (this.goal - 1) > this.width - 1) {
            diagonalUpConnect = false;
          } else {
            //check diagonal
            for (int goalIndex = 1; goalIndex < this.goal; goalIndex++) {
              if (currPlayerCheck != this.grid.get(columns + goalIndex).get(rows - goalIndex)) {
                diagonalUpConnect = false;
              }
            }
          }

          // If any of these were true, the current player has won
          if (downConnect || rightConnect || diagonalDownConnect || diagonalUpConnect) {
            this.currentGameState = GameState.GAMEOVER;
            return true;
          }
        }
      }
    }

    // The game is a stalemate if the grid is full
    if (fullGrid) {
      this.currentGameState = GameState.STALEMATE;
    }
    return fullGrid;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getGoal() {
    return this.goal;
  }

  @Override
  public int getPlayers() {
    return this.players;
  }

  @Override
  public int getCurrentPlayer() {
    return this.currentPlayer;
  }

  @Override
  public int getNextPlayer() {
    if (this.currentPlayer + 1 > this.players) {
      return 1;
    } else {
      return this.currentPlayer + 1;
    }
  }

  @Override
  public int getLastPlayer() {
    if (this.currentPlayer - 1 < 1) {
      return this.players;
    } else {
      return this.currentPlayer - 1;
    }
  }

  @Override
  public int getWinner() throws IllegalArgumentException {
    if (!this.isGameOver()) {
      throw new IllegalArgumentException("Cannot get winner until the game has been won.");
    }
    if (this.currentGameState.equals(GameState.GAMEOVER)) {
      return this.getLastPlayer();
    } else {
      throw new IllegalArgumentException("Game ended in a stalemate.");
    }
  }

  @Override
  public void placeChip(int player, int column) throws IllegalArgumentException,
          IndexOutOfBoundsException {

    if (this.isGameOver()) {
      throw new IllegalArgumentException("Cannot place chip, the game is over.");
    }
    if (column < 0 || column >= this.width) {
      throw new IndexOutOfBoundsException("Given column is out of bounds.");
    }
    if (player != this.currentPlayer) {
      throw new IllegalArgumentException("Cannot place chip, it is Player " + this.currentPlayer
              + "'s turn.");
    }
    if (isColumnFull(column)) {
      throw new IllegalArgumentException("Cannot place chip, column is full.");
    }
    ArrayList<Integer> requestedColumn = this.grid.get(column);

    for (int index = requestedColumn.size() - 1; index > -1; index--) {
      int potentialSpot = requestedColumn.get(index);
      if (potentialSpot == 0) {
        requestedColumn.set(index, player);
        break;
      }
    }
    this.currentPlayer = this.getNextPlayer();
  }

  @Override
  public int getPlayerAt(int x, int y) throws IllegalArgumentException {
    int requested = this.grid.get(x).get(y);
    if (requested == 0) {
      throw new IllegalArgumentException("The requested spot is empty.");
    } else {
      return this.grid.get(x).get(y);
    }
  }

  @Override
  public int getNumChipsInColumn(int column) {
    ArrayList<Integer> requested = this.grid.get(column);
    int count = 0;
    for (Integer chip : requested) {
      if (chip != 0) {
        count++;
      }
    }
    return count;
  }

  @Override
  public boolean isColumnFull(int column) {
    ArrayList<Integer> requested = this.grid.get(column);
    for (Integer chip : requested) {
      if (chip == 0) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String getGameState() {
    String result = " _______________________";

    for (int rows = 0; rows < this.height; rows++) {
      result += "\n|";
      for (int columns = 0; columns < this.width; columns++) {
        result += "  " + this.grid.get(columns).get(rows).toString();
      }
      result += "  |";
    }
    if (this.isGameOver() && this.currentGameState.equals(GameState.GAMEOVER)) {
      result += "\n _______________________ \nGame Over. Player " + this.getWinner() + " wins!";
    } else if (this.isGameOver() && this.currentGameState.equals(GameState.STALEMATE)) {
      result += "\n _______________________ \nGame Over. Stalemate.";
    } else {
      result += "\n _______________________ \nIt is Player " + this.currentPlayer + "'s turn.\n";
    }
    return result;
  }

  @Override
  public String openingMessage() {
    return "---------------------------\nWelcome to ConnectN! N = " + this.goal + ".\n";
  }
}
