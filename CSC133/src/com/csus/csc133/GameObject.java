package com.csus.csc133;

import java.util.Random;
import com.csus.csc133.student.Student;

public abstract class GameObject {
	private double x;
	private double y;
	private static final Random random = new Random();

	/*
	 * constructor: initializes an object to a random position within the game
	 * world, and utilizes a boundary check
	 */
	public GameObject() {
		this.x = Math.max(0, Math.min(x, GameModel.getGAMEWORLD_WIDTH() - 1));
		this.y = Math.max(0, Math.min(y, GameModel.getGAMEWORLD_HEIGHT() - 1));
	}

	public double getX() {
		return x;
	}

	public void setX(double next_X) {
		x = next_X;
	}

	public double getY() {
		return y;
	}

	public void setY(double next_Y) {
		y = next_Y;
	}

	public abstract void handleCollide(Student s);

	@Override
	public String toString() {
		String fullname = getClass().toString();
		fullname = fullname.substring(fullname.lastIndexOf('.') + 1);

		return fullname + ", pos (" + Math.round(x) + ", " + Math.round(y) + ")";
	}

}