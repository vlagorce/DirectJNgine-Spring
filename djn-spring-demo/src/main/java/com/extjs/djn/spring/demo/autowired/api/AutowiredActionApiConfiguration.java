package com.extjs.djn.spring.demo.autowired.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.extjs.djn.spring.action.conf.impl.BaseActionApiConfiguration;
import com.extjs.djn.spring.demo.autowired.IAutoWiredDirectAction;

public class AutowiredActionApiConfiguration extends BaseActionApiConfiguration<IAutoWiredDirectAction> {

    @Autowired
    @Override
    public void setListActions(List<IAutoWiredDirectAction> listActions) {
	super.setListActions(listActions);
    }
}
