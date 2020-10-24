package edu.miu.groupx.product.productservice.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import javax.persistence.*;

import lombok.Data;
@Component
@Entity
@Data
public class Product implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	private Integer version;
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private double Price;
	@NotNull
	private String imageUrl;
	
	private Date addedOn;
	@ManyToOne
	private ProductCatagory productCatagory;
	@Transient
	private Long productCatagoryId;
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	public Product() {
		
	}
	
	
	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public void setId(Long id) {
		this.id = id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Date getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}


	public ProductCatagory getProductCatagory() {
		return productCatagory;
	}


	public void setProductCatagory(ProductCatagory productCatagory) {
		this.productCatagory = productCatagory;
	}


	public ProductStatus getStatus() {
		return status;
	}


	public void setStatus(ProductStatus status) {
		this.status = status;
	}


	public Long getProductCatagoryId() {
		return productCatagoryId;
	}


	public void setProductCatagoryId(Long productCatagoryId) {
		this.productCatagoryId = productCatagoryId;
	}
	
	

}
