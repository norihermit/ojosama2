package cgu.im.helloworld01.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClassOfDramaRepository extends CrudRepository<ClassOfDrama,Long>{
	List<ClassOfDrama> findByDrama(Drama drama);
}
