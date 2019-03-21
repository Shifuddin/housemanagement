package housingservice.response;

/**
 * Custom response class
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
public  class CustomResponse {
	private String resourceName;
	private String identifier;
	private String message;
	
	public CustomResponse(String resourceName, String identifier, String message) {
		this.setResourceName(resourceName);
		this.setIdentifier(identifier);
		this.setMessage(message);
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.resourceName + "," + this.identifier +"," +this.message;
	}
}
