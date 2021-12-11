package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.emarket.hustle.hustleemarketrest.entity.CustomerDetail;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.CustomerDetailService;
import org.emarket.hustle.hustleemarketrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * there is no post/insert in CustomerDetail
 * because you need to be a Customer to create
 * a CustomerDetail
 *
 */
@RestController
@RequestMapping("${api.basePath}")
public class CustomerDetailRestController
{
	@Autowired
	CustomerDetailService customerDetailService;

	@Autowired
	CustomerService customerService;

	/*
	 * #######################################
	 * ######## GET CUSTOMER DETAIL ##########
	 * #######################################
	 */
	@GetMapping("/customer-details")
	public List<CustomerDetail> getCustomerDetail()
	{
		return customerDetailService.getCustomerDetail();
	}

	/*
	 * #######################################
	 * ##### GET CUSTOMER DETAIL BY ID #######
	 * #######################################
	 */

	@GetMapping("/customer-details/{id}")
	public CustomerDetail getCustomerDetailById(@PathVariable int id)
	{
		return customerDetailService.getCustomerDetailById(id);
	}

	/*
	 * #######################################
	 * ####### UPDATE CUSTOMER DETAIL ########
	 * #######################################
	 */

	@PutMapping("/customer-details")
	public CustomerDetail updateCustomerDetail(@RequestBody CustomerDetail customerDetail)
	{
		customerDetailService.saveCustomerDetail(customerDetail);
		return customerDetail;
	}

	/*
	 * #######################################
	 * ###### DELETE CUSTOMER DETAIL #########
	 * #######################################
	 */

	@DeleteMapping("/customer-details/{id}")
	public ProcessConfirmation deleteCustomerDetail(@PathVariable int id)
	{

		Customer customer = customerService.getCustomerById(id);

		if(customer == null)
		{
			throw new NotFoundException("CUSTOMER WITH ID: " + id);
		}

		/*
		 * severe the ties between the customer and the customerdetail
		 * so we can safely delete the customerdetail
		 */
		CustomerDetail customerDetail = customer.getCustomerDetail();

		customer.setCustomerDetail(null);

		customerDetailService.deleteCustomerDetailById(customerDetail.getId());

		return new ProcessConfirmation("SUCCESS",
				"CUSTOMERDETAIL", "CUSTOMER DETAIL WITH ID: " + customerDetail.getId() + " WAS DELETED.");

	}

}
