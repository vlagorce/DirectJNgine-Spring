/* 
 *   This file is part of DirectJNgine-Spring. Copyright © 2009  vlagorce
 *   
 *   DirectJNgine-Spring is an java Api used to easily configure DirectJNgine with spring.
 *   
 *   DirectJNgine-Spring is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   DirectJNgine-Spring is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with DirectJNgine-Spring.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *   DirectJNgine-Spring uses the ExtJs library (http://extjs.com), which is 
 *   distributed under the GPL v3 license (see http://extjs.com/license).
 *   
 *   DirectJNgine-Spring uses the DirectJNgine api (http://code.google.com/p/directjngine/), which is 
 *   distributed under the GPL v3 license.
 */
package com.extjs.djn.spring.loader;

import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.context.ContextLoader;

/**
 * Helper used to retrieve bean in spring context
 * 
 * @author vlagorce
 * 
 */
public class SpringLoaderHelper {

    /**
     * Allow to autowired a non spring instanciated object
     * 
     * @param objToLoad
     * @param prefixBeanName
     */
    public static void autowireBean(Object objToLoad, String prefixBeanName) {
	DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ContextLoader.getCurrentWebApplicationContext()
		.getAutowireCapableBeanFactory();
	beanFactory.autowireBeanProperties(objToLoad, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
	beanFactory.initializeBean(objToLoad, prefixBeanName);
    }

    /**
     * Return the spring instance of a bean from is className.
     * 
     * Return null if no bean found.
     * 
     * @param instanceClass
     * 
     * @return
     */
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
