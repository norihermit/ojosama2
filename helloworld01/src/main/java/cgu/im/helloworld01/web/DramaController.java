package cgu.im.helloworld01.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgu.im.helloworld01.domain.Drama;
import cgu.im.helloworld01.domain.DramaRepository;

@RestController
@RequestMapping("/api")
public class DramaController {
	
	@Autowired
	private DramaRepository drepository;
	public DramaController(DramaRepository drepository) {
		super();
		this.drepository = drepository;
	}

	@GetMapping("/dramas")
    public Iterable<Drama> getDramas() {
		//Fetch and return cars
    	return drepository.findAll();
	}
}
