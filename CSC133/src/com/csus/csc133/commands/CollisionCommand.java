package com.csus.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.csus.csc133.GameModel;

public class CollisionCommand extends Command {
	private GameModel gm;
	private String action;

	public CollisionCommand(GameModel gm, String action) {
		super(action);
		this.gm = gm;
		this.action = action;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (action) {
		case "Lecture Hall":
			gm.studentcollidelecturehall();
			break;
		case "Water Dispenser":
			gm.studentcollidewater();
			break;
		case "Restroom":
			gm.studentcolliderestroom();
			break;

		}

	}

}
