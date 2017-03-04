package com.cat.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy extends AbstractAopProxy {

	public CglibProxy(AdvisedSupport advisedSupport) {
		super(advisedSupport);
	}

	@Override
	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		TargetSource targetSource = super.advisedSupport.getTargetSource();
		enhancer.setSuperclass(targetSource.getTargetClass());
		enhancer.setInterfaces(targetSource.getInterfaces());
		enhancer.setCallback(from(super.advisedSupport.getMethodInterceptor()));
		return enhancer.create();
	}

	private MethodInterceptor from(org.aopalliance.intercept.MethodInterceptor methodInterceptor) {
		return (obj, method, args, proxy) -> {
			CglibMethodInvocation invocation = new CglibMethodInvocation(
					super.advisedSupport.getTargetSource().getTarget(), method, args, proxy
			);

			return methodInterceptor.invoke(invocation);
		};
	}

	private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
		private final MethodProxy methodProxy;

		CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy proxy) {
			super(target, method, args);
			this.methodProxy = proxy;
		}

		@Override
		public Object proceed() throws Throwable {
			return this.methodProxy.invoke(super.target, super.arguments);
		}
	}
}
