package com.extjs.djn.spring.loader;

import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.context.ContextLoader;

public class SpringLoaderHelper {

    public static void autowireBean(Object objToLoad, String prefixBeanName) {
	DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ContextLoader.getCurrentWebApplicationContext()
		.getAutowireCapableBeanFactory();
	beanFactory.autowireBeanProperties(objToLoad, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
	beanFactory.initializeBean(objToLoad, prefixBeanName);
    }

    public static Object getBeanOfType(Class<?> instanceClass) {
	Object bean = null;
	Map<String, Object> beansMap = getBeansOfType(instanceClass);
	if (beansMap.size() > 1) {
	    throw new IllegalStateException("Only one instance of " + instanceClass + "is expected");
	} else if (beansMap.size() == 1) {
	    bean = beansMap.values().iterator().next();
	}
	return bean;

    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getBeansOfType(Class<?> instanceClass) {
	return ContextLoader.getCurrentWebApplicationContext().getBeansOfType(instanceClass);

    }

}
