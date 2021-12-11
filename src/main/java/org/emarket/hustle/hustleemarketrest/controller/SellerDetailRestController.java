package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Seller;
import org.emarket.hustle.hustleemarketrest.entity.SellerDetail;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.SellerDetailService;
import org.emarket.hustle.hustleemarketrest.service.SellerService;
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
 * there is no post/insert in SellerDetail
 * because you need to be a Seller to create
 * a SellerDetail
 *
 */
@RestController
@RequestMapping("/emarket-hustle")
public class SellerDetailRestController
{

	@Autowired
	SellerDetailService sellerDetailService;

	@Autowired
	SellerService sellerService;

	/*
	 * #######################################
	 * ########## GET SELLER DETAIL ##########
	 * #######################################
	 */
	@GetMapping("/seller-details")
	public List<SellerDetail> getSellerDetail()
	{
		return sellerDetailService.getSellerDetail();
	}

	/*
	 * #######################################
	 * ###### GET SELLER DETAIL BY ID ########
	 * #######################################
	 */

	@GetMapping("/seller-details/{id}")
	public SellerDetail getSellerDetailById(@PathVariable int id)
	{
		return sellerDetailService.getSellerDetailById(id);
	}

	/*
	 * #######################################
	 * ######## UPDATE SELLER DETAIL #########
	 * #######################################
	 */

	@PutMapping("/seller-details")
	public SellerDetail updateSellerDetail(@RequestBody SellerDetail sellerDetail)
	{
		sellerDetailService.saveSellerDetail(sellerDetail);
		return sellerDetail;
	}

	/*
	 * #######################################
	 * ####### DELETE SELLER DETAIL ##########
	 * #######################################
	 */

	@DeleteMapping("/seller-details/{id}")
	public ProcessConfirmation deleteSellerDetail(@PathVariable int id)
	{
		Seller seller = sellerService.getSellerById(id);

		if(seller == null)
		{
			throw new NotFoundException("SELLER WITH ID: " + id);
		}

		/*
		 * severe the ties between the customer and the customerdetail
		 * so we can safely delete the customerdetail
		 */
		SellerDetail sellerDetail = seller.getSellerDetail();

		seller.setSellerDetail(null);

		sellerDetailService.deleteSellerDetail(sellerDetail);

		return new ProcessConfirmation("SUCCESS",
				"SELLERDETAIL", "SELLER DETAIL WITH ID: " + id + " WAS DELETED.");

	}

}
