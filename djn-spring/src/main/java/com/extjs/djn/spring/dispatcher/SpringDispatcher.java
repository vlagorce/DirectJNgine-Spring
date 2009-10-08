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
package com.extjs.djn.spring.dispatcher;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.extjs.djn.spring.loader.SpringLoaderHelper;
import com.softwarementors.extjs.djn.api.RegisteredAction;
import com.softwarementors.extjs.djn.api.RegisteredApi;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.router.dispatcher.Dispatcher;

/**
 * Dispatcher used to retrieve DirectAction
 * 
 * @author vlagorce
 * 
 */
public class SpringDispatcher extends Dispatcher {

    private static final Logger LOGGER = Logger.getLogger(SpringDispatcher.class);

    private final Map<Class<?>, Object> mapClassBeanName;

    public SpringDispatcher(Registry registry) {
	this.mapClassBeanName = new HashMap<Class<?>, Object>();
	initDispatcher(registry);
    }

    @Override
    protected Object createActionInstance(Class<?> instanceClass) {
	Object actionInstance = null;
	try {

	    if (this.mapClassBeanName.containsKey(instanceClass)) {
		actionInstance = this.mapClassBeanName.get(instanceClass);
	    } else {
		throw new InstantiationException("No instanceClass found for " + instanceClass);
	    }
	} catch (Exception e) {
	    throw createUnableToCreateInstanceException(instanceClass, e);
	}

	return actionInstance;
    }

    protected void initDispatcher(Registry registry) {
	for (RegisteredApi api : registry.getApis()) {
	    for (RegisteredAction registeredAction : api.getActions()) {
		Object action = SpringLoaderHelper.getBeanOfType(registeredAction.getActionClass());
		if (action == null) {
		    try {
			action = registeredAction.getActionClass().newInstance();
		    } catch (Exception e) {
			LOGGER.error(e);
		    }
		}
		this.mapClassBeanName.put(registeredAction.getActionClass(), action);
		if (LOGGER.isDebugEnabled()) {
		    LOGGER.debug("Add " + action.getClass() + " in dispatcher.");
		}
	    }
	}
    }
}