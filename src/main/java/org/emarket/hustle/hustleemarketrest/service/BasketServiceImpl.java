package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.emarket.hustle.hustleemarketrest.dao.BasketRepository;
import org.emarket.hustle.hustleemarketrest.entity.Basket;
import org.emarket.hustle.hustleemarketrest.response.FailedException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.NotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService
{

	@Autowired
	BasketRepository basketRepository;

	@Override
	public List<Basket> getBasket()
	{
		List<Basket> baskets = basketRepository.findAll();

		if(baskets.isEmpty())
		{
			throw new NotFoundException("BASKETS");
		}

		return baskets;
	}

	@Transactional
	@Override
	public List<Basket> getCustomerBasket(int customerId)
	{

		List<Basket> baskets = basketRepository.findByCustomerId(customerId);

		if(baskets.isEmpty())
		{
			throw new NotFoundException("BASKET FOR CUSTOMER: " + customerId);
		}

		return baskets;

	}

	@Override
	public Basket getBasketById(int id)
	{
		Optional<Basket> result = basketRepository.findById(id);

		if(result.isEmpty())
		{
			throw new NotFoundException("BASKET WITH ID: " + id);
		}

		return result.get();
	}

	@Override
	public void saveBasket(Basket basket)
	{
		try
		{
			basketRepository.save(basket);
		}
		catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			throw new NotPermittedException("DUPLICATE ITEM IN BASKET");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("SAVING BASKET");
		}

	}

	@Override
	public void deleteBasket(Basket basket)
	{
		try
		{
			basketRepository.delete(basket);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING BASKET");
		}

	}

	@Override
	public void deleteBasketById(int id)
	{
		try
		{
			basketRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING BASKET WITH ID: " + id);
		}

	}

	@Transactional
	@Override
	public void deleteBsaketByCustomer(int customerId)
	{
		try
		{
			basketRepository.deleteByCustomerId(customerId);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING BASKET WITH CUSTOMER: " + customerId);
		}

	}

}
