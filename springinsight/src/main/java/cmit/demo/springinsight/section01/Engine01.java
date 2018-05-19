package cmit.demo.springinsight.section01;

import org.springframework.beans.factory.BeanNameAware;

public class Engine01 implements Engine, BeanNameAware {
	
	String name;
	
	@Override
	public void fire() {
		System.out.println("Engine01 fire : " + name +"  : "+ this.toString());
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("Engine01 setting bean name"+name);
		this.name = name;
	}
}
