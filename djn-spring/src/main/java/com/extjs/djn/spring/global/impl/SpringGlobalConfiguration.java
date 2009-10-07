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
 * Configuration container
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

    public RegistryConfigurator getRegistryConfigurator() {
        return registryConfigurator;
    }

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
