package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestUser;

public interface CustomerService
{
	public List<Customer> getCustomer();

	public List<Customer> getCustomer(GetRequestUser getRequest);

	public Customer getCustomerById(int id);

	public Customer saveCustomer(Customer customer);

	public void deleteCustomerById(int id);

	public Customer loginCustomer(String username);

}
