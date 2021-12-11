package org.emarket.hustle.hustleemarketrest.dao;

import org.emarket.hustle.hustleemarketrest.entity.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer>
{
	public Slice<History> findHistoryByStoreId(int id, Pageable pageable);

	public Slice<History> findHistoryByItemId(int id, Pageable pageable);
}
