/**
 * An edge in the graph, a Road in a Route Graph.  
 * @author Wateen Taleb
 * @date Winter 2019
 *
 */
public class Road {

	/**
	 * First endpoint of edge 
	 */
	private Intersection firstEndpoint;
	/**
	 * Second endpoint of edge
	 */
	private Intersection secondEndpoint;
	
	/**
	 * Constructor
	 * @param firstEndpoint
	 * @param secondEndpoint
	 */
	public Road(Intersection firstEndpoint, Intersection secondEndpoint){
		this.firstEndpoint = firstEndpoint;
		this.secondEndpoint = secondEndpoint;
	}
	
	/**
	 * Returns the first endpoint
	 */
	public Intersection getFirstEndpoint(){
		return this.firstEndpoint;
	}

	/**
	 * Returns the second endpoint
	 */
	public Intersection getSecondEndpoint(){
		return this.secondEndpoint;
	}
	
	/**
	 * toString method
	 * @return a String representation of a Road (edge)
	 */
	public String toString(){
		return "{" + getFirstEndpoint().getLabel() + "," + getSecondEndpoint().getLabel() + "}";
	}
	
	
	
}
