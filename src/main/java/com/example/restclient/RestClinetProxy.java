package com.example.restclient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.client.RestTemplate;

import com.example.common.util.SpringUtils;

public class RestClinetProxy implements InvocationHandler {

	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		if (method.isAnnotationPresent(Get.class)) {
			Get get = AnnotatedElementUtils.findMergedAnnotation(method, Get.class);
			String path = get.value();
			RestTemplate restTemplate = SpringUtils.getBean(RestTemplate.class);
			Map<String, Object> urlVariables = new HashMap<String, Object>();
			Parameter[] param = method.getParameters();
			for (int j = 0; j < param.length; j ++) {
				urlVariables.put(param[j].getName(), args[j]);
			}
			return restTemplate.getForObject("http://127.0.0.1:8888" + path, method.getReturnType(),
					urlVariables);
		}
		return "String";
	}

}
