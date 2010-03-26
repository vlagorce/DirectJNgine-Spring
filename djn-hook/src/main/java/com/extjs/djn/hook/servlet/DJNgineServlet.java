/*
 * Copyright © 2008, 2009 Pedro Agulló Soliveres.
 * 
 * This file is part of DirectJNgine.
 * 
 * DirectJNgine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * 
 * Commercial use is permitted to the extent that the code/component(s)
 * do NOT become part of another Open Source or Commercially developed
 * licensed development library or toolkit without explicit permission.
 * 
 * DirectJNgine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with DirectJNgine. If not, see <http://www.gnu.org/licenses/>.
 * 
 * This software uses the ExtJs library (http://extjs.com), which is
 * distributed under the GPL v3 license (see http://extjs.com/license).
 */
package com.extjs.djn.hook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.ApiConfiguration;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;
import com.softwarementors.extjs.djn.jscodegen.CodeFileGenerator;
import com.softwarementors.extjs.djn.router.processor.standard.form.upload.UploadFormPostRequestProcessor;
import com.softwarementors.extjs.djn.scanner.Scanner;
import com.softwarementors.extjs.djn.servlet.DirectJNgineServlet;
import com.softwarementors.extjs.djn.servlet.ServletRegistryConfigurator;
import com.softwarementors.extjs.djn.servlet.ServletUtils;

public class DJNgineServlet extends DirectJNgineServlet {

    private static final Logger logger = Logger.getLogger(DJNgineServlet.class);

    private GlobalConfiguration globalConfiguration;
    
    private 
    
    @Override
    public void init(ServletConfig configuration) throws ServletException {
	doBeforeInit(configuration);
	super.init(configuration);
    }

    protected void doBeforeInit(ServletConfig configuration) throws ServletException {

    }

    protected void createDirectJNgineRouter(ServletConfig configuration) {
	List<ApiConfiguration> apiConfigurations = createApiConfigurationsFromServletConfigurationApi(configuration);
	Registry registry = new Registry(globalConfiguration);
	Scanner scanner = new Scanner(registry);
	scanner.scanAndRegisterApiConfigurations(apiConfigurations);

	this.uploader = UploadFormPostRequestProcessor.createFileUploader();
	this.processor = createRequestRouter(registry, globalConfiguration);
    }

    protected void performCustomRegistryConfiguration() {


	    performCustomRegistryConfiguration(registryConfiguratorClass, registry, configuration);
    }

    protected void createRegistry() {
	Registry registry = new Registry(globalConfiguration);
    }

    protected void genereateApiFile() {

	try {
	    CodeFileGenerator.updateApiFiles(registry);
	    subtaskTimer.stop();
	    subtaskTimer.logDebugTimeInMilliseconds("Djn initialization: Api Files creation time");
	} catch (IOException ex) {
	    ServletException e = new ServletException("Unable to create DirectJNgine API files", ex);
	    logger.fatal(e.getMessage(), e);
	    throw e;
	}
    }
    
    protected GlobalConfiguration getGlobalConfiguration(){
	if(globalConfiguration == null){
	    
	}
	return globalConfiguration
    }
    
    protected GlobalConfiguration creatGlobalConfiguration(){
	return new Glob
    }
}
