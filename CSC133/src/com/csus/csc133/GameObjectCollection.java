package com.csus.csc133;

import java.util.NoSuchElementException;
import java.util.Vector;

import com.csus.csc133.facilities.LectureHall;

public class GameObjectCollection {
	private Vector<GameObject> objects;

	public GameObjectCollection() {
		objects = new Vector<>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public int size() {
		return objects.size();
	}

	public CustomIterator getCustomIterator() {
		return new CustomIterator();
	}

	public class CustomIterator {
		private int i = 0;

		public boolean hasNext() {
			return i < objects.size();
		}

		public GameObject getNext() {
			if (!hasNext()) {
				throw new NoSuchElementException("Collection empty.");
			} else {
				return objects.get(i++);
			}
		}
	}

	public void remove(GameObject o) {
		objects.remove(o);
		
	}
}
