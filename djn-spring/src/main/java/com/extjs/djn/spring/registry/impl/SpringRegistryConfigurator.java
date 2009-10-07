package com.extjs.djn.spring.registry.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;

import org.springframework.util.CollectionUtils;

import com.extjs.djn.spring.action.IDirectAction;
import com.extjs.djn.spring.action.conf.IActionApiConfiguration;
import com.extjs.djn.spring.registry.ISpringRegistryConfigurator;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.ApiConfiguration;
import com.softwarementors.extjs.djn.scanner.Scanner;
import com.softwarementors.extjs.djn.servlet.RegistryConfigurator;

public class SpringRegistryConfigurator implements ISpringRegistryConfigurator {

    private List<IActionApiConfiguration<IDirectAction>> actionApiConfigurations;

    private RegistryConfigurator configurator;

    public SpringRegistryConfigurator() {

    }

    public void configure(Registry registry, ServletConfig config) {
	if (!CollectionUtils.isEmpty(actionApiConfigurations)) {
	    List<ApiConfiguration> apiConfigurations = new ArrayList<ApiConfiguration>(actionApiConfigurations.size());
	    for (IActionApiConfiguration<IDirectAction> actionApiConfiguration : actionApiConfigurations) {
		apiConfigurations.add(actionApiConfiguration.createApiConfiguration(config.getServletContext()));
	    }
	    Scanner scanner = new Scanner(registry);
	    scanner.scanAndRegisterApiConfigurations(apiConfigurations);
	}
	if (configurator != null) {
	    configurator.configure(registry, config);
	}

    }

    public void setActionApiConfigurations(List<IActionApiConfiguration<IDirectAction>> actionApiConfigurations) {
	this.actionApiConfigurations = actionApiConfigurations;
    }

    public void setConfigurator(RegistryConfigurator configurator) {
	this.configurator = configurator;
    }

}
