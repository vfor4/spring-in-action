package tacos.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import tacos.bean.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long>{


	
}
