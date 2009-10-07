package com.softwarementors.extjs.djn.test.config;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletConfig;

import com.softwarementors.extjs.djn.StringUtils;
import com.softwarementors.extjs.djn.api.RegisteredAction;
import com.softwarementors.extjs.djn.api.RegisteredApi;
import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.servlet.RegistryConfigurator;
import com.softwarementors.extjs.djn.test.CustomRegistryConfiguratorHandlingTest;

public class RegistryConfiguratorForTesting implements RegistryConfigurator {

  private Method getMethod( Class<?> cls, String name, Class<?> parameterTypes) {
    assert cls != null;
    assert !StringUtils.isEmpty(name);

    try {
      Method m = cls.getMethod(name, parameterTypes);
      return m;
    }
    catch (SecurityException e) {
      // Do not do this in production quality code!
      throw new RuntimeException(e);
    }
    catch (NoSuchMethodException e) {
      // Do not do this in production quality code!
      throw new RuntimeException(e);
    }
  }
  
  public void configure(Registry registry, ServletConfig config) {
    assert registry != null;
    assert config != null;
    
    // Create a new api programmatically
    String apiFile = config.getServletContext().getRealPath("test/ProgrammaticApi.js");
    RegisteredApi api = registry.addApi( "programmaticApi", apiFile, "Djn.programmaticNamespace", "Djn.programmaticNamespace" );
    
    // Register a new action with a method
    RegisteredAction action = api.addAction( CustomRegistryConfiguratorHandlingTest.class, "MyCustomRegistryConfiguratorHandlingTest");
    Method m = getMethod( CustomRegistryConfiguratorHandlingTest.class, "test_programmaticMethod", String.class);
    action.addMethod( "myProgrammaticMethod", m, false); 
    
    // Register a poll method
    Method pm = getMethod( CustomRegistryConfiguratorHandlingTest.class, "test_programmaticPollMethod", Map.class);
    api.addPollMethod( "myProgrammaticPollMethod",CustomRegistryConfiguratorHandlingTest.class, pm);
  }

}
