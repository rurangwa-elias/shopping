package edu.miu.groupx.security.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.miu.groupx.security.model.User;


public interface AdminRepository extends JpaRepository<User, String> 
{

	@Modifying
	@Query(nativeQuery=true, value ="UPDATE User uu SET uu.id = :user.id, uu.name = :user.name, uu.email = :user.email, uu.image = :user.image, uu.username = :user.username, uu.password = :user.password, uu.enabled = :user.enabled " + 
			"WHERE uu.id = :id)")
	void updateUserById(@Param("user") User user, @Param("id") String id);
	
	
	@Query(nativeQuery=true, value="SELECT count(*) FROM User WHERE id = :id and enabled = 1")
	Integer getApprovalForVendor(@Param("id") String id);
	
	@Query(nativeQuery=true, value="INSERT INTO product (id, name, description, price) VALUES "
			+ "(:id, :name, :description, :price)")
	void addProduct(@Param("id") String id, @Param("name") String name, @Param("description") String descrition, @Param("price") Integer price);
	
	@Modifying
	@Query(nativeQuery=true, value="UPDATE Product prod SET prod.status = :status WHERE prod.userid = :id")
	void updateStatusProduct(@Param("id") String id, @Param("status") String status);
	
	
}
