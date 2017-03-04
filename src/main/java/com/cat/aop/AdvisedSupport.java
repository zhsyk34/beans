package com.cat.aop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class AdvisedSupport {
	private final TargetSource targetSource;
	private final MethodInterceptor methodInterceptor;
	//TODO
	//private MethodMatcher methodMatcher;
}
