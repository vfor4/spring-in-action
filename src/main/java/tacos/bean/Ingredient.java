package tacos.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	private final String id;
	
	@Column(name = "name")
	private final String name;
	
	@Enumerated(EnumType.STRING)
	private final Type type;
	
	@ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
	private List<Taco> tacos;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
