package com.scrt.mi.generator.attribute;
/**
 * @ClassName: DaoAttribute 
 * @Description: 持久层用到的基本常量，常与数据库字段对应.
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年6月30日 
 *
 */
public class DaoAttribute {
	/**mysql驱动类*/
	public static final String MYSQL_DRIVER="com.mysql.jdbc.Driver";
	/**mysql获取数据库所有表sql*/
	public static final String MYSQL_GETALLTABLESNAMESQL="select TABLE_NAME,TABLE_COMMENT from information_schema.tables where table_schema=?";
	/**mysql获取某个表的所有字段信息*/
	public static final String MYSQL_GETCOLUMNINFOSQL="select COLUMN_NAME,DATA_TYPE,COLUMN_TYPE,COLUMN_KEY,EXTRA,COLUMN_COMMENT,IS_NULLABLE from information_schema.columns where table_schema=? and table_name = ? ";
	
	/**mssql驱动类*/
	public static final String MSSQL_DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	/**sql if start*/
	public static final String MYBATIS_SQL_IF_START="<if test=\"null != %s\"  >";
	/**sql if end*/
	public static final String MYBATIS_SQL_IF_END="</if>";
	
	
	
}
