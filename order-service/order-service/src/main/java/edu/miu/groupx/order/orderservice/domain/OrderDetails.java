package edu.miu.groupx.order.orderservice.domain;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties("order")
public class OrderDetails {

	@Id
	@GeneratedValue
	private Long orderDetailId;
	private Double quantity;
	private Double itemPrice;
	private Double subTotal;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
	private Long productId;

	public OrderDetails(Double quantity, double itemPrice, Long productId){
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.productId = productId;
		this.subTotal = quantity * itemPrice;
	}

	public OrderDetails(){

	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getSubTotal() {
		return getQuantity() * getItemPrice();
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "OrderDetails{" +
				"orderDetailId=" + orderDetailId +
				", quantity=" + quantity +
				", itemPrice=" + itemPrice +
				", subTotal=" + subTotal +
				", productId=" + productId +
				'}';
	}
}
