package com.cat.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy extends AbstractAopProxy implements InvocationHandler {

	public JdkProxy(AdvisedSupport advisedSupport) {
		super(advisedSupport);
	}

	@Override
	public Object getProxy() {
		return Proxy.newProxyInstance(
				this.getClass().getClassLoader(),
				super.advisedSupport.getTargetSource().getInterfaces(),
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return super.advisedSupport.getMethodInterceptor().invoke(
				new ReflectiveMethodInvocation(super.advisedSupport.getTargetSource().getTarget(), method, args)
		);
	}

}
