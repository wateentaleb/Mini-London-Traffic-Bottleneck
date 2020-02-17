
/**
 * A vertex in the graph.  These represent intersections in the Route Graph.
 * @author Wateen Taleb
 * @date Winter 2019
 *
 */
public class Intersection{
	
	/**
	 * A label for the vertex.
	 */
	private int label;
	/**
	 * has the vertex been marked?
	 */
	private boolean marked;
	
	/**
	 * Constructor
	 * @param label - number which labels the intersection.
	 */
	public Intersection(int label){
		this.label = label;
		this.marked = false;
	}
	
	/**
	 * Marks the vertex with a specified marking true/false.
	 * @param mark - a boolean
	 */
	public void setMark(boolean mark){
		this.marked = mark;
	}
	
	/**
	 * Returns whether the vertex is marked or not.
	 * @return marked - a boolean.
	 */
	public boolean getMark(){
		return this.marked;
	}
	
	/**
	 * Returns the label of the vertex.
	 * @return label - an integer.
	 */
	public int getLabel(){
		return this.label;
	}
}
