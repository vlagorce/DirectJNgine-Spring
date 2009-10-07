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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class ServerMethodParametersReceptionTest {

  public ServerMethodParametersReceptionTest() {
    // Do nothing
  }
  
  @DirectMethod
  public boolean test_handleJsonDataMethod( JsonArray data ) {
    assert data != null;
    
    if( data.size() != 1) {
      throw new DirectTestFailedException( "We expected a json array with just one element");
    }
    
    JsonElement element = data.get(0);
    if( !element.isJsonPrimitive()) {
      throw new DirectTestFailedException( "We expected the first json item to be a json primitive");
    }
   
    JsonPrimitive primitive = (JsonPrimitive)element;
    if( !primitive.isBoolean()) {
      throw new DirectTestFailedException( "We expected a primitive json boolean element");
    }
    if( primitive.getAsBoolean()) {
      throw new DirectTestFailedException( "We expected a false value");
    }
    
    return primitive.getAsBoolean();
  }
  
  @DirectMethod
  @SuppressWarnings("unused") // Even though private, we call this via introspection!
  private boolean djn_test_privateMethodCall() {
    return true;
  }
  
  @SuppressWarnings("unused") // Even though private, we call this via introspection!
  @DirectMethod
  private static boolean djn_test_privateStaticMethodCall() {
    return true;
  }
  
  @DirectMethod
  public String djn_test_serverReceivingNoArguments() {
    return "noArgumentsMethod called";
  }
  
  @DirectMethod
  public int djn_test_serverReceivingOnePrimitiveArgument( int value ) {
    return value + 1;
  }
  
  @DirectMethod
  public String djn_test_serverReceivingPrimitiveAndString( int arg1, String arg2 ) {
    return arg2 + "&&" + arg1;
  }
  
  public String djn_test_serverCallForNonAnnotatedMethod( int arg1, String arg2 ) {
    return arg2 + "&&" + arg1;
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingOneNullStringArgument( String arg) {
    return arg == null;
  }

  @DirectMethod
  public boolean djn_test_serverReceivingFunctionInParameter( String arg ) {
    assert arg != null;
    
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending an Javascript 'function'");
  }
  
  @DirectMethod
  public String djn_test_serverReceivingUniqueParameterUndefined( String arg) {
    assert arg != null;
    
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending an 'undefined'");
  }
  
  @DirectMethod
  public String djn_test_serverReceivingSeveralParametersWithTheLastOneUndefined( String arg1, String arg2, String arg3) {
    assert arg1 != null;
    assert arg2 != null;
    assert arg3 != null;
    
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending an 'undefined'");
  }
  
  @DirectMethod
  public String djn_test_serverReceivingSeveralParametersOneOfThemUndefined( String arg1, String arg2, String arg3) {
    assert arg1 != null;
    assert arg2 != null;
    assert arg3 != null;
    
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending an 'undefined'");
  }
  
  @DirectMethod
  public String djn_test_serverReceivingUniqueParameterNull( String arg) {
    if( arg != null )
      return "Expected arg==null";
    return "ok";
  }
  
  @DirectMethod
  public String djn_test_serverReceivingSeveralParametersWithTheLastOneNull( String arg1, String arg2, String arg3) {
    assert arg1 != null;
    assert arg2 != null;
    
    if( arg3 != null )
      return "Expected arg3==null";
    return "ok";
  }
  
  @DirectMethod
  public String djn_test_serverReceivingSeveralParametersOneOfThemNull( String arg1, String arg2, String arg3) {
    assert arg1 != null;
    assert arg3 != null;
    
    if( arg2 != null )
      return "Expected arg2==null";
    return "ok";
  }
  
  @DirectMethod
  public String djn_test_serverReceivingParametersOfAllPrimitiveAndWrapperTypesExceptLongCorrectly( byte b, short s, int i, float f, double d, boolean bo, char c, 
                                               Byte b2, Short s2, Integer i2, Float f2, Double d2, Boolean bo2, Character c2, Number n) {
    String result = "";
    if( b != Byte.MAX_VALUE )
      result += "byte, ";
    if( s != Short.MAX_VALUE )
      result += "short, ";
    if( i != Integer.MAX_VALUE )
      result += "int, ";
    if( f != Float.MAX_VALUE )
      result += "float, ";
    if( d != Double.MAX_VALUE )
      result += "double, ";
    if( bo != true )
      result += "boolean, ";
    if( c != 'a' )
      result += "char, ";    
    if( b2.byteValue() != Byte.MIN_VALUE )
      result += "Byte, ";
    if( s2.shortValue() != Short.MIN_VALUE )
      result += "Short, ";
    if( i2.intValue() != Integer.MIN_VALUE )
      result += "Integer, ";
    if( f2.floatValue() != Float.MIN_VALUE )
      result += "Float, ";
    if( d2.doubleValue() != Double.MIN_VALUE )
      result += "Double, ";
    if( bo2.booleanValue() != false )
      result += "Boolean, ";
    if( c2.charValue() != 'b' )
      result += "Character, ";
    if( n.doubleValue() != Double.MIN_VALUE )
      result += "Number, ";
    
    if( !result.equals(""))
      result = "Problems with types => " + result;
    else
      result = "ok";
    
    return result;
  }
  
  @DirectMethod
  public int djn_test_serverReceivingMoreParametersThanExpected( int value ) {
    return value;
  }
  
  @DirectMethod
  public int djn_test_serverReceivingLessParametersThanExpected( int arg1, String arg2, int arg3 ) {
    assert arg1 != Integer.MAX_VALUE; 
    assert arg2 != null;
    
    return arg3;
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingNonEmptyPrimitiveArray( int[] values) {
    return values != null && values.length == 2 && values[0] == 5 && values[1] == 3;    
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingEmptyPrimitiveArray( int[] values) {
    return values != null && values.length == 0;
  }

  @DirectMethod
  public boolean djn_test_serverReceivingNullPrimitiveArray( int[] values) {
    return values == null;
  }

  @DirectMethod
  public boolean djn_test_serverReceivingNonEmptyStringArray( String[] values) {
    return values != null && values.length == 2 && values[0].equals("value1") && values[1].equals("value2") ;    
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingNonEmptyStringArrayHavingANullValue( String[] values) {
    return values != null && values.length == 3 && values[0].equals("value1") && values[1] == null && values[2].equals("value3") ;    
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingNonEmptyStringArrayHavingAnUndefinedValue( String[] values) {
    assert values != null;
    throw new DirectTestFailedException( "We expected that method should not have been called, as it has 'undefined' values");
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingParametersWithObjectHavingArrayWithUndefinedValue( String fakeArgumentWeShouldNotArriveHere) {
    assert fakeArgumentWeShouldNotArriveHere != null; 
    throw new DirectTestFailedException( "We expected that method should not have been called, as it has 'undefined' values");
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingParametersWithArrayHavingArrayWithUndefinedValue( String fakeArgumentWeShouldNotArriveHere) {
    assert fakeArgumentWeShouldNotArriveHere != null; 
    throw new DirectTestFailedException( "We expected that method should not have been called, as it has 'undefined' values");
  }

  @DirectMethod // Test deep recursion!
  public boolean djn_test_serverReceivingParametersWithArrayHavingObjectHavingArrayWithUndefinedValue( String fakeArgumentWeShouldNotArriveHere) {
    assert fakeArgumentWeShouldNotArriveHere != null; 
    throw new DirectTestFailedException( "We expected that method should not have been called, as it has 'undefined' values");
  }
  
  @DirectMethod // Test deep recursion!
  public boolean djn_test_serverReceivingParametersWithObjectHavingObjectHavingArrayWithUndefinedValue( String fakeArgumentWeShouldNotArriveHere) {
    assert fakeArgumentWeShouldNotArriveHere != null; 
    throw new DirectTestFailedException( "We expected that method should not have been called, as it has 'undefined' values");
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingEmptyStringArray( String[] values) {
    return values != null && values.length == 0;
  }

  @DirectMethod
  public boolean djn_test_serverReceivingNullStringArray( String[] values) {
    return values == null;
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingNullForAPrimitiveArgument( int arg ) {
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending a 'null', but can receive just one int. Value=" + arg);
  }

  @DirectMethod
  public boolean djn_test_serverReceivingUndefinedForAPrimitiveArgument( int arg ) {
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending an 'undefined', but can receive just one int. Value=" + arg);
  }

  @DirectMethod
  public boolean djn_test_serverReceivingStringForAnIntArgument( int arg ) {
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending an string, but can receive just one int. Value=" + arg);
  }

  @DirectMethod
  public boolean djn_test_serverReceivingStringRepresentingValidIntForAnIntArgument( int arg ) {
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending an string, but can receive just one int. Value=" + arg);
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingOneElementIntArrayForAnIntArgument( int arg ) {
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending an array with just one item, but can receive just one int. Value=" + arg);
  }
  
  @DirectMethod
  public boolean djn_test_serverReceivingMultiElementIntArrayForAnIntArgument( int arg ) {
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending an array of items, but can receive just one int. Value=" + arg);
  }
  
  @DirectMethod
  public char djn_test_serverReceivingCharFromSize1String( char c ) {
    return c;   
  }
  
  @DirectMethod
  public char djn_test_serverReceivingCharFromSize2String( char c ) {
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending a string with two characters, but can receive just one char. Value=" + c);
  }
  
  @DirectMethod
  public char djn_test_serverReceivingCharFromNumberInValidJavaCharRange( Character c ) {
   return c.charValue(); 
  }
  
  @DirectMethod
  public char djn_test_serverReceivingCharFromNumberTooBig( Character c ) {
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending a number that's too big for a char. Value=" + c);
  }
  
  @DirectMethod
  public char djn_test_serverReceivingCharFromNumberTooSmall( Character c ) {
    throw new DirectTestFailedException( "We expected that method should not have been called, we are sending a number that's too small for a char. Value=" + c);
  }
  
  @DirectMethod
  public byte djn_test_serverReceivingByteFromANumberTooBig( Byte b ) {
    return b.byteValue();
  }

  @DirectMethod
  public Byte djn_test_serverReceivingByteFromANumberTooSmall( byte b ) {
    return new Byte(b);
  }
  
  /* 
  @DirectMethod
  public Calendar djn_test_serverReceivingJavascriptDate( Calendar date ) {
    return date;    
  }
  */

  @DirectMethod
  public boolean djn_test_serverReceivingBigDecimal( BigDecimal arg) {
    return arg != null && arg.equals( new BigDecimal("3.2"));
  }

  @DirectMethod
  public boolean djn_test_serverReceivingBigDecimalFromString( BigDecimal arg) {
    return arg != null && arg.equals( new BigDecimal("3.33"));
  }

  @DirectMethod
  public boolean djn_test_serverReceivingBigInteger( BigInteger arg) {
    return arg != null && arg.equals( new BigInteger("92"));
  }

  @DirectMethod
  public boolean djn_test_serverReceivingBigIntegerFromString( BigInteger arg) {
    return arg != null && arg.equals( new BigInteger("88"));
  }

  @DirectMethod
  public boolean djn_test_serverReceivingComplexObject( ComplexObject arg) {
    return arg != null && arg.name.equals( "MyPet") && arg.age == 2;
  }

  @DirectMethod
  public boolean djn_test_serverReceivingVeryComplexObject( VeryComplexObject arg) {
    return arg != null && 
           arg.ints.length == 2 && arg.ints[0].equals(new Integer(33)) && arg.ints[1] == null &&
           arg.myComplexObject != null && arg.myComplexObject.name.equals("MyPet") && arg.myComplexObject.age == 0 &&
           arg.moreComplexObjects != null && arg.moreComplexObjects.length == 2 && 
             arg.moreComplexObjects[0] == null && 
             arg.moreComplexObjects[1] != null && arg.moreComplexObjects[1].name == null && arg.moreComplexObjects[1].age == 5 &&
           arg.notSetInJs == 19; 
  }
  
  @DirectMethod
  public double djn_test_serverReceivingDoubleArray( double[] values ) {
    if( values == null || values.length != 3)  {
      throw new DirectTestFailedException( "We expected a non null array with three doubles");
    }
    double result = 0.0;
    for( double v : values ) {
      result += v;
    }
    
    return result;
  }

  @DirectMethod
  public double djn_test_serverReceivingPrimitiveDoubleArray( Double[] values ) {
    if( values == null || values.length != 3)  {
      throw new DirectTestFailedException( "We expected a non null array with three doubles");
    }
    double result = 0.0;
    for( Double v : values ) {
      if( v != null) {
        result += v.doubleValue();
      }
    }
    
    return result;
  }
  
  /*
  @SuppressWarnings("deprecation") // Unfortunately, Date is not a very "robust" class, and most of its methods are deprecated
  @DirectMethod
  public boolean djn_test_serverReceivingJavascriptDateAsJavaDate( Date date ) {
    if( date == null || date.getYear() != 1915 || date.getMonth() != 5 || date.getDate() != 25 ||
        date.getHours() != 13 || date.getMinutes() != 55 || date.getSeconds() != 18 ) {
         throw new DirectTestFailedException( "We expected a non null array with three doubles");
    }
        
    return true; 
  }
  */
  @DirectMethod
  public boolean djn_test_serverReceivingCallCausingInfiniteRecursionIfParametersRecursivelyChecked( Object o) {
    assert o == o; // To please our very demanding compiler options
    
    return true;
  }
  
  @DirectMethod
  public void djn_test_serverReceivingMap( Map<String,String> values ) {
    if( values == null || values.size() != 2 || !values.get("key1").equals("value1") || !values.containsKey("key2") || values.get("key2") != null )  {
      throw new DirectTestFailedException( "We expected different values");
    }
  }
}
