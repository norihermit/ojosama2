package cgu.im.helloworld01.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private DramaRepository dramaRepository;

    @GetMapping("/dramas")
    public List<Map<String, Object>> getAllDramas() {
        List<Drama> dramas = dramaRepository.findAll();
        return dramas.stream()
                     .map(this::convertDramaToMap)
                     .collect(Collectors.toList());
    }

    private Map<String, Object> convertDramaToMap(Drama drama) {
        Map<String, Object> dramaMap = new HashMap<>();
        dramaMap.put("id", drama.getId());
        dramaMap.put("dramaName", drama.getDramaName());
        dramaMap.put("dramaCountry", drama.getDramaCountry());
        dramaMap.put("dramaIntro", drama.getDramaIntro());
        dramaMap.put("dramaYear", drama.getDramaYear());
        dramaMap.put("dramaEpisode", drama.getDramaEpisode());

        List<Map<String, Object>> classOfDramaList = drama.getClassOfDramas().stream()
            .map(classOfDrama -> {
                Map<String, Object> codMap = new HashMap<>();
                codMap.put("id", classOfDrama.getId());
                return codMap;
            }).collect(Collectors.toList());

        dramaMap.put("classOfDramas", classOfDramaList);
        return dramaMap;
    }
}
