package com.csus.csc133.views;

import java.util.Observer;
import java.util.Observable;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;
import com.csus.csc133.GameModel;

public class ViewMap extends Container implements Observer {

	public ViewMap() {
		super();
		getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(184, 57, 57)));
	}

	@Override
	public void update(Observable observable, Object data) {
		if (observable instanceof GameModel) {
			GameModel gm = (GameModel) observable;
			gm.printOutput();
		}
		revalidate();
		repaint();
	}
	


}
