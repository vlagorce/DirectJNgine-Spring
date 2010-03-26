/*
 * This file is part of DirectJNgine-Spring. Copyright © 2009 vlagorce
 * 
 * DirectJNgine-Spring is an java Api used to easily configure DirectJNgine with
 * spring.
 * 
 * DirectJNgine-Spring is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * DirectJNgine-Spring is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with DirectJNgine-Spring. If not, see <http://www.gnu.org/licenses/>.
 * 
 * DirectJNgine-Spring uses the ExtJs library (http://extjs.com), which is
 * distributed under the GPL v3 license (see http://extjs.com/license).
 * 
 * DirectJNgine-Spring uses the DirectJNgine api
 * (http://code.google.com/p/directjngine/), which is
 * distributed under the GPL v3 license.
 */
package com.extjs.djn.spring.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;

import org.springframework.util.CollectionUtils;

import com.extjs.djn.spring.action.IDirectAction;
import com.extjs.djn.spring.action.conf.IActionApiConfiguration;
import com.extjs.djn.spring.dispatcher.SpringDispatcher;
import com.extjs.djn.spring.global.ISpringGlobalConfiguration;
import com.extjs.djn.spring.global.impl.SpringGlobalConfiguration;
import com.extjs.djn.spring.loader.SpringLoaderHelper;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.ApiConfiguration;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;
import com.softwarementors.extjs.djn.router.RequestRouter;
import com.softwarementors.extjs.djn.servlet.DirectJNgineServlet;
import com.softwarementors.extjs.djn.servlet.ServletRegistryConfigurator;
import com.softwarementors.extjs.djn.servlet.ServletUtils;

/**
 * DirectJNgine servlet override to used spring object configuration
 * 
 * @author vlagorce
 * 
 */
public class SpringDirectJNgineServlet extends DirectJNgineServlet {

    private static final String PREFIX_SPRING_CONFIG_BEAN_NAME = "direct-spring-config";

    /** serialVersionUID */
    private static final long serialVersionUID = 6872468120152346213L;

    private ISpringGlobalConfiguration springGlobalConfiguration;

    @Override
    protected List<ApiConfiguration> createApiConfigurationsFromServletConfigurationApi(ServletConfig configuration) {

	List<ApiConfiguration> apiConfigurations = new ArrayList<ApiConfiguration>(springGlobalConfiguration.getActionApiConfigurations().size());
	if (!CollectionUtils.isEmpty(springGlobalConfiguration.getActionApiConfigurations())) {
	    for (IActionApiConfiguration<IDirectAction> actionApiConfiguration : springGlobalConfiguration.getActionApiConfigurations()) {
		apiConfigurations.add(actionApiConfiguration.createApiConfiguration(configuration.getServletContext()));
	    }
	}
	return apiConfigurations;
    }

    @Override
    protected GlobalConfiguration createGlobalConfiguration(ServletConfig configuration) {

	ServletUtils.checkRequiredParameters(configuration, GlobalParameters.PROVIDERS_URL);
	String providersUrl = ServletUtils.getRequiredParameter(configuration, GlobalParameters.PROVIDERS_URL);

	this.springGlobalConfiguration = (SpringGlobalConfiguration) SpringLoaderHelper.getBeanOfType(SpringGlobalConfiguration.class);

	if (springGlobalConfiguration == null) {
	    springGlobalConfiguration = new SpringGlobalConfiguration();
	    SpringLoaderHelper.autowireBean(springGlobalConfiguration, PREFIX_SPRING_CONFIG_BEAN_NAME);
	}

	return springGlobalConfiguration.createGlobalConfiguration(providersUrl);
    }

    @Override
    protected RequestRouter createRequestRouter(Registry registry, GlobalConfiguration globalConfiguration) {
	return new RequestRouter(registry, globalConfiguration, new SpringDispatcher(registry));
    }

    @Override
    protected void performCustomRegistryConfiguration(Class<? extends ServletRegistryConfigurator> configuratorClass, Registry registry, ServletConfig configuration) {
	//FIXME never call if you don't give any class name in the servlet (event if we'll not use it)..
	springGlobalConfiguration.performCustomRegistryConfiguration(registry, configuration);

    }
}
