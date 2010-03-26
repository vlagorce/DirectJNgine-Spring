package com.extjs.djn.ioc.servlet;

import java.util.List;

import javax.servlet.ServletConfig;

import com.extjs.djn.ioc.conf.IGlobalConfigurationManager;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.ApiConfiguration;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;
import com.softwarementors.extjs.djn.router.dispatcher.Dispatcher;

public abstract class BaseIOCDirectJNgineServlet extends BaseDirectJNgineServlet {

    private IGlobalConfigurationManager configurationManager;

    public void setConfigurationManager(IGlobalConfigurationManager configurationManager) {
	this.configurationManager = configurationManager;
    }

    @Override
    protected GlobalConfiguration createGlobalConfiguration(ServletConfig configuration) {
	return configurationManager.getGlobalConfiguration();
    }

    protected Dispatcher createDispatcher(Class<? extends Dispatcher> cls) {
	return configurationManager.getDispatcher();
    }

    @Override
    protected List<ApiConfiguration> createApiConfigurationsFromServletConfigurationApi(ServletConfig configuration) {
	return configurationManager.getApiConfigurations();
    }

    @Override
    protected void performCustomRegistryConfiguration(Registry registry) {
	configurationManager.performCustomRegistryConfiguration(registry, getServletConfig());
    }

}
