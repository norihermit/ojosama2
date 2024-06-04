package cgu.im.helloworld01.domain;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface DramaRepository extends CrudRepository<Drama, Long> {
	///JPA查詢///
	// 查找全部的戲劇
	List<Drama> findAll();
	// 特定年份的戲劇
    List<Drama> findByDramaYear(int year);
    // 小於幾集的戲劇
    List<Drama> findByDramaEpisodeLessThan(int episodes);
    
    ///JPQL///
    // 查找某國家的戲劇
    @Query("select d from Drama d where d.dramaCountry = :dramaCountry")
    List<Drama> findByDramaCountry(@Param("dramaCountry") String Country);
    
    // 查找包含某字的戲劇
    @Query("select d from Drama d where d.dramaName like %?1%")
    List<Drama> findByDramaNameContaining(String name);

    // 查找包含某字的戲劇介紹
    @Query("select d from Drama d where d.dramaIntro like %?1%")
    List<Drama> findByDramaIntroContaining(String name);

    //查找某類型的戲劇
    @Query(value = "SELECT d.id, d.drama_name, d.drama_country, d.drama_intro, d.drama_year, d.drama_episode, " +
            "GROUP_CONCAT(c.class_name) AS drama_class " +
            "FROM class_of_drama cod " +
            "JOIN drama d ON d.id = cod.drama_id " +
            "JOIN class c ON cod.class_id = c.classid " +
            "WHERE c.class_name = :className " +
            "GROUP BY d.id, d.drama_name, d.drama_country, d.drama_intro, d.drama_year",
            nativeQuery = true)
    
    List<Drama> findDramaWithClassesByClassName(@Param("className") String className);
    
    @Query("select distinct d from Drama d left join fetch d.classOfDramas")
    List<Drama> findAllWithClassOfDramas();
}
