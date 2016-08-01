package com.bqsolo.framework.dao.other;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;



/** 
* @ClassName: BaseMapper 
* @Description: MyBatis CRUD基础接口
* @company 
* @author xuefeng.gao
* @Email ourjava@qq.com
* @date 2014-12-11
* 
* @param <E> 处理的ENTITY对象
*/ 
public interface BaseMapper<E> {
	public static final String KEY_RESULTMAP = "BaseResultMap";
	
	
	/** 
	* @Title: doSelectById 
	* @Description: 根据主键ID查询实体
	* @param id 主键ID
	* @param clazz 实体对象的Class对象
	* @return 成功行数
	*/ 
	@SelectProvider(type = BaseCUDTemplate.class,method = "doSelectById")
    @ResultMap(KEY_RESULTMAP)
	public E doSelectById(@Param("id")Object id,@Param("class") Class<E> clazz);	
	
	/** 
	* @Title: doSelectById 
	* @Description: 根据主键ID查询实体
	* @param id 主键ID
	* @param clazz 实体对象的Class对象
	* @return 成功行数
	*/ 
	
	@SelectProvider(type = BaseCUDTemplate.class,method = "doSelectByIds")
    @ResultMap(KEY_RESULTMAP)
	//	@ResultType(java.util.ArrayList.class)
	public List<E> doSelectByIds(@Param("list")List<?> idLst,@Param("class") Class<E> clazz);		

	/** 
	* @Title: doSelectByCondition 
	* @Description: 根据主键ID查询实体
	* @param id 主键ID
	* @param clazz 实体对象的Class对象
	* @return 成功行数
	*/ 
	@SelectProvider(type = BaseCUDTemplate.class,method = "doSelectByCondition")
    @ResultMap(KEY_RESULTMAP)
	public List<E> doSelectByCondition(@Param("id")Object id,@Param("class") Class<E> clazz);	


	/** 
	* @Title: doInsert 
	* @Description: 新增实体对象
	* @param Entity 实体对象
	* @return 成功行数
	*/ 
	@InsertProvider(type = BaseCUDTemplate.class,method = "doInsert")
	public int doInsert(E Entity);
	
	/** 
	* @Title: doBatchInsert 
	* @Description: 批量新增实体集合
	* @param Entity 批量新增实体集合
	* @return 成功行数
	*/ 
	@InsertProvider(type = BaseCUDTemplate.class,method = "doBatchInsert")
	public int doBatchInsert(@Param("list")List<E> entityList,@Param("excludeColumns")Map<String,String> excludeColumns,@Param("id")Object id);	
	
	
	/** 
	* @Title: doSelectById 
	* @Description: 根据主键ID查询实体
	* @param id 主键ID
	* @param clazz 实体对象的Class对象
	* @return 成功行数
	*/ 
	@SelectProvider(type = BaseCUDTemplate.class,method = "doGetPrimaryKey")
    @ResultType(String.class)
	public String doGetPrimaryKey();	

	/** 
	* @Title: doUpdate 
	* @Description: 更新实体对象
	* 1.如果对象属性为NULL，不予更新
	* 2.如果需要清除对象，请置为空 或0.
	* @param Entity 实体对象
	* @return  成功行数
	*/ 
	@UpdateProvider(type = BaseCUDTemplate.class,method = "doUpdate")
	public int doUpdate(E Entity);
	
	
	/** 
	* @Title: doUpdate 
	* @Description: 批量逻辑删除实体对象
	* 1.如果对象属性为NULL，不予更新
	* 2.如果需要清除对象，请置为空 或0.
	* @param Entity 实体对象
	* @return  成功行数
	*/ 
	@UpdateProvider(type = BaseCUDTemplate.class,method = "doRemove")
	public int doRemove(@Param("id")Object id,@Param("entity") E Entity);
	

	/** 
	* @Title: doDelete 
	* @Description: 根据实体对象删除数据
	* @param Entity 实体对象
	* @return  成功行数
	*/ 
	@DeleteProvider(type = BaseCUDTemplate.class,method = "doDelete")
	public int doDelete(E Entity);
	

	/** 
	* @Title: doDeleteById 
	* @Description: 根据主键ID删除数据
	* @param id 记录主键ID
	* @param clazz 实体对象的Class对象
	* @return 成功行数
	*/ 
	@DeleteProvider(type = BaseCUDTemplate.class,method = "doDeleteById")
	public int doDeleteById(@Param("id")Object id,@Param("class") Class<E> clazz);
	
	
}
