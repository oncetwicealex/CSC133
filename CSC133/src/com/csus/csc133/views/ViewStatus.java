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

			try {
				double lectureTime = Double.parseDouble(gm.getLectureTimeRemaining());
				double roundedTime = Math.round(lectureTime * 10.0) / 10.0;
				labels[1].setText("Lecture Time: " + roundedTime);
			} catch (NumberFormatException nfe) {
				labels[1].setText("Lecture Time: " + gm.getLectureTimeRemaining());
			}

			try {
				double roundedTime = Math.round(gm.getTime() * 10.0) / 10.0;
				labels[2].setText("Game Time: " + roundedTime);
			} catch (NumberFormatException nfe) {
				labels[2].setText("Game Time: " + gm.getTime());
			}

			labels[3].setText("Absences: " + gm.getPlayer().getAbsenceTime());

			try {
				double roundedTime = Math.round(gm.getPlayer().getHydration() * 10.0) / 10.0;
				labels[4].setText("Hydration: " + roundedTime);
			} catch (NumberFormatException nfe) {
				labels[4].setText("Hydration: " + gm.getPlayer().getHydration());
			}

			try {
				double roundedTime = Math.round(gm.getPlayer().getWaterIntake() * 10.0) / 10.0;
				labels[5].setText("Water Intake: " + roundedTime);
			} catch (NumberFormatException nfe) {
				labels[5].setText("Water Intake: " + gm.getPlayer().getWaterIntake());
			}

			try {
				double roundedTime = Math.round(gm.getPlayer().getTimeRemain() * 10.0) / 10.0;
				labels[6].setText("Hold Time: " + roundedTime);
			} catch (NumberFormatException nfe) {
				labels[6].setText("Hold Time: " + gm.getPlayer().getTimeRemain());
			}

		}
		revalidate();
	}
}
