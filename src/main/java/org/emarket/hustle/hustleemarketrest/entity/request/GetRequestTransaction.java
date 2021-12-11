package org.emarket.hustle.hustleemarketrest.entity.request;

public class GetRequestTransaction
{

	private String usernamePattern;

	private String approvedTimePattern;

	private String field;

	private int page;

	private int size;

	public GetRequestTransaction()
	{
		usernamePattern = "";
		approvedTimePattern = "";
		field = "id";
		page = 0;
		size = 50;
	}

	public String getUsernamePattern()
	{
		return usernamePattern;
	}

	public void setUsernamePattern(String usernamePattern)
	{
		this.usernamePattern = usernamePattern;
	}

	public String getApprovedTimePattern()
	{
		return approvedTimePattern;
	}

	public void setApprovedTimePattern(String approvedTimePattern)
	{
		this.approvedTimePattern = approvedTimePattern;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

}
