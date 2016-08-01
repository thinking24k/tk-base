package com.bqsolo.framework.dao.other;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;




import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @ClassName: CUDTemplate
 * @Description: CRUD MYBATIS 模板类
 * @company
 * @author x
 * @Email x
 * @date x
 * 
 * @param <T>
 */
public class BaseCUDTemplate<T> {
	private static Logger logger = Logger.getLogger(BaseCUDTemplate.class);
	protected static final String KEY_ID = "id";
	protected static final String KEY_ID_LIST = "list";
	protected static final String KEY_ENTITY_CLASS = "class";
	protected static final String KEY_ENTITY = "entity";


	@SuppressWarnings("unchecked")
	public String doSelectById(Map<String, Object> para) {

		Class<T> clazz = (Class<T>) para.get(KEY_ENTITY_CLASS);
		T obj;
		try {
			obj = clazz.newInstance();
			String idname = obj.id();
			obj.caculationColumnList();

			BEGIN();
			SELECT(obj.returnSelectColumnsName());
			FROM(obj.tablename());
			WHERE(idname + "=#{" + KEY_ID + "}");
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("查询记录失败：", e);
			e.printStackTrace();
		}
		String sql = SQL();
		return sql;
	}
	
	@SuppressWarnings("unchecked")
	public String doSelectByIds(Map<String, Object> para){
		Class<T> clazz = (Class<T>) para.get(KEY_ENTITY_CLASS);
		Object idLst =  para.get(KEY_ID_LIST);
		T obj;
		String selectScopeStr;
		try {
			if(idLst instanceof List){
				selectScopeStr = " in("+ListUtil.listToStringUsedBySQL((List)idLst, ",")+")";
			}else{
				selectScopeStr = " = "+ idLst;
			}
			
			obj = clazz.newInstance();
			String idname = obj.id();
			obj.caculationColumnList();

			BEGIN();
			SELECT(obj.returnSelectColumnsName());
			FROM(obj.tablename());
			WHERE(idname + selectScopeStr);
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("查询记录失败：", e);
			e.printStackTrace();
		}
		String sql = SQL();
		return sql;		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public String doSelectByCondition(Map<String, Object> para) {
		return null;
	}	
	

	public String doInsert(T obj) {
		BEGIN();
		INSERT_INTO(obj.tablename());
		obj.caculationColumnList();
		VALUES(obj.returnInsertColumnsName(), obj.returnInsertColumnsDefine());
		String sql = SQL();
		return sql;
	}
	
	/** 
	* @Title: doBatchInsert 
	* @Description: 获取批量新增语句 xuefeng.gao
	* 因Mybaits底层无法支持,So手动写
	* @param objList
	* @return  
	*/ 
	public String doBatchInsert(Map<String, Object> para) {
		StringBuffer sb = new StringBuffer();
		List<T> objList = (List<T>)MapUtil.getValue(para,"list");
		Map<String,String> excludeColumns =  (Map<String,String>)MapUtil.getValue(para,"excludeColumns");
			T obj = objList.get(0);
			obj.caculationColumnList();
			sb.append("insert into ").append(obj.tablename() );
			sb.append("(").append(obj.returnBatchInsertColumnsName(excludeColumns)).append(")");		
			sb.append(" values");
			int index = 0;
			for(T item:objList){
				String itemStr = item.returnBatchInsertColumnsDefine(index,excludeColumns);
				sb.append("(").append(itemStr).append("),");
				index++;				
			}
			String sql=sb.substring(0, sb.lastIndexOf(","));
		return sql;
	}
	
	
	public String doGetPrimaryKey() {
		BEGIN();
		SELECT("LAST_INSERT_ID()");
		String sql = SQL();
		return sql;
	}
	
	
	

	public String doUpdate(T obj) {
		String idname = obj.id();

		BEGIN();

		UPDATE(obj.tablename());
		obj.caculationColumnList();
		SET(obj.returnUpdateSet());
		WHERE(idname + "=#{" + idname + "}");

		String sql = SQL();
		return sql;
	}

	public String doDelete(T obj) {
		String idname = obj.id();

		BEGIN();

		DELETE_FROM(obj.tablename());
		WHERE(idname + "=#{" + idname + "}");

		String sql = SQL();
		return sql;
	}
	
	/** 
	* @Title: doUpdate 
	* @Description: 批量逻辑删除实体对象
	* 1.如果对象属性为NULL，不予更新
	* 2.如果需要清除对象，请置为空 或0.
	* @param obj 实体对象
	* @return  成功行数
	*/ 

	public String doRemove(Map<String, Object> para){		
		T obj = (T)para.get(KEY_ENTITY);
		Object id = para.get(KEY_ID);		

		String deleteScopeStr = null;
		if(id instanceof List){
			deleteScopeStr = " in("+ListUtil.listToStringUsedBySQL((List)id, ",")+")";
		}else{
			deleteScopeStr = " =#{" + KEY_ID + "}";
		}		

		String idname = obj.id();
		
		BEGIN();
		UPDATE(obj.tablename());
		obj.caculationColumnList();
		SET(obj.returnUpdateSet(KEY_ENTITY+"."));
		WHERE(idname + deleteScopeStr);
		String sql = SQL();
		return sql;
	}
	
	

	@SuppressWarnings("unchecked")
	public String doDeleteById(Map<String, Object> para) {
		Class<T> clazz = (Class<T>) para.get(KEY_ENTITY_CLASS);
		Object id = para.get(KEY_ID);
		
		String deleteScopeStr = null;
		if(id instanceof List){
			deleteScopeStr = " in("+ListUtil.listToStringUsedBySQL((List)id, ",")+")";
		}else{
			deleteScopeStr = " =#{" + KEY_ID + "}";
		}
		
		T obj;
		try {
			obj = clazz.newInstance();
			String idname = obj.id();
			BEGIN();
			DELETE_FROM(obj.tablename());
			WHERE(idname + deleteScopeStr);
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("删除记录失败：", e);
			e.printStackTrace();
		}
		String sql = SQL();
		return sql;
	}
	

	

}
