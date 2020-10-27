package edu.miu.groupx.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.security.model.User;

@Service
@Transactional
public class AdminService 
{
	@Autowired
	private AdminRepository adminRepo;
	
	public List<User> findAll()
	{
		return adminRepo.findAll();
	}
	
	public void addUser(User user)
	{
		adminRepo.save(user);
	}
	
	public User getUser(String id)
	{
		return adminRepo.findById(id).get();
	}
	
	public void delete(String id)
	{
		adminRepo.deleteById(id);
	}
	
	public void updateUserById(User user, String id)
	{
		adminRepo.updateUserById(user, id);
	}
	
	public Boolean getApprovalForVendor(String id)
	{
		if(adminRepo.getApprovalForVendor(id) >= 1)
		{
			return true;
		}// if
		
		return false;
	}
	
	public void updateStatusProduct(String id, String status)
	{
		adminRepo.updateStatusProduct(id, status);
	}

	
	
	
}
