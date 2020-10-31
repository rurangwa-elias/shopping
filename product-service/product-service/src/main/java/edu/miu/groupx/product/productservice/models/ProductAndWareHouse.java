package edu.miu.groupx.product.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductAndWareHouse {

    private Long id;
    private int quantity;

    private ProductStatus status;

    private List<Product> products;
    private Integer version;
    private String name;
    private String description;
    private double Price;

    private String imageUrl;

    private List<ProductImages> pictures ;

    private Date addedOn;

    private Set<Category> category= new HashSet();
    private Long productCatagoryId;

    private Long productWarehouseId;

    private User user;
}
