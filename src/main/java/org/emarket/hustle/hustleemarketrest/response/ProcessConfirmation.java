package org.emarket.hustle.hustleemarketrest.response;

/**
 * @author houou
 *         this class is used to return confirmation messages
 *         for example: confirmation that the user was success-
 *         fully deleted
 */
public class ProcessConfirmation
{

	private String status;

	private String userClass;

	private String message;

	public ProcessConfirmation(String status, String userClass, String message)
	{
		this.status = status;
		this.userClass = userClass;
		this.message = message;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getUserClass()
	{
		return userClass;
	}

	public void setUserClass(String userClass)
	{
		this.userClass = userClass;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "ProcessConfirmation [status=" + status + ", userClass=" + userClass + ", message=" + message + "]";
	}

}
