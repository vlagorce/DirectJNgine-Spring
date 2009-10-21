

Before running the demo 

• paste the extjs 3.0.x js api to the webapp/extjs

• paste the djn folder in webapp/djn
	You can find this file in WebContent in directjngine.1.1.zip.

• paste ejn folder in webapp/ejn
	You can find this file in WebContent in directjngine.1.1.zip.

• paste test folder in webapp/test
	You can find this file in WebContent in directjngine.1.1.zip.

• paste index.html in webapp/
	You can find this file in WebContent in directjngine.1.1.zip.
	
	
Modify webapp\test\DjnTests.html

• Add just before the </head> tag.
	
	<script type="text/javascript" src="../testSpring/DjnSpringTestApi.js"></script> 
	<script type="text/javascript" src="../testSpring/djn-spring-tests.js"></script> 

. Add the following instruction in the js function registerProvider() 

	Ext.Direct.addProvider( DjnSpring.test.REMOTING_API);
	
• Add in the js function runTests() the following declaration

	DjnSpring.AutowiredTest
	DjnSpring.FullAutowiredTest


   