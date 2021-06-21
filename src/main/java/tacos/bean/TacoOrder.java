package tacos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "taco_order")
public class TacoOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	@Column(name = "delivery_Name")
	private String deliveryName;

	@NotBlank(message = "Street is required")
	@Column(name = "delivery_Street")
	private String deliveryStreet;

	@NotBlank(message = "City is required")
	@Column(name = "delivery_City")
	private String deliveryCity;

	@NotBlank(message = "State is required")
	@Column(name = "delivery_State")
	private String deliveryState;

	@NotBlank(message = "Zip code is required")
	@Column(name = "delivery_Zip")
	private String deliveryZip;

	@CreditCardNumber(message = "Not a valid credit card number")
	@Column(name = "cc_Number")
	private String ccNumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	@Column(name = "cc_Expiration")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, message = "Invalid CVVv")
	@Column(name = "cc_CVV")
	private String ccCVV;

	@Column(name="placed_At")
	private Date placedAt;

	
	@ManyToMany
	@JoinTable(name = "taco_order_tacos", 
	joinColumns = @JoinColumn(name= "taco_Order_id"),
	inverseJoinColumns = @JoinColumn(name = "taco_id"))
	private List<Taco> tacos;

	@ManyToOne
	private User user;
	
	public void addTaco(Taco taco) {
		tacos.add(taco);
	}

	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
}
