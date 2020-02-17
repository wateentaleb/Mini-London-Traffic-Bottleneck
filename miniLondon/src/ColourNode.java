import java.awt.Color;

/**
 * This represents the colour data drawn to the screen for a vertex (Intersection) of a graph (RouteGraph)
 */
/**
 * @author Wateen Taleb
 * @date Winter 2019
 *
 **/
public class ColourNode {

	/**
	 * Colour of node
	 */
	Color nodeColour;
	/**
	 * x-coordinate of node
	 */
	double xPosition=0;
	/**
	 * y-coordinate of node
	 */
	double yPosition=0;
	/**
	 * dark text on label or not
	 */
	boolean darkText = false;
	
	/**
	 * Constructor
	 * @param x - an x-coordinate of a vertex.
	 * @param y - a y-coordinate of a vertex.
	 */
	public ColourNode(double x, double y){
		xPosition=x;
		yPosition=y;
		nodeColour = new Color(0,0,0);
		darkText=false;
	}
	
	/**
	 * As routes are processed, we must update the colour and label colour of a vertex.
	 * @param val - the number of vehicles crossing this vertex
	 * @param max - the maximum number of routes processed so far.
	 */
	public void updateColour(int val, int max){
		nodeColour = new Color(((int) (255*((int) ((double)val)/((double)max)))),((int) (255*((int) ((double)val)/((double)max)))),((int) (255*((int) ((double)val)/((double)max)))));
		if(((double)val)/((double)max)<0.5){
			darkText=false;
		}
		else{
			darkText=true;
		}
	}
	
	/**
	 * The x-coordinate of a vertex
	 * @return xPosition
	 */
	public double getX(){
		return xPosition;
	}
	/**
	 * The y-coordinate of a vertex
	 * @return yPosition
	 */
	public double getY(){
		return yPosition;
	}
	/**
	 * Give the colour of the vertex
	 * @return nodeColour
	 */
	public Color getColour(){
		return nodeColour;
	}
	/**
	 * Is the label storing dark text?
	 * @return darkText
	 */
	public boolean isDarkText(){
		return darkText;
	}
	
	
}
