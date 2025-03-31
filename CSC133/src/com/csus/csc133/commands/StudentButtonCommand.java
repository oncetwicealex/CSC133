package com.csus.csc133.commands;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.csus.csc133.GameModel;
import com.csus.csc133.GameObject;
import com.csus.csc133.GameObjectCollection;
import com.csus.csc133.student.Student;
import com.csus.csc133.student.StudentAngry;
import com.csus.csc133.student.StudentBiking;
import com.csus.csc133.student.StudentCar;
import com.csus.csc133.student.StudentConfused;
import com.csus.csc133.student.StudentFriendly;
import com.csus.csc133.student.StudentHappy;
import com.csus.csc133.student.StudentNonstop;
import com.csus.csc133.student.StudentRunning;
import com.csus.csc133.student.StudentSleeping;
import com.csus.csc133.studentwstrategy.StudentWithStrategy;

public class StudentButtonCommand extends Command {
	private GameModel gm;
	private String action;

	public StudentButtonCommand(GameModel gm, String action) {
		super("Student");
		this.action = action;
		this.gm = gm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Dialog dialog = new Dialog("Select Student Type: ");
		dialog.setLayout(new BorderLayout());
		;

		TextField inputField = new TextField("", "Enter Student Type (0-9");

		dialog.add(BorderLayout.CENTER, inputField);

		Button confirmButton = new Button("Confirm");

		confirmButton.addActionListener(ev -> {
			String inputText = inputField.getText();
			int studentType = -1;

			try {
				studentType = Integer.parseInt(inputText);
			} catch (NumberFormatException nfe) {
				Dialog.show("Error", "Invalid input. PLease enter a number between 0 and 9)", "OK", null);
				return;
			}

			if (studentType < 0 || studentType > 9) {
				Dialog.show("Error", "Invalid input. PLease enter a number between 0 and 9)", "OK", null);
				return;
			}

			// Map the input number to a student class

			Class<? extends Student> studentClass = null;
			switch (studentType) {
			case 0:
				studentClass = StudentAngry.class;
				break;
			case 1:
				studentClass = StudentBiking.class;
				break;
			case 2:
				studentClass = StudentCar.class;
				break;
			case 3:
				studentClass = StudentConfused.class;
				break;
			case 4:
				studentClass = StudentFriendly.class;
				break;
			case 5:
				studentClass = StudentHappy.class;
				break;
			case 6:
				studentClass = StudentNonstop.class;
				break;
			case 7:
				studentClass = StudentSleeping.class;
				break;
			case 8:
				studentClass = StudentRunning.class;
				break;
			case 9:
				studentClass = StudentWithStrategy.class;
				break;

			}

			if (studentClass == null) {
				Dialog.show("Error", "Invalid Student Type", "OK", null);
			}

			GameObjectCollection.CustomIterator it = gm.getObjectsIterator();
			Student selectedStudent = null;
			while (it.hasNext()) {
				GameObject o = it.getNext();

				if (studentClass.isInstance(o)) {
					selectedStudent = (Student) o;
					break;
				}
			}

			if (selectedStudent == null) {
				Dialog.show("Not Found", "No student of the selected type exists.", "OK", null);
			} else {
				gm.getPlayer().handleCollide(selectedStudent);
				selectedStudent.handleCollide(gm.getPlayer());

				gm.updateMessage("Player collided with: " + selectedStudent.getTypeName());
			}
			dialog.dispose();

		});

		dialog.add(BorderLayout.SOUTH, confirmButton);
		dialog.showPacked(BorderLayout.CENTER, true);
	}
}
