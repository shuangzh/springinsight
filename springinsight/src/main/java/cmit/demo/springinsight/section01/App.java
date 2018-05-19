package cmit.demo.springinsight.section01;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Bean(initMethod="start")
	BenzCar benzCar(Engine engine){
		BenzCar car = new BenzCar();
		car.engine = engine;
		return car ;
	}
	
	@Bean(initMethod="start")
	BmwCar bmwCar(){
		return new BmwCar();
	}
	
//	@Bean
	Engine01 engine01(){
		return new Engine01();
	}
	
	@Bean
	SpecialBeanForEngine specialBeanForEngine(){
		return new SpecialBeanForEngine();
	}
}
