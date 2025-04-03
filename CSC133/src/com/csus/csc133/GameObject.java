package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.csus.csc133.student.Student;

public abstract class GameObject {
	private double x;
	private double y;
	private int color;
	private int size;

	/*
	 * constructor: initializes an object to a random position within the game
	 * world, and utilizes a boundary check
	 */
	public GameObject() {
		this.x = Math.max(0, Math.min(x, GameModel.getGAMEWORLD_WIDTH() - 1));
		this.y = Math.max(0, Math.min(y, GameModel.getGAMEWORLD_HEIGHT() - 1));
		this.color = ColorUtil.rgb(255, 0, 0);
		this.size = 0;
	}

	public double getX() {
		return x;
	}

	public void setX(double next_X) {
		x = next_X;
		clampPosition();
	}

	public double getY() {
		return y;
	}

	public void setY(double next_Y) {
		y = next_Y;
		clampPosition();
	}

	public int getColor() {
		return color;
	}

	public void setColor(int newColor) {
		this.color = newColor;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int newSize) {
		this.size = newSize;
	}

	public void clampPosition() {
		int halfSize = getSize() / 2;

		if (x < halfSize) {
			x = halfSize;
		} else if (x > GameModel.getGAMEWORLD_WIDTH() - halfSize) {
			x = GameModel.getGAMEWORLD_WIDTH() - halfSize;

		}
		
		if (y < halfSize) {
			y = halfSize;
		} else if (y > GameModel.getGAMEWORLD_HEIGHT() - halfSize) {
			y = GameModel.getGAMEWORLD_HEIGHT() - halfSize;
		}

	}
	public int[] boundingBox(GameObject o) {
		int left = (int) (o.getX() - (o.getSize() / 2));
		int right = (int) (o.getX() + (o.getSize() / 2));
		int top = (int) (o.getY() - (o.getSize() / 2));
		int bottom = (int) (o.getY() + (o.getSize() / 2));
		
		return new int[] {top, right, left, bottom};

	}
	
	public boolean overlaps(GameObject o) {
		int halfThis = this.getSize() / 2;
		int halfOther = o.getSize() / 2;
		
		double dx = Math.abs(this.getX() - o.getX());
		double dy = Math.abs(this.getY() - o.getY());
		return dx < (halfThis + halfOther) && dy < (halfThis + halfOther);

	}

	public abstract void handleCollide(Student s);

	public abstract void draw(Graphics g, Component c);

	@Override
	public String toString() {
		String fullname = getClass().toString();
		fullname = fullname.substring(fullname.lastIndexOf('.') + 1);

		return fullname + ", pos (" + Math.round(x) + ", " + Math.round(y) + "), color: "
				+ Integer.toHexString(getColor()) + ", size: " + size;
	}

}