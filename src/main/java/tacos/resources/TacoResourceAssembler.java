package tacos.resources;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import tacos.bean.Taco;
import tacos.controller.DesignTacoController;

public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

	public TacoResourceAssembler() {
		super(DesignTacoController.class, TacoResource.class);
	}

	@Override
	protected TacoResource instantiateModel(Taco taco) {
		return new TacoResource(taco);
	}

	@Override
	public TacoResource toModel(Taco entity) {
		return createModelWithId(entity.getId(), entity);
	}

}
