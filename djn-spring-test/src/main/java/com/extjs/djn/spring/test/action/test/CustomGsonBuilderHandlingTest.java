package com.extjs.djn.spring.test.action.test;

import org.springframework.stereotype.Component;

import com.extjs.djn.spring.test.config.api.test.ITestDirectAction;

@Component
public class CustomGsonBuilderHandlingTest extends com.softwarementors.extjs.djn.test.CustomGsonBuilderHandlingTest implements
	ITestDirectAction {

    public CustomGsonBuilderHandlingTest() {
	super();
    }

}