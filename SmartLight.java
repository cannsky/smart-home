import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SmartLight extends SmartObject implements LocationControl, Programmable{
	
	/*
	 * Name, Surname: Can Gok
	 * 
	 * Student Number: 150118014
	 * 
	 * SmartLight object extended from SmartObject abstract class and implements LocationControl and Programmable interfaces.
	 * 
	 */
	
	private boolean hasLightTurned;
	
	private Calendar programTime;
	
	private boolean programAction;
	
	public SmartLight(String alias, String macId) {
		super(alias, macId);
		
	}
	
	// if connection estabilished, that method will turn the light on
	
	// And if light is off, current time will be displayed otherwise user will get a message that light is already turned on.
	
	public void turnOnLight() {
		if(this.isConnectionStatus()) {
			if(this.isHasLightTurned()) {
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " already turned on");
			}
			else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
				this.setHasLightTurned(true);
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " is turned on now (Current Time : " + simpleDateFormat.format(calendar.getTime()) + ")");
			}
		}
	}
	
	// if connection estabilished, that method will turn the light off
	
	// And if light is on, current time will be displayed otherwise user will get a message that light is already turned off.
	
	public void turnOffLight() {
		if(this.isConnectionStatus()) {
			if(!this.isHasLightTurned()) {
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " already turned off");
			}
			else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
				this.setHasLightTurned(false);
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " is turned off now (Current Time : " + simpleDateFormat.format(calendar.getTime()) + ")");
			}
		}
	}
	
	// test object method will test objects qualifications and variables.
	
	// it will be called when a new object created.
	
	public boolean testObject() {
		if(this.isConnectionStatus()) {
			System.out.println("Test is starting for SmartLight");
			this.SmartObjectToString();
			this.turnOnLight();
			this.turnOffLight();
			System.out.println("Test Completed for " + this.getClass().getSimpleName());
			return true;
		}
		else {
			return false;
		}
	}
	
	// shutdown object will close the object if the light is open and connection estabilished.
	
	public boolean shutDownObject() {
		if(this.isConnectionStatus()) {
			if(this.isHasLightTurned()) {
				this.SmartObjectToString();
				this.turnOffLight();
				return true;
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	// This is a key feature when the user leaves the home, lights will be turned off too.
	
	public void onLeave() {
		if(this.isConnectionStatus()) {
			System.out.println("On Leave -> Smart Light - " + this.getAlias());
			this.turnOffLight();
		}
	}
	
	// This is an another key feature that when user enters lights will be opened.
	
	public void onCome() {
		if(this.isConnectionStatus()) {
			System.out.println("On Come -> Smart Light - " + this.getAlias());
			this.turnOnLight();
		}
	}
	
	// Set timer will do the opening or closing the smart devices opposite to their current status in given time in seconds.
	
	public void setTimer(int seconds) {
		if(this.isConnectionStatus()) {
			Calendar calendar = Calendar.getInstance();
			this.setProgramTime(calendar);
			calendar.add(Calendar.SECOND, seconds);
			this.setProgramTime(calendar);
			Calendar calendarNow = Calendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
			if(this.isHasLightTurned()) {
				this.setProgramAction(false);
				System.out.println("Smart light - " + this.getAlias() + " will be turned off " + seconds + " seconds later! (Current time: " + simpleDateFormat.format(calendarNow.getTime()) + ")");
			}
			else {
				this.setProgramAction(true);
				System.out.println("Smart light - " + this.getAlias() + " will be turned on " + seconds + " seconds later! (Current time: " + simpleDateFormat.format(calendarNow.getTime()) + ")");
			}
		}
	}
	
	// cancels the timer with setting program time null
	
	public void cancelTimer() {
		if(this.isConnectionStatus()) {
			this.setProgramTime(null);
		}
	}
	
	// run program will be called if the program time and action time are equal for the object
	
	// this is the method that timers action will be done.
	
	// dates will be compared according to their String values, formatted in the same way
	
	// if time zone will be changed, there can be differences in dates.
	
	public void runProgram() {
		if(this.isConnectionStatus()) {
			if(this.getProgramTime() == null) {
				return;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
			if(this.isProgramAction()) {
				if(simpleDateFormat.format(this.getProgramTime().getTime()).equals(simpleDateFormat.format(Calendar.getInstance().getTime()))) {
					this.setHasLightTurned(true);
					System.out.println("RunProgram -> Smart Light -" + this.getAlias());
					System.out.println("Smart Light" + this.getAlias() + " is turned on now (Current time: " + simpleDateFormat.format(Calendar.getInstance().getTime()) + ")");
					this.setProgramTime(null);
				}
			}
			else {
				if(simpleDateFormat.format(this.getProgramTime().getTime()).equals(simpleDateFormat.format(Calendar.getInstance().getTime()))) {
					this.setHasLightTurned(false);
					System.out.println("RunProgram -> Smart Light -" + this.getAlias());
					System.out.println("Smart Light" + this.getAlias() + " is turned off now (Current time: " + simpleDateFormat.format(Calendar.getInstance().getTime()) + ")");
					this.setProgramTime(null);
				}
			}
		}
	}
	
	// getters and setters for smartlight object

	public boolean isHasLightTurned() {
		return hasLightTurned;
	}

	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
	}

	public Calendar getProgramTime() {
		return programTime;
	}

	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}

	public boolean isProgramAction() {
		return programAction;
	}

	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}
	
}
