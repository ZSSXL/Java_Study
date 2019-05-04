package com.zss.service.impl;

import com.zss.service.TestService;

public class TestServiceImpl implements TestService{

	@Override
	public void Test() {
		System.out.println("Git测试");
	}

	@Override
	public void update() {
		System.out.println("update，更新测试");
	}
	
	public void UpdateToLocal() {
		System.out.println("update to local!!");
	}

}
