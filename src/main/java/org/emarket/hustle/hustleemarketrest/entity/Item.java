package org.emarket.hustle.hustleemarketrest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.emarket.hustle.hustleemarketrest.response.NotPermittedException;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.core.lang.NonNull;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "item")
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@NonNull
	@Column(name = "category")
	private int category;

	@NonNull
	@Column(name = "name")
	private String name;

	@Column(name = "in_stock")
	private double inStock;

	@Column(name = "stock_sold")
	private double stockSold;

	@NonNull
	@Column(name = "price")
	private double price;

	@NonNull
	@Column(name = "measurement")
	private String measurement;

	@NonNull
	@Column(name = "increment")
	private double increment;

	@Column(name = "overall_review")
	private double overallReview;

	@Column(name = "description")
	private String description;

	@Column(name = "ingredient")
	private String ingredient;

	@Column(name = "direction")
	private String direction;

	@Column(name = "delisted")
	private boolean delisted;

	@JsonBackReference
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "store_id", updatable = false)
	private Store store;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "item_id")
	private List<ItemImage> itemImage;

	@Column(name = "creation_Date", updatable = false)
	private long creationDate;

	@Column(name = "modified_date")
	private long modifiedDate;

	public Item()
	{
		modifiedDate = System.currentTimeMillis();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getCategory()
	{
		return category;
	}

	public void setCategory(int category)
	{
		this.category = category;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getInStock()
	{
		return inStock;
	}

	public void setInStock(double inStock)
	{
		if(checkLessZero(inStock))
		{
			throw new NotPermittedException("INSTOCK LESS THAN 0");
		}
		this.inStock = inStock;
	}

	public double getStockSold()
	{
		return stockSold;
	}

	public void setStockSold(double stockSold)
	{
		if(checkLessZero(stockSold))
		{
			throw new NotPermittedException("STOCKSOLD LESS THAN 0");
		}

		this.stockSold = stockSold;
	}

	public double getPrice()
	{

		return price;
	}

	public void setPrice(double price)
	{
		if(checkLessZero(price))
		{
			return;
		}
		this.price = price;
	}

	public String getMeasurement()
	{
		return measurement;
	}

	public void setMeasurement(String measurement)
	{
		this.measurement = measurement;
	}

	public double getIncrement()
	{
		return increment;
	}

	public void setIncrement(double increment)
	{
		if(checkLessZero(increment))
		{
			throw new NotPermittedException("INCREMENT LESS THAN 0");
		}
		this.increment = increment;
	}

	public double getOverallReview()
	{
		return overallReview;
	}

	public void setOverallReview(double overallReview)
	{
		this.overallReview = overallReview;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getIngredient()
	{
		return ingredient;
	}

	public void setIngredient(String ingredient)
	{
		this.ingredient = ingredient;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	public boolean isDelisted()
	{
		return delisted;
	}

	public void setDelisted(boolean delisted)
	{
		this.delisted = delisted;
	}

	public Store getStore()
	{
		return store;
	}

	public void setStore(Store store)
	{
		this.store = store;
	}

	public List<ItemImage> getItemImage()
	{
		return itemImage;
	}

	public void setItemImage(List<ItemImage> itemImage)
	{
		this.itemImage = itemImage;
	}

	public long getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(long creationDate)
	{
		this.creationDate = creationDate;
	}

	public long getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(long modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString()
	{
		return "Item [id=" + id + ", category=" + category + ", name=" + name + ", inStock=" + inStock + ", stockSold="
				+ stockSold + ", price=" + price + ", measurement=" + measurement + ", overallReview=" + overallReview
				+ ", description=" + description + ", ingredient=" + ingredient + ", direction=" + direction
				+ ", delisted=" + delisted + ", store=" + store + ", itemImage=" + itemImage + ", creationDate="
				+ creationDate + ", modifiedDate=" + modifiedDate + "]";
	}

	/*
	 * #######################################
	 * ######### CUSTOMIZED METHODS ##########
	 * #######################################
	 */
	@JsonIgnore
	public void updateInStock(double num)
	{
		if(checkLessZero(inStock + num))
		{
			throw new NotPermittedException("UPDATING INSTOCK WITH LESS THAN 0");
		}
		inStock += num;

	}

	@JsonIgnore
	public boolean checkLessZero(double num)
	{
		if(num < 0)
		{
			return true;
		}

		return false;
	}

}
