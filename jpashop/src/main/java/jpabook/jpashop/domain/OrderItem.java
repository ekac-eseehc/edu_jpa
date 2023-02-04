package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@Column(name = "ORDER_ID")
	private Long orderID;
	
	@Column(name = "ITEM_ID")
	private Long itemId;
	
	private int rderPrice;
	private int ount;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderID() {
		return orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public int getRderPrice() {
		return rderPrice;
	}
	public void setRderPrice(int rderPrice) {
		this.rderPrice = rderPrice;
	}
	public int getOunt() {
		return ount;
	}
	public void setOunt(int ount) {
		this.ount = ount;
	}
}
