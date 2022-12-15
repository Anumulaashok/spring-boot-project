package com.example.demo.Model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.demo.DTO_and_ENUM.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer addressid;
	private String area;
	private String village;
	private String state;
	private String pincode;
	private String mobilenumber;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "address")
	private User user;
	
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(area, other.area) 
//				&& Objects.equals(email, other.email)
				&& Objects.equals(mobilenumber, other.mobilenumber) && Objects.equals(pincode, other.pincode)
				&& Objects.equals(state, other.state) 
				&& Objects.equals(user, other.user) && Objects.equals(village, other.village);
	}
	@Override
	public int hashCode() {
		return Objects.hash(area,
//				email,
				mobilenumber, pincode, state, user, village);
	}
	
	
	public Address( String area, String village, String state, String pincode, String mobilenumber) {
		super();
		
		this.area = area;
		this.village = village;
		this.state = state;
		this.pincode = pincode;
		this.mobilenumber = mobilenumber;
	}
	public Integer getAddressid() {
		return addressid;
	}

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	@Override
	public String toString() {
		return "Address [area=" + area + ", village= " + village + ", state=" + state + ", pincode=" + pincode
				+ ", mobilenumber=" + mobilenumber + "]";
	}
	
	
	
}
