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
package com.extjs.djn.spring.global;

import java.util.List;

import javax.servlet.ServletConfig;

import com.extjs.djn.spring.action.IDirectAction;
import com.extjs.djn.spring.action.conf.IActionApiConfiguration;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;

/**
 * Spring Configuration container Interface
 * 
 *@author vlagorce
 * 
 */
public interface ISpringGlobalConfiguration {

    GlobalConfiguration createGlobalConfiguration(String providersUrl);

    List<IActionApiConfiguration<IDirectAction>> getActionApiConfigurations();

    void performCustomRegistryConfiguration(Registry registry, ServletConfig configuration);
}
