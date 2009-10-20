package com.extjs.djn.spring.demo.autowired.action;

import org.springframework.stereotype.Component;

import com.extjs.djn.spring.demo.autowired.IAutoWiredDirectAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

@Component
public class FullAutowiredAction implements IAutoWiredDirectAction {

    @DirectMethod
    public String method() {
	return this.getClass().getName();
    }
}
