package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.SellerDetailRepository;
import org.emarket.hustle.hustleemarketrest.entity.SellerDetail;
import org.emarket.hustle.hustleemarketrest.response.FailedException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerDetailServiceImpl implements SellerDetailService
{

	@Autowired
	private SellerDetailRepository sellerDetailService;

	@Override
	public List<SellerDetail> getSellerDetail()
	{
		List<SellerDetail> sellerDetails = sellerDetailService.findAll();

		if(sellerDetails.isEmpty())
		{
			throw new NotFoundException("SELLERS");
		}
		return sellerDetails;
	}

	@Override
	public SellerDetail getSellerDetailById(int id)
	{
		Optional<SellerDetail> result = sellerDetailService.findById(id);

		if(result.isEmpty())
		{
			throw new NotFoundException("SELLERDETAIL WITH ID: " + id);
		}
		return result.get();

	}

	@Override
	public void saveSellerDetail(SellerDetail sellerDetail)
	{
		try
		{
			sellerDetailService.save(sellerDetail);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING SELLERDETAIL");
		}
	}

	@Override
	public void deleteSellerDetailById(int id)
	{
		try
		{
			sellerDetailService.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING SELLERDETAIL WITH ID: " + id);
		}

	}

	@Override
	public void deleteSellerDetail(SellerDetail sellerDetail)
	{
		try
		{
			sellerDetailService.delete(sellerDetail);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING SELLERDETAIL");
		}
	}

}
