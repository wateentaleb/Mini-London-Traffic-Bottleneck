/**
 * An exception occurs for a Graph.  It is advised you pass a meaningful message when this exception occurs as several different graph methods use this.
 * @author Wateen Taleb
 * @date Winter 2019
 *
 */
public class GraphException extends Exception {
	  /**
	   * Constructor
	   * @param mssg - a String passed to be a part of the error message.
	   */
	  public GraphException(String message) {
	    super("Graph Exception: " + message);
	  }
}