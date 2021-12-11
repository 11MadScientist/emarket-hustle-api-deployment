package org.emarket.hustle.hustleemarketrest.dao;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer>
{
	List<CustomerAddress> findCustomerAddressByCustomerId(int id);
}
