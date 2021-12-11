package org.emarket.hustle.hustleemarketrest.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "history")
public class History
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@JsonBackReference
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "transaction_id", updatable = false)
	private Transaction transaction;

	@JoinColumn(name = "store_id", updatable = false)
	private int storeId;

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "item_id", updatable = false)
	private Item item;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "price", updatable = false)
	private double price;

	@Column(name = "quantity", updatable = false)
	private double quantity;

	@Column(name = "total")
	private double total;

	@Column(name = "status")
	private String status;

	@Column(name = "creation_date", updatable = false)
	private long creationDate;

	@Column(name = "modified_date")
	private long modifiedDate;

	public History()
	{
		modifiedDate = System.currentTimeMillis();
	}

	public History(int id, Transaction order, int storeId, Item item, String itemName, double price, double quantity)
	{
		this.id = id;
		transaction = order;
		this.storeId = storeId;
		this.item = item;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
		total = quantity * price;
		modifiedDate = System.currentTimeMillis();
		creationDate = modifiedDate;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Transaction getTransaction()
	{
		return transaction;
	}

	public void setTransaction(Transaction transaction)
	{
		this.transaction = transaction;
	}

	public int getStoreId()
	{
		return storeId;
	}

	public void setStoreId(int storeId)
	{
		this.storeId = storeId;
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public double getQuantity()
	{
		return quantity;
	}

	public void setQuantity(double quantity)
	{
		this.quantity = quantity;
	}

	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
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

}
