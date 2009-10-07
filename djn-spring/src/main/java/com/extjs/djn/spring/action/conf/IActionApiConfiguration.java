package com.extjs.djn.spring.action.conf;

import java.util.List;

import javax.servlet.ServletContext;

import com.extjs.djn.spring.action.IDirectAction;
import com.softwarementors.extjs.djn.config.ApiConfiguration;

public interface IActionApiConfiguration<A extends IDirectAction> {

	ApiConfiguration createApiConfiguration(ServletContext context);

	List<A> getListActions();
}
