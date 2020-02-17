/**
 * @author Wateen Taleb
 * @date Winter 2019
 *
 **/

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

public class DepthFirstSearch {

//    private Intersection w;
//    // 0 if first end point , 1 if second end point
//    private boolean position;
//    private Intersection first;
//    private Intersection second;
//    ArrayList<Road> list = new ArrayList<Road>();

    private Stack<Intersection> path;
    private Iterator<Road> roads;
    private Road edge;
    private Intersection vertex;

    /*
    his class implements a depth-first search algorithm that finds a path given a starting vertex and end vertex.
    You are provided classes Intersection, Road, RouteGraph, GraphException, please review these classes before using them.
     You are not allowed to modify any of the code in these classes.
Your DepthFirstSearch class will have the following instance variables: (1) a RouteGraph inputGraph and (2) a Stack<Intersection> stack.
 The stack will be used for storing the path that will be passed onto the class RouteFind when updating information on the traffic
  information, which will maintain the AVL Tree storing the number of times a vehicle enters each intersection.
For this class, you must implement all and only the following public methods:

     */
Stack <Intersection> stack = new Stack<Intersection>();
    private RouteGraph graph;
    public DepthFirstSearch(RouteGraph graph){
        this.graph = graph;

    }

    //Reset stack, then you will call pathRec(startVertex, endVertex) (described below), and return stack.
    public Stack<Intersection> path(Intersection startVertex, Intersection endVertex) throws GraphException {

        path =  new Stack<Intersection>();



        pathRec(startVertex,endVertex);

        path.push(endVertex);

         return path;
    }


    /*
    Implement the algorithm (called path) given in class for computing a path via a depth-first search.
    You must implement this algorithm; it will not find shortest paths, nor paths using the least number of edges,
    we will assume the drivers in Mini-London might like to go for long drives. This recursive method when completed
    must have the path stored in stack. Tip: You might find the method incidentRoads useful from the RouteGraph class,
     also remember that the edges are undirected (the order of the endpoints for edges are irrelevant). You are not resposible
      for de-marking the vertices, the class RouteFind will take care of removing the marking
     */
    public boolean pathRec(Intersection startVertex, Intersection endVertex) throws GraphException {

        roads = graph.incidentRoads(startVertex);

        if (startVertex == endVertex) return true;
        startVertex.setMark(true);
        path.push(startVertex);

        if (startVertex == endVertex) return true;
        else {
            roads = graph.incidentRoads(startVertex);

            // adding all the adjacent edges to the node in an array lis t
            while (roads.hasNext()) {
                Road road = roads.next();
                next:
                {
                    vertex = getIncident(road.getFirstEndpoint(), road.getSecondEndpoint());
                    if (vertex != null && !vertex.getMark()) {
                        if (pathRec(vertex, endVertex)) return true;
                    }
                }
            }
            // reached a dead end so were gonna pop that path off
            path.pop();
           pathRec(path.pop(),endVertex);
//            pathRec(path.pop(),endVertex);
            return false;
        }


    }




    private Intersection getIncident(Intersection First ,Intersection Second){

        if (First.getMark() && Second.getMark())
            return null;
        else if(First.getMark()) return Second;
        else return First;

    }


}


