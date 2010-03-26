package com.extjs.djn.spring.global;

import javax.servlet.ServletConfig;

import com.extjs.djn.ioc.conf.global.IGlobalConfigurationManager;

public interface ISpringGlobalConfigurationManager extends IGlobalConfigurationManager {

    void setServletConfig(ServletConfig servletConfig);
}
