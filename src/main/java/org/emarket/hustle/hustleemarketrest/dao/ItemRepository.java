package org.emarket.hustle.hustleemarketrest.dao;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Integer>
{
	public Slice<Item> findByDelistedFalse(Pageable pageable);

	@Query("SELECT i FROM Item i WHERE i.name LIKE:name AND i.category IN :categories AND delisted=false")
	public Slice<Item> findByCategory(
			@Param("categories") List<Integer> categories,
			@Param("name") String name,
			Pageable pageable);

	@Modifying
	@Query("DELETE from Item b WHERE b.store.id=:id")
	public void deleteItemByStoreId(@Param("id") int id);
}
