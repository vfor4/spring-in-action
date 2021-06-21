package tacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.bean.TacoOrder;
import tacos.dao.OrderRepository;
import tacos.service.OrderService;

@Service
public class TacoOrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public TacoOrder save(TacoOrder order) {
		return orderRepository.save(order);
	}

}
