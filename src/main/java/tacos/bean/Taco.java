package tacos.bean;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "taco")
public class Taco{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CREATEDAT")
	private Date createdAt;

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	@ManyToMany
	@JoinTable(name = "taco_ingredients", 
				joinColumns = @JoinColumn(name= "taco_id"),
				inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
	
	@ManyToMany(mappedBy = "tacos")
	private List<TacoOrder> taco_Orders;

	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
