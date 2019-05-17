package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.entity.Flat;


public interface FlatRepository extends JpaRepository<Flat, Long> {

	List<Flat> findByUserId(Long id);

	Flat findFirstById(Long id);

	List<Flat> findByUserIdOrderByCreatedDesc(Long id);

	Flat findFirstByUserId(Long id);

	@Query("SELECT f FROM Flat f ORDER BY f.created DESC")
	List<Flat> findAllOrder();

	/*
	@Query(value = "SELECT f FROM Flat f WHERE f.city LIKE %:search%")
	List<Flat> findByGivenCity(@Param("search") String search);
	@Query(value = "SELECT f FROM Flat f WHERE f.name LIKE %:search%")
	List<Flat> findByGivenName(@Param("search") String search);
	@Query(value = "SELECT f FROM Flat f WHERE f.price BETWEEN :num1 AND :num2 ORDER BY f.created DESC")
	List<Flat> findByGivenNumbers(@Param("num1") Double num1, @Param("num2") Double num2);
	@Query(value = "SELECT f FROM Flat f WHERE f.numberOfGuests BETWEEN :num1 AND :num2 ORDER BY f.created DESC")
	List<Flat> findByGivenGuests(@Param("num1") Integer num1, @Param("num2") Integer num2);
	*/

	@Query(value = "SELECT f FROM Flat f WHERE f.name LIKE %:name% AND f.city LIKE %:city% AND f.price BETWEEN :num1 AND :num2 AND f.numberOfGuests BETWEEN :num3 AND :num4 ORDER BY f.created DESC")
	List<Flat> findByGivenCityNameNumsGuests(@Param("name") String name, @Param("city") String city, @Param("num1") Double num1, @Param("num2") Double num2, @Param("num3") Integer num3, @Param("num4") Integer num4);
	
}
