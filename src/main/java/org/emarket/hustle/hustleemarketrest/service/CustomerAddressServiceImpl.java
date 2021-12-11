package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.CustomerAddressRepository;
import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;
import org.emarket.hustle.hustleemarketrest.response.FailedException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService
{

	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	@Override
	public List<CustomerAddress> getCustomerAddress()
	{
		List<CustomerAddress> customerAddresses = customerAddressRepository.findAll();

		if(customerAddresses.isEmpty())
		{
			throw new NotFoundException("CUSTOMERADDRESSES");
		}

		return customerAddresses;
	}

	@Override
	public List<CustomerAddress> getCustomerAddressByCustomerId(int id)
	{

		// @formatter:off
		List<CustomerAddress> customerAddresses = customerAddressRepository
									  .findCustomerAddressByCustomerId(id);

		if(customerAddresses.isEmpty())
		{
			throw new NotFoundException("CUSTOMERADDRESSES");
		}

		return customerAddresses;
	}

	@Override
	public CustomerAddress getCustomerAddressById(int id)
	{
		Optional<CustomerAddress> result = customerAddressRepository.findById(id);

		if(result.isEmpty())
		{
			throw new NotFoundException("CUSTOMERADDRESS WITH ID: " + id);
		}

		return result.get();
	}

	@Override
	public void saveCustomerAddress(CustomerAddress customerDetail)
	{
		try
		{
			customerAddressRepository.save(customerDetail);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING CUSTOMERADDRESS");
		}

	}

	@Override
	public void deleteCustomerAddressById(int id)
	{
		try
		{
			customerAddressRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING CUSTOMERADDRESS");
		}

	}

}
