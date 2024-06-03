package cgu.im.helloworld01;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cgu.im.helloworld01.domain.AppUser;
import cgu.im.helloworld01.domain.AppUserRepository;
import cgu.im.helloworld01.domain.Car;
import cgu.im.helloworld01.domain.CarRepository;
import cgu.im.helloworld01.domain.Owner;
import cgu.im.helloworld01.domain.OwnerRepository;

@SpringBootApplication
public class Helloworld01Application implements CommandLineRunner {
	
	 private static final Logger logger =
	            LoggerFactory.getLogger(
	                    Helloworld01Application.class
	            );

	private final CarRepository repository;
	private final OwnerRepository orepository;
	private final AppUserRepository urepository;
	
	
	public Helloworld01Application(CarRepository repository,
								   OwnerRepository orepository,
								   AppUserRepository urepository) {
	       this.repository = repository;
	       this.orepository = orepository;
	       this.urepository = urepository;
	}

	
	public static void main(String[] args) {
		SpringApplication.run(Helloworld01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
//		// Add owner objects and save these to db
		Owner owner1 = new Owner("John" , "Johnson");
		Owner owner2 = new Owner("Mary" , "Robinson");
		orepository.saveAll(Arrays.asList(owner1, owner2));

		
		repository.save(new Car("Ford", "Mustang", "Red",
				"ADF-1121", 2023, 59000, owner1));
		repository.save(new Car("Nissan", "Leaf", "White",
				"SSJ-3002", 2020, 29000, owner2));
		repository.save(new Car("Toyota", "Prius", "Silver",
				"KKO-0212", 2022, 39000, owner2));
		
        // Username: user, password: user
        urepository.save(new AppUser("user", 
            "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue","USER"));
        
        // Username: admin, password: admin
        urepository.save(new AppUser("admin", 
            "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN"));
		
		// Fetch all cars and log to console
		for (Car car : repository.findAll()) {
			logger.info("brand: {}, model: {}",
			car.getBrand(), car.getModel());
		}
		
		// findByBrand
		System.out.println("JPA查詢："+repository.findByBrand("Ford").
	        		get(0).getColor());
		
		// findByColor
		Car car1 = repository.findByColor("Silver").get(0);	     
		logger.info("Silver car: {}", car1.getRegistrationNumber());
		
		// EX2-3:查詢2021年(含)之後出廠的車號
		for(int i = 2021; i <= 2024; i++)
		{
		 	List<Car> cars = repository.findByModelYear(i);
			    	 
		 	for(int k = 0; k < cars.size(); k++)
		 	{
		 	   logger.info("Year "+i+"{}", cars.get(k).getRegistrationNumber()); 
		 	}
		}
		
		//查出“Nissan”廠牌“Leaf”車型的車號
		System.out.println("------------------------");
		System.out.println("取出Nissan Leaf的車號: "+repository.findByBrandAndModel("Nissan", "Leaf").
		     			get(0).getRegistrationNumber());
		
		System.out.println("------------------------");
		System.out.println("請查出“Ford”廠牌或Silver顏色的車號: ");
		
		// 請查出“Ford”廠牌或Silver顏色的車號
		for (Car car : repository.findByBrandOrColor("Ford", "Silver")) {	    	 
			logger.info("brand: {}, model: {}, car#: {}",
		                car.getBrand(), car.getModel(), car.getRegistrationNumber());
		}
		
		//請以出廠年份排序，列出所有車輛的廠牌、車號以及出廠年份
		System.out.println("------------------------");
		System.out.println("以出廠年份排序，列出所有車輛的廠牌、車號以及出廠年份");
		    
		for (Car car : repository.findAllByOrderByModelYear()) {	    	 
		     logger.info("brand: {}, car#: {}, year: {}",
		     car.getBrand(), car.getRegistrationNumber(), car.getModelYear());
		}
		
		// 請以出廠年份遞減排序，列出所有車輛的廠牌、車號以及出廠年份: 
		for (Car car : repository.findAllByOrderByModelYearDesc()) {	    	 
			logger.info("brand: {}, car#: {}, year: {}",
			car.getBrand(), car.getRegistrationNumber(), car.getModelYear());
		}
		
//		// 刪除某類型的一筆資料
//		System.out.println("------------------------");
//		System.out.println("查出白色汽車");
//		Car c = repository.findByColor("White").get(0);
//			   
//		System.out.println("刪除白色汽車");
//		repository.delete(c);
//		
//		// 查詢某類型的資料筆數
//		System.out.println("------------------------");
//		System.out.println("汽車數量="+repository.count());
//
//		System.out.println("------------------------");
//		System.out.println("刪除所有汽車");
//		repository.deleteAll();
//		
//		System.out.println("------------------------");
//		System.out.println("汽車數量="+repository.count());
		
		// 找出Ford廠牌的所有汽車，並印出廠牌、車號、出廠年份
		System.out.println("------------------------");	    
		for (Car car : repository.findByBrand1("Ford")) {	    	 
		     	logger.info("brand: {}, car#: {}, year: {}",
			car.getBrand(), car.getRegistrationNumber(), car.getModelYear());
		}
		
		// 取得廠牌以a字母結尾的車子
		System.out.println("------------------------");	    
		for (Car car : repository.findByBrandEndsWith("a")) {	    	 
			logger.info("brand: {}, car#: {}, year: {}",
			         car.getBrand(), car.getRegistrationNumber(), car.getModelYear());
		}
		
		// 請取得廠牌包含a字母的車
		for (Car car : repository.findByBrandContaining("a")) {	    	 
		     logger.info("brand: {}, car#: {}, year: {}",
		     car.getBrand(), car.getRegistrationNumber(), car.getModelYear());
		}
		
		// 請用JPQL取得包含六個字母廠牌的車
		for (Car car : repository.findByBrandSixLetter()) {	    	 
		     logger.info("brand: {}, car#: {}, year: {}",
		     car.getBrand(), car.getRegistrationNumber(), car.getModelYear());
		}




















		
	}

}
