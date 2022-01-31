public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera>{
	
	/*
	 * 
	 * Smart Camera object is subclass of SmartObject, also has MotionControl and comparable interfaces.
	 * 
	 * Smart Cameras can be sorted via, Arrays.sort();
	 * 
	 * Also smart cameras will record due to day or not, (night vision property)
	 * 
	 * Smart cameras can start recording if there is motion
	 * 
	 */
	
	private boolean status;
	
	private int batteryLife;
	
	private boolean nightVision;
	
	public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
		
		super(alias, macId);
		
		this.setNightVision(nightVision);
		
		this.setBatteryLife(batteryLife);
		
	}
	
	// if connection estabilished, changes the status and records.
	
	// if it is already recording, user will get a message that smart camera is already recording.
	
	// if night vision property is not available at night, camera will not record.
	
	public void recordOn(boolean isDay) {
		if(this.isConnectionStatus()) {
			if(!isDay && !this.isNightVision()) {
				System.out.println("Sorry! Smart Camera - " + this.getAlias() + " does not have night vision feature.");
				return;
			}
			if(this.isStatus()) {
				System.out.println("Smart Camera" + " - " + this.getAlias() + " already turned on");
			}
			else {
				this.setStatus(true);
				System.out.println("Smart Camera" + " - " + this.getAlias() + " is turned on now");
			}
		}
	}
	
	// if connection estabilished, changes the status and records.
	
	// if it is already not recording, user will get a message that smart camera is already not recording.
	
	public void recordOff() {
		if(this.isConnectionStatus()) {
			if(!this.isStatus()) {
				System.out.println("Smart Camera" + " - " + this.getAlias() + " already turned off");
			}
			else {
				this.setStatus(false);
				System.out.println("Smart Camera" + " - " + this.getAlias() + " is turned off now");
			}
		}
	}
	
	// test object method will test objects qualifications and variables.
	
	// it will be called when a new object created.
	
	public boolean testObject() {
		if(this.isConnectionStatus()) {
			System.out.println("Test is starting for SmartCamera");
			this.SmartObjectToString();
			System.out.println("Test is starting for SmartCamera day time");
			this.recordOn(true);
			this.recordOff();
			System.out.println("Test is starting for SmartCamera night time");
			this.recordOn(false);
			this.recordOff();
			System.out.println("Test Completed for " + this.getClass().getSimpleName());
			return true;
		}
		else {
			return false;
		}
	}
	
	// If object is open, closes the object
	
	public boolean shutDownObject() {
		if(this.isConnectionStatus()) {
			if(this.isStatus()) {
				this.SmartObjectToString();
				this.recordOff();
				return true;
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	//If there is motion, camera will start recording.
	
	public boolean controlMotion(boolean hasMotion, boolean isDay) {
		if(hasMotion) {
			System.out.println("Motion detected!");
			if(isDay) {
				this.recordOn(true);
				return true;
			}
			else {
				if(this.isNightVision()) {
					this.recordOn(false);
					return true;
				}
				else {
					return false;
				}
			}
		}
		else {
			System.out.println("Motion not detected!");
			return false;
		}
	}
	
	//Comparing method for Arrays.sort, looks for the battery life of the smart cameras and returns 1,0 and -1 according to the values
	
	public int compareTo(SmartCamera smartCamera) {
		if(this.getBatteryLife() > smartCamera.getBatteryLife()) {
			return 1;
		}
		else if(this.getBatteryLife() == smartCamera.getBatteryLife()){
			return 0;
		}
		else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return "Smart Camera -> " + this.getAlias() + "'s battery life is " + this.getBatteryLife() + " status is " + (this.isStatus() ? "recording" : "not recording");
	}
	
	//getters and setters

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	public boolean isNightVision() {
		return nightVision;
	}

	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}
	
}
