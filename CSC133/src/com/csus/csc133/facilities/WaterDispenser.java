package com.csus.csc133.facilities;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.csus.csc133.student.Student;

public class WaterDispenser extends Facility {
	private static int waterSize = 40;
	private String name = "Water Dispenser";

	public WaterDispenser() {
		setSize(waterSize);
	}

	@Override
	public void handleCollide(Student s) {
		if (s == null)
			return;

		s.drinkWater();

	}

	public String toString() {
		return super.toString();

	}

	@Override
	public void draw(Graphics g, Component c) {
		int size = getSize();
		int xPos = (int) (getX() + c.getX() - size / 2);
		int yPos = (int) (getY() + c.getY() - size / 2);
		
		g.setColor(ColorUtil.rgb(50, 66, 168));
		g.fillArc(xPos, yPos, size, size, 0, 360);

		g.setColor(ColorUtil.BLACK);
		g.drawString("Water", xPos, yPos + size);
		
	}

	public String getName() {
		return name;
	}

}