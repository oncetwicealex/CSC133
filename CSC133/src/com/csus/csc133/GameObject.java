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
	private boolean selected = false;

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
		

		if (x < getSize() / 2) {
			x = getSize() / 2;
		} else if (x > GameModel.getGAMEWORLD_WIDTH() - getSize() / 2) {
			x = GameModel.getGAMEWORLD_WIDTH() - getSize() / 2;

		}

		if (y < getSize() / 2) {
			y = getSize() / 2;
		} else if (y > GameModel.getGAMEWORLD_HEIGHT() - getSize() / 2) {
			y = GameModel.getGAMEWORLD_HEIGHT() - getSize() / 2;
		}

	}

	public int[] getBoundingBox() {
		int left = (int) (getX() - (getSize() / 2));
		int right = (int) (getX() + (getSize() / 2));
		int top = (int) (getY() - (getSize() / 2));
		int bottom = (int) (getY() + (getSize() / 2));

		return new int[] { left, top, right, bottom };

	}

	public boolean overlaps(GameObject o) {
		int halfThis = this.getSize() / 2;
		int halfOther = o.getSize() / 2;

		double dx = Math.abs(this.getX() - o.getX());
		double dy = Math.abs(this.getY() - o.getY());
		return dx < (halfThis + halfOther) && dy < (halfThis + halfOther);

	}

	public boolean contains(int x, int y) {
		int left = (int) (getX() - getSize() / 2);
		int right = (int) (getX() + getSize() / 2);
		int top = (int) (getY() - getSize() / 2);
		int bottom = (int) (getY() + getSize() / 2);
		return (x >= left && x <= right && y >= top && y <= bottom);

	}

	public boolean collidesWith(GameObject o) {
		int[] box1 = this.getBoundingBox(); // box1 = {left1, top1, right1, bottom1 }
		int[] box2 = o.getBoundingBox(); // box 2 = {left2, top2, right2, bottom2 }

		// check for no left/right overlap first: right1 < left2 or right2 < left1
		if (box1[2] < box2[0] || box2[2] < box1[0]) {
			return false;
		}

		// now check for no top/bottom overlap: bottom1 < top2 or bottom2 < top1
		if (box1[3] < box2[1] || box2[3] < box1[1]) {
			return false;
		}

		// if no separation, the boxes overlap
		return true;

	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean s) {
		selected = s;
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