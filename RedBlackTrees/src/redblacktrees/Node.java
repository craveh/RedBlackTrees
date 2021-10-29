package redblacktrees;

public class Node {
	Node left;
	Node right;
	Integer value;
	boolean black;
	Node parent;
	
	public Node(Integer val) {
		this.value = val;
		black = true;
		left = right = null;
		parent = null;
		
	}
	
	public boolean isRed() {
		if(!black) {
			return true;
		}
		return false;
	}
	
	public int getVal() {
		return this.value;
	}
	
	public void changeColor() {
		if(black) {
			black = false;
		}else {
			black = true;
		}
	}
		
	public String toString() {
		String color = "";
		if(isRed()) {
			color = "R";
		}else {
			color = "B";
		}
		
		return (color + value);
	}
	
	public boolean getColor() {
		return black;
	}
	public void setColor(boolean color) {
		this.black = color;
	}
		
	

}
