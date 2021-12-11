package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.BcryptSecurity;
import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestUser;
import org.emarket.hustle.hustleemarketrest.response.ErrorLoginException;
import org.emarket.hustle.hustleemarketrest.response.FailedException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.basePath}")
public class CustomerRestController
{

	// for the bean bCrypt
	@Autowired
	private BcryptSecurity bcrypt;

	@Autowired
	private CustomerService customerService;

	/*
	 * #######################################
	 * ########### GET CUSTOMER ##############
	 * #######################################
	 */

	@GetMapping("/customers")
	public List<Customer> getCustomer(@RequestBody(required = false) GetRequestUser getRequest)
	{
		if(getRequest == null)
		{
			return customerService.getCustomer();
		}

		return customerService.getCustomer(getRequest);
	}

	/*
	 * #######################################
	 * ####### GET CUSTOMER BY ID ############
	 * #######################################
	 */
	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable int id)
	{
		return customerService.getCustomerById(id);
	}

	/*
	 * #######################################
	 * ########### ADD CUSTOMER ##############
	 * #######################################
	 */

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		/*
		 * set customer id to 0 to trigger INSERT, not UPDATE
		 * check if the customerDetail exist
		 * change id to 0 if exists
		 */
		customer.setId(0);
		customer.setCreationDate(customer.getModifiedDate());

		/* encrypting password using bcrypt */
		customer.setPassword(bcrypt.encode(customer.getPassword()));
		return customerService.saveCustomer(customer);

	}

	/*
	 * #######################################
	 * ######## UPDATE CUSTOMER ##############
	 * #######################################
	 */

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer)
	{
		/*
		 * because we do not pass the password to the client, we need to update
		 * the password when the client wants to update their data.
		 * we can do this by getting the data first then pinning the saved password
		 * from the database to passed Customer data
		 */

		Customer dbCustomer = customerService.getCustomerById(customer.getId());

		if(dbCustomer == null)
		{
			throw new NotFoundException("CUSTOMER WITH ID: " + customer.getId());
		}

		customer.setPassword(dbCustomer.getPassword());
		customer.setCreationDate(dbCustomer.getCreationDate());

		return customerService.saveCustomer(customer);

	}

	/*
	 * #######################################
	 * ########## DELETE CUSTOMER ############
	 * #######################################
	 */
	@DeleteMapping("/customers/{id}")
	public ProcessConfirmation deleteCustomerById(@PathVariable int id)
	{

		customerService.deleteCustomerById(id);

		return new ProcessConfirmation("SUCCESS", "CUSTOMER",
				"THE CUSTOMER WITH ID:" + id + " WAS DELETED.");

	}

	/*
	 * #######################################
	 * ########## LOGIN CUSTOMER #############
	 * #######################################
	 */

	@PostMapping("/customers/login")
	public Customer loginCustomer(@RequestBody Customer customer)
	{
		/*
		 * fetching Customer through username,
		 * checking the password on the returned
		 * customer if it matches the password
		 * given by the user.
		 * gives data if true, error when false
		 */

		Customer dbCustomer = customerService.loginCustomer(customer.getUsername());

		if(bcrypt.matches(customer.getPassword(), dbCustomer.getPassword()))
		{
			return dbCustomer;
		}
		throw new ErrorLoginException("CUSTOMER [Email, Password]");

	}

	/*
	 * #######################################
	 * ##### CUSTOMER CHANGE PASSWORD ########
	 * #######################################
	 */

	/*
	 * we need to put a custom mapping for changing of password
	 * because there is no method that checks if the password has
	 * already been hashed, or not. so in order to hash the new
	 * password and not hash the previous password, we need to create
	 * a new mapping
	 */

	@PutMapping("/customers/{password}")
	public Customer changePassword(@PathVariable String password, @RequestBody Customer customer)
	{
		Customer dbCustomer = customerService.getCustomerById(customer.getId());

		if(bcrypt.matches(password, dbCustomer.getPassword()))
		{
			customer.setPassword(bcrypt.encode(customer.getPassword()));

			customerService.saveCustomer(customer);
			return customer;
		}

		throw new FailedException("UPDATING PASSWORD");

	}

}
