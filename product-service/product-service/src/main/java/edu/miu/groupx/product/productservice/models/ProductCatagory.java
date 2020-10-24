package edu.miu.groupx.product.productservice.models;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
@Component
@Entity
@Data
public class ProductCatagory implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private int quantity;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productCatagory")
  private Set<Product> products;
	
	public ProductCatagory() {
		
	}
	
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
