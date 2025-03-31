package com.csus.csc133.views;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.csus.csc133.GameModel;

import java.util.Observer;
import java.util.Observable;

public class ViewStatus extends Container implements Observer {
	private Label[] labels;
	private String[] labelTexts = { "Lecture Hall: ", "Lecture Time: ", "Game Time: ", "Absences: ", "Hydration: ",
			"Water Intake: ", "Hold Time: " };

	public ViewStatus() {
		super(new BoxLayout(BoxLayout.Y_AXIS));

		labels = new Label[labelTexts.length];

		for (int i = 0; i < labelTexts.length; i++) {
			labels[i] = new Label(labelTexts[i]);
			add(labels[i]);

		}

	}

	@Override
	public void update(Observable observable, Object data) {
		if (observable instanceof GameModel) {
			GameModel gm = (GameModel) observable;
			labels[0].setText("Lecture Hall: " + gm.getLectureName());
			labels[1].setText("Lecture Time: " + gm.getLectureTimeRemaining());
			labels[2].setText("Game Time: " + gm.getTime());
			labels[3].setText("Absences: " + gm.getPlayer().getAbsenceTime());
			labels[4].setText("Hydration: " + gm.getPlayer().getHydration());
			labels[5].setText("Water Intake: " + gm.getPlayer().getWaterIntake());
			labels[6].setText("Hold Time: " + gm.getPlayer().getTimeRemain());

		}
		revalidate();
	}
}
