package com.extjs.djn.spring.registry;

import javax.servlet.ServletConfig;

import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.servlet.RegistryConfigurator;

public interface ISpringRegistryConfigurator extends RegistryConfigurator {
	public void configure(Registry registry, ServletConfig config);
}
