package me.ali.skiplist.model;

import java.util.Iterator;

import me.ali.skiplist.util.Geometric;

public class SkipList implements ListInterface<Integer>, Iterable<Node> {

	private Level upper;
	private int levelCount;
	private Geometric rand;

	public SkipList() {
		this.upper = Level.createEmpty();
		this.levelCount = 1;
		this.rand = new Geometric();
	}

	@Override
	public boolean find(Integer value) {
		Iterator<Node> it = upper.iterator();
		Node node = it.next();
		while (node != null && node.hasRight()) {
			Node next = node.getRight();
			if (node.value() < value && value <= next.value()) {
				if (next.value() == value)
					return true;
				node = node.getDown();
			} else {
				node = next;
			}
		}
		return false;
	}

	@Override
	public void insert(Integer value) {
		// generate random level for new value
		int levelIndex = rand.next();

		// create required empty levels
		while (levelIndex + 1 > levelCount) {
			levelCount++;
			Level newUpper = Level.createEmpty();
			newUpper.setDown(upper);
			upper = newUpper;
		}

		// find start level
		Level level = upper;
		while (levelIndex < levelCount) {
			level = level.getDown();
			levelIndex++;
		}

		// iterate and insert
		Iterator<Node> it = level.iterator();
		Node node = it.next();
		Node up = null;
		while (node != null && node.hasRight()) {
			Node next = node.getRight();
			if (node.value() < value && value <= next.value()) {
				Node newNode = new Node(value);
				node.setRight(newNode);
				newNode.setRight(next);
				if (up != null)
					up.setDown(newNode);
				up = newNode;
				node = node.getDown();
			} else {
				node = next;
			}
		}

	}

	@Override
	public boolean delete(Integer value) {
		Iterator<Node> it = upper.iterator();
		Node node = it.next();
		boolean deleted = false;
		while (node != null && node.hasRight()) {
			Node next = node.getRight();
			if (node.value() < value && value <= next.value()) {
				if(next.value() == value) {
					node.setRight(next.getRight());
					next.setDown(null);// for garbage collection
					deleted = true;
				}
				node = node.getDown();
			} else {
				node = next;
			}
		}
		return deleted;
	}

	@Override
	public void join(ListInterface<Integer> list) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Appends another to the end of this, assumes last key in this ≤ first key in another
	 */
	@Override
	public void paste(ListInterface<Integer> another) {
		// TODO Auto-generated method stub
	}

	/**
	 *  Remove from this all elements with keys ≥ splitter and return them in a new list
	 */
	@Override
	public SkipList split(Integer splitter) {
		SkipList newList = new SkipList();
		newList.levelCount = this.levelCount;
		
		{//copy levels
			Level thisL = this.upper;
			Level newL = newList.upper;
			while(thisL.hasDown()) {
				Level down = Level.createEmpty();
				newL.setDown(down);
				
				thisL = thisL.getDown();
				newL = newL.getDown();
			}
		}
		
		{//split
			Level thisL = this.upper;
			Level newL = newList.upper;
			while(true) {
				Iterator<Node> iterator = thisL.iterator();
				Node prev = null;
				while (iterator.hasNext()) {
					Node node = iterator.next();
					if(node.value() > splitter && node != thisL.max) {
						prev.setRight(thisL.max);
						newL.min.setRight(node);
						break;
					}
					prev = node;
				}
				
				if(thisL.hasDown()) {
					thisL = thisL.getDown();
					newL = newL.getDown();
				}else {
					break;
				}
			}
		}
		
		//TODO delete empty levels
		return newList;
	}

	@Override
	public Iterator<Node> iterator() {
		return new SkipListIterator(upper);
	}

	private static class SkipListIterator implements Iterator<Node> {

		Level currentLevel;
		Iterator<Node> currentLevelIterator;

		public SkipListIterator(Level level) {
			this.currentLevel = level;
			this.currentLevelIterator = level.iterator();
		}

		@Override
		public boolean hasNext() {
			return currentLevelIterator.hasNext() || currentLevel.hasDown();
		}

		@Override
		public Node next() {
			if (!currentLevelIterator.hasNext()) {
				this.currentLevel = currentLevel.getDown();
				this.currentLevelIterator = currentLevel.iterator();
			}
			return currentLevelIterator.next();
		}

	}

}
