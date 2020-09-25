package me.ali.skiplist;

import java.util.Iterator;
import java.util.Scanner;

import me.ali.skiplist.model.Node;
import me.ali.skiplist.model.SkipList;

public class App {

	public static void main(String[] args) {

		SkipList list = new SkipList();
		for (int i = 20; i > 0; i--) {
			list.insert(i);
		}
		print(list);

		try (Scanner sc = new Scanner(System.in)) {
			outer: while (true) {
				String operand = sc.next();
				switch (operand) {
				case "e":
				case "exit":
					break outer;

				case "i":
				case "insert":
					list.insert(sc.nextInt());
					print(list);
					break;

				case "d":
				case "delete":
					list.delete(sc.nextInt());
					print(list);
					break;

				case "f":
				case "find":
					System.out.println(list.find(sc.nextInt()));
					break;

				case "s":
				case "split":
					SkipList splited = list.split(sc.nextInt());
					print(list);
					print(splited);
					break;
					
				default:
					System.out.println("Illegal Operand");
					break;
				}

			}
		}

	}

	static void print(SkipList list) {
		Iterator<Node> it = list.iterator();
		while (it.hasNext()) {
			Node next = it.next();
			System.out.print(next.value() + "  ");
			if (next.value() == Integer.MAX_VALUE)
				System.out.println();
		}
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
	}

}
