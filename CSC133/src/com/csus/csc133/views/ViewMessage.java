package com.csus.csc133.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import java.util.Observer;
import java.util.Observable;

public class ViewMessage extends Container implements Observer {

	private Label message;

	public ViewMessage() {
		super(new BorderLayout());
		getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(0, 0, 0)));
		message = new Label("Welcome to the game!");

		add(BorderLayout.SOUTH, message);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data != null && data instanceof String) {
			message.setText((String) data);
		} else {
			message.setText("");
		}

		revalidate();
	}

}
