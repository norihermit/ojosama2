package cgu.im.helloworld01.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="carowners")
public interface OwnerRepository extends CrudRepository<Owner, Long>{
	
	

}
