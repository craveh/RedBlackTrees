package redblacktrees;



public class RedBlackTree {
	
	Node root;
	Node nullNode;
	public RedBlackTree() {
		root = null;
		nullNode = new Node(null);
		
	}
	public RedBlackTree(int val) {
		this();
		root = new Node(val);
		//root.left = new Node(null);
		//root.right = new Node(null);
		
		root.left = nullNode;
		root.right = nullNode;
		nullNode.parent = root;
		//System.out.println("test " + nullNode.parent);
				
	}
	
	public RedBlackTree(Node node) {
		root = node;
		//Assuming just one node, that may not be black
		if(node.isRed()) {
			node.changeColor();
		}
		//What if they insert an entire tree here?
	}
	
	
	public void printSideways() {
		
		printSideways(root, 0);
	}
	
	private void printSideways(Node node, int level){
		if (node != null){
			printSideways(node.right, level+1);
			for (int i = 0; i < level; i++){
				System.out.print("       ");
			}
			if(node == nullNode) {
				System.out.println("nil");
			}else {
				System.out.println(node);
			}
			
			printSideways(node.left, level+1);
		}
	}
	
	public void insert(int val) {

//Create new Node
		Node newNode = new Node(val);
		newNode.left = newNode.right = nullNode;
		
//Check if root is null, this tree will always be valid
		if(root == null) {
			root = newNode;
			return;
		}
//Check if val is in tree		
		if(valInTree(val)) {
			return;
		}

//		
		Node nodePar = null;
		Node node = root;
		
		while(node != nullNode) {
			nodePar = node;
			if(val < node.value) {
				node = node.left;
			}else {
				node = node.right;
			}
			
		}
		
		newNode.parent = nodePar;
		if(val < nodePar.value) {
			nodePar.left = newNode;
		}else {
			nodePar.right = newNode;
		}
		newNode.changeColor();
		
//Test for valid case, of red child of the black root, otherwise tree.
		if(newNode.parent.parent == null) {
			return;
		}
		fixTreeInsert(newNode);
		System.out.println();
	
	}
	
	/**
	 * This method fixes up the tree, after insertion
	 */
	private void fixTreeInsert(Node newNode) {
		
		Node grandparentsOther;
		Node node =  newNode;
		
		
		while(node.parent.isRed()) {
			if(node.parent == node.parent.parent.right) {
				grandparentsOther = node.parent.parent.left;
				//Case 1
				if(grandparentsOther.isRed()) {
					node.parent.changeColor();
					node.parent.parent.changeColor();
					grandparentsOther.changeColor();
					node = node.parent.parent;
				}else {
					if(node == node.parent.left){
						node = node.parent;
						rightRotate(node);
					}
					node.parent.changeColor();
					node.parent.parent.changeColor();
					leftRotate(node.parent.parent);
							
				}
			}else {
				grandparentsOther = node.parent.parent.right;
				//Case 1
				if(grandparentsOther.isRed()) {
					node.parent.changeColor();
					node.parent.parent.changeColor();
					grandparentsOther.changeColor();
					node = node.parent.parent;
				}else {
					if(node == node.parent.right){
						node = node.parent;
						leftRotate(node);
					}
					node.parent.changeColor();
					node.parent.parent.changeColor();
					rightRotate(node.parent.parent);
							
				}
				
			}
			if(node == root) {
				break;
			}
			
			
		}
		if(root.isRed()) {
			root.changeColor();
			
		}
	}
	
	private void rightRotate(Node node) {
		// TODO Auto-generated method stub
		
		Node sib = node.left;
		node.left = sib.right;
		if(sib.right != nullNode) {
			sib.right.parent = node;
		}
		sib.parent = node.parent;
		if(node.parent == null) {
			root = sib;
			
		}else if(node == node.parent.right) {
			node.parent.right = sib;
			
		}else {
			node.parent.left = sib;
			
		}
		sib.right = node;
		node.parent = sib;
		
	}
	private void leftRotate(Node node) {
		
		Node sib = node.right;
		node.right = sib.left;
		if(sib.left != nullNode) {
			sib.left.parent = node;
		}
		sib.parent = node.parent;
		if(node.parent == null) {
			root = sib;
			
		}else if(node == node.parent.left) {
			node.parent.left = sib;
			
		}else {
			node.parent.right = sib;
			
		}
		sib.left = node;
		node.parent = sib;
		
	}
	
	public Integer minVal() {
		if(root == null) {
			return null;
		}
		return helpMinVal(root);
	}
	
	
	private int helpMinVal(Node node) {
		
		while(node.left != nullNode) {
			node = node.left;
		}
		
		return node.value;
	}
	
	public Integer maxVal() {
		if(root == null) {
			return null;
		}
		return helpMaxVal(root);
	}
	
	private int helpMaxVal(Node node) {
		
		while(node.right != nullNode) {
			node = node.right;
		}
		
		return node.value;
		
	}
	
	public Integer successor(int val) {
		
		Node node = getNode(val);
		if(node == null) {
			return null;
			
		}
		
		if(node.right != nullNode) {
			return helpMinVal(node.right);
		}else {
			Node successor = node.parent;
			while(successor!=nullNode & node == successor.right) {
				node = successor;
				successor = successor.parent;
			}
			return successor.value;
		}
		
		
	}
	
