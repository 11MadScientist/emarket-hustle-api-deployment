package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.hustleemarketrest.entity.Basket;
import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.emarket.hustle.hustleemarketrest.response.NotPermittedException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.BasketService;
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
public class BasketRestController
{
	@Autowired
	private BasketService basketService;

	Logger log = Logger.getLogger(BasketRestController.class.getName());

	/*
	 * checking if quantity is not greater than instock and quantity not less than 1
	 */
	public boolean checkQuantity(Basket basket)
	{

		if(basket.getItem().getInStock() < basket.getQuantity() || basket.getQuantity() < 1)
		{
			log.info(basket.getItem().getInStock() + ":" + basket.getQuantity());
			throw new NotPermittedException("DECLARING QUANTITY GREATER THAN INSTOCK OR LESS THAN 0");

		}
		return true;
	}

	/*
	 * #######################################
	 * ############# GET BASKET ##############
	 * #######################################
	 */

	@GetMapping("/baskets")
	public List<Basket> getCustomerBasket(@RequestBody(required = false) Customer customer)
	{
		if(customer == null)
		{
			return basketService.getBasket();
		}

		return basketService.getCustomerBasket(customer.getId());
	}

	/*
	 * #######################################
	 * ######### GET BASKET BY ID ############
	 * #######################################
	 */

	@GetMapping("/baskets/{id}")
	public Basket getBasketById(@PathVariable int id)
	{
		return basketService.getBasketById(id);
	}

	/*
	 * #######################################
	 * ############ ADD BASKET ###############
	 * #######################################
	 */

	@PostMapping("/baskets/{customerId}")
	public Basket addBasket(@PathVariable int customerId, @RequestBody Basket basket)
	{

		checkQuantity(basket);

		basket.setId(0);
		basket.setCustomerId(customerId);
		basket.setCreationDate(basket.getModifiedDate());

		basketService.saveBasket(basket);

		return basket;
	}

	/*
	 * #######################################
	 * ########### UPDATE BASKET #############
	 * #######################################
	 */

	@PutMapping("/baskets")
	public Basket updateBasket(@RequestBody Basket basket)
	{
		checkQuantity(basket);

		basketService.saveBasket(basket);
		return basket;
	}

	/*
	 * #######################################
	 * ######## DELETE BASKET BY ID ##########
	 * #######################################
	 */

	@DeleteMapping("/baskets/{id}")
	public ProcessConfirmation deleteBasketById(@PathVariable int id)
	{

		basketService.deleteBasketById(id);

		return new ProcessConfirmation("SUCCESS", "ITEM",
				"THE ITEM WITH ID:" + id + " WAS DELETED.");

	}

	/*
	 * #######################################
	 * #### DELETE BASKET BY CUSTOMER ########
	 * #######################################
	 */

	@DeleteMapping("/baskets/customers/{id}")
	public ProcessConfirmation deleteBasketByCustomer(@PathVariable int id)
	{
		basketService.deleteBsaketByCustomer(id);

		return new ProcessConfirmation("SUCCESS", "ALL BASKET ENTRIES",
				"FROM CUSTOMER WITH ID: " + id + " WAS DELETED.");

	}
}
