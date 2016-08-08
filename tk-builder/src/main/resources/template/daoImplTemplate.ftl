package ${basePackage}.dao.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import ${basePackage}.entity.${entityName};
import ${basePackage}.dao.${tableEntity.className}Dao;
import ${basePackage}.dao.mapper.${tableEntity.className}Mapper;
import com.xxwl.tk.framework.dao.impl.GenericDAOImpl;
import com.xxwl.tk.framework.dao.mapper.BaseMapper;
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
	@Resource
	private ${tableEntity.className}Mapper ${lowercaseClassName}Mapper;

	@Override
	public BaseMapper<${entityName}, Integer> getBaseMapper() {
		return ${lowercaseClassName}Mapper;
	}

}
