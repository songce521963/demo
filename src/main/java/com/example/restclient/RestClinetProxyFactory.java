package com.example.restclient;

import java.lang.reflect.Proxy;

public class RestClinetProxyFactory {

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<T> restClientInterface, RestClinetProxy restClinetProxy) {
		return (T) Proxy.newProxyInstance(restClientInterface.getClassLoader(),
				new Class[] { restClientInterface },
				restClinetProxy);
	}

}
