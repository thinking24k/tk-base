package com.xxwl.tk.main.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.xxwl.tk.main.entity.model.MainPageModel;
import com.xxwl.tk.main.service.PicService;
import com.xxwl.tk.test.SpringBaseTest;

public class PicServiceImplTest extends SpringBaseTest {
	@Resource
	private PicService picService;
	@Test
	public void testQueryMainPageData() {
		List<MainPageModel> queryMainPageData = picService.queryMainPageData(null);
		assertNotNull(queryMainPageData);
	}

}
