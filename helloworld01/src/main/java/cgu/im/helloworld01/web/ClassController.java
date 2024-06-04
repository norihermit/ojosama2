package cgu.im.helloworld01.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cgu.im.helloworld01.domain.Class;
import cgu.im.helloworld01.domain.ClassRepository;

@RestController
@RequestMapping("/api")
public class ClassController {

	@Autowired
    private ClassRepository classRepository;
	
	@GetMapping("/classes")
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }
}
