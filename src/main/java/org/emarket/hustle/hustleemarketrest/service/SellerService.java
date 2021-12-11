package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Seller;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestUser;

public interface SellerService
{
	public List<Seller> getSeller();

	public List<Seller> getSeller(GetRequestUser getRequest);

	public Seller getSellerById(int id);

	public Seller saveSeller(Seller seller);

	public void deleteSeller(Seller seller);

	public void deleteSellerById(int id);

	public Seller loginSeller(String username);
}
