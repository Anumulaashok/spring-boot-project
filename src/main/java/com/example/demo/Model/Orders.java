package com.example.demo.Model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.demo.DTO_and_ENUM.Order_Status;
import com.example.demo.DTO_and_ENUM.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
public class Orders {
	
	@Id
	private Integer invoiceId;
	private LocalDate orderDate;
	private Integer item;
	
	@Enumerated(EnumType.STRING)
	private Order_Status status;
	
	 @Enumerated(EnumType.STRING)
	private PaymentMethod type=PaymentMethod.OnlinePayment;
	
	
	private double totalprice;
	private String url;
	
	private String address;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private OrderDetails orderDetails;
	
	
	
	public Orders(Integer id,LocalDate localDate, Integer item, Order_Status status, double totalprice, String url, String address) {
		super();
		this.invoiceId=id;
		this.orderDate = localDate;
		this.item = item;
		this.status = status;
		this.totalprice = totalprice;
		this.url = url;
		this.address = address;
	}
	@JsonIgnore
	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	

}
