package cgu.im.helloworld01.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<Class, Long>{
	Class findByClassName(String className);
	List<Class> findAll();
}
