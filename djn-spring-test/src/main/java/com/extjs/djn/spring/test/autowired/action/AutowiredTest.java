package com.extjs.djn.spring.test.autowired.action;

import com.extjs.djn.spring.test.autowired.IAutoWiredDirectAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class AutowiredTest implements IAutoWiredDirectAction {

    @DirectMethod
    public boolean test_methodAT() {
	return true;
    }
}
