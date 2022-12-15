package com.example.demo.DTO_and_ENUM;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
	
	private Integer invoiceID;
	private String Shipping_address;
	private Map<String, Double>  products_Details= new HashMap<>();
	private Double total;
	
}
