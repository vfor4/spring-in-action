package tacos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.bean.Ingredient;
import tacos.bean.Ingredient.Type;
import tacos.bean.Taco;
import tacos.bean.TacoOrder;
import tacos.dao.IngredientRepository;
import tacos.dao.TacoRepository;
import tacos.resources.TacoResource;
import tacos.resources.TacoResourceAssembler;

@RestController
@RequestMapping(path = "/design", produces = { "application/json" })
@CrossOrigin(origins = "*")
public class DesignTacoController {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private TacoRepository tacoRepository;

	@Autowired
	private IngredientRepository ingreRepository;

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		try {
			ingredientRepository.findAll().forEach(i -> ingredients.add(i));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model) {
		model.addAttribute("taco", new Taco());
		return "design";
	}

	@GetMapping("/recent")
	public CollectionModel<TacoResource> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepository.findAll(page).getContent();
		CollectionModel<TacoResource> tacoResources = new TacoResourceAssembler().toCollectionModel(tacos);
		CollectionModel<TacoResource> recentResources = new CollectionModel<TacoResource>(tacoResources);
		recentResources
				.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(DesignTacoController.class).recentTacos()).withRel("recents"));
		return recentResources;
	}

	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute("tacoOrder") TacoOrder tacoOrder) {
		if (errors.hasErrors()) {
			return "design";
		}
		taco = tacoRepository.save(taco);
		tacoOrder.setTacos(new ArrayList<>());
		tacoOrder.addTaco(taco);
		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepository.findById(id);
		if (optTaco.isPresent()) {
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepository.save(taco);
	}

}
