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

import java.util.Date;

import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class CustomGsonBuilderHandlingTest {

  @SuppressWarnings("deprecation") // Unfortunately Date has many deprecated methods
  @DirectMethod
  public boolean djn_test_specialDateDeserialization(Date date) {
    assert date != null;
    
   // Date stores years as realYears - 1900
   // Date stores month as realMonth-1 (first month is 0!)    
    if( date.getYear() != (2005 -1900) || date.getMonth() != (3-1) || date.getDate() != 20 ) {
      throw new DirectTestFailedException( "Expected to receive a date corresponding to May 10, 2005");
    }
    return true;
  }
  
  @SuppressWarnings("deprecation") // Unfortunately Date has many deprecated methods
  @DirectMethod
  public Date djn_test_specialDateSerialization() {
    // Date stores years as realYears - 1900
    // Date stores month as realMonth-1 (first month is 0!)    
    return new Date( 2007-1900, 5-1, 29 );
  }
  
}
