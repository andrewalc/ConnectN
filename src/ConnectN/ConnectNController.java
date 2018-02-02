package ConnectN;

import java.io.IOException;
import java.util.Scanner;

public class ConnectNController implements IConnectNController {

  private Readable rd;
  private Appendable ap;

  public ConnectNController(Readable rd, Appendable ap) throws IllegalStateException {
    if (rd == null || ap == null) {
      throw new IllegalStateException("Readable and Appendable object cannot be null");
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void startGame(IConnectN model) throws NullPointerException {
    try {
      if (model == null) {
        throw new NullPointerException("Model is null");
      }
      Scanner scan = new Scanner(this.rd);
      IConnectNCommand cmd = null;

      // Start the game
      model.startGame();
      this.ap.append(model.openingMessage());
      //show the initial board
      this.ap.append(model.getGameState());

      while (scan.hasNext() && !model.isGameOver()) {
        // Columns begin at 1 on the left.
        boolean validInput = false;
        try {
          int inputColumn = Integer.parseInt(scan.next()) - 1;
          cmd = new PlaceChipCommand(model.getCurrentPlayer(), inputColumn);
          validInput = true;
        } catch (NumberFormatException ex) {
          this.ap.append("Please input the column number you'd like to place a chip in.\n");
        }


        try {
          if (validInput) {
            cmd.perform(model);
          }
        } catch (IllegalArgumentException ex) {
          this.ap.append(ex.getMessage() + "\n");
        } catch (IndexOutOfBoundsException ex) {
          this.ap.append(ex.getMessage() + "\n");
        }
        this.ap.append(model.getGameState());
      }

    } catch (IOException ex) {
      ex.getMessage();
    }

  }
}
