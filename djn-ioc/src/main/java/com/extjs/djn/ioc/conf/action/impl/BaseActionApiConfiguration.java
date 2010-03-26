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
package com.extjs.djn.ioc.conf.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.extjs.djn.ioc.conf.action.IActionApiConfiguration;
import com.extjs.djn.ioc.conf.action.IDirectAction;
import com.softwarementors.extjs.djn.StringUtils;
import com.softwarementors.extjs.djn.config.ApiConfiguration;

/**
 * Default implementation of DirectAction api configuration
 * 
 * @author vlagorce
 * 
 * @param <A>
 */
public class BaseActionApiConfiguration<A extends IDirectAction> implements IActionApiConfiguration<A> {

    /**
     *(Optional) ActionNameSpace use for DirectAction defined in this ActionApi
     */
    private String actionsNamespace = "";

    /**
     *(Optional) The file name use for generation js api file.
     * 
     * If null he will be generated from apiName
     */
    private String apiFileName;

    /**
     *(Optional) The folder where the js file will be generated
     */
    private String apiFolder;

    /**
     *(Mandatory) The name of the api
     */
    private String apiName;

    /**
     *(Optional) ApiNamespace used for this ActionApi
     */
    private String apiNamespace;

    /**
     * (Optional)The full path of the js api file (with extension)
     * 
     * If null will generated with apiFolder + (apiFileName or apiName)
     */
    private String apiRelativPathFile;

    /**
     * List of action related to this api-js
     */
    private List<A> listActions;

    public ApiConfiguration createApiConfiguration(ServletContext context) {
	assert apiName != null;

	if (StringUtils.isEmpty(apiRelativPathFile)) {
	    StringBuilder sbRelativPathFile = new StringBuilder();
	    if (!StringUtils.isEmpty(apiFolder)) {
		sbRelativPathFile.append(apiFolder);
	    }
	    if (StringUtils.isEmpty(apiFileName)) {
		StringBuilder fileName = new StringBuilder();
		fileName.append(apiName);
		fileName.append("-api.js");
		apiFileName = fileName.toString();
	    }
	    if (sbRelativPathFile.lastIndexOf("/") != sbRelativPathFile.length()) {
		sbRelativPathFile.append("/");
	    }
	    sbRelativPathFile.append(apiFileName);
	    apiRelativPathFile = sbRelativPathFile.toString();
	}

	if (StringUtils.isEmpty(apiNamespace)) {
	    apiNamespace = apiName;
	}

	String apiFullPathFile = context.getRealPath(apiRelativPathFile);

	return new ApiConfiguration(apiName, apiFullPathFile, apiNamespace, actionsNamespace, getListActionClass());
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

    /**
     * @return the actionsNamespace
     */
    protected String getActionsNamespace() {
	return this.actionsNamespace;
    }

    protected String getApiFolder() {
	return apiFolder;
    }

    /**
     * @return the apiName
     */
    protected String getApiName() {
	return this.apiName;
    }

    /**
     * @return the apiNamespace
     */
    protected String getApiNamespace() {
	return this.apiNamespace;
    }

    protected List<Class<?>> getListActionClass() {
	List<Class<?>> classes = new ArrayList<Class<?>>(listActions.size());

	for (A directAction : getListActions()) {
	    classes.add(directAction.getClass());
	}

	return classes;
    }

}
