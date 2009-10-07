package com.extjs.djn.spring.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;

import org.apache.log4j.Logger;

import com.extjs.djn.spring.dispatcher.SpringDispatcher;
import com.extjs.djn.spring.global.ISpringGlobalConfiguration;
import com.extjs.djn.spring.global.impl.SpringGlobalConfiguration;
import com.extjs.djn.spring.loader.SpringLoaderHelper;
import com.softwarementors.extjs.djn.ServletUtils;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.ApiConfiguration;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;
import com.softwarementors.extjs.djn.router.RequestRouter;
import com.softwarementors.extjs.djn.servlet.DirectJNgineServlet;

public class SpringDirectJNgineServlet extends DirectJNgineServlet {

    private static final Logger LOGGER = Logger.getLogger(SpringDirectJNgineServlet.class);

    private static final String PREFIX_SPRING_CONFIG_BEAN_NAME = "direct-spring-config";

    private ISpringGlobalConfiguration springGlobalConfiguration;

    @Override
    protected List<ApiConfiguration> createApiConfigurationsFromServletConfigurationApi(ServletConfig configuration) {
	return new ArrayList<ApiConfiguration>();
    }

    @Override
    protected GlobalConfiguration createGlobalConfiguration(ServletConfig configuration) {
	ServletUtils.checkRequiredParameters(configuration, GlobalParameters.PROVIDERS_URL);
	String providersUrl = ServletUtils.getRequiredParameter(configuration, GlobalParameters.PROVIDERS_URL);

	// this.springGlobalConfiguration = new SpringGlobalConfiguration();

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
    protected void performCustomRegistryConfiguration(ServletConfig configuration, Registry registry) {
	springGlobalConfiguration.performCustomRegistryConfiguration(registry, configuration);
    }

}
