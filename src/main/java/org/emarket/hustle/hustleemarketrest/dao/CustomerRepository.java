package org.emarket.hustle.hustleemarketrest.dao;

import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	public Customer findCustomerByUsername(String username);

	public Slice<Customer> findCustomerByFirstNameLikeOrLastNameLike(
			String searchPattern, String searchPattern2, Pageable pageable);

	public Slice<Customer> findCustomerByUsernameLike(
			String searchPattern, Pageable pageable);

}
