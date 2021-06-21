package tacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.bean.Taco;
import tacos.dao.TacoRepository;
import tacos.service.TacoService;

@Service
public class TacoServiceImpl implements TacoService {

	@Autowired
	private TacoRepository tacoRepository;
	
	@Override
	public Taco save(Taco taco) {
		return tacoRepository.save(taco);
	}

}
