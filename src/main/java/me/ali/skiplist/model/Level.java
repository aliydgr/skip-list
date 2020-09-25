package me.ali.skiplist.model;

import java.util.Iterator;

class Level implements Iterable<Node> {

	final Node min;
	final Node max;
	Level down;

	private Level() {
		this.min = new Node(Integer.MIN_VALUE);
		this.max = new Node(Integer.MAX_VALUE);
		this.min.setRight(max);
	}

	protected static Level createEmpty() {
		return new Level();
	}
	
	protected Level getDown() {
		return down;
	}
	
	protected void setDown(Level down) {
		this.down = down;
		this.min.setDown(down.min);
		this.max.setDown(down.max);
	}
	
	protected boolean hasDown() {
		return this.down != null;
	}

	@Override
	public Iterator<Node> iterator() {
		return new LevelIterator(min);
	}

	private static class LevelIterator implements Iterator<Node> {

		Node next;

		LevelIterator(Node min) {
			this.next = min;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public Node next() {
			Node result = this.next;
			this.next =  result.getRight();
			return result;
		}

	}

}
