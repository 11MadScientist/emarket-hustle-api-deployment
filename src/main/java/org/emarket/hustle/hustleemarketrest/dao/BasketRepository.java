package org.emarket.hustle.hustleemarketrest.dao;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Basket;
import org.emarket.hustle.hustleemarketrest.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer>
{

	public List<Basket> findByCustomerId(int customerId);

//	@Modifying
//	@Query("DELETE FROM Basket b WHERE b.customerId=:id")
//	public void deleteCustomerBasket(@Param("id") int customerId);

	public void deleteByCustomerId(int id);

//	@Modifying
//	@Query("DELETE from Basket b WHERE b.item.id=:id")
//	public void deleteItemBasket(@Param("id") int id);

	public void deleteByItem(Item item);
}
