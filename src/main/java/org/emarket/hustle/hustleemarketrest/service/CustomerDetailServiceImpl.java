package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.CustomerDetailRepository;
import org.emarket.hustle.hustleemarketrest.entity.CustomerDetail;
import org.emarket.hustle.hustleemarketrest.response.FailedException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService
{

	@Autowired
	private CustomerDetailRepository customerDetailRepository;

	@Override
	public List<CustomerDetail> getCustomerDetail()
	{
		List<CustomerDetail> customerDetails = customerDetailRepository.findAll();
		if(customerDetails.isEmpty())
		{
			throw new NotFoundException("CUSTOMERDETAILS");
		}
		return customerDetails;
	}

	@Override
	public CustomerDetail getCustomerDetailById(int id)
	{
		Optional<CustomerDetail> result = customerDetailRepository.findById(id);

		if(result.isEmpty())
		{
			throw new NotFoundException("CUSTOMERDETAIL WITH ID: " + id);
		}
		return result.get();

	}

	@Override
	public void saveCustomerDetail(CustomerDetail customerDetail)
	{
		try
		{
			customerDetailRepository.save(customerDetail);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING CUSTOMERDETAIL");
		}
	}

	@Override
	public void deleteCustomerDetailById(int id)
	{
		try
		{
			customerDetailRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING CUSTOMERDETAIL WITH ID: " + id);
		}

	}

	@Override
	public void deleteCustomerDetail(CustomerDetail customerDetail)
	{
		try
		{
			customerDetailRepository.delete(customerDetail);
		}
		catch (Exception e)
		{

			throw new FailedException("DELETING CUSTOMERDETAIL");
		}

	}

}
