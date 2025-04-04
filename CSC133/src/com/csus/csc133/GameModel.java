package com.csus.csc133;

import java.util.*;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.csus.csc133.GameObjectCollection.CustomIterator;
import com.csus.csc133.facilities.*;
import com.csus.csc133.lecture.Lecture;
import com.csus.csc133.student.*;
import com.csus.csc133.studentwstrategy.StudentWithStrategy;

public class GameModel extends Observable {
	private GameObjectCollection objects;
	private StudentPlayer player1;
	private Lecture currentlecture;
	private LectureHall currentLectureHall;
	private double time;
	private boolean paused = false;
	private boolean positionMode = false;
	private static final Random rand = new Random();
	private static final double FRAME_ELAPSED_TIME = 0.02;

	private String[] halls = { "Riverside Hall", "Brighton Hall", "Eureka Hall", "Tahoe Hall", "Sequoia Hall",
			"Yosemite Hall", "Sierra Hall", "Klamath Hall", "Humboldt Hall", "Alpine Hall", "Shasta Hall",
			"Mendocino Hall", "Capistrano Hall", "Amador Hall", "Calaveras Hall", "Kadema Hall", "Mariposa Hall",
			"Del Norte Hall", "Douglass Hall", "Tschannen Science Complex" };

	private static int GAMEWORLD_WIDTH = 1024;
	private static int GAMEWORLD_HEIGHT = 768;

	public GameModel() {
		objects = new GameObjectCollection();
		time = 0.0;
		init();

		setChanged();
		notifyObservers("Initial Update");

	}

	public void init() {

		String[] threeHalls = new String[3];
		boolean[] used = new boolean[halls.length];

		int count = 0;
		while (count < 3) {
			int index = rand.nextInt(halls.length);

			if (!used[index]) {
				used[index] = true;
				threeHalls[count] = halls[index];
				count++;

			}
		}

		System.out.println("Initializing game...");
		System.out.println("Time: " + time);

		/* initialize facilities */

		final int MAX_ATTEMPTS = 20;

		ArrayList<GameObject> placedFacilities = new ArrayList<>();

		for (int i = 0; i < threeHalls.length; i++) {
			LectureHall lecturehall = new LectureHall(threeHalls[i], this);
			if (placeFacilityOC(lecturehall, placedFacilities, rand, MAX_ATTEMPTS)) {
				placedFacilities.add(lecturehall);
				objects.add(lecturehall);
			} else {
				System.out.println("Could not place lecture hall without overlap.");
			}
		}
		for (int i = 0; i < 2 + rand.nextInt(3); i++) {
			Restroom restroom = new Restroom();
			if (placeFacilityOC(restroom, placedFacilities, rand, MAX_ATTEMPTS)) {
				placedFacilities.add(restroom);
				objects.add(restroom);
			} else {
				System.out.println("Could not place restroom without overlap.");
			}
		}

		for (int i = 0; i < 2 + rand.nextInt(3); i++) {
			WaterDispenser wd = new WaterDispenser();
			if (placeFacilityOC(wd, placedFacilities, rand, MAX_ATTEMPTS)) {
				placedFacilities.add(wd);
				objects.add(wd);
			} else {
				System.out.println("Could not place waterdispenser without overlap.");
			}
		}

		/* initialize students */

		player1 = StudentPlayer.getStudentPlayer();
		objects.add(player1);

		for (int i = 0; i < 3; i++) {
			objects.add(new StudentWithStrategy());
		}

		for (int i = 0; i < 1 + rand.nextInt(2); i++) {
			objects.add(new StudentAngry());
		}

		for (int i = 0; i < 1 + rand.nextInt(2); i++) {
			objects.add(new StudentBiking());
		}

		for (int i = 0; i < 1 + rand.nextInt(2); i++) {
			objects.add(new StudentCar());
		}

		for (int i = 0; i < 1 + rand.nextInt(2); i++) {
			objects.add(new StudentConfused());
		}

		for (int i = 0; i < 1 + rand.nextInt(2); i++) {
			objects.add(new StudentFriendly());
		}

		for (int i = 0; i < 1 + rand.nextInt(2); i++) {
			objects.add(new StudentHappy());
		}

		for (int i = 0; i < 1 + rand.nextInt(2); i++) {
			objects.add(new StudentNonstop());
		}

		for (int i = 0; i < 1 + rand.nextInt(2); i++) {
			objects.add(new StudentRunning());
		}

		for (int i = 0; i < 1 + rand.nextInt(2); i++) {
			objects.add(new StudentSleeping());
		}

		GameObjectCollection.CustomIterator it = objects.getCustomIterator();
		while (it.hasNext()) {
			GameObject o = it.getNext();
			System.out.println(o.toString());
		}

		/* initialize random */

		Random random = new Random();

		GameObjectCollection.CustomIterator it2 = objects.getCustomIterator();
		while (it2.hasNext()) {
			GameObject o = it2.getNext();

			if (!(o instanceof Facility)) {
				int halfSize = o.getSize() / 2;

				int x = halfSize + random.nextInt(getGAMEWORLD_WIDTH() - o.getSize());
				int y = halfSize + random.nextInt(getGAMEWORLD_HEIGHT() - o.getSize());
				o.setX(x);
				o.setY(y);
			}
		}

	}

