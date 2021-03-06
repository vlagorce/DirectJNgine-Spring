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
package com.extjs.djn.spring.dispatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.extjs.djn.ioc.conf.action.IDirectAction;
import com.extjs.djn.ioc.dispatcher.BaseIOCDispatcher;

/**
 * Dispatcher used to retrieve DirectAction
 * 
 * @author vlagorce
 * 
 */
@Component
public class SpringDispatcher extends BaseIOCDispatcher {

    public SpringDispatcher() {
    }

    @Autowired(required = false)
    @Override
    public void setActionApiConfigurations(List<IDirectAction> directActions) {
	super.setActionApiConfigurations(directActions);
    }
}
