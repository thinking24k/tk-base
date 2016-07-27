package ${basePackage}.service;

import com.bqsolo.framework.dao.GenericDAO;
import com.bqsolo.framework.page.PageBean;
import ${basePackage}.entity.${entityName};
/** 
* @ClassName: ${tableEntity.className}Service 
* @Description: 本类是由代码生成器自动生成${entityName}逻辑层(Service)
* @company 
* @author ${builderAuthor}
* @Email ${builderEmail}
* @date ${nowDate}
*  
*/ 

public interface ${tableEntity.className}Service extends GenericDAO<${entityName}, Integer> {
	

	public  PageBean<${entityName}> pageQuery(Criteria<${entityName}> criteria);
	
	

}
