import java.util.ArrayList;
import java.util.Arrays;

public class SmartHome {
	
	/*
	 * Name, Surname: Can Gok
	 * 
	 * Student Name: 150118014
	 */
	
	/*
	 * 
	 * Smart Home has an array list of smart objects that has a type of smart object
	 * 
	 * new smartobjects can add through methods to that smart object list
	 */
	
	private ArrayList<SmartObject> smartObjectList = new ArrayList<SmartObject>();
	
	public SmartHome() {
		
	}
	
	public boolean addSmartObject(SmartObject smartObject) {
		System.out.println("---------------------------------");
		System.out.println("---------------------------------");
		System.out.println("Adding New SmartObject");
		System.out.println("---------------------------------");
		smartObject.connect("10.0.0." + (100 + smartObjectList.size()));
		smartObjectList.add(smartObject);
		smartObject.testObject();
		return true;
	}
	
	public boolean removeSmartObject(SmartObject smartObject) {
		smartObjectList.remove(smartObject);
		return true;
	}
	
	// control location controls onCome and onLeave methods according to the LocationControl interface
	
	public void controlLocation(boolean onCome) {
		if(onCome) {
			System.out.println("---------------------------------");
			System.out.println("---------------------------------");
			System.out.println("LocationControl: OnCome ");
			System.out.println("---------------------------------");
			smartObjectList.forEach( (smartObject) -> {
				if(smartObject instanceof LocationControl) {
					((LocationControl) smartObject).onCome();
				}
			});
		}
		else {
			System.out.println("---------------------------------");
			System.out.println("---------------------------------");
			System.out.println("LocationControl: OnLeave ");
			System.out.println("---------------------------------");
			smartObjectList.forEach( (smartObject) -> {
				if(smartObject instanceof LocationControl) {
					((LocationControl) smartObject).onLeave();
				}
			});
		}
	}
	
	//controlmotion controls the controlMotion method according to the MotionControl interface
	
	public void controlMotion(boolean hasMotion, boolean isDay) {
		System.out.println("---------------------------------");
		System.out.println("---------------------------------");
		System.out.println("MotionControl: hasMotion, isDay ");
		System.out.println("---------------------------------");
		smartObjectList.forEach( (smartObject) -> {
			if(smartObject instanceof MotionControl) {
				((MotionControl) smartObject).controlMotion(hasMotion, isDay);
			}
		});
	}
	
	//controlProgrammable runs the timers actions with using smartObject.runProgram();
	
	public void controlProgrammable() {
		System.out.println("---------------------------------");
		System.out.println("---------------------------------");
		System.out.println("Programmable: runProgram ");
		System.out.println("---------------------------------");
		smartObjectList.forEach( (smartObject) -> {
			if(smartObject instanceof Programmable) {
				((Programmable) smartObject).runProgram();
			}
		});
	}
	
	//control timer assigns actions to the object which has programmable interface
	
	public void controlTimer(int seconds) {
		if(seconds > 0) {
			return;
		}
		if(seconds == 0) {
			smartObjectList.forEach( (smartObject) -> {
				if(smartObject instanceof Programmable) {
					((Programmable) smartObject).cancelTimer();
				}
			});
			return;
		}
		System.out.println("---------------------------------");
		System.out.println("---------------------------------");
		System.out.println("Programmable: Timer = " + seconds + " seconds");
		System.out.println("---------------------------------");
		smartObjectList.forEach( (smartObject) -> {
			if(smartObject instanceof Programmable) {
				((Programmable) smartObject).setTimer(seconds);
			}
		});
	}
	
	//control timer randomly assigns actions to the object which has programmable interface in random seconds
	
	public void controlTimerRandomly() {
		System.out.println("---------------------------------");
		System.out.println("---------------------------------");
		System.out.println("Programmable: Timer = 0, 5 or 10 seconds randomly");
		System.out.println("---------------------------------");
		smartObjectList.forEach( (smartObject) -> {
			if(smartObject instanceof Programmable) {
				int rand = 1 + (int)(Math.random() * 3);
				if(rand == 0) {
					((Programmable) smartObject).cancelTimer();
				}
				else {
					((Programmable) smartObject).setTimer(5 * rand);
				}
			}
		});
	}
	
	//that method sorts the smart camera objects according to their battery life with comparable interface
	
	public void sortCameras() {
		System.out.println("---------------------------------");
		System.out.println("---------------------------------");
		System.out.println("Sort Smart Cameras");
		System.out.println("---------------------------------");
		SmartCamera[] smartCameras = new SmartCamera[smartObjectList.size()];
		int j = 0;
		for(int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof SmartCamera) {
				smartCameras[j++] = (SmartCamera)(smartObjectList.get(i));
			}
		}
		SmartCamera[] newSmartCameras = new SmartCamera[j];
		for(int i = 0; i < j; i++) {
			newSmartCameras[i] = smartCameras[i];
		}
		Arrays.sort(newSmartCameras);
		for(int i = 0; i < newSmartCameras.length; i++) {
			System.out.println(newSmartCameras[i]);
		}
	}
	
	//getters and setters

	public ArrayList<SmartObject> getSmartObjectList() {
		return smartObjectList;
	}

	public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
		this.smartObjectList = smartObjectList;
	}
	
}
