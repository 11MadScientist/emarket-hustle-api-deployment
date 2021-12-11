package org.emarket.hustle.hustleemarketrest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import io.micrometer.core.lang.NonNull;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "customer_detail")
public class CustomerDetail
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@NonNull
	@Column(name = "email")
	private String email;

	@NonNull
	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "status")
	private boolean status;

	@Column(name = "dp_link")
	private String dpLink;

	public CustomerDetail()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public boolean isStatus()
	{
		return status;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public String getDpLink()
	{
		return dpLink;
	}

	public void setDpLink(String dpLink)
	{
		this.dpLink = dpLink;
	}

	@Override
	public String toString()
	{
		return "CustomerDetail [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", status=" + status
				+ ", dpLink=" + dpLink + "]";
	}

}
