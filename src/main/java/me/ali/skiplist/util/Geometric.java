package me.ali.skiplist.util;

import java.util.Random;

public class Geometric {

	private final Random rand;

	public Geometric() {
		this.rand = new Random();
	}
	
	public int next() {
		int i = 1;
		while (rand.nextBoolean())
			i++;
		return i;
	}
	
}
