package cgu.im.helloworld01.web;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cgu.im.helloworld01.domain.ClassOfDrama;
import cgu.im.helloworld01.domain.ClassOfDramaRepository;
import cgu.im.helloworld01.domain.Drama;
import cgu.im.helloworld01.domain.DramaRepository;

@RestController
@RequestMapping("/api")
public class ClassOfDramaController {

    @Autowired
    private ClassOfDramaRepository classOfDramaRepository;

    @Autowired
    private DramaRepository dramaRepository;

    @GetMapping("/dramas/{dramaId}/classes")
    public List<String> getClassesByDramaId(@PathVariable Long dramaId) {
        Drama drama = dramaRepository.findById(dramaId).orElseThrow(() -> new RuntimeException("Drama not found"));
        List<ClassOfDrama> classOfDramas = classOfDramaRepository.findByDramaId(drama);
        return classOfDramas.stream()
                            .map(classOfDrama -> classOfDrama.getClassId().getClassName())
                            .collect(Collectors.toList());
    }
}
