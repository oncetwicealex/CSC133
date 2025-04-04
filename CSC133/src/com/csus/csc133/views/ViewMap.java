package com.csus.csc133.views;

import java.util.Observer;
import java.util.Observable;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
import com.csus.csc133.GameModel;
import com.csus.csc133.GameObject;
import com.csus.csc133.GameObjectCollection.CustomIterator;

public class ViewMap extends Container implements Observer {
	private GameModel gm;
	private int lastWidth = 0;
    private int lastHeight = 0;

	public ViewMap() {
		super();
		getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(184, 57, 57)));
	}
	

	@Override
	public void update(Observable observable, Object data) {
		if (observable instanceof GameModel) {
			this.gm = (GameModel) observable;
			gm.printOutput();
		}
		revalidate();
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		 int currentWidth = getWidth();
	        int currentHeight = getHeight();
	        if (currentWidth != lastWidth || currentHeight != lastHeight) {
	            lastWidth = currentWidth;
	            lastHeight = currentHeight;
	            GameModel.setGAMEWORLD_WIDTH(currentWidth);
	            GameModel.setGAMEWORLD_HEIGHT(currentHeight);
	            System.out.println("ViewMap dimensions updated: " + currentWidth + " x " + currentHeight);
	        }

		if (gm != null) {
			CustomIterator it = gm.getObjectsIterator();

			while (it.hasNext()) {
				GameObject o = it.getNext();
				o.draw(g, this);
			}
		}
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		x = x - getAbsoluteX();
		y = y - getAbsoluteY();
		
		if(gm.isPaused() && gm.isPositionMode()) {
			moveSelectedObject(x,y);
			return;
		}
		
		GameObject clickedObject = null;
		
		CustomIterator it = gm.getObjectsIterator();
		while(it.hasNext()) {
			GameObject o = it.getNext();
			if (o.contains(x, y)) {
				clickedObject = o;
				break;
			}
		}
		
		if (clickedObject != null ) {
			selectOnly(clickedObject);
			
		} else {
			unselectAll();
		}
		
		repaint();
		revalidate();
	}


	private void moveSelectedObject(int x, int y) {
		GameObject selected = null;
		CustomIterator it = gm.getObjectsIterator();
		while(it.hasNext()) {
			GameObject o = it.getNext();
			if(o.isSelected()) {
				selected = o;
				break;
			}
		}
		
		if(selected!=null) {
			selected.setX(x);
			selected.setY(y);
			gm.updateMessage("Moved object");
		}else {
			gm.updateMessage("No selected object to move");
			
			
			
		}
		gm.setPositionMode(false);
		repaint();
		revalidate();
	}


	private void unselectAll() {
		CustomIterator it = gm.getObjectsIterator();
		while (it.hasNext()) {
			it.getNext().setSelected(false);
		}
	}


	private void selectOnly(GameObject clickedObject) {
		CustomIterator it = gm.getObjectsIterator();
		while (it.hasNext()) {
			GameObject o = it.getNext();
			o.setSelected(o==clickedObject);
		}
		
	}

}
