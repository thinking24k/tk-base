package com.scrt.mi.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.scrt.mi.generator.attribute.DaoAttribute;
import com.scrt.mi.generator.entity.ColumnEntity;
import com.scrt.mi.generator.entity.TableEntity;

/**
 * @ClassName: DbUtils 
 * @Description: jdbc连接数据库工具类
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月1日 
 *
 */
public class DbUtils{
	private Logger logger=Logger.getLogger(DbUtils.class);

	/**
	 * 
	 * @Title: getCon 
	 * @Description: 获得一个 Connection 连接对象
	 * @return 返回存在的Connection 失败返回null
	 */
	public Connection getCon(String url,String user,String pwd){
		String drivre="";
		if(url!=null && url.contains("mysql")){
			drivre=DaoAttribute.MYSQL_DRIVER;
		}
		try {
			Class.forName(drivre);
			return DriverManager.getConnection(url,user,pwd);
		} catch (SQLException e) {
			logger.error("获取conn失败:"+e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.error("反射类失败:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: close 
	 * @Description: 关闭数据库连接
	 * @param rs
	 * @param ps
	 * @param con
	 */
	public void close(ResultSet rs,PreparedStatement ps,Connection con){
		if(null!=rs){
			try{
				rs.close();
			}
			catch(SQLException e){
				logger.error("ResultSet关闭失败："+e.getMessage());
				e.printStackTrace();
			}
		}
		if(null!=ps){
			try{
				ps.close();
			}
			catch(SQLException e){
				logger.error("PreparedStatement关闭失败："+e.getMessage());
				e.printStackTrace();
			}
		}
		if(null !=con){
			try{
				con.close();
			}
			catch(SQLException e){
				logger.error("Connection关闭失败："+e.getMessage());
				e.printStackTrace();
			}
		}

	}
	/**
	 * @Title: getAllTables 
	 * @Description: 获取某数据库所有的表名
	 * @param databaseName
	 * @return List<String>表名集合，没有任何表返回null
	 */
	public List<TableEntity> getAllTables(Connection connection,String databaseName){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TableEntity> lists = new ArrayList<TableEntity>();
		try {
			ps = connection.prepareStatement(DaoAttribute.MYSQL_GETALLTABLESNAMESQL);
			ps.setString(1, databaseName);
			rs = ps.executeQuery();
			TableEntity entity=null;
			while (rs.next()) {
				entity=new TableEntity();
				entity.setTableName(rs.getString("TABLE_NAME"));
				entity.setAnnotations(rs.getString("TABLE_COMMENT"));
				lists.add(entity);
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			close(rs, ps, connection);
		}
		return lists;
	}
	/**
	 * 
	  * @Title: getColumnInfos 
	  * @Description: 获取某表的所有字段信息
	  * @param connection
	  * @param tableName
	  * @return
	 */
	public List<ColumnEntity> getColumnInfos(Connection connection,String databaseName,String tableName){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ColumnEntity> lists = new ArrayList<ColumnEntity>();
		try {
			ps = connection.prepareStatement(DaoAttribute.MYSQL_GETCOLUMNINFOSQL);
			ps.setString(1, databaseName);
			ps.setString(2, tableName);
			rs = ps.executeQuery();
			ColumnEntity columnEntity=null;
			while (rs.next()) {//循环获取数据库字段信息并填充到实体中
				columnEntity=new ColumnEntity();
				columnEntity.setColumnName(rs.getString("COLUMN_NAME"));
				columnEntity.setColumnType(rs.getString("DATA_TYPE"));
				columnEntity.setDatasize(getDataSize(rs.getString("COLUMN_TYPE")));
				String isPK=rs.getString("COLUMN_KEY");
				columnEntity.setIsPrimaryKey(isPK==null?ColumnEntity.NO:isPK.equalsIgnoreCase("PRI")?ColumnEntity.YES:ColumnEntity.NO);
				String isNull=rs.getString("IS_NULLABLE");
				columnEntity.setIsNull(isNull==null?ColumnEntity.NO:isNull.equalsIgnoreCase("YES")?ColumnEntity.YES:ColumnEntity.NO);
				String isAuto=rs.getString("EXTRA");
				columnEntity.setIsAutoIncrement(isAuto==null?ColumnEntity.NO:isAuto.equalsIgnoreCase("auto_increment")?ColumnEntity.YES:ColumnEntity.NO);
				columnEntity.setColumnAnnotations(rs.getString("COLUMN_COMMENT"));
				lists.add(columnEntity);
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} finally {//关闭数据库连接
			close(rs, ps, connection);
		}
		return lists;
	}
	//根据columnType字段取出字段的数据大小
	private Integer getDataSize(String columnType){
		if(StringUtil.isEmpty(columnType)){
			return 0;
		}
		try {
			//如int(11)数据长度为11 而datetime在此会报错，它的数据长度为0
			return Integer.parseInt(columnType.substring(columnType.indexOf("(")+1,columnType.indexOf(")"))) ;
		} catch (Exception e) {
			logger.error("忽略此异常（代码中已进行正常处理）:"+e);
			return 0;
		}
	}

}
