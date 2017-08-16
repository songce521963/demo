package com.example.restclient;

import static org.springframework.util.Assert.notNull;

import java.lang.reflect.Method;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.Proxy;

public class RestClinetFactoryBean<T> implements InitializingBean, FactoryBean<T> {

	private Class<?> restClientInterface;

	public RestClinetFactoryBean() {}

	public RestClinetFactoryBean(Class<T> restClientInterface) {
		this.restClientInterface = restClientInterface;
	}

	@SuppressWarnings("unchecked")
	public T getObject() throws Exception {

		notNull(this.restClientInterface, "Property 'restClientInterface' is required");

		if (restClientInterface.isInterface()) {
			return (T) InterfaceProxy.newInstance(restClientInterface);
		} else {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(restClientInterface);
			enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
			enhancer.setCallback(new MethodInterceptorImpl());
			return (T) enhancer.create();
		}
	}

	public Class<?> getObjectType() {
		return restClientInterface;
	}

	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() throws Exception {}
}

class InterfaceProxy implements InvocationHandler {
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("ObjectProxy execute:" + method.getName());
		return method.invoke(proxy, args);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T newInstance(Class<T> innerInterface) {
		ClassLoader classLoader = innerInterface.getClassLoader();
		Class[] interfaces = new Class[] { innerInterface };
		InterfaceProxy proxy = new InterfaceProxy();
		return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
	}
}

class MethodInterceptorImpl implements MethodInterceptor {
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
			throws Throwable {
		System.out.println("MethodInterceptorImpl:" + method.getName());
		return methodProxy.invokeSuper(o, objects);
	}
}
