package com.csus.csc133.commands;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.csus.csc133.CustomButton;
import com.csus.csc133.GameModel;

public class PauseCommand extends Command {
	
	private GameModel gm;
	private String action; 
	
	public PauseCommand(GameModel gm, String action) {
		super(action);
		this.gm = gm;
		this.action = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Button source = (Button) e.getComponent();
		
		switch (action) {
		case "Pause":
			gm.setPaused(true);
			source.setText("Play");
			this.action = "Play";
			this.setCommandName("Play");
			source.getParent().revalidate();
			break;
			
		case "Play":
			gm.setPaused(false);
			source.setText("Pause");
			this.action = "Pause";
			this.setCommandName("Pause");
			source.getParent().revalidate();
			break;
		}
	}

}
