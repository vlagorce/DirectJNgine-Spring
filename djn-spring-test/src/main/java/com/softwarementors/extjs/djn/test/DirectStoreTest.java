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

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class DirectStoreTest {
  public static class DirectStoreResult {
    @SuppressWarnings("unused") // Not used in Java code, but used by JS client!
    private int rowCount;

    @SuppressWarnings("unused") // Not used in Java code, but used by JS client!
    private Output[] items;
    
    public DirectStoreResult( Output[] result, int totalRows ) {
      assert result != null;
      
      this.rowCount = totalRows;
      this.items = result;
    }
  }
  
  public static class Output {
    @SuppressWarnings("unused") // Not used in Java code, but used by JS client!
    private long id;

    @SuppressWarnings("unused") // Not used in Java code, but used by JS client!
    private String name;
  }

  @DirectMethod
  public Output[] djn_test_load() {
    Output[] result = new Output[2];
    result[0] = new Output();
    result[0].id = 99;
    result[0].name = "name1";

    result[1] = new Output();
    result[1].id = 100;
    result[1].name = "name2";
    
    /*
    DirectStoreResult r = new DirectStoreResult( result, 2);
    return r;
    */
    return result;
  }
  
  @DirectMethod
  public DirectStoreResult djn_test_load2() {
    Output[] result = new Output[2];
    result[0] = new Output();
    result[0].id = 99;
    result[0].name = "name1";

    result[1] = new Output();
    result[1].id = 100;
    result[1].name = "name2";
    
    DirectStoreResult r = new DirectStoreResult( result, 347);
    return r;
  }
  
  @DirectMethod
  public DirectStoreResult djn_test_loadWithArguments( String argFromBaseParams, int argPassedInLoadCall, boolean argPassedInBeforeLoadEvent ) {
    if( !argFromBaseParams.equals("aValue") || argPassedInLoadCall != 34 || argPassedInBeforeLoadEvent ) {
      throw new DirectTestFailedException( "Did no receive expected values");
    }
    
    Output[] result = new Output[2];
    result[0] = new Output();
    result[0].id = 99;
    result[0].name = "name1";

    result[1] = new Output();
    result[1].id = 100;
    result[1].name = "name2";
    
    DirectStoreResult r = new DirectStoreResult( result, 347);
    return r;
  }
  
  @DirectMethod
  public DirectStoreResult djn_test_loadWithArgumentsWithDirectJsonHandling( JsonArray arrayData ) {
    assert arrayData != null;

    JsonObject data = (JsonObject)arrayData.get(0);
    String argFromBaseParams = data.getAsJsonPrimitive("argFromBaseParams").getAsString();
    int argPassedInLoadCall = data.getAsJsonPrimitive("argPassedInLoadCall").getAsInt();
    boolean argPassedInBeforeLoadEvent = data.getAsJsonPrimitive("argPassedInBeforeLoadEvent").getAsBoolean();
    
    if( !argFromBaseParams.equals("aValue") || argPassedInLoadCall != 34 || argPassedInBeforeLoadEvent ) {
      throw new DirectTestFailedException( "Did no receive expected values");
    }
    
    Output[] result = new Output[2];
    result[0] = new Output();
    result[0].id = 99;
    result[0].name = "name1";

    result[1] = new Output();
    result[1].id = 100;
    result[1].name = "name2";
    
    DirectStoreResult r = new DirectStoreResult( result, 347);
    return r;
  }
  
  /*
  @DirectMethod
  public DirectStoreResult test_paramsAsHashSetToTrue( Map<String,String> parameters) {
    assert parameter != null; 
    
    return djn_test_load2( );
  }
  */
  
  public static class Parameter {
    String param;
    String value;
    
    public static Map<String, String> toMap( Parameter[] parameters ) {
      assert parameters != null;
      
      Map<String, String> result = new HashMap<String,String>(parameters.length);
      for( Parameter parameter : parameters ) {
        result.put( parameter.param, parameter.value );
      }
      
      return result;
    }
  }
  
  
  @DirectMethod
  public DirectStoreResult test_simulatePassingDynamicParams( int param1, boolean param2, Parameter[] parameters) {
    assert parameters != null;
    
    Map<String, String> params = Parameter.toMap(parameters);
    String dyn1Value = params.get("dyn1");
    String dyn2Value = params.get("dyn2");
    if( param1 != 98 ) {
      throw new DirectTestFailedException( "Expected value to be 98");
    }
    if( !param2 ) {
      throw new DirectTestFailedException( "Expected param2 to be true");
    }
    if( !"55".equals(dyn1Value)) {
      throw new DirectTestFailedException( "Expected dynamic param 'dyn1' to be '55'");
    }
    if( !"dyn2Value".equals(dyn2Value)) {
      throw new DirectTestFailedException( "Expected dynamic param 'dyn2' to be 'dyn2Value'");
    }
    
    Output[] result = new Output[2];
    result[0] = new Output();
    result[0].id = 99;
    result[0].name = "name1";

    result[1] = new Output();
    result[1].id = 100;
    result[1].name = "name2";
    
    DirectStoreResult r = new DirectStoreResult( result, 347);
    return r;    
  }
}
