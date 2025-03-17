package com.csus.csc133;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.csus.csc133.facilities.*;
import com.csus.csc133.student.Student;
import com.csus.csc133.student.StudentPlayer;

public class SacRun extends Form {

	private GameModel gm;
	private static final Random rand = new Random();

	public SacRun() {
		gm = new GameModel();

		A1();
		

	}

	 //UI provided for A1 only, remove it in A2
	private void A1() {
		Label myLabel = new Label("Enter a Command:");
		TextField myTextField = new TextField();
		this.add(myLabel).add(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if (sCommand.isEmpty())
					return;
				handleInput(sCommand.charAt(0));
			}
		});
	}
	
	private void A2() {}
	

	private void handleInput(char key) {

		switch (key) {
		case 'w':
			gm.getPlayer().startMoving();
			break;

		case 's':
			gm.getPlayer().stopMoving();
			break;

		case 'a':
			gm.getPlayer().turnLeft();
			break;

		case 'd':
			gm.getPlayer().turnRight();
			break;

		case '1':
			gm.studentcollidelecturehall();
			break;

		case '2':
			gm.studentcolliderestroom();
			break;

		case '3':
			gm.studentcollidewater();
			break;

		case '4':

			gm.studentcollidestudent();
			break;

		case 'f':
			gm.nextFrame();
			break;

		case 'm':
			gm.printOutput();
			break;

		case 'n':
			System.out.println("My name: Alexandra Allwein");
			break;

		default:
			System.out.println("Invalid Input. Try again.");
			break;
		}

	}

}