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

	//Tong doand thu
	@Query("SELECT SUM(o.TotalAmount) FROM Order o JOIN o.orderStatuses os WHERE os.status.id = 3") //test
	Double calculateTotalRevenue();

	//Tong doand thu theo nam
	@Query("SELECT SUM(o.TotalAmount) FROM Order o " +
			"JOIN o.orderStatuses os " +
			"WHERE os.status.id = 4 " +
			"AND YEAR(o.createDate) = :year " )
	Double calculateTotalRevenueByYear(@Param("year") int year);

	//Tong doand thu theo thang
	@Query("SELECT SUM(o.TotalAmount), o.createDate FROM Order o " +
			"JOIN o.orderStatuses os " +
			"WHERE os.status.id = 4 " +
			"AND MONTH(o.createDate) = :month " +
			"AND YEAR(o.createDate) = :year " +
			"GROUP BY o.createDate")
	List<Object[]> calculateTotalRevenueByMonth(@Param("month") int month, @Param("year") int year);

	//Tong so luong hoa don theo nam
	@Query("SELECT count(o.id) FROM Order o " +
			"JOIN o.orderStatuses os " +
			"WHERE os.status.id = 4 " +
			"AND YEAR(o.createDate) = :year ")
	Integer calculateTotalOrderByYear(@Param("year") int year);

	//Tong hoa don theo thang
//	@Query("SELECT count(o.id), o.createDate FROM Order o " +
//			"JOIN o.orderStatuses os " +
//			"WHERE os.status.id = 4 " +
//			"AND MONTH(o.createDate) = :month " +
//			"AND YEAR(o.createDate) = :year " +
//			"GROUP BY o.createDate")
//	List<Object[]> calculateTotalOrderByMonth(@Param("month") int month, @Param("year") int year);

//		@Query("SELECT count(o.id), o.createDate FROM Order o " +
//			"JOIN o.orderStatuses os " +
//			"WHERE os.status.id = :status " +
//			"AND MONTH(o.createDate) = :month " +
//			"AND YEAR(o.createDate) = :year " +
//			"GROUP BY o.createDate")
//	List<Object[]> calculateTotalOrderByMonth(@Param("month") int month, @Param("year") int year, @Param("status") int status);

	//Tong hoa don theo thang
	@Query("SELECT COUNT(DISTINCT o.id) FROM Order o " +
			"JOIN o.orderStatuses os " +
			"WHERE os.status.id = 4 " +
			"AND MONTH(o.createDate) = :month " +
			"AND YEAR(o.createDate) = :year ")
	List<Object[]> calculateTotalOrderByMonth4(@Param("month") int month, @Param("year") int year);

	@Query("SELECT COUNT(DISTINCT o.id) FROM Order o " +
			"JOIN o.orderStatuses os " +
			"WHERE os.status.id = 0 " +
			"AND MONTH(o.createDate) = :month " +
			"AND YEAR(o.createDate) = :year ")
	List<Object[]> calculateTotalOrderByMonth0(@Param("month") int month, @Param("year") int year);

	@Query("SELECT COUNT(DISTINCT o.id) FROM Order o " +
			"JOIN o.orderStatuses os " +
			"WHERE os.status.id > -1 " +
			"AND MONTH(o.createDate) = :month " +
			"AND YEAR(o.createDate) = :year ")
	List<Object[]> calculateTotalOrderByMonthAll(@Param("month") int month, @Param("year") int year);

	@Query("SELECT SUM(o.TotalAmount), o.createDate FROM Order o " +
			"JOIN o.orderStatuses os " +
			"WHERE os.status.id = 4 " +
			"AND DAY(o.createDate) = :day " +
			"AND MONTH(o.createDate) = :month " +
			"AND YEAR(o.createDate) = :year " +
			"GROUP BY o.createDate")
	List<Object[]> calculateTotalRevenueByDate(@Param("day") int day, @Param("month") int month, @Param("year") int year);

	@Query("SELECT o FROM Order o JOIN o.orderStatuses os WHERE os.status.id <> 1")
    List<Order> findOrdersWithStatusNotEqualToOne();
}
