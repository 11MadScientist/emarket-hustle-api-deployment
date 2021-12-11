package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Basket;

public interface BasketService
{
	public List<Basket> getBasket();

	public List<Basket> getCustomerBasket(int customerId);

	public Basket getBasketById(int id);

	public void saveBasket(Basket basket);

	public void deleteBasket(Basket basket);

	public void deleteBasketById(int id);

	public void deleteBsaketByCustomer(int customerId);
}
