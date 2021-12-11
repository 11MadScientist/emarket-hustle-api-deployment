package org.emarket.hustle.hustleemarketrest.dao;

import org.emarket.hustle.hustleemarketrest.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
	public Slice<Transaction> findTransactionByCustomerUsernameLikeAndApprovedTimeLike(
			String searchPattern, String searchPattern2, Pageable pageable);
}
