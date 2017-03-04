package com.cat.aop;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
abstract class AbstractAopProxy implements AopProxy {
	final AdvisedSupport advisedSupport;
}
