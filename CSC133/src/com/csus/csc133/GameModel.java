package com.csus.csc133;

import java.util.*;

import com.csus.csc133.facilities.*;
import com.csus.csc133.lecture.Lecture;
import com.csus.csc133.student.*;

public class GameModel {
	private Vector<GameObject> objects;
	private StudentPlayer player1;
	private Lecture currentlecture;
	private static int time;
	private static final Random rand = new Random();

	private static int GAMEWORLD_WIDTH = 1024;
	private static int GAMEWORLD_HEIGHT = 768;

	public GameModel() {
		objects = new Vector<>();
		time = 0;
		init();

	}

	public void init() {

		System.out.println("Initializing game...");
		System.out.println("Time: " + time);

		/* initialize facilities */
		LectureHall lecturehall = new LectureHall("Riverside Hall");
		Restroom restroom = new Restroom();
		WaterDispenser waterdisp = new WaterDispenser();

		objects.add(lecturehall);
		objects.add(restroom);
		objects.add(waterdisp);

		/* initialize students */

		player1 = new StudentPlayer();
		objects.add(player1);

		objects.add(new StudentAngry());
		objects.add(new StudentBiking());
		objects.add(new StudentCar());
		objects.add(new StudentConfused());
		objects.add(new StudentFriendly());
		objects.add(new StudentHappy());
		objects.add(new StudentNonstop());
		objects.add(new StudentRunning());
		objects.add(new StudentSleeping());

		for (GameObject o : objects) {
			System.out.println(o.toString());
		}

		/* initialize random */

		Random random = new Random();

		for (GameObject o : objects) {
			o.setX(random.nextInt(getGAMEWORLD_WIDTH()));
			o.setY(random.nextInt(GAMEWORLD_HEIGHT));
		}

	}

	public static int getGAMEWORLD_WIDTH() {
		return GAMEWORLD_WIDTH;
	}

	public static void setGAMEWORLD_WIDTH(int w) {
		GAMEWORLD_WIDTH = w;
	}

	public static int getGAMEWORLD_HEIGHT() {
		return GAMEWORLD_HEIGHT;
	}

	public static void setGAMEWORLD_HEIGHT(int h) {
		GAMEWORLD_HEIGHT = h;
	}

	/*
	 * implement Lecture behavior in GameModel
	 * 
	 */

	public void nextFrame() {
		time++;

		System.out.println("Advanced to next frame.");
		System.out.println("Current game time: " + time);

		// start lecture
		if (currentlecture == null || !currentlecture.isOngoing() && rand.nextInt(10) == 0) {
			currentlecture = new Lecture();
			currentlecture.startLecture();

			for (GameObject obj : objects) {
				if (obj instanceof LectureHall) {
					((LectureHall) obj).setLecture(currentlecture);
					break;
				}
			}

			player1.resetAttendance();

		}

		// decrease lecture time by 1
		if (currentlecture != null && currentlecture.isOngoing()) {
			currentlecture.decreaseTime();

			// check if lecture ended and student made it before time ran out
			if (!currentlecture.isOngoing()) {
				if (!player1.attendedLecture()) {
					player1.countAbsenceTime();
					System.out.println("Lecture ended. Student did NOT attend. Absence +1");
				} else {
					System.out.println("Lecture ended. Student attended.");
				}

				currentlecture = null;
			}
		}

		for (GameObject obj : objects) {
			if (obj instanceof Student) {
				((Student) obj).move();
			}
		}

		checkGameOver();

	}

	

	public void studentcollidelecturehall() {
		for (GameObject obj : objects) {
			if (obj instanceof LectureHall) {
				((LectureHall) obj).handleCollide(player1);
				System.out.println("Player collided with lecture hall.");
				break;
			}

		}
	}

	public void studentcolliderestroom() {
		for (GameObject obj : objects) {
			if (obj instanceof Restroom) {
				((Restroom) obj).handleCollide(player1);
				System.out.println("Player collided with restroom.");
				break;
			}
		}

	}

	public void studentcollidewater() {
		for (GameObject obj : objects) {
			if (obj instanceof WaterDispenser) {
				((WaterDispenser) obj).handleCollide(player1);
				System.out.println("Player collided with Water Dispenser.");
				break;
			}
		}

	}

	public void studentcollidestudent() {
		ArrayList<Student> NPC = new ArrayList<>();

		for (GameObject obj : objects) {
			if (obj instanceof Student && !(obj instanceof StudentPlayer)) {
				NPC.add((Student) obj);
			}

		}
		if (!NPC.isEmpty()) {

			Student randStudent = NPC.get(rand.nextInt(NPC.size()));

			player1.handleCollide(randStudent);
			randStudent.handleCollide(player1);

			String studentClassName = randStudent.getClass().toString();
			String className = studentClassName.substring(studentClassName.lastIndexOf('.') + 1);

			System.out.println("Player collided with: " + className);

		}

	}

	public StudentPlayer getPlayer() {

		return player1;
	}

	public Vector<GameObject> getObjects() {

		return objects;
	}

	public int getTime() {
		return time;
	}
	
	public void checkGameOver() {
		if (player1.getAbsenceTime() >= 3 || player1.getHydration() <= 0 || player1.getWaterIntake() >= 500) {
			System.out.println("Game over. Time: " + time);
			System.exit(0);
		}

	}
	
	public void printOutput() {
		System.out.println("Current game time: " + getTime());
		
		for (GameObject obj : objects) {
			System.out.println(obj.toString());
		}
	}


}