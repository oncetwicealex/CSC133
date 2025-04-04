package com.csus.csc133.facilities;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.csus.csc133.student.Student;

public class Restroom extends Facility {
	private int restroomSize = 90;
	private String name = "Restroom";
	

	public Restroom() {
		super();
		setSize(restroomSize);
	}

	@Override
	public void handleCollide(Student s) {
		if (s == null)
			return;

		s.useRestroom();
	}

	public String toString() {
		return super.toString();

	}

	@Override
	public void draw(Graphics g, Component c) {
		int size = getSize();
		int xPos = (int)(getX() + c.getX() - size/2);
		int yPos = (int)(getY() + c.getY() - size/2);
		
		g.setColor(ColorUtil.rgb(69,115,45));
		g.fillRect(xPos,yPos,size,size);
	
		g.setColor(ColorUtil.BLACK);
		g.drawString("Restroom", xPos, yPos + size);
		
		if(isSelected()) {
			g.setColor(ColorUtil.rgb(255,0,0));
			g.drawRect(xPos, yPos, size, size);
			
		}
		
	}

	public String getName() {
		return name;
	}

}