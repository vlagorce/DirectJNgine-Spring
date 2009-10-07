package com.extjs.djn.spring.dispatcher;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.extjs.djn.spring.loader.SpringLoaderHelper;
import com.softwarementors.extjs.djn.api.RegisteredAction;
import com.softwarementors.extjs.djn.api.RegisteredApi;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.router.dispatcher.Dispatcher;

public class SpringDispatcher extends Dispatcher {

    private static final Logger LOGGER = Logger.getLogger(SpringDispatcher.class);

    private final Map<Class<?>, Object> mapClassBeanName;

    public SpringDispatcher(Registry registry) {
	this.mapClassBeanName = new HashMap<Class<?>, Object>();
	initDispatcher(registry);
    }

    protected void initDispatcher(Registry registry){
	for (RegisteredApi api : registry.getApis()) {
	    for (RegisteredAction registeredAction : api.getActions()) {
		Object action = SpringLoaderHelper.getBeanOfType(registeredAction.getActionClass());
		if (action == null) {
		    try{
			action = registeredAction.getActionClass().newInstance();
		    }catch (Exception e) {
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
}
