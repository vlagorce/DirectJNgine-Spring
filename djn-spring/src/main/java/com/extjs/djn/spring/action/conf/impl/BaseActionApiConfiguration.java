package com.extjs.djn.spring.action.conf.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.extjs.djn.spring.action.IDirectAction;
import com.extjs.djn.spring.action.conf.IActionApiConfiguration;
import com.softwarementors.extjs.djn.config.ApiConfiguration;

public class BaseActionApiConfiguration<A extends IDirectAction> implements IActionApiConfiguration<A> {

    private String apiName;

    private String apiNamespace;

    private String actionsNamespace;

    private List<A> listActions;

    /**
     * The file name use for generation js api file (without extension)
     */
    private String apiFileName;

    /**
     * The full path of the js api file (with extension)
     */
    private String apiRelativPathFile;

    /**
     * The folder where the js file should be generated
     */
    private String apiFolder;

    private ApiConfiguration apiConfiguration;

    public ApiConfiguration createApiConfiguration(ServletContext context) {
	if (apiConfiguration == null) {
	    Assert.notNull(apiName, "apiName is mandatory");

	    if (!StringUtils.hasText(apiRelativPathFile)) {
		StringBuilder sbRelativPathFile = new StringBuilder();
		if (StringUtils.hasText(apiFolder)) {
		    sbRelativPathFile.append(apiFolder);
		}
		if (!StringUtils.hasText(apiFileName)) {
		    StringBuilder fileName = new StringBuilder();
		    fileName.append("/");
		    fileName.append(apiName);
		    fileName.append("-api.js");
		    apiFileName = fileName.toString();
		}
		sbRelativPathFile.append("/"+apiFileName);
		apiRelativPathFile = sbRelativPathFile.toString();
	    }

	    String apiFullPathFile = context.getRealPath(apiRelativPathFile);

	    List<Class<?>> classes = new ArrayList<Class<?>>(listActions.size());
	    for (A directAction : getListActions()) {
		classes.add(directAction.getClass());
	    }

	    apiConfiguration = new ApiConfiguration(getApiName(), apiFullPathFile, getApiNamespace(), getActionsNamespace(), classes);
	}
	return apiConfiguration;
    }

    /**
     * @return the actionsNamespace
     */
    public String getActionsNamespace() {
	if (actionsNamespace == null) {
	    actionsNamespace = "";
	}
	return this.actionsNamespace;
    }

    public String getApiFolder() {
	return apiFolder;
    }

    /**
     * @return the apiName
     */
    public String getApiName() {
	return this.apiName;
    }

    /**
     * @return the apiNamespace
     */
    public String getApiNamespace() {
	if (apiNamespace == null) {
	    apiNamespace = "";
	}
	return this.apiNamespace;
    }

    /**
     * @return the listActions
     */
    public List<A> getListActions() {
	return this.listActions;
    }

    /**
     * @param actionsNamespace
     *            the actionsNamespace to set
     */
    public void setActionsNamespace(String actionsNamespace) {
	this.actionsNamespace = actionsNamespace;
    }

    public void setApiFileName(String apiFileName) {
	this.apiFileName = apiFileName;
    }

    public void setApiFolder(String apiFolder) {
	this.apiFolder = apiFolder;
    }

    /**
     * @param apiName
     *            the apiName to set
     */
    public void setApiName(String apiName) {
	this.apiName = apiName;
    }

    /**
     * @param apiNamespace
     *            the apiNamespace to set
     */
    public void setApiNamespace(String apiNamespace) {
	this.apiNamespace = apiNamespace;
    }

    public void setApiRelativPathFile(String apiRelativPathFile) {
	this.apiRelativPathFile = apiRelativPathFile;
    }

    /**
     * @param listActions
     *            the listActions to set
     */
    public void setListActions(List<A> listActions) {
	this.listActions = listActions;
    }

}
