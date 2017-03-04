package com.cat.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyFactoryTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MethodInterceptor methodInterceptor;

	@Before
	public void before() throws Exception {
		methodInterceptor = invocation -> {
			logger.debug("before");
			Object result = invocation.proceed();
			logger.debug("after");
			return result;
		};
	}

	private AdvisedSupport target() {
		TargetSource targetSource = TargetSource.of(new UserRepositoryImpl(), UserRepositoryImpl.class, new Class<?>[]{UserRepository.class});
		return AdvisedSupport.of(targetSource, methodInterceptor);
	}

	private AdvisedSupport target2() {
		TargetSource targetSource = TargetSource.of(new RoleRepositoryImpl(), RoleRepositoryImpl.class, null);
		return AdvisedSupport.of(targetSource, methodInterceptor);
	}

	@Test
	public void getJdkAopProxy() throws Exception {
		AopProxy factory = ProxyFactory.getJdkAopProxy(target2());
		UserRepository proxy = (UserRepository) factory.getProxy();
		proxy.login();
	}

	@Test
	public void getCglibAopProxy() throws Exception {
		AopProxy factory = ProxyFactory.getCglibAopProxy(target2());
		UserRepository proxy = (UserRepository) factory.getProxy();
		proxy.login();
	}

	@Test
	public void getJdkAopProxy2() throws Exception {
		AopProxy factory = ProxyFactory.getJdkAopProxy(target2());
		RoleRepositoryImpl proxy = (RoleRepositoryImpl) factory.getProxy();
		proxy.registy();
	}

	@Test
	public void getCglibAopProxy2() throws Exception {
		AopProxy factory = ProxyFactory.getCglibAopProxy(target2());
		RoleRepositoryImpl proxy = (RoleRepositoryImpl) factory.getProxy();
		proxy.registy();
	}

}