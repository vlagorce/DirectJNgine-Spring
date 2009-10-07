/*
 * Copyright © 2008, 2009 Pedro Agulló Soliveres.
 * 
 * This file is part of DirectJNgine.
 *
 * DirectJNgine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * Commercial use is permitted to the extent that the code/component(s)
 * do NOT become part of another Open Source or Commercially developed
 * licensed development library or toolkit without explicit permission.
 *
 * DirectJNgine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DirectJNgine.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * This software uses the ExtJs library (http://extjs.com), which is 
 * distributed under the GPL v3 license (see http://extjs.com/license).
 */

package com.softwarementors.extjs.djn.test;

import java.util.Map;

import com.softwarementors.extjs.djn.config.annotations.DirectPollMethod;

public class PollTest {
  private static int call = 1;
  
  public String djnpoll_test_pollForNonAnnotatedMethod( Map<String,String> parameters) {
    assert parameters != null;
    
    if( !parameters.isEmpty() ) {
      throw new DirectTestFailedException( "We expected to receive no parameters");
    }
    
    return "Ok " + call++;
  }
  
  @DirectPollMethod
  public String djnpoll_test_pollWithNoBaseParams( Map<String,String> parameters) {
    assert parameters != null;
    
    if( !parameters.isEmpty() ) {
      throw new DirectTestFailedException( "We expected to receive no parameters");
    }
    
    return "Ok " + call++;
  }
  
  @DirectPollMethod
  public String djnpoll_test_pollWithBaseParams( Map<String,String> parameters ) {
    assert parameters != null;
    
    if( parameters.size() != 1 || !parameters.containsKey("arg1") || !parameters.get("arg1").equals("value")) {
      throw new DirectTestFailedException( "We expected to receive one parameters, called arg and with value 'value'");
    }
    
    String result = "";
    for( String key : parameters.keySet()) {
      String value = parameters.get(key);
      result += key + "=" + value;
    }
    
    return result;
  }

  @DirectPollMethod
  public String djnpoll_test_pollCausingServerError( Map<String,String> parameters ) {
    assert parameters != null;
    
    if( !parameters.isEmpty() ) {
      throw new DirectTestFailedException( "We expected to receive no parameters");
    }

    class MyServerException extends RuntimeException { /* Do nothing */ }
    
    throw new MyServerException(); 
  }
}
