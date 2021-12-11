package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Store;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestStore;

public interface StoreService
{
	public List<Store> getStore();

	public List<Store> getStore(GetRequestStore getRequest);

	public Store getStoreById(int id);

	public Store saveStore(Store store);

	public void deleteStore(Store store);

	public void deleteStoreById(int id);
}
