package org.emarket.hustle.hustleemarketrest.entity.request;

import java.util.List;

public class GetRequestItem
{

	private String name;

	private List<Integer> category;

	private String field;

	private int page;

	private int size;

	public GetRequestItem()
	{
		name = "";
		field = "stockSold";
		page = 0;
		size = 50;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Integer> getCategory()
	{
		return category;
	}

	public void setCategory(List<Integer> category)
	{
		this.category = category;
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
