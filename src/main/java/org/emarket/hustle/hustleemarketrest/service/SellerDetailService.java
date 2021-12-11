package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.SellerDetail;

public interface SellerDetailService
{
	public List<SellerDetail> getSellerDetail();

	public SellerDetail getSellerDetailById(int id);

	public void saveSellerDetail(SellerDetail customerDetail);

	public void deleteSellerDetailById(int id);

	public void deleteSellerDetail(SellerDetail customerDetail);
}
