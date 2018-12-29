package com.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.Address;
import com.java.dto.Cart;
import com.java.dto.User;

@Transactional
@Repository
@RepositoryRestResource(exported=false)
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select cart from User where userId= :userId")
	Cart getCart(int userId);
	
	@Modifying
	@Query("update User set firstName=?2, lastName=?3 where userId=?1")
	void updateUserById(int id,String firstName, String lastName);
	
	@Query("select userId from User where email=:email")
	int getId(String email);
	
	@Query("select addresses from User where userId = ?1")
	List<Address> findByAddresses(int userId);
	
	
	User findByEmail(String email);

	@Query(nativeQuery=true,name="select * from User where userId=:id")
	User findByUserId(int id);
}
