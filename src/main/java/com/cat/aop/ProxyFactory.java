package com.cat.aop;

public abstract class ProxyFactory {

	public static AopProxy getJdkAopProxy(AdvisedSupport advisedSupport) {
//		Class<?>[] interfaces = advisedSupport.getTargetSource().getInterfaces();
//		assert interfaces != null && interfaces.length > 0;
		return new JdkProxy(advisedSupport);
	}

	public static AopProxy getCglibAopProxy(AdvisedSupport advisedSupport) {
		return new CglibProxy(advisedSupport);
	}
}
