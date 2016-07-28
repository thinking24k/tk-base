package com.bqsolo.main.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bqsolo.main.dao.mapper.UserMapper;
import com.bqsolo.main.entity.UserEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/*.xml")
public class UserServiceTest {
	@Resource
	private UserService  userService;
	@Resource
	private UserMapper userMapper;
	
	@Test
	public final void testPageQuery() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetCount() {
		fail("Not yet implemented");
	}

	@Test
	public final void testQueryForList() {
		fail("Not yet implemented");
	}

	@Test
	public final void testQueryForPageList() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetByIds() {
		UserEntity userEntity = userMapper.getById(1);
		assertNotNull(userEntity);
	}

	@Test
	public final void testDoAdd() {
		UserEntity e=new UserEntity(null, "BB", null, "123456", "123456", null, 6D, 0, null);
		userService.doAdd(e);
		
	}

	@Test
	public final void testDoAddBatch() {
		fail("Not yet implemented");
	}

	@Test
	public final void testDoUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public final void testDoDelete() {
		fail("Not yet implemented");
	}

	@Test
	public final void testDoDeletes() {
		fail("Not yet implemented");
	}

	@Test
	public final void testDoRemove() {
		fail("Not yet implemented");
	}

}
