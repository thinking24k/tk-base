package com.tk.base.builder.util;

import com.tk.base.builder.attribute.DaoAttribute;

public class SqlBuilder {
	/**
	 * 
	 * @Title: sqlAppendIF 
	 * @Description: 给sql 添加if
	 * @param name
	 * @param isColumn
	 * @return
	 */
	public static String sqlAppendIF(String name,boolean isColumn){
		StringBuilder sb=new StringBuilder(String.format(DaoAttribute.MYBATIS_SQL_IF_START, name));
		if(isColumn){
			sb.append(name);
			sb.append(",");
		}else{//参数
			sb.append("#{");
			sb.append(name);
			sb.append("},");
		}
		sb.append(DaoAttribute.MYBATIS_SQL_IF_END);
		sb.append("\n");
		return sb.toString();
	}
	/**
	 * 
	 * @Title: sqlAppend 
	 * @Description: 追加sql
	 * @param name
	 * @param isColumn
	 * @return
	 */
	public static String sqlAppend(String name,boolean isColumn){
		if(isColumn){
			return name+",";
		}else{//参数
			return "#{"+name+"},";
		}
	}
	/**
	 * 
	 * @Title: sqlDelLastdou 
	 * @Description: 删除sql中结尾的逗号
	 * @param sql
	 * @return
	 */
	public static String sqlDelLastdou(String sql){
		if(null==sql || sql.trim().equals("")){
			return sql;
		}
		if(sql.contains(",")){
			return  new StringBuilder(sql).deleteCharAt(sql.lastIndexOf(",")).toString();
		}
		return sql;
	}
	
}
