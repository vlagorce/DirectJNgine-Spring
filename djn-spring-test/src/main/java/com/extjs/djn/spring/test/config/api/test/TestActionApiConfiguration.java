package com.extjs.djn.spring.test.config.api.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestActionApiConfiguration extends com.extjs.djn.spring.action.conf.impl.BaseActionApiConfiguration<ITestDirectAction> {

    @Autowired
    @Override
    public void setListActions(List<ITestDirectAction> listActions) {
	super.setListActions(listActions);
    }
}