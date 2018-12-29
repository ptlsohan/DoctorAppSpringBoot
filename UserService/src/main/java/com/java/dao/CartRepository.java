//package com.java.dao;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.java.dto.Cart;
//import com.java.dto.Product;
//
////@Repository
//public interface CartRepository extends JpaRepository<Cart, Integer> {
//	
//		public void addProductToCart(Product product);
//		
////		@Modifying
////		@Query("update Cart set quantity= :quantity where name =: name")
////		public void deleteCart(Product product);
//		@Modifying
//		@Query("update Product set quantity= :quantity where name =: name")
//		public void updateProduct_Quantity(@Param("name")String name,@Param("quantity")int quantity);
//
//}
