package org.emarket.hustle.hustleemarketrest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.emarket.hustle.hustleemarketrest.response.NotPermittedException;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "transaction")
public class Transaction
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "customer_id", updatable = false)
	private int customerId;

	@Column(name = "customer_username", updatable = false)
	private String customerUsername;

	@Column(name = "customer_address_id", updatable = false)
	private int customerAddressId;

	@Column(name = "status")
	private String status;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "transaction")
	private List<History> histories;

	@Column(name = "sub_total")
	private double subTotal;

	@Column(name = "service_fee")
	private double serviceFee;

	@Column(name = "delivery_fee")
	private double deliveryFee;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "grand_total")
	private double grandTotal;

	@Column(name = "preferred_time_delivered")
	private long preferredTimeDelivered;

	@Column(name = "approved_time")
	private long approvedTime;

	@Column(name = "delivered_time")
	private long deliveredTime;

	@Column(name = "creation_date")
	private long creationDate;

	@Column(name = "modified_date")
	private long modifiedDate;

	public Transaction()
	{
		subTotal = 0;
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

	public int getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}

	public String getCustomerUsername()
	{
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername)
	{
		this.customerUsername = customerUsername;
	}

	public int getCustomerAddressId()
	{
		return customerAddressId;
	}

	public void setCustomerAddressId(int customerAddressId)
	{
		this.customerAddressId = customerAddressId;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public List<History> getHistories()
	{
		return histories;
	}

	public void setHistories(List<History> histories)
	{
		this.histories = histories;
	}

	public double getSubTotal()
	{
		return subTotal;
	}

	public void setSubTotal(double subTotal)
	{
		this.subTotal = subTotal;
	}

	public double getServiceFee()
	{
		return serviceFee;
	}

	public void setServiceFee(double serviceFee)
	{
		this.serviceFee = serviceFee;
	}

	public double getDeliveryFee()
	{
		return deliveryFee;
	}

	public void setDeliveryFee(double deliveryFee)
	{
		this.deliveryFee = deliveryFee;
	}

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public double getGrandTotal()
	{
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal)
	{
		this.grandTotal = grandTotal;
	}

	public long getPreferredTimeDelivered()
	{
		return preferredTimeDelivered;
	}

	public void setPreferredTimeDelivered(long preferredTimeDelivered)
	{
		this.preferredTimeDelivered = preferredTimeDelivered;
	}

	public long getApprovedTime()
	{
		return approvedTime;
	}

	public void setApprovedTime(long approvedTime)
	{
		this.approvedTime = approvedTime;
	}

	public long getDeliveredTime()
	{
		return deliveredTime;
	}

	public void setDeliveredTime(long deliveredTime)
	{
		this.deliveredTime = deliveredTime;
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

	/*
	 * ####################################################
	 * ################# CUSTOM METHODS ###################
	 * ####################################################
	 */

	@JsonIgnore
	public void addHistory(History history)
	{
		if(histories == null)
		{
			histories = new ArrayList<>();

		}
		histories.add(history);
	}

	@JsonIgnore
	public void addSubTotal(double total)
	{
		if(total < 0)
		{
			throw new NotPermittedException("ADDING NEGATIVE NUMBER TO GRAND TOTAL");
		}
		subTotal += total;

	}

	@JsonIgnore
	public void setGrandTotal()
	{
		grandTotal = deliveryFee + serviceFee + subTotal;
	}

}
