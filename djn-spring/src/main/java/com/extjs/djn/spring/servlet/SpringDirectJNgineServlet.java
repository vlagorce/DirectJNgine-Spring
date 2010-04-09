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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.extjs.djn.ioc.conf.global.IGlobalConfigurationManager;
import com.extjs.djn.ioc.servlet.BaseIOCDirectJNgineServlet;
import com.extjs.djn.spring.conf.global.ISpringGlobalConfigurationManager;
import com.extjs.djn.spring.conf.global.impl.SpringGlobalConfigurationManager;
import com.extjs.djn.spring.loader.SpringLoaderHelper;

/**
 * DirectJNgine servlet override to used spring object configuration
 * 
 * @author vlagorce
 * 
 */
public class SpringDirectJNgineServlet extends BaseIOCDirectJNgineServlet {

    /** serialVersionUID */
    private static final long serialVersionUID = -4871120248127784841L;

    private ISpringGlobalConfigurationManager springGlobalConfigurationManager;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
	loadSpringGlobalConfigurationManager(servletConfig);
	super.init(servletConfig);
    }

    protected void loadSpringGlobalConfigurationManager(ServletConfig servletConfig) {
	springGlobalConfigurationManager = (ISpringGlobalConfigurationManager) SpringLoaderHelper.getBeanOfType(SpringGlobalConfigurationManager.class);
	springGlobalConfigurationManager.setServletConfig(servletConfig);
    }

    @Override
    public IGlobalConfigurationManager getConfigurationManager() {
	return springGlobalConfigurationManager;
    }

}
