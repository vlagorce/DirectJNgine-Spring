package com.extjs.djn.spring.global;

import java.util.List;

import javax.servlet.ServletConfig;

import com.extjs.djn.spring.action.IDirectAction;
import com.extjs.djn.spring.action.conf.IActionApiConfiguration;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;

public interface ISpringGlobalConfiguration {

    GlobalConfiguration createGlobalConfiguration(String providersUrl);

    List<IActionApiConfiguration<IDirectAction>> getActionApiConfigurations();

    void performCustomRegistryConfiguration(Registry registry, ServletConfig configuration);
}
