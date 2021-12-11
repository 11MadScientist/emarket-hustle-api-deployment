package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.CustomerDetail;

public interface CustomerDetailService
{
	public List<CustomerDetail> getCustomerDetail();

	public CustomerDetail getCustomerDetailById(int id);

	public void saveCustomerDetail(CustomerDetail customerDetail);

	public void deleteCustomerDetailById(int id);

	public void deleteCustomerDetail(CustomerDetail customerDetail);

}
