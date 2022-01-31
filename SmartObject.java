
public abstract class SmartObject {
	
	/*
	 * Name, Surname: Can Gok
	 * 
	 * Student Number: 150118014
	 * 
	 * Explanation of the Abstract Class SmartObject
	 * 
	 * SmartObject is the abstract class of smart objects in smart home system.
	 * SmartObject has the predefined variables and methods for smart objects.
	 * alias, macId and IP are the same variables used in every smart object.
	 * 
	 */
	
	private String alias;
	
	private String macId;
	
	private String IP;
	
	private boolean connectionStatus;
	
	public SmartObject(String alias, String macId) {
		
		this.setAlias(alias);
		
		this.setMacId(macId);
		
	}
	
	//Connect smart object to the system
	
	//Update its ip, connection status and display connection estabilished.
	
	public boolean connect(String IP) {
		this.setIP(IP);
		this.setConnectionStatus(true);
		System.out.println(this.getAlias() + " connection estabilished.");
		return true;
	}
	
	//Disconnect smart object from system, set its ip to null and connection status to false.
	
	public boolean disconnect(String IP) {
		this.setIP(null);
		this.setConnectionStatus(false);
		return true;
	}
	
	//When object called, toString method will display the qualifications of the smart object
	
	public void SmartObjectToString() {
		System.out.println("This is " + this.getClass().getSimpleName() + " device " + this.getAlias());
		System.out.println("\tMacId: " + this.getMacId());
		System.out.println("\tIP: " + this.getIP());
	}
	
	//Control connection if there is or not.
	
	public boolean controlConnection() {
		if(this.isConnectionStatus()) {
			return true;
		}
		else {
			System.out.println("The device is not connected. " + this.getClass().getSimpleName() + " -> " + this.getAlias());
			return false;
		}
	}
	
	//Abstract classes for sub classes of SmartObject
	
	public abstract boolean testObject();
	
	public abstract boolean shutDownObject();
	
	//Getter and Setter methods.

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public boolean isConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
	
}
