package com.csus.csc133;

import java.util.ArrayList;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.csus.csc133.commands.ChangePositionCommand;
import com.csus.csc133.commands.ChangeStrategyCommand;
import com.csus.csc133.commands.MovementCommand;
import com.csus.csc133.commands.PauseCommand;
import com.csus.csc133.commands.SystemCommand;
import com.csus.csc133.student.StudentPlayer;
import com.csus.csc133.views.ViewMap;
import com.csus.csc133.views.ViewMessage;
import com.csus.csc133.views.ViewStatus;

public class SacRun extends Form {

	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean upPressed = false;
	private boolean downPressed = false;
	
	private boolean paused = false;

	private GameModel gm;
	private ChangeStrategyCommand changeStrategyCommand;

	public SacRun() {
		gm = new GameModel();
		StudentPlayer.getStudentPlayer().setGameModel(gm);
		A2();

		UITimer timer = new UITimer(new Runnable() {
			public void run() {
				if (leftPressed) {
		            Command c = getCommandFor("Turn Left");
		            if (c != null) c.actionPerformed(null);
		        }
		        if (rightPressed) {
		            Command c = getCommandFor("Turn Right");
		            if (c != null) c.actionPerformed(null);
		        }
		        if (upPressed) {
		            Command c = getCommandFor("Move");
		            if (c != null) c.actionPerformed(null);
		        }
		        if (downPressed) {
		            Command c = getCommandFor("Stop");
		            if (c != null) c.actionPerformed(null);
		        }
		        
				gm.nextFrame();
			}
		});

		timer.schedule(20, true, this);

	}

	// UI provided for A1 only, remove it in A2
//	private void A1() {
//		Label myLabel = new Label("Enter a Command:");
//		TextField myTextField = new TextField();
//		this.add(myLabel).add(myTextField);
//		this.show();
//		myTextField.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				String sCommand = myTextField.getText().toString();
//				myTextField.clear();
//				if (sCommand.isEmpty())
//					return;
//				handleInput(sCommand.charAt(0));
//			}
//		});
//	}

	private void A2() {
		// Set the layout for the form
		setLayout(new BorderLayout());

		// Create the main views
		final ViewMap viewMap = new ViewMap();
		gm.addObserver(viewMap);
		ViewMessage viewMessage = new ViewMessage();
		gm.addObserver(viewMessage);
		ViewStatus viewStatus = new ViewStatus();
		gm.addObserver(viewStatus);

		// add main regions

		add(BorderLayout.NORTH, new Container());
		add(BorderLayout.CENTER, viewMap);

		Container south = createSouthPanel();
		south.add(viewMessage);
		add(BorderLayout.SOUTH, south);

		Container east = createEastPanel();
		east.add(viewStatus);
		add(BorderLayout.EAST, east);

		// create left container with a helper method createWestPanel()
		Container west = createWestPanel();
		add(BorderLayout.WEST, west);
		addWestButtons(west);

		setupToolbar();

		show();
	}

	private Command getCommandFor(String name) {
		switch (name) {
		case "Move":
			return new MovementCommand(gm, "Move");
		case "Stop":
			return new MovementCommand(gm, "Stop");
		case "Turn Left":
			return new MovementCommand(gm, "Turn Left");
		case "Turn Right":
			return new MovementCommand(gm, "Turn Right");
		case "Change Strategies":
			if (changeStrategyCommand == null) {
				changeStrategyCommand = new com.csus.csc133.commands.ChangeStrategyCommand(gm);
			}
			return changeStrategyCommand;
		case "Pause":
			return new PauseCommand(gm, "Pause");
		case "Play":
			return new PauseCommand(gm, "Play");
		case "Change Position":
			return new ChangePositionCommand(gm, "Change Position");
			
//		case "Lecture Hall":
//			return new com.csus.csc133.commands.CollisionCommand(gm, "Lecture Hall");
//		case "Water Dispenser":
//			return new com.csus.csc133.commands.CollisionCommand(gm, "Water Dispenser");
//		case "Restroom":
//			return new com.csus.csc133.commands.CollisionCommand(gm, "Restroom");
//		case "Student":
//			return new com.csus.csc133.commands.StudentButtonCommand(gm, "Student");
//		case "Next Frame":
//			return new com.csus.csc133.commands.SystemCommand(gm, "Next Frame");
		default:
			return null;
		}
	}

	private Container createWestPanel() {
		Container west = new Container(new BoxLayout(BoxLayout.Y_AXIS)) {
			@Override
			public Dimension calcPreferredSize() {
				Dimension d = super.calcPreferredSize();
				// Fix the width to 300 pixels
				return new Dimension(300, d.getHeight());
			}
		};
		return west;
	}

	private Container createEastPanel() {
		Container east = new Container(new BoxLayout(BoxLayout.Y_AXIS)) {
			@Override
			public Dimension calcPreferredSize() {
				Dimension d = super.calcPreferredSize();
				// Fix the width to 300 pixels
				return new Dimension(400, d.getHeight());
			}
		};
		return east;
	}

	private Container createSouthPanel() {
		Container south = new Container(new BoxLayout(BoxLayout.Y_AXIS)) {
			@Override
			public Dimension calcPreferredSize() {
				Dimension d = super.calcPreferredSize();
				return new Dimension(d.getWidth(), d.getHeight());
			}
		};
		return south;
	}

	private void addWestButtons(Container container) {
//		String[] buttonNames = { "Move", "Stop", "Turn Left", "Turn Right", "Change Strategies", "Lecture Hall",
//				"Water Dispenser", "Restroom", "Student", "Next Frame" };

		String[] buttonNames = { "Move", "Stop", "Turn Left", "Turn Right", "Change Strategies", "Pause", "Change Position" };

		ArrayList<Button> buttons = new ArrayList<>();

		// Create buttons and add them to the list
		for (String name : buttonNames) {
			CustomButton button = new CustomButton(name);
			Command command = getCommandFor(name);
			if (command != null) {
				button.setCommand(command);
			}
			buttons.add(button);
		}

		// Add all buttons from the list to the given container
		for (Button button : buttons) {
			container.add(button);
		}
	}

	@Override
	public void keyPressed(int k) {
		super.keyPressed(k);

		char c = (char) k;

		if (c == 'a') {
			leftPressed = true;
		} else if (c == 'd') {
			rightPressed = true;
		} else if (c == 'w') {
			upPressed = true;
		} else if (c == 's') {
			downPressed = true;
		}

	}

	@Override
	public void keyReleased(int k) {
		super.keyReleased(k);

		char c = (char) k;

		if (c == 'a') {
			leftPressed = false;
		} else if (c == 'd') {
			rightPressed = false;
		} else if (c == 'w') {
			upPressed = false;
		} else if (c == 's') {
			downPressed = false;
		}

	}

	

	private void setupToolbar() {

		Toolbar tb = getToolbar();

		if (changeStrategyCommand == null) {
			changeStrategyCommand = new ChangeStrategyCommand(gm);
		}
		SystemCommand aboutCommand = new SystemCommand(gm, "About");
		SystemCommand exitCommand = new SystemCommand(gm, "Exit");

		tb.addCommandToSideMenu(changeStrategyCommand);
		tb.addCommandToSideMenu(aboutCommand);
		tb.addCommandToSideMenu(exitCommand);

		tb.addCommandToRightBar("About", null, aboutCommand);
	}

}