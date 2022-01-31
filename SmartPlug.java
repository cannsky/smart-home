import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SmartPlug extends SmartObject implements Programmable {
	
	private boolean status;
	
	private Calendar programTime;
	
	private boolean programAction;
	
	public SmartPlug(String alias, String macId) {
		
		super(alias, macId);
		
	}
	
	// if connection estabilished, that method will turn on
	
	// And if plug is off, current time will be displayed otherwise user will get a message that plug is already turned on.
	
	public void turnOn() {
		if(this.isConnectionStatus()) {
			if(this.isStatus()) {
				System.out.println("Smart Plug" + " - " + this.getAlias() + " already turned on");
			}
			else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
				this.setStatus(true);
				System.out.println("Smart Plug" + " - " + this.getAlias() + " is turned on now (Current Time : " + simpleDateFormat.format(calendar.getTime()) + ")");
			}
		}
	}
	
	// if connection estabilished, that method will turn the plug off
	
	// And if plug is off, current time will be displayed otherwise user will get a message that plug is already turned off.
	
	public void turnOff() {
		if(this.isConnectionStatus()) {
			if(!this.isStatus()) {
				System.out.println("Smart Plug" + " - " + this.getAlias() + " already turned off");
			}
			else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
				this.setStatus(false);
				System.out.println("Smart Plug" + " - " + this.getAlias() + " is turned off now (Current Time : " + simpleDateFormat.format(calendar.getTime()) + ")");
			}
		}
	}
	
	// test object method will test objects qualifications and variables.
	
	// it will be called when a new object created.
	
	public boolean testObject() {
		if(this.isConnectionStatus()) {
			System.out.println("Test is starting for SmartPlug");
			this.SmartObjectToString();
			this.turnOn();
			this.turnOff();
			System.out.println("Test Completed for " + this.getClass().getSimpleName());
			return true;
		}
		else {
			return false;
		}
	}
	
	// shutdown object will close the object if the plug is open and connection estabilished.
	
	public boolean shutDownObject() {
		if(this.isConnectionStatus()) {
			if(this.isStatus()) {
				this.SmartObjectToString();
				this.turnOff();
				return true;
			}
			return true;
		}
		else {
			return false;
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
			if(this.isStatus()) {
				this.setProgramAction(false);
				System.out.println("Smart plug - " + this.getAlias() + " will be turned off " + seconds + " seconds later! (Current time: " + simpleDateFormat.format(calendarNow.getTime()) + ")");
			}
			else {
				this.setProgramAction(true);
				System.out.println("Smart plug - " + this.getAlias() + " will be turned on " + seconds + " seconds later! (Current time: " + simpleDateFormat.format(calendarNow.getTime()) + ")");
			}
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
					this.setStatus(true);
					System.out.println("RunProgram -> Smart Plug -" + this.getAlias());
					System.out.println("Smart Plug" + this.getAlias() + " is turned on now (Current time: " + simpleDateFormat.format(Calendar.getInstance().getTime()) + ")");
					this.setProgramTime(null);
				}
			}
			else {
				if(simpleDateFormat.format(this.getProgramTime().getTime()).equals(simpleDateFormat.format(Calendar.getInstance().getTime()))) {
					this.setStatus(false);
					System.out.println("RunProgram -> Smart Plug -" + this.getAlias());
					System.out.println("Smart Light" + this.getAlias() + " is turned off now (Current time: " + simpleDateFormat.format(Calendar.getInstance().getTime()) + ")");
					this.setProgramTime(null);
				}
			}
		}
	}
	
	// cancels the timer with setting program time null
	
	public void cancelTimer() {
		if(this.isConnectionStatus()) {
			this.setProgramTime(null);
		}
	}
	
	//getters and setters

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