	public void updateMessage(String message) {
		setChanged();
		notifyObservers(message);
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

		if (isPaused()) {
			setChanged();
			notifyObservers("Game is paused.");
			return;
		}
		time += getFrameElapsedTime();

		// start lecture
		if (currentlecture == null || !currentlecture.isOngoing() && rand.nextInt(10) == 0) {
			currentlecture = new Lecture();
			currentlecture.startLecture();

			// Collect all LectureHall instances into a list
			ArrayList<LectureHall> lectureHalls = new ArrayList<>();
			GameObjectCollection.CustomIterator it3 = objects.getCustomIterator();
			while (it3.hasNext()) {
				GameObject o = it3.getNext();

				if (o instanceof LectureHall) {
					lectureHalls.add((LectureHall) o);
				}
			}

			if (!lectureHalls.isEmpty()) {
				int randomIndex = rand.nextInt(lectureHalls.size());
				currentLectureHall = lectureHalls.get(randomIndex);
				currentLectureHall.setLecture(currentlecture);
				System.out.println("Lecture started in");
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
				currentLectureHall = null;
			}
		}

		GameObjectCollection.CustomIterator it4 = objects.getCustomIterator();
		while (it4.hasNext()) {
			GameObject o = it4.getNext();
			if (o instanceof Student) {
				((Student) o).move();
			}
		}

		ArrayList<Student> studentList = new ArrayList<>();
		CustomIterator itStudents = objects.getCustomIterator();
		while (itStudents.hasNext()) {
			GameObject o = itStudents.getNext();
			if (o instanceof Student) {
				studentList.add((Student) o);
			}
		}

		for (int i = 0; i < studentList.size(); i++) {
			Student s1 = studentList.get(i);
			for (int j = i + 1; j < studentList.size(); j++) {
				Student s2 = studentList.get(j);
				if (s1.collidesWith(s2)) {
					s1.handleCollide(s2);
				} else {
					s1.getCurrentCollisions().remove(s2);
					s2.getCurrentCollisions().remove(s1);
				}
			}

		}

		// Collision detection
		// Check collisions between the StudentPlayer and all facilities

		if (currentLectureHall != null && player1.collidesWith(currentLectureHall)) {
			currentLectureHall.handleCollide(player1);
		}
		GameObjectCollection.CustomIterator collisionit = objects.getCustomIterator();

		while (collisionit.hasNext()) {
			GameObject o = collisionit.getNext();

			if (!(o instanceof LectureHall) && o instanceof Facility) {
				if (player1.collidesWith(o)) {
					o.handleCollide(player1);
					System.out.println("collision detected between StudentPlayer and ");
				}
			}
		}

		checkGameOver();

		setChanged();
		notifyObservers(null);

	}

