/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agulló Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'DjnSpring.test');

DjnSpring.test.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/' + (window.location.pathname.split('/')[1]) + '/' + 'djn/directprovider';

DjnSpring.test.POLLING_URLS = {
}

DjnSpring.test.REMOTING_API = {
  url: DjnSpring.test.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    AutowiredTest: [
      {
        name: 'test_methodAT'/*() => boolean */,
        len: 0,
        formHandler: false
      }
    ],
    FullAutowiredTest: [
      {
        name: 'test_methodFAT'/*() => boolean */,
        len: 0,
        formHandler: false
      }
    ]
  }
}

