package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.SellerRepository;
import org.emarket.hustle.hustleemarketrest.entity.Seller;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestUser;
import org.emarket.hustle.hustleemarketrest.response.FailedException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService
{

	@Autowired
	SellerRepository sellerRepository;

	@Override
	public List<Seller> getSeller()
	{
		List<Seller> sellers = sellerRepository.findAll();

		if(sellers.isEmpty())
		{
			throw new NotFoundException("SELLERS");
		}

		return sellers;
	}

	@Override
	public List<Seller> getSeller(GetRequestUser getRequest)
	{
		// @formatter:off
		Pageable pageable = PageRequest.of(getRequest.getPage(),
							getRequest.getSize(),
							Sort.by(Sort.Direction.ASC, getRequest.getField()));


		Slice<Seller> slicedSellers = null;

		// @formatter:off
		if(getRequest.getSearchField().equals("name"))
		{
			slicedSellers = sellerRepository.findSellerByFirstNameLikeOrLastNameLike
			("%"+getRequest.getSearchPattern()+"%","%"+getRequest.getSearchPattern()+"%"
							, pageable);
		}

		else if(getRequest.getSearchField().equals("username"))
		{
			slicedSellers = sellerRepository.findSellerByUsernameLike
					("%"+getRequest.getSearchPattern()+"%", pageable);
		}

		List<Seller> sellers = slicedSellers.getContent();

		if(sellers.isEmpty())
		{
			throw new NotFoundException("SELLERS");
		}

		return sellers;
	}

	@Override
	public Seller getSellerById(int id)
	{
		Optional<Seller> seller = sellerRepository.findById(id);

		if(seller.isEmpty())
		{
			throw new NotFoundException("SELLER WITH ID: " + id);
		}

		return seller.get();
	}

	@Override
	public Seller saveSeller(Seller seller)
	{
		try
		{
			return sellerRepository.save(seller);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING SELLER");
		}
	}

	@Override
	public void deleteSeller(Seller seller)
	{
		try
		{
			sellerRepository.delete(seller);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETE SELLER");
		}

	}

	@Override
	public void deleteSellerById(int id)
	{
		try
		{
			sellerRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETE SELLER WITH ID: " + id);
		}

	}

	@Override
	public Seller loginSeller(String username)
	{
		try
		{
			return sellerRepository.findSellerByUsername(username);
		}
		catch (Exception e)
		{
			throw new NotFoundException("SELLER WITH USERNAME: " + username);
		}
	}



}
