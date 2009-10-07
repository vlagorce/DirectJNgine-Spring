package com.softwarementors.extjs.djn.test;

import java.util.Map;

public class CustomRegistryConfiguratorHandlingTest {
  public String test_programmaticMethod( String value ) {
    if( !value.equals( "programmatic")) {
      throw new DirectTestFailedException( "We expected to receive 'programmatic' as value");
    }
    
    return value;
  }
  
  public String test_programmaticPollMethod( Map<String,String> parameters ) {
    assert parameters != null;
    
    if( parameters.size() != 1 || !parameters.containsKey("myParameter") || !parameters.get("myParameter").equals("myValue")) {
      throw new DirectTestFailedException( "We expected to receive 'myParameter' with a value of 'myValue'");
    }
    
    return "ok";
  }
}
