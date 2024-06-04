package cgu.im.helloworld01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cgu.im.helloworld01.domain.AppUser;
import cgu.im.helloworld01.domain.AppUserRepository;
import cgu.im.helloworld01.domain.ClassOfDramaRepository;
import cgu.im.helloworld01.domain.ClassRepository;
import cgu.im.helloworld01.domain.Drama;
import cgu.im.helloworld01.domain.DramaRepository;
import cgu.im.helloworld01.domain.Class;
import cgu.im.helloworld01.domain.ClassOfDrama;


@SpringBootApplication
public class Helloworld01Application implements CommandLineRunner {
	
	 private static final Logger logger =
	            LoggerFactory.getLogger(
	                    Helloworld01Application.class
	            );
	 
	private final AppUserRepository urepository;
	private final DramaRepository drepository;
	private final ClassRepository crepository;
	private final ClassOfDramaRepository codrepository;
	
	
	public Helloworld01Application(AppUserRepository urepository,
								   DramaRepository drepository,
								   ClassRepository crepository,
								   ClassOfDramaRepository codrepository
								   ) {
	       this.urepository = urepository;
	       this.drepository = drepository;
	       this.crepository = crepository;
	       this.codrepository =codrepository;
	}

	
	public static void main(String[] args) {
		SpringApplication.run(Helloworld01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
        // 取得總共有幾部戲劇
        List<Drama> dramas = drepository.findAll();
        logger.info("Number of dramas found: {}", dramas.size());

        // 尋找當前最大ID
        Long maxDramaId = dramas.stream().map(Drama::getId).max(Long::compare).orElse(0L);
        logger.info("Max Drama ID: {}", maxDramaId);

        ///新增資料///解開註解可以做測試
        // 創建戲劇資料
        Drama drama = new Drama();
        drama.setDramaName("voice3");
        drama.setDramaCountry("韓國國");
        drama.setDramaIntro("用聲音聽犯人");
        drama.setDramaEpisode(10);
        drama.setDramaYear(2017);
        drepository.save(drama);

        // 將影片有的類別裝在陣列裡
        List<String> classNames = Arrays.asList("驚悚", "復仇", "懸疑", "動作");
        List<ClassOfDrama> classOfDramaList = new ArrayList<>();
        
        //用迴圈分開查詢classId，最後用saveAll所有陣列一起儲存到資料庫
        for (String className : classNames) {
            Class clazz = crepository.findByClassName(className);
            if (clazz != null) {
                ClassOfDrama classOfDrama = new ClassOfDrama();
                classOfDrama.setDramaId(drama);
                classOfDrama.setClassId(clazz);
                classOfDramaList.add(classOfDrama);
            } else {
                logger.warn("Class with name {} not found!", className);
            }
        }
        // 保存ClassOfDramaList到整個陣列裡
        codrepository.saveAll(classOfDramaList);
       
        ///JPA查詢///
        // 印出所有的戲劇名稱
        for (Drama drama1 : drepository.findAll()) {
        	logger.info("DramaName: {}",
        	drama1.getDramaName());
        }
        
        // 查詢並印出 2022 年的所有戲劇
        List<Drama> dramas2022 = drepository.findByDramaYear(2022);
        System.out.println("2022 年的戲劇有：");
        for (Drama d : dramas2022) {
            System.out.println("DramaName: " + d.getDramaName());
        }
        
        // 查詢並印出小於10集的所有戲劇
        List<Drama> shortDramas = drepository.findByDramaEpisodeLessThan(10);
        System.out.println("小於10集的戲劇有：");
        for (Drama d : shortDramas) {
            System.out.println("DramaName: " + d.getDramaName() + ", Episodes: " + d.getDramaEpisode());
        }
        
        ///JPQL查詢///
        // 查找美國的戲劇
        System.out.println("------------------------");	    
        for (Drama d : drepository.findByDramaCountry("美國")) {	    	 
             	logger.info("DramaName: {},DramaYear: {},DramaCountry: {}",
        	d.getDramaName(), d.getDramaYear(), d.getDramaCountry());
        }
        // 查找包含黑暗的戲劇
        for (Drama d : drepository.findByDramaNameContaining("黑暗")) {	    	 
            logger.info("name: {}",
            d.getDramaName());
        }
        // 查找包含醫生的戲劇介紹
        for (Drama d : drepository.findByDramaIntroContaining("醫生")) {	    	 
            logger.info("name: {},dramaIntro: {}",
            d.getDramaName(),d.getDramaIntro());
        }
        // 查詢某個類別對應的所有戲劇
        List<Drama> thrillers = drepository.findDramaWithClassesByClassName("驚悚");
        logger.info("有驚悚類型的電影:");
        for (Drama c : thrillers) {
            logger.info("DramaName: {}", c.getDramaName());
        }
	}

}
