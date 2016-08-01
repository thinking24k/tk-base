package com.bqsolo.framework.domain;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bqsolo.framework.utils.EntityReflectUtil;
import com.bqsolo.main.utils.http.JsonUtil;



/** 
* @ClassName: ORMEntity 
* @Description: ORM基础映射ENTITY,所有需要存储的DOMAIN必须继承该类
* @company 
* @author xuefeng.gao
* @Email ourjava@qq.com
* @date 2015年6月9日 
*  
*/ 
public class OrmEntity implements Serializable  {

	private static final long serialVersionUID = -6609158926115906312L;
	//允许更新空值的字段
	private Map allowUpdateNullColumn;
	/**
	 * 获取POJO对应的表名 需要POJO中的属性定义@Table(name)
	 * 
	 * @return
	 * @throws Exception 
	 */
	public  String tablename() throws Exception {
		Table table = this.getClass().getAnnotation(Table.class);
		if (table != null)
			return table.name();
		else
			throw new Exception(
					"undefine POJO @Table, need Tablename(@Table(name))");
	}

	/**
	 * 获取POJO中的主键字段名 需要定义@Id
	 * 
	 * @return
	 */
	public String id() {
		for (Field field : this.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Id.class))
				return field.getName();
		}
		throw new RuntimeException("undefine POJO @Id");
	}

	/**
	 * 用于存放POJO的列信息
	 */
	private transient static Map<Class<? extends OrmEntity>, List<String>> columnMap = new HashMap<Class<? extends OrmEntity>, List<String>>();

	protected static Map<Class<? extends OrmEntity>, List<String>> returnColumnMap() {
		return columnMap;
	}

	private boolean isNull(String fieldname) {
		try {
			Field field = EntityReflectUtil.getDeclaredField(this.getClass(),fieldname);
			return isNull(field);
		} catch (SecurityException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean isNull(Field field) {
		try {
			field.setAccessible(true);
			return field.get(this) == null;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 用于计算类定义 需要POJO中的属性定义@Column(name)
	 */
	public void caculationColumnList() {
		if (columnMap.containsKey(this.getClass()))
			return;

		Field[] fields = EntityReflectUtil.getDeclaredFields(this.getClass());
		List<String> columnList = new ArrayList<String>(fields.length);

		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class))
				columnList.add(field.getName());
		}
		columnMap.put(this.getClass(), columnList);
	}

	/**
	 * 获取用于WHERE的 有值字段表
	 * 
	 * @return
	 */
	public List<WhereColumn> returnWhereColumnsName() {
		Field[] fields =EntityReflectUtil.getDeclaredFields(this.getClass());
		List<WhereColumn> columnList = new ArrayList<WhereColumn>(fields.length);

		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class) && !isNull(field))
				columnList.add(new WhereColumn(field.getName(), field
						.getGenericType().equals(String.class)));
		}

		return columnList;
	}

	/**
	 * Where条件信息
	 * 
	 * @author HUYAO
	 * 
	 */
	public class WhereColumn {
		public String name;
		public boolean isString;

		public WhereColumn(String name, boolean isString) {
			this.name = name;
			this.isString = isString;
		}
	}

	/**
	 * 用于获取Insert的字段累加
	 * 
	 * @return
	 */
	public String returnInsertColumnsName() {
		StringBuilder sb = new StringBuilder();

		List<String> list = columnMap.get(this.getClass());
		int i = 0;
		for (String column : list) {
			if (isNull(column))
				continue;

			if (i++ != 0)
				sb.append(',');
			sb.append(column);
		}
		return sb.toString();
	}
	
	/**

	 * @return
	 */
	/** 
	* @Title: returnBatchInsertColumnsName 
	 * 用于获取BatchInsert的字段累加
	 * remark:除ID外,其他column均需defined
	* @param excudeCoumns Key为排除的字段,可为空
	* @return  
	*/ 
	public String returnBatchInsertColumnsName(Map<String,String> excudeCoumns) {
		StringBuilder sb = new StringBuilder();
		List<String> list = columnMap.get(this.getClass());
		int i = 0;
		for (String column : list) {
			if (column.equalsIgnoreCase(id())&&isNull(column)){
				continue;
			}
			if(excudeCoumns!=null&&excudeCoumns.containsKey(column)){
				continue;
			}
			if (i++ != 0)
				sb.append(',');
			sb.append(column);
		}
		return sb.toString();
	}	

	/**
	 * 用于获取Select的字段累加
	 * 
	 * @return
	 */
	public String returnSelectColumnsName() {
		StringBuilder sb = new StringBuilder();

		List<String> list = columnMap.get(this.getClass());
		int i = 0;
		for (String column : list) {
			if (i++ != 0)
				sb.append(',');
			sb.append(column);
		}
		return sb.toString();
	}

	/**
	 * 用于获取Insert的字段映射累加
	 * 
	 * @return
	 */
	public String returnInsertColumnsDefine() {
		StringBuilder sb = new StringBuilder();
		List<String> list = columnMap.get(this.getClass());
		int i = 0;
		for (String column : list) {
			if (isNull(column))
				continue;
			if (i++ != 0)
				sb.append(',');
			sb.append("#{").append(column).append('}');
		}
		return sb.toString();
	}
	
	/** 
	* @Title: returnBatchInsertColumnsDefine 
	* @Description: TODO
	 * 用于获取批量Insert的字段映射累加
	 * remark:即便是空字段(除主键)，也需累加
	* @param index
	* @return  
	*/ 
	public String returnBatchInsertColumnsDefine(int index,Map<String,String> excudeCoumns) {
		StringBuilder sb = new StringBuilder();
		List<String> list = columnMap.get(this.getClass());
		int i = 0;
		for (String column : list) {
			if (column.equalsIgnoreCase(id())&&isNull(column))
				continue;
			if(excudeCoumns!=null&&excudeCoumns.containsKey(column)){
				continue;
			}			
			if (i++ != 0)
				sb.append(',');
			//需要拼成#{list[{0}].columnname}形式
			sb.append("#{").append("list[").append(index).append("].").append(column).append('}');
		}
		return sb.toString();
	}	
	

	/**
	 * 用于获取Update Set的字段累加
	 * 
	 * @return
	 */
	public String returnUpdateSet() {
		return returnUpdateSet("");
	}
	
	/**
	 * 用于获取Update Set的字段累加
	 * @param entityPrefix   表前缀,底层框架用
	 * @return
	 */
	public String returnUpdateSet(String entityPrefix) {
		StringBuilder sb = new StringBuilder();
		if(entityPrefix==null){
			entityPrefix = "";
		}
		List<String> list = columnMap.get(this.getClass());
		int i = 0;
		for (String column : list) {
			if(allowUpdateNullColumn==null||!allowUpdateNullColumn.containsKey(column)){
				if(isNull(column))
					continue;				
			}
			if (i++ != 0)
				sb.append(',');
			sb.append(column).append("=#{").append(entityPrefix).append(column).append('}');
		}
		return sb.toString();
	}	




	public String toJSONString() {
		String jsonStr = null;
		try {
			jsonStr = JsonUtil.convertObject2Json(this, getClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 打印类字段信息
	 */
	@Override
	public String toString() {
		Field[] fields = EntityReflectUtil.getDeclaredFields(this.getClass());
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (Field f : fields) {
			if (Modifier.isStatic(f.getModifiers())
					|| Modifier.isFinal(f.getModifiers()))
				continue;
			Object value = null;
			try {
				f.setAccessible(true);
				value = f.get(this);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null)
				sb.append(f.getName()).append('=').append(value).append(',');
		}
		sb.append(']');

		return sb.toString();
	}

}
