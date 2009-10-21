
DjnSpring.AutowiredTest = {
  testClassName: 'AutowiredTest',
  
  test_methodAT : function() {
	AutowiredTest.test_methodAT(function(result, response){
      Djn.Test.checkSuccessfulResponse("test_methodAT", response, result === true);
    });
  }
}

DjnSpring.FullAutowiredTest = {
  testClassName: 'FullAutowiredTest',
  
  test_methodFAT : function() {
	FullAutowiredTest.test_methodFAT(function(result, response){
      Djn.Test.checkSuccessfulResponse("test_methodFAT", response, result === true);
    });
  }
}



function registerSpringRemotingProvider() {  
	var remotingProvider = Ext.Direct.addProvider( DjnSpring.test.REMOTING_API)
	// VERY IMPORTANT: this is for debugging purposes, set validateCalls to false if this causes problems
    Djn.RemoteCallSupport.addCallValidation(remotingProvider);
    Djn.RemoteCallSupport.validateCalls = true;
	return remotingProvider;
  }