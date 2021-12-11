package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.BcryptSecurity;
import org.emarket.hustle.hustleemarketrest.entity.Store;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestStore;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.StoreService;
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
public class StoreRestController
{
	@Autowired
	public StoreService storeService;

	@Autowired
	public BcryptSecurity bcrypt;

	/*
	 * #######################################
	 * ############ GET STORE ################
	 * #######################################
	 */

	@GetMapping("/stores")
	public List<Store> getStore(@RequestBody(required = false) GetRequestStore getRequest)
	{
		if(getRequest == null)
		{
			return storeService.getStore();
		}

		return storeService.getStore(getRequest);
	}

	/*
	 * #######################################
	 * ########### GET STORE BY ID ###########
	 * #######################################
	 */

	@GetMapping("/stores/{id}")
	public Store getStoreById(@PathVariable int id)
	{
		return storeService.getStoreById(id);
	}

	/*
	 * #######################################
	 * ############# ADD STORE ###############
	 * #######################################
	 */

	@PostMapping("/stores")
	public ProcessConfirmation addStore()
	{
		return new ProcessConfirmation("Failed", "Store", "Unsupported endpoint");
	}

	/*
	 * #######################################
	 * ########### UPDATE STORE ##############
	 * #######################################
	 */

	@PutMapping("/stores")
	public ProcessConfirmation updateStore(@RequestBody Store store)
	{
		return new ProcessConfirmation("Failed", "Store", "Unsupported endpoint");
	}

	/*
	 * #######################################
	 * ######### DELETE STORE BY ID ##########
	 * #######################################
	 */
	@DeleteMapping("/stores/{id}")
	public ProcessConfirmation deleteStoreById(@PathVariable int id)
	{

		Store store = storeService.getStoreById(id);

		if(store == null)
		{
			throw new NotFoundException("STORE WITH ID: " + id);
		}

		/*
		 * severe the ties between the store and the seller
		 * so we can safely delete the store
		 */
		store.getSeller().setStore(null);

		storeService.deleteStoreById(id);

		return new ProcessConfirmation("SUCCESS", "SELLER",
				"THE CUSTOMER WITH ID:" + id + " WAS DELETED.");

	}

}
