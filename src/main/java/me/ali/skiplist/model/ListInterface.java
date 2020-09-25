package me.ali.skiplist.model;

public interface ListInterface<T> {

	boolean find(T value);
	void insert(T value);
	boolean delete(T value);
	
	void join(ListInterface<T> another);
	void paste(ListInterface<T> another);
	ListInterface<T> split(T splitter);
	
}
