package com.extjs.djn.spring.test.config.api.apinamespace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NameSpaceTestActionApiConfiguration extends com.extjs.djn.spring.action.conf.impl.BaseActionApiConfiguration<INameSpaceTestDirectAction> {

    @Autowired
    @Override
    public void setListActions(List<INameSpaceTestDirectAction> listActions) {
	super.setListActions(listActions);
    }
}