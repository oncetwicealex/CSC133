package com.csus.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.csus.csc133.GameModel;
import com.csus.csc133.GameObject;
import com.csus.csc133.GameObjectCollection;
import com.csus.csc133.studentwstrategy.StudentWithStrategy;

public class ChangeStrategyCommand extends Command {
	private GameModel gm;

	public ChangeStrategyCommand(GameModel gm) {
		super("Change Strategies");
		this.gm = gm;
	}

	public void actionPerformed(ActionEvent e) {

		GameObjectCollection.CustomIterator iteratorStrategy = gm.getObjectsIterator();
		while (iteratorStrategy.hasNext()) {
			GameObject o = iteratorStrategy.getNext();
			if (o instanceof StudentWithStrategy) {
				StudentWithStrategy s1 = (StudentWithStrategy) o;
				s1.changeStrategy();
			}
		}
		gm.updateMessage("Strategies changed");

	}

}
