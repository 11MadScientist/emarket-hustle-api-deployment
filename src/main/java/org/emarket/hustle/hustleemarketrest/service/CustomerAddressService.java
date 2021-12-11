package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;

public interface CustomerAddressService
{
	public List<CustomerAddress> getCustomerAddress();

	public List<CustomerAddress> getCustomerAddressByCustomerId(int id);

	public CustomerAddress getCustomerAddressById(int id);

	public void saveCustomerAddress(CustomerAddress customerDetail);

	public void deleteCustomerAddressById(int id);
}
