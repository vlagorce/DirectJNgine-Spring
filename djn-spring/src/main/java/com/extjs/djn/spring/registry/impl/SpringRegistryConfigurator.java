/* 
 *   This file is part of DirectJNgine-Spring. Copyright © 2009  vlagorce
 *   
 *   DirectJNgine-Spring is an java Api used to easily configure DirectJNgine with spring.
 *   
 *   DirectJNgine-Spring is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   DirectJNgine-Spring is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with DirectJNgine-Spring.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *   DirectJNgine-Spring uses the ExtJs library (http://extjs.com), which is 
 *   distributed under the GPL v3 license (see http://extjs.com/license).
 *   
 *   DirectJNgine-Spring uses the DirectJNgine api (http://code.google.com/p/directjngine/), which is 
 *   distributed under the GPL v3 license.
 */
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

/**
 * 
 * DirectJNgine Registry configuration.
 * 
 * @author vlagorce
 * 
 */
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
