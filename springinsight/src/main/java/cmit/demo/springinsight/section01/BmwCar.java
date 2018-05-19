package cmit.demo.springinsight.section01;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class BmwCar implements InitializingBean {
	
	@Autowired
	Engine engine;
	
	public BmwCar(){
		System.out.println("BmwCar Constructor");
		if(engine==null){
			System.out.println("BmwCar's engine not setting");
		}else{
			System.out.println("BmwCar's engine installed");
		}
	}
	
	void start(){
		System.out.println("BmwCar start");
		engine.fire();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("BmwCar initializingBean after propertieSet");
		if(engine==null){
			System.out.println("BmwCar's engine not setting, in initializingBean ");
		}else{
			System.out.println("BmwCar's engine installed, in initializingBean");
			engine.fire();
		}
	}
	
	@PostConstruct
	public void postConstruct(){
		System.out.println("BmwCar postConstruct");
		if(engine==null){
			System.out.println("BmwCar's engine not setting, in postConstruct");
		}else{
			System.out.println("BmwCar's engine installed, in postConstruct");
		}
	}	
}
