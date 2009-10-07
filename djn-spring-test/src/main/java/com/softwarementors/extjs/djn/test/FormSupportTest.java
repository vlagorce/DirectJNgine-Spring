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

import org.apache.commons.fileupload.FileItem;

import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;

public class FormSupportTest {

  public boolean djnform_test_formPostForNonAnnotatedMethod( Map<String, String> formParameters, Map<String, FileItem> fileFields )  {
    assert formParameters != null;
    assert fileFields != null;

    return true;
  }

  
  @DirectFormPostMethod
  public String djnform_test_handleForm( Map<String, String> formParameters, Map<String, FileItem> fileFields )  {
    assert formParameters != null;
    assert fileFields != null;

    String input1Value = formParameters.get( "input1");    
    String input2Value = formParameters.get( "input2");    
    if( formParameters.size() != 2 || !input1Value.equals("value=&1") || !input2Value.equals("") ) {
      throw new DirectTestFailedException( "Unexpected error receiving parameters");
    }
    
    return input1Value;
  }

  @DirectFormPostMethod
  public boolean djnform_test_handleFormCausingServerException( Map<String, String> formParameters, Map<String, FileItem> fileFields ) {
    assert formParameters != null;
    assert fileFields != null;

    class MyServerException extends RuntimeException { /* Do nothing */ }
    
    throw new MyServerException(); 
  }
}
