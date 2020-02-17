import java.util.ArrayList;
import java.util.Iterator;

/**
 * A graph representing the routes.  The edges are Road objects and the vertices are Intersection objects.
 * @author Wateen Taleb
 * @data Winter 2019
 *
 */
public class RouteGraph {
	
	/**
	 * Vertices of graph
	 */
	private Intersection[] nodes;
	/**
	 * Edges of graph, for ease I am using an ArrayList.  Traditionally, this is usually a linked list.
	 */
	private ArrayList<Road>[] edges;
	/**
	 * number of nodes
	 */
	private int numberOfNodes;
	/**
	 * number of edges
	 */
	private int numberOfEdges;
	
	/**
	 * Constructor
	 * @param n - an integer, vertices are assumed to be labelled 0, 1, 2,..., n-1
	 */
	public RouteGraph(int n){
		nodes = new Intersection[n];
		for(int i=0;i<n;i++){
			nodes[i] = new Intersection(i);
		}
		edges = new ArrayList[n];
		for(int i=0;i<n;i++){
			edges[i] = new ArrayList<Road>();
		}
		this.numberOfNodes=n;
		this.numberOfEdges=0;
		
	}
	
	/**
	 * Given an integer (label), it returns the Intersection object (vertex).
	 * @param label - an integer between 0 and n-1 (n is the number of vertices).
	 * @return Intersection object
	 * @throws GraphException - if a node does not exist with the given label.
	 */
	public Intersection getIntersection(int label) throws GraphException{
		if(label<0 || label>=numberOfNodes){
			throw new GraphException("Indexing a node " + label + ", this does not exist");
		}
		return this.nodes[label];
		
	}
	
	/**
	 * Given two endpoints u and v for some undirected edge, it returns an undirected edge (Road).  
	 * IMPORTANT NOTE: Undirected edges have it where (u,v) is the same as (v,u).  So the order the endpoints are given does not matter as edges are added.
	 * @param u - first endpoint
	 * @param v - second endpoint 
	 * @return Road object
	 * @throws GraphException - if the undirected edge does not exist.
	 */
	public Road getRoad(Intersection u, Intersection v) throws GraphException{
		
		int vertexLabelOne = u.getLabel();
		int vertexLabelTwo = v.getLabel();
		//The edges are undirected, so one can simply scan one of the two lists.
		for(int i =0;i<edges[vertexLabelOne].size();i++){
			Road edgePossible = (Road)edges[vertexLabelOne].get(i);
			boolean directionOne = edgePossible.getFirstEndpoint().getLabel() == vertexLabelOne && edgePossible.getSecondEndpoint().getLabel() == vertexLabelTwo;
			boolean directionTwo = edgePossible.getSecondEndpoint().getLabel() == vertexLabelOne && edgePossible.getFirstEndpoint().getLabel() == vertexLabelTwo;
			if(directionOne || directionTwo){
				return edgePossible;
			}
		}
		//the edge does not exist, so throw the GraphException.
		throw new GraphException("Edge does not exist");
	}
	
	/**
	 * Returns true if vertices u and v are adjacent, false otherwise.
	 * @param u
	 * @param v
	 * @return returns true if adjacent, false otherwise.
	 * @throws GraphException - if any of the methods throw the GraphException.
	 */
	public boolean areAdjacent(Intersection u, Intersection v) throws GraphException{
			
		int vertexLabelOne = u.getLabel();
		int vertexLabelTwo = v.getLabel();
		//The edges are undirected, so one can simply scan one of the two lists.
		for(int i =0;i<edges[vertexLabelOne].size();i++){
			Road edgePossible = (Road)edges[vertexLabelOne].get(i);
			//we need to check both endpoints, remember the edges here are undirected.  If the edges were directed, we would need to check both adjacency lists.
			boolean directionOne = edgePossible.getFirstEndpoint().getLabel() == vertexLabelOne && edgePossible.getSecondEndpoint().getLabel() == vertexLabelTwo;
			boolean directionTwo = edgePossible.getSecondEndpoint().getLabel() == vertexLabelOne && edgePossible.getFirstEndpoint().getLabel() == vertexLabelTwo;
			if(directionOne || directionTwo){
				return true;//found one.
			}
		}
		return false;//we didn't find it.			
	
	}
	
	/**
	 * Get all the edges (roads) incident on vertex (intersection) u.
	 * @param u
	 * @return Iterator that yields all the roads incident on u.
	 * @throws GraphException - if the intersection u does not exist.
	 */
	public Iterator<Road> incidentRoads(Intersection u) throws GraphException{
			int label = getIntersection(u.getLabel()).getLabel();
			return edges[label].iterator();
	}
	
	/**
	 * Inserting a new undirected edge (road) with endpoints u and v.
	 * @param u
	 * @param v
	 * @throws GraphException - if one of the graph methods throws GraphException e.g. areAdjacent.
	 */
	public void insertEdge(Intersection u, Intersection v) throws GraphException{
		if(!areAdjacent(u,v)){//areAdjacent returns true if an undirected edge {u,v} is in the graph.
			Road newRoad = new Road(u,v);//adding undirected edge {u,v} to the graph.
			edges[u.getLabel()].add(newRoad);
			edges[v.getLabel()].add(newRoad);
			numberOfEdges++;
		}
	}
	
	
}
