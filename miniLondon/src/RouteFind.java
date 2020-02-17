/**
 * @author Wateen Taleb
 * @date Winter 2019
 *
 **/
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class RouteFind extends JFrame {

	/**
	 * IMPORTANT CONSTANTS FOR SPEED OF PROCESSING
	 * PATH_SLEEP - this is for viewing the path.
	 * REFRESH_SLEEP - this is how quickly it clears the redrawing for the next path.
	 */
	/**
	 * PATH SLEEP constant
	 */
	private int PATH_SLEEP;//technically these are not constants, ALL_CAPS is being used here to help you identify this constant
	/**
	 * REFRESH SLEEP constant
	 */
	private int REFRESH_SLEEP;//technically not an actual constant, but we will treat it as a final variable.

	/**
	 * RouteGraph
	 */
	private RouteGraph graph;
	/**
	 * Tree that stores information
	 */
	private AVLTree tree;
	/**
	 * Colours of nodes for drawing.
	 */
	private ColourNode[] colourArray;
	/**
	 * routes read
	 */
	private int routesRead;

	/**
	 * backdrop image
	 */
	private BufferedImage backDropImage;
	/**
	 * Double buffer image graphics...
	 */
	private Graphics doubleBufferGraphics;
	/**
	 * For double buffering the window so there isn't flickering
	 */
	private Image doubleBufferImage;

	/**
	 * yShift for window
	 */
	private final int Y_SHIFT=30;

	/**
	 * width of oval
	 */
	private final int LABEL_WIDTH=13;

	/**
	 * height of oval
	 */
	private final int LABEL_HEIGHT=13;

	/**
	 * Bottleneck's index.
	 */
	private int bottleneckLabel;
	/**
	 * If a path exists, we need to know the nodes in it for drawing sometimes, here's an ArrayList we might need to pass around for drawing.
	 */
	private ArrayList<ColourNode> colourPath;
	/**
	 * We want to know the current path being sought out...
	 */
	private String pathSeeking;

	/**
	 * Constructor
	 * @param file - the file containing the undirected graph data and the (x,y)-coordinates of all the vertices. Create also the AVL Tree.
	 */
	public RouteFind(String file, String path_sleep, String refresh_sleep){

		this.pathSeeking=null;//keeping track of the path (nothing yet)
		this.colourPath=null;//keeping track of the edges (nothing yet)
		this.bottleneckLabel = -1;//will change when the bottleneck is computed.
		this.routesRead=0;//no routes have been read in yet.
		this.PATH_SLEEP = 0;
		this.REFRESH_SLEEP = 0;
		try{
			this.PATH_SLEEP = Integer.parseInt(path_sleep);
			this.REFRESH_SLEEP = Integer.parseInt(refresh_sleep);
			BufferedReader fileRead = new BufferedReader(new FileReader(file));
			int size = Integer.parseInt(fileRead.readLine());
			fileRead.readLine();
			//create a graph of given size.
			graph = new RouteGraph(size);

			//create AVL Tree.
			tree = new AVLTree();
			//create the nodes of the tree.
			for(int i =0;i<size;i++){
				tree.putAVL(tree.root(),i,0);
			}
			//add in the edges.
			for(int i=0;i<size;i++){
				String[] tokens = fileRead.readLine().split(",");
				for(int j=0;j<tokens.length;j++){
					if(Integer.parseInt(tokens[j])==1){

						//get the vertices (intersections).
						Intersection u = graph.getIntersection(i);
						Intersection v = graph.getIntersection(j);

						//we will assume repeated edges are not added.
						graph.insertEdge(u, v);

					}
				}
			}
			fileRead.readLine();
			colourArray = new ColourNode[size];
			for(int i=0;i<size;i++){
				String[] nodeLocation = fileRead.readLine().split(",");
				colourArray[i] = new ColourNode(Double.parseDouble(nodeLocation[1])/2.0,Double.parseDouble(nodeLocation[2])/2.0);
			}
			fileRead.close();
			this.backDropImage = ImageIO.read(new File("roadmap.gif"));
			this.setSize(710, 465+Y_SHIFT);
			this.setResizable(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setTitle("Mini-London's Traffic Bottleneck");
			this.setLayout(new GridLayout(1, 1));
			this.setVisible(true);
			//add(imageLabel);
			repaint();


		}
		catch(TreeException e){
			System.out.println("TreeException was thrown: " + e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("General Exception happened: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * To get the graph. Normally you would not expose important structures like this, but for the purposes of this assignment this is being done.
	 * @return graph
	 */
	public RouteGraph getGraph(){
		return this.graph;
	}

	/**
	 * Returns the label of the node that is the bottleneck!  Stores this in an instance variable for display purposes.
	 */
	public int bottleneck(){

		ArrayList<AVLTreeNode> nodes = tree.inorder(tree.root());
		//now we find the maximum!
		int maximum=0;
		int index=-1;
		for(int i=0;i<nodes.size();i++){
			if(index==-1 || maximum < nodes.get(i).getData()){
				index=i;
				maximum = nodes.get(i).getData();//data stores the value of how many times a node is visited in a path.
			}
		}
		this.bottleneckLabel = index;
		return index;

	}

	/**
	 * This updates the label showing the route being processed.
	 * @param pathLabel - a String giving the route e.g. "16,8" a route from vertex 16 to vertex 8.
	 */
	public void setPathLabel(String pathLabel){
		this.pathSeeking=pathLabel;
	}

	/**
	 * This is the method that increments the data in the AVLTreeNode objects, given a path of vertices.
	 * @param path - a stack containing a path.
	 * @throws InterruptedException - if something occurs with the delay of the program.
	 */
	public void updateData(Stack<Intersection> path) throws InterruptedException{

		Intersection tempNode;
		AVLTreeNode treeNode;
		routesRead++;
		while(!path.isEmpty()){
			tempNode = path.pop();

			treeNode = tree.get(tree.root(),tempNode.getLabel());

			//Thread.sleep(1);
			treeNode.incrementData();
			//make sure routesRead is >0

			Thread.sleep(PATH_SLEEP);
			if(routesRead>0){
				for(int i=0;i<colourArray.length;i++){//update the colours on the intersections (vertices) of the RouteGraph.
					colourArray[i].updateColour(tree.get(tree.root(),i).getData(),this.routesRead);
				}
			}
			repaint();

		}
		colourPath=null;//just resetting the edges, we explicitly catch this in the drawing code.
		resetGraphNodes();

		Thread.sleep(REFRESH_SLEEP);
		repaint();
	}

	/**
	 * This resets the marks on the graph so when the next route is given, it is "clean".
	 */
	public void resetGraphNodes(){

		//need to reset the graph for the next path to be found!
		try{
			for(int i=0;i<colourArray.length;i++){
				graph.getIntersection(i).setMark(false);
			}
		}
		catch(GraphException e){
			System.out.println("resetGraphNodes: " + e.getMessage());
		}
	}

	/**
	 * The algorithm that finds the path will have a bunch of extra nodes marked, this cleans those up.
	 * @param nodes - a stack containing a path.
	 * @throws GraphException
	 */
	public void cleanExtraMarkedNodes(Stack<Intersection> nodes) throws GraphException{

		for(int i=0;i<colourArray.length;i++){
			//check if the stack contains a given intersection, if it doesn't de-mark it.
			if(!nodes.contains(graph.getIntersection(i))){
				graph.getIntersection(i).setMark(false);
			}
		}
	}

	/**
	 * Convert stack into array of ColourNodes to be read to draw lines.
	 * @param g
	 */
	public void pathColourNodes(Stack<Intersection> nodes) throws GraphException{
		ArrayList<ColourNode> listOfNodes = new ArrayList<ColourNode>();
		for(int i=0;i<nodes.size();i++){
			//technically this is an abuse of a stack, I highly recommend not doing this.  Java collections such as these have a get method, stacks do not technically have this method in how we discussed in class.
			listOfNodes.add(colourArray[nodes.get(i).getLabel()]);
		}
		colourPath=listOfNodes;
		//return listOfNodes;
	}

	/**
	 * Drawing the graphics to the window.
	 */
	public void paint(Graphics g){
		doubleBufferImage = createImage(getWidth(),getHeight());
		doubleBufferGraphics = doubleBufferImage.getGraphics();
		paintComponent(doubleBufferGraphics);
		g.drawImage(doubleBufferImage,0,0,this);
	}

	/**
	 * a subroutine for paint.
	 * @param g - Graphics object.
	 */
	public void paintComponent(Graphics g){
		//super(g);
		g.drawImage(backDropImage, 0, 0+Y_SHIFT, this);
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		boolean marked= false;
		g.setColor(Color.WHITE);
		g.fillRect(28, 30, 160, 60);
		g.setColor(Color.BLACK);
		g.drawString("Routes Found: " + this.routesRead, 32, 52);
		if(pathSeeking!=null){
			g.drawString("Route: " + this.pathSeeking, 32, 52+16);
		}
		if(bottleneckLabel!=-1){
			g.drawString("Bottleneck Intersection: " + this.bottleneckLabel, 32, 52+16);
		}
		//before drawing any nodes, should there be a path, we should draw its edges
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.RED);
		//sometimes colourPath is null, but need to ensure if mid-draw colourPath becomes null we do not run into any NullPointerExceptions.  I'd not recommend doing it the way I am here, this should be good enough for our application.
		try{
			if(colourPath!=null){
				if(colourPath.size()>1){
					for(int i=0;i<colourPath.size()-1;i++){
						g2.drawLine((int)colourPath.get(i).getX(),(int)colourPath.get(i).getY()+Y_SHIFT,(int)colourPath.get(i+1).getX(),(int)colourPath.get(i+1).getY()+Y_SHIFT);
					}
				}
			}
		}
		catch(NullPointerException e){
			//this is done to simply ensure we handle the NullPointException.
		}

		for(int i=0;i<colourArray.length;i++){
			//g.drawOval((int)((double)colourArray[i].getX()+((double)LABEL_WIDTH/2.0)), (int)((double)colourArray[i].getY()+((double)LABEL_HEIGHT/2.0))+Y_SHIFT, LABEL_WIDTH, LABEL_HEIGHT);
			marked=false;
			try{
				Intersection vertex = graph.getIntersection(i);
				marked = vertex.getMark();
			}
			catch(GraphException e){
				System.out.println("Failed to determine if vertex is marked or not for drawing: " + e.getMessage());
			}
			drawCircle(g,(int)colourArray[i].getX(),(int)colourArray[i].getY(),LABEL_WIDTH,colourArray[i].getColour(),marked);
			//g.setFont(font);
			if(colourArray[i].isDarkText()){
				g.setColor(Color.BLACK);
			}
			else{
				g.setColor(Color.WHITE);
			}
			if(i<10){
				g.drawString(""+i, ((int)colourArray[i].getX())-(int)(LABEL_WIDTH/2.0)+4,((int)colourArray[i].getY())+Y_SHIFT+LABEL_HEIGHT/2-3);
			}
			else if(i>=20){
				g.drawString(""+i, ((int)colourArray[i].getX())-(int)(LABEL_WIDTH/2.0)+2,((int)colourArray[i].getY())+Y_SHIFT+LABEL_HEIGHT/2-2);
			}
			else{
				g.drawString(""+i, ((int)colourArray[i].getX())-(int)(LABEL_WIDTH/2.0),((int)colourArray[i].getY())+Y_SHIFT+LABEL_HEIGHT/2-2);
			}


		}
	}

	private void drawCircle(Graphics g, int x, int y, int r,Color colour,boolean isMarked) {
		x = x-(r/2);
		y = y-(r/2);
		//if is marked make it red outline
		if(isMarked){
			g.setColor(Color.RED);
		}
		else{
			g.setColor(Color.BLACK);
		}
		g.fillOval(x-1,y+Y_SHIFT-1,r+3,r+3);
		g.setColor(colour);
		g.fillOval(x,y+Y_SHIFT,r,r);
	}

	/**
	 * Given a string "starting vertex, ending vertex", will find the route and perform the task
	 * @param s - as described above.
	 * @throws GraphException
	 * @throws NumberFormatException
	 * @throws InterruptedException
	 */
	public static void readRoute(RouteFind route,DepthFirstSearch dfs, String s) throws NumberFormatException, GraphException, InterruptedException{
		String[] tokens = s.split(",");
		Intersection intersectionOne = route.getGraph().getIntersection(Integer.parseInt(tokens[0]));
		Intersection intersectionTwo = route.getGraph().getIntersection(Integer.parseInt(tokens[1]));
		Stack<Intersection> path = dfs.path(intersectionOne,intersectionTwo);
		route.cleanExtraMarkedNodes(path);
		route.pathColourNodes(path);
		// messing up at update data
		route.updateData(path);
		route.resetGraphNodes();
		route.repaint();


	}

	public static void main(String []args){
		RouteFind route = new RouteFind(args[0],args[2],args[3]);
		DepthFirstSearch pathFinding = new DepthFirstSearch(route.getGraph());
		try{
			BufferedReader bReader = new BufferedReader(new FileReader(args[1]));
			String targets = bReader.readLine();
			while(targets!=null){
				route.setPathLabel(targets);
				readRoute(route, pathFinding, targets);
				targets=bReader.readLine();
			}
			bReader.close();


			route.setPathLabel(null);//we are done searching for paths, don't need this label visible anymore.

			//now we need to find the bottleneck!
			int index=route.bottleneck();
			System.out.println("Bottleneck Intersection is Intersection " + index + ".");
		}
		catch(NumberFormatException e){
			System.out.println("Casting route information to int failed: " + e.getMessage());
		}
		catch(GraphException e){
			System.out.println("Something happened" + e.getMessage());
			e.printStackTrace();
		}
		catch(InterruptedException e){
			System.out.println("Something happened" + e.getMessage());
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found, ensure command line argument entered properly: " + e.getMessage());
		}
		catch (IOException e) {
			System.out.println("IO Exception occurred when reading the route data: " + e.getMessage());
		}
	}




}