package com.xxwl.tk.main.service;

import java.util.Map;

import org.junit.Test;

import com.xxwl.tk.framework.utils.MapUtil;
import com.xxwl.tk.main.entity.UserEntity;

public class MapTest {
	@Test
	public final void testDoMap() {
		 Map<String, Object> temp = MapUtil.getValue(new UserEntity(null, "AA1", null, "123456", "123456", null, 6D, 0, null) );
		for (String s : temp.keySet()) {
			System.out.println(temp.get(s));
		}
		
	}
}