	public Integer predecessor(int val) {
		Node node = getNode(val);
		if(node == null) {
			return null;
		}
		
		if(node.left != nullNode ) {
			return helpMaxVal(node.left);
		}else {
			
			Node pred = node.parent;
			while(pred!=nullNode & node == pred.left) {
				node = pred;
				pred = pred.parent;
			}
			return pred.value;
			
		}
	
		
	}
	
	
	private Node getNode(int val) {
		//Node nodeReturn = null;
		Node node = root;
		while(node != nullNode) {
			if(node.value == val) {
				return node;
			}else if(node.value < val) {
				node = node.right;
			}else {
				node = node.left;
			}
		}
		return null;
	}
	
	public void delete(int val) {
		delete(root, val);
	}
	
	private void delete(Node node, int val) {
		
		Node toDel = nullNode;
		Node x, y;
		
		//get node
		//Node node = root;
		
		while(node != nullNode) {
			if(node.value == val) {
				toDel = node;
			}
			
			if(node.value < val){
				node = node.right;
			}else {
				node = node.left;
			}
			
		}
		
		if(toDel == nullNode) {
			System.out.println("This value is not in the tree");
		}
		// save node color
		y = toDel;
		boolean color = y.getColor();
		
		if(toDel.left == nullNode) {
			x = toDel.right;
			redBlackTrans(toDel, toDel.right);
		}else if(toDel.right == nullNode) {
			x = toDel.left;
			redBlackTrans(toDel, toDel.left);
		}else {
			y = getMinNode(toDel.right);
			color = y.getColor();
			//y.setColor(color);
			x = y.right;
			
			
			if (y.parent == toDel) {
		        x.parent = y;
		    } else {
		        redBlackTrans(y, y.right);
		        y.right = toDel.right;
		        y.right.parent = y;
		      }

		      redBlackTrans(toDel, y);
		      y.left = toDel.left;
		      y.left.parent = y;
		      y.setColor(toDel.getColor());
		}
		
		if(color) {
			deleteFixer(x);
		}
		
		
	}
	
	private void deleteFixer(Node inserted) {
		
		Node insertedSibling;
		while(inserted != root & inserted.getColor()) {
			if(inserted == inserted.parent.left) {
				insertedSibling = inserted.parent.right;
				if(insertedSibling.isRed()) {
					insertedSibling.setColor(true);
					inserted.parent.setColor(false);
					leftRotate(inserted.parent);
					insertedSibling = inserted.parent.right;
				}
				
				if(insertedSibling.left.getColor() && insertedSibling.right.getColor()) {
					insertedSibling.setColor(false);
					inserted = inserted.parent;
				}else {
					if(insertedSibling.right.getColor()){
						insertedSibling.left.setColor(true);
						insertedSibling.setColor(false);
						rightRotate(insertedSibling);
						insertedSibling = inserted.parent.right;
					}
					insertedSibling.setColor(inserted.parent.getColor());
					inserted.parent.setColor(true);
					insertedSibling.right.setColor(true);
					leftRotate(inserted.parent);
					inserted = root;
				}
				
			}else {
				insertedSibling = inserted.parent.left;
				if(insertedSibling.isRed()) {
					insertedSibling.setColor(true);
					inserted.parent.setColor(false);
					rightRotate(inserted.parent);
					insertedSibling = inserted.parent.left;
				}
				
				
				if(insertedSibling.left.getColor() && insertedSibling.right.getColor()) {
					insertedSibling.setColor(false);
					inserted = inserted.parent;
				}else {
					if(insertedSibling.left.getColor()){
						insertedSibling.right.setColor(true);
						insertedSibling.setColor(false);
						leftRotate(insertedSibling);
						insertedSibling = inserted.parent.left;
					}
					insertedSibling.setColor(inserted.parent.getColor());
					inserted.parent.setColor(true);
					insertedSibling.left.setColor(true);
					rightRotate(inserted.parent);
					inserted = root;
				}
			}
		}
		inserted.setColor(true);
		
	}
	private Node getMinNode(Node right) {
		
		
		Node node = root;
		while(node.left != nullNode) {
			node = node.left;
		}
		
		return node;

	}
	private void redBlackTrans(Node toDel, Node toPut) {
		if(toDel.parent == nullNode) {
			root = toPut;
		}else if(toDel == toDel.parent.left) {
			toDel.parent.left = toPut;
		}else {
			toDel.parent.right = toPut;
			
		}
		toPut.parent = toDel.parent;
		
	}
	public int treeBH() {
		int BH = 0;
		if(root == null) {
			return  BH;
		}
		Node node = root;
		while(node != nullNode) {
			if(node.getColor()) {
				BH++;
			}
			node = node.left;
			
		}
		
		return BH;
		
	}
	
	public boolean valInTree(int val) {
	
		Node node = root;
		
		if(node == null) {
			return false;
		}
		
		while(node.value != null) {
			if(node.value == val) {
				return true;
			}else if(node.value < val){
				node = node.right;
			}else {
				node = node.left;
			}
			
		}
		return false;
	}
	/**
	 * This method starts the inorder printing of the tree
	 */
	public void printInOrder() {
		if(root == null) {
			return;
		}
		
		inOrder(root);
		System.out.println();
	}
	
	/**
	 * This method uses recursion to print out the values of the tree in order
	 * 
	 * @param node, the node being recursed on
	 */
	
	private void inOrder(Node node) {
		if(node == nullNode) {
			return;
		}
		
		inOrder(node.left);
		System.out.print(node.value + " ");
		inOrder(node.right);
		
	}
	

}
