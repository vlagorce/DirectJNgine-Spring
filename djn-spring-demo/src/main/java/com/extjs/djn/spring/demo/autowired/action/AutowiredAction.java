package com.extjs.djn.spring.demo.autowired.action;

import com.extjs.djn.spring.demo.autowired.IAutoWiredDirectAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class AutowiredAction implements IAutoWiredDirectAction {

    @DirectMethod
    public String method() {
	return this.getClass().getName();
    }
}
