package util;

import lejos.robotics.subsumption.Behavior;

/*
 
 I ran into problems when making this class generic... Seems like openjdk6
 in addition to missing a PriorityQueue class apparently doesn't know Array class so 
 using StackOverflow trick to create the array didn't work...
 
 @SuppressWarnings("unchecked")
 final T[] a = (T[]) Array.newInstance(type, size);
 this.array = a;
 
 Decided to make this only work with the Behavior class then... Maybe another time!
 
 */

public class PriorityQueue {

	private int heap_size;
	private PriorityPair[] array;
	
	public PriorityQueue(int size) {
		this.array = new PriorityPair[size];
		this.heap_size = 0;
	}
	
	public PriorityQueue(PriorityQueue queue) {
		this.array = queue.array;
		this.heap_size = queue.heap_size;
	}
	
	//Create a new pair and insert it in the right place
	public boolean insert(Behavior behavior, int priority) {
		if (this.isFull()) return false;
		
		this.heap_size++;
		int i = this.heap_size;
		PriorityPair pair = new PriorityPair(behavior, priority);
		
		while (i > 1 && this.array[this.parent(i)].getPriority() > priority) {
			this.array[i] = this.array[this.parent(i)];
			i = this.parent(i);
		}
		
		this.array[i] = pair;
		
		return true;
	}
	
	public Behavior min() {
		return this.array[1].getObject();
	}
	
	public Behavior del_min() {
		Behavior min = this.min();
		
		this.array[1] = this.array[heap_size];
		this.heap_size--;
		this.heapify(1);
		
		return min;
	}
	
	private void heapify(int index) {
		int left = this.leftChild(index);
		int right = this.rightChild(index);
		
		if (right <= this.heap_size) {
			//which one is smaller?
			int smallest;
			if (this.array[left].getPriority() < this.array[right].getPriority()) {
				smallest = left;
			}else {
				smallest = right;
			}
			
			if (this.array[index].getPriority() > this.array[smallest].getPriority()) {
				PriorityPair temp = this.array[index];
				this.array[index] = this.array[smallest];
				this.array[smallest] = temp;
				this.heapify(smallest);
			}
			
		}else if (left == this.heap_size && this.array[index].getPriority() > this.array[left].getPriority()) {
			PriorityPair temp = this.array[index];
			this.array[index] = this.array[left];
			this.array[left] = temp;
		}
	}
	
	public boolean isEmpty() {
		return this.heap_size == 0;
	}
	
	public boolean isFull() {
		return this.heap_size == array.length-1;
	}
	
	public int size() {
		return this.heap_size;
	}
	
	private int parent(int index) {
		return (int)Math.floor(index / 2.0);
	}
	
	private int leftChild(int index) {
		return index * 2;
	}
	
	private int rightChild(int index) {
		return index * 2 + 1;
	}
	
}
