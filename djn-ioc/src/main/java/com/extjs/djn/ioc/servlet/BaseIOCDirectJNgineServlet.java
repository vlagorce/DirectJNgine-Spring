package com.extjs.djn.ioc.servlet;

import java.util.List;

import javax.servlet.ServletConfig;

import com.extjs.djn.ioc.conf.global.IGlobalConfigurationManager;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.ApiConfiguration;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;
import com.softwarementors.extjs.djn.router.dispatcher.Dispatcher;

public abstract class BaseIOCDirectJNgineServlet extends BaseDirectJNgineServlet {

    public abstract IGlobalConfigurationManager getConfigurationManager();

    protected GlobalConfiguration createGlobalConfiguration(ServletConfig configuration) {
	return getConfigurationManager().getGlobalConfiguration();
    }

    protected Dispatcher createDispatcher(Class<? extends Dispatcher> cls) {
	return getConfigurationManager().getDispatcher();
    }

    protected List<ApiConfiguration> createApiConfigurationsFromServletConfigurationApi(ServletConfig configuration) {
	return getConfigurationManager().getApiConfigurations();
    }

    protected void performCustomRegistryConfiguration(Registry registry) {
	getConfigurationManager().performCustomRegistryConfiguration(registry, getServletConfig());
    }

}
