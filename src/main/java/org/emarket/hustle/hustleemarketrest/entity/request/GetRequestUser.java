package org.emarket.hustle.hustleemarketrest.entity.request;

public class GetRequestUser
{

	private String searchField;

	private String searchPattern;

	private String field;

	private int page;

	private int size;

	public GetRequestUser()
	{
		searchPattern = "";
		field = "lastName";
		page = 0;
		size = 50;
	}

	public String getSearchField()
	{
		return searchField;
	}

	public void setSearchField(String searchField)
	{
		this.searchField = searchField;
	}

	public String getSearchPattern()
	{
		return searchPattern;
	}

	public void setSearchPattern(String searchPattern)
	{
		this.searchPattern = searchPattern;
	}

	public String getName()
	{
		return searchPattern;
	}

	public void setName(String name)
	{
		searchPattern = name;
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
