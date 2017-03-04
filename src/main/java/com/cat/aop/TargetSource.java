package com.cat.aop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class TargetSource {
	private final Object target;
	private final Class<?> targetClass;
	private final Class<?>[] interfaces;
}
