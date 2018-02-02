package ConnectN;

/**
 * Command Class representing the placeChip method from IConnectN
 */
public class PlaceChipCommand implements IConnectNCommand {

  private int player;
  private int column;

  public PlaceChipCommand(int player, int column) {
    this.player = player;
    this.column = column;
  }

  @Override
  public void perform(IConnectN model) {
    model.placeChip(this.player, this.column);
  }

  public String toString() {
    return "" + player;
  }
}
