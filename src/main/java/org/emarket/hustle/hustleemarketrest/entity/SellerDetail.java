package org.emarket.hustle.hustleemarketrest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "seller_detail")
public class SellerDetail
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "status")
	private boolean status;

	@Column(name = "authorized")
	private boolean authorized;

	@Column(name = "dp_link")
	private String dpLink;

//	private Image dp;

	public SellerDetail()
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

	public boolean isAuthorized()
	{
		return authorized;
	}

	public void setAuthorized(boolean authorized)
	{
		this.authorized = authorized;
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
		return "SellerDetail [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", status=" + status
				+ ", authorized=" + authorized + ", dpLink=" + dpLink + "]";
	}

}
