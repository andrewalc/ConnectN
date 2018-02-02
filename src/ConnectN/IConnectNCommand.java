package ConnectN;

/**
 * Created by Andrew Alcala on 5/31/2017.
 */
/**
 *  An interface representing classes who execute its respective command in ConnectN through
 *  calling its perform method.
 */
public interface IConnectNCommand {

  /**
   * Performs the associate command that is associated with this class implementation from the
   * given IConnectN model.
   * @param model The ConnectNModel of the game of ConnectN.
   */
  void perform(IConnectN model);
}

