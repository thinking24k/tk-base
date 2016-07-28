package ${basePackage}.dao.impl;

import org.springframework.stereotype.Repository;
import ${basePackage}.entity.${entityName};
import ${basePackage}.dao.${tableEntity.className}Dao;
import com.bqsolo.framework.dao.impl.GenericDAOImpl;
/** 
* @ClassName: ${tableEntity.className}DaoImpl
* @Description: 本类是由代码生成器自动生成${entityName}持久层接口(DaoImpl)
* @company 
* @author ${builderAuthor}
* @Email ${builderEmail}
* @date ${nowDate}
*  
*/ 
@Repository
public class ${tableEntity.className}DaoImpl  extends GenericDAOImpl<${entityName}, Integer> implements ${tableEntity.className}Dao{


}
