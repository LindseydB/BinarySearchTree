import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tree {
	//define global variables
	private Node root;
	ArrayList<Node> nodes = new ArrayList<Node>();

	//default constructor for creating a new tree
	public Tree() {
		//open the CSV file containing all the names
		String fileName = "names.csv";
		this.loadNodes(fileName);
		this.init();
	}
	
	// *** getting ready to load custom files, yet to implement with JavaFX ***
	public Tree(String fileName) {
		this.loadNodes(fileName);
		this.init();
	}
	
	private void init() {
		//build the initial tree (unsorted)
		buildTree();

	}
	
	private void loadNodes(String fileName) {
		Scanner scanner;
		//try catch block for reading from the file
		try {
			//create a new scanner
			scanner = new Scanner(new File(fileName));
			//read the file line by line
			while (scanner.hasNext()) {
				//split each line on the white space to get first and last names ***(Might create issues where surnames have spaces, maybe resolve by only splitting on the first space)***
				String name[] = scanner.nextLine().trim().split(" ");
				//the first array value contains the firstname, and the second the surname
				String firstName = name[0];
				String lastName = name[1];
				
				//create a new node containing a Person object
				Node n = new Node(new Person(firstName,lastName));
				//add the node to the arraylist of nodes
				nodes.add(n);
			}
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//method to build the tree. REFERENCE: https://www.moreofless.co.uk/binary-search-tree-bst-java/
	public void buildTree() {
		//get the first node from the array list
		Node rootNode = nodes.get(0);
		//add the node to the tree (the first one will always be the root node)
		this.put(rootNode.getPerson());
	
		//loop through all the nodes and add each person under the root node
		for(Node n : nodes) {
			this.put(root, n.getPerson());
		}
	}
	
	//method for adding a new person to the tree
	public void put(Person newP) {
		this.put(root, newP);
	}
	

	//method to add new node to to the tree
	public void put(Node current, Person next) {
		//check if this is the first node in the tree
		if(root == null) {
			//its the first node, so add it as the root
			root = new Node(next);
		}else {
			//else - add the child node
			root.put(next);
		}	
	}

	//find a person in the tree by a given name
	public Person get(String name) {
		//if root is null, return null, else recursively call the get method of the node to find it
		return root == null ? null : root.get(name); 
	}

	//method to get the tree in alphabetical order
	public ArrayList<Node> inOrder() {
		//create new arraylist
		ArrayList<Node> allNodesInOrder = new ArrayList<Node>();
		//sort the binary tree
		return inOrder(root,allNodesInOrder);
	}
	
	//method where the actual sorting happens recursively
	private ArrayList<Node> inOrder(Node node,ArrayList<Node> nodes) {
		//if the root is null, the tree hasn't been built
		if (node == null) {
			return null;
		}   
		//in-order transversal of the tree
		inOrder(node.left,nodes);
		//add each node to an ArrayList in order
		nodes.add(node);
		inOrder(node.right,nodes);
		//return the sorted arraylist
		return nodes;
	} 
	
	//method to get the tree in reverse order (descending alphabetically)
	public ArrayList<Node> reverseOrder() {
		//create new arraylist
		ArrayList<Node> allNodesInReverseOrder = new ArrayList<Node>();
		//sort the binary tree
		return reverseOrder(root, allNodesInReverseOrder);
	}

	//method where the actual sorting happens recursively
	private ArrayList<Node> reverseOrder(Node node,ArrayList<Node> allPeople) {
		//if the root is null, the tree hasn't been built
		if (node == null) {
			return null;
		}   
		
		//same as inOrder method, but subtrees switched (.right first, then .left)
		reverseOrder(node.right,allPeople);
		//add the node to the arraylist
		allPeople.add(node);
		reverseOrder(node.left,allPeople);
		//return the sorted arraylist
		return allPeople;
	}
	
	
	
	//method to find nodes that have a person with a name (first and surname) longer than the specified length. 
	public List<Person> dfFindLongNames(int minLength){
		//create the list
		List<Person> listOfLong = new ArrayList<Person>();
		//call the helper method
		dfFindLongNamesHelper(root, minLength, listOfLong);
		//return the list
		return listOfLong;
	}
	
	//recursive method doesn't require a return value REFERENCE: slide 24
	public void  dfFindLongNamesHelper(Node n, int minLength, List<Person> listOfLong){
		//make sure there's a valid node
		if (n!=null){
			//check the length of the name of the person
			if (n.getPerson().getName().length()> minLength) { 
				//if it matches, add it to the list
				listOfLong.add(n.getPerson()); 
			}
			//recurseively call the helper function for the left and right subtrees
			dfFindLongNamesHelper(n.left, minLength, listOfLong); 
			dfFindLongNamesHelper(n.right, minLength, listOfLong); 
		}
	}
	
	
	
	
	//balancing binary tree reference: https://www.geeksforgeeks.org/convert-normal-bst-balanced-bst/

	/* Recursive function to construct binary tree */
	Node buildTreeHelper(ArrayList<Node> nodes, int start, int end)  
	{ 
		// base case - stop recursive function once all nodes are added
		if (start > end) 
			return null; 

		// Get the middle element and make it root
		int mid = (start + end) / 2; 
		Node node = nodes.get(mid); 

		// Using index in Inorder traversal, construct left and right subtrees
		node.left = buildTreeHelper(nodes, start, mid - 1); 
		node.right = buildTreeHelper(nodes, mid + 1, end); 

		return node; 
	} 
	
	public void fixSkew() {
		fixSkew(root);
	}

	//function to fix the skew in the binary tree
	private Node fixSkew(Node r)  
	{ 
		// Get an arrayList of all the nodes in sorted (alphabetical) order 
		ArrayList<Node> nodes = new ArrayList<Node>(); 
		inOrder(r, nodes); 

		//Get the total number of nodes in the tree, and use the helper method to re-construct it 
		int n = nodes.size(); 
		return buildTreeHelper(nodes, 0, n - 1); 
	} 

	//method to get the total height of the binary tree (can be used to see if the tree is balanced)
	private int depth(Node node)  
    { 
		//if the root node is null, there is no tree
        if (node == null) {
            return 0; 
        }else { 
            //recursively compute the height of each subtree
            int lDepth = depth(node.left); 
            int rDepth = depth(node.right); 
            //work out which subtree is larger
            int bigger = Math.max(lDepth, rDepth);
            //return the larges subtree
            return bigger+1;
        } 
    } 
	
	public int getTreeHeight() {
		return depth(root);
	}

}
