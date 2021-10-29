package redblacktrees;

public class RedBlackTester {
	public static void main(String[] args) {
		RedBlackTree tree1 = new RedBlackTree();
		
		tree1.insert(55);

		tree1.insert(40);
		tree1.insert(65);
		tree1.insert(60);
		tree1.insert(75);
		tree1.insert(57);
		tree1.insert(42);
		
		
		//tree1.printInOrder();
		tree1.printSideways();
		
		//System.out.println(tree1.successor(65));
		//System.out.println(tree1.successor(57));
		//System.out.println(tree1.predecessor(42));
		System.out.println();
		System.out.println(tree1.treeBH());
		
		
		tree1.delete(40);
		System.out.println();
		
		System.out.println();
		System.out.println();
		System.out.println();
		tree1.printSideways();
		
		//System.out.println(tree1.valInTree(27));
		
		//System.out.println(tree1.maxVal());
		//System.out.println(tree1.minVal());
		
		
		//Node redNode = new Node(37);
		//System.out.println(redNode);
		//redNode.changeColor();
		//
		//System.out.println(redNode);
		
		
		//RedBlackTree tree2 = new RedBlackTree(redNode);
		//System.out.println(tree2);
	}

}
