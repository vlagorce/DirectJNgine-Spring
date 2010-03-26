package com.extjs.djn.ioc.conf;

import java.util.List;

import javax.servlet.ServletConfig;

import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.ApiConfiguration;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;
import com.softwarementors.extjs.djn.router.dispatcher.Dispatcher;

public interface IGlobalConfigurationManager {

    void performCustomRegistryConfiguration(Registry registry, ServletConfig configuration);

    List<ApiConfiguration> getApiConfigurations();

    Dispatcher getDispatcher();

    GlobalConfiguration getGlobalConfiguration();
}
