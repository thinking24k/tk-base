package com.xxwl.tk.main.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xxwl.tk.framework.page.Criteria;
import com.xxwl.tk.framework.page.PageBean;
import com.xxwl.tk.main.dao.mapper.UserMapper;
import com.xxwl.tk.main.entity.UserEntity;
import com.xxwl.tk.main.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/*.xml")
public class UserServiceTest {
	@Resource
	private UserService  userService;
	@Resource
	private UserMapper userMapper;
	
	@Test
	public final void testPageQuery() {
		Criteria<UserEntity> criteria=new Criteria<UserEntity>();
		
		PageBean<UserEntity> pageQuery = userService.pageQuery(criteria);
		for (UserEntity userEntity : pageQuery.getData()) {
			System.out.println(userEntity);
		}
	}

	@Test
	public final void testGetCount() {
		Criteria<UserEntity> criteria=new Criteria<UserEntity>();
		long count = userService.getCount(criteria);
		System.out.println(count);
	}

	@Test
	public final void testQueryForList() {
		Criteria<UserEntity> criteria=new Criteria<UserEntity>();
		UserEntity userEntity2 = new UserEntity();
		userEntity2.setNickname("A");
		criteria.setParam(userEntity2);
		List<UserEntity> queryForList = userService.queryForList(criteria);
		for (UserEntity userEntity :queryForList) {
			System.out.println(userEntity);
		}
	}

	@Test
	public final void testQueryForPageList() {
		Criteria<UserEntity> criteria=new Criteria<UserEntity>();
		UserEntity userEntity2 = new UserEntity();
		userEntity2.setNickname("A");
		criteria.setParam(userEntity2);
		List<UserEntity> queryForList = userService.queryForList(criteria);
		for (UserEntity userEntity :queryForList) {
			System.out.println(userEntity);
		}
	}

	@Test
	public final void testGetById() {
		UserEntity userEntity = userService.getById(5);
		System.out.println(userEntity);
	}

	@Test
	public final void testGetByIds() {
		List<Integer> list=new ArrayList<Integer>();
		list.add(4);
		list.add(5);
		list.add(6);
		List<UserEntity> byIds = userService.getByIds(list);
		for (UserEntity userEntity : byIds) {
			System.out.println(userEntity);
		}
	}

	@Test
	public final void testDoAdd() {
		UserEntity e=new UserEntity(null, "AA", null, "123456", "123456", null, 6D, 0, null);
		userService.doAdd(e);
		
	}

	@Test
	public final void testDoAddBatch() {
		//使用代码或不批量
		List<UserEntity> list=new ArrayList<UserEntity>();
		list.add(new UserEntity(null, "AA1", null, "123456", "123456", null, 6D, 0, null));
		list.add(new UserEntity(null, "AA2", null, "123456", "123456", null, 6D, 0, null));
		list.add(new UserEntity(null, "AA3", null, "123456", "123456", null, 6D, 0, null));
		userService.doAddBatch(list);
	}

	@Test
	public final void testDoUpdate() {
		userService.doUpdate(new UserEntity(7, "doUpdate", null, null, null, null, null, null, null));
	}

	@Test
	public final void testDoDelete() {
		userService.doDelete(7);
	}

	@Test
	public final void testDoDeletes() {
		List<Integer> temp=new ArrayList<Integer>();
		temp.add(1);
		temp.add(2);
		temp.add(3);
		temp.add(4);
		userService.doDeletes(temp);
	}

	@Test
	public final void testDoRemove() {
		userService.doRemove(5);
	}

}
