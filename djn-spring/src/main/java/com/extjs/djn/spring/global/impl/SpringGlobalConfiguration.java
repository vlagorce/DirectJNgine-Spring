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
package com.extjs.djn.spring.global.impl;

import java.util.List;

import javax.servlet.ServletConfig;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.extjs.djn.spring.action.IDirectAction;
import com.extjs.djn.spring.action.conf.IActionApiConfiguration;
import com.extjs.djn.spring.global.ISpringGlobalConfiguration;
import com.extjs.djn.spring.registry.impl.SpringRegistryConfigurator;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;
import com.softwarementors.extjs.djn.gson.GsonBuilderConfigurator;
import com.softwarementors.extjs.djn.servlet.RegistryConfigurator;

/**
 * Spring Configuration container
 * 
 * @author vlagorce
 */
public class SpringGlobalConfiguration implements ISpringGlobalConfiguration, InitializingBean {

    @Autowired(required = false)
    private List<IActionApiConfiguration<IDirectAction>> actionApiConfigurations;

    private int batchRequestsMaxThreadsPerRequest = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_MAX_THREADS_PER_REQUEST;

    private int batchRequestsMaxThreadsPoolSize = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_MIN_THREAD_POOL_SIZE;

    private int batchRequestsMinThreadsPoolSize = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_MIN_THREAD_POOL_SIZE;

    private boolean batchRequestsMultithreadingEnabled = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_MULTITHREADING_ENABLED_VALUE;

    private int batchRequestsThreadKeepAliveSeconds = GlobalConfiguration.DEFAULT_BATCH_REQUESTS_THREAD_KEEP_ALIVE_SECONDS;

    private boolean debug = false;

    private Class<? extends GsonBuilderConfigurator> gsonBuilderConfiguratorClass = GlobalConfiguration.DEFAULT_GSON_BUILDER_CONFIGURATOR_CLASS;

    private boolean minify = GlobalConfiguration.DEFAULT_MINIFY_VALUE;

    @Autowired(required = false)
    private RegistryConfigurator registryConfigurator;

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    /**
     * return the globalConfiguration using by DirectJNgine api
     * 
     * @param providerUrl
     * @return
     */
    public GlobalConfiguration createGlobalConfiguration(String providerUrl) {

	return new GlobalConfiguration(providerUrl, debug, gsonBuilderConfiguratorClass, SpringRegistryConfigurator.class, minify,
		batchRequestsMultithreadingEnabled, batchRequestsMinThreadsPoolSize, batchRequestsMaxThreadsPoolSize,
		batchRequestsThreadKeepAliveSeconds, batchRequestsMaxThreadsPerRequest);

    }

    public List<IActionApiConfiguration<IDirectAction>> getActionApiConfigurations() {
	return actionApiConfigurations;
    }

    /**
     * @return the batchRequestsMaxThreadsPerRequest
     */
    public int getBatchRequestsMaxThreadsPerRequest() {
	return batchRequestsMaxThreadsPerRequest;
    }

    /**
     * @return the batchRequestsMaxThreadsPoolSize
     */
    public int getBatchRequestsMaxThreadsPoolSize() {
	return batchRequestsMaxThreadsPoolSize;
    }

    /**
     * @return the batchRequestsMinThreadsPoolSize
     */
    public int getBatchRequestsMinThreadsPoolSize() {
	return batchRequestsMinThreadsPoolSize;
    }

    /**
     * @return the batchRequestsThreadKeepAliveSeconds
     */
    public int getBatchRequestsThreadKeepAliveSeconds() {
	return batchRequestsThreadKeepAliveSeconds;
    }

    public Class<? extends GsonBuilderConfigurator> getGsonBuilderConfiguratorClass() {
	return gsonBuilderConfiguratorClass;
    }

    public RegistryConfigurator getRegistryConfigurator() {
	return registryConfigurator;
    }

    /**
     * @return the batchRequestsMultithreadingEnabled
     */
    public boolean isBatchRequestsMultithreadingEnabled() {
	return batchRequestsMultithreadingEnabled;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
	return debug;
    }

    /**
     * @return the minify
     */
    public boolean isMinify() {
	return minify;
    }

    @Override
    public void performCustomRegistryConfiguration(Registry registry, ServletConfig configuration) {

	SpringRegistryConfigurator springRegistryConfigurator = new SpringRegistryConfigurator();

	springRegistryConfigurator.setActionApiConfigurations(actionApiConfigurations);

	if (this.registryConfigurator != null) {
	    springRegistryConfigurator.setConfigurator(this.registryConfigurator);
	}

	springRegistryConfigurator.configure(registry, configuration);
    }

    public void setActionApiConfigurations(List<IActionApiConfiguration<IDirectAction>> actionApiConfigurations) {
	this.actionApiConfigurations = actionApiConfigurations;
    }

    /**
     * @param batchRequestsMaxThreadsPerRequest
     *            the batchRequestsMaxThreadsPerRequest to set
     */
    public void setBatchRequestsMaxThreadsPerRequest(int batchRequestsMaxThreadsPerRequest) {
	this.batchRequestsMaxThreadsPerRequest = batchRequestsMaxThreadsPerRequest;
    }

    /**
     * @param batchRequestsMaxThreadsPoolSize
     *            the batchRequestsMaxThreadsPoolSize to set
     */
    public void setBatchRequestsMaxThreadsPoolSize(int batchRequestsMaxThreadsPoolSize) {
	this.batchRequestsMaxThreadsPoolSize = batchRequestsMaxThreadsPoolSize;
    }

    /**
     * @param batchRequestsMinThreadsPoolSize
     *            the batchRequestsMinThreadsPoolSize to set
     */
    public void setBatchRequestsMinThreadsPoolSize(int batchRequestsMinThreadsPoolSize) {
	this.batchRequestsMinThreadsPoolSize = batchRequestsMinThreadsPoolSize;
    }

    /**
     * @param batchRequestsMultithreadingEnabled
     *            the batchRequestsMultithreadingEnabled to set
     */
    public void setBatchRequestsMultithreadingEnabled(boolean batchRequestsMultithreadingEnabled) {
	this.batchRequestsMultithreadingEnabled = batchRequestsMultithreadingEnabled;
    }

    /**
     * @param batchRequestsThreadKeepAliveSeconds
     *            the batchRequestsThreadKeepAliveSeconds to set
     */
    public void setBatchRequestsThreadKeepAliveSeconds(int batchRequestsThreadKeepAliveSeconds) {
	this.batchRequestsThreadKeepAliveSeconds = batchRequestsThreadKeepAliveSeconds;
    }

    /**
     * @param debug
     *            the debug to set
     */
    public void setDebug(boolean debug) {
	this.debug = debug;
    }

    public void setGsonBuilderConfiguratorClass(Class<? extends GsonBuilderConfigurator> gsonBuilderConfiguratorClass) {
	this.gsonBuilderConfiguratorClass = gsonBuilderConfiguratorClass;
    }

    /**
     * @param minify
     *            the minify to set
     */
    public void setMinify(boolean minify) {
	this.minify = minify;
    }

    public void setRegistryConfigurator(RegistryConfigurator registryConfigurator) {
	this.registryConfigurator = registryConfigurator;
    }

    public void setRegistryConfigurator(SpringRegistryConfigurator registryConfigurator) {
	this.registryConfigurator = registryConfigurator;
    }

}