	public Lecture getCurrentlecture() {
		return currentlecture;
	}

	public void setCurrentlecture(Lecture currentlecture) {
		this.currentlecture = currentlecture;
	}

	public LectureHall getCurrentLectureHall() {
		return currentLectureHall;
	}

	public void setCurrentLectureHall(LectureHall currentLectureHall) {
		this.currentLectureHall = currentLectureHall;
	}

	public StudentPlayer getPlayer() {

		return player1;
	}

	public double getTime() {

		return time;
	}

	public String getLectureTimeRemaining() {

		GameObjectCollection.CustomIterator it9 = objects.getCustomIterator();
		while (it9.hasNext()) {
			GameObject o = it9.getNext();
			if (o instanceof LectureHall) {
				LectureHall lh = (LectureHall) o;
				if (!"null".equals(lh.getLectureStatus())) {
					return lh.getLectureStatus();

				}

			}
		}
		return "null";
	}

	public String getLectureName() {

		GameObjectCollection.CustomIterator it10 = objects.getCustomIterator();
		while (it10.hasNext()) {
			GameObject o = it10.getNext();
			if (o instanceof LectureHall) {
				return ((LectureHall) o).getLectureName();
			}
		}
		return "None";
	}

	public void checkGameOver() {
		if (player1.getAbsenceTime() >= 3 || player1.getHydration() <= 0 || player1.getWaterIntake() >= 500) {
			String message = "Game Over: ";

			if (player1.getAbsenceTime() >= 3) {
				message += "Too many absences.";
			}
			if (player1.getHydration() <= 0) {
				message += "No hydration";
			}
			if (player1.getWaterIntake() >= 500) {
				message += "Excessive water intake";
			}
			double roundedTime = Math.round(time * 100.0)/100.0;
			message += "\nTime: " + roundedTime;

			Dialog.show("Game Over", message, "Confirm", null);
			Display.getInstance().exitApplication();

		}

	}

	public void changeStrategies() {
		GameObjectCollection.CustomIterator it12 = objects.getCustomIterator();
		while (it12.hasNext()) {
			GameObject o = it12.getNext();
			if (o instanceof StudentWithStrategy) {
				((StudentWithStrategy) o).changeStrategy();
			}
		}
	}

	public void printOutput() {
		System.out.println("Current game time: " + getTime());

		GameObjectCollection.CustomIterator it11 = objects.getCustomIterator();
		while (it11.hasNext()) {
			GameObject o = it11.getNext();
			System.out.println(o.toString());
		}
	}

	public CustomIterator getObjectsIterator() {
		return objects.getCustomIterator();
	}

	public static double getFrameElapsedTime() {
		return FRAME_ELAPSED_TIME;
	}

	public boolean placeFacilityOC(GameObject facility, ArrayList<GameObject> placedFacilities, Random random,
			int maxAttempts) {
		int attempts = 0;
		int halfSize = facility.getSize() / 2;

		while (attempts < maxAttempts) {
			int x = halfSize + random.nextInt(getGAMEWORLD_WIDTH() - facility.getSize());
			int y = halfSize + random.nextInt(getGAMEWORLD_HEIGHT() - facility.getSize());

			facility.setX(x);
			facility.setY(y);

			boolean overlap = false;

			for (GameObject placed : placedFacilities) {
				if (facility.overlaps(placed)) {
					overlap = true;
					break;
				}
			}
			if (!overlap) {
				return true;
			}
			attempts++;
		}
		return false;

	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean b) {
		paused = b;

	}

	public boolean isPositionMode() {
		return positionMode;
	}

	public void setPositionMode(boolean b) {
		positionMode = b;

	}

}