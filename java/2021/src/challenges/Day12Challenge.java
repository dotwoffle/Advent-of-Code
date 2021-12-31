package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import util.Pair;


/**Day 12 challenge: <a href=https://adventofcode.com/2021/day/12>Passage Pathing</a>*/
public class Day12Challenge extends Challenge {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	/**Graph representing the caves.*/
	private final Graph CAVE_GRAPH;
	
	
	/*********************/
	/*    Constructor    */
	/*********************/

	public Day12Challenge() throws FileNotFoundException, IOException {
		
		super(2021, 12);
		
		//init
		
		CAVE_GRAPH = new Graph();
		
		//load graph
		for(String line : input) {
			
			String[] nodes = line.split("-"); //both node ids from line
			
			CAVE_GRAPH.addNode(nodes[0]);
			CAVE_GRAPH.addNode(nodes[1]);
			
			CAVE_GRAPH.addConnection(nodes[0], nodes[1]);
			
		}
		
	}
	
	
	/*********************/
	/*    Graph Class    */
	/*********************/
	
	/**Simple graph class to store nodes and edges.*/
	private class Graph {
		
		/*******************/
		/*    Variables    */
		/*******************/
		
		/**ID of the start node.*/
		private final String START_NODE = "start";
		/**ID of the end node.*/
		private final String END_NODE = "end";
		/**Maps node IDs to other nodes connected to it.*/
		private HashMap<String, HashSet<String>> connections;
		
		
		/*********************/
		/*    Constructor    */
		/*********************/
		
		/**Creates an empty graph object.*/
		public Graph() {
			
			//init
			
			connections = new HashMap<>();
			
		}
		
		
		/*****************/
		/*    Methods    */
		/*****************/
		
		/**Adds a new node to the graph if it doesn't already exist.
		 * @param nodeId The string ID for the new node.*/
		public void addNode(String nodeId) {
			connections.putIfAbsent(nodeId, new HashSet<String>());
		}
		
		/**Adds a new undirected connection between the two given nodes.
		 * @param node1 The first node ID.
		 * @param node2 The second node ID.*/
		public void addConnection(String node1, String node2) {
			connections.get(node1).add(node2);
			connections.get(node2).add(node1);
		}
		
		/**Checks if the given node is a small cave.
		 * @param nodeId The node ID to check.
		 * @return True if the node ID is all lowercase, false otherwise.*/
		public boolean isSmallCave(String nodeId) {
			return !nodeId.equals(END_NODE) && nodeId.toLowerCase() == nodeId;
		}
		
		/**Gets all of the neighbors of a given node.
		 * @param nodeId The ID of the node to search for.
		 * @return A set of all of the nodes connected to the given node.*/
		public HashSet<String> getNeighbors(String nodeId) {
			return connections.get(nodeId);
		}
		
		@Override
		public String toString() {
			
			String graphStr = "";
			
			for(String key : connections.keySet())
				for(String id : connections.get(key))
					graphStr += key + " -- " + id + "\n";
			
			return graphStr;
			
		}
		
	}
	
	/************************/
	/*    TreeNode class    */
	/************************/
	
	/**Class to store paths while searching the graph.*/
	private class TreeNode {
		
		/*******************/
		/*    Variables    */
		/*******************/
		
		/**If this is a small cave node, whether or not this is the second time this cave has occured in its path.*/
		public final boolean IS_DOUBLE_COUNT;
		/**The parent node of this node.*/
		public final TreeNode PARENT;
		/**The graph node ID for this tree node.*/
		public String nodeId;
		/**The child nodes of this node.*/
		public ArrayList<TreeNode> children;
		
		
		/*********************/
		/*    Constructor    */
		/*********************/
		
		/**Creates a new tree node object.
		 * @param nodeId The graph node ID string to store in this node.
		 * @param parent The parent TreeNode for this node. If this node is the tree root, parent should be null.*/
		public TreeNode(String nodeId, TreeNode parent) {
			
			//init
			
			this.nodeId = "PENDING";
			this.PARENT = parent;
			this.children = new ArrayList<>();
			
			//see if this is a double small cave
			if(CAVE_GRAPH.isSmallCave(nodeId) && isNodeInPath(nodeId))
				this.IS_DOUBLE_COUNT = true;
			else
				this.IS_DOUBLE_COUNT = false;
			
			this.nodeId = nodeId;
			
		}
		
		
		/*****************/
		/*    Methods    */
		/*****************/
		
		/**Counts the total number of completed paths in this tree.
		 * @return The number of paths where the end node is reached.*/
		public int countPaths() {
			
			//base case
			if(nodeId.equals(CAVE_GRAPH.END_NODE))
				return 1;
			else if(children.isEmpty())
				return 0;
			
			//otherwise check all children
			else {
				
				int totalPaths = 0;
				
				//find all paths in child nodes
				for(TreeNode child : children)
					totalPaths += child.countPaths();
				
				return totalPaths;
				
			}
			
		}
		
