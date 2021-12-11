package org.emarket.hustle.hustleemarketrest.response;

public class FailedException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public FailedException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public FailedException(String message)
	{
		super(message);
	}

	public FailedException(Throwable cause)
	{
		super(cause);
	}

}
