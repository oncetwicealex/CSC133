package com.csus.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.csus.csc133.GameModel;

public class ChangePositionCommand extends Command {
	private GameModel gm;
	private String action; 
	
	public ChangePositionCommand(GameModel gm, String action) {
		super(action);
		this.gm = gm;
		this.action = action;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!gm.isPaused()) {
			gm.updateMessage("Must pause the game before changing positions.");
			return;
		}
		
		if (gm.isPositionMode()) {
			gm.setPositionMode(false);
			gm.updateMessage("Canceled changing position");
		} else {
			gm.setPositionMode(true);
			gm.updateMessage("Click on Map to move selected object");
		}
	}
}