		/**Checks if a given node is in the backtrace to the root from this node.
		 * @param nodeId The ID of the node to search for.
		 * @return True if the given node is found in one of the ancestor nodes, false otherwise.*/
		public boolean isNodeInPath(String nodeId) {
			
			TreeNode currentNode = this; //current node
			
			while(currentNode != null) {
				if(currentNode.nodeId.equals(nodeId))
					return true;
				else
					currentNode = currentNode.PARENT;
			}
			
			return false;
			
		}
		
		/**Checks if the given node occurs twice along the backtrace from this node to the root.
		 * @param nodeId The ID of the node to search for.
		 * @return True if the given node is found twice in the path, false otherwise.*/
		public boolean isNodeDouble(String nodeId) {
			
			TreeNode currentNode = this; //current node
			
			while(currentNode != null) {
				if(currentNode.nodeId.equals(nodeId) && currentNode.IS_DOUBLE_COUNT)
					return true;
				else
					currentNode = currentNode.PARENT;
			}
			
			return false;
			
		}
		
		/**Checks if there is a double-counted small cave in the path this node is on.
		 * @return The ID of the node that is doubly counted, if there is one. Otherwise, null is returned.*/
		public String getDoubleNode() {
			
			TreeNode currentNode = this; //current node
			
			while(currentNode != null) {
				if(currentNode.IS_DOUBLE_COUNT)
					return currentNode.nodeId;
				else
					currentNode = currentNode.PARENT;
			}
			
			return null;
			
		}
		
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {

		TreeNode pathTree = new TreeNode(CAVE_GRAPH.START_NODE, null); //tree to store paths
		TreeNode currentNode = null; //track current tree location
		Queue<Pair<String, TreeNode>> searchQ = new LinkedList<>(); //graph search queue
		
		//add start node to queue
		searchQ.add(new Pair<String, TreeNode>(CAVE_GRAPH.START_NODE, pathTree));
		
		//find all paths
		while(!searchQ.isEmpty()) {
			
			Pair<String, TreeNode> qTop = searchQ.remove(); //pop queue
			String nextCave = qTop.first; //get next cave to search
			currentNode = qTop.second; //update current tree node
			
			//get all neighbors
			for(String neighbor : CAVE_GRAPH.getNeighbors(nextCave)) {
				
				if(!CAVE_GRAPH.isSmallCave(neighbor) || !currentNode.isNodeInPath(neighbor)) {
					
					TreeNode newChild = new TreeNode(neighbor, currentNode); //create child node
					currentNode.children.add(newChild); //add to tree
					
					//add neighbor to queue if it's not the end node
					if(!neighbor.equals(CAVE_GRAPH.END_NODE))
						searchQ.add(new Pair<String, TreeNode>(neighbor, newChild));
					
				}
					
			}
				
		}
		
		System.out.println(pathTree.countPaths());
			
	}
		

	@Override
	protected void challengePart2() {
		
		TreeNode pathTree = new TreeNode(CAVE_GRAPH.START_NODE, null); //tree to store paths
		TreeNode currentNode = null; //track current tree location
		Queue<Pair<String, TreeNode>> searchQ = new LinkedList<>(); //graph search queue
		
		//add start node to queue
		searchQ.add(new Pair<String, TreeNode>(CAVE_GRAPH.START_NODE, pathTree));
		
		//find all paths
		while(!searchQ.isEmpty()) {
			
			Pair<String, TreeNode> qTop = searchQ.remove(); //pop queue
			String nextCave = qTop.first; //get next cave to search
			currentNode = qTop.second; //update current tree node
			
			//get all neighbors
			for(String neighbor : CAVE_GRAPH.getNeighbors(nextCave)) {
				
				//did not feel like condensing these if statements
				if(!neighbor.equals(CAVE_GRAPH.START_NODE)) {
					
					if(CAVE_GRAPH.isSmallCave(neighbor)) {
						
						if(currentNode.getDoubleNode() == null) {
							
							TreeNode newChild = new TreeNode(neighbor, currentNode); //create child node
							currentNode.children.add(newChild); //add to tree
							
							//add neighbor to queue if it's not the end node
							if(!neighbor.equals(CAVE_GRAPH.END_NODE))
								searchQ.add(new Pair<String, TreeNode>(neighbor, newChild));
							
						}
						else if(!neighbor.equals(currentNode.getDoubleNode()) && !currentNode.isNodeInPath(neighbor)) {
							
							TreeNode newChild = new TreeNode(neighbor, currentNode); //create child node
							currentNode.children.add(newChild); //add to tree
							
							//add neighbor to queue if it's not the end node
							if(!neighbor.equals(CAVE_GRAPH.END_NODE))
								searchQ.add(new Pair<String, TreeNode>(neighbor, newChild));
							
						}
						
					}
					else {
						
						TreeNode newChild = new TreeNode(neighbor, currentNode); //create child node
						currentNode.children.add(newChild); //add to tree
						
						//add neighbor to queue if it's not the end node
						if(!neighbor.equals(CAVE_GRAPH.END_NODE))
							searchQ.add(new Pair<String, TreeNode>(neighbor, newChild));
						
					}
					
				}
					
			}
				
		}
		
		System.out.println(pathTree.countPaths());
		
	}

}
