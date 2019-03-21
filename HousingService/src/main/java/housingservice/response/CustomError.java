package housingservice.response;

import java.util.Date;
/**
 * Custom error class
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */

public class CustomError {
	private Date timestamp;
	private String errorMessage;
	private String details;
	
	public CustomError(Date timestamp, String errorMessage, String details) {
		this.setTimestamp(timestamp);
		this.setErrorMessage(errorMessage);
		this.setDetails(details);
	}
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Override
	public String toString() {
		return this.timestamp.toString() + "," + this.errorMessage + "," + this.details;
	}
	

}
