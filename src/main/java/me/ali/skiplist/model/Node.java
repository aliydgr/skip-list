package me.ali.skiplist.model;

public class Node {

	private final int value;
	private Node down;
	private Node right;
	
	public Node(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
	
	protected Node getDown() {
		return down;
	}
	
	protected void setDown(Node down) {
		this.down = down;
	}
	
	protected boolean hasDown() {
		return this.down != null;
	}
	
	protected Node getRight() {
		return right;
	}
	
	protected void setRight(Node right) {
		this.right = right;
	}
	
	protected boolean hasRight() {
		return this.right != null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (value != other.value)
			return false;
		return true;
	}

}
