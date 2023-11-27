package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.entity.Order;
@Repository
public interface OrderDAO extends JpaRepository<Order, String>{
	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);

	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findOrderDTOByUsername(String username);

//	@Query("SELECT o FROM Order o WHERE o.Status = 2 AND YEAR(o.createDate) = :year")
//	List<Order> findDeliveredOrdersByYear(@Param("year") int year);

//	@Query("SELECT DAY(o.createDate), sum(o.TotalAmount) FROM Order o WHERE o.Status = 0 AND MONTH(o.createDate)= 11 GROUP BY DAY(o.createDate)")
//	List<Object[]> findDeliveredOrdersByMonth();

	@Query("SELECT od.id FROM Order od")
	List<String> getAllOrderId();


	@Query("SELECT SUM(o.TotalAmount) FROM Order o JOIN o.orderStatuses os WHERE os.status.id = 3") //test
	Double calculateTotalRevenue();

	@Query("SELECT SUM(o.TotalAmount), o.createDate FROM Order o " +
			"JOIN o.orderStatuses os " +
			"WHERE os.status.id = 3 " +
			"AND MONTH(o.createDate) = :month " +
			"AND YEAR(o.createDate) = :year " +
			"GROUP BY o.createDate")
	List<Object[]> calculateTotalRevenueByMonth(@Param("month") int month, @Param("year") int year);


	@Query("SELECT SUM(o.TotalAmount), o.createDate FROM Order o " +
			"JOIN o.orderStatuses os " +
			"WHERE os.status.id = 3 " +
			"AND DAY(o.createDate) = :day " +
			"AND MONTH(o.createDate) = :month " +
			"AND YEAR(o.createDate) = :year " +
			"GROUP BY o.createDate")
	List<Object[]> calculateTotalRevenueByDate(@Param("day") int day, @Param("month") int month, @Param("year") int year);
}
