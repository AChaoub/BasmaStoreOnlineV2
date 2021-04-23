package org.bos.Achaoub;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContext;

public class SpringApplicationContext implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;

	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
        CONTEXT = applicationContext;
	}

}
