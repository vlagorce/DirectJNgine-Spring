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
package com.extjs.djn.ioc.conf.global.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletConfig;

import com.extjs.djn.ioc.conf.action.IActionApiConfiguration;
import com.extjs.djn.ioc.conf.action.IDirectAction;
import com.extjs.djn.ioc.conf.global.IGlobalConfigurationManager;
import com.extjs.djn.ioc.dispatcher.BaseIOCDispatcher;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.ApiConfiguration;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;
import com.softwarementors.extjs.djn.gson.DefaultGsonBuilderConfigurator;
import com.softwarementors.extjs.djn.gson.GsonBuilderConfigurator;
import com.softwarementors.extjs.djn.router.dispatcher.Dispatcher;
import com.softwarementors.extjs.djn.router.processor.standard.json.DefaultJsonRequestProcessorThread;
import com.softwarementors.extjs.djn.router.processor.standard.json.JsonRequestProcessorThread;
import com.softwarementors.extjs.djn.servlet.ServletRegistryConfigurator;
import com.softwarementors.extjs.djn.servlet.ServletUtils;
import com.softwarementors.extjs.djn.servlet.DirectJNgineServlet.GlobalParameters;

/**
 * Spring Configuration container
 * 
 * @author vlagorce
 */
public class GlobalConfigurationManager implements IGlobalConfigurationManager {

    private String contextPath = "";

    private int batchRequestsMaxThreadsPerRequest = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_MAX_THREADS_PER_REQUEST;

    private int batchRequestsMaxThreadsPoolSize = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_MAX_THREAD_POOL_SIZE;

    private int batchRequestsMinThreadsPoolSize = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_MIN_THREAD_POOL_SIZE;

    private boolean batchRequestsMultithreadingEnabled = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_MULTITHREADING_ENABLED_VALUE;

    private int batchRequestsThreadKeepAliveSeconds = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_THREAD_KEEP_ALIVE_SECONDS;

    private boolean debug = false;

    private boolean minify = GlobalConfiguration.DEFAULT_MINIFY_VALUE;

    private List<IActionApiConfiguration<IDirectAction>> actionApiConfigurations;

    private GsonBuilderConfigurator gsonBuilderConfigurator;

    private Dispatcher dispatcher;

    private JsonRequestProcessorThread jsonRequestProcessorThread;

    private ServletRegistryConfigurator registryConfigurator;

    private GlobalConfiguration globalConfiguration;

    private ServletConfig servletConfig;

    /**
     * return the globalConfiguration using by DirectJNgine api
     * 
     * @param providerUrl
     * @return
     */
    public GlobalConfiguration getGlobalConfiguration() {

	String providersUrl = ServletUtils.getRequiredParameter(servletConfig, GlobalParameters.PROVIDERS_URL);
	if (globalConfiguration == null) {
	    // FIXME we give the class name but DJN don't need it in IOC mode.
	    globalConfiguration = new GlobalConfiguration(contextPath, providersUrl, debug, getGsonBuilderConfigurator().getClass(), getJsonRequestProcessorThread().getClass(),
		    getDispatcher().getClass(), minify, batchRequestsMultithreadingEnabled, batchRequestsMinThreadsPoolSize, batchRequestsMaxThreadsPoolSize,
		    batchRequestsThreadKeepAliveSeconds, batchRequestsMaxThreadsPerRequest);
	}
	return globalConfiguration;
    }

    public List<ApiConfiguration> getApiConfigurations() {
	List<ApiConfiguration> apiConfigurations;
	if (actionApiConfigurations != null && actionApiConfigurations.size() > 0) {
	    apiConfigurations = new ArrayList<ApiConfiguration>(actionApiConfigurations.size());
	    for (IActionApiConfiguration<IDirectAction> actionApiConfiguration : actionApiConfigurations) {
		apiConfigurations.add(actionApiConfiguration.createApiConfiguration(servletConfig.getServletContext()));
	    }
	} else {
	    apiConfigurations = Collections.emptyList();
	}
	return apiConfigurations;
    }

    public void performCustomRegistryConfiguration(Registry registry, ServletConfig configuration) {
	if (getRegistryConfigurator() != null) {
	    getRegistryConfigurator().configure(registry, configuration);
	}
    }

    public GsonBuilderConfigurator getGsonBuilderConfigurator() {
	if (gsonBuilderConfigurator == null) {
	    gsonBuilderConfigurator = new DefaultGsonBuilderConfigurator();
	}
	return gsonBuilderConfigurator;
    }

    public Dispatcher getDispatcher() {
	if (dispatcher == null) {
	    dispatcher = new BaseIOCDispatcher();
	}
	return dispatcher;
    }

    public JsonRequestProcessorThread getJsonRequestProcessorThread() {
	if (jsonRequestProcessorThread == null) {
	    jsonRequestProcessorThread = new DefaultJsonRequestProcessorThread();
	}
	return jsonRequestProcessorThread;
    }

    public ServletRegistryConfigurator getRegistryConfigurator() {
	return registryConfigurator;
    }

    public void setServletConfig(ServletConfig servletConfig) {
	this.servletConfig = servletConfig;
    }

    public void setGsonBuilderConfigurator(GsonBuilderConfigurator gsonBuilderConfigurator) {
	this.gsonBuilderConfigurator = gsonBuilderConfigurator;
    }

    public void setDispatcher(Dispatcher dispatcher) {
	this.dispatcher = dispatcher;
    }

    public void setJsonRequestProcessorThread(JsonRequestProcessorThread jsonRequestProcessorThread) {
	this.jsonRequestProcessorThread = jsonRequestProcessorThread;
    }

    public void setContextPath(String contextPath) {
	this.contextPath = contextPath;
    }

    public void setRegistryConfigurator(ServletRegistryConfigurator registryConfigurator) {
	this.registryConfigurator = registryConfigurator;
    }

    public void setActionApiConfigurations(List<IActionApiConfiguration<IDirectAction>> actionApiConfigurations) {
	this.actionApiConfigurations = actionApiConfigurations;
    }

    public void setBatchRequestsMaxThreadsPerRequest(int batchRequestsMaxThreadsPerRequest) {
	this.batchRequestsMaxThreadsPerRequest = batchRequestsMaxThreadsPerRequest;
    }

    public void setBatchRequestsMaxThreadsPoolSize(int batchRequestsMaxThreadsPoolSize) {
	this.batchRequestsMaxThreadsPoolSize = batchRequestsMaxThreadsPoolSize;
    }

    public void setBatchRequestsMinThreadsPoolSize(int batchRequestsMinThreadsPoolSize) {
	this.batchRequestsMinThreadsPoolSize = batchRequestsMinThreadsPoolSize;
    }

    public void setBatchRequestsMultithreadingEnabled(boolean batchRequestsMultithreadingEnabled) {
	this.batchRequestsMultithreadingEnabled = batchRequestsMultithreadingEnabled;
    }

    public void setBatchRequestsThreadKeepAliveSeconds(int batchRequestsThreadKeepAliveSeconds) {
	this.batchRequestsThreadKeepAliveSeconds = batchRequestsThreadKeepAliveSeconds;
    }

    public void setDebug(boolean debug) {
	this.debug = debug;
    }

    public void setMinify(boolean minify) {
	this.minify = minify;
    }

    public void setServletRegistryConfigurator(ServletRegistryConfigurator registryConfigurator) {
	this.registryConfigurator = registryConfigurator;
    }
}
