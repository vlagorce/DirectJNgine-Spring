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
package com.extjs.djn.spring.action.conf.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.extjs.djn.spring.action.IDirectAction;
import com.extjs.djn.spring.action.conf.IActionApiConfiguration;
import com.softwarementors.extjs.djn.config.ApiConfiguration;

/**
 * Default implementation of DirectAction api configuration
 * 
 * @author vlagorce
 * 
 * @param <A>
 */
public class BaseActionApiConfiguration<A extends IDirectAction> implements IActionApiConfiguration<A> {

    private String actionsNamespace;

    private ApiConfiguration apiConfiguration;

    /**
     * The file name use for generation js api file (without extension)
     */
    private String apiFileName;

    /**
     * The folder where the js file should be generated
     */
    private String apiFolder;

    private String apiName;

    private String apiNamespace;

    /**
     * The full path of the js api file (with extension)
     */
    private String apiRelativPathFile;

    private List<A> listActions;

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
		sbRelativPathFile.append("/" + apiFileName);
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
