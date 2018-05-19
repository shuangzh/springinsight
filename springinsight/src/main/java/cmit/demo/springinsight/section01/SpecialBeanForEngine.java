package cmit.demo.springinsight.section01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.ResolvableType;


public class SpecialBeanForEngine implements BeanFactoryPostProcessor, BeanNameAware{
	
	String name;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] names = beanFactory.getBeanDefinitionNames();
		System.out.println("SpecialBeanForEngine bean name :"+name);
		System.out.println("----------------------");
		for(String n: names){
			System.out.println(n);
		}
		System.out.println("----------------------");
		
		BeanDefinitionRegistry bdr = (BeanDefinitionRegistry)beanFactory;
//		BeanDefinition bd = bdr.getBeanDefinition("benzCar");
//		System.out.println(bd.getClass());
		GenericBeanDefinition gbd = new GenericBeanDefinition();
//		gbd.setBeanClass(Engine01.class);
		gbd.setBeanClass(EngineFactory.class);
		gbd.setScope(BeanDefinition.SCOPE_SINGLETON);
		gbd.setAutowireCandidate(true);
		bdr.registerBeanDefinition("engine01-gbd", gbd);
	}
	
	public static class EngineFactory implements FactoryBean<Engine>, BeanNameAware, InvocationHandler{
		
		String name;
		
		@Override
		public Engine getObject() throws Exception {
			System.out.println("EngineFactory  to build Engine01 , EngineFactory :"+ name);
			Engine prox = (Engine) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{Engine.class}, this);
			return prox;
			//return new Engine01();
		}

		@Override
		public Class<?> getObjectType() {
			return Engine.class;
		}

		@Override
		public boolean isSingleton() {
			return true;
		}

		@Override
		public void setBeanName(String name) {
			this.name = name;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("here is invoke  engine:"+method.getName());
			return null;
		}
	}

	@Override
	public void setBeanName(String name) {
		this.name =name;
	}
}
