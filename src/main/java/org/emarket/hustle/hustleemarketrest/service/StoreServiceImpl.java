package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.StoreRepository;
import org.emarket.hustle.hustleemarketrest.entity.Store;
import org.emarket.hustle.hustleemarketrest.entity.request.GetRequestStore;
import org.emarket.hustle.hustleemarketrest.response.FailedException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService
{

	@Autowired
	StoreRepository storeRepository;

	@Override
	public List<Store> getStore()
	{
		List<Store> stores = storeRepository.findAll();

		if(stores.isEmpty())
		{
			throw new NotFoundException("STORES");
		}

		return stores;
	}

	@Override
	public List<Store> getStore(GetRequestStore getRequest)
	{
		// @formatter:off
		Pageable pageable = PageRequest.of(getRequest.getPage(),
							getRequest.getSize(),
							Sort.by(Sort.Direction.ASC, getRequest.getField()));


		Slice<Store> slicedCustomers = null;

		// @formatter:off
		if(getRequest.getSearchField().equals("storeName"))
		{
			slicedCustomers = storeRepository.findStoreByStoreNameLike
					("%"+getRequest.getSearchPattern()+"%", pageable);
		}

		else if(getRequest.getSearchField().equals("storeAddress"))
		{
			slicedCustomers = storeRepository.findStoreByStoreAddressLike
					("%"+getRequest.getSearchPattern()+"%", pageable);
		}

		List<Store> stores = slicedCustomers.getContent();

		if(stores.isEmpty())
		{
			throw new NotFoundException("STORE");
		}

		return stores;
	}

	@Override
	public Store getStoreById(int id)
	{
		Optional<Store> store = storeRepository.findById(id);

		if(store.isEmpty())
		{
			throw new RuntimeException("Store with id:" + id + " was not found");
		}

		return store.get();
	}

	@Override
	public Store saveStore(Store store)
	{
		try
		{
			return storeRepository.save(store);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING STORE");
		}
	}

	@Override
	public void deleteStore(Store store)
	{
		try
		{
			storeRepository.delete(store);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING STORE");
		}

	}

	@Override
	public void deleteStoreById(int id)
	{
		try
		{
			storeRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING STORE WITH ID: " + id);
		}
	}



}
