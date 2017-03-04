package com.cat.aop;

public class UserRepositoryImpl implements UserRepository {
	@Override
	public void login() {
		System.out.println("login now!");
	}
}
