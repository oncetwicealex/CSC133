package com.csus.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.csus.csc133.GameModel;

public class SystemCommand extends Command {
	private GameModel gm;
	private String action;

	public SystemCommand(GameModel gm, String action) {
		super(action);
		this.gm = gm;
		this.action = action;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (action) {
		case "About":
			Dialog.show("About", "A3 Alexandra Allwein CSC133 FALL 2025", "Confirm", null);
			break;
		case "Exit":
			if (Dialog.show("Confirm Exit", "Are you sure you want to exit?", "Yes", "No")) {
				Display.getInstance().exitApplication();
			}
			break;

		}
	}

}
