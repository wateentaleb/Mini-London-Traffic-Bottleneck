/**
 * An exception occurs for a BST.  It is advised you pass a meaningful message when this exception occurs as several different tree methods use this.
 * @author Wateen Taleb
 * @date Winter 2019
 *
 */
public class TreeException extends Exception {
	  /**
	   * Constructor
	   * @param mssg - a String passed to be a part of the error message.
	   */
	  public TreeException(String message) {
	    super("Tree Exception: " + message);
	  }
}