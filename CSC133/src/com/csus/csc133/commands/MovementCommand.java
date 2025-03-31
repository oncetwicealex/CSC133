package com.csus.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.csus.csc133.GameModel;

public class MovementCommand extends Command {
	private GameModel gm;
	private String action;

	public MovementCommand(GameModel gm, String action) {
		super(action);
		this.gm = gm;
		this.action = action;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (action) {
		case "Move":
			gm.getPlayer().startMoving();
			break;
		case "Stop":
			gm.getPlayer().stopMoving();
			break;
		case "Turn Left":
			gm.getPlayer().turnLeft();
			break;
		case "Turn Right":
			gm.getPlayer().turnRight();

			break;

		}
	}

}
